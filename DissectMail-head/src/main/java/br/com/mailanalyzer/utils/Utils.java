package br.com.mailanalyzer.utils;

import br.com.mailanalyzer.domain.Message;
import br.com.mailanalyzer.main.Config;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.lang.StringUtils;




/**
 *
 * @author Lucas Israel
 * @contact mcluck.ti@gmail.com
 * @version 1.0
 * @Date 29-04-2011
 *
 */
public class Utils {

    public static void printMessage(Message m) {
        if (Config.LOG.PRINT_IN_CONSOLE) {
            System.out.println();
            System.out.println("--------- PRINT-MESSAGE");
            System.out.println();

            System.out.println("ID: " + m.getId());
            System.out.println("Assunto: " + m.getAssunto());
            System.out.println("Mensagem: " + m.getMensagem());


            System.out.println();
            System.out.println("________________________");
            System.out.println();
        }
    }
    private static Utils instancia = new Utils();

    /**
     * Pattern singleton.
     */
    protected Utils() {
        super();
    }

    /**
     * Recupera a única instância de {@link Utils}.
     * 
     * @return {@link Utils}
     */
    public static Utils getInstance() {
        return instancia;
    }

    /**
     * Completa com zeros a esquerda o número informado
     * 
     * @param valor valor a ser completado
     * @param numeroCasas número de casas decimais no total
     * @return número formatado com zeros
     */
    public String completaComZero(String valor, int numeroCasas) {
        return StringUtils.leftPad(valor, numeroCasas, '0');
    }

    /**
     * Copia uma stream para outra.
     * 
     * @param destino stream de destino
     * @param origem stream de origem
     * @throws IOException caso algum erro de i/o aconteça
     */
    public void copia(OutputStream destino, InputStream origem) throws IOException {
        byte[] buffer = new byte[8192];
        int qtdeLida = 0;

        while ((qtdeLida = origem.read(buffer)) > 0) {
            destino.write(buffer, 0, qtdeLida);
        }
        destino.close();
        origem.close();
    }
}
