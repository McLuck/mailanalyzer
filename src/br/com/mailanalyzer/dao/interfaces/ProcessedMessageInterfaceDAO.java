package br.com.mailanalyzer.dao.interfaces;

import br.com.mailanalyzer.domain.ProcessedMessage;
import java.util.List;

/**
 *
 * @author Lucas Israel
 * @contact mcluck.ti@gmail.com
 * @version 1.0
 * @Date 28-04-2011
 *
 */
public interface ProcessedMessageInterfaceDAO extends BaseInterfaceDAO<ProcessedMessage> {

    /**
     * Recupera uma lista de ProcessedMessage buscando por keyword parcial.
     * 
     * @param nome Keyword parcial para busca em nome ou na mensagem
     * @return lista de ProcessedMessage
     */
    List<ProcessedMessage> getLikeName(String keyword);

    /**
     * Recupera uma lista de ProcessedMessage buscando por ID de subject.
     * 
     * @param subjectID ID da subject para busca
     * @return lista de ProcessedMessage
     */
    List<ProcessedMessage> getBySubject(Integer subjectID);
}
