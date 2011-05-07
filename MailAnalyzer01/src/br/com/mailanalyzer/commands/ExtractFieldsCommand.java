/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.mailanalyzer.commands;

import br.com.mailanalyzer.domain.Field;
import br.com.mailanalyzer.domain.ProcessedMessage;
import br.com.mailanalyzer.exceptions.MessageNotFound;
import java.util.Hashtable;

public abstract class ExtractFieldsCommand extends CommandFluxo{
    private ProcessedMessage processedMessage;

    public ExtractFieldsCommand(){
        setParameters(new Hashtable());
    }

    private String getValue(Field field){
        String value = null;

        String nameField = field.getName();
        int typeField = field.getType();

        //Procurar na mensagem processada o campo solicitado
        
        return value;
    }

    /**
     * Performs the extraction of the fields defined in the identified subject of the ProcessedMessage Object
     * @throws MessageNotFound
     */
    public void extract() throws MessageNotFound{
        for(int i=0;i<processedMessage.getSubjectIdentified().getFields().size();i++){
            getParameters().put(processedMessage.getSubjectIdentified().getFields().get(i), getValue(processedMessage.getSubjectIdentified().getFields().get(i)));
        }
    }


    /**
     * @return the processedMessage
     */
    public ProcessedMessage getProcessedMessage() {
        return processedMessage;
    }

    /**
     * @param processedMessage the processedMessage to set
     */
    public void setProcessedMessage(ProcessedMessage processedMessage) {
        this.processedMessage = processedMessage;
    }

    @Override
    public boolean startNewThread(){
        return false;
    }
}
