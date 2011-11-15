/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.mailanalyzer.dao.interfaces;

import br.com.mailanalyzer.domain.Palavra;
import java.util.List;

/**
 *
 * @author McLuck
 */
public interface PalavraInterfaceDAO extends BaseInterfaceDAO<Palavra>{
    public boolean existe(String palavra);

    public List<Palavra> getPalavrasSalvas(String texto);
}
