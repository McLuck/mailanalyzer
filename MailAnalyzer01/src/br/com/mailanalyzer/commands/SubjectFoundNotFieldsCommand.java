package br.com.mailanalyzer.commands;

/**
 *
 * @author McLuck
 */
public abstract class SubjectFoundNotFieldsCommand extends SubjectFoundCommand{
    private boolean required = false;
    public boolean isRequired(){
        return required;
    }
}
