/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.mailanalyzer.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Dominio para assuntos nao resolvidos. Assuntos nao encontrados
 * @author Lucas Israel
 * @contact mcluck.ti@gmail.com
 * @version 1.0
 * @Date 25-09-2011
 *
 */
@Entity
@Table(name="assuntos_pendentes")
public class AssuntosPendentes extends DomainObject{
    
    @ManyToOne(cascade = {CascadeType.REFRESH}, targetEntity = Message.class)
    @JoinColumn(name = "message_id", nullable = true)
    private Message message;

    private boolean resolvido;

    /**
     * @return the message
     */
    public Message getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(Message message) {
        this.message = message;
    }

    /**
     * @return the resolvido
     */
    public boolean isResolvido() {
        return resolvido;
    }

    /**
     * @param resolvido the resolvido to set
     */
    public void setResolvido(boolean resolvido) {
        this.resolvido = resolvido;
    }
}
