/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.mailanalyzer.domain;

import java.util.Collection;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 *
 * @author McLuck
 */
@Entity
@Table(name="tb_texto")
public class Texto extends DomainObject{
    private String assunto;

    @ManyToMany(fetch=FetchType.LAZY, cascade={CascadeType.ALL})
    @JoinTable(name="texto_has_palavra", joinColumns={@JoinColumn(name="texto_id")}, inverseJoinColumns={@JoinColumn(name="palavra_id")})
    private List<Palavra> palavras;

    /**
     * @return the assunto
     */
    public String getAssunto() {
        return assunto;
    }

    /**
     * @param assunto the assunto to set
     */
    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }

    /**
     * @return the palavras
     */
    public List<Palavra> getPalavras() {
        return palavras;
    }

    /**
     * @param palavras the palavras to set
     */
    public void setPalavras(List<Palavra> palavras) {
        this.palavras = palavras;
    }
}
