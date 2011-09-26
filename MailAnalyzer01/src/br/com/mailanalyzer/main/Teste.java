package br.com.mailanalyzer.main;

import br.com.mailanalyzer.analise2.GerenciamentoAnalisador;
import br.com.mailanalyzer.dao.AssuntosPendentesDAO;
import br.com.mailanalyzer.domain.ActiveReceiver;
import br.com.mailanalyzer.domain.AssuntosPendentes;
import br.com.mailanalyzer.exemplo.commands.CommandAssuntoNaoEncontrado;
import br.com.mailanalyzer.fluxo.MainFluxo;
import br.com.mailanalyzer.grafico.FormTermos;
import br.com.mailanalyzer.grafico.FormWizard;
import br.com.mailanalyzer.grafico.ListaPendentes;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author Lucas Israel
 * @contact mcluck.ti@gmail.com
 * @version 1.0
 * @Date 15-05-2011
 *
 */
public class Teste implements Runnable {

    public static void main(String[] args) {
        //Seta para ambiente de teste. (Isto ira habilitar a resposta automatica da mensagem)
        Config.IS_TEST_ENVIRONMENT = false;

        //Seta modo debug. Isto ira habilitar o andamento dos processos no console.
        Config.DEBUG_MODE = true;

        //Seta proxy. Caso esteja utilizando PROXY, obrigatorio setar as configs de Proxy.
        Config.IS_PROXY = false;
        //Config.PROXY_ADDRESS = "10.12.128.13";
        //Config.PROXY_PORT = 8080;
        //Config.PROXY_SCHEME = "http";

        ActiveReceiver receive = new ActiveReceiver();
        receive.setNome("Email teste");
        receive.setLastID(0);
        receive.setOtype(Base.RECEIVER_TYPE_EMAIL);
        receive.setPort(465);
        receive.setSsl(true);
        receive.setUsuario("manalyzertest@gmail.com");
        receive.setHost("pop.gmail.com");
        receive.setSenha("1qw23er45t");

        //Seta acao default para assunto nao encontrado
        Base.NOT_FOUND_COMMAND = new CommandAssuntoNaoEncontrado();

        Config.Register.ADD_RECEIVER(receive);

        GerenciamentoAnalisador.load();

        //Inicia servico Base. O Fluxo Principal
        Teste t = new Teste();
        t.run();
        systray();
    }

    public void run() {
        MainFluxo main = new MainFluxo();
        main.init();
    }

    public static void systray() {
        if (!SystemTray.isSupported()) {
            System.out.println("SystemTray is not supported");
            return;
        }
        final PopupMenu popup = new PopupMenu();
        final TrayIcon trayIcon =
                new TrayIcon(createImage("msg.gif", null));
        final SystemTray tray = SystemTray.getSystemTray();

        // Create a pop-up menu components
        MenuItem assuntosPends = new MenuItem("Ver Assuntos Nao Encontrados");
        MenuItem pararServico = new MenuItem("Sair");

        Menu displayMenu = new Menu("Add");
        MenuItem agregacao = new MenuItem("Nova Agregacao de palavras");
        MenuItem eliminatoria = new MenuItem("Nova Composicao Eliminatoria");
        MenuItem substituicaoTerm = new MenuItem("Nova Substituicao de palavras");
        MenuItem ctxMandatorio = new MenuItem("Nova Composicao Contx Mandatorio");




        //Add components to pop-up menu
        popup.add(assuntosPends);

        popup.addSeparator();
        popup.add(displayMenu);
        displayMenu.add(substituicaoTerm);
        displayMenu.add(agregacao);
        displayMenu.add(eliminatoria);
        displayMenu.add(ctxMandatorio);

        popup.addSeparator();
        popup.add(pararServico);


        trayIcon.setPopupMenu(popup);

        try {
            tray.add(trayIcon);
        } catch (AWTException e) {
            System.out.println("TrayIcon could not be added.");
        }

        assuntosPends.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                AssuntosPendentesDAO adao = new AssuntosPendentesDAO();
                java.util.List<AssuntosPendentes> lista = adao.obterTodos();
                ListaPendentes form = new ListaPendentes(lista);
                form.setVisible(true);
                adao.close();
            }
        });

        pararServico.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        agregacao.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                FormWizard form = new FormWizard(FormWizard.TIPO_AGREGACAO);
                form.setVisible(true);
            }
        });

        ctxMandatorio.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                FormWizard form = new FormWizard(FormWizard.TIPO_MANDATORIO);
                form.setVisible(true);
            }
        });

        substituicaoTerm.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                FormTermos form = new FormTermos();
                form.setVisible(true);
            }
        });
        eliminatoria.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                FormWizard form = new FormWizard(FormWizard.TIPO_ELIMINATORIO);
                form.setVisible(true);
            }
        });
    }

    //Obtain the image URL
    protected static Image createImage(String path, String description) {
        URL imageURL = Teste.class.getResource(path);

        if (imageURL == null) {
            System.err.println("Resource not found: " + path);
            return null;
        } else {
            return (new ImageIcon(imageURL, description)).getImage();
        }
    }
}
