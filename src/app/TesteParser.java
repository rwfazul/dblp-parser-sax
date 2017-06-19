/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import parser.ParserXmlSax;

/**
 *
 * @author rhau
 */
public class TesteParser {
    
    public static void main(String[] args) {
        String tipoBuscado = "article";
        String tagBusca = "title";
        
        System.setProperty("entityExpansionLimit", "10000000");
        String xmlPath = "~/Desktop/dblp-2016-11-02.xml";
        String txtPath = "~/Desktop/articleTitles.txt"; 
  
        ParserXmlSax mydevSAX = new ParserXmlSax(tipoBuscado, tagBusca, txtPath);
        mydevSAX.fazerParsing(xmlPath);
    }
    
}
