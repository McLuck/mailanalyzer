package br.com.mailanalyzer.testes;

import br.com.mailanalyzer.dao.HB;
import br.com.mailanalyzer.dao.actions.ActionActiveReceiver;
import br.com.mailanalyzer.domain.ActiveReceiver;
import br.com.mailanalyzer.domain.DomainObject;
import br.com.mailanalyzer.domain.Receiver;
import br.com.mailanalyzer.main.Base;
import br.com.mailanalyzer.utils.Utils;
import br.com.mailanalyzer.utils.cripto.Encriptador;
import java.util.List;

/**
 * Esta classe ira testar varios cenarios de CRUD do ActiveReceiver
 * @author Lucas Israel
 * @contact mcluck.ti@gmail.com
 * @version 1.0
 * @Date 21-04-2011
 *
 */
public class TesteActiveReceiver {

    public static void main(String[] args) throws InstantiationException, IllegalAccessException {
        //ActionActiveReceiver a = (ActionActiveReceiver)br.com.mcluck.asynchronously.Utils.Factory.getInstance(ActionActiveReceiver.class);
        ActionActiveReceiver a = new ActionActiveReceiver();

        ActiveReceiver act = new ActiveReceiver();
        List<ActiveReceiver> lista = a.showAll();
        
        for(ActiveReceiver ac : lista){
           ac.setNome("Email Teste do Mail Analyzer");
           a.setReceive(ac);
           a.alterar();
        }
        HB.getInstancia().closeSession();
        

        /* RODADA 2 TesteActiveReceiver completo (exceto exclusao)
        
        //Cadastrando email 2:
        myReceive = new ActiveReceiver();
        myReceive.setHost("pop.gmail.com");
        myReceive.setLastID(1);
        myReceive.setNome("Gmail de Francisca Herlayne");
        myReceive.setUsuario("fran.herlayne@gmail.com");
        myReceive.setOtype(Receiver.ACTIVE_RECEIVER_TYPE_EMAIL);
        myReceive.setPort(995);
        myReceive.setSsl(true);
        
        //setar senha com criptografia. Chave: email
        String senha = "minhaSenhaGmail";
        enc = new Encriptador(myReceive.getUsuario());
        senha = enc.encriptar(senha);
        myReceive.setSenha(senha);
        
        
        //Set ActiveReceive in Action
        a.setReceive(myReceive);
        //Salva
        a.salvar();
        
        //Cadastrando email 3:
        myReceive = new ActiveReceiver();
        myReceive.setHost("pop.gmail.com");
        myReceive.setLastID(1);
        myReceive.setNome("Gmail de Guilherme Lucas");
        myReceive.setUsuario("gfaria.mello@gmail.com");
        myReceive.setOtype(Receiver.ACTIVE_RECEIVER_TYPE_EMAIL);
        myReceive.setPort(995);
        myReceive.setSsl(true);
        
        //setar senha com criptografia. Chave: email
        senha = "minhaSenhaGmail";
        enc = new Encriptador(myReceive.getUsuario());
        senha = enc.encriptar(senha);
        myReceive.setSenha(senha);
        //Set ActiveReceive in Action
        a.setReceive(myReceive);
        //Salva
        a.salvar();
        
        
        
        //Cadastrando email 4:
        myReceive = new ActiveReceiver();
        myReceive.setHost("pop.gmail.com");
        myReceive.setLastID(1);
        myReceive.setNome("Gmail de Bruno Marin");
        myReceive.setUsuario("brunomarinmota@gmail.com");
        myReceive.setOtype(Receiver.ACTIVE_RECEIVER_TYPE_EMAIL);
        myReceive.setPort(995);
        myReceive.setSsl(true);
        
        //setar senha com criptografia. Chave: email
        senha = "minhaSenhaGmail";
        enc = new Encriptador(myReceive.getUsuario());
        senha = enc.encriptar(senha);
        myReceive.setSenha(senha);
        //Set ActiveReceive in Action
        a.setReceive(myReceive);
        //Salva
        a.salvar();
        
        
        
        //Cadastrando email 5:
        myReceive = new ActiveReceiver();
        myReceive.setHost("pop.gmail.com");
        myReceive.setLastID(1);
        myReceive.setNome("Gmail de Pedro Lobo");
        myReceive.setUsuario("pedro.lobo29@gmail.com");
        myReceive.setOtype(Receiver.ACTIVE_RECEIVER_TYPE_EMAIL);
        myReceive.setPort(995);
        myReceive.setSsl(true);
        
        //setar senha com criptografia. Chave: email
        senha = "minhaSenhaGmail";
        enc = new Encriptador(myReceive.getUsuario());
        senha = enc.encriptar(senha);
        myReceive.setSenha(senha);
        //Set ActiveReceive in Action
        a.setReceive(myReceive);
        //Salva
        a.salvar();
        
        
        
        // ---------- TESTANDO BUSCA, LISTAS e ALTERACOES --------
        
        
        //teste Buscando por nome (LIKE) e lista (Nao deve retornar nada)
        a.buscar("Lucas"); //Falta o parametro % para a busca
        
        //teste alteracao de registro (FORCANDO ERRO)
        try {
        myReceive = new ActiveReceiver();
        myReceive.setId(1);
        a.get(myReceive);
        enc = new Encriptador(myReceive.getUsuario());
        myReceive.setSenha(enc.encriptar(myReceive.getSenha()));
        a.salvar();
        } catch (Exception ex) {
        ex.printStackTrace();
        }
        
        //teste exibe lista e exibir todos os registros
        a.showAll();
         */




        //Rodada 3: TesteActiveReceiver de busca por nome (like)
        //a.buscar("%lucas%");



        /*
        //Rodada 4: Recuperar um registro pela ID
        myReceive = new ActiveReceiver();
        myReceive.setId(1); //ID valida. De Lucas Israel
        a.get(myReceive);
        
         */


        /*
        //Rodada 5: Alteracao de um registro. (Primeiro recupera o objeto depois altera
        //Aqui vou aplicar criptografia na conta com ID 1 (nao apliquei quando cadastrei)
        myReceive = new ActiveReceiver();
        myReceive.setId(1); //ID valida. De Lucas Israel
        myReceive = a.get(myReceive);
        enc = new Encriptador(myReceive.getUsuario());
        myReceive.setSenha(enc.encriptar(myReceive.getSenha()));
        a.salvar();
        
        //Listar tudo pra ter certeza que alterou
        a.showAll();
        */
        a.showAll();
    }
}
/*
 * HISTORICO DE LOG
 * 
 * Primeira rodada: Cadastro do registro 1. Conta de Lucas Israel (Sem criptografia na senha)
 * 
 * Segunda rodada: TesteActiveReceiver completo (menos exclusao)
 * Resultado:
---
Cadastrando...
Gmail de Francisca Herlayne foi salvo com sucesso. ID do objeto: 2 - Na data: 30/04/2011 - 13:37
---
Cadastrando...
Gmail de Guilherme Lucas foi salvo com sucesso. ID do objeto: 3 - Na data: 30/04/2011 - 13:37
---
Cadastrando...
Gmail de Bruno Marin foi salvo com sucesso. ID do objeto: 4 - Na data: 30/04/2011 - 13:37
---
Cadastrando...
Gmail de Pedro Lobo foi salvo com sucesso. ID do objeto: 5 - Na data: 30/04/2011 - 13:37
--- 
Buscando por Lucas...
---
-----
Nome: null
Host: null
Usuario: null
Senha: null
java.lang.NullPointerException
	at br.com.mailanalyzer.utils.cripto.Encriptador.encriptar(Encriptador.java:32)
	at br.com.mailanalyzer.testes.TesteActiveReceiver.main(TesteActiveReceiver.java:126)

-----
Exibir tudo ...
ID: 1 - Nome: Gmail de Lucas Israel - SENHA: minhaSenhaGmail
ID: 2 - Nome: Gmail de Francisca Herlayne - SENHA: ADTADbADPADWACPAC7ADKADgADUADCADAADbADGACpADT
ID: 3 - Nome: Gmail de Guilherme Lucas - SENHA: ADUADPADPADaADKAC0ACTADbADNADNACzADcAChADQADZ
ID: 4 - Nome: Gmail de Bruno Marin - SENHA: ADPADbADjADWADQADAADGADgADRADPAC0ADcADVADKACs
ID: 5 - Nome: Gmail de Pedro Lobo - SENHA: ADdADOADSADaADQACBADRADdADKADQAB5ACmAChADQADZ
---
 * 
 * 
 * 
 * 
 * Rodada 3: TesteActiveReceiver de busca por nome (like)
 * Resultado:
Buscando por %lucas%...
ID: 1 - Nome: Gmail de Lucas Israel - SENHA: minhaSenhaGmail
ID: 3 - Nome: Gmail de Guilherme Lucas - SENHA: ADUADPADPADaADKAC0ACTADbADNADNACzADcAChADQADZ
---
 * 
 * 
 * 
 * 
 * 
 * 
 * Rodada 4: Recuperar um registro pela ID
 * Resultado:
----- Recuperando pela ID: 1
Nome: Gmail de Lucas Israel
Host: pop.gmail.com
Usuario: mcluck.ti@gmail.com
Senha: minhaSenhaGmail
-----
 * 
 * 
 * 
 * 
 * 
 * Rodada 5: Alteracao de um registro. (Primeiro recupera o objeto depois altera
 * Resultado:
----- Recuperando pela ID: 1
Nome: Gmail de Lucas Israel
Host: pop.gmail.com
Usuario: mcluck.ti@gmail.com
Senha: minhaSenhaGmail
-----
Cadastrando...
Gmail de Lucas Israel foi salvo com sucesso. ID do objeto: 1 - Na data: 30/04/2011 - 13:18
---
Exibir tudo ...
ID: 1 - Nome: Gmail de Lucas Israel - SENHA: ADaADMADaADdADEAC+ACTADiADRAChACuADaADCADSADY
ID: 2 - Nome: Gmail de Francisca Herlayne - SENHA: ADTADbADPADWACPAC7ADKADgADUADCADAADbADGACpADT
ID: 3 - Nome: Gmail de Guilherme Lucas - SENHA: ADUADPADPADaADKAC0ACTADbADNADNACzADcAChADQADZ
ID: 4 - Nome: Gmail de Bruno Marin - SENHA: ADPADbADjADWADQADAADGADgADRADPAC0ADcADVADKACs
ID: 5 - Nome: Gmail de Pedro Lobo - SENHA: ADdADOADSADaADQACBADRADdADKADQAB5ACmAChADQADZ
---
 * 
 * 
 */