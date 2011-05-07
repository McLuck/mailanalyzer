package br.com.mailanalyzer.testes;

import br.com.mailanalyzer.dao.FieldDAO;
import br.com.mailanalyzer.dao.SubjectDAO;
import br.com.mailanalyzer.dao.actions.FieldAction;
import br.com.mailanalyzer.domain.Field;
import br.com.mailanalyzer.domain.Subject;

/**
 *
 * @author Lobos
 */
public class TestFieldAction {

    public static void main(String args[]) {

        FieldAction fieldtest = new FieldAction();
        FieldDAO field1 = new FieldDAO();
        Field f1 = new Field();
        SubjectDAO sub = new SubjectDAO();
        Subject sub1 = new Subject();

        sub1.setName("aaaa");
        sub1.setCommandFlowName("fluxo");
        sub1.setText("bbb");
        f1.setName("Lobo");
        f1.setRequired(false);
        f1.setSubject(sub1);
        field1.salvar(f1);



    }
}
