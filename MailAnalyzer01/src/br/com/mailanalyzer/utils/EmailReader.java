package br.com.mailanalyzer.utils;

import br.com.mailanalyzer.log.L;
import br.com.mailanalyzer.main.Config;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Address;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.InternetAddress;

/**
 *
 * @author lucasisrael
 */
public class EmailReader {
    public static final String TAG = "Leitor de emails";

    /*public static void main(String[] args) {
    // TODO Auto-generated method stub
    try {
    String popServer = "pop.gmail.com";
    String popUser = "manalyzertest@gmail.com";
    String popPassword = "1qw23er45t";
    
    System.out.println("java EmailReader "
    + popServer + " " + popUser + " " + popPassword);
    
    receive(popServer, popUser, popPassword);
    } catch (Exception ex) {
    System.out.println("Usage: EmailReader"
    + " popServer popUser popPassword");
    }
    
    System.exit(0);
    
    }*/
    private String host = "pop.gmail.com";
    private String autenticador = "true";
    private String debug = "true";
    private String charset = "UTF-8";
    private String port = "995";
    private String starttls = "true";
    private String socketport = "995";
    private String fallback = "true";
    private String socketclass = "javax.net.ssl.SSLSocketFactory";
    private String protocolo = "pop3";
    public static boolean IS_SESSION_OPENED = false;

