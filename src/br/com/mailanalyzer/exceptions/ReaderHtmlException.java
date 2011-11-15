
package br.com.mailanalyzer.exceptions;

/**
 *
 * @author Lucas Israel
 * @contact mcluck.ti@gmail.com
 * @version 1.0
 * @Date 25-04-2011
 * 
 */
public class ReaderHtmlException extends Exception{
    private static String MESSAGE = "Error in reading the HTML message.";
    @Override
    public String toString(){
        return MESSAGE;
    }

    @Override
    public void printStackTrace() {
        System.err.println(MESSAGE);
        super.printStackTrace();
    }

    @Override
    public String getLocalizedMessage() {
        return toString();
    }
}
