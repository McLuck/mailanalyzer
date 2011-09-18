/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.mailanalyzer.dao;

import br.com.mailanalyzer.analise.Composicao;
import br.com.mailanalyzer.dao.interfaces.ComposicaoInterfaceDAO;
import br.com.mailanalyzer.domain.DomainObject;
import br.com.mailanalyzer.utils.GenericsUtil;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Lucas Israel
 * @contact mcluck.ti@gmail.com
 * @version 1.0
 * @Date 17-09-2011
 *
 */
public class ComposicaoDAO extends BaseDAO<Composicao> implements ComposicaoInterfaceDAO{
    public ComposicaoDAO(){
        super(Composicao.class);
    }
    
    public List<Composicao> getByTexto(String texto) {
        Criteria criteria = createCriteria(Composicao.class);
        criteria.add(Restrictions.eq("original", texto));
        return GenericsUtil.checkedList(criteria.list(), Composicao.class);
    }

    public List<Composicao> getByRaiz(int raizId) {
        Criteria criteria = createCriteria(Composicao.class);
        criteria.add(Restrictions.eq("raiz.id", raizId));
        return GenericsUtil.checkedList(criteria.list(), Composicao.class);
    }

}
