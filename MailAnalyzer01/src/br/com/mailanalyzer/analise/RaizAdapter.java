/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.mailanalyzer.analise;

import br.com.mailanalyzer.domain.RaizDomain;

/**
 *
 * @author McLuck
 */
public class RaizAdapter {
    private Raiz logica;
    public RaizAdapter(RaizDomain dominio){
        logica = new Raiz();
        logica.setAssunto(dominio.getAssunto());
        ComposicaoAdapter cAdapter = new ComposicaoAdapter(dominio.getAgregacao(), logica);
        logica.setAgregacoes(cAdapter.getComposicao());
        cAdapter = new ComposicaoAdapter(dominio.getMandatorios(), logica);
        logica.setMandatorios(cAdapter.getComposicao());
        cAdapter = new ComposicaoAdapter(dominio.getBase(), logica);
        logica.setBase(cAdapter.getComposicao());
        logica.setTextoOriginal(dominio.getTextoOriginal());
    }
    public Raiz getRaiz(){
        return logica;
    }
}
