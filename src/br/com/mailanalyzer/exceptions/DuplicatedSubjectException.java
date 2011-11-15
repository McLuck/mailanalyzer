package br.com.mailanalyzer.exceptions;

/**
 *
 * @author Lucas Israel
 * @contact mcluck.ti@gmail.com
 * @version 1.0
 * @Date 25-04-2011
 *
 */
public class DuplicatedSubjectException extends Exception {

    private static String MESSAGE = "Duplicated Subject was found. The service can not make any decision. The flow will run the subject command not found.";

    @Override
    public String toString() {
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
