package br.com.mailanalyzer.utils.cripto;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.thoughtworks.xstream.core.util.Base64Encoder;


/**
 *
 * @author Lucas Israel
 * @contact mcluck.ti@gmail.com
 * @version 1.0
 * @Date 29-04-2011
 *
 */
public abstract class CryptographyGeneric {

    private MessageDigest messageDigest;
    private Base64Encoder encoder;

    protected void useAlgorithm(String algorithm) throws NoSuchAlgorithmException {
        if (messageDigest == null || messageDigest.getAlgorithm() != algorithm) {
            messageDigest = MessageDigest.getInstance(algorithm);
        }

        if (encoder == null) {
            encoder = new Base64Encoder();
        }
    }

    protected String encryptByAlgorithm(String algorithm, String value)
            throws NoSuchAlgorithmException {

        if (value == null) {
            throw new IllegalArgumentException("The value is null.");
        }

        useAlgorithm(algorithm);
        byte[] hash = messageDigest.digest(value.getBytes());
        return encoder.encode(hash);
    }
}
