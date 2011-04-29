package br.com.mailanalyzer.dao.interfaces;

import br.com.mailanalyzer.domain.Message;
import java.util.List;

/**
 *
 * @author Lucas Israel
 * @contact mcluck.ti@gmail.com
 * @version 1.0
 * @Date 28-04-2011
 *
 */
public interface MessageInterfaceDAO extends BaseInterfaceDAO<Message> {

    /**
     * Recupera uma lista de Message buscando por keyword parcial.
     * 
     * @param keyword Assunto ou mensagem parcial para busca
     * @return lista de Message
     */
    List<Message> getLikeKeyword(String keyword);
}
