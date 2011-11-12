package br.com.mailanalyzer.dao;

import br.com.mailanalyzer.dao.interfaces.ProcessedMessageInterfaceDAO;
import br.com.mailanalyzer.domain.ProcessedMessage;
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
public class ProcessedMessageDAO extends BaseDAO<ProcessedMessage> implements ProcessedMessageInterfaceDAO {

    /**
     * Cria uma nova instância do tipo {@link ProcessedMessageDAO}
     */
    public ProcessedMessageDAO(){
        super(ProcessedMessage.class);
    }

    /**
     * {@inheritDoc}
     */
    public List<ProcessedMessage> getLikeName(String keyword) {
        Criteria criteria = this.createCriteria(ProcessedMessage.class);
        criteria.add(Restrictions.like("name", keyword));
        criteria.add(Restrictions.like("message", keyword));
        return GenericsUtil.checkedList(criteria.list(), ProcessedMessage.class);
    }

    /**
     * {@inheritDoc}
     */
    public List<ProcessedMessage> getBySubject(Integer subjectID) {
        Criteria criteria = this.createCriteria(ProcessedMessage.class);
        criteria.add(Restrictions.eq("subject.id", subjectID));
        return GenericsUtil.checkedList(criteria.list(), ProcessedMessage.class);
    }
}
