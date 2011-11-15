package br.com.mailanalyzer.utils.cripto;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author Lucas Israel
 * @contact mcluck.ti@gmail.com
 * @version 1.0
 * @Date 29-04-2011
 *
 */
public interface Cryptography {
    String encrypt(String value) throws NoSuchAlgorithmException;
}
