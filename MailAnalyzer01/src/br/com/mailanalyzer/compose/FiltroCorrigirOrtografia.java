/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mailanalyzer.compose;

import br.com.mailanalyzer.fluxo.InterfaceComposeFlow;
import br.com.mailanalyzer.fluxo.MutableComponent;
import br.com.mailanalyzer.fluxo.PropertyRetriever;
import br.com.mailanalyzer.main.Base;
import br.com.mailanalyzer.main.Config;
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
public class FiltroCorrigirOrtografia implements InterfaceComposeFlow, PropertyRetriever, MutableComponent {

    private String CorrigirOrtografia(String args) {
        Configuration config = new Configuration();
        if(Config.IS_PROXY){
            config.setProxy(Config.PROXY_ADDRESS, Config.PROXY_PORT, Config.PROXY_SCHEME);
        }

        SpellChecker checker = new SpellChecker(config);
        checker.setOverHttps(true); // Use https (default true from v1.1)
        checker.setLanguage(Language.PORTUGUESE); // Use English (default)
        SpellRequest request = new SpellRequest();
        request.setText(args);
        request.setIgnoreDuplicates(true); // Ignore duplicates

        SpellResponse spellResponse = checker.check(request);
       
        for (SpellCorrection sc : spellResponse.getCorrections()) {
            args = correct(args, sc.getOffset(), sc.getWords()[0]);
        }
        
        
        return args;
    }

    public void execute() {
        this.msg = CorrigirOrtografia(msg);
    }

    public boolean stopFlow() {
        return false;
    }
    
    private String correct(String msg, int p, String subs){
        if(subs.equals("")){
            return msg;
        }
        int pf = 0;
        for(int i=p;i<msg.length();i++){
            if(msg.charAt(i)==' '){
                pf=i;
                break;
            }
        }
        String palavra = msg.substring(p, pf);
        msg = msg.replace(palavra, subs);
        
        return msg;
    }

    public Object getPropertyName() {
        return Base.FIELD_FILTRO_ORTOGRAFIA;
    }

    public Object getPropertyValue() {
        return msg;
    }

    public void updateComponent(Object obj) {
        this.msg = (String)obj;
    }
    
    private String msg;
}
