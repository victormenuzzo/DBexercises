from htmldom import htmldom
import psycopg2.extras
from xml.etree.ElementTree import Element, SubElement, tostring, Comment
from xml.dom import minidom
import requests
import wikipedia


log = open("log_movie.txt", "w+")

# Defining Functions
def print_progess(iteration, total, prefix='', suffix='', length=20, fill='█'):
    percent = float(iteration / total) * 100

    filled = int(length * iteration / total)
    bar = fill * filled + '-' * (length - filled)
    print('\r                                                                                             ', end='\r')
    print('\r %s |%s| %.2f%% %s' % (prefix, bar, percent, suffix), end='\r')

    return


def prettify(elem):
    rough_string = tostring(elem, 'utf-8')
    reparsed = minidom.parseString(rough_string)

    return reparsed.toprettyxml(indent="  ")


def insert_node(root_insert_node, string, attr):
    node = SubElement(root_insert_node, string)
    if attr is not None:
        value = SubElement(node, "value")
        value.text = attr

    return node


def insert_mv_node(root_insert_mv, string, attr):
    node = SubElement(root_insert_mv, string)
    for elem in attr:
        value = SubElement(node, "value")
        value.text = elem

    return node


def get_genres(parse_tree):
    element_list = []
    for node in parse_tree.find("div.subtext>a[href]"):
        if node.attr("href").split("=")[0] == "/search/title?genres":
            element_list.append(node.text()[:-1])

    return element_list


def get_date(parse_tree):
    node = parse_tree.find("div.subtext>a[title]")
    element = node.text()[:-1]

    return element


def get_dir(parse_tree):
    for node in parse_tree.find("div.credit_summary_item>h4.inline"):
        if "Director" in node.text() or "Diretor" in node.text():
            return node.next().text()

    return


# END_Defining Functions

# Trying to connect
print("Connecting to database...")
try:
    conn = psycopg2.connect("dbname='1802Viludani' user='1802Viludani' host='200.134.10.32' password='578769'")
    cur = conn.cursor(cursor_factory=psycopg2.extras.DictCursor)
    try:
        cur.execute("SELECT * FROM filme;")
        rows = cur.fetchall()

        lenght = len(rows)
        count = 0

        # Setting up xml
        root = Element('Movies')
        root.set('version', '1.1')
        root.append(Comment('Projeto 06 - Banco de Dados - Viludani'))

        # Generating and Parsing DOM
        for row in rows:
            try:
                # Setting up to request the english version
                headers = {"Accept-Language": "en-US,en;q=0.5"}
                src = requests.get(row[0], headers=headers)

                tree = htmldom.HtmlDom()
                tree = tree.createDom(src.text)

                # HTML parse
                name = tree.find("head > title").text().split(' - ')[0]
                if ' (' in name:
                    name = name.split(' (')[0]

                count = count + 1
                try:
                    movie_elem = insert_node(root, 'movie', None)
                    insert_node(movie_elem, "uri", row[0])
                    insert_node(movie_elem, "name", name)
                    insert_mv_node(movie_elem, 'genre', get_genres(tree))
                    insert_node(movie_elem, 'release', get_date(tree))
                    insert_node(movie_elem, 'director', get_dir(tree))

                    try:
                        info = wikipedia. .page(name)
                        insert_node(movie_elem, 'info', info.summary)
                        insert_node(movie_elem, 'more_info', info.url)
                    except Exception as e:
                        log.write(str(e))

                except Exception as e:
                    log.write("ERROR: Generating XML elements")
                    log.write(str(e))

                # print_progess
                print_progess(count, lenght, "Parsing Files", name)

            except Exception as e:
                log.write("ERROR: While parsing Artists")
                log.write(str(e))

        print("")

        try:
            # Generating the output file
            output_file = open("movie.xml", "w+")
            print("Generating the output file...")
            output_file.write(prettify(root))
            output_file.close()
        except Exception as e:
            log.write("ERROR: Generating output")
            log.write(str(e))

    except Exception as e:
        log.write("ERROR: Can't SELECT from artistasmusicais")
        log.write(str(e))

except Exception as e:
    log.write("ERROR: unable to connect to the database.")
    log.write(str(e))

log.close()
