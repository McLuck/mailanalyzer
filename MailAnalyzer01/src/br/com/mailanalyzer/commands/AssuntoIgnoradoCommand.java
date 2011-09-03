/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.mailanalyzer.commands;

import br.com.mailanalyzer.domain.Message;
import br.com.mailanalyzer.log.L;
import br.com.mailanalyzer.main.Base;

/**
 *
 * @author BEFFP
 */
public class AssuntoIgnoradoCommand extends SubjectNotFoundCommand{
    public static final String TAG = "AssuntoIgnoradoCommand";
    @Override
    public void run() {
        Message m = (Message)getParameters().get(Base.MESSAGE_TEMP_ASSUNTO_IGNORADO);
        L.d(TAG, "Assunto ignorado na mensagem. Recebeu TAG para nao ser tratado automaticamente. Abortando tratamento de mensagem.");
    }
}
