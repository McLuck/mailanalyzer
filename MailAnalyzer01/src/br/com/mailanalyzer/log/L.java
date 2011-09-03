/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mailanalyzer.log;

import br.com.mailanalyzer.main.Config;
import br.com.mailanalyzer.utils.Converte;

/**
 *
 * @author lucasisrael
 */
public class L {

    private static String getTime() {
        StringBuffer sb = new StringBuffer();

        java.util.Date dt = new java.util.Date();
        sb.append(Converte.ToStringDataVisual(dt));
        sb.append(" ");
        sb.append(Converte.DateToStringTimer(dt.getTime()));

        return sb.toString();
    }

    public static void d(String tag, Object o) {
        
        //implementar rotinas de LOG aqui
        
        

        if (Config.DEBUG_MODE) {
            System.out.print(getTime() + " - ");
            System.out.print(tag);
            System.out.print(" - ");

            if (o instanceof String) {
                System.out.println(String.valueOf(o));
            } else if (o instanceof Exception) {
                ((Exception) o).printStackTrace();
            } else {
                System.out.println("LOG nao identificado.");
            }
        }
    }

    public static void e(String tag, Object o){
        if(Config.DEBUG_MODE){
            System.out.print(getTime() + " - ");
            System.out.print(tag);
            System.out.print(" - ");
            System.err.println(o);
        }
    }
}
