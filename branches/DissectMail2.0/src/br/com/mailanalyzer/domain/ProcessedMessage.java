
package br.com.mailanalyzer.domain;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Bruno Marin Mota
 * @contact brunomarinmota@gmail.com
 * @version 1.1
 * @Date 17/04/2011
 * @reviser Lucas Israel
 *
 */
@Entity
@Table(name = "processed_message")
public class ProcessedMessage extends DomainObject implements Serializable {

    private static final long serialVersionUID = 1L;


    @Column(name = "name")
    private String name;
    @Column(name = "omessage")
    private String message;
    
    @ManyToOne(cascade = {CascadeType.ALL}, targetEntity = Subject.class)
    @JoinColumn(name = "subject_id", nullable = true)
    private Subject subject;

    /**
     *  @return the name
     *
     */
    public String getName() {
        return name;
    }

    /**
     *  @param name the name to set
     *
     */
    public void setName(String name) {
        this.name = name;

    }

    /**
     *  @return the message
     *
     */
    public String getMessage() {
        return message;
    }

    /**
     *  @param message the message to set
     *
     */
    public void setMessage(String message) {
        this.message = message;

    }
    /**
     *  @return the subjectIdentified
     *
     */
    public Subject getSubjectIdentified() {
        return subject;
    }

    /**
     *  @param subjectIdentified the subjectIdentified to set
     *
     */
    public void setSubjectIdentified(Subject subjectIdentified) {
        this.subject = subjectIdentified;
    }
}
