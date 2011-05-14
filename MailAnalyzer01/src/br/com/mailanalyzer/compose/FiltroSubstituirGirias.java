package br.com.mailanalyzer.compose;

import br.com.mailanalyzer.dao.actions.ActionTermVariation;
import br.com.mailanalyzer.domain.TermVariation;
import br.com.mailanalyzer.fluxo.InterfaceComposeFlow;
import br.com.mailanalyzer.fluxo.MutableComponent;
import br.com.mailanalyzer.fluxo.PropertyRetriever;
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

    public void execute() {
        msg = Substituir(msg);
    }

    public boolean stopFlow() {
        return false;
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
        this.msg = (String)obj;
    }
    
    private String msg;
}


