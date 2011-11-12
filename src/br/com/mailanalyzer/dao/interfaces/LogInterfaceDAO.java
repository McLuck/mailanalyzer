package br.com.mailanalyzer.dao.interfaces;

import br.com.mailanalyzer.domain.DomainObject;
import br.com.mailanalyzer.domain.Log;
import java.util.List;

/**
 *
 * @author Lucas Israel
 * @contact mcluck.ti@gmail.com
 * @version 1.0
 * @Date 28-04-2011
 *
 */
public interface LogInterfaceDAO extends BaseInterfaceDAO<Log> {

    /**
     * Recupera uma lista de Log buscando por detalhe parcial.
     * 
     * @param detalhe Detalhe parcial da busca
     * @return lista de Log
     */
    List<Log> getLikeDetalhe(String detalhe);
    
    /**
     * Recupera uma lista de Log buscando por Ocorrencia
     * 
     * @param ocorrencia ID da ocorrencia a ser buscada
     * @return lista de Log
     */
    List<Log> getByOcorrencia(Integer ocorrencia);
    
    /**
     * Recupera uma lista de Log buscando por referencia
     * 
     * @param referencia DomainObject a ser pesquisado no LOG
     * @return lista de Log
     */
    List<Log> getByReferencia(DomainObject referencia);
}
