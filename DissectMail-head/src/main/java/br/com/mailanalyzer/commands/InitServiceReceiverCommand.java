package br.com.mailanalyzer.commands;

import br.com.mailanalyzer.compose.ActiveReceiverService;
import br.com.mailanalyzer.domain.ActiveReceiver;
import br.com.mailanalyzer.log.L;
import br.com.mailanalyzer.main.Base;
import br.com.mailanalyzer.main.Config;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Lucas Israel
 */
public class InitServiceReceiverCommand extends CommandFluxo{
    public static final String TAG = "Comando do receptor de serviço";
    @Override
    public void run() {
        Base.ACTIVE_SERVICES = new ActiveReceiverService[Base.GET_ACTIVE_RECEIVERS().size()];
        int i=0;
        //Inicia todos os servicos ActiveReceivers
        for(ActiveReceiver ac : Base.GET_ACTIVE_RECEIVERS()){
            ActiveReceiverService service = new ActiveReceiverService(ac);
            L.d(TAG, this, "Iniciando serviço do recebedor ativo");
            System.err.println("Iniciando servico de recebedor ativo: "+ac.getNome());
            
            //Guarda o servico para poder parar depois.
            Base.ACTIVE_SERVICES[i]=service;

            //Inicia o servico
            Thread receiver = new Thread(Base.ACTIVE_SERVICES[i]);
            receiver.start();

            i++;
            try {
                //Tempo para nao iniciar os servicos juntos.
                //A nao sincronizacao dos servicos rodando, permite melhor aproveitamento do link de conexao                
                Thread.sleep(400);
                if(Config.isNivelLogMaximo()){
                    L.d(TAG, this, "Tempo para não iniciar serviços juntos. Não se inicia serviços ao mesmo tempo, para melhor aproveitamento do link de conexão.");
                }
            } catch (InterruptedException ex) {                
                Logger.getLogger(InitServiceReceiverCommand.class.getName()).log(Level.SEVERE, null, ex);
                if(Config.isNivelLogMaximo()){
                    L.d(TAG, this, "Iniciando outro serviço");
                }
            }
        }
    }

    @Override
    public boolean startNewThread(){
        return false;
    }

}
