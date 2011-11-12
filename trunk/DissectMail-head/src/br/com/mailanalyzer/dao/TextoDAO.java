/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.mailanalyzer.dao;

import br.com.mailanalyzer.dao.interfaces.TextoInterfaceDAO;
import br.com.mailanalyzer.domain.Texto;

/**
 *
 * @author McLuck
 */
public class TextoDAO extends BaseDAO<Texto> implements TextoInterfaceDAO{
    public TextoDAO(){
        super(Texto.class);
    }
}
