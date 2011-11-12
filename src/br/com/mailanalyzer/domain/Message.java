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
 * @reviser
 *
 */
@Entity
@Table(name = "omessage")
public class Message extends DomainObject implements Serializable {

    private static final long serialVersionUID = 1L;
    @Column(name = "assunto")
    private String assunto;
    @Column(name = "mensagem")
    private String mensagem;
    @Column(name = "codExterno")
    private String codExterno;
    @Column(name = "origem")
    private String origem;
    @Column(name = "tipo_recebimento")
    private int tipoRecebimento;
    
    @ManyToOne(cascade = {CascadeType.ALL}, targetEntity = Status.class)
    @JoinColumn(name = "status_id", nullable = true)
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

    /**
     * @return the origem
     */
    public String getOrigem() {
        return origem;
    }

    /**
     * @param origem the origem to set
     */
    public void setOrigem(String origem) {
        this.origem = origem;
    }

    /**
     * @return the tipoRecebimento
     */
    public int getTipoRecebimento() {
        return tipoRecebimento;
    }

    /**
     * @param tipoRecebimento the tipoRecebimento to set
     */
    public void setTipoRecebimento(int tipoRecebimento) {
        this.tipoRecebimento = tipoRecebimento;
    }
}
