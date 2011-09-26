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
    private int peso;
    private String textoOriginal;
    private int tipo;
    private boolean sequencial;

    @ManyToOne(cascade = {CascadeType.ALL}, targetEntity = RaizDomain.class)
    @JoinColumn(name = "raiz_id", nullable = true, columnDefinition="int default 0")
    private RaizDomain raiz;


    @OneToMany(mappedBy = "pai", cascade = {CascadeType.ALL})
    @JoinColumn(name = "composicao_id", nullable = false)
    @Cascade(value = org.hibernate.annotations.CascadeType.DELETE_ORPHAN)
    private List<ElementoDomain> elementos;

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
     * @return the tipo
     */
    public int getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    /**
     * @return the elementos
     */
    public List<ElementoDomain> getElementos() {
        return elementos;
    }

    /**
     * @param elementos the elementos to set
     */
    public void setElementos(List<ElementoDomain> elementos) {
        this.elementos = elementos;
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
     * @return the sequencial
     */
    public boolean isSequencial() {
        return sequencial;
    }

    /**
     * @param sequencial the sequencial to set
     */
    public void setSequencial(boolean sequencial) {
        this.sequencial = sequencial;
    }
}
