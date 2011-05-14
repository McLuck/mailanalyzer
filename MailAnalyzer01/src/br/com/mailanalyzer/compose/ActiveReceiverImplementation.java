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
import br.com.mailanalyzer.main.Base;
import br.com.mailanalyzer.utils.EmailReader;

/**
 *
 * @author McLuck
 */
public class ActiveReceiverImplementation implements Receiver, InterfaceComposeFlow, PropertyRetriever {

    public ActiveReceiverImplementation(ActiveReceiver activeReceiver) {
        this.activeReceiver = activeReceiver;
    }
    private ActiveReceiver activeReceiver;
    private Message message;

    public Message getMessage() {
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

                EmailReader reader = new EmailReader();

                reader.setHost("pop.gmail.com");
                reader.setAutenticador("true");
                reader.setCharset("UTF-8");
                reader.setDebug("true");
                reader.setPort("995");
                reader.setProtocolo("pop3");
                reader.setSocketclass("javax.net.ssl.SSLSocketFactory");
                reader.setSocketport("995");
                reader.setFallback("true");
                reader.setStarttls("true");
                
                Message m[];             
                m = reader.receive(activeReceiver.getHost(), activeReceiver.getUsuario(), activeReceiver.getSenha());




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
    }

    public Object getPropertyValue() {
        return new Object[]{message, activeReceiver};
    }

    public boolean stopFlow() {
        return getMessage() == null;
    }
}
