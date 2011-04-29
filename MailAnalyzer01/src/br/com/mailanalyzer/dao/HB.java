package br.com.mailanalyzer.dao;

import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.SessionFactory;
import org.hibernate.Session;

/**
 * Hibernate Utility class with a convenient method to get Session Factory object.
 * @author Lucas Israel
 * @contact mcluck.ti@gmail.com
 * @version 1.0
 * @Date 25-04-2011
 *
 */
public class HB {

    private static HB instancia = null;
    private SessionFactory sessionFactory;
    private final ThreadLocal<Session> sessionThread = new ThreadLocal<Session>();
    
    /**
     * Cria uma nova inst�ncia do tipo {@link HibernateUtil}.
     */
    private HB() {
        //singleton
        try {
            // Create the SessionFactory from standard (hibernate.cfg.xml) 
            // config file.
            sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            // Log the exception. 
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    /**
     * Recupera a �nica inst�ncia de {@link HibernateUtil}.
     * 
     * @return {@link HibernateUtil}
     */
    public static HB getInstancia() {
        synchronized (HB.class) {
            if (instancia == null) {
                instancia = new HB();
            }
        }
        return instancia;
    }

    /**
     * Fecha a nova sess�o criada do hibernate.
     */
    public void closeSession() {
        Session session = this.sessionThread.get();
        if ((session != null) && session.isOpen()) {
            this.sessionThread.set(null);
            session.close();
        }
    }

    /**
     * Recupera uma nova sess�o criada do hibernate. <br />
     * obs: esta {@link Session}
     * 
     * @return sess�o criada
     */
    public Session getNewSession() {
        //recupera o objeto session do threadLocal
        Session session = this.sessionThread.get();
        //abre uma nova sess�o para a thread atual somente se a mesma estiver nula ou fechada
        if ((session == null) || !session.isOpen()) {
            session = getNewSessionFromFactory();
            this.sessionThread.set(session);
        }
        return session;
    }

    /**
     * Atribui a f�brica de sess�es do hibernate.
     * 
     * @param sessionFactory f�brica de sess�es do hibernate
     */
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    

    
    private Session getNewSessionFromFactory() {
        return sessionFactory.openSession();
    }
}
