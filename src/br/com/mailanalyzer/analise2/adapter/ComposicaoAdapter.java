/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.mailanalyzer.analise2.adapter;

import br.com.mailanalyzer.analise2.Composicao;
import br.com.mailanalyzer.analise2.Elemento;
import br.com.mailanalyzer.domain.ComposicaoDomain;
import br.com.mailanalyzer.domain.ElementoDomain;
import br.com.mailanalyzer.domain.RaizDomain;
import java.util.ArrayList;

/**
 *
 * @author Lucas Israel
 * @contact mcluck.ti@gmail.com
 * @version 1.0
 * @Date 25-09-2011
 */
public class ComposicaoAdapter {
    private ComposicaoDomain dominio;
    private Composicao composicao;

    public ComposicaoAdapter(ComposicaoDomain dominio){
        this.dominio = dominio;
        composicao = new Composicao();
        composicao.setId(dominio.getId());
        composicao.setPeso(dominio.getPeso());
        composicao.setSequencial(dominio.isSequencial());
        composicao.setTextoOriginal(dominio.getTextoOriginal());
        composicao.setTipo(dominio.getTipo());
        composicao.setElementos(new ArrayList<Elemento>());
        for(ElementoDomain eld : dominio.getElementos()){
            ElementoAdapter ela = new ElementoAdapter(eld);
            composicao.getElementos().add(ela.getElemento());
        }
    }

    public ComposicaoAdapter(Composicao composicao, RaizDomain raiz){
        this.composicao = composicao;
        dominio = new ComposicaoDomain();
        dominio.setId(composicao.getId());
        dominio.setPeso(composicao.getPeso());
        dominio.setSequencial(composicao.isSequencial());
        dominio.setTextoOriginal(composicao.getTextoOriginal());
        dominio.setTipo(composicao.getTipo());
        dominio.setElementos(new ArrayList<ElementoDomain>());
        dominio.setRaiz(raiz);
        dominio.setDataRegistro(new java.util.Date().getTime());
        for(Elemento el : composicao.getElementos()){
            ElementoAdapter ela = new ElementoAdapter(el, dominio);
            dominio.getElementos().add(ela.getDominio());
        }
    }

    public ComposicaoDomain getDominio(){
        return dominio;
    }
    public Composicao getComposical(){
        return composicao;
    }
}
