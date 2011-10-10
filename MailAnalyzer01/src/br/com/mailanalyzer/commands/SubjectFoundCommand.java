/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

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
public abstract class SubjectFoundCommand extends CommandFluxo{
    private ProcessedMessage procMsg;
    public static final String TAG = "Comando para assunto encontrado";

    public SubjectFoundCommand(ProcessedMessage procMsg){
        this.procMsg = procMsg;
        L.d(TAG, this, "Mensagem processada");
    }

    /**
     * @return the procMsg
     */
    public ProcessedMessage getProcMsg() {
        return procMsg;
    }

    /**
     * @param procMsg the procMsg to set
     */
    public void setProcMsg(ProcessedMessage procMsg) {
        this.procMsg = procMsg;
    }
}
