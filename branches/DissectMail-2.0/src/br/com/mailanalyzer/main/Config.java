package br.com.mailanalyzer.main;

import br.com.mailanalyzer.analise2.GerenciamentoAnalisador;
import br.com.mailanalyzer.analise2.Raiz;
import br.com.mailanalyzer.analise2.adapter.RaizAdapter;
import br.com.mailanalyzer.dao.HB;
import br.com.mailanalyzer.dao.RaizDAO;
import br.com.mailanalyzer.dao.TermVariationDAO;
import br.com.mailanalyzer.dao.actions.ActionActiveReceiver;
import br.com.mailanalyzer.dao.actions.ActionTermVariation;
import br.com.mailanalyzer.domain.ActiveReceiver;
import br.com.mailanalyzer.domain.ComposicaoDomain;
import br.com.mailanalyzer.domain.RaizDomain;
import br.com.mailanalyzer.domain.TermVariation;
import br.com.mailanalyzer.log.L;
import java.util.List;

/**
 *
 * @author Lucas Israel
 * @contact mcluck.ti@gmail.com
 * @version 1.0
 * @Date 25-04-2011
 *
 */
public class Config {

    public static final String TAG_CONFIG = "Configurações";
    /**
     * Informa a API se deve trabalhar usando um proxy.<br>
     * O padrao e <b>false</b>. Caso seja setado para usar PROXY, os dados do proxy deverao ser informados.
     */
    public static boolean IS_PROXY = false;
    /**
     * Informa o endereco do proxy a ser usado pela API.<br>
     * Esta informacao so sera utilizada caso o <b>IS_PROXY</b> esteja setado como <b>true</b><br>
     * 
     */
    public static String PROXY_ADDRESS = "";
    /**
     * Informa a porta do proxy a ser usada pela API.<br>
     * Esta informacao so sera utilizada caso o <b>IS-PROXY</b> esteja setado como <b>true</b><br>
     * <b>DEFAULT: <i>8080</i></b>
     */
    public static int PROXY_PORT = 8080;
    /**
     * Informa o schema do proxy a ser usado pela API<br>
     * Esta informacao so sera utilizada caso o <b>IS-PROXY</b> esteja setado como <b>true</b><br>
     * <b>DEFAULT: <i>http</i></b>
     */
    public static String PROXY_SCHEME = "http";
    /**
     * Informa se apos uma mensagem ser recebida o tratamento da mensagem deve ser executado em Thread ou nao.<br>
     * <b>DEFAULT: <i>true</i></b>
     */
    public static boolean THREAD_FOR_MESSAGES_HANDLING = false;
    /**
     * TAG usada para a API ignorar o tratamento da mensagem.<br>
     * Mensagem que chegarem contendo esta tag no assunto serao ignoradas.<br>
     * <b>DEFAULT: <i>[NOBOT]</i></b>
     * 
     */
    public static String TAG_TO_IGNORE_MESSAGE = "[NOBOT]";
    /**
     * Este booleano seta a API para ambiente de teste, onde varias outras acoes sao tomadas.<br>
     * Se estiver setado para ambiente de teste, muito emails serao disparados e uma grande quantidade de LOG sera armazenada.<br>
     * 
     * <b>DEFAULT: <i>true</i></b>
     */
    public static boolean IS_TEST_ENVIRONMENT = true;
    /**
     * Esta String contem o endereco do servidor SMTP que sera enviado todos os emails da API.<br>
     * Por padrao, o servidor SMTP e' configurado para os servidores do google<br>
     * <b>DEFAULT: <i>smtp.gmail.com</i></b>
     */
    public static String SERVER_SMTP_ADDRESS = "smtp.gmail.com";
    /**
     * A porta do servidor SMTP que sera usado para disparo de emails pela API<br>
     * Por padrao, o servidor SMTP e' configurado para os servidores do google<br>
     * <b>DEFAULT: <i>465</i></b>
     */
    public static int PORT_SERVER_SMTP = 465;

    public static class LOG {

