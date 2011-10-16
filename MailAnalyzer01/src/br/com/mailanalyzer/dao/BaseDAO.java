package br.com.mailanalyzer.dao;

import br.com.mailanalyzer.dao.interfaces.BaseInterfaceDAO;
import br.com.mailanalyzer.domain.DomainObject;
import br.com.mailanalyzer.log.L;
import br.com.mailanalyzer.utils.GenericsUtil;
import java.io.Serializable;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Order;

/**
 *
 * @author Lucas Israel
 * @contact mcluck.ti@gmail.com
 * @version 1.0
 * @Date 28-04-2011
 *
 */
public abstract class BaseDAO<T extends DomainObject> implements BaseInterfaceDAO<T> {

    private SessionFactory sessionFactory;
    private Class<T> domainObject;
    private HB hibernateUtil;
    protected boolean isLOGGING = false;
    private int qtdRegistro = QTD_REGISTROS_PAGINA;
   

    /**
     * Cria uma nova inst�ncia do tipo {@link BaseDAO}.
     * 
     * @param domainObject classe utilizada
     */
    protected BaseDAO(Class<T> domainObject) {
        this.domainObject = domainObject;
        this.hibernateUtil = HB.getInstancia();
    }

    /**
     * {@inheritDoc}
     */
    public void atualizar(T obj) {
        getSession().getTransaction().begin();
        if (obj.getId() == 0) {
            obj.setDataRegistro(new java.util.Date().getTime());
            obj.setDataAlteracao(0);
        } else {
            obj.setDataAlteracao(new java.util.Date().getTime());
        }
        this.getSession().update(obj);
        this.getSession().flush();
    }

    public void commit() {
        try {
            getSession().getTransaction().commit();
        } catch (Exception e) {
        }
    }

    public void clear(){
        try{
            getSession().clear();
        }catch(Exception e){

        }
    }
    public void close() {
        try {
            getSession().getTransaction().commit();
        } catch (Exception e) {
            L.e("BASEDAO", this, "Commit foi chamado, mas n�o existe uma conex�o aberta.", e);
        }
        try {
            getSession().flush();
        } catch (Exception e) {
            L.e("BASEDAO", this, "Falha ao fazer descarga na sess�o do Hibernate.", e);
        }
        try {
            getSession().close();
        } catch (Exception ex) {
            L.e("BASEDAO", this, "DAO - Falha ao fechar conex�o.", ex);
        }
    }

    /**
     * {@inheritDoc}
     */
    public void excluir(T obj) {
        this.getSession().delete(obj);
        this.getSession().flush();
    }

    /**
     * {@inheritDoc}
     */
    public T obter(Integer pk) {
        return this.domainObject.cast(this.getSession().get(this.domainObject, pk));
    }

    /**
     * {@inheritDoc}
     */
    public T obterAntigo(Integer pk) {
        return this.domainObject.cast(this.hibernateUtil.getNewSession().get(this.domainObject, pk));
    }

    /**
     * {@inheritDoc}
     */
    public List<T> obterTodos() {
        Criteria criteria = this.createCriteria(this.domainObject);
        Order order = this.getOrdemLista();
        if (order != null) {
            criteria.addOrder(order);
        }
        if(qtdRegistro!= QTD_REGISTROS_PAGINA || isLOGGING){
            criteria.setMaxResults(qtdRegistro);
        }
        return GenericsUtil.checkedList(criteria.list(), this.domainObject);
    }

    /**
     * {@inheritDoc}
     */
    public Integer salvar(T obj) {
        getSession().getTransaction().begin();
        if (obj.getId() == 0) {
            obj.setDataRegistro(new java.util.Date().getTime());
            obj.setDataAlteracao(0);
        } else {
            obj.setDataAlteracao(new java.util.Date().getTime());
        }
        Integer id = (Integer) this.getSession().save(obj);
        //this.getSession().flush();
        return id;
    }

    /**
     * Atribui o utilit�rio {@link HibernateUtil}.
     * 
     * @param hibernateUtil {@link HibernateUtil}
     */
    public void setHibernateUtil(HB hibernateUtil) {
        this.hibernateUtil = hibernateUtil;
    }

    /**
     * Atribui a f�brica de sess�es do hibernate.
     * 
     * @param sessionFactory f�brica de sess�es do hibernate
     */
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * Adiciona pagina��o a consulta.
     * 
     * @param criteria criteria da consulta
     * @param paginaAtual n�mero da p�gina atual
     * @param maximoRegistros m�ximo de registros da p�gina
     */
    protected void adicionarPaginacao(Criteria criteria, Integer paginaAtual, int maximoRegistros) {
        if (paginaAtual != null) {
            criteria.setFirstResult(paginaAtual.intValue() * maximoRegistros);
        }
        criteria.setMaxResults(maximoRegistros);
    }

    /**
     * Cria um {@link Criteria} para a classe informada.
     * 
     * @param clazz classe a criar o criteria
     * @return criteria da classe
     */
    protected Criteria createCriteria(Class<? extends Serializable> clazz) {
        return this.getSession().createCriteria(clazz);
    }

    /**
     * Cria um {@link Criteria} para a classe informada.
     * 
     * @param clazz classe a criar o criteria
     * @param alias alias para o objeto
     * @return criteria da classe
     */
    protected Criteria createCriteria(Class<? extends Serializable> clazz, String alias) {
        return this.getSession().createCriteria(clazz, alias);
    }

    /**
     * Recupera a ordena��o padr�o do m�todo {@link #obterTodos()}.
     * 
     * @return ordena��o padr�o
     */
    protected Order getOrdemLista() {
        return null;
    }

    /**
     * Recupera sess�o atual do hibernate.
     * 
     * @return session sess�o atual
     */
    protected Session getSession() {
        return ((Session) hibernateUtil.getNewSession());
        //return this.sessionFactory.getCurrentSession();
    }

    /**
     * @return the qtdRegistro
     */
    public int getQtdRegistro() {
        return qtdRegistro;
    }

    /**
     * @param qtdRegistro the qtdRegistro to set
     */
    public void setQtdRegistro(int qtdRegistro) {
        this.qtdRegistro = qtdRegistro;
    }
}
