/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.mailanalyzer.analise;

import br.com.mailanalyzer.domain.ElementoDomain;

/**
 *
 * @author McLuck
 */
public class ElementoAdapter {
    private Elemento logica;
    public ElementoAdapter(ElementoDomain dominio, Composicao pai){
        logica = new Elemento();
        logica.setPalavra(dominio.getPalavra(), Raiz.PROCURAR_EM_SINONIMOS);
        logica.setRelevancia(dominio.getPeso());
        logica.setItemPai(pai);
    }
    public Elemento getElemento(){
        return logica;
    }
}
