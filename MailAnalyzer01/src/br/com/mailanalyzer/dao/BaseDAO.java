package br.com.mailanalyzer.dao;

import br.com.mailanalyzer.dao.interfaces.BaseInterfaceDAO;
import br.com.mailanalyzer.domain.DomainObject;
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
public abstract class BaseDAO <T extends DomainObject> implements BaseInterfaceDAO<T> {

    private SessionFactory sessionFactory;
    private Class<T> domainObject;
    private HB hibernateUtil;
    protected boolean isLOGGING = false;
    
    /**
	 * Cria uma nova inst�ncia do tipo {@link BaseDAO}.
	 * 
	 * @param domainObject classe utilizada
	 */
	protected BaseDAO(Class<T> domainObject) {
		this.domainObject = domainObject;
	}

	/**
	 * {@inheritDoc}
	 */
	public void atualizar(T obj) {
		this.getSession().update(obj);
		this.getSession().flush();
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
		return GenericsUtil.checkedList(criteria.list(), this.domainObject);
	}

	/**
	 * {@inheritDoc}
	 */
	public Integer salvar(T obj) {
		Integer id = (Integer) this.getSession().save(obj);
		this.getSession().flush();
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
		return this.sessionFactory.getCurrentSession();
	}
    
}
