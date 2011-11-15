package br.com.mailanalyzer.domain;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.Cascade;

//import br.com.mailanalyzer.commands.Command;
/**
 *
 * @author Guilherme Lucas
 * @contact gfaria.mello@gmail.com
 * @version 1.1
 * @Date 29/04/2011
 * @reviser Lucas Israel
 *
 */
@Entity
@Table(name = "subject")
public class Subject extends DomainObject implements Serializable {

    private static final long serialVersionUID = 1L;
    
    
    @Column(name = "name")
    private String name;

    @Column(name = "otext")
    private String text;

    private String commandFlowName;

    @OneToMany(mappedBy = "subject", cascade = {CascadeType.ALL})
    @JoinColumn(name = "subject_id", nullable = true)
    @Cascade(value = org.hibernate.annotations.CascadeType.DELETE_ORPHAN)
    private List<Field> fields;

    @OneToMany(mappedBy = "subject", cascade = {CascadeType.ALL})
    @JoinColumn(name = "subject_id", nullable = true)
    @Cascade(value = org.hibernate.annotations.CascadeType.DELETE_ORPHAN)
    private List<ProcessedMessage> processedMessage;

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the text
     */
    public String getText() {
        return text;
    }

    /**
     * @param text the text to set
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * @return the commandFlowName
     */
    public String getCommandFlowName() {
        return commandFlowName;
    }

    /**
     * @param commandFlowName the commandFlowName to set
     */
    public void setCommandFlowName(String commandFlowName) {
        this.commandFlowName = commandFlowName;
    }

    /**
     * @return the processedMessage
     */
    public List<ProcessedMessage> getProcessedMessage() {
        return processedMessage;
    }

    /**
     * @param processedMessage the processedMessage to set
     */
    public void setProcessedMessage(List<ProcessedMessage> processedMessage) {
        this.processedMessage = processedMessage;
    }

    /**
     * @return the fields
     */
    public List<Field> getFields() {
        return fields;
    }

    /**
     * @param fields the fields to set
     */
    public void setFields(List<Field> fields) {
        this.fields = fields;
    }
    /*
    public Command getCommand() {
    throw new UnsupportedOperationException("Not supported yet.");
    }
     */
}
