/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mailanalyzer.compose;

import br.com.mailanalyzer.domain.ActiveReceiver;
import br.com.mailanalyzer.domain.Message;
import br.com.mailanalyzer.domain.Receiver;
import br.com.mailanalyzer.fluxo.InterfaceComposeFlow;
import br.com.mailanalyzer.fluxo.PropertyRetriever;
import br.com.mailanalyzer.log.Log;
import br.com.mailanalyzer.main.Base;
import br.com.mailanalyzer.utils.EmailReader;
import br.com.mailanalyzer.utils.Utils;
import br.com.mailanalyzer.utils.cripto.Encriptador;

/**
 *
 * @author McLuck
 */
public class ActiveReceiverImplementation implements Receiver, InterfaceComposeFlow, PropertyRetriever {

    public ActiveReceiverImplementation(ActiveReceiver activeReceiver) {
        this.activeReceiver = activeReceiver;
    }
    private ActiveReceiver activeReceiver;
    private Message message[];

    public Message[] getMessage() {
        return message;
    }

    /**
     * @return the activeReceiver
     */
    public ActiveReceiver getActiveReceiver() {
        return activeReceiver;
    }

    /**
     * @param activeReceiver the activeReceiver to set
     */
    public void setActiveReceiver(ActiveReceiver activeReceiver) {
        this.activeReceiver = activeReceiver;
    }

    public void execute() {
        switch (activeReceiver.getOtype()) {
            case Base.RECEIVER_TYPE_EMAIL: {
                Log.d("ActiveReceiverImplementation", "Buscando no email: " + activeReceiver.getUsuario());
                EmailReader reader = new EmailReader();

                reader.setHost("pop.gmail.com");
                reader.setAutenticador("true");
                reader.setCharset("UTF-8");
                reader.setDebug("false");
                reader.setPort("995");
                reader.setProtocolo("pop3");
                reader.setSocketclass("javax.net.ssl.SSLSocketFactory");
                reader.setSocketport("995");
                reader.setFallback("true");
                reader.setStarttls("true");
                String senha = activeReceiver.getSenha();
                Encriptador enc = new Encriptador(activeReceiver.getUsuario());
                senha = enc.decriptar(senha);
                message = reader.receive(activeReceiver.getHost(), activeReceiver.getUsuario(), senha);

                Log.d("ActiveReceiverImplementation", "Finalizando recebimento de mensagens por email. Mensagens encontradas: " + message.length);

                //Seta o tipo de recebimento nas mensagens que chegam.
                if (message != null) {
                    for (int i = 0; i < message.length; i++) {
                        message[i].setTipoRecebimento(Base.RECEIVER_TYPE_EMAIL);
                    }
                }

                /*
                for(Message m : message){
                Utils.printMessage(m);
                }*/
                //Iniciar busca por protocolo pop



                break;
            }
            case Base.RECEIVER_TYPE_SMSC_GATEWAY: {
                //Inicia busca no gatway de SMSs pre definido.



                break;
            }
        }
    }

    public Object getPropertyName() {
        return new String[]{Base.FIELD_MESSAGE, Base.FIELD_ACTIVE_RECEIVER};
        //return Base.FIELD_MESSAGE;
    }

    public Object getPropertyValue() {
        return new Object[]{message, activeReceiver};
        //return message;
    }

    public boolean stopFlow() {
        return getMessage() == null;
    }
}
