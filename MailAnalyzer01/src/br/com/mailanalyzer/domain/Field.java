package br.com.mailanalyzer.domain;

import javax.persistence.CascadeType;
import javax.persistence.Table;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author ---
 * @contact ---
 * @version 1.1
 * @Date ---
 * @reviser Lucas Israel
 *
 */
@Entity
@Table(name = "ofield")
public class Field extends DomainObject {

    private String name;
    private int type;
    private boolean required;


    @ManyToOne(cascade = {CascadeType.ALL}, targetEntity = Subject.class)
    @JoinColumn(name = "subject_id", nullable = true)
    private Subject subject;
    public static final int STRING = 0;
    public static final int INTEGER = 1;
    public static final int DATE_TIME = 2;
    public static final int DOUBLE = 3;

    /**
     *  @return the name
     *
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
     * @return the type
     */
    public int getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(int type) {
        this.type = type;
    }

    /**
     * @return the required
     */
    public boolean isRequired() {
        return required;
    }

    /**
     * @param required the required to set
     */
    public void setRequired(boolean required) {
        this.required = required;
    }

    /**
     * @return the subject
     */
    public Subject getSubject() {
        return subject;
    }

    /**
     * @param subject the subject to set
     */
    public void setSubject(Subject subject) {
        this.subject = subject;
    }
}
