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

public class Filme {

    private String person;
    private int rating;
    private String movieUri;

    public Filme() {
    }

    public Filme(String person, int rating, String movieUri) {
        this.person = person;
        this.rating = rating;
        this.movieUri = movieUri;
    }

    public String getPerson() {
        return person;
    }

    public int getRating() {
        return rating;
    }

    public String getMovieUri() {
        return movieUri;
    }

    public void setPerson(String person) {
        this.person = person;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public void setMovieUri(String movieUri) {
        this.movieUri = movieUri;
    }

    public static ArrayDeque xmlparseFilme(String url) throws MalformedURLException, ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(new URL(url).openStream());

        //optional, but recommended
        //read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
        doc.getDocumentElement().normalize();

        NodeList nList = doc.getElementsByTagName("LikesMovie");
        ArrayDeque<Filme> list = new ArrayDeque();
        String[] buffer;

        for (int temp = 0; temp < nList.getLength(); temp++) {

            Node nNode = nList.item(temp);

            list.add(new Filme());

            if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                Element eElement = (Element) nNode;

                buffer = eElement.getAttribute("person").split("/CSB30/2018/2/");

                list.peekLast().setPerson(buffer[1]);
                list.peekLast().setRating(Integer.parseInt(eElement.getAttribute("rating")));
                list.peekLast().setMovieUri(eElement.getAttribute("movieUri"));
            }
        }

        return list;
    }

}