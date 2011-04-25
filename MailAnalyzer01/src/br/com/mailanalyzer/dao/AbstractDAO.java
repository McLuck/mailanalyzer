
package br.com.mailanalyzer.dao;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
/**
 *
 * @author Lucas Israel
 * @contact mcluck.ti@gmail.com
 * @version 1.0
 * @Date 25-04-2011
 *
 */
public class AbstractDAO {

    private Session session;

    private void createSession() {
        if (session == null) {
            session = HB.getSESSION();
        } else if (!session.isConnected()) {
            session = HB.getSESSION();
        } else if (!session.isOpen()) {
            session = HB.getSESSION();
        }
    }

    /**
     * It s close session of the hibernate
     */
    public void closeSession() {
        try {
            session.flush();
        } catch (Exception e) {
        }
        try {
            session.close();
        } catch (Exception e) {
        }
        session = null;
    }

    /**
     * This will insert or update objects of the domain class
     * @param domainObject A object of the domain class
     * @throws Exception Possible exception of the process
     */
    public void save(Object domainObject) throws Exception {
        createSession();
        session.beginTransaction();
        session.saveOrUpdate(domainObject);
        session.getTransaction().commit();
        //closeSession();
    }

    /**
     * This will delete object of the domain class
     * @param domainObject A object of the domain class
     * @throws Exception Possible exception of the process
     */
    public void delete(Object domainObject) throws Exception {
        createSession();
        session.beginTransaction();
        session.delete(domainObject);
        session.getTransaction().commit();
        closeSession();
    }

    /**
     * This will execute a query in DB. A session of the hibernate will be created and not will closed after transaction.
     * @param hql a String with HQL or SQL query
     * @return a java.util.List containing the collection of de DomainObjects
     */
    public List getList(String hql) {
        List objs = new ArrayList();
        createSession();
        session.beginTransaction();
        Query q = session.createQuery(hql);
        objs = q.list();
        //session.getTransaction().commit();
        return objs;
    }

    /**
     * This will load all registers from DB. A session of the hibernate will be created and not will closed after transaction.
     * @param domainObject a object of the DomainObject class
     * @return a java.util.List containing the collection of de DomainObjects
     */
    public List getListAllRegisters(Object domainObject) {
        String hql = "FROM " + domainObject.getClass().getName();
        return getList(hql);
    }

    /**
     * This will complete a object of the domain class from DB using id as parameter. A session of the hibernate be opened.
     * @param domain a object of the domain class contening id for search
     * @return a object of the domain class with all attributes from DB
     * @throws Exception Possible exception of the process
     */
    public Object getCompleteObjById(Object domain) throws Exception {
        Method method = domain.getClass().getMethod("getId", new Class[0]);
        Object retorno = method.invoke(domain, new Object[0]);
        String id = String.valueOf(retorno);
        StringBuffer sb = new StringBuffer();
        sb.append("FROM ");
        sb.append(domain.getClass().getName());
        sb.append(" WHERE id = '");
        sb.append(id);
        sb.append("'");
        domain = ((List<Object>) this.getList(sb.toString())).get(0);
        return domain;
    }
}
