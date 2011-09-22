/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.mailanalyzer.domain;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.Cascade;

/**
 *
 * @author Lucas Israel
 * @contact mcluck.ti@gmail.com
 * @version 1.0
 * @Date 19-09-2011
 *
 */
@Entity
@Table(name="composicao")
public class ComposicaoDomain extends DomainObject{

    @OneToMany(mappedBy = "itemPai", cascade = {CascadeType.ALL})
    @Cascade(value = org.hibernate.annotations.CascadeType.DELETE_ORPHAN)
    private List<ElementoDomain> elemtentos;

    @OneToMany(mappedBy = "itemPai", cascade = {CascadeType.ALL})
    @Cascade(value = org.hibernate.annotations.CascadeType.DELETE_ORPHAN)
    private List<ComposicaoDomain> composicoes;

    @Column(name="peso")
    private int peso;

    @ManyToOne(cascade = {CascadeType.ALL}, targetEntity = ComposicaoDomain.class)
    @JoinColumn(name = "composicao_pai_id", nullable = true)
    private ComposicaoDomain itemPai;

    @ManyToOne(cascade = {CascadeType.ALL}, targetEntity = RaizDomain.class)
    @JoinColumn(name = "raiz_id", nullable = true)
    private RaizDomain raiz;

    @Column(name="original")
    private String original;
    @Column(name="elementoInicio")
    private int elementoInicio;
    @Column(name="elementoFim")
    private int elementoFim;

    /**
     * @return the original
     */
    public String getOriginal() {
        return original;
    }

    /**
     * @param original the original to set
     */
    public void setOriginal(String original) {
        this.original = original;
    }

    /**
     * @return the elementoInicio
     */
    public int getElementoInicio() {
        return elementoInicio;
    }

    /**
     * @param elementoInicio the elementoInicio to set
     */
    public void setElementoInicio(int elementoInicio) {
        this.elementoInicio = elementoInicio;
    }

    /**
     * @return the elementoFim
     */
    public int getElementoFim() {
        return elementoFim;
    }

    /**
     * @param elementoFim the elementoFim to set
     */
    public void setElementoFim(int elementoFim) {
        this.elementoFim = elementoFim;
    }

    /**
     * @return the raiz
     */
    public RaizDomain getRaiz() {
        return raiz;
    }

    /**
     * @param raiz the raiz to set
     */
    public void setRaiz(RaizDomain raiz) {
        this.raiz = raiz;
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
     * @return the elemtentos
     */
    public List<ElementoDomain> getElemtentos() {
        return elemtentos;
    }

    /**
     * @param elemtentos the elemtentos to set
     */
    public void setElemtentos(List<ElementoDomain> elemtentos) {
        this.elemtentos = elemtentos;
    }

    /**
     * @return the composicoes
     */
    public List<ComposicaoDomain> getComposicoes() {
        return composicoes;
    }

    /**
     * @param composicoes the composicoes to set
     */
    public void setComposicoes(List<ComposicaoDomain> composicoes) {
        this.composicoes = composicoes;
    }
}
