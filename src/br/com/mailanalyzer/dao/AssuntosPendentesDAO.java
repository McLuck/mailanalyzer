
package br.com.mailanalyzer.dao;

import br.com.mailanalyzer.dao.interfaces.AssuntosPendentesInterfaceDAO;
import br.com.mailanalyzer.domain.AssuntosPendentes;
import br.com.mailanalyzer.utils.GenericsUtil;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Lucas Israel
 * @contact mcluck.ti@gmail.com
 * @version 1.0
 * @Date 25-09-2011
 *
 */
public class AssuntosPendentesDAO extends BaseDAO<AssuntosPendentes> implements AssuntosPendentesInterfaceDAO{
    public AssuntosPendentesDAO(){
        super(AssuntosPendentes.class);
    }

    @Override
    public List<AssuntosPendentes> getByMessage(int messageID) {
        Criteria criteria = createCriteria(AssuntosPendentes.class);
        criteria.add(Restrictions.eq("message.id", messageID));
        return GenericsUtil.checkedList(criteria.list(), AssuntosPendentes.class);
    }

    @Override
    public List<AssuntosPendentes> getByResolvidos(boolean resolvido) {
        Criteria criteria = createCriteria(AssuntosPendentes.class);
        criteria.add(Restrictions.eq("resolvido", resolvido));
        return GenericsUtil.checkedList(criteria.list(), AssuntosPendentes.class);
    }

}
