
package br.com.mailanalyzer.exceptions;

/**
 *
 * @author Lucas Israel
 * @contact mcluck.ti@gmail.com
 * @version 1.0
 * @Date 25-04-2011
 * 
 */
public class UnrecognizableCharacterException extends Exception{
    private static String MESSAGE = "One or more characters not recognized. Maybe the charset of the message is incorrect.";
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
