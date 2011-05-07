/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mailanalyzer.compose;

import br.com.mailanalyzer.fluxo.InterfaceComposeFlow;
import org.xeustechnologies.googleapi.spelling.Configuration;
import org.xeustechnologies.googleapi.spelling.Language;
import org.xeustechnologies.googleapi.spelling.SpellChecker;
import org.xeustechnologies.googleapi.spelling.SpellCorrection;
import org.xeustechnologies.googleapi.spelling.SpellRequest;
import org.xeustechnologies.googleapi.spelling.SpellResponse;

/**
 *
 *
 * @author Herlayne
 * @contact fran.herlayne@gmail.com
 * @version 1.0
 * @Date 07-05-2011
 * 
 */
public class FiltroCorrigirOrtografia implements InterfaceComposeFlow {
    
  public void CorrigirOrtografia (String args) {

      Configuration config = new Configuration();

        config.setProxy( "my_proxy_host", 8080, "http" );
         SpellChecker checker = new SpellChecker( );
         checker.setOverHttps( true ); // Use https (default true from v1.1)
         checker.setLanguage( Language.PORTUGUESE ); // Use English (default)
         SpellRequest request = new SpellRequest();
         request.setText( args );
         request.setIgnoreDuplicates( true ); // Ignore duplicates

         SpellResponse spellResponse = checker.check( request );

         for( SpellCorrection sc : spellResponse.getCorrections() ){
             System.out.println("---");
             for(String s : sc.getWords())
                 System.out.println(s);
         }
             
  }

    public void execute() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean stopFlow() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

 /* public static void main(String []arg){
      String input = "esti sabadu ta uma merda";
      FiltroCorrigirOrtografia filtro = new FiltroCorrigirOrtografia();
      filtro.CorrigirOrtografia(input);
    }*/
}
