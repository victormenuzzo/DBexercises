/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import java.io.PrintStream;

public class ReadXMLFile {

    public static void main(String argv[]) {

        try {

            File fXmlFile = new File("marvel_simplificado.xml");
            File dir = new File("dadosMarvel"); //criação da pasta dadosMarvel que foi solicitada para inserir os arquivos
            dir.mkdir();
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);

            //optional, but recommended
            //read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
            doc.getDocumentElement().normalize();

            //System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
            NodeList nList = doc.getElementsByTagName("hero");

            System.out.println("----------------------------");

            PrintStream output_good = new PrintStream("dadosMarvel/herois_good.csv"), 
                    output_bad = new PrintStream("dadosMarvel/herois_bad.csv"), 
                    output_all = new PrintStream("dadosMarvel/herois.csv"); 
            int nGood = 0, nBad = 0, total = nList.getLength();

            double weightHulk = 0.0, heightHulk = 0.0, totalWeight = 0.0; //variaveis utilizadas para realizar as contas de imc e porcentagem bons/ruins   

            for (int temp = 0; temp < nList.getLength(); temp++) {

                Node nNode = nList.item(temp);

                //System.out.println("\nCurrent Element :" + nNode.getNodeName());
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element eElement = (Element) nNode; 

                    if (eElement.getElementsByTagName("alignment").item(0).getTextContent().compareTo("Good") == 0) { //verifica se o personagem é bom e escreve no arquivo dos bons os dados do personagem
                        output_good.print(eElement.getAttribute("id") + ", "
                                + eElement.getElementsByTagName("name").item(0).getTextContent() + ", "
                                + eElement.getElementsByTagName("popularity").item(0).getTextContent() + ", "
                                + eElement.getElementsByTagName("alignment").item(0).getTextContent() + ", "
                                + eElement.getElementsByTagName("gender").item(0).getTextContent() + ", "
                                + eElement.getElementsByTagName("height_m").item(0).getTextContent() + ", "
                                + eElement.getElementsByTagName("weight_kg").item(0).getTextContent() + ", "
                                + eElement.getElementsByTagName("hometown").item(0).getTextContent() + ", "
                                + eElement.getElementsByTagName("intelligence").item(0).getTextContent() + ", "
                                + eElement.getElementsByTagName("strength").item(0).getTextContent() + ", "
                                + eElement.getElementsByTagName("speed").item(0).getTextContent() + ", "
                                + eElement.getElementsByTagName("durability").item(0).getTextContent() + ", "
                                + eElement.getElementsByTagName("energy_Projection").item(0).getTextContent() + ", "
                                + eElement.getElementsByTagName("fighting_Skills").item(0).getTextContent() + "\n");

                        nGood++;
                    } else if (eElement.getElementsByTagName("alignment").item(0).getTextContent().compareTo("Bad") == 0) { //verifica se o personagem é ruim escreve no arquivo dos bons os dados do personagem
                        output_bad.print(eElement.getAttribute("id") + ", "
                                + eElement.getElementsByTagName("name").item(0).getTextContent() + ", "
                                + eElement.getElementsByTagName("popularity").item(0).getTextContent() + ", "
                                + eElement.getElementsByTagName("alignment").item(0).getTextContent() + ", "
                                + eElement.getElementsByTagName("gender").item(0).getTextContent() + ", "
                                + eElement.getElementsByTagName("height_m").item(0).getTextContent() + ", "
                                + eElement.getElementsByTagName("weight_kg").item(0).getTextContent() + ", "
                                + eElement.getElementsByTagName("hometown").item(0).getTextContent() + ", "
                                + eElement.getElementsByTagName("intelligence").item(0).getTextContent() + ", "
                                + eElement.getElementsByTagName("strength").item(0).getTextContent() + ", "
                                + eElement.getElementsByTagName("speed").item(0).getTextContent() + ", "
                                + eElement.getElementsByTagName("durability").item(0).getTextContent() + ", "
                                + eElement.getElementsByTagName("energy_Projection").item(0).getTextContent() + ", "
                                + eElement.getElementsByTagName("fighting_Skills").item(0).getTextContent() + "\n");

                        nBad++;
                    }

                    output_all.print(eElement.getAttribute("id") + ", "
                            + eElement.getElementsByTagName("name").item(0).getTextContent() + ", "
                            + eElement.getElementsByTagName("popularity").item(0).getTextContent() + ", "
                            + eElement.getElementsByTagName("alignment").item(0).getTextContent() + ", "
                            + eElement.getElementsByTagName("gender").item(0).getTextContent() + ", "
                            + eElement.getElementsByTagName("height_m").item(0).getTextContent() + ", "
                            + eElement.getElementsByTagName("weight_kg").item(0).getTextContent() + ", "
                            + eElement.getElementsByTagName("hometown").item(0).getTextContent() + ", "
                            + eElement.getElementsByTagName("intelligence").item(0).getTextContent() + ", "
                            + eElement.getElementsByTagName("strength").item(0).getTextContent() + ", "
                            + eElement.getElementsByTagName("speed").item(0).getTextContent() + ", "
                            + eElement.getElementsByTagName("durability").item(0).getTextContent() + ", "
                            + eElement.getElementsByTagName("energy_Projection").item(0).getTextContent() + ", "
                            + eElement.getElementsByTagName("fighting_Skills").item(0).getTextContent() + "\n");

                    if (eElement.getElementsByTagName("name").item(0).getTextContent().compareTo("Hulk") == 0) { //dados para o imc do hulk
                        weightHulk = Double.parseDouble(eElement.getElementsByTagName("weight_kg").item(0).getTextContent()); //transforma o texto referente ao peso em double
                        heightHulk = Double.parseDouble(eElement.getElementsByTagName("height_m").item(0).getTextContent()); //transforma o texto referente â altura em double 	  
                    }

                    totalWeight += Double.parseDouble(eElement.getElementsByTagName("weight_kg").item(0).getTextContent()); //transforma o texto referente ao peso em double, faz a soma total               
                }
            }
            System.out.println("Proporção de heróis bons/maus:" + nBad + "/" + nGood + " = " + ((float) nBad / nGood) + "\n"); // printa na tela a proporcao de herois bons em relacao aos maus
            System.out.println("Proporção de heróis bons em relacao ao total:" + nGood + "/" + total + " = " + ((float) nGood / total) * 100 + "%" + "\n"); //printa na tela a proporcao de herois bons em porcentagem
            System.out.println("Proporção de heróis maus em relacao ao total:" + nBad + "/" + total + " = " + ((float) nBad / total) * 100 + "%" + "\n"); // printa na tela a proporcao de herois maus em porcentagem
            System.out.println("Média de peso:" + totalWeight + "/" + total + " = " + totalWeight / total + "\n"); //calcula e printa na tela a media de peso dos herois
            System.out.println("IMC Hulk:" + weightHulk + " / " + heightHulk + " x " + heightHulk + " = " + weightHulk / (heightHulk * heightHulk)); //calcula e printa na tela o IMC do hulk
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
