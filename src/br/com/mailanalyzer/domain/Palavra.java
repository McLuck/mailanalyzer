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
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author McLuck
 */
@Entity
@Table(name="palavras")
public class Palavra extends DomainObject{
    @Column(name="apalavra", length=255, unique=true)
    private String palavra;

    @OneToMany(mappedBy="palavra", cascade={CascadeType.ALL})
    @JoinColumn(name="palavra_id")
    private List<ElementoDomain> elementos;

    public Palavra(){

    }
    public Palavra(String palv){
        setPalavra(palv);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + (this.palavra != null ? this.palavra.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object o){
        if(o != null && o instanceof Palavra){
            return ((Palavra)o).getPalavra().equals(palavra);
        }else if(o != null && o instanceof String){
            return ((String)o).toLowerCase().trim().equals(palavra);
        }
        return false;
    }

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
        palavra = palavra.replace(" ", "").toLowerCase();
        this.palavra = palavra;
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
}
