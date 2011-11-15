

package br.com.mailanalyzer.dao;


import br.com.mailanalyzer.dao.interfaces.RaizInterfaceDAO;
import br.com.mailanalyzer.domain.DomainObject;
import br.com.mailanalyzer.domain.RaizDomain;

/**
 *
 * @author Lucas Israel
 * @contact mcluck.ti@gmail.com
 * @version 1.0
 * @Date 17-09-2011
 *
 */
public class RaizDAO extends BaseDAO<RaizDomain> implements RaizInterfaceDAO{
    public RaizDAO(){
        super(RaizDomain.class);
    }
}
