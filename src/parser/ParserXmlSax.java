/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parser;

import java.io.FileWriter;
import java.io.IOException;
import static java.lang.System.exit;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

// http://stackoverflow.com/questions/26310595/how-to-parse-big-50-gb-xml-files-in-java
/**
 *
 * @author rhau
 */
public class ParserXmlSax extends DefaultHandler {
    
    private String txtPath;
    private String tagAtual;
    private String tipoBuscado;
    private String tagBusca;
    private Boolean tipoEncontrado = false;
    // private Boolean author = false;
    private Integer qtTitulos = 0;
    private StringBuilder sb = new StringBuilder();
    // private StringBuilder autorIndividual = new StringBuilder();
    // private StringBuilder autores = new StringBuilder();

    public String getTxtPath() {
        return txtPath;
    }

    public void setTxtPath(String txtPath) {
        this.txtPath = txtPath;
    }
    
    public String getTipoBuscado() {
        return tipoBuscado;
    }

    public void setTipoBuscado(String tipoBuscado) {
        this.tipoBuscado = tipoBuscado;
    }

    public String getTagBusca() {
        return tagBusca;
    }

    public void setTagBusca(String tagBusca) {
        this.tagBusca = tagBusca;
    }
   
    public ParserXmlSax(String tipoBuscado, String tagBusca, String txtPath) {
        super();
        setTipoBuscado(tipoBuscado);
        setTagBusca(tagBusca);
        setTxtPath(txtPath);
    }

    public void fazerParsing(String xmlPath) {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser saxParser;
        try {
            saxParser = factory.newSAXParser();
            saxParser.parse(xmlPath, this);
        } catch (ParserConfigurationException | IOException e) {
            StringBuffer msg = new StringBuffer();
            msg.append("Erro:\n");
            msg.append(e.getMessage()).append("\n");
            msg.append(e.toString());
            System.out.println(msg);
     
        } catch (SAXException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void startDocument() {
        System.out.println("\nIniciando o Parsing...\n");
    }

    @Override
    public void endDocument() {
        System.out.println("\nFim do Parsing...");
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes atts) {
        if (tipoBuscado.equals(qName)) 
            tipoEncontrado = true;
        tagAtual = qName;
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (tipoEncontrado) {
            if (tagAtual.equals(tagBusca)) 
                sb.append(new String(ch, start, length));  
            /*else if (tagAtual.equals("author")) {
                autorIndividual.append(new String(ch, start, length));
                author = true;
            }*/
        }     
    }
    
    @Override
    public void endElement(String uri, String localName, String qName)
            throws SAXException {
        if (tipoEncontrado) {
             /*if (author) {
                author = false;
                autores.append(" author:\"").append(autorIndividual.toString()).append("\"");
                autorIndividual.delete(0, autorIndividual.length());
            }
            else */ 
            if (tipoBuscado.equals(qName)) { 
                tipoEncontrado = false;
                // sb.append(autores.toString());
                // autores.delete(0, autores.length());
                sb.append("\n\n");
                if (Objects.equals(++qtTitulos, 2000)) {
                    apendTxt(sb); 
                    sb.delete(0, sb.length());
                    qtTitulos = 0;
                    exit(0);
                } 
            }
        } 
        tagAtual = "";
    }

    public void apendTxt(StringBuilder sb) {
        FileWriter fw= null;
        try {
            fw = new FileWriter(txtPath, true);
            fw.write(sb.toString());
            fw.close();
        } catch (IOException ex) {
            Logger.getLogger(ParserXmlSax.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }

}
