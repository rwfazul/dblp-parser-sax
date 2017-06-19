/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.io.FileWriter;
import java.io.IOException;
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
public class GetCorpoTags extends DefaultHandler {
    
    private String txtPath;
    private String tagAtual;
    private String tipoBuscado;
    private String tagBusca;
    private Boolean tipoEncontrado = false;
    private Integer qtTitulos = 0;
    private StringBuilder sb = new StringBuilder();

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
   
    public GetCorpoTags(String tipoBuscado, String tagBusca, String txtPath) {
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
        if (tipoBuscado.equals(qName)) { 
            tipoEncontrado = true;
            sb.append("<article>\n");
        }
                    
        tagAtual = qName;
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (tipoEncontrado) {
            sb.append("  <").append(tagAtual).append(">");
            sb.append(new String(ch, start, length));   
            sb.append("<\\").append(tagAtual).append(">\n");
        }     
    }
    
    @Override
    public void endElement(String uri, String localName, String qName)
            throws SAXException {
        if (tipoEncontrado) {
            if (tipoBuscado.equals(qName)) { 
                tipoEncontrado = false;
                sb.append("<\\").append(qName).append(">\n\n");
                if (Objects.equals(++qtTitulos, 2000)) {
                    apendTxt(sb);
                    sb.delete(0, sb.length());
                    qtTitulos = 0;
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
            Logger.getLogger(GetCorpoTags.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }

}
