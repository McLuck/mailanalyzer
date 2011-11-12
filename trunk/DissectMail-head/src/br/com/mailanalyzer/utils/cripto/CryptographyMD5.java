
package br.com.mailanalyzer.utils.cripto;

/**
 *
 * @author Lucas Israel
 * @contact mcluck.ti@gmail.com
 * @version 1.0
 * @Date 29-04-2011
 *
 */
import java.security.NoSuchAlgorithmException;

public class CryptographyMD5 extends CryptographyGeneric implements Cryptography {

    protected CryptographyMD5(){}
    public String encrypt(String value) throws NoSuchAlgorithmException {
        return encryptByAlgorithm("MD5", value);
    }
}
