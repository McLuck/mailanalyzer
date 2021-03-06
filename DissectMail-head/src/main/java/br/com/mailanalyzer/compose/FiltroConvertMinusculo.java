package br.com.mailanalyzer.compose;

import br.com.mailanalyzer.domain.Message;
import br.com.mailanalyzer.fluxo.InterfaceComposeFlow;
import br.com.mailanalyzer.fluxo.MutableComponent;
import br.com.mailanalyzer.fluxo.PropertyRetriever;
import br.com.mailanalyzer.log.L;
import br.com.mailanalyzer.main.Base;
import br.com.mailanalyzer.main.Config;

/**
 *
 * @author Lobo
 * @contact pedro.lobo29@gmail.com
 * @version 1.0
 * @Date 07/05/2011
 * @reviser ---
 */
public class FiltroConvertMinusculo implements InterfaceComposeFlow, PropertyRetriever, MutableComponent {
	private static final String TAG = "Filtro Minusculo";
    public void execute() {
        try {
        	if(Config.isNivelLogMaximo()){
        		L.d(TAG, this.getClass(), "Iniciando filtro para conversao para minusculo na mensagem: \n".concat(msg));
        	}
            if (msg == null) {
                stop = true;
                return;
            }
            msg = parse(msg);
            if(Config.isNivelLogMaximo()){
        		L.d(TAG, this.getClass(), "Filtro para convers�o para minusculo foi aplicado. Mensagem: \n".concat(msg));
        	}
        } catch (Exception ex) {
            L.e("Filtro conver��o para minusculos", this, "Falhou em converter caracteres para minusculos", ex);
        }
    }
    private boolean stop = false;

    public boolean stopFlow() {
        return stop;
    }

    //Converte toda String para minusculo.
    private String parse(String txt) {
        return txt.toLowerCase();
    }

    public Object getPropertyName() {
        return Base.FIELD_FILTRO_MINUSCULO;
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
