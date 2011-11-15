package br.com.mailanalyzer.dao.interfaces;

import br.com.mailanalyzer.domain.Sinonimos;
import java.util.List;

/**
 *
 * @author Lucas Israel
 * @contact mcluck.ti@gmail.com
 * @version 1.0
 * @Date 28-04-2011
 *
 */
public interface SinonimosInterfaceDAO extends BaseInterfaceDAO<Sinonimos>{
    /**
     * Sinonimos da palavra passada por parametro.<br>
     * Cada elemento da lista, é um grupo diferente de sinonimos.
     *
     * @param palavra O termo que deseja buscar sinonimos
     * @return lista de Conjunto de sinonimos.
     */
    List<Sinonimos> getByPalavra(String palavra);
}
