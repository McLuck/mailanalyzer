package br.com.mailanalyzer.commands;

import br.com.mailanalyzer.dao.actions.ActionActiveReceiver;
import br.com.mailanalyzer.dao.actions.ActionMessage;
import br.com.mailanalyzer.domain.ActiveReceiver;
import br.com.mailanalyzer.domain.Message;
import br.com.mailanalyzer.log.Log;
import br.com.mailanalyzer.main.Base;
import br.com.mailanalyzer.main.Config;
import br.com.mailanalyzer.utils.SendMail;
import br.com.mailanalyzer.utils.Utils;
import br.com.mailanalyzer.utils.cripto.Encriptador;

/**
 *
 * @author Lucas Israel
 * @contact mcluck.ti@gmail.com
 * @version 1.0
 * @Date 25-04-2011
 *
 */
public class MensagemTratadaCommand extends CommandFluxo {

    @Override
    public void run() {
        Log.d(this.getClass().getSimpleName(), "Executando...");
        
        ActionMessage dao = new ActionMessage();
        Message m = (Message) this.firstObject;
        String msg = (String) getParameters().get(Base.FIELD_FILTRO_ORTOGRAFIA);
        
        m.setMensagem(msg);

        dao.setMessage(m);
        dao.alterar();
        Utils.printMessage(m);
        Log.d(this.getClass().getSimpleName(), "Mensagem recebida, tratada e salva. Preparar análise de mensagem.");

        
        if(Config.IS_TEST_ENVIRONMENT){
            ActiveReceiver a = (ActiveReceiver)getParameters().get(Base.FIELD_ACTIVE_RECEIVER);
            if(a==null){
                a = Base.ACTIVE_SERVICES[0].getReceiver();
            }
            debugEnvironment(m, a);
        }
        
        Log.d(this.getClass().getSimpleName(), "Finalizado.");
    }

    private void debugEnvironment(Message m, ActiveReceiver a) {
        Log.d(this.getClass().getSimpleName(), "Enviando mensagem de retorno. AMBIENTE DE TESTE ATIVO.");
        String corpo = "<p>Olá, você enviou um email para a <em><strong>API Mail Analyzer.</strong></em></p>"
                + "<p>Muito obrigado por usar nosso sistema.</p>"
                + "<p>A partir de agora, seus emails serão respondidos pelo Mail Analyzer automaticamente.</p>"
                + "<p>Caso queira que este email seja tratado pelo seu dono legítimo e não pela API, por favor inclua a tag #NOBOT# no assunto do email.</p>"
                + "<p>&nbsp;</p>"
                + "<p>Como um programa de computador, tratei sua mensagem e interpretei da seguinte forma:</p>"
                + "<div style='border:solid 2px; width:300px; height:100px; overflow:auto' id='suamensagem'>"
                + "#SUAMSG#"
                + "</div>"
                + "<br/><br/>"
                + "<p>Obrigado!</p>"
                + "<strong><em>Mail Analyzer </em></strong>";
        
        StringBuffer sb = new StringBuffer();
        sb.append("Assunto: ");
        sb.append(m.getAssunto());
        sb.append("<br />");
        sb.append("Mensagem: ");
        sb.append(m.getMensagem());
        sb.append("<br />");

        corpo = corpo.replace("#SUAMSG#", sb.toString());
        corpo = corpo.replace("#NOBOT#", Config.TAG_TO_IGNORE_MESSAGE);
        
        //Log.d(this.getClass().getSimpleName(),"Corpo da mensagem: " +corpo);
        SendMail sm = new SendMail();
        sm.setFrom(a.getUsuario());
        sm.setMailSMTPServer(Config.SERVER_SMTP_ADDRESS);
        sm.setMailSMTPServerPort(String.valueOf(Config.PORT_SERVER_SMTP));
        sm.setUser(a.getUsuario());
        
        String senha = a.getSenha();
        Encriptador enc = new Encriptador(a.getUsuario());
        senha = enc.decriptar(senha);
        sm.setPassword(senha);
        
        String to = m.getOrigem();
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
        sm.setSubject("RE-MAnalyzer:"+m.getAssunto());
        sm.setMessage(corpo);
        sm.sendMail();
        Log.d(this.getClass().getSimpleName(), "Enviou mensagem de retorno. AMBIENTE DE TESTE ATIVO.");
    }
}
