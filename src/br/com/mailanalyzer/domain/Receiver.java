
package br.com.mailanalyzer.domain;
/**
 *
 * @author Lucas Israel
 * @contact mcluck.ti@gmail.com
 * @version 1.0
 * @Date 21-04-2011
 *
 */
public interface Receiver {
    public static final int ACTIVE_RECEIVER_TYPE_EMAIL = 1;
    public Message[] getMessage();
}
