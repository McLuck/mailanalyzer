package br.com.mailanalyzer.compose;

import br.com.mailanalyzer.dao.actions.ActionTermVariation;
import br.com.mailanalyzer.domain.Message;
import br.com.mailanalyzer.domain.TermVariation;
import br.com.mailanalyzer.fluxo.InterfaceComposeFlow;
import br.com.mailanalyzer.fluxo.MutableComponent;
import br.com.mailanalyzer.fluxo.PropertyRetriever;
import br.com.mailanalyzer.log.L;
import br.com.mailanalyzer.main.Base;
import br.com.mailanalyzer.main.Config;

import java.util.List;

/**
 *Classe para substituir gÌrias de um texto.
 * @author Guilherme Lucas
 * @contact gfaria.mello@gmail.com
 * @version 1.0
 * @Date 07-05-2011
 *
 */
public class FiltroSubstituirGirias implements InterfaceComposeFlow, PropertyRetriever, MutableComponent {

    private boolean stop = false;
    private static final String TAG = "Filtro Substituir Girias"; 
    public void execute() {
        try {
        	if(Config.isNivelLogMaximo()){
        		L.d(TAG, this.getClass(), "Iniciando filtro de Substituição de termos na mensagem: \n".concat(msg));
        	}
            if (msg == null) {
                stop = true;
                return;
            }
            msg = Substituir(msg);
            if(Config.isNivelLogMaximo()){
        		L.d(TAG, this.getClass(), "Aplicou filtro de substituição de termos na mensagem: \n".concat(msg));
        	}
        } catch (Exception e) {
            L.e("Filtro de gÌrias", this, "Falhou ao tentar substituir termos na mensagem", e);
        }

    }

    public boolean stopFlow() {
        return stop;
    }

    public String Substituir(String text) {
        if(Base.VARIACOES_DE_TERMOS == null){
            Base.LoadTermVariations();
        }
        for(TermVariation tv : Base.VARIACOES_DE_TERMOS){
            String[] v = tv.getVariations().split(";");
            for(String s : v){
                if(text.contains(s)){
                    text = text.replace(s, tv.getReplacer());
                }
            }
        }
        return text;
    }

    public Object getPropertyName() {
        return Base.FIELD_FILTRO_GIRIA;
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
