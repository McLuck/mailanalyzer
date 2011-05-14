package br.com.mailanalyzer.commands;

import br.com.mailanalyzer.dao.actions.ActionMessage;
import br.com.mailanalyzer.domain.Message;
/**
 *
 * @author Lucas Israel
 * @contact mcluck.ti@gmail.com
 * @version 1.0
 * @Date 25-04-2011
 *
 */
public class MensagemTratadaCommand extends CommandFluxo{

    @Override
    public void run() {
        ActionMessage dao = new ActionMessage();
        Message m = new Message();
        
        
    }
    
}