        /**
         * Nivel de detalhe do log
         */
        public static int NIVEL = 3;
        /**
         * Imprime lancamentos no console.<br/>
         * Padrao: <b>true</b>
         */
        public static boolean PRINT_IN_CONSOLE = true;
        /**
         * Caso imprima no console, a tag usada para identificar o lancamento do log.<br/>
         * Padrao: <b>[DISSECT MAIL]</b>
         */
        public static String TAG_CONSOLE = "[DISSECT MAIL]";
        public static final int NIVEL_BAIXO = 0;
        public static final int NIVEL_MEDIO = 1;
        public static final int NIVEL_MAXIMO = 3;
    }

    /**
     * Classe de Config para configurar de forma rapida as propriedades do hibernate.
     * @author Lucas Israel
     * @contact mcluck.ti@gmail.com
     * @version 1.0
     * @Date 13-07-2011
     *
     */
    public static class Hibernate {

        /**
         * Classe contendo valores de configuracoes padroes para propriedades do Hibernate
         * @author Lucas Israel
         * @contact mcluck.ti@gmail.com
         * @version 1.0
         * @Date 13-07-2011
         *
         */
        public static class values {

            /**
             * Classe contendo os principais e mais utilizados Drivers de conexao
             * @author Lucas Israel
             * @contact mcluck.ti@gmail.com
             * @version 1.0
             * @Date 13-07-2011
             *
             */
            public static class DriverClass {

                /**
                 * Driver: com.mysql.jdbc.Driver
                 */
                public static String MY_SQL = "com.mysql.jdbc.Driver";
                /**
                 * Driver: com.ibm.db2.jdbc.app.DB2Driver
                 */
                public static String DB2 = "com.ibm.db2.jdbc.app.DB2Driver";
                /**
                 * Driver: com.informix.jdbc.IfxDriver
                 */
                public static String INFORMIX = "com.informix.jdbc.IfxDriver";
                /**
                 * Driver: com.mckoi.JDBCDriver
                 */
                public static String MCKOI = "com.mckoi.JDBCDriver";
                /**
                 * Driver: com.pointbase.jdbc.jdbcUniversalDriver
                 */
                public static String POINTBASE = "com.pointbase.jdbc.jdbcUniversalDriver";
                /**
                 * Driver: com.sun.sql.jdbc.sqlserver.SQLServerDriver
                 */
                public static String SQL_SERVER = "com.sun.sql.jdbc.sqlserver.SQLServerDriver";
                /**
                 * Driver: com.sun.sql.jdbc.sybase.SybaseDriver
                 */
                public static String SYSBASE = "com.sun.sql.jdbc.sybase.SybaseDriver";
                /**
                 * Driver: interbase.interclient.Driver
                 */
                public static String INTERBASE = "interbase.interclient.Driver";
                /**
                 * Driver: oracle.jdbc.OracleDriver
                 */
                public static String ORACLE = "oracle.jdbc.OracleDriver";
                /**
                 * Driver: oracle.jdbc.driver.OracleDriver
                 */
                public static String ORACLE_JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
                /**
                 * Driver: org.apache.derby.jdbc.ClientDriver
                 */
                public static String APACHE_DERBY = "org.apache.derby.jdbc.ClientDriver";
                /**
                 * Driver: org.firebirdsql.jdbc.FBDriver
                 */
                public static String FIREBIRD = "org.firebirdsql.jdbc.FBDriver";
                /**
                 * Driver: org.hsqldb.jdbcDriver
                 */
                public static String HSQLDB = "org.hsqldb.jdbcDriver";
                /**
                 * Driver: org.postgresql.Driver
                 */
                public static String POSTGREE = "org.postgresql.Driver";
            }

            /**
             * Classe contendo as configuracoes da propriedade <b>hibernate.connection.release_mode</b>
             * @author Lucas Israel
             * @contact mcluck.ti@gmail.com
             * @version 1.0
             * @Date 13-07-2011
             *
             */
            public static class ReleaseMode {

                /**
                 * Configuracao para propriedade hibernate "release node"<br/>
                 * VALUE: <b>after_statement</b>
                 */
                public static String release_mode_after_statement = "after_statement";
                /**
                 * Configuracao para propriedade hibernate "release node"<br/>
                 * VALUE: <b>after_transaction</b>
                 */
                public static String release_mode_after_transaction = "after_transaction";
                /**
                 * Configuracao para propriedade hibernate "release node"<br/>
                 * VALUE: <b>auto</b>
                 */
                public static String release_mode_auto = "auto";
                /**
                 * Configuracao para propriedade hibernate "release node"<br/>
                 * VALUE: <b>on_close</b>
                 */
                public static String release_mode_on_close = "on_close";
            }

