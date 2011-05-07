
package br.com.mailanalyzer.testes;

import br.com.mailanalyzer.dao.actions.FieldAction;
import br.com.mailanalyzer.domain.Field;

/**
 *
 * @author Lobos
 */
public class TestFieldAction {

    public static void main(String args[]) {

        FieldAction fieldtest = new FieldAction();

       Field f1 = new Field();

       f1.setName("Lobo");
       f1.setRequired(false);
    }
}
