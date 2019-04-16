package readxmlfile;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayDeque;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

public class Conhecidos {

    private String person;
    private String colleague;

    public Conhecidos() {
    }

    public Conhecidos(String person, String colleague) {
        this.person = person;
        this.colleague = colleague;
    }

    public String getPerson() {
        return person;
    }

    public String getColleague() {
        return colleague;
    }

    public void setPerson(String person) {
        this.person = person;
    }

    public void setColleague(String colleague) {
        this.colleague = colleague;
    }

    public static ArrayDeque xmlparseConhecidos(String url) throws MalformedURLException, ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(new URL(url).openStream());

        //optional, but recommended
        //read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
        doc.getDocumentElement().normalize();

        NodeList nList = doc.getElementsByTagName("Knows");
        ArrayDeque<Conhecidos> list = new ArrayDeque();
        String[] buffer1, buffer2;

        for (int temp = 0; temp < nList.getLength(); temp++) {

            Node nNode = nList.item(temp);

            list.add(new Conhecidos());

            if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                Element eElement = (Element) nNode;

                buffer1 = eElement.getAttribute("person").split("/CSB30/2018/2/");
                buffer2 = eElement.getAttribute("colleague").split("/CSB30/2018/2/");

                list.peekLast().setPerson(buffer1[1]);
                list.peekLast().setColleague(buffer2[1]);

            }
        }

        return list;
    }

}