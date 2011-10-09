package br.com.mailanalyzer.domain;

import br.com.mailanalyzer.dao.AbstractDAO;
import java.io.Serializable;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;

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
@Table(name = "olog")
public class Log extends DomainObject implements Serializable {

    private static final long serialVersionUID = 1L;
    private int ocorrencia, nivel;
    private String detalhe, referencia, exceptionApp, tagApp;

    /**
     *
     *  @return the Object of the reference of the LOG, if exist.
     *
     */
    public DomainObject getReference() {
        AbstractDAO dao = new AbstractDAO();
        try {
            String ref[] = referencia.split(";");
            int idobj = Integer.parseInt(ref[0]);
            String className = ref[1];

            DomainObject dmo = (DomainObject) Class.forName(className).newInstance();
            dmo.setId(idobj);
            dmo = (DomainObject) dao.getCompleteObjById(dmo);
            return dmo;
        } catch (Exception ex) {
            dao.closeSession();
        }

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

    /**
     * @return the nivel
     */
    public int getNivel() {
        return nivel;
    }

    /**
     * @param nivel the nivel to set
     */
    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    /**
     * @return the exceptionApp
     */
    public String getExceptionApp() {
        return exceptionApp;
    }

    /**
     * @param exceptionApp the exceptionApp to set
     */
    public void setExceptionApp(String exceptionApp) {
        this.exceptionApp = exceptionApp;
    }

    /**
     * @return the tagApp
     */
    public String getTagApp() {
        return tagApp;
    }

    /**
     * @param tagApp the tagApp to set
     */
    public void setTagApp(String tagApp) {
        this.tagApp = tagApp;
    }
}
