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
@Table(name = "omessage")
public class Message extends DomainObject{

        @Column(name = "assunto")
	private String assunto;

        @Column(name = "mesagem")
	private String mensagem;

        @Column(name = "codExterno")
	private String codExterno;

        @ManyToOne
        @JoinColumn(name="status_id")
        private Status status;

        /**
	 *  @return the assunto
	 *
	 */
	public String getAssunto() {
		return assunto;
	}

	/**
	 *  @param assunto the assunto to set
	 *
	 */
	public void setAssunto(String assunto) {
            this.assunto = assunto;

	}

	/**
	 *  @return the mensagem
	 *
	 */
	public String getMensagem() {
		return mensagem;
	}

	/**
	 *  @param mensagem the mensagem to set
	 *
	 */
	public void setMensagem(String mensagem) {
            this.mensagem = mensagem;

	}

	/**
	 *  @return the codExterno
	 *
	 */
	public String getCodExterno() {
		return codExterno;
	}

	/**
	 *  @param codExterno the codExterno to set
	 *
	 */
	public void setCodExterno(String codExterno) {
            this.codExterno = codExterno;

	}

	/**
	 *  @return the status
	 *
	 */
	public Status getStatus() {
		return status;
	}

	/**
	 *  @param status the status to set
	 *
	 */
	public void setStatus(Status status) {
            this.status = status;

	}

}
