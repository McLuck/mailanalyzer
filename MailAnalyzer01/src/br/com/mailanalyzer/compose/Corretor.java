package br.com.mailanalyzer.compose;

import br.com.mailanalyzer.fluxo.InterfaceComposeFlow;
import br.com.mailanalyzer.fluxo.MutableComponent;

/**
 *
 * @author Lucas Israel
 */
public class Corretor implements FilterInterface, InterfaceComposeFlow, MutableComponent{
    private String input;
    private String[]results;
    private boolean isAnalyzed = false;
    public void filter() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private String[] correct(String word){
        return new String[0];
    }

    private String analyzer(String text){
        if(isAnalyzed)
            return text;

        //Aplica analise

        //Recursivo ate terminar a analse
        return analyzer(text);
    }

    public void execute() {
        filter();
    }

    public void updateComponent(Object obj) {

        if(obj!=null){
            String[] str = (String[])obj;
            if(str.length>0)
                input = str[0];
        }
            
    }

    public boolean stopFlow() {
        return false;
    }
}
