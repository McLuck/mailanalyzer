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
@Table(name="raiz")
public class RaizDomain extends DomainObject{
    @ManyToOne(cascade = {CascadeType.ALL}, targetEntity = Subject.class)
    @JoinColumn(name = "subject_id", nullable = true)
    private Subject assunto;

    @Column(name="textoOriginal")
    private String textoOriginal;

    @ManyToOne(cascade = {CascadeType.ALL}, targetEntity = ComposicaoDomain.class)
    @JoinColumn(name = "agregacao_id", nullable = true)
    private ComposicaoDomain agregacao;

    @ManyToOne(cascade = {CascadeType.ALL}, targetEntity = ComposicaoDomain.class)
    @JoinColumn(name = "mandatorio_id", nullable = true)
    private ComposicaoDomain mandatorios;

    @ManyToOne(cascade = {CascadeType.ALL}, targetEntity = ComposicaoDomain.class)
    @JoinColumn(name = "base_id", nullable = true)
    private ComposicaoDomain base;


    @OneToMany(mappedBy = "raiz", cascade = {CascadeType.ALL})
    @Cascade(value = org.hibernate.annotations.CascadeType.DELETE_ORPHAN)
    private List<ComposicaoDomain> composicoes;

    /**
     * @return the assunto
     */
    public Subject getAssunto() {
        return assunto;
    }

    /**
     * @param assunto the assunto to set
     */
    public void setAssunto(Subject assunto) {
        this.assunto = assunto;
    }

    /**
     * @return the agregacao
     */
    public ComposicaoDomain getAgregacao() {
        return agregacao;
    }

    /**
     * @param agregacao the agregacao to set
     */
    public void setAgregacao(ComposicaoDomain agregacao) {
        this.agregacao = agregacao;
    }

    /**
     * @return the mandatorios
     */
    public ComposicaoDomain getMandatorios() {
        return mandatorios;
    }

    /**
     * @param mandatorios the mandatorios to set
     */
    public void setMandatorios(ComposicaoDomain mandatorios) {
        this.mandatorios = mandatorios;
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

    /**
     * @return the textoOriginal
     */
    public String getTextoOriginal() {
        return textoOriginal;
    }

    /**
     * @param textoOriginal the textoOriginal to set
     */
    public void setTextoOriginal(String textoOriginal) {
        this.textoOriginal = textoOriginal;
    }

    /**
     * @return the base
     */
    public ComposicaoDomain getBase() {
        return base;
    }

    /**
     * @param base the base to set
     */
    public void setBase(ComposicaoDomain base) {
        this.base = base;
    }
}