            /**
             * Classe contendo as configuracoes da propriedade <b>hibernate.current_session_context_class</b>
             * @author Lucas Israel
             * @contact mcluck.ti@gmail.com
             * @version 1.0
             * @Date 13-07-2011
             *
             */
            public static class SessionContextClass {

                /**
                 * Configuracao para propriedade hibernate "current_session_context_class"<br/>
                 * VALUE: <b>thread</b>
                 */
                public static String current_session_context_class_thread = "thread";
                /**
                 * Configuracao para propriedade hibernate "current_session_context_class"<br/>
                 * VALUE: <b>jta</b>
                 */
                public static String current_session_context_class_jta = "jta";
                /**
                 * Configuracao para propriedade hibernate "current_session_context_class"<br/>
                 * VALUE: <b>managed</b>
                 */
                public static String current_session_context_class_managed = "managed";
            }

            /**
             * Classe contendo as configuracoes da propriedade <b>hibernate.hbm2ddl.auto</b>
             * @author Lucas Israel
             * @contact mcluck.ti@gmail.com
             * @version 1.0
             * @Date 13-07-2011
             *
             */
            public static class Hbm2dllAuto {

                /**
                 * Configuracao para propriedade hibernate "hibernate.hbm2ddl.auto"<br/>
                 * VALUE: <b>update</b>
                 */
                public static String hbm2ddl_auto_update = "update";
                /**
                 * Configuracao para propriedade hibernate "hibernate.hbm2ddl.auto"<br/>
                 * VALUE: <b>create</b>
                 */
                public static String hbm2ddl_auto_create = "create";
                /**
                 * Configuracao para propriedade hibernate "hibernate.hbm2ddl.auto"<br/>
                 * VALUE: <b>create-drop</b>
                 */
                public static String hbm2ddl_auto_create_drop = "create-drop";
                /**
                 * Configuracao para propriedade hibernate "hibernate.hbm2ddl.auto"<br/>
                 * VALUE: <b>validate</b>
                 */
                public static String hbm2ddl_auto_create_validate = "validate";
            }
        }

        /**
         * Seta URL diferenciada para o BD
         * @param url
         */
        public static void SET_URL_BD(String url) {
            HB.changeDefault = true;
            HB.CONNECTION_URL = url;
        }

        /**
         * Seta Username diferenciado para o BD
         * @param user
         */
        public static void SET_USERNAME_BD(String user) {
            HB.changeDefault = true;
            HB.USERNAME = user;
        }

        /**
         * Seta senha diferenciada para o BD
         * @param pwd
         */
        public static void SET_PASSWORD_BD(String pwd) {
            HB.changeDefault = true;
            HB.PASSWORD = pwd;
        }

        /**
         * Seta DIALECT diferenciada para o Hibernate<br/>
         * Verifique configuracao adicional na classe <b>Config.Hibernate</b>
         * @param dialect
         */
        public static void SET_DIALECT(String dialect) {
            HB.changeDefault = true;
            HB.DIALECT = dialect;
        }

        /**
         * Seta DRIVER_CLASS diferenciada para o Hibernate<br/>
         * Verifique configuracao adicional na classe <b>Config.Hibernate</b>
         * @param driver
         */
        public static void SET_DRIVER_CLASS(String driver) {
            HB.changeDefault = true;
            HB.DRIVER_CLASS = driver;
        }

        /**
         * Seta HBM2DLL_AUTO diferenciada para o Hibernate<br/>
         * Verifique configuracao adicional na classe <b>Config.Hibernate</b>
         * @param config
         */
        public static void SET_HBM2DLL_AUTO(String config) {
            HB.changeDefault = true;
            HB.HBM2DLL_AUTO = config;
        }

