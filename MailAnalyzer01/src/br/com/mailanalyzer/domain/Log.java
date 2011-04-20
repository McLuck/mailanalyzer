package br.com.mailanalyzer.domain;

import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;

@Entity
@Table(name="olog")
public class Log extends DomainObject {

	private int ocorrencia;
	private String detalhe,referencia;

	/**
	 *
	 *  @return the Object of the reference of the LOG, if exist.
	 *
	 */
	public DomainObject getReference() {
		return null;
	}

	/**
	 *  @return the ocorrencia
	 *
	 */
	 public int getOcorrencia() {
        return ocorrencia;
    }

    /**
     * @param ocorrencia the ocorrencia to set
     */
    public void setOcorrencia(int ocorrencia) {
        this.ocorrencia = ocorrencia;
    }

    /**
     * @return the detalhe
     */
    public String getDetalhe() {
        return detalhe;
    }

    /**
     * @param detalhe the detalhe to set
     */
    public void setDetalhe(String detalhe) {
        this.detalhe = detalhe;
    }

    /**
     * @return the referencia
     */
    public String getReferencia() {
        return referencia;
    }

    /**
     * @param referencia the referencia to set
     */
    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }
}