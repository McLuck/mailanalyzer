/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.mailanalyzer.compose;

import br.com.mailanalyzer.domain.Message;
import br.com.mailanalyzer.fluxo.InterfaceComposeFlow;
import br.com.mailanalyzer.fluxo.MutableComponent;

/**
 *
 * @author McLuck
 */
public class SaveMessage implements InterfaceComposeFlow, MutableComponent{
    private Message message;
    public void execute() {
        //save Message
        //dao.save(message);
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

}