        /**
         * Seta PROVIDER_CLASS diferenciada para o Hibernate
         * @param provider
         */
        public static void SET_PROVIDER_CLASS(String provider) {
            HB.changeDefault = true;
            HB.PROVIDER_CLASS = provider;
        }

        /**
         * Seta POOL_MAX_SIZE diferenciada para o C3P0
         * @param maxSize
         */
        public static void SET_POOL_MAX_SIZE(int maxSize) {
            HB.changeDefault = true;
            HB.POOL_MAX_SIZE = maxSize;
        }

        /**
         * Seta POOL_MIN_SIZE diferenciada para o C3P0
         * @param minSize
         */
        public static void SET_POOL_MIN_SIZE(int minSize) {
            HB.changeDefault = true;
            HB.POOL_MIN_SIZE = minSize;
        }

        /**
         * Seta POOL_TIMEOUT diferenciada para o C3P0
         * @param timeout
         */
        public static void SET_POOL_TIMEOUT(long timeout) {
            HB.changeDefault = true;
            HB.POOL_TIMEOUT = timeout;
        }

        /**
         * Seta POOL_MAX_STATEMENT diferenciada para o C3P0
         * @param max
         */
        public static void SET_POOL_MAX_STATEMENT(int max) {
            HB.changeDefault = true;
            HB.POOL_MAX_STATEMENT = max;
        }

        /**
         * Seta POOL_TEST_PERIOD diferenciada para o C3P0
         * @param test
         */
        public static void SET_POOL_TEST_PERIOD(long test) {
            HB.changeDefault = true;
            HB.POOL_TEST_PERIOD = test;
        }

        /**
         * Seta POOL_ACQUIRE_INCREMENT diferenciada para o C3P0
         * @param acquire
         */
        public static void SET_POOL_ACQUIRE_INCREMENT(int acquire) {
            HB.changeDefault = true;
            HB.POOL_ACQUIRE_INCREMENT = acquire;
        }

        /**
         * Seta AUTO_CLOSE_SESSION diferenciada para o Hibernate
         * @param autoClose
         */
        public static void SET_AUTO_CLOSE_SESSION(boolean autoClose) {
            HB.changeDefault = true;
            HB.AUTO_CLOSE_SESSION = autoClose;
        }

        /**
         * Seta POOL_SIZE diferenciada para o C3P0
         * @param size
         */
        public static void SET_POOL_SIZE(int size) {
            HB.changeDefault = true;
            HB.POOL_SIZE = size;
        }
        /**
         * Set MEMCACHED_CACHE_PROVIDER_CLASS para o Hibernate. <br>
         * Default: Memcached
         * @param providerClass
         */
        public static void SET_CACHE_PROVIDER_CLASS(String providerClass){
            HB.changeDefault = true;
            HB.MEMCACHED_CACHE_PROVIDER_CLASS = providerClass;
        }

