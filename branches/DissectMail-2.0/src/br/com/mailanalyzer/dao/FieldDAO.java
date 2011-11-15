package br.com.mailanalyzer.dao;

import br.com.mailanalyzer.dao.interfaces.FieldInterfaceDAO;
import br.com.mailanalyzer.domain.Field;
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
public class FieldDAO extends BaseDAO<Field> implements FieldInterfaceDAO{
    
    /**
     * Cria uma nova instância do tipo {@link FieldDAO}
     */
    public FieldDAO(){
        super(Field.class);
    }

    /**
     * {@inheritDoc}
     */
    public List<Field> getLikeName(String nome) {
        Criteria criteria = this.createCriteria(Field.class);
        criteria.add(Restrictions.like("name", nome));
        return GenericsUtil.checkedList(criteria.list(), Field.class);
    }

    /**
     * {@inheritDoc}
     */
    public List<Field> getBySubject(Integer subjectID) {
        Criteria criteria = this.createCriteria(Field.class);
        criteria.add(Restrictions.eq("subject.id", subjectID));
        return GenericsUtil.checkedList(criteria.list(), Field.class);
    }
    
}
