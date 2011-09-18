/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.mailanalyzer.dao.interfaces;

import br.com.mailanalyzer.analise.Composicao;
import br.com.mailanalyzer.analise.Elemento;
import java.util.List;

/**
 *
 * @author Lucas Israel
 * @contact mcluck.ti@gmail.com
 * @version 1.0
 * @Date 17-09-2011
 *
 */
public interface ElementoInterfaceDAO extends BaseInterfaceDAO<Elemento>{
    public List<Elemento> getByComposicao(Composicao composicao);
    public List<Elemento> getByPalavra(String palavra);
}