        /**
         * Set MEMCACHED_USE_QUERY_CACHE para Memcached <br>
         * Default: true
         * @param useQ
         */
        public static void SET_MEMCACHED_USE_QUERY_CACHE(boolean useQ){
            HB.MEMCACHED_USE_QUERY_CACHE = useQ;
            HB.changeDefault = true;
        }
        /**
         * Set MEMCACHED_SERVERS. Configura os servidores Memcached. <br>
         * Default: localhost:11211 <br>
         * Para separar os servidores Memcached, use o formato "host:port host2:port" usando espacos
         * para separar os enderecos de hosts
         * @param servers
         */
        public static void SET_MEMCACHED_SERVERS(String servers){
            HB.MEMCACHED_SERVERS = servers;
            HB.changeDefault = true;
        }
        /**
         * Set MEMCACHED_TIMESECONDS para Memcached<br>
         * default: 300
         * @param timeSeconds
         */
        public static void SET_MEMCACHED_TIMESECONDS(int timeSeconds){
            HB.MEMCACHED_TIMESECONDS = timeSeconds;
            HB.changeDefault = true;
        }
        /**
         * SET MEMCACHED_KEY_STRATEGY para o Memcached<br>
         * Default: com.googlecode.hibernate.memcached.HashCodeKeyStrategy
         * @param keyStrat
         */
        public static void SET_MEMCACHED_KEY_STRATEGY(String keyStrat){
            HB.MEMCACHED_KEY_STRATEGY = keyStrat;
            HB.changeDefault = true;
        }
        /**
         * Set MEMCACHED_READ_BUFFER_SIZE para o Memcached<br>
         * Default: DefaultConnectionFactory.DEFAULT_READ_BUFFER_SIZE = 16384
         * @param readBufferSize
         */
        public static void SET_MEMCACHED_READ_BUFFER_SIZE(int readBufferSize){
            HB.MEMCACHED_READ_BUFFER_SIZE = readBufferSize;
            HB.changeDefault = true;
        }
        /**
         * Set MEMCACHED_OPERATION_QUEUE_LENGTH para Memcached
         * Default: 16384
         * @param operation
         */
        public static void SET_MEMCACHED_OPERATION_QUEUE_LENGTH(int operation){
            HB.MEMCACHED_OPERATION_QUEUE_LENGTH = operation;
            HB.changeDefault = true;
        }
        /**
         * Set MEMCACHED_TIMEOUT para o Memcached<br>
         * Default: DefaultConnectionFactory.DEFAULT_OPERATION_TIMEOUT = 1000
         * @param timeout
         */
        public static void SET_MEMCACHED_TIMEOUT(int timeout){
            HB.MEMCACHED_TIMEOUT = timeout;
            HB.changeDefault = true;
        }
        /**
         * Set MEMCACHED_HASH_ALGORITHM para Memcached<br>
         * Default: HashAlgorithm.NATIVE_HASH
         * @param algortimo
         */
        public static void SET_MEMCACHED_HASH_ALGORITHM(String algortimo){
            HB.MEMCACHED_HASH_ALGORITHM = algortimo;
            HB.changeDefault = true;
        }
        /**
         * Set MEMCACHED_CONNECTION_FACTORY para o Memcached<br>
         * Default: DefaultConnectionFactory
         * @param connectionFactory
         */
        public static void SET_MEMCACHED_CONNECTION_FACTORY(String connectionFactory){
            HB.MEMCACHED_CONNECTION_FACTORY = connectionFactory;
            HB.changeDefault = true;
        }

        /**
         * Set MEMCACHED_CLEAR_SUPPORTED para o Memcached<br>
         * <b>NAO USAR EM AMBIENTE DE PRODUCAO. HABILITAR APENAS PARA TESTES.</b><br>
         * Default: false
         * @param clearSupport
         */
        public static void SET_MEMCACHED_CLEAR_SUPPORTED(boolean clearSupport){
            HB.MEMCACHED_CLEAR_SUPPORTED = clearSupport;
            HB.changeDefault = true;
        }
        /**
         * Habilita ou desabilita o uso do memcached.<br>
         * Caso esta opcao seja alterada, deve ser "setado" tambem as configuracoes do usuario e senha (pois irao para o padrao).
         * @param usarMemcached
         */
        public static void SET_USE_MEMCACHED(boolean usarMemcached){
            HB.setUseMemcached(usarMemcached);
        }
    }

    /**
     * Se  nivel do log esta baixo
     * @return
     */
    public static boolean isNivelLogBaixo() {
        return Config.LOG.NIVEL == Config.LOG.NIVEL_BAIXO;
    }

    /**
     * Se  nivel do log esta medio
     * @return
     */
    public static boolean isNivelLogMedio() {
        return Config.LOG.NIVEL == Config.LOG.NIVEL_MEDIO;
    }

    /**
     * Se  nivel do log esta maximo
     * @return
     */
    public static boolean isNivelLogMaximo() {
        return Config.LOG.NIVEL == Config.LOG.NIVEL_MAXIMO;
    }

    /**
     * Classe de Config para registrar de forma rapida novos itens na API.
     * @author Lucas Israel
     * @contact mcluck.ti@gmail.com
     * @version 1.0
     * @Date 13-07-2011
     *
     */
    public static class Register {

        public static final String TAG_REGISTER = "-Registros";

