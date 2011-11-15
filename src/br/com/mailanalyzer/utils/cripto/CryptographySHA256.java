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

public class CryptographySHA256 extends CryptographyGeneric implements Cryptography {

    protected CryptographySHA256() {
    }

    public String encrypt(String value) throws NoSuchAlgorithmException {
        return encryptByAlgorithm("SHA-256", value);
    }
}
