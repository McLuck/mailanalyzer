/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.mailanalyzer.fluxo;

import br.com.mailanalyzer.commands.CommandFluxo;
import br.com.mailanalyzer.commands.MainCommand;
import br.com.mailanalyzer.log.L;
import br.com.mailanalyzer.main.Base;
import br.com.mailanalyzer.main.Config;

/**
 *
 * @author McLuck
 */
public class MainFluxo extends Fluxo{
    public static final String TAG = "Fluxo Principal";

    public MainFluxo(){
        //Por enquanto, so tem um fluxo dentro da aplicacao...
        super(Base.MAIN_FLOW, new InterfaceComposeFlow[]{
            new InitServiceReceiverFlow()
        });
        if(!Config.isNivelLogBaixo()){
            L.d(TAG, this, "Inicia o primeiro fluxo da aplicação");
        }
    }

    @Override
    public CommandFluxo getCommandFluxo() {
        return new MainCommand();
    }

}
