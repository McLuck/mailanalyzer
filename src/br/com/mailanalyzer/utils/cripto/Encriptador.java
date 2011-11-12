package br.com.mailanalyzer.utils.cripto;

/**
 *
 * @author Lucas Israel
 * @contact mcluck.ti@gmail.com
 * @version 1.0
 * @Date 29-04-2011
 *
 */
public class Encriptador {

    private String chave;
    static final char[] charTab =
            "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".toCharArray();

    /**
     * Gera uma nova instancia do Encriptador valida somente para a chave informada
     * @param chave Uma chave a ser usada para encriptar ou decriptar os dados desta instancia
     */
    public Encriptador(String chave) {
        this.chave = chave;
    }

    /**
     * Encripta uma String com a chave da Instancia atual
     * @param str Uma string nao nula a ser criptografada
     * @return Uma String criptografada com a chave da instancia atual
     */
    public String encriptar(String str) {
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < str.length(); i++) {
            buffer.append((char) (str.charAt(i) + getKeyChar(i)));
        }
        String encryptedMSG = buffer.toString();
        String encryptedBase64MSG = encode(encryptedMSG);
        return encryptedBase64MSG;
    }

    /**
     * Decripta uma String com a chave da instancia atual
     * @param strBase64 Uma String criptografada a ser decriptada
     * @return Uma String decriptada com a chave da instancia atual
     */
    public String decriptar(String strBase64) {
        String str = decode(strBase64);
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < str.length(); i++) {
            buffer.append((char) (str.charAt(i) - getKeyChar(i)));
        }
        String decryptedMSG = buffer.toString();
        return decryptedMSG;
    }

    private char getKeyChar(int position) {
        return chave.charAt(position % chave.length());
    }

    private void printString(String str) {
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < str.length(); i++) {
            buffer.append((int) (str.charAt(i))).append('|');
        }
        //Utils.log( buffer.toString());
    }


    /**
     * Converte cada char em 3 chars fatorando o número em base 64.
     * @param str
     * @return
     */
    private String encode(String str) {
        StringBuffer encoded = new StringBuffer();
        for (int i = 0; i < str.length(); i++) {
            int caracter = (int) str.charAt(i);
            int base = charTab.length;
            int fator1 = caracter / base / base;
            int fator2 = (caracter - (fator1 * base * base)) / base;
            int resto = caracter % base;
            encoded.append((char) charTab[fator1]).append((char) charTab[fator2]).append((char) charTab[resto]);
        }
        return encoded.toString();
    }

    /**
     * Converte cada 3 chars em 1 char revertendo o processo da fatoracao em base 64.
     * @param str
     * @return
     */
    private String decode(String str) {
        StringBuffer decoded = new StringBuffer();
        for (int i = 0; i < str.length(); i += 3) {
            int fator1 = decode(str.charAt(i));
            int fator2 = decode(str.charAt(i + 1));
            int resto = decode(str.charAt(i + 2));
            int base = charTab.length;
            int caracter = ((fator1 * base) + fator2) * base + resto;
            decoded.append((char) caracter);
        }
        return decoded.toString();
    }

    static int decode(char c) {
        if (c >= 'A' && c <= 'Z') {
            return ((int) c) - 65;
        } else if (c >= 'a' && c <= 'z') {
            return ((int) c) - 97 + 26;
        } else if (c >= '0' && c <= '9') {
            return ((int) c) - 48 + 26 + 26;
        } else {
            switch (c) {
                case '+':
                    return 62;
                case '/':
                    return 63;
                case '=':
                    return 0;
                default:
                    throw new RuntimeException(
                            "unexpected code: " + c);
            }
        }
    }
}
