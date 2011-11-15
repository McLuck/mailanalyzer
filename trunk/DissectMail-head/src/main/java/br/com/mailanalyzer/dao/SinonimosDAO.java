/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.mailanalyzer.dao;


import br.com.mailanalyzer.dao.interfaces.SinonimosInterfaceDAO;
import br.com.mailanalyzer.domain.Sinonimos;
import br.com.mailanalyzer.utils.GenericsUtil;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Lucas Israel
 * @contact mcluck.ti@gmail.com
 * @version 1.0
 * @Date 03-09-2011
 *
 */
public class SinonimosDAO extends BaseDAO<Sinonimos> implements SinonimosInterfaceDAO{
    public SinonimosDAO(){
        super(Sinonimos.class);
    }

    public List<Sinonimos> getByPalavra(String palavra) {
        Criteria criteria = this.createCriteria(Sinonimos.class);
        criteria.add(Restrictions.like("sinonimoss", "%"+palavra+"%"));
        return GenericsUtil.checkedList(criteria.list(), Sinonimos.class);
    }
}
