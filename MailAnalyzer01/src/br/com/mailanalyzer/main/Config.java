package br.com.mailanalyzer.main;

/**
 *
 * @author Lucas Israel
 * @contact mcluck.ti@gmail.com
 * @version 1.0
 * @Date 25-04-2011
 *
 */
public class Config {
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
     * Este booleano seta a API para exibir o andamento dos processos no console.<br>
     * <b>DEFAULT: <i>false</i></b>
     */
    public static boolean DEBUG_MODE = false;
    
    
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
}
