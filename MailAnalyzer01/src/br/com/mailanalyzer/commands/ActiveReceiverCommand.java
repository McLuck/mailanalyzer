package br.com.mailanalyzer.commands;

import br.com.mailanalyzer.domain.ActiveReceiver;
import br.com.mailanalyzer.domain.Message;
import br.com.mailanalyzer.fluxo.TratarMensagemFlow;
import br.com.mailanalyzer.log.L;
import br.com.mailanalyzer.main.Base;
import br.com.mailanalyzer.main.Config;
import java.util.Hashtable;

/**
 * 
 * @author Lucas Israel
 * @contact mcluck.ti@gmail.com
 * @version 1.0
 * @Date 21-04-2011
 *
 */
public class ActiveReceiverCommand extends CommandFluxo {

    public static final String TAG = "Comando do recebedor de mensagens";

    @Override
    public void run() {
        //Recupera as mensagens entradas
        if (!Config.isNivelLogBaixo()) {
            L.d(TAG, this, "Iniciando execução de mensagens recebidas");
        }
        ActiveReceiver receiver = (ActiveReceiver) getParameters().get(Base.FIELD_ACTIVE_RECEIVER);
        Object o = getParameters().get(Base.FIELD_MESSAGE);
        if (o == null) {
            if (!Config.isNivelLogBaixo()) {
                L.d(TAG, this, "Mensagem é nula. Encerrando processo de mensagem recebida.");
            }
            return;
        }

        Message[] msgs = (Message[]) o;

        //Cria um fluxo diferente para cada mensagem
        for (Message m : msgs) {
            if (!Config.isNivelLogBaixo()) {
                L.d(TAG, this, "Iniciando novo fluxo para tratamento de nova mensagem.");
            }

            //Aqui inicia o tratamento da mensagem. Desnecessario para msgs que devem ser ignoradas.
            //Portanto, vamos adicionar, neste ponto, a excecao de fluxo.
            if (m.getAssunto().toLowerCase().contains(Base.TAG_PARA_IGNORAR_MENSAGEM)) {
                AssuntoIgnoradoCommand asign = new AssuntoIgnoradoCommand(); //Dispara comando para assunto ignorado.
                Hashtable mapa = new Hashtable();
                mapa.put(Base.MESSAGE_TEMP_ASSUNTO_IGNORADO, m);
                asign.setParameters(mapa);
                asign.run();

                return; //Interrompe a execucao.
            } else {
                TratarMensagemFlow tratamento = new TratarMensagemFlow(m);
                tratamento.init();
            }
        }
        if (!Config.isNivelLogBaixo()) {
            L.d(TAG, this, "Execução terminada");
        }
    }

    @Override
    public boolean startNewThread() {
        return Config.THREAD_FOR_MESSAGES_HANDLING;
    }
}
