package br.com.mailanalyzer.dao;

import br.com.mailanalyzer.dao.interfaces.SubjectInterfaceDAO;
import br.com.mailanalyzer.domain.Subject;
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
public class SubjectDAO extends BaseDAO<Subject> implements SubjectInterfaceDAO{
    /**
     * Cria uma nova instância do tipo {@link SubjectDAO}
     */
    public SubjectDAO(){
        super(Subject.class);
    }
    
    /**
     * {@inheritDoc}
     */
    public List<Subject> getLikeName(String nome) {
        Criteria criteria = this.createCriteria(Subject.class);
        criteria.add(Restrictions.like("name", nome));
        return GenericsUtil.checkedList(criteria.list(), Subject.class);
    }

    /**
     * {@inheritDoc}
     */
    public List<Subject> getLikeTexto(String txt) {
        Criteria criteria = this.createCriteria(Subject.class);
        criteria.add(Restrictions.like("text", txt));
        return GenericsUtil.checkedList(criteria.list(), Subject.class);
    }

    /**
     * {@inheritDoc}
     */
    public List<Subject> getByCommandFlowName(String nome) {
        Criteria criteria = this.createCriteria(Subject.class);
        criteria.add(Restrictions.eq("commandFlowName", nome));
        return GenericsUtil.checkedList(criteria.list(), Subject.class);
    }
    
}
