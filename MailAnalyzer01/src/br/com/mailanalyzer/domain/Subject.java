package br.com.mailanalyzer.domain;

//import br.com.mailanalyzer.commands.CommandListener;

import javax.persistence.Column;
import javax.persistence.Table;


//import br.com.mailanalyzer.commands.Command;
/**
 * 
 *  @author Guilherme Lucas
 * 
 */

@Table(name = "subject")
public class Subject extends DomainObject {

    @Column(name = "name")
    private String name;

    @Column(name = "otext")
    private String text;

    
    private String commandFlowName;
    //private Field[] fileds;
    private ProcessedMessage processedMessage;
    
    /*
    public Command getCommand() {
    throw new UnsupportedOperationException("Not supported yet.");
    }
     */
    /**
     *  @return the name
     *
     */
    /*	public String getName() {
    return null;
    }

    /**
     *  @param name the name to set
     *
     */
    /*	public void setName(String name) {

    }

    /**
     *  @return the text
     *
     */
    /*	public String getText() {
    return null;
    }

    /**
     *  @param text the text to set
     *
     */
    /*	public void setText(String text) {

    }

    /**
     *  @return the fileds
     *
     */
    /*	public Field[] getFileds() {
    return null;
    }

    /**
     *  @param fileds the fileds to set
     *
     */
    /*	public void setFileds(Field[] fileds) {

    }
     */
}
