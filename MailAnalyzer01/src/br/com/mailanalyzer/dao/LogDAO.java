package br.com.mailanalyzer.dao;

import br.com.mailanalyzer.dao.interfaces.LogInterfaceDAO;
import br.com.mailanalyzer.domain.DomainObject;
import br.com.mailanalyzer.domain.Log;
import br.com.mailanalyzer.utils.GenericsUtil;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Lucas Israel
 * @contact mcluck.ti@gmail.com
 * @version 1.0
 * @Date 29-04-2011
 *
 */
public class LogDAO extends BaseDAO<Log> implements LogInterfaceDAO{
    
    /**
     * Cria uma nova instância do tipo {@link LogDAO}
     */
    public LogDAO(){
        super(Log.class);
        isLOGGING = true;
    }

    @Override
    public Order getOrdemLista(){
        return Order.desc("id");
    }

    /**
     * {@inheritDoc}
     */
    public List<Log> getLikeDetalhe(String detalhe) {
        Criteria criteria = this.createCriteria(Log.class);
        criteria.add(Restrictions.like("detalhe", "%"+detalhe+"%"));
        criteria.addOrder(getOrdemLista());
        criteria.setMaxResults(getQtdRegistro());
        return GenericsUtil.checkedList(criteria.list(), Log.class);
    }

    public List<Log> getLikeAll(String all) {
        Criteria criteria = this.createCriteria(Log.class);
        criteria.add(Restrictions.or(Restrictions.like("detalhe", "%"+all+"%"), Restrictions.or(Restrictions.like("referencia", "%"+all+"%"), Restrictions.like("tagApp", "%"+all+"%"))));
        criteria.addOrder(getOrdemLista());
        criteria.setMaxResults(getQtdRegistro());
        return GenericsUtil.checkedList(criteria.list(), Log.class);
    }

    public List<Log> getLikeLikeTag(String all) {
        Criteria criteria = this.createCriteria(Log.class);
        criteria.add(Restrictions.like("tagApp", "%"+all+"%"));
        criteria.addOrder(getOrdemLista());
        criteria.setMaxResults(getQtdRegistro());
        return GenericsUtil.checkedList(criteria.list(), Log.class);
    }

    public List<Log> getLikeLikeReferencia(String referencia) {
        Criteria criteria = this.createCriteria(Log.class);
        criteria.add(Restrictions.like("referencia", "%"+referencia+"%"));
        criteria.addOrder(getOrdemLista());
        criteria.setMaxResults(getQtdRegistro());
        return GenericsUtil.checkedList(criteria.list(), Log.class);
    }


    /**
     * {@inheritDoc}
     */
    public List<Log> getByOcorrencia(Integer ocorrencia) {
        Criteria criteria = this.createCriteria(Log.class);
        criteria.add(Restrictions.eq("ocorrencia", ocorrencia));
        criteria.addOrder(getOrdemLista());
        criteria.setMaxResults(getQtdRegistro());
        return GenericsUtil.checkedList(criteria.list(), Log.class);
    }

    public List<Log> getByOcorrencias(Integer[] ocorrencias) {
        Criteria criteria = this.createCriteria(Log.class);
        criteria.add(Restrictions.in("ocorrencia", ocorrencias));
        criteria.addOrder(getOrdemLista());
        criteria.setMaxResults(getQtdRegistro());
        return GenericsUtil.checkedList(criteria.list(), Log.class);
    }

    /**
     * {@inheritDoc}
     */
    public List<Log> getByReferencia(DomainObject referencia) {
        StringBuffer sb = new StringBuffer();
        sb.append(referencia.getId());
        sb.append(";");
        sb.append(referencia.getClass().getName());
        sb.append(";");
        Criteria criteria = this.createCriteria(Log.class);
        criteria.add(Restrictions.eq("referencia", sb.toString()));
        return GenericsUtil.checkedList(criteria.list(), Log.class);
    }
    
}
