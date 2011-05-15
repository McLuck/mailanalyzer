/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mailanalyzer.utils;

import br.com.mailanalyzer.log.Log;
import br.com.mailanalyzer.main.Config;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

/**
 *
 * @author lucasisrael
 */
public class SendMail {

    private String mailSMTPServer;
    private String mailSMTPServerPort;
    private String user, password;
    private boolean usingSSL;
    private String copy;
    private String message;
    private String subject;
    private String from, to;

    /**
     * Sera setado as configuracoes padroes para o gmail.<br>
     * Server SMTP = smtp.gmail.com<br>
     * Porta SMTP = 465
     */
    public SendMail() { //Para o GMAIL 
        mailSMTPServer = "smtp.gmail.com";
        mailSMTPServerPort = "465";
        usingSSL = true;
    }

    /**
     * Entrar todos os dados do servidor SMTP desejado
     * @param mailSMTPServer
     * @param mailSMTPServerPort
     * @param ssl
     * @param user
     * @param senha 
     */
    public SendMail(String mailSMTPServer, String mailSMTPServerPort, boolean ssl, String user, String senha) {
        this.mailSMTPServer = mailSMTPServer;
        this.mailSMTPServerPort = mailSMTPServerPort;
        this.usingSSL = ssl;
        this.user = user;
        this.password = senha;
    }

    public void sendMail() {
        Session session = null;
        if (usingSSL) {
            Properties props = new Properties();

            if (Config.IS_PROXY) {
                props.setProperty("proxySet", "true");
                props.setProperty("socksProxyHost", Config.PROXY_ADDRESS); // IP do Servidor Proxy
                props.setProperty("socksProxyPort", String.valueOf(Config.PROXY_PORT));  // Porta do servidor Proxy
            }


            props.put("mail.transport.protocol", "smtp"); //define protocolo de envio como SMTP
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", getMailSMTPServer()); //server SMTP do GMAIL
            props.put("mail.smtp.auth", "true"); //ativa autenticacao
            props.put("mail.smtp.user", getFrom()); //usuario ou seja, a conta que esta enviando o email (tem que ser do GMAIL)
            props.put("mail.debug", "false");
            props.put("mail.smtp.port", getMailSMTPServerPort()); //porta
            props.put("mail.smtp.socketFactory.port", getMailSMTPServerPort()); //mesma porta para o socket
            props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            props.put("mail.smtp.socketFactory.fallback", "false");

            //Cria um autenticador que sera usado a seguir
            SimpleAuth auth = null;
            auth = new SimpleAuth(user, password);

            //Session - objeto que ira realizar a conexão com o servidor
		/*Como há necessidade de autenticação é criada uma autenticacao que
             * é responsavel por solicitar e retornar o usuário e senha para 
             * autenticação */
            
            
            //Aguarda sessao disponivel.
            session = Session.getInstance(props, auth);
            session.setDebug(false); //Habilita o LOG das ações executadas durante o envio do email

            //Objeto que contém a mensagem
            Message msg = new MimeMessage(session);

            try {
                //Setando o destinatário
                msg.setRecipient(Message.RecipientType.TO, new InternetAddress(getTo()));

                //Adicionando copias
                try {
                    if (copy != null) {
                        if (copy.length() > 3) {
                            String[] ems = copy.split(",");
                            for (String em : ems) {
                                em = em.replace(" ", "");
                                msg.addRecipient(Message.RecipientType.CC, new InternetAddress(em));
                            }
                        }
                    }
                } catch (Exception e) {
                    Log.d(this.getClass().getSimpleName(), e);
                }

                //Setando a origem do email
                msg.setFrom(new InternetAddress(getFrom()));
                //Setando o assunto
                msg.setSubject(getSubject());
                //Setando o conteúdo/corpo do email
                msg.setContent(getMessage(), "text/html");

            } catch (Exception e) {
                System.out.println(">> Erro: Completar Mensagem");
                e.printStackTrace();
            }

            //Objeto encarregado de enviar os dados para o email
            Transport tr;
            try {
                tr = session.getTransport("smtp"); //define smtp para transporte
			/*
                 *  1 - define o servidor smtp
                 *  2 - seu nome de usuario do gmail
                 *  3 - sua senha do gmail
                 */
                tr.connect(getMailSMTPServer(), user, password);
                msg.saveChanges(); // don't forget this
                //envio da mensagem
                tr.sendMessage(msg, msg.getAllRecipients());
                tr.close();
            } catch (Exception e) {
                // TODO Auto-generated catch block
                Log.d(this.getClass().getSimpleName(), e);
                //e.printStackTrace();
            }



        } else {
            //Caso o server SMTP nao use SSL, enviar email de forma simples. Sem autenticacao.
            SimpleEmail email = new SimpleEmail();

            try {
                email.setDebug(false);
                email.setHostName(mailSMTPServer);
                email.setAuthentication(user, password);
                email.setSSL(true);
                email.addTo(getTo()); //pode ser qualquer um email
                email.setFrom(user); //aqui necessita ser o email que voce fara a autenticacao
                email.setSubject(getSubject());
                email.setMsg(getMessage());
                email.send();

            } catch (EmailException e) {
                Log.d(this.getClass().getSimpleName(), e);
            }
        }
        session = null;
        EmailReader.IS_SESSION_OPENED = false;
    }

    /**
     * @return the mailSMTPServer
     */
    public String getMailSMTPServer() {
        return mailSMTPServer;
    }

    /**
     * @param mailSMTPServer the mailSMTPServer to set
     */
    public void setMailSMTPServer(String mailSMTPServer) {
        this.mailSMTPServer = mailSMTPServer;
    }

    /**
     * @return the mailSMTPServerPort
     */
    public String getMailSMTPServerPort() {
        return mailSMTPServerPort;
    }

    /**
     * @param mailSMTPServerPort the mailSMTPServerPort to set
     */
    public void setMailSMTPServerPort(String mailSMTPServerPort) {
        this.mailSMTPServerPort = mailSMTPServerPort;
    }

    /**
     * @return the user
     */
    public String getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(String user) {
        this.user = user;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the usingSSL
     */
    public boolean isUsingSSL() {
        return usingSSL;
    }

    /**
     * @param usingSSL the usingSSL to set
     */
    public void setUsingSSL(boolean usingSSL) {
        this.usingSSL = usingSSL;
    }

    /**
     * @return the copy
     */
    public String getCopy() {
        return copy;
    }

    /**
     * Pode-se enviar email como copia para mais de uma pessoa<br>
     * Este parametro deve ser setado com os emails desejado.<br>
     * Os emails entrados devem ser separados por virgula<br>
     * 
     * @param copy the copy to set
     */
    public void setCopy(String copy) {
        this.copy = copy;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * @return the subject
     */
    public String getSubject() {
        return subject;
    }

    /**
     * @param subject the subject to set
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }

    /**
     * @return the from
     */
    public String getFrom() {
        return from;
    }

    /**
     * @param from the from to set
     */
    public void setFrom(String from) {
        this.from = from;
    }

    /**
     * @return the to
     */
    public String getTo() {
        return to;
    }

    /**
     * @param to the to to set
     */
    public void setTo(String to) {
        this.to = to;
    }
    
    /**
     * Adiciona copia no email
     * @param email 
     */
    public void addCopy(String email){
        if(copy==null){
            copy="";
        }
        if(!copy.equals("")){
            copy = copy.concat(",");
            copy = copy.concat(email);
        }else{
            copy = email;
        }
    }
}
