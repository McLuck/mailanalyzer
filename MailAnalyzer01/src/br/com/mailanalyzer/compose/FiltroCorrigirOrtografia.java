/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mailanalyzer.compose;

import br.com.mailanalyzer.domain.Message;
import br.com.mailanalyzer.fluxo.InterfaceComposeFlow;
import br.com.mailanalyzer.fluxo.MutableComponent;
import br.com.mailanalyzer.fluxo.PropertyRetriever;
import br.com.mailanalyzer.log.Log;
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

    private boolean stop = false;

    public static void main(String[] args) {
        String arg = "Solisito a confexão da minha carterinha com urjencia.";
        FiltroCorrigirOrtografia fl = new FiltroCorrigirOrtografia();
        fl.updateComponent(arg);
        fl.execute();
        System.out.println((String) fl.getPropertyValue());
    }

    private String CorrigirOrtografia(String args) {
        try {
            
            Configuration config = new Configuration();
            
            if (Config.IS_PROXY) {
                config.setProxy(Config.PROXY_ADDRESS, Config.PROXY_PORT, Config.PROXY_SCHEME);
            }

            SpellChecker checker = new SpellChecker(config);
            
            
            checker.setOverHttps(true); // Use https (default true from v1.1)
            checker.setLanguage(Language.PORTUGUESE); // Use English (default)
            SpellRequest request = new SpellRequest();
            request.setText(args);
            request.setIgnoreDuplicates(true); // Ignore duplicates
            

            SpellResponse spellResponse = checker.check(request);
            
            String msg2 = args;

            for (SpellCorrection sc : spellResponse.getCorrections()) {
                msg2 = corretor(args, sc.getOffset(), sc.getWords()[0], msg2);//correct(args, sc.getOffset(), sc.getWords()[0]);
            }
            return msg2;
        } catch (Exception e) {
            Log.d(this.getClass().getSimpleName(), e);
        }

        return args;

    }

    private String getPalavra(int p, String msgOriginal) {
        int pf = msgOriginal.length();
        for (int i = p; i < msgOriginal.length(); i++) {
            if (msgOriginal.charAt(i) == ' ') {
                pf = i;
                break;
            }
        }
        String palavra = msgOriginal.substring(p, pf);
        return palavra;
    }

    public String corretor(String original, int pos, String subs, String otherMSG) {
        if (subs == null) {
            return otherMSG;
        }
        if (subs.equals("")) {
            return otherMSG;
        }
        String palavra = getPalavra(pos, original);

        otherMSG = otherMSG.replace(palavra, subs);

        return otherMSG;
    }

    public void execute() {
        Log.d(this.getClass().getSimpleName(), "Executando...");
        if (msg == null) {
            stop = true;
            return;
        }
        this.msg = CorrigirOrtografia(msg);
        Log.d(this.getClass().getSimpleName(), "Finalizado");
    }

    public boolean stopFlow() {
        return false;
    }

    public Object getPropertyName() {
        return Base.FIELD_FILTRO_ORTOGRAFIA;
    }

    public Object getPropertyValue() {
        return msg;
    }

    public void updateComponent(Object obj) {
        if (obj instanceof String) {
            this.msg = (String) obj;
        } else if (obj instanceof Message) {
            this.msg = ((Message) obj).getMensagem();
        } else {
            this.msg = null;
        }
    }
    private String msg;
}
