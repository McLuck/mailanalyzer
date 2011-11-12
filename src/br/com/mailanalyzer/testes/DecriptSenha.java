/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mailanalyzer.testes;

import br.com.mailanalyzer.utils.cripto.Encriptador;

/**
 *
 * @author McLuck
 */
public class DecriptSenha {

    public static void main(String[] args) {
        String senha = "ACeADSADlACTACfADeADsACZACnADo";
        Encriptador enc = new Encriptador("manalyzertest@gmail.com");
        senha = enc.decriptar(senha);
        System.out.println(senha);
    }
}
