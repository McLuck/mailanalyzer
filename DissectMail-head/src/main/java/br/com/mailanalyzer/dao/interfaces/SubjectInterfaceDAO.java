package br.com.mailanalyzer.dao.interfaces;

import br.com.mailanalyzer.domain.Subject;
import java.util.List;

/**
 *
 * @author Lucas Israel
 * @contact mcluck.ti@gmail.com
 * @version 1.0
 * @Date 28-04-2011
 *
 */
public interface SubjectInterfaceDAO extends BaseInterfaceDAO<Subject> {
    /**
     * Recupera uma lista de Subject buscando por nome parcial.
     * 
     * @param nome Nome parcial para busca
     * @return lista de Subject
     */
    List<Subject> getLikeName(String nome);
    
    /**
     * Recupera uma lista de Subject buscando por texto parcial.
     * 
     * @param txt Texto parcial para busca
     * @return lista de Subject
     */
    List<Subject> getLikeTexto(String txt);
    
    /**
     * Recupera uma lista de Subject buscando por nome do fluxo de comando.
     * 
     * @param nome Nome do fluxo de comando para busca
     * @return lista de Subject
     */
    List<Subject> getByCommandFlowName(String nome);
}
