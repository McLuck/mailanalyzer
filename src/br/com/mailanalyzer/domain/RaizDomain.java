/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.mailanalyzer.domain;

import java.util.List;
import javax.persistence.CascadeType;
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
    @JoinColumn(name = "assunto_id", nullable = false)
    private Subject assunto;


    @OneToMany(mappedBy = "raiz", cascade = {CascadeType.ALL})
    @JoinColumn(name = "raiz_id", nullable = false)
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
