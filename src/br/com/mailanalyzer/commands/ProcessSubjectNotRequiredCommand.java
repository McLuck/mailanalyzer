package br.com.mailanalyzer.commands;


import br.com.mailanalyzer.domain.ProcessedMessage;
import br.com.mailanalyzer.log.L;
/**
 *
 * @author Bruno Marin Mota
 * @contact brunomarinmota@gmail.com
 * @version 1.0
 * @Date 07/05/2011
 * @reviser
 *
 */
public abstract class ProcessSubjectNotRequiredCommand extends SubjectFoundNotFieldsCommand{

    public static final String TAG = "Comando para assunto não requerido";

    public ProcessSubjectNotRequiredCommand(ProcessedMessage processedMessage) {
        super(processedMessage);
        L.d(TAG, this, "Mensagem processada");
    }

}
