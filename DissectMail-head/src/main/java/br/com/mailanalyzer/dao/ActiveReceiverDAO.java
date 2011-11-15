package br.com.mailanalyzer.dao;

import br.com.mailanalyzer.dao.interfaces.ActiveReceiverInterfaceDAO;
import br.com.mailanalyzer.domain.ActiveReceiver;
import br.com.mailanalyzer.utils.GenericsUtil;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Lucas Israel
 * @contact mcluck.ti@gmail.com
 * @version 1.0
 * @Date 28-04-2011
 *
 */
public class ActiveReceiverDAO extends BaseDAO<ActiveReceiver> implements ActiveReceiverInterfaceDAO {

    /**
     * Cria uma nova instância do tipo {@link ActiveReceiverDAO}
     */
    public ActiveReceiverDAO() {
        super(ActiveReceiver.class);
    }

    /**
     * {@inheritDoc}
     */
    public List<ActiveReceiver> getLikeName(String nome) {
        Criteria criteria = this.createCriteria(ActiveReceiver.class);
        criteria.add(Restrictions.like("nome", nome));
        return GenericsUtil.checkedList(criteria.list(), ActiveReceiver.class);
    }

    /**
     * {@inheritDoc}
     */
    public List<ActiveReceiver> getLikeHost(String hoste) {
        Criteria criteria = this.createCriteria(ActiveReceiver.class);
        criteria.add(Restrictions.like("host", hoste));
        return GenericsUtil.checkedList(criteria.list(), ActiveReceiver.class);
    }

    /**
     * {@inheritDoc}
     */
    public List<ActiveReceiver> getbyType(Integer type) {
        Criteria criteria = this.createCriteria(ActiveReceiver.class);
        criteria.add(Restrictions.eq("host", type));
        return GenericsUtil.checkedList(criteria.list(), ActiveReceiver.class);
    }
}
