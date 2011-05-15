/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.mailanalyzer.compose;

import br.com.mailanalyzer.dao.HB;
import br.com.mailanalyzer.dao.actions.ActionMessage;
import br.com.mailanalyzer.domain.Message;
import br.com.mailanalyzer.fluxo.InterfaceComposeFlow;
import br.com.mailanalyzer.fluxo.MutableComponent;
import br.com.mailanalyzer.fluxo.PropertyRetriever;
import br.com.mailanalyzer.log.Log;
import br.com.mailanalyzer.main.Base;
import br.com.mailanalyzer.utils.Utils;

/**
 * 
 * @author Lucas Israel
 * @contact mcluck.ti@gmail.com
 * @version 1.0
 * @Date 21-04-2011
 *
 */
public class SaveMessage implements InterfaceComposeFlow, MutableComponent, PropertyRetriever{
    private Message[] message;
    public void execute() {
        Log.d("SaveMessage", "Iniciando execução do componente");
        if(message==null){
            Log.d("SaveMessage", "Objeto message de SaveMessage é nulo. Fluxo será encerrado.");
            stop = true;
            return;
        }
            
        ActionMessage dao = new ActionMessage();
        for(int i=0;i<message.length;i++){
            try{
                HB.getInstancia().closeSession();
            }catch(Exception e){}
            
            dao.setMessage(message[i]);
            dao.salvar();
        }
        Log.d("SaveMessage", "Salvou msg originais no banco de dados.");
    }
    private boolean stop = false;
    public void updateComponent(Object obj) {
        Log.d("SaveMessage", "UPDATE COMPONENT: "+obj);
        if(obj==null){
            stop = true;
            return;
        }
            
        if(obj instanceof Message[]){
            this.message = (Message[])obj;
        }else if(obj instanceof Object[]){
            Object[] objs  = (Object[])obj;
            for(Object o : objs){
                if(o instanceof Message[]){
                    this.message = (Message[])o;
                }
            }
        }else{
            Log.d("SaveMessage", "UpdateComponent nao pode atualizar message (Message[]) com o parametro "+obj);
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
