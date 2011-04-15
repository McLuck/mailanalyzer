/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.mailanalyzer.domain;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class DomainObject {

    @Id @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;

    private long dataRegistro;

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the dataRegistro
     */
    public long getDataRegistro() {
        return dataRegistro;
    }

    /**
     * @param dataRegistro the dataRegistro to set
     */
    public void setDataRegistro(long dataRegistro) {
        this.dataRegistro = dataRegistro;
    }

}