    public br.com.mailanalyzer.domain.Message[] receive(String popServer, String popUser, String popPassword) throws Exception {

        br.com.mailanalyzer.domain.Message[] mensgs = null;
        Store store = null;
        Folder folder = null;

        while (IS_SESSION_OPENED) {
            //Sessao aberta, aguarda
            L.d(TAG, this, "Sessao mail aberta, aguardando encerramento da sessao para busca de email.");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(EmailReader.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        IS_SESSION_OPENED = true;
        Session session = null;
        try {
            // -- Get hold of the default session --
            Properties prop = new Properties();
            prop.put(getHost(), popServer);

            prop.put("mail.pop3.auth", getAutenticador());
            prop.put("mail.debug", getDebug());
            prop.put("mail.pop3.debug", getDebug());
            prop.put("mail.mime.charset", getCharset());
            prop.put("mail.pop3.port", getPort());
            prop.put("mail.pop3.starttls.enable", getStarttls());
            prop.put("mail.pop3.socketFactory.port", getSocketport());
            prop.put("mail.pop3.socketFactory.fallback", getFallback());
            prop.put("mail.pop3.socketFactory.class", getSocketclass());



            session = Session.getInstance(prop, null);
            boolean adebug = false;
            try {
                adebug = Boolean.parseBoolean(debug);
            } catch (Exception e) {
                adebug = false;
            }
            session.setDebug(adebug);

            // -- Get hold of a POP3 message store, and connect to it --
            //store = session.getStore("pop3");
            store = session.getStore(getProtocolo());
            store.connect(popServer, 995, popUser, popPassword);
            //store.connect(popServer, popUser, popPassword);

            // -- Try to get hold of the default folder --
            folder = store.getDefaultFolder();
            if (folder == null) {
                throw new Exception("No default folder");
            }

            // -- ...and its INBOX --
            folder = folder.getFolder("INBOX");
            if (folder == null) {
                throw new Exception("No POP3 INBOX");
            }

            // -- Open the folder for read only --
            folder.open(Folder.READ_ONLY);

            // -- Get the message wrappers and process them --
            Message[] msgs = folder.getMessages();

            //System.out.println("ava au.com.covermore.EmailReader"
            //      + " msgs " + msgs.length);
            mensgs = new br.com.mailanalyzer.domain.Message[msgs.length];
            for (int msgNum = 0; msgNum < msgs.length; msgNum++) {
                //printMessage(msgs[msgNum]);
                br.com.mailanalyzer.domain.Message m = new br.com.mailanalyzer.domain.Message();
                m.setAssunto(msgs[msgNum].getSubject());


                //seta a origem
                String from = null;//((InternetAddress) msgs[msgNum].getFrom()[0]).getPersonal();
                if (from == null) {
                    from = "";
                    boolean first = true;
                    for (Address a : msgs[msgNum].getFrom()) {
                        if (!first) {
                            from = from.concat(",");
                        } else {
                            first = false;
                        }
                        from = from.concat(((InternetAddress) a).getAddress());
                    }

                    for (Address a : msgs[msgNum].getAllRecipients()) {
                        if (!first) {
                            from = from.concat(",");
                        } else {
                            first = false;
                        }
                        from = from.concat(((InternetAddress) a).getAddress());
                    }
                    //from = ((InternetAddress) msgs[msgNum].getFrom()[0]).getAddress();
                }

                m.setOrigem(from);


                //m.setMensagem(msgs[msgNum].get);
                Part messagePart = msgs[msgNum];
                Object content = messagePart.getContent();

                // -- or its first body part if it is a multipart message --
                if (content instanceof Multipart) {
                    messagePart = ((Multipart) content).getBodyPart(0);
                    //System.out.println("[ Multipart Message ]");
                }

                // -- Get the content type --
                String contentType = messagePart.getContentType();

                // -- If the content is plain text, we can print it --
                //System.out.println("CONTENT:" + contentType);

                if (contentType.startsWith("text/plain") || contentType.startsWith("text/html")) {
                    InputStream is = messagePart.getInputStream();

                    BufferedReader reader = new BufferedReader(new InputStreamReader(is));
                    String thisLine = reader.readLine();
                    StringBuffer sb = new StringBuffer();
                    while (thisLine != null) {
                        sb.append(thisLine);
                        thisLine = reader.readLine();
                    }
                    m.setMensagem(sb.toString());
                }
                m.setCodExterno(String.valueOf(msgs[msgNum].getMessageNumber()));
                mensgs[msgNum] = m;
                //processMessage(msgs[msgNum]);
            }

        } catch (Exception ex) {
            L.e(TAG, this, "Erro ao receber mensagem.", ex);
            throw ex;
        } finally {
            // -- Close down nicely --
            try {
                if (folder != null) {
                    folder.close(false);
                }
                if (store != null) {
                    store.close();
                }
                if (session != null) {
                    session = null;
                }
            } catch (Exception ex2) {
                ex2.printStackTrace();
            }
            IS_SESSION_OPENED = false;
        }
        return mensgs;


    }

    /**
     * this method will print the message
     * @param message
     */
    public static void printMessage(Message message) {
        if(!Config.LOG.PRINT_IN_CONSOLE){
            return;
        }
        try {
            // Get the header information
            String from = ((InternetAddress) message.getFrom()[0]).getPersonal();
            if (from == null) {
                from = ((InternetAddress) message.getFrom()[0]).getAddress();
            }
            System.out.println("FROM: " + from);

            String subject = message.getSubject();
            System.out.println("SUBJECT: " + subject);

            //String dateTime = message.getSentDate().toString();
            System.out.println("DATE: " + message.getSentDate());

            // -- Get the message part (i.e. the message itself) --
            Part messagePart = message;
            Object content = messagePart.getContent();

            // -- or its first body part if it is a multipart message --
            if (content instanceof Multipart) {
                messagePart = ((Multipart) content).getBodyPart(0);
                System.out.println("[ Multipart Message ]");
            }

            // -- Get the content type --
            String contentType = messagePart.getContentType();

            // -- If the content is plain text, we can print it --
            System.out.println("CONTENT:" + contentType);

            if (contentType.startsWith("text/plain") || contentType.startsWith("text/html")) {
                InputStream is = messagePart.getInputStream();

                BufferedReader reader = new BufferedReader(new InputStreamReader(is));
                String thisLine = reader.readLine();

                while (thisLine != null) {
                    System.out.println(thisLine);
                    thisLine = reader.readLine();
                }
            }

            System.out.println("-----------------------------");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * this method will process an email message and write
     * relevant contents into the xml file
     * @param message
     */
    private void processMessage(Message message) {
        try {
            // -- Get the message part (i.e. the message itself) --
            Part messagePart = message;
            Object content = messagePart.getContent();

            // -- or its first body part if it is a multipart message --
            if (content instanceof Multipart) {
                messagePart = ((Multipart) content).getBodyPart(0);
            }

            // -- Get the content type --
            String contentType = messagePart.getContentType();

            if (contentType.startsWith("text/plain") || contentType.startsWith("text/html")) {
                InputStream is = messagePart.getInputStream();

                BufferedReader reader = new BufferedReader(new InputStreamReader(is));


                // now read the message into a List - each message line is a list item
                // a List will be easier to manipulate and its indexed
                // remove blank lines at the same time
                List msgList = new LinkedList();

                String thisLine = reader.readLine();
                while (thisLine != null) {
                    if (thisLine.trim().length() > 0) {
                        msgList.add(thisLine);
                    }
                    thisLine = reader.readLine();
                }

            }
        } catch (Exception x) {
            x.printStackTrace();
        }

    }

    /**
     * @return the host
     */
    public String getHost() {
        return host;
    }

    /**
     * @param host the host to set
     */
    public void setHost(String host) {
        this.host = host;
    }

    /**
     * @return the autenticador
     */
    public String getAutenticador() {
        return autenticador;
    }

    /**
     * @param autenticador the autenticador to set
     */
    public void setAutenticador(String autenticador) {
        this.autenticador = autenticador;
    }

    /**
     * @return the debug
     */
    public String getDebug() {
        return debug;
    }

    /**
     * @param debug the debug to set
     */
    public void setDebug(String debug) {
        this.debug = debug;
    }

    /**
     * @return the charset
     */
    public String getCharset() {
        return charset;
    }

    /**
     * @param charset the charset to set
     */
    public void setCharset(String charset) {
        this.charset = charset;
    }

    /**
     * @return the port
     */
    public String getPort() {
        return port;
    }

    /**
     * @param port the port to set
     */
    public void setPort(String port) {
        this.port = port;
    }

    /**
     * @return the starttls
     */
    public String getStarttls() {
        return starttls;
    }

    /**
     * @param starttls the starttls to set
     */
    public void setStarttls(String starttls) {
        this.starttls = starttls;
    }

    /**
     * @return the socketport
     */
    public String getSocketport() {
        return socketport;
    }

    /**
     * @param socketport the socketport to set
     */
    public void setSocketport(String socketport) {
        this.socketport = socketport;
    }

    /**
     * @return the fallback
     */
    public String getFallback() {
        return fallback;
    }

    /**
     * @param fallback the fallback to set
     */
    public void setFallback(String fallback) {
        this.fallback = fallback;
    }

    /**
     * @return the socketclass
     */
    public String getSocketclass() {
        return socketclass;
    }

    /**
     * @param socketclass the socketclass to set
     */
    public void setSocketclass(String socketclass) {
        this.socketclass = socketclass;
    }

    /**
     * @return the protocolo
     */
    public String getProtocolo() {
        return protocolo;
    }

    /**
     * @param protocolo the protocolo to set
     */
    public void setProtocolo(String protocolo) {
        this.protocolo = protocolo;
    }
}
