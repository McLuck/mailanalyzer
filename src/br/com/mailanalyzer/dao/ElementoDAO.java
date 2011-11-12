/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.mailanalyzer.dao;

import br.com.mailanalyzer.dao.interfaces.ElementoInterfaceDAO;
import br.com.mailanalyzer.domain.ComposicaoDomain;
import br.com.mailanalyzer.domain.ElementoDomain;
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
public class ElementoDAO extends BaseDAO<ElementoDomain> implements ElementoInterfaceDAO{
    public ElementoDAO(){
        super(ElementoDomain.class);
    }

    public List<ElementoDomain> getByComposicao(ComposicaoDomain composicao) {
        Criteria criteria = createCriteria(ElementoDomain.class);
        criteria.add(Restrictions.eq("itemPai.id", composicao.getId()));
        return GenericsUtil.checkedList(criteria.list(), ElementoDomain.class);
        
    }

    public List<ElementoDomain> getByPalavra(String palavra) {
        Criteria criteria = createCriteria(ElementoDomain.class);
        criteria.add(Restrictions.eq("palavra", palavra));
        return GenericsUtil.checkedList(criteria.list(), ElementoDomain.class);
    }
}
