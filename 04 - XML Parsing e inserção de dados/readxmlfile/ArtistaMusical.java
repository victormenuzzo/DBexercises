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

public class ArtistaMusical {

    private String person;
    private int rating;
    private String bandUri;

    public ArtistaMusical() {
    }

    public ArtistaMusical(String person, int rating, String bandUri) {
        this.person = person;
        this.rating = rating;
        this.bandUri = bandUri;
    }

    public String getPerson() {
        return person;
    }

    public int getRating() {
        return rating;
    }

    public String getBandUri() {
        return bandUri;
    }

    public void setPerson(String person) {
        this.person = person;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public void setBandUri(String bandUri) {
        this.bandUri = bandUri;
    }

    public static ArrayDeque xmlparseArtistaMusical(String url) throws MalformedURLException, ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(new URL(url).openStream());

        //optional, but recommended
        //read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
        doc.getDocumentElement().normalize();

        NodeList nList = doc.getElementsByTagName("LikesMusic");
        ArrayDeque<ArtistaMusical> list = new ArrayDeque();
        String[] buffer;

        for (int temp = 0; temp < nList.getLength(); temp++) {

            Node nNode = nList.item(temp);

            list.add(new ArtistaMusical());

            if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                Element eElement = (Element) nNode;

                buffer = eElement.getAttribute("person").split("/CSB30/2018/2/");

                list.peekLast().setPerson(buffer[1]);
                list.peekLast().setRating(Integer.parseInt(eElement.getAttribute("rating")));
                list.peekLast().setBandUri(eElement.getAttribute("bandUri"));
            }
        }

        return list;
    }

}