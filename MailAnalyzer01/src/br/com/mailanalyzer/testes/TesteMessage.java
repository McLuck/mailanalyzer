package br.com.mailanalyzer.testes;

import br.com.mailanalyzer.dao.actions.ActionMessage;
import br.com.mailanalyzer.domain.Message;

/**
 * Esta classe ira testar varios cenarios de CRUD do Message
 * @author Bruno Marin Mota
 * @contact brunomarinmota@gmail.com
 * @version 1.0
 * @Date 07-05-2011
 *
 */
public class TesteMessage {

    public static void main(String[] args) throws InstantiationException, IllegalAccessException {
        //Message m = (Message)br.com.mcluck.asynchronously.Utils.Factory.getInstance(Message.class);
        ActionMessage m = new ActionMessage();

        Message myMessage;

        // RODADA 2 TesteMessage completo (exceto exclusao)
        
        //Cadastrando mensagem 2:
        myMessage = new Message();
        myMessage.setAssunto("Teste 1");
        myMessage.setCodExterno("1");
        myMessage.setMensagem("Que belo programa para o SABADÃO!!");
        
        
        //Set ActiveMessage in Action
        m.setMessage(myMessage);
        //Salva
        m.salvar();
        
        //Cadastrando email 2:
        myMessage = new Message();
        myMessage.setAssunto("Teste 2");
        myMessage.setCodExterno("2");
        myMessage.setMensagem("Maravilha...");
              
        //Set ActiveMessage in Action
        m.setMessage(myMessage);
        //Salva
        m.salvar();
        
        // ---------- TESTANDO BUSCA, LISTAS e ALTERACOES --------
        
        
        //teste Buscando por assunto (LIKE) e lista (Nao deve retornar nada)
        m.buscar("Teste"); //Falta o parametro % para a busca
        
        //teste alteracao de registro (FORCANDO ERRO)
        try {
        myMessage = new Message();
        myMessage.setId(1);
        m.get(myMessage);
        myMessage.setMensagem("Teste Erro");
        m.salvar();
        } catch (Exception ex) {
        ex.printStackTrace();
        }
        
        //teste exibe lista e exibir todos os registros
        m.showAll();
         

        //Rodada 3: TesteActiveReceiver de busca por assunto (like)
        m.buscar("%Teste%");

        
        //Rodada 4: Recuperar um registro pela ID
        myMessage = new Message();
        myMessage.setId(1); //ID valida.
        m.get(myMessage);
        
        
        //Rodada 5: Alteracao de um registro. (Primeiro recupera o objeto depois altera
        //Aqui vou alterar a mensagem na conta com ID 1
        myMessage = new Message();
        myMessage.setId(1); //ID valida.
        myMessage = m.get(myMessage);
        myMessage.setMensagem("Teste de alteração da Mesagem");
        m.salvar();
        
        //Listar tudo pra ter certeza que alterou
        m.showAll();
        
    }
}


/*Teste 1 foi salvo com sucesso. ID do objeto: 11 - Na data: 07/05/2011 - 13:50
---
Cadastrando...
Teste 2 foi salvo com sucesso. ID do objeto: 12 - Na data: 07/05/2011 - 13:50
---
Buscando por Teste...
---
----- Recuperando pela ID: 1
Assunto: Teste 1
Cod Externo: 1
Mensagem: Teste de alteração da Mesagem
-----
Cadastrando...
Teste 1 foi salvo com sucesso. ID do objeto: 1 - Na data:   ---   -  -
---
Exibir tudo ...
ID: 1 - ASSUNTO: Teste 1 - MENSAGEM: Teste de alteração da Mesagem
ID: 2 - ASSUNTO: Teste 2 - MENSAGEM: Maravilha...
ID: 3 - ASSUNTO: Teste 1 - MENSAGEM: Que belo programa para o SABADÃO!!
ID: 4 - ASSUNTO: Teste 2 - MENSAGEM: Maravilha...
ID: 5 - ASSUNTO: Teste 1 - MENSAGEM: Que belo programa para o SABADÃO!!
ID: 6 - ASSUNTO: Teste 2 - MENSAGEM: Maravilha...
ID: 7 - ASSUNTO: Teste 1 - MENSAGEM: Que belo programa para o SABADÃO!!
ID: 8 - ASSUNTO: Teste 2 - MENSAGEM: Maravilha...
ID: 9 - ASSUNTO: Teste 1 - MENSAGEM: Que belo programa para o SABADÃO!!
ID: 10 - ASSUNTO: Teste 2 - MENSAGEM: Maravilha...
ID: 11 - ASSUNTO: Teste 1 - MENSAGEM: Que belo programa para o SABADÃO!!
ID: 12 - ASSUNTO: Teste 2 - MENSAGEM: Maravilha...
---
----- Recuperando pela ID: 1
Assunto: Teste 1
Cod Externo: 1
Mensagem: Teste de alteração da Mesagem
-----
----- Recuperando pela ID: 1
Assunto: Teste 1
Cod Externo: 1
Mensagem: Teste de alteração da Mesagem
-----
Cadastrando...
Teste 1 foi salvo com sucesso. ID do objeto: 1 - Na data:   ---   -  -
---
Exibir tudo ...
ID: 1 - ASSUNTO: Teste 1 - MENSAGEM: Teste de alteração da Mesagem
ID: 2 - ASSUNTO: Teste 2 - MENSAGEM: Maravilha...
ID: 3 - ASSUNTO: Teste 1 - MENSAGEM: Que belo programa para o SABADÃO!!
ID: 4 - ASSUNTO: Teste 2 - MENSAGEM: Maravilha...
ID: 5 - ASSUNTO: Teste 1 - MENSAGEM: Que belo programa para o SABADÃO!!
ID: 6 - ASSUNTO: Teste 2 - MENSAGEM: Maravilha...
ID: 7 - ASSUNTO: Teste 1 - MENSAGEM: Que belo programa para o SABADÃO!!
ID: 8 - ASSUNTO: Teste 2 - MENSAGEM: Maravilha...
ID: 9 - ASSUNTO: Teste 1 - MENSAGEM: Que belo programa para o SABADÃO!!
ID: 10 - ASSUNTO: Teste 2 - MENSAGEM: Maravilha...
ID: 11 - ASSUNTO: Teste 1 - MENSAGEM: Que belo programa para o SABADÃO!!
ID: 12 - ASSUNTO: Teste 2 - MENSAGEM: Maravilha...
--- */