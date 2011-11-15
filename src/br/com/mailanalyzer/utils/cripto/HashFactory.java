package br.com.mailanalyzer.utils.cripto;

/**
 *
 * @author Lucas Israel
 * @contact mcluck.ti@gmail.com
 * @version 1.0
 * @Date 29-04-2011
 *
 */
public class HashFactory {
    /**
     * Cria uma nova instancia de Cryptography para para hash MD5
     * @return Nova instancia de CryptographyMD5
     */
    public static CryptographyMD5 MD5(){
        return new CryptographyMD5();
    }

    /**
     * Cria uma nova instancia de Cryptography para para hash SHA256
     * @return Nova instancia de SHA256
     */
    public static CryptographySHA256 SHA256(){
        return new CryptographySHA256();
    }

    /**
     * Cria uma nova instancia de Cryptography para para hash SHA512
     * @return Nova instancia de SHA512
     */
    public static CryptographySHA512 SHA512(){
        return new CryptographySHA512();
    }
}
