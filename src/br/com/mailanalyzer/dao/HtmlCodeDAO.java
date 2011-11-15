/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.mailanalyzer.dao;

import br.com.mailanalyzer.dao.interfaces.HtmlCodeInterfaceDAO;
import br.com.mailanalyzer.domain.HtmlCode;
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
public class HtmlCodeDAO extends BaseDAO<HtmlCode> implements HtmlCodeInterfaceDAO{
    public HtmlCodeDAO(){
        super(HtmlCode.class);
    }


    /**
     * {@inheritDoc}
     */
    public List<HtmlCode> getByNome(String nome) {
        Criteria criteria = createCriteria(HtmlCode.class);
        criteria.add(Restrictions.eq("nome", nome));
        return GenericsUtil.checkedList(criteria.list(), HtmlCode.class);
    }

}
