package br.com.mailanalyzer.analise;

/**
 *
 * @author Lucas Israel
 * @contact mcluck.ti@gmail.com
 * @version 1.0
 * @Date 27-08-2011
 *
 */
public class UtilsString {

    public static String NORMALIZAR(String texto) {
        texto = texto.toLowerCase();
        texto = texto.trim();
        texto = texto.replace("?", "");
        texto = texto.replace("!", "");
        texto = texto.replace(".", "");
        texto = texto.replace("&", "");
        texto = texto.replace("{", "");
        texto = texto.replace("}", "");
        texto = texto.replace("\\", "");
        texto = texto.replace("/", "");
        texto = texto.replace("©", "");
        texto = SubstituirCaracter(texto);
        //
        //Adicionar todos os padroes do texto

        return texto;
    }

    private static String SubstituirCaracter(String text) {
        return text.replaceAll("[ãâàáä]", "a").replaceAll("[êèéë]", "e").replaceAll("[îìíï]", "i").replaceAll("[õôòóö]", "o").replaceAll("[ûúùü]", "u").replaceAll("[ÃÂÀÁÄ]", "A").replaceAll("[ÊÈÉË]", "E").replaceAll("[ÎÌÍÏ]", "I").replaceAll("[ÕÔÒÓÖ]", "O").replaceAll("[ÛÙÚÜ]", "U").replace('ç', 'c').replace('Ç', 'C').replace('ñ', 'n').replace('Ñ', 'N').replace("\\", "").replaceAll("[´`#$%¨*&]", "").replaceAll("[()={}\\[\\]~^]", "").replaceAll("[-_+'ªº/¬]", "");
    }
}
