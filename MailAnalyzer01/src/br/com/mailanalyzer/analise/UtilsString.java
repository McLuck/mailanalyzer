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
        texto = texto.replace("�", "");
        texto = SubstituirCaracter(texto);
        //
        //Adicionar todos os padroes do texto

        return texto;
    }

    private static String SubstituirCaracter(String text) {
        return text.replaceAll("[�����]", "a").replaceAll("[����]", "e").replaceAll("[����]", "i").replaceAll("[�����]", "o").replaceAll("[����]", "u").replaceAll("[�����]", "A").replaceAll("[����]", "E").replaceAll("[����]", "I").replaceAll("[�����]", "O").replaceAll("[����]", "U").replace('�', 'c').replace('�', 'C').replace('�', 'n').replace('�', 'N').replace("\\", "").replaceAll("[�`#$%�*&]", "").replaceAll("[()={}\\[\\]~^]", "").replaceAll("[-_+'��/�]", "");
    }
}
