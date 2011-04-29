package br.com.mailanalyzer.dao;

import br.com.mailanalyzer.dao.interfaces.TermVariationInterfaceDAO;
import br.com.mailanalyzer.domain.TermVariation;
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
public class TermVariationDAO extends BaseDAO<TermVariation> implements TermVariationInterfaceDAO{
    
    /**
     * Cria uma nova instância do tipo {@link TermVariationDAO}
     */
    public TermVariationDAO(){
        super(TermVariation.class);
    }

    /**
     * {@inheritDoc}
     */
    public List<TermVariation> getLikeName(String nome) {
        Criteria criteria = this.createCriteria(TermVariation.class);
        criteria.add(Restrictions.like("name", nome));
        return GenericsUtil.checkedList(criteria.list(), TermVariation.class);
    }

    /**
     * {@inheritDoc}
     */
    public List<TermVariation> getLikeVariations(String variations) {
        Criteria criteria = this.createCriteria(TermVariation.class);
        criteria.add(Restrictions.like("variations", variations));
        return GenericsUtil.checkedList(criteria.list(), TermVariation.class);
    }

    /**
     * {@inheritDoc}
     */
    public List<TermVariation> getLikeReplacers(String replacers) {
        Criteria criteria = this.createCriteria(TermVariation.class);
        criteria.add(Restrictions.like("replacer", replacers));
        return GenericsUtil.checkedList(criteria.list(), TermVariation.class);
    }
    
}
