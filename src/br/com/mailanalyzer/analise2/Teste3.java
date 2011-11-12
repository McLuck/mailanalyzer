/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.mailanalyzer.analise2;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author McLuck
 */
public class Teste3 {
    public static void main(String...args){
        
        try {
            Class classe = Class.forName("br.com.mailanalyzer.exemplo.commands.CommandAssuntoEncontrado");
            Object o = classe.newInstance();
            System.out.println(o);
        } catch (Exception ex) {
            Logger.getLogger(Teste3.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
