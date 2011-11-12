package br.com.mailanalyzer.testes;
import br.com.mailanalyzer.dao.actions.ActionMessage;
import br.com.mailanalyzer.dao.actions.ActionProcessedMessage;
import br.com.mailanalyzer.dao.actions.ActionSubject;
import br.com.mailanalyzer.domain.ProcessedMessage;
import br.com.mailanalyzer.domain.DomainObject;
import br.com.mailanalyzer.domain.Message;
import br.com.mailanalyzer.domain.Receiver;
import br.com.mailanalyzer.domain.Subject;
import br.com.mailanalyzer.utils.cripto.Encriptador;

/**
 *
 * @author Herlayne
 * @contact fran.herlayne@gmail.com
 * @version 1.0
 * @Date 07-05-2011
 */
public class TesteProcessedMessage {
    public static void main(String[] args) throws InstantiationException, IllegalAccessException {
        
        ProcessedMessage msg = new ProcessedMessage();
        
        Subject sb = new Subject();
        
        sb.setId(2);
        
        ActionSubject actionSB = new ActionSubject();
        actionSB.getVariation(sb);
        sb.setId(2);
        sb.setCommandFlowName("testeFluxo");
        sb.setName("BBB");
        sb.setText("Um subject aqui, veja só.!");
        
        msg.setMessage("Teste de mensagem processada com sucesso!");
        msg.setSubjectIdentified(sb);
        
        actionSB.setVariation(sb);
        actionSB.Salvar();
    }

}
