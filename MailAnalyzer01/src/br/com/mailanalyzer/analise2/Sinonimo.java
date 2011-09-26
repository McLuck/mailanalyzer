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
            L.d(TAG_SERVICOS, "Instanciando novo objeto.");
            instancia = new Sinonimo();
            sdao = new SinonimosDAO();
        }
        return instancia;
    }

    public synchronized String[] GET_SINONIMOS(String palavra) {
        //Se nao for usar sinonimos, nem carrega
        if (!Raiz.PROCURAR_EM_SINONIMOS) {
            return new String[]{};
        }

        List<Sinonimos> lista = sdao.getByPalavra(palavra);
        if(lista!=null && !lista.isEmpty()){
            Sinonimos sins = lista.get(0);
            return sins.getSinonimoss().split("#");
        }
        
        return new String[]{};
    }
    public static void main(String...args){
        String p = "tirano";
        String [] v = Sinonimo.getInstancia().GET_SINONIMOS(p);
        /*for(String s : v){
            System.out.println(s);
        }*/
    }
/*
    

    private static synchronized void carregarSinonimos() {
        if (carregado) {
            return;
        }

        L.d(TAG_SERVICOS, "Iniciando carregamento de sinonimos na memoria.");
        ///home/mcluck/NetBeansProjects/manalyzer/MailAnalyzer01/src
        File f = new File("/home/mcluck/NetBeansProjects/manalyzer/MailAnalyzer01/src/dic_sin_ptbr.txt");

        StringBuffer sb = new StringBuffer();
        try {
            FileInputStream fis = new FileInputStream(f);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis, "ISO-8859-1"));

            String line;
            while ((line = in.readLine()) != null) {
                //System.out.println(line);
                sb.append(line);
                sb.append("###");
            }

            tratarSinonimos(sb.toString());
            //TABELA_SINONIMOS = tratarSinonimos(sb.toString());
            //carregado = true;
            L.d(TAG_SERVICOS, "Sinonimos foram carregados corretamente na memoria.");
        } catch (Exception e) {
            e.printStackTrace();
            L.e(TAG_SERVICOS, "Erro ao carregar sinonimos. " + e);
        }
    }

    public static String[][] tratarSinonimos(String texto) {
        
        L.d(TAG_SERVICOS, "Palavras foram lidas, Iniciando tratamento de dados.");
        String[] v = texto.split("###");
        String[][] v2 = new String[v.length][];
        int p = 0;
        SinonimosDAO sdao = new SinonimosDAO();
        for (int i = 0; i < v.length; i++) {
            boolean vai = false;
            if (v[i].trim().startsWith("(Sinônimo)") && v[i].contains("|")) {
                String a = UtilsString.NORMALIZAR(v[i].replace("(Sinônimo)", ""));
                a = a.trim();
                if(a.startsWith("fustigar")){
                    vai = true;
                    continue;
                }
                if(!vai)continue;
                
                a = a.replace("|", "#");
                a = a.trim();
                System.out.println(a);
                //System.out.println((UtilsString.NORMALIZAR(v[i].replace("(Sinônimo)", "").replace("|", "###"))).trim());
                //v2[p] = UtilsString.NORMALIZAR(v[i].replace("(Sinônimo)", "").replace("|", "*")).split("\\*");
                Sinonimos sin = new Sinonimos();
                sin.setSinonimoss(a);
                sdao.salvar(sin);
                //sdao.commit();
                p++;
                if(p>50){
                    sdao.commit();
                    p = 0;
                }
            }
        }
        sdao.commit();
        sdao.close();
        L.d(TAG_SERVICOS, "Dados tratados. Sinonimos estruturado em array de String.");
        return v2;
    }*/
}
