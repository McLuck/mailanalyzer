/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.mailanalyzer.commands;

import br.com.mailanalyzer.compose.ActiveReceiverService;
import br.com.mailanalyzer.log.L;
import br.com.mailanalyzer.main.Base;
import br.com.mailanalyzer.main.Config;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author McLuck
 */
public class MainCommand extends CommandFluxo{

    public static final String TAG = "Comando principal";

    @Override
    public void run() {
        
        while(Base.RUNNING){
            try {
                Thread.sleep(1000);
                if(Config.isNivelLogMaximo()){
                    L.d(TAG, this, "Tempo para não iniciar serviços juntos. Não se inicia serviços ao mesmo tempo, para melhor aproveitamento do link de conexão.");
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(MainCommand.class.getName()).log(Level.SEVERE, null, ex);
                if(Config.isNivelLogMaximo()){
                    L.d(TAG, this, "Iniciando outro serviço");
                }
            }
        }
        
        //Para os servicos de Recebimento
        stopServices();

        //LOG DE TUDO
    }

    private void stopServices(){
        for(ActiveReceiverService service : Base.ACTIVE_SERVICES){
            service.stopService();
        }
    }

    @Override
    public boolean startNewThread(){
        return false;
    }

    /**
     * This function will print a message in console
     * @param msg a String for print in console
     * @throws Exception Possible null pointer exception
     */
    public void print(String msg) throws Exception{
        //aasldkjaskgdh
    }

    public static void main(String[] sss){
        
    }

}
