/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mailanalyzer.analise2.adapter;

import br.com.mailanalyzer.analise2.Composicao;
import br.com.mailanalyzer.analise2.Raiz;
import br.com.mailanalyzer.domain.ComposicaoDomain;
import br.com.mailanalyzer.domain.RaizDomain;
import java.util.ArrayList;

/**
 *
 * @author Lucas Israel
 * @contact mcluck.ti@gmail.com
 * @version 1.0
 * @Date 25-09-2011
 *
 * ALTERADO EM: 15/10/11 - Adicionando aprendizado automatico e apontamento de palavras
 */
public class RaizAdapter {

    private RaizDomain dominio;
    private Raiz raiz;

    public RaizAdapter(RaizDomain dominio) {
        this.dominio = dominio;
        raiz = new Raiz();

        //Adicionado para teste com cache
        raiz.setDominio(dominio);


        raiz.setId(dominio.getId());
        raiz.setAssunto(dominio.getAssunto());
        raiz.setAgregacoes(new ArrayList<Composicao>());
        raiz.setEliminatorios(new ArrayList<Composicao>());
        raiz.setMandatorios(new ArrayList<Composicao>());
        for (ComposicaoDomain cpd : dominio.getComposicoes()) {
            ComposicaoAdapter ca = new ComposicaoAdapter(cpd);
            raiz.addComposicao(ca.getComposical(), ca.getComposical().getTipo());
        }
    }

    public RaizAdapter(Raiz raiz) {
        this.raiz = raiz;
        dominio = new RaizDomain();
        dominio.setAssunto(raiz.getAssunto());
        dominio.setId(raiz.getId());
        dominio.setComposicoes(new ArrayList<ComposicaoDomain>());
        for (Composicao cp : raiz.getAgregacoes()) {
            ComposicaoAdapter ca = new ComposicaoAdapter(cp, dominio);
            dominio.getComposicoes().add(ca.getDominio());
        }
        for (Composicao cp : raiz.getEliminatorios()) {
            ComposicaoAdapter ca = new ComposicaoAdapter(cp, dominio);
            dominio.getComposicoes().add(ca.getDominio());
        }
        for (Composicao cp : raiz.getMandatorios()) {
            ComposicaoAdapter ca = new ComposicaoAdapter(cp, dominio);
            dominio.getComposicoes().add(ca.getDominio());
        }
        ComposicaoAdapter ca = new ComposicaoAdapter(raiz.getBase(), dominio);
        dominio.getComposicoes().add(ca.getDominio());
    }

    public Raiz getRaiz() {
        return raiz;
    }

    public RaizDomain getDominio() {
        return dominio;
    }
}
