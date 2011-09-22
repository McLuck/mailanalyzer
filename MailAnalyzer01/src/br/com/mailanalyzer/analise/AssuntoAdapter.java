
package br.com.mailanalyzer.analise;

import br.com.mailanalyzer.domain.Subject;

/**
 *
 * @author McLuck
 */
public class AssuntoAdapter {
    private Subject assunto;
    private Raiz r;
    public AssuntoAdapter(Subject sub){
        this.assunto = sub;
        r = new Raiz();
        r.aprender(assunto.getText());
        r.setAssunto(assunto);
        for(Composicao c : ManagementAnalyzer.getComposicoesComuns()){
            r.append(c);
        }
    }

    public Raiz getRaiz(){
        return r;
    }
}
