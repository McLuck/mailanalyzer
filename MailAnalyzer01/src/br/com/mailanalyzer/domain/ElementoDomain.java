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

    @Column(name="palavra")
    private String palavra;
    @Column(name="peso")
    private int peso;
    @ManyToOne(cascade = {CascadeType.ALL}, targetEntity = ComposicaoDomain.class)
    @JoinColumn(name = "composicao_pai_id", nullable = true)
    private ComposicaoDomain itemPai;

    /**
     * @return the palavra
     */
    public String getPalavra() {
        return palavra;
    }

    /**
     * @param palavra the palavra to set
     */
    public void setPalavra(String palavra) {
        this.palavra = palavra;
    }

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

    /**
     * @return the itemPai
     */
    public ComposicaoDomain getItemPai() {
        return itemPai;
    }

    /**
     * @param itemPai the itemPai to set
     */
    public void setItemPai(ComposicaoDomain itemPai) {
        this.itemPai = itemPai;
    }
}
