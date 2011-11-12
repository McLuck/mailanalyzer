/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.mailanalyzer.main;

import br.com.mailanalyzer.exemplo.commands.CommandAssuntoNaoEncontrado;
import br.com.mailanalyzer.fluxo.FluxoTeste;

/**
 *
 * @author McLuck
 */
public class Teste2 {
    public static void main(String[] args) {
        //Seta para ambiente de teste. (Isto ira habilitar a resposta automatica da mensagem)
        Config.IS_TEST_ENVIRONMENT = false;

        //Seta modo debug. Isto ira habilitar o andamento dos processos no console.
        Config.LOG.PRINT_IN_CONSOLE = true;
        //Seta acao default para assunto nao encontrado
        Base.NOT_FOUND_COMMAND = new CommandAssuntoNaoEncontrado();

        FluxoTeste flow = new FluxoTeste();
        flow.init();
    }
}
