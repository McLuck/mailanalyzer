package br.com.mailanalyzer.dao.actions;

import br.com.mailanalyzer.dao.MessageDAO;
import br.com.mailanalyzer.domain.Message;
import br.com.mailanalyzer.utils.Converte;
import br.com.mcluck.asynchronously.annotation.Asynchronous;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 * @author Bruno Marin Mota
 * @contact brunomarinmota@gmail.com
 * @version 1.0
 * @Date 07-05-2011
 *
 */
public class ActionMessage {

    private Message message;

    public void setMessage(Message message) {
        this.message = message;
    }
    
    public Message get(Message message){
        MessageDAO dao = new MessageDAO();
        message = dao.obter(message.getId());
        
        this.message = message;
        /*System.out.println("----- Recuperando pela ID: "+message.getId());
        System.out.println("Assunto: "+message.getAssunto());
        System.out.println("Cod Externo: "+message.getCodExterno());
        System.out.println("Mensagem: "+message.getMensagem());
        System.out.println("-----");*/
        return message;
    }

    public void salvar() {
        //System.out.println("Cadastrando...");
        if(message.getId() == 0){
            message.setDataRegistro(new java.util.Date().getTime());
        }
        MessageDAO dao = new MessageDAO();
        dao.salvar(message);
       //System.out.println(message.getAssunto() + " foi salvo com sucesso. ID do objeto: " + message.getId() + " - Na data: " + Converte.ToStringDataVisual(message.getDataRegistro()) + " - " + Converte.DateToStringTimer(message.getDataRegistro()));
       //System.out.println("---");
    }

    public void alterar() {
        //System.out.println("Alterando...");
        MessageDAO dao = new MessageDAO();
        dao.salvar(message);
        //System.out.println(message.getAssunto() + " foi salvo com sucesso. ID do objeto: " + message.getId() + " - Na data: " + Converte.ToStringDataVisual(message.getDataRegistro()) + " - " + Converte.DateToStringTimer(message.getDataRegistro()));
        //System.out.println("---");
    }

    @Asynchronous
    public void excluir() {
        //System.out.println("Excluindo...");
        MessageDAO dao = new MessageDAO();
        dao.excluir(message);
        //System.out.println(message.getAssunto()+" foi excluido com sucesso.");
        //System.out.println("---");
    }

    public List<Message> buscar(String str) {
        //System.out.println("Buscando por " + str + "...");
        MessageDAO dao = new MessageDAO();
        List<Message> lista = dao.getLikeKeyword(str);
        return lista;
    }
    
    public List<Message> showAll() {
        //System.out.println("Exibir tudo ...");
        MessageDAO dao = new MessageDAO();
        List<Message> lista = dao.obterTodos();
        
        //System.out.println("---");
        return lista;
    }
    
    public void delAll(){
        List<Message> lista = showAll();
        MessageDAO dao = new MessageDAO();
    }
}
