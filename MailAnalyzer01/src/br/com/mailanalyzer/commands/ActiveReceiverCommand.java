package br.com.mailanalyzer.commands;

import br.com.mailanalyzer.domain.ActiveReceiver;
import br.com.mailanalyzer.domain.Message;
import br.com.mailanalyzer.fluxo.TratarMensagemFlow;
import br.com.mailanalyzer.log.Log;
import br.com.mailanalyzer.main.Base;
import br.com.mailanalyzer.main.Config;
import br.com.mcluck.asynchronously.Utils.Factory;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 * @author Lucas Israel
 * @contact mcluck.ti@gmail.com
 * @version 1.0
 * @Date 21-04-2011
 *
 */
public class ActiveReceiverCommand extends CommandFluxo {

    @Override
    public void run() {
        //Recupera as mensagens entradas
        Log.d(this.getClass().getSimpleName(), "--------- Executando comando de mensagem recebida...");
        ActiveReceiver receiver = (ActiveReceiver) getParameters().get(Base.FIELD_ACTIVE_RECEIVER);
        Object o = getParameters().get(Base.FIELD_MESSAGE);
        if(o==null){
            Log.d(this.getClass().getSimpleName(), "--------- Mensagem é nula. Encerrando processo de mensagem recebida.");
            return;
        }
        
        Message[] msgs = (Message[])o;
        
        //Cria um fluxo diferente para cada mensagem
        for (Message m : msgs) {
            Log.d(this.getClass().getSimpleName(), "Iniciando novo fluxo para tratamento de nova mensagem.");
            TratarMensagemFlow tratamento = new TratarMensagemFlow(m);
            tratamento.init();   
        }
        Log.d(this.getClass().getSimpleName(), "Execução terminada.");
    }
    
    @Override
    public boolean startNewThread(){
        return Config.THREAD_FOR_MESSAGES_HANDLING;
    }
}
