package br.com.mailanalyzer.commands;

import br.com.mailanalyzer.domain.ActiveReceiver;
import br.com.mailanalyzer.main.Base;

/**
 * 
 * @author Lucas Israel
 * @contact mcluck.ti@gmail.com
 * @version 1.0
 * @Date 21-04-2011
 *
 */
public class ActiveReceiverCommand extends CommandFluxo{

    @Override
    public void run() {
        ActiveReceiver receiver = (ActiveReceiver)getParameters().get(Base.FIELD_ACTIVE_RECEIVER);
        receiver.setLastID(receiver.getLastID()+1);
    }

}
