/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.mailanalyzer.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Bruno Marin Mota
 */
@Entity
@Table(name = "processed_message")
public class ProcessedMessage extends DomainObject{

        @Column(name = "name")
	private String name;

        @Column(name = "omessage")
	private String message;

        @ManyToOne
        @JoinColumn(name="subject_id")
	//private Subject subject;

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
/*	public Subject getSubjectIdentified() {
		return subject;
	}

*/
	/**
	 *  @param subjectIdentified the subjectIdentified to set
	 *
	 */
/*	public void setSubjectIdentified(Subject subjectIdentified) {
            this.subject = subject;

	}
*/
        
}
