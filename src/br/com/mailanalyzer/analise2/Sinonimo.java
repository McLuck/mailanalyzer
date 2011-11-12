package br.com.mailanalyzer.analise2;

import br.com.mailanalyzer.dao.SinonimosDAO;
import br.com.mailanalyzer.domain.Sinonimos;
import br.com.mailanalyzer.log.L;
import java.util.List;

/**
 *
 * @author Lucas Israel
 * @contact mcluck.ti@gmail.com
 * @version 1.0
 * @Date 27-08-2011
 *
 */
public class Sinonimo {
    
    //Normalmente, são preposilções
    public static final String[] IRRELEVANTES={
        "de","e","a","o","u","i","da","do"
    };
    private Sinonimo(){}

    public static final String TAG = "Sinonimo";
    private static boolean carregado = false;
    public static final String TAG_SERVICOS = "Sinonimo(Serviços Internos)";
    public String[][] TABELA_SINONIMOS = null;
    private static Sinonimo instancia = null;
    private static SinonimosDAO sdao;
    public static Sinonimo getInstancia() {
        if (instancia == null) {
            L.i(TAG, Sinonimo.class, "Iniciando singleton. Instanciando objeto.");
            instancia = new Sinonimo();
            sdao = new SinonimosDAO();
        }
        return instancia;
    }

    public synchronized String[] GET_SINONIMOS(String palavra) {
        //Se nao for usar sinonimos, nem carrega
        if (!Raiz.PROCURAR_EM_SINONIMOS) {
            L.a(TAG, Sinonimo.class, "Raiz esta configurada para nao carregar Sinonimos de palavras. Abortando procura por sinonimos.");
            return new String[]{};
        }

        List<Sinonimos> lista = sdao.getByPalavra(palavra);
        if(lista!=null && !lista.isEmpty()){
            Sinonimos sins = lista.get(0);
            return sins.getSinonimoss().split("#");
        }
        
        return new String[]{};
    }
}
