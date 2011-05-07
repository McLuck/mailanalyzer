/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.mailanalyzer.commands;

import br.com.mailanalyzer.compose.ActiveReceiverService;
import br.com.mailanalyzer.main.Base;

/**
 *
 * @author McLuck
 */
public class MainCommand extends CommandFluxo{

    @Override
    public void run() {
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
