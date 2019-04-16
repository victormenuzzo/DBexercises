from htmldom import htmldom
import psycopg2.extras
from xml.etree.ElementTree import Element, SubElement, tostring, Comment
from xml.dom import minidom
import itunes


log = open("log_music.txt", "w+")

# Defining Functions
def print_progess(iteration, total, prefix='', suffix='', length=20, fill='█'):
    
    percent = float(iteration / total)*100
    
    filled = int(length * iteration / total)
    bar = fill * filled + '-' * (length - filled)
    print('\r %s |%s| %.2f%% %s               ' % (prefix, bar, percent, suffix), end='\r')
    
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


def get_table(parse_tree, string):
    
    element_list = []
    for node in parse_tree.find("th"):
        if node.text() == string:
            elements = node.next()
            for element in elements.find("a"):
                if "&#" not in element.text():
                    element_list.append(element.text())
                    
    return element_list


# END_Defining Functions

# Trying to connect
print("Connecting to database...")
try:
    conn = psycopg2.connect("dbname='1802Viludani' user='1802Viludani' host='200.134.10.32' password='578769'")
    cur = conn.cursor(cursor_factory=psycopg2.extras.DictCursor)
    try:
        cur.execute("SELECT * FROM artistasmusicais;")
        rows = cur.fetchall()

        lenght = len(rows)
        count = 0

        # Setting up xml
        root = Element('Artists')
        root.set('version', '1.2')
        root.append(Comment('Projeto 06 - Banco de Dados - Viludani'))

        # Generating and Parsing DOM
        for row in rows:
            try:
                tree = htmldom.HtmlDom(row[0])
                tree = tree.createDom()

                # HTML parse
                name = tree.find("head > title").text().split(' - ')[0]
                if ' (' in name:
                    name = name.split(' (')[0]

                origin = get_table(tree, "Origin")
                hometown = get_table(tree, "Born")
                members = get_table(tree, "Members")
                genres = get_table(tree, "Genres")

                count = count + 1
                try:
                    artist_elem = insert_node(root, 'artist', None)
                    insert_node(artist_elem, "uri", row[0])
                    insert_node(artist_elem, "name", name)
                    insert_mv_node(artist_elem, "origin", origin)
                    insert_mv_node(artist_elem, "hometown", hometown)
                    insert_mv_node(artist_elem, "members", members)
                    insert_mv_node(artist_elem, "genres", genres)

                    try:
                        albums = []
                        it_artist = itunes.search_artist(name)[0]
                        for album in it_artist.get_albums():
                            albums.append(album.name)

                        insert_mv_node(artist_elem, 'albums', albums)
                    except Exception as e:
                        log.write("ERROR: Itune API")
                        log.write(str(e))

                except Exception as e:
                    log.write("ERROR: Generating XML elements")
                    log.write(str(e))

                # Printing Progess
                print_progess(count, lenght, "Parsing Files", name)

            except Exception as e:
                log.write("ERROR: While parsing Artists")
                log.write(str(e))

        print("")

        try:
            # Generating the output file
            output_file = open("music.xml", "w+")
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
