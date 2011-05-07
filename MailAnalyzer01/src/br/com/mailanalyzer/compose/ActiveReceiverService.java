/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.mailanalyzer.compose;

import br.com.mailanalyzer.domain.ActiveReceiver;
import br.com.mailanalyzer.fluxo.ActiveReceiverFlow;
import br.com.mailanalyzer.fluxo.PropertyRetriever;
import br.com.mailanalyzer.main.Base;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Lucas Israel
 */
public class ActiveReceiverService implements  Runnable{

    public ActiveReceiverService(ActiveReceiver activeReceiver){
        this.activeReceiver = activeReceiver;
    }

    private boolean flag = true;
    private long lastTimeConsult =0;
    public void stopService(){
        flag=false;
    }
    private ActiveReceiver activeReceiver;
    public void run() {
        setTimerNow();

        while(flag){

            if(canSearch()){
                ActiveReceiverFlow flowRec = new ActiveReceiverFlow(activeReceiver);
                flowRec.init();
                setTimerNow();
            }

            try {
                Thread.sleep(Base.INTERVALO_SERVICO);
            } catch (InterruptedException ex) {
                Logger.getLogger(ActiveReceiverService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void setTimerNow(){
        lastTimeConsult = new java.util.Date().getTime();
    }

    private boolean canSearch(){
        long now = new java.util.Date().getTime();
        return ((lastTimeConsult+Base.RANGE_CONSULT_MESSAGE) <= now);
    }
}