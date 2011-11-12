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
     * Alterar para <b>true</b> para setar as configuracoes de seu banco de dados
     */
    public static boolean changeDefault = false;
    public static String CONNECTION_URL = "jdbc:mysql://localhost/mcluckbr8", USERNAME = "root", PASSWORD="123456";
    /**
     * Default: org.hibernate.dialect.MySQLDialect
     */
    public static String DIALECT = "org.hibernate.dialect.MySQLDialect";
    /**
     * Default: com.mysql.jdbc.Driver
     */
    public static String DRIVER_CLASS = "com.mysql.jdbc.Driver";
    /**
     * Default: org.hibernate.connection.C3P0ConnectionProvider
     */
    public static String PROVIDER_CLASS = "org.hibernate.connection.C3P0ConnectionProvider";
    /**
     * Default: hibernate.c3p0.max_size = 10
     */
    public static int POOL_MAX_SIZE = 10;
    /**
     * Default: hibernate.c3p0.min_size = 5
     */
    public static int POOL_MIN_SIZE = 5;
    /**
     * Default: hibernate.c3p0.timeout = 300
     */
    public static long POOL_TIMEOUT = 300;
    /**
     * Default: hibernate.c3p0.max_statements = 50
     */
    public static int POOL_MAX_STATEMENT = 50;
    /**
     * Default: hibernate.c3p0.idle_test_period = 3000
     */
    public static long POOL_TEST_PERIOD = 3000;
    /**
     * Default: hibernate.c3p0.acquire_increment = 3
     */
    public static int POOL_ACQUIRE_INCREMENT = 3;
    /**
     * Default: hibernate.transaction.auto_close_session = false
     */
    public static boolean AUTO_CLOSE_SESSION = false;
    /**
     * Default: hibernate.connection.pool_size = 5
     */
    public static int POOL_SIZE = 5;

    /**
     * Default: hibernate.connection.release_mode = after_transaction<br/>
     * Verifique configuracao adicional na classe <b>Config.Hibernate</b>
     */
    public static String RELEASE_MODE = "after_transaction";


    /**
     * Default: hibernate.current_session_context_class = thread<br/>
     * Verifique configuracao adicional na classe <b>Config.Hibernate</b>
     */
    public static String SESSION_CONTEXT_CLASS = "thread";

    /**
     * Default: hibernate.hbm2ddl.auto = update<br/>
     * Verifique configuracao adicional na classe <b>Config.Hibernate</b>
     */
    public static String HBM2DLL_AUTO = "update";


    /**
     * Cria uma nova instância do tipo {@link HibernateUtil}.
     */
    private HB() {
        //singleton
        try {
            // Create the SessionFactory from standard (hibernate.cfg.xml) 
            // config file.
            if (changeDefault) {
                AnnotationConfiguration conf = new AnnotationConfiguration().configure().setProperty("hibernate.connection.url", CONNECTION_URL).setProperty("hibernate.connection.username", USERNAME).setProperty("hibernate.connection.password", PASSWORD).setProperty("hibernate.dialect", DIALECT).setProperty("hibernate.connection.driver_class", DRIVER_CLASS).setProperty("hibernate.connection.provider_class", PROVIDER_CLASS).setProperty("hibernate.c3p0.max_size", String.valueOf(POOL_MAX_SIZE)).setProperty("hibernate.c3p0.timeout", String.valueOf(POOL_TIMEOUT))
                        .setProperty("hibernate.c3p0.max_statements", String.valueOf(POOL_MAX_STATEMENT))
                        .setProperty("hibernate.c3p0.idle_test_period", String.valueOf(POOL_TEST_PERIOD))
                        .setProperty("hibernate.c3p0.acquire_increment", String.valueOf(POOL_ACQUIRE_INCREMENT))
                        .setProperty("hibernate.transaction.auto_close_session", String.valueOf(AUTO_CLOSE_SESSION))
                        .setProperty("hibernate.connection.pool_size", String.valueOf(POOL_SIZE))
                        .setProperty("hibernate.connection.release_mode", RELEASE_MODE)
                        .setProperty("hibernate.current_session_context_class", SESSION_CONTEXT_CLASS)
                        .setProperty("hibernate.hbm2ddl.auto", HBM2DLL_AUTO);

                sessionFactory = conf.buildSessionFactory();

            } else {
                sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
            }

        } catch (Throwable ex) {
            // Log the exception. 
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    /**
     * Recupera a única instância de {@link HibernateUtil}.
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
     * Fecha a nova sessão criada do hibernate.
     */
    public void closeSession() {
        Session session = this.sessionThread.get();
        if ((session != null) && session.isOpen()) {
            this.sessionThread.set(null);
            session.close();
        }
    }

    /**
     * Recupera uma nova sessão criada do hibernate. <br />
     * obs: esta {@link Session}
     * 
     * @return sessão criada
     */
    public Session getNewSession() {
        //recupera o objeto session do threadLocal
        Session session = this.sessionThread.get();
        //abre uma nova sessão para a thread atual somente se a mesma estiver nula ou fechada
        if ((session == null) || !session.isOpen()) {
            session = getNewSessionFromFactory();
            this.sessionThread.set(session);
        }
        return session;
    }

    /**
     * Atribui a fábrica de sessões do hibernate.
     * 
     * @param sessionFactory fábrica de sessões do hibernate
     */
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private Session getNewSessionFromFactory() {
        return sessionFactory.openSession();
    }
}
