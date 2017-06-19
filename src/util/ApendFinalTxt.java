/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import parser.ParserXmlSax;

/**
 *
 * @author rhau
 */
public class ApendFinalTxt {
    
    public static void main(String[] args) {
        final String path = "~/Desktop/aqui.txt/";
        int i = 0;
        while (i < 1000) {
            FileWriter fw= null;
            try {
                fw = new FileWriter(path, true);
                String apendFinal = "apend no final num " + i + "\n";
                fw.write(apendFinal);
                fw.close();
                i++;
            } catch (IOException ex) {
                Logger.getLogger(ParserXmlSax.class.getName()).log(Level.SEVERE, null, ex);
            }  
        }
    }
    
}
