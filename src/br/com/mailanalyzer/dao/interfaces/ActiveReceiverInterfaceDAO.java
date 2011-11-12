package br.com.mailanalyzer.dao.interfaces;

import br.com.mailanalyzer.domain.ActiveReceiver;
import java.util.List;

/**
 *
 * @author Lucas Israel
 * @contact mcluck.ti@gmail.com
 * @version 1.0
 * @Date 28-04-2011
 *
 */
public interface ActiveReceiverInterfaceDAO extends BaseInterfaceDAO<ActiveReceiver> {

    /**
     * Recupera uma lista de ActiveReceiver buscando por nome parcial.
     * 
     * @param nome Nome parcial para busca
     * @return lista de ActiveReceiver
     */
    List<ActiveReceiver> getLikeName(String nome);

    /**
     * Recupera uma lista de ActiveReceiver buscando por host parcial.
     * 
     * @param hoste Host parcial a ser buscado
     * @return lista de ActiveReceiver
     */
    List<ActiveReceiver> getLikeHost(String hoste);

    /**
     * Recupera uma lista de ActiveReceiver buscando por tipo.
     * 
     * @param type Tipo a ser buscado
     * @return lista de ActiveReceiver
     */
    List<ActiveReceiver> getbyType(Integer type);
}
