package br.com.mailanalyzer.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author Guilherme Lucas
 * @contact gfaria.mello@gmail.com
 * @version 1.1
 * @Date 29/04/2011
 * @reviser Lucas Israel
 *
 */
@Entity
@Table(name = "termvariation")
public class TermVariation extends DomainObject implements Serializable {

    private static final long serialVersionUID = 1L;
    @Column(name = "name")
    private String name;
    @Column(name = "variations")
    private String variations;
    @Column(name = "replacer")
    private String replacer;

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the variations
     */
    public String getVariations() {
        return variations;
    }

    /**
     * @param variations the variations to set
     */
    public void setVariations(String variations) {
        this.variations = variations;
    }

    /**
     * @return the replacer
     */
    public String getReplacer() {
        return replacer;
    }

    /**
     * @param replacer the replacer to set
     */
    public void setReplacer(String replacer) {
        this.replacer = replacer;
    }
}
