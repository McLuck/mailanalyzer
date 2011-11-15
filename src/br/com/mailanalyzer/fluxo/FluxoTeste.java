/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.mailanalyzer.fluxo;

import br.com.mailanalyzer.commands.CommandFluxo;
import br.com.mailanalyzer.commands.ReceiverCommandTeste;

/**
 *
 * @author McLuck
 */
public class FluxoTeste extends Fluxo{

    public FluxoTeste(){
        super("FluxoDeTeste", new InterfaceComposeFlow[]{});
    }
    @Override
    public CommandFluxo getCommandFluxo() {
        return new ReceiverCommandTeste();
    }

}