        /**
         * Registra uma nova conta de recebimento ativo
         * @param receiv Um objeto de ActiveReceiver com os dados
         */
        public static void ADD_RECEIVER(ActiveReceiver receiv) {

            if (isNivelLogMaximo()) {
                L.i(TAG_CONFIG.concat(TAG_REGISTER), Register.class, "Adicionando novo registro de recebedor de mensagem...");
            }


            ActionActiveReceiver action = new ActionActiveReceiver();
            List<ActiveReceiver> lista = action.showAll();

            boolean encontrado = false;
            for (ActiveReceiver ar : lista) {
                if (ar.equals(receiv)) {
                    encontrado = true;
                }
            }
            if (!encontrado) {
                action.setReceive(receiv);
                action.salvar();
            }

            L.i(TAG_CONFIG.concat(TAG_REGISTER), Register.class, "Registro do receive " + receiv.getNome() + " foi adicionado.");
        }

        /**
         * Registra uma ou mais girias no banco de dados
         * @param termosIncorretos Giria a ser adicionada. Separar por ponto e virgula em casos com mais de uma. "<b>;</b>"
         */
        public static void ADD_SUBSTITUICAO_TERMO(String termoCorreto, String termosIncorretos) {

            TermVariationDAO tdao = new TermVariationDAO();
            List<TermVariation> lista = tdao.getByReplacer(termoCorreto);
            TermVariation t;
            if (lista == null || lista.isEmpty()) {
                t = new TermVariation();
                t.setReplacer(termoCorreto);
                t.setVariations(termosIncorretos);
            } else {
                t = lista.get(0);
            }
            t.addVariation(termosIncorretos);
            tdao.salvar(t);
            tdao.commit();
            tdao.close();
        }

        /**
         * Para add nova composicao eliminatoria
         * @param composicao
         * @param sequencial
         * @param raizID
         */
        public static void ADD_COMPOSICAO_ELIMINATORIA(String composicao, boolean sequencial, int raizID) {
            Raiz raiz = null;
            for (Raiz r : GerenciamentoAnalisador.getMatrizes()) {
                if (r.getId() == raizID) {
                    raiz = r;
                    break;
                }
            }
            if (raiz != null) {
                raiz.aprenderNovaComposicaoEliminatoria(composicao, sequencial);
                RaizAdapter rAdapt = new RaizAdapter(raiz);
                RaizDomain rd = rAdapt.getDominio();
                RaizDAO rdao = new RaizDAO();
                rdao.atualizar(rd);
                rdao.commit();
                rdao.close();

                //Recarrega na memoria
                GerenciamentoAnalisador.load();
            }
        }

        /**
         * Para add nova composicao Mandatoria
         * @param composicao
         * @param sequencial
         * @param raizID
         */
        public static void ADD_COMPOSICAO_MANDATORIA(String composicao, boolean sequencial, int raizID) {
            Raiz raiz = null;
            for (Raiz r : GerenciamentoAnalisador.getMatrizes()) {
                if (r.getId() == raizID) {
                    raiz = r;
                    break;
                }
            }
            if (raiz != null) {
                raiz.aprenderContextoMandatorio(composicao, sequencial);
                RaizAdapter rAdapt = new RaizAdapter(raiz);
                RaizDomain rd = rAdapt.getDominio();
                RaizDAO rdao = new RaizDAO();
                rdao.atualizar(rd);
                rdao.commit();
                rdao.close();

                //Recarrega na memoria
                GerenciamentoAnalisador.load();
            }
        }

        /**
         * Para add nova agregacao de palavras para uma raiz
         * @param composicao
         * @param sequencial
         * @param raizID
         */
        public static void ADD_COMPOSICAO_AGREGACAO(String composicao, boolean sequencial, int peso, int raizID) {
            Raiz raiz = null;
            for (Raiz r : GerenciamentoAnalisador.getMatrizes()) {
                if (r.getId() == raizID) {
                    raiz = r;
                    break;
                }
            }
            if (raiz != null) {
                raiz.aprenderNovaAgregacao(composicao, peso, sequencial);
                RaizAdapter rAdapt = new RaizAdapter(raiz);
                RaizDomain rd = rAdapt.getDominio();
                RaizDAO rdao = new RaizDAO();
                rdao.atualizar(rd);
                rdao.commit();
                rdao.close();

                //Recarrega na memoria
                GerenciamentoAnalisador.load();
            }
        }
    }
}
