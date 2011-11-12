/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.mailanalyzer.dao;

import br.com.mailanalyzer.dao.interfaces.ComposicaoInterfaceDAO;
import br.com.mailanalyzer.domain.ComposicaoDomain;
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
public class ComposicaoDAO extends BaseDAO<ComposicaoDomain> implements ComposicaoInterfaceDAO{
    public ComposicaoDAO(){
        super(ComposicaoDomain.class);
    }
    
    public List<ComposicaoDomain> getByTextoOriginal(String texto) {
        Criteria criteria = createCriteria(ComposicaoDomain.class);
        criteria.add(Restrictions.eq("textoOriginal", texto));
        return GenericsUtil.checkedList(criteria.list(), ComposicaoDomain.class);
    }

    public List<ComposicaoDomain> getByRaiz(int raizId) {
        Criteria criteria = createCriteria(ComposicaoDomain.class);
        criteria.add(Restrictions.eq("raiz.id", raizId));
        return GenericsUtil.checkedList(criteria.list(), ComposicaoDomain.class);
    }

    public List<ComposicaoDomain> getComposicoesComuns() {
        Criteria criteria = createCriteria(ComposicaoDomain.class);
        criteria.add(Restrictions.eq("raiz","0"));
        return GenericsUtil.checkedList(criteria.list(), ComposicaoDomain.class);
    }

}
