package br.com.mailanalyzer.testes;

import br.com.mailanalyzer.dao.ActiveReceiverDAO;
import br.com.mailanalyzer.domain.ActiveReceiver;
import br.com.mailanalyzer.utils.Converte;
import br.com.mcluck.asynchronously.annotation.Asynchronous;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 * @author Lucas Israel
 * @contact mcluck.ti@gmail.com
 * @version 1.0
 * @Date 21-04-2011
 *
 */
public class ActionActiveReceiver {

    private ActiveReceiver receive;

    public void setReceive(ActiveReceiver receiv) {
        this.receive = receiv;
    }
    
    public ActiveReceiver get(ActiveReceiver receiv){
        ActiveReceiverDAO dao = new ActiveReceiverDAO();
        receiv = dao.obter(receiv.getId());
        
        this.receive = receiv;
        System.out.println("----- Recuperando pela ID: "+receive.getId());
        System.out.println("Nome: "+receive.getNome());
        System.out.println("Host: "+receive.getHost());
        System.out.println("Usuario: "+receive.getUsuario());
        System.out.println("Senha: "+receive.getSenha());
        System.out.println("-----");
        return receive;
    }

    public void salvar() {
        System.out.println("Cadastrando...");
        ActiveReceiverDAO dao = new ActiveReceiverDAO();
        dao.salvar(receive);
        System.out.println(receive.getNome() + " foi salvo com sucesso. ID do objeto: " + receive.getId() + " - Na data: " + Converte.ToStringDataVisual(receive.getDataRegistro()) + " - " + Converte.DateToStringTimer(receive.getDataRegistro()));
        System.out.println("---");
    }

    public void alterar() {
        System.out.println("Alterando...");
        ActiveReceiverDAO dao = new ActiveReceiverDAO();
        dao.salvar(receive);
        System.out.println(receive.getNome() + " foi salvo com sucesso. ID do objeto: " + receive.getId() + " - Na data: " + Converte.ToStringDataVisual(receive.getDataRegistro()) + " - " + Converte.DateToStringTimer(receive.getDataRegistro()));
        System.out.println("---");
    }

    @Asynchronous
    public void excluir() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            Logger.getLogger(ActionActiveReceiver.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Excluindo...");
        ActiveReceiverDAO dao = new ActiveReceiverDAO();
        dao.excluir(receive);
        System.out.println(receive.getNome()+" foi excluido com sucesso.");
        System.out.println("---");
    }

    public void buscar(String str) {
        System.out.println("Buscando por " + str + "...");
        ActiveReceiverDAO dao = new ActiveReceiverDAO();
        List<ActiveReceiver> lista = dao.getLikeName(str);
        for(ActiveReceiver a : lista){
            System.out.println("ID: "+a.getId()+" - Nome: "+a.getNome()+" - SENHA: "+a.getSenha());
        }
        System.out.println("---");
    }
    
    public void showAll() {
        System.out.println("Exibir tudo ...");
        ActiveReceiverDAO dao = new ActiveReceiverDAO();
        List<ActiveReceiver> lista = dao.obterTodos();
        for(ActiveReceiver a : lista){
            System.out.println("ID: "+a.getId()+" - Nome: "+a.getNome()+" - SENHA: "+a.getSenha());
        }
        System.out.println("---");
    }
}
