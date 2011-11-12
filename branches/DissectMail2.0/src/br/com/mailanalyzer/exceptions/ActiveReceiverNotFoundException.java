/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.mailanalyzer.exceptions;

/**
 *
 * @author Lucas Israel
 * @contact mcluck.ti@gmail.com
 * @version 1.0
 * @Date 25-04-2011
 *
 */
public class ActiveReceiverNotFoundException extends Exception{
    private static String MESSAGE = "Could not find the host defined in the active receiver of messages.";
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
