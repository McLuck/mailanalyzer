/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.mailanalyzer.dao.interfaces;

import br.com.mailanalyzer.domain.ComposicaoDomain;
import br.com.mailanalyzer.domain.ElementoDomain;
import java.util.List;

/**
 *
 * @author Lucas Israel
 * @contact mcluck.ti@gmail.com
 * @version 1.0
 * @Date 17-09-2011
 *
 */
public interface ElementoInterfaceDAO extends BaseInterfaceDAO<ElementoDomain>{
    public List<ElementoDomain> getByComposicao(ComposicaoDomain composicao);
    public List<ElementoDomain> getByPalavra(String palavra);
}
