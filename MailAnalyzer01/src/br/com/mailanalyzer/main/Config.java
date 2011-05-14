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
}
