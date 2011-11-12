package br.com.mailanalyzer.dao.actions;

import br.com.mailanalyzer.dao.MessageDAO;
import br.com.mailanalyzer.dao.ProcessedMessageDAO;
import br.com.mailanalyzer.domain.Message;
import br.com.mailanalyzer.domain.ProcessedMessage;
import br.com.mailanalyzer.domain.Subject;
import br.com.mailanalyzer.utils.Converte;
import br.com.mcluck.asynchronously.annotation.Asynchronous;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Herlayne
 * @contact fran.herlayne@gmail.com
 * @version 1.0
 * @Date 07-05-2011
 *
 */
public class ActionProcessedMessage {

    private ProcessedMessage message;

    /**
     * @return the message
     */
    public ProcessedMessage getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(ProcessedMessage message) {
        this.message = message;
    }

    public void Salvar() {
        if(message.getId()==0){
            message.setDataRegistro(new java.util.Date().getTime());
        }
        ProcessedMessageDAO dao = new ProcessedMessageDAO();
        dao.salvar(message);
    }

    public void excluir() {
        ProcessedMessageDAO dao = new ProcessedMessageDAO();
        dao.excluir(message);
    }

    public List<ProcessedMessage> buscar(String x) {
        ProcessedMessageDAO dao = new ProcessedMessageDAO();
        List<ProcessedMessage> lista = dao.getLikeName(x);
        return lista;
    }

    public List<ProcessedMessage> showAll() {
        ProcessedMessageDAO dao = new ProcessedMessageDAO();
        List<ProcessedMessage> lista = dao.obterTodos();
        return lista;

    }

    public List<ProcessedMessage> getBySubject(Integer idSubject){
        ProcessedMessageDAO dao = new ProcessedMessageDAO();
        return dao.getBySubject(idSubject);
    }
}
