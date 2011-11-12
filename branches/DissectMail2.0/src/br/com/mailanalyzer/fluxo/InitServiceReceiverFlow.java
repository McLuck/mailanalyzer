/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.mailanalyzer.fluxo;

import br.com.mailanalyzer.commands.CommandFluxo;
import br.com.mailanalyzer.commands.InitServiceReceiverCommand;
import br.com.mailanalyzer.log.L;
import br.com.mailanalyzer.main.Base;
import br.com.mailanalyzer.main.Config;

/**
 *
 * @author McLuck
 */
public class InitServiceReceiverFlow extends Fluxo implements InterfaceComposeFlow{

    public static final String TAG = "Fluxo do recebedor de serviço";

    public InitServiceReceiverFlow(){
        super(Base.INIT_SERVICE_RECEIVER_FLOW, new InterfaceComposeFlow[]{});
        if(!Config.isNivelLogBaixo()){
            L.d(TAG, this, "Inicio do Fluxo do Recebedor de Serviço");
        }
    }

    @Override
    public CommandFluxo getCommandFluxo() {
        //Inicia Lista de variacoes;
        L.a(TAG, this, "Inicia lista de variações");
        Base.LoadTermVariations();
        return new InitServiceReceiverCommand();
    }

    public void execute() {
        
    }

    public boolean stopFlow() {
        return false;
    }

}
