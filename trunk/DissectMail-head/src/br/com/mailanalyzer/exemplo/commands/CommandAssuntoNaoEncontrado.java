/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.mailanalyzer.exemplo.commands;

import br.com.mailanalyzer.commands.SubjectNotFoundCommand;
import br.com.mailanalyzer.dao.AssuntosPendentesDAO;
import br.com.mailanalyzer.domain.AssuntosPendentes;
import br.com.mailanalyzer.domain.Message;
import br.com.mailanalyzer.domain.Receiver;
import br.com.mailanalyzer.domain.Subject;

/**
 *
 * @author McLuck
 */
public class CommandAssuntoNaoEncontrado extends SubjectNotFoundCommand{

    @Override
    public void run() {
        //Recuperando mensagem alocada no analisador
        Message msg = (Message)getParameters().get("msg");
        Receiver rec = (Receiver)getParameters().get("receiver");

        //Special case. (casos de erro no processamento do assunto encontrado,
        //mesmo sendo localizado, ele ira executar este comando.
        //Entao, se assunto nao for nulo, houve erro ao processar seu comando.
        Object assunto = getParameters().get("assunto");
        if(assunto!=null){
            Subject sub = (Subject)assunto;
        }


        //Cenario teste, salva para assuntos pendentes para ser analisado posteriormente
        AssuntosPendentes ass = new AssuntosPendentes();
        AssuntosPendentesDAO adao = new AssuntosPendentesDAO();
        ass.setMessage(msg);
        ass.setResolvido(false);
        adao.salvar(ass);
        adao.commit();
        adao.close();
    }

}
