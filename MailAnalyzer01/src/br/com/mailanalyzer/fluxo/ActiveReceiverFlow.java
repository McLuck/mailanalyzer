/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.mailanalyzer.fluxo;

import br.com.mailanalyzer.commands.ActiveReceiverCommand;
import br.com.mailanalyzer.commands.Command;
import br.com.mailanalyzer.commands.CommandFluxo;
import br.com.mailanalyzer.compose.ActiveReceiverImplementation;
import br.com.mailanalyzer.compose.SaveMessage;
import br.com.mailanalyzer.domain.ActiveReceiver;
import br.com.mailanalyzer.main.Base;

/**
 *
 * @author McLuck
 */
public class ActiveReceiverFlow extends Fluxo{

    public ActiveReceiverFlow(ActiveReceiver activeReceiver){
        super(Base.ACTIVE_RECEIVER_FLOW, new InterfaceComposeFlow[]{
            new ActiveReceiverImplementation(activeReceiver),
            new SaveMessage()
        });
    }

    @Override
    public CommandFluxo getCommandFluxo() {
        //Comando a ser executado no final do fluxo
        return new ActiveReceiverCommand();
    }

}
