package br.com.mailanalyzer.compose;

import br.com.mailanalyzer.dao.actions.ActionTermVariation;
import br.com.mailanalyzer.domain.TermVariation;
import br.com.mailanalyzer.fluxo.InterfaceComposeFlow;
import java.util.List;

/**
 *Classe para substituir gírias de um texto.
 * @author Guilherme Lucas
 * @contact gfaria.mello@gmail.com
 * @version 1.0
 * @Date 07-05-2011
 *
 */
public class FiltroSubstituirGirias implements InterfaceComposeFlow {

    public void execute() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean stopFlow() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String Substituir(String text) {
        ActionTermVariation action = new ActionTermVariation();

        List<TermVariation> lista = action.showAll();

        for (TermVariation tv : lista) {

            String[] variations = tv.getVariations().split(";");
            for (String s : variations) {
                text = text.replace(s, tv.getReplacer());
            }

        }

        return text;
    }
}
