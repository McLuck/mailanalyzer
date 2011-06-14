package br.com.mailanalyzer.main;

import br.com.mailanalyzer.domain.ActiveReceiver;
import br.com.mailanalyzer.fluxo.MainFluxo;



/**
 *
 * @author Lucas Israel
 * @contact mcluck.ti@gmail.com
 * @version 1.0
 * @Date 15-05-2011
 *
 */
public class Teste implements Runnable{
    public static void main(String[]args){
        //Seta para ambiente de teste. (Isto ira habilitar a resposta automatica da mensagem)
        Config.IS_TEST_ENVIRONMENT = true;
        
        //Seta modo debug. Isto ira habilitar o andamento dos processos no console.
        Config.DEBUG_MODE = true;
        
        //Seta proxy. Caso esteja utilizando PROXY, obrigatorio setar as configs de Proxy.
        Config.IS_PROXY = false;
        //Config.PROXY_ADDRESS = "10.12.128.13";
        //Config.PROXY_PORT = 8080;
        //Config.PROXY_SCHEME = "http";

        ActiveReceiver receive = new ActiveReceiver();
        receive.setNome("Email teste");
        receive.setLastID(0);
        receive.setOtype(Base.RECEIVER_TYPE_EMAIL);
        receive.setPort(465);
        receive.setSsl(true);
        receive.setUsuario("manalyzertest@gmail.com");
        receive.setHost("pop.gmail.com");
        receive.setSenha("1qw23er45t");

        Config.Register.ADD_RECEIVER(receive);
        
        //Inicia servico Base. O Fluxo Principal
        Teste t = new Teste();
        t.run();
    }

    public void run() {
        MainFluxo main = new MainFluxo();
        main.init();
    }
}
