/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parser;

import org.xml.sax.SAXException;

/**
 *
 * @author rhau
 */
public class MySAXTerminatorException extends SAXException {
    
    public MySAXTerminatorException(String str) {
        super(str);
    }
    
}