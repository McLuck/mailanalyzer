package br.com.mailanalyzer.dao;

import br.com.mailanalyzer.dao.interfaces.LogInterfaceDAO;
import br.com.mailanalyzer.domain.DomainObject;
import br.com.mailanalyzer.domain.Log;
import br.com.mailanalyzer.utils.GenericsUtil;
import java.util.List;
import org.hibernate.Criteria;
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

    /**
     * {@inheritDoc}
     */
    public List<Log> getLikeDetalhe(String detalhe) {
        Criteria criteria = this.createCriteria(Log.class);
        criteria.add(Restrictions.like("detalhe", detalhe));
        return GenericsUtil.checkedList(criteria.list(), Log.class);
    }

    /**
     * {@inheritDoc}
     */
    public List<Log> getByOcorrencia(Integer ocorrencia) {
        Criteria criteria = this.createCriteria(Log.class);
        criteria.add(Restrictions.eq("ocorrencia", ocorrencia));
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
