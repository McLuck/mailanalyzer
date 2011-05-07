package br.com.mailanalyzer.commands;

import br.com.mailanalyzer.domain.ActiveReceiver;
import br.com.mailanalyzer.main.Base;

/**
 *
 * @author Lucas Israel
 */
public class ActiveReceiverCommand extends CommandFluxo{

    @Override
    public void run() {
        //Faz LOG COMPLETO

        //Faz a atualizacao da ID no banco de dados de ActiveReceiver
        ActiveReceiver receiver = (ActiveReceiver)getParameters().get(Base.FIELD_ACTIVE_RECEIVER);
        receiver.setLastID(receiver.getLastID()+1);
        //dao.save(receiver);

        //Inicia proximo Fluxo
    }

}
