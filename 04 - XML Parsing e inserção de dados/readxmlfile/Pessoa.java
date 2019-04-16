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

public class Pessoa {

    private String login;
    private String nome;
    private String cidadeNatal;
    private String nascimento;

    public Pessoa() {
    }

    public Pessoa(String login, String nome, String cidadeNatal, String nascimento) {
        this.login = login;
        this.nome = nome;
        this.cidadeNatal = cidadeNatal;
        this.nascimento = nascimento;
    }

    public String getLogin() {
        return login;
    }

    public String getNome() {
        return nome;
    }

    public String getCidadeNatal() {
        return cidadeNatal;
    }

    public String getNascimento() {
        return nascimento;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCidadeNatal(String cidadeNatal) {
        this.cidadeNatal = cidadeNatal;
    }

    public void setNascimento(String nascimento) {
        this.nascimento = nascimento;
    }

    public static ArrayDeque xmlparsePerson(String url) throws MalformedURLException, ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(new URL(url).openStream());

        //optional, but recommended
        //read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
        doc.getDocumentElement().normalize();

        NodeList nList = doc.getElementsByTagName("Person");
        ArrayDeque<Pessoa> list = new ArrayDeque();
        String[] buffer;

        for (int temp = 0; temp < nList.getLength(); temp++) {

            Node nNode = nList.item(temp);

            list.add(new Pessoa());

            if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                Element eElement = (Element) nNode;

                buffer = eElement.getAttribute("uri").split("/CSB30/2018/2/");

                list.peekLast().setLogin(buffer[1]);
                list.peekLast().setNome(eElement.getAttribute("name"));
                list.peekLast().setCidadeNatal(eElement.getAttribute("hometown"));
                list.peekLast().setNascimento(eElement.getAttribute("birthdate"));
            }
        }

        return list;
    }

}