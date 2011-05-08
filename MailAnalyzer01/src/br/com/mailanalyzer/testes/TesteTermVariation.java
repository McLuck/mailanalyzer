package br.com.mailanalyzer.testes;

import br.com.mailanalyzer.dao.actions.ActionTermVariation;
import br.com.mailanalyzer.domain.TermVariation;

/**
 * Esta classe ira testar varios cenarios de CRUD do TermVariation
 * @author Guilherme Lucas
 * @contact gfaria.mello@gmail.com
 * @version 1.0
 * @Date 07-05-2011
 *
 */
public class TesteTermVariation {

    public static void main (String Args []) throws InstantiationException, IllegalAccessException {
    ActionTermVariation atv = new ActionTermVariation();
    TermVariation tester;


    

    tester = new TermVariation();
    tester.setName("Teste1");
    tester.setReplacer("Legal");
    tester.setVariations("Mto loko");
    atv.setVariation(tester);
    atv.Salvar();

   /*
    tester = new TermVariation();
    tester.setId(10);
    atv.setVariation(tester);
    tester = atv.getVariation(tester);
    tester.setName("teste alteracao. merda de teclado!!2");
    tester.setReplacer("ade");
    tester.setVariations("sad");
    }
 */
    }
}
