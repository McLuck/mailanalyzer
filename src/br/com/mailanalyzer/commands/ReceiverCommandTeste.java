/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.mailanalyzer.commands;

import br.com.mailanalyzer.dao.MessageDAO;
import br.com.mailanalyzer.domain.Message;
import br.com.mailanalyzer.fluxo.TratarMensagemFlow;
import br.com.mailanalyzer.log.L;
import br.com.mailanalyzer.main.Base;
import javax.swing.JOptionPane;

/**
 *
 * @author McLuck
 */
public class ReceiverCommandTeste extends CommandFluxo{
    private MessageDAO mdao = new MessageDAO();
    @Override
    public void run() {
        String input = "";
        while(input!=null){
            input = JOptionPane.showInputDialog("Digite a mensagem:");
            L.d("Recebedor de Teste", this, "Recebendo mensagem: "+input);
            Message msg = new Message();
            msg.setAssunto("Teste");
            msg.setMensagem(input);
            msg.setTipoRecebimento(Base.RECEIVER_TYPE_SMSC_GATEWAY);
            mdao.salvar(msg);
            mdao.commit();
            mdao.close();
            TratarMensagemFlow flow = new TratarMensagemFlow(msg);
            flow.init();
        }
    }

}
