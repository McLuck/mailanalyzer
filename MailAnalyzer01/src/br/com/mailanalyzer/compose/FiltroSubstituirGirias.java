package br.com.mailanalyzer.compose;

import br.com.mailanalyzer.dao.actions.ActionTermVariation;
import br.com.mailanalyzer.domain.Message;
import br.com.mailanalyzer.domain.TermVariation;
import br.com.mailanalyzer.fluxo.InterfaceComposeFlow;
import br.com.mailanalyzer.fluxo.MutableComponent;
import br.com.mailanalyzer.fluxo.PropertyRetriever;
import br.com.mailanalyzer.log.Log;
import br.com.mailanalyzer.main.Base;
import java.util.List;

/**
 *Classe para substituir gírias de um texto.
 * @author Guilherme Lucas
 * @contact gfaria.mello@gmail.com
 * @version 1.0
 * @Date 07-05-2011
 *
 */
public class FiltroSubstituirGirias implements InterfaceComposeFlow, PropertyRetriever, MutableComponent {

    private boolean stop = false;

    public void execute() {
        Log.d(this.getClass().getSimpleName(), "Executando...");
        try {
            if (msg == null) {
                stop = true;
                return;
            }
            msg = Substituir(msg);
        } catch (Exception e) {
            Log.d(this.getClass().getSimpleName(), e);
        }

        Log.d(this.getClass().getSimpleName(), "Finaluzado");
    }

    public boolean stopFlow() {
        return stop;
    }

    public String Substituir(String text) {
        ActionTermVariation action = new ActionTermVariation();

        List<TermVariation> lista = action.showAll();

        for (TermVariation term : lista) {

            String[] variations = term.getVariations().split(";");
            for (String s : variations) {
                text = text.replace(s, term.getReplacer());
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
