/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.mailanalyzer.dao.interfaces;

import br.com.mailanalyzer.domain.HtmlCode;
import java.util.List;

/**
 *
 * @author Lucas Israel
 * @contact mcluck.ti@gmail.com
 * @version 1.0
 * @Date 19-09-2011
 *
 */
public interface HtmlCodeInterfaceDAO extends BaseInterfaceDAO<HtmlCode>{
    /**
     * Recupera uma lista de HtmlCode buscando por nome
     *
     * @param nome Nome a ser buscado
     * @return lista de HtmlCode
     */
    List<HtmlCode> getByNome(String nome);
}
