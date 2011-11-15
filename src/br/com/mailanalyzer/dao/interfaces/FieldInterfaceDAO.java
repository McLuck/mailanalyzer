package br.com.mailanalyzer.dao.interfaces;

import br.com.mailanalyzer.domain.Field;
import java.util.List;

/**
 *
 * @author Lucas Israel
 * @contact mcluck.ti@gmail.com
 * @version 1.0
 * @Date 28-04-2011
 *
 */
public interface FieldInterfaceDAO extends BaseInterfaceDAO<Field> {

    /**
     * Recupera uma lista de Field buscando por nome parcial.
     * 
     * @param nome Nome parcial para busca
     * @return lista de Field
     */
    List<Field> getLikeName(String nome);
    
    /**
     * Recupera uma lista de Field buscando por id do subject.
     * 
     * @param subjectID ID do subject a ser buscado
     * @return lista de Field
     */
    List<Field> getBySubject(Integer subjectID);
}
