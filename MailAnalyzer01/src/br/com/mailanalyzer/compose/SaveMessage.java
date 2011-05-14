/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.mailanalyzer.compose;

import br.com.mailanalyzer.dao.actions.ActionMessage;
import br.com.mailanalyzer.domain.Message;
import br.com.mailanalyzer.fluxo.InterfaceComposeFlow;
import br.com.mailanalyzer.fluxo.MutableComponent;
import br.com.mailanalyzer.fluxo.PropertyRetriever;
import br.com.mailanalyzer.main.Base;

/**
 * 
 * @author Lucas Israel
 * @contact mcluck.ti@gmail.com
 * @version 1.0
 * @Date 21-04-2011
 *
 */
public class SaveMessage implements InterfaceComposeFlow, MutableComponent, PropertyRetriever{
    private Message message;
    public void execute() {
        ActionMessage dao = new ActionMessage();
        dao.setMessage(message);
        dao.salvar();
    }
    private boolean stop = false;
    public void updateComponent(Object obj) {
        if(obj==null){
            stop = true;
            return;
        }
            
        if(obj instanceof Object[]){
            Object[] objs = (Object[])obj;
            for(Object o : objs){
                if(o instanceof Message){
                    this.message = (Message)o;
                    break;
                }
            }
        }else if(obj instanceof Message){
            this.message = (Message)obj;
        }
    }

    public boolean stopFlow() {
        return stop;
    }

    public Object getPropertyName() {
        return Base.FIELD_SAVE_MESSAGE;
    }

    public Object getPropertyValue() {
        return message;
    }

}
