/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mailanalyzer.testes;

import br.com.mailanalyzer.grafico.FormTermos;
import br.com.mailanalyzer.grafico.FormWizard;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author McLuck
 */
public class TrayTest {

    public static void main(String... args) {
        test();
    }

    public static void test() {
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
                JOptionPane.showMessageDialog(null,
                        "Ver Assuntos Pendentes");
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
        URL imageURL = TrayTest.class.getResource(path);

        if (imageURL == null) {
            System.err.println("Resource not found: " + path);
            return null;
        } else {
            return (new ImageIcon(imageURL, description)).getImage();
        }
    }
}
