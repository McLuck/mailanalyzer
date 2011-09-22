/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mailanalyzer.analise;

import br.com.mailanalyzer.domain.ComposicaoDomain;
import br.com.mailanalyzer.domain.ElementoDomain;
import java.util.ArrayList;

/**
 *
 * @author McLuck
 */
public class ComposicaoAdapter {

    private Composicao logica;

    public ComposicaoAdapter(ComposicaoDomain dominio, Raiz r) {
        setData(dominio, null, r);
    }

    public ComposicaoAdapter(ComposicaoDomain dominio, Composicao pai) {
        setData(dominio, pai, null);
    }
    
    private void setData(ComposicaoDomain dominio, Composicao pai, Raiz r){
        logica = new Composicao();
        logica.setElementoFim(dominio.getElementoFim());
        logica.setElementoInicio(dominio.getElementoInicio());
        logica.setRelevancia(dominio.getPeso());
        logica.setOriginal(dominio.getOriginal());
        logica.setItens(new ArrayList<Item>());
        logica.setItemPai(pai);
        logica.setRaiz(r);
        for (ElementoDomain elem : dominio.getElemtentos()) {
            if (elem != null) {
                ElementoAdapter adapt = new ElementoAdapter(elem, logica);
                logica.getItens().add(adapt.getElemento());
            }
        }

        for (ComposicaoDomain cd : dominio.getComposicoes()) {
            if (cd != null) {
                ComposicaoAdapter cAdapter = new ComposicaoAdapter(cd, logica);
                logica.getItens().add(cAdapter.getComposicao());
            }
        }
    }

    public Composicao getComposicao() {
        return logica;
    }
}
