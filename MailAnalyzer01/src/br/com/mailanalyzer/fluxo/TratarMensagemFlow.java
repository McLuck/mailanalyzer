/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mailanalyzer.fluxo;

import br.com.mailanalyzer.commands.CommandFluxo;
import br.com.mailanalyzer.compose.FiltroConvertMinusculo;
import br.com.mailanalyzer.compose.FiltroHtml;
import br.com.mailanalyzer.main.Base;

/**
 *
 * @author lucasisrael
 */
public class TratarMensagemFlow extends Fluxo{
    
    public TratarMensagemFlow(){
        super(Base.TRATAR_MENSAGEM_FLOW, new InterfaceComposeFlow[]{new FiltroHtml(), new FiltroConvertMinusculo()});
    }

    @Override
    public CommandFluxo getCommandFluxo() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
