/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mailanalyzer.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Lucas Israel
 * @contact mcluck.ti@gmail.com
 * @version 1.0
 * @Date 19-09-2011
 *
 */
@Entity
@Table(name = "elemento")
public class ElementoDomain extends DomainObject {
    private int peso;
//    private String palavra;

    @ManyToOne(cascade={CascadeType.ALL}, targetEntity=Palavra.class)
    @JoinColumn(name="palavra_id", nullable=false)
    private Palavra palavra;

    @ManyToOne(cascade = {CascadeType.ALL}, targetEntity = ComposicaoDomain.class)
    @JoinColumn(name = "composicao_id", nullable = false)
    private ComposicaoDomain pai;

    /**
     * @return the peso
     */
    public int getPeso() {
        return peso;
    }

    /**
     * @param peso the peso to set
     */
    public void setPeso(int peso) {
        this.peso = peso;
    }

//    /**
//     * @return the palavra
//     */
//    public String getPalavra() {
//        return palavra;
//    }
//
//    /**
//     * @param palavra the palavra to set
//     */
//    public void setPalavra(String palavra) {
//        this.palavra = palavra;
//    }

    /**
     * @return the pai
     */
    public ComposicaoDomain getPai() {
        return pai;
    }

    /**
     * @param pai the pai to set
     */
    public void setPai(ComposicaoDomain pai) {
        this.pai = pai;
    }

    /**
     * @return the palavra
     */
    public Palavra getPalavra() {
        return palavra;
    }

    /**
     * @param palavra the palavra to set
     */
    public void setPalavra(Palavra palavra) {
        this.palavra = palavra;
    }
}
