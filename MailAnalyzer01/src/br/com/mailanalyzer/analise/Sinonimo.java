package br.com.mailanalyzer.analise;

import br.com.mailanalyzer.log.L;
import java.io.*;

/**
 *
 * @author Lucas Israel
 * @contact mcluck.ti@gmail.com
 * @version 1.0
 * @Date 27-08-2011
 *
 */
public class Sinonimo {

    public static final String TAG = "Sinonimo";
    private static boolean carregado = false;
    public static final String TAG_SERVICOS = "Sinonimo(Serviços Internos)";
    public String[][] TABELA_SINONIMOS = null;
    private static Sinonimo instancia = null;

    public static Sinonimo getInstancia() {
        if (instancia == null) {
            L.d(TAG_SERVICOS, "Instanciando novo objeto.");
            instancia = new Sinonimo();
            new Thread(new Runnable() {

                public void run() {
                    //Carregamento asincrono.
                    instancia.carregarSinonimos();
                }
            }).start();
        }
        return instancia;
    }

    public synchronized static String[] GET_SINONIMOS(String palavra) {
        //Se nao for usar sinonimos, nem carrega
        if (!Raiz.PROCURAR_EM_SINONIMOS) {
            return new String[]{};
        }

        //Se os sinonimos ainda nao foram preenchidos, envia para o LOG a mensagem e aguarda.
        if (instancia == null) {
            //Inicializa a instancia;
            getInstancia();
        }
        boolean frt = true;
        while (!carregado) {
            if (frt) {
                frt = false;
                L.d(TAG, "Sinonimos sendo carregado. Aguardando array ser populado.");
            }
            try {
                Thread.sleep(100);
            } catch (Exception e) {
            }
        }

        for (int i = 0; i < getInstancia().TABELA_SINONIMOS.length; i++) {
            if (getInstancia().TABELA_SINONIMOS[i] == null) {
                continue;
            }
            for (int k = 0; k < getInstancia().TABELA_SINONIMOS[i].length; k++) {
                if (getInstancia().TABELA_SINONIMOS[i][k] == null) {
                    continue;
                }

                if (getInstancia().TABELA_SINONIMOS[i][k].trim().equals(palavra.trim())) {
                    return getInstancia().TABELA_SINONIMOS[i];
                }
            }
        }
        return new String[]{};
    }

    private synchronized void carregarSinonimos() {
        if (carregado) {
            return;
        }

        L.d(TAG_SERVICOS, "Iniciando carregamento de sinonimos na memoria.");
        File f = new File("/home/mcluck/NetBeansProjects/mlp/TestCube/src/dic_sin_ptbr.txt");

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


            TABELA_SINONIMOS = tratarSinonimos(sb.toString());
            carregado = true;
            L.d(TAG_SERVICOS, "Sinonimos foram carregados corretamente na memoria.");
        } catch (Exception e) {
            e.printStackTrace();
            L.e(TAG_SERVICOS, "Erro ao carregar sinonimos. " + e);
        }
    }

    public String[][] tratarSinonimos(String texto) {
        L.d(TAG_SERVICOS, "Palavras foram lidas, Iniciando tratamento de dados.");
        String[] v = texto.split("###");
        String[][] v2 = new String[v.length][];
        int p = 0;
        for (int i = 0; i < v.length; i++) {
            if (v[i].trim().startsWith("(Sinônimo)") && v[i].contains("|")) {

                v2[p] = UtilsString.NORMALIZAR(v[i].replace("(Sinônimo)", "").replace("|", "*")).split("\\*");
                p++;
            }
        }
        L.d(TAG_SERVICOS, "Dados tratados. Sinonimos estruturado em array de String.");
        return v2;
    }
}
