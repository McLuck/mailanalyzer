/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.mailanalyzer.testes;

/**
 *
 * @author McLuck
 */
public class Teste {
    public static void main(String[]args) throws InstantiationException, IllegalAccessException{
        Action a = (Action)br.com.mcluck.asynchronously.Utils.Factory.getInstance(Action.class);

        a.excluir();
        a.alterar();
        a.buscar("Fran;Gui;Marin;Lobo");
        a.cadastrar();
        
    }
}
