/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.mailanalyzer.dao.interfaces;

import br.com.mailanalyzer.domain.AssuntosPendentes;
import java.util.List;

/**
 *
 * @author Lucas Israel
 * @contact mcluck.ti@gmail.com
 * @version 1.0
 * @Date 25-09-2011
 *
 */
public interface AssuntosPendentesInterfaceDAO extends BaseInterfaceDAO<AssuntosPendentes>{
    
    List<AssuntosPendentes> getByMessage(int assuntoID);
    List<AssuntosPendentes> getByResolvidos(boolean resolvido);
}
