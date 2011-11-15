/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.mailanalyzer.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author mcluck
 */
@Entity
@Table(name = "sinonimos")
public class Sinonimos extends DomainObject{
    @Column(length=1000, name="sinonimoss")
    private String sinonimoss;

    /**
     * @return the sinonimoss
     */
    public String getSinonimoss() {
        return sinonimoss;
    }

    /**
     * @param sinonimoss the sinonimoss to set
     */
    public void setSinonimoss(String sinonimoss) {
        this.sinonimoss = sinonimoss;
    }

}
