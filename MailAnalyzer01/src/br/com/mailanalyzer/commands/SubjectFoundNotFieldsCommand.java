package br.com.mailanalyzer.commands;

import br.com.mailanalyzer.domain.ProcessedMessage;
import br.com.mailanalyzer.log.L;

/**
 *
 * @author Lucas Israel
 * @contact mcluck.ti@gmail.com
 * @version 1.0
 * @Date 07/05/2011
 * @reviser Bruno Marin Mota
 *
 */
public abstract class SubjectFoundNotFieldsCommand extends SubjectFoundCommand{

    private boolean required = false;
    public static final String TAG = "Comando para assunto encontrado";
    public boolean isRequired(){
        return required;
    }

   public SubjectFoundNotFieldsCommand(ProcessedMessage procMsg ) {
       super(procMsg);
       L.d(TAG, this, "Mensagem processada");
   }
}