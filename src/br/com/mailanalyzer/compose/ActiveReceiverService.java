/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.mailanalyzer.compose;

import br.com.mailanalyzer.domain.ActiveReceiver;
import br.com.mailanalyzer.fluxo.ActiveReceiverFlow;
import br.com.mailanalyzer.fluxo.PropertyRetriever;
import br.com.mailanalyzer.log.L;
import br.com.mailanalyzer.main.Base;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Lucas Israel
 */
public class ActiveReceiverService implements  Runnable{
    public static final String TAG = "ActiveReceiverService";
    public ActiveReceiverService(ActiveReceiver activeReceiver){
        this.activeReceiver = activeReceiver;
    }
    
    public ActiveReceiver getReceiver(){
        return activeReceiver;
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
            //System.out.println(" Rodando o servico... ");
            if(canSearch()){
                ActiveReceiverFlow flowRec = new ActiveReceiverFlow(activeReceiver);
                flowRec.init();
                L.d(TAG, this, "Iniciando fluxo de consulta");
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
