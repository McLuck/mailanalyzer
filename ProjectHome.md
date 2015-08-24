This is the repository for the project DissectMail. Welcome and thank you for your cooperation.

### Nomes ###
Se você está procurando o projeto Mail Analyzer, este projeto mudou de nome para Dissect Mail devido a existência de um software com o mesmo nome. Pedimos desculpas pela inconveniência causada.

### Sobre ###
DissectMail é uma API em fase de desenvolvimento.
Seu propósito é prover interação automática entre usuário e sistema através de mensagens.
A API propõe interpretar uma mensagem e ir buscar referências desta. Ao compreender o sentido da mensagem, executa alguma ação baseada no que foi interpretado pela API.

A API oferece alguns recursos como a ferramenta para tratar métodos assíncronos (já disponível para download) com o uso de annotations.

Usando a anotação @Asynchronous, o método será executado em uma Thread quando chamado.

A biblioteca deve ser baixada e configurada no classpath.
Após isto, basta indicar com a anotação nos métodos que precisam ser assíncronos.

Veja abaixo um breve exemplo do uso da ferramenta.


```

public class Teste {
    
    public static void main(String args[]) throws InstantiationException, IllegalAccessException{
        
        //Instancia o objeto usando a Factory disponível na Ferramenta
        Teste t = (Teste)Factory.getInstance(Teste.class);
        
        //chama o metodo anotado.
        t.cadastrar(new Object());
        
        //Imprime qualquer coisa somente para ilustrar o funcionamento Assincrono
        System.out.println("Isto vem depois do cadastro, mas sera impresso antes.");
    }

    @Asynchronous
    public void cadastrar(Object myRegister) {
        //executa qualquer coisa
        System.out.println("Cadastro.");
        

        //Dorme. Apenas para testar.
        try {
            Thread.sleep(5000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }

        enviarEmail();
    }

    public void enviarEmail() {
        //Envia email
        System.out.println("Enviei email.");
    }
}

```

OBS: Dependência com javassist.
Baixar jar (biblioteca) do javassist em http://www.jboss.org/javassist/downloads


Para configurar o Mail Analyzer em seu projeto, baixe, descompacte e coloque as bibliotecas em seu classpath.

Veja exemplo de como implementar com o Mail Analyzer:
```
import br.com.mailanalyzer.domain.ActiveReceiver;
import br.com.mailanalyzer.fluxo.MainFluxo;
import br.com.mailanalyzer.main.Base;
import br.com.mailanalyzer.main.Config;

public class Main {

    public static void main(String[] args) {

        //Configuracao rapida para BD
        Config.Hibernate.SET_USERNAME_BD("root");
        Config.Hibernate.SET_PASSWORD_BD("123456");
        Config.Hibernate.SET_URL_BD("jdbc:mysql://localhost/ma2");

        Config.Hibernate.SET_DRIVER_CLASS(Config.Hibernate.values.DriverClass.MY_SQL);

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
        receive.setUsuario("email@gmail.com");
        receive.setHost("pop.gmail.com");
        receive.setSenha("senhaDoEmail");

        Config.Register.ADD_RECEIVER(receive);

        //Inicia servico Base. O Fluxo Principal
        Main t = new Main();
        t.run();
    }

    public void run() {
        MainFluxo main = new MainFluxo();
        main.init();
    }
}

```

Com isto você terá integrado o Mail Analyzer em seu projeto.
A funcionalidade de análise está sendo desenvolvida. Em breve a nova versão irá fornecer canal de feedback para a API "aprender" com as ações tomadas e poder tomar ações com base no perfil criado (conceito de AID).

Acompanhem o desenvolvimento.

Obrigado