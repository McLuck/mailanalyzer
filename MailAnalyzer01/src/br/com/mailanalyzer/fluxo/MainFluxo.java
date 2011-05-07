/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.mailanalyzer.fluxo;

import br.com.mailanalyzer.commands.CommandFluxo;
import br.com.mailanalyzer.commands.MainCommand;
import br.com.mailanalyzer.main.Base;

/**
 *
 * @author McLuck
 */
public class MainFluxo extends Fluxo{

    public MainFluxo(){
        //Por enquanto, so tem um fluxo dentro da aplicacao...
        super(Base.MAIN_FLOW, new InterfaceComposeFlow[]{
            new InitServiceReceiverFlow()
        });
    }

    @Override
    public CommandFluxo getCommandFluxo() {
        return new MainCommand();
    }

}
