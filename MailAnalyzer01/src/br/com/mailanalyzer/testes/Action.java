package br.com.mailanalyzer.testes;

import br.com.mcluck.asynchronously.annotation.Asynchronous;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author McLuck
 */
public class Action {

    public void cadastrar() {
        System.out.println("Cadastrando...");
        System.out.println("---");
    }

    public void alterar() {
        System.out.println("Alterando...");
        System.out.println("---");
    }

    //@Asynchronous
    public void excluir() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Action.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Excluindo...");
        System.out.println("---");
    }

    public void buscar(String str) {
        System.out.println("Buscando por " + str + "...");
        System.out.println("---");
    }
}
