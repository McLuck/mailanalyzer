
package br.com.mailanalyzer.domain;
/**
 *
 * @author Lucas Israel
 * @contact mcluck.ti@gmail.com
 * @version 1.0
 * @Date 21-04-2011
 *
 */
public interface PassiveReceiver extends Receiver{
    public void setMessage(Message message);
}
