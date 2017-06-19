/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;

/**
 *
 * @author rhau
 */
public class CriaTxtsApartirXml {

    public static void main(String[] args) {
        Integer count = 0;
        Integer numTxt = 0;
        final Integer titulosPorTxt = 2000;
        final String path = "~/Desktop/dblp-2016.txt";
        StringBuilder sb = new StringBuilder();
        BufferedReader br;    
        try {
            br = new BufferedReader(new FileReader(path));
           
            try {
                String x = br.readLine();
                while (x != null) {
                    if (x.contains("<article")) {
                        while (x != null) {
                            x = br.readLine();
                            if (x.contains("<title")) {
                                String titulo = x.replace("<title>", "");
                                titulo = titulo.replace("</title>", "");
                                sb.append(titulo).append("\n");
                                if (Objects.equals(++count, titulosPorTxt)) {
                                    criar_aquivo(sb, ++numTxt);
                                    sb.delete(0, sb.length());
                                    count = 0;
                                }
                                break;
                            }

                        }
                    }
                    x = br.readLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }

    private static void criar_aquivo(StringBuilder sb, Integer integer) {
        try {
            FileWriter arquivo;
            arquivo = new FileWriter(new File("~/Desktop/arquivos/articles" + integer));
            arquivo.write(sb.toString());
            arquivo.close();
        } catch (IOException ex) {
            System.out.println("Falha ao criar o arquivo " + integer + ".");
        }
    }
    
}
