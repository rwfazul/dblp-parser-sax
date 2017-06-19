/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author rhau
 */
public class XmlDomComNode {

    public static void main(String[] args) {
        XmlDomComNode test = new XmlDomComNode();
        test.readXML();
    }

    private void readXML() {
        final String path = "~/Desktop/dblp-2016-11-02.xml";
        Document doc = null;
        try {
            doc = parseXML(path);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (doc != null) {
            NodeList nList = doc.getElementsByTagName("title");
            System.out.println(nList.getLength());
        }
    }

    private Document parseXML(String filePath) throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse(filePath);
        doc.getDocumentElement().normalize();
        return doc;
    }
    
}
