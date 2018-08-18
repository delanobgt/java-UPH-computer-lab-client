/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author irvin
 */
public class Utility {

    public static String getStudentAlias(String name) {
        StringBuilder sb = new StringBuilder();
        String[] tokens = name.split(" +");
        for (int i = 0; i < Math.min(2, tokens.length); i++) {
            sb.append(Character.toUpperCase(tokens[i].charAt(0)));
        }
        return sb.toString();
    }

    public static String getSentenceCase(String sentence) {
        StringBuilder sb = new StringBuilder();
        String[] tokens = sentence.split(" +");
        for (int i = 0; i < tokens.length; i++) {
            if (i > 0) {
                sb.append(' ');
            }
            sb.append(Character.toUpperCase(tokens[i].charAt(0)));
            sb.append(tokens[i].substring(1).toLowerCase());
        }
        return sb.toString();
    }
    
    public static void sleep(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException ex) {
            Logger.getLogger(Utility.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
