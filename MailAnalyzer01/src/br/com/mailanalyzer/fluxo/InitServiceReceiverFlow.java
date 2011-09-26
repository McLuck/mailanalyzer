/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.mailanalyzer.fluxo;

import br.com.mailanalyzer.commands.CommandFluxo;
import br.com.mailanalyzer.commands.InitServiceReceiverCommand;
import br.com.mailanalyzer.main.Base;

/**
 *
 * @author McLuck
 */
public class InitServiceReceiverFlow extends Fluxo implements InterfaceComposeFlow{

    public InitServiceReceiverFlow(){
        super(Base.INIT_SERVICE_RECEIVER_FLOW, new InterfaceComposeFlow[]{});
    }

    @Override
    public CommandFluxo getCommandFluxo() {
        //Inicia Lista de variacoes;
        Base.LoadTermVariations();
        return new InitServiceReceiverCommand();
    }

    public void execute() {
        
    }

    public boolean stopFlow() {
        return false;
    }

}
