/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.mailanalyzer.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @param class ActiveReceiver
 * @author Francisca Herlayne
 * @version 1.0 
 */
  @Entity
  @Table(name = "activerecive")
public class ActiveReceiver extends DomainObject {

        @Column(name = "nome")
  	private String nome;

        @Column(name = "usuario")
	private String usuario;

        @Column(name = "senha")
	private String senha;

        @Column(name = "ohost")
	private String host;

        @Column(name = "isssl")
	private boolean ssl;

        @Column(name = "porta")
	private int port;

        @Column(name = "otype")
        private int otype;

        @Column(name = "lastid")
        private int lastID;

   /**
    * @return the host
    */
    public String getHost() {
        return host;
    }

    /**
     * @param host the host to set
     */
    public void setHost(String host) {
        this.host = host;
    }

    public int getLastID() {
        return lastID;
    }

    public void setLastID(int lastID) {
        this.lastID = lastID;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getOtype() {
        return otype;
    }

    public void setOtype(int otype) {
        this.otype = otype;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public boolean isSsl() {
        return ssl;
    }

    public void setSsl(boolean ssl) {
        this.ssl = ssl;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }



}
