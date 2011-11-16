/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.mailanalyzer.exemplo.commands;

import br.com.mailanalyzer.commands.SubjectFoundCommand;
import br.com.mailanalyzer.dao.HtmlCodeDAO;
import br.com.mailanalyzer.domain.ActiveReceiver;
import br.com.mailanalyzer.domain.HtmlCode;
import br.com.mailanalyzer.domain.Message;
import br.com.mailanalyzer.domain.ProcessedMessage;
import br.com.mailanalyzer.domain.Receiver;
import br.com.mailanalyzer.domain.Subject;
import br.com.mailanalyzer.log.L;
import br.com.mailanalyzer.main.Base;
import br.com.mailanalyzer.main.Config;
import br.com.mailanalyzer.utils.SendMail;
import br.com.mailanalyzer.utils.cripto.Encriptador;

/**
 * Classe de exemplo de uso da API.
 * Uma classe parecida com esta devera ser criada na aplicação que usa a API
 * @author Lucas Israel
 */
public class CommandAssuntoEncontrado extends SubjectFoundCommand{
    public static final String TAG = "Comando de assunto encontrado";
    public CommandAssuntoEncontrado(){
        super(new ProcessedMessage());
    }

    /*
     * FAZENDO TUDO EM UMA CLASSE
     * Apenas para exemplo, em um cenario real, o ideal eh criar uma classe de comando para
     * cada novo assunto cadastrado.
     */
    
    @Override
    public void run() {
        //HTML PEDRO

        //Recuperando mensagem alocada no analisador
        Message msg = (Message)getParameters().get("msg");
        Receiver rec = (Receiver)getParameters().get("receiver");
        Subject assunto = (Subject)getParameters().get("assunto");

        //Solucao improvisada para atender todos os assuntos cadastrados
        //Usada somente neste exemplo. Nao fazer isto em um cenario real.
        HtmlCodeDAO hdao = new HtmlCodeDAO();
        
        //Default
        HtmlCode html = hdao.obter(7);
        String corpo = html.getCodigo();

        if(assunto.getName().toLowerCase().trim().equals("portal do aluno")){
            html = hdao.obter(5);
        }else if(assunto.getName().toLowerCase().trim().equals("matricula do aluno'")){
            html = hdao.obter(2);
        }else if(assunto.getName().toLowerCase().trim().equals("grade horá‡ria do aluno")){
            html = hdao.obter(6);
        }else if(assunto.getName().toLowerCase().trim().equals("histórico escolar do aluno'")){
            html = hdao.obter(3);
        }
        hdao.close();
        corpo = corpo.replace("#TAG_REPROVA#", Base.TAG_PARA_IGNORAR_MENSAGEM);
        corpo = corpo.replace("#MSG_RESPOSTA#", html.getCodigo());
        corpo = corpo.replace("#MSG_RECEBIDA#", "<br/><br/><br/><br/>"+msg.getMensagem());


        SendMail sm = new SendMail();
        ActiveReceiver a = (ActiveReceiver)rec;
        sm.setFrom(a.getUsuario());
        sm.setMailSMTPServer(Config.SERVER_SMTP_ADDRESS);
        sm.setMailSMTPServerPort(String.valueOf(Config.PORT_SERVER_SMTP));
        sm.setUser(a.getUsuario());

        String senha = a.getSenha();
        Encriptador enc = new Encriptador(a.getUsuario());
        senha = enc.decriptar(senha);
        sm.setPassword(senha);

        String to = msg.getOrigem();
        //System.out.println(to);
        if(to.contains(",")){
            String emails[] = to.split(",");
            to = emails[0];
            for(int i=1;i<emails.length;i++){
                if(emails[i].equals(a.getUsuario())){
                    continue;
                }
                sm.addCopy(emails[i]);
            }
        }

        sm.setTo(to);
        sm.setUsingSSL(true);
        sm.setSubject("RE-"+msg.getAssunto());
        sm.setMessage(corpo);
        sm.sendMail();
        L.d(TAG, this, "Enviou mensagem: "+corpo);
    }
}
