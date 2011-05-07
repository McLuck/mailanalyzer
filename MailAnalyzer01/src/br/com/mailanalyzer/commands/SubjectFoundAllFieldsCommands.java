package br.com.mailanalyzer.commands;

import br.com.mailanalyzer.domain.ProcessedMessage;
/**
 *
 * @author Bruno Marin Mota
 * @contact brunomarinmota@gmail.com
 * @version 1.0
 * @Date 07/05/2011
 * @reviser 
 *
 */
public abstract class SubjectFoundAllFieldsCommands extends SubjectFoundCommand {

	public SubjectFoundAllFieldsCommands(ProcessedMessage processedMessage) {
            super(processedMessage);
	}

    @Override
    public void run() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
