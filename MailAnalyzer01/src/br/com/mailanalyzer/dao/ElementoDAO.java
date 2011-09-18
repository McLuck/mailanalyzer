/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.mailanalyzer.dao;

import br.com.mailanalyzer.analise.Composicao;
import br.com.mailanalyzer.analise.Elemento;
import br.com.mailanalyzer.dao.interfaces.ElementoInterfaceDAO;
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
public class ElementoDAO extends BaseDAO<Elemento> implements ElementoInterfaceDAO{
    public ElementoDAO(){
        super(Elemento.class);
    }

    public List<Elemento> getByComposicao(Composicao composicao) {
        Criteria criteria = createCriteria(Elemento.class);
        criteria.add(Restrictions.eq("itemPai.id", composicao.getId()));
        return GenericsUtil.checkedList(criteria.list(), Elemento.class);
        
    }

    public List<Elemento> getByPalavra(String palavra) {
        Criteria criteria = createCriteria(Elemento.class);
        criteria.add(Restrictions.eq("palavra", palavra));
        return GenericsUtil.checkedList(criteria.list(), Elemento.class);
    }
}
