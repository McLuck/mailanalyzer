
package br.com.mailanalyzer.compose;

import br.com.mailanalyzer.fluxo.InterfaceComposeFlow;
import br.com.mailanalyzer.fluxo.MutableComponent;
import br.com.mailanalyzer.fluxo.PropertyRetriever;
import br.com.mailanalyzer.main.Base;

/**
 *
 * @author Lobo
 * @contact pedro.lobo29@gmail.com
 * @version 1.0
 * @Date 07/05/2011
 * @reviser ---
 */
public class FiltroConvertMinusculo implements InterfaceComposeFlow , PropertyRetriever, MutableComponent{

    public void execute() {
        msg = parse(msg);
    }

    public boolean stopFlow() {
        return false;
    }

    //Converte toda String para minusculo.
    private String parse(String txt){
    return txt.toLowerCase();
    }

    public Object getPropertyName() {
        return Base.FIELD_FILTRO_MINUSCULO;
    }

    public Object getPropertyValue() {
        return msg;
    }

    public void updateComponent(Object obj) {
        this.msg = (String)obj;
    }

    String msg;
}
