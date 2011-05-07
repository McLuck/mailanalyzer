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
public abstract class ProcessSubjectRequiredCommand extends SubjectFoundNotFieldsCommand {
 
	public ProcessSubjectRequiredCommand(ProcessedMessage processedMessage) {
           super(processedMessage);
	}
	 
}
