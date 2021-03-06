/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * FormWizard.java
 *
 * Created on 25/09/2011, 19:27:26
 */
package br.com.mailanalyzer.grafico;

import br.com.mailanalyzer.analise2.GerenciamentoAnalisador;
import br.com.mailanalyzer.analise2.Raiz;
import br.com.mailanalyzer.main.Config;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author McLuck
 */
public class FormWizard extends javax.swing.JFrame {

    /** Creates new form FormWizard */
    public FormWizard(int tp) {
        this.tipo = tp;
        initComponents();
        initCombo();
    }
    public static final int TIPO_ELIMINATORIO = 0;
    public static final int TIPO_MANDATORIO = 1;
    public static final int TIPO_AGREGACAO = 2;
    private int tipo;

    private void initCombo() {
        if (GerenciamentoAnalisador.getMatrizes() == null) {
            GerenciamentoAnalisador.load();
        }
        for (Raiz raiz : GerenciamentoAnalisador.getMatrizes()) {
            jComboBox1.addItem(new ComboBox(raiz));
        }
        error1.setVisible(false);
        switch (tipo) {
            case TIPO_AGREGACAO: {
                peso.setVisible(true);
                jLabel3.setVisible(true);
                peso.setEditable(true);
                peso.setEnabled(true);
                setTitle("Adicionar nova Agregação de palavras");
                break;
            }
            case TIPO_ELIMINATORIO:{
                setTitle("Adicionar nova composição eliminatória");
                break;
            }
            case TIPO_MANDATORIO:{
                setTitle("Adicionar nova composição mandatória");
            }

        }
        if (tipo != TIPO_AGREGACAO) {
            peso.setEditable(false);
            peso.setEnabled(false);
            peso.setVisible(false);
            jLabel3.setVisible(false);
        }
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jComboBox1 = new javax.swing.JComboBox();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        composicao = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        peso = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        error1 = new javax.swing.JLabel();
        chk_sequencial = new javax.swing.JCheckBox();

        setTitle("Add");

        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jButton1.setText("Salvar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel1.setText("Matriz de Assunto");

        jLabel2.setText("Digite a nova composição no campo abaixo:");

        jLabel3.setText("Informe o peso desta composicao:");

        error1.setForeground(new java.awt.Color(153, 0, 0));
        error1.setText("valor deve ser numérico");

        chk_sequencial.setText("Palavras devem estar nesta ordem sequencial");

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .addContainerGap()
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(composicao, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 416, Short.MAX_VALUE)
                            .add(jComboBox1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jLabel1)
                            .add(jLabel2)))
                    .add(layout.createSequentialGroup()
                        .add(169, 169, 169)
                        .add(jButton1))
                    .add(layout.createSequentialGroup()
                        .addContainerGap()
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(peso, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 416, Short.MAX_VALUE)
                            .add(layout.createSequentialGroup()
                                .add(jLabel3)
                                .add(49, 49, 49)
                                .add(error1))
                            .add(chk_sequencial))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(12, 12, 12)
                .add(jLabel1)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jComboBox1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(jLabel2)
                .add(4, 4, 4)
                .add(composicao, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(chk_sequencial)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 13, Short.MAX_VALUE)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel3)
                    .add(error1))
                .add(4, 4, 4)
                .add(peso, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jButton1)
                .add(34, 34, 34))
        );

        jButton1.getAccessibleContext().setAccessibleName("bt_salvar");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        ComboBox cb = (ComboBox) jComboBox1.getSelectedItem();
        String nComposicao = composicao.getText();
        boolean sequencial = chk_sequencial.isSelected();
        int oPeso = 0;
        if (tipo == TIPO_AGREGACAO) {
            try {
                oPeso = Integer.parseInt(peso.getText());
                Config.Register.ADD_COMPOSICAO_AGREGACAO(nComposicao, sequencial, oPeso, cb.getId());
            } catch (Exception e) {
                error1.setVisible(true);
                return;
            }
        } else if (tipo == TIPO_ELIMINATORIO) {
            Config.Register.ADD_COMPOSICAO_ELIMINATORIA(nComposicao, sequencial, cb.getId());
        } else if (tipo == TIPO_MANDATORIO) {
            Config.Register.ADD_COMPOSICAO_MANDATORIA(nComposicao, sequencial, cb.getId());
        }
        JOptionPane.showMessageDialog(null, "Composição foi salva com sucesso!");
        setVisible(false);
        try {
            finalize();
        } catch (Throwable ex) {
            Logger.getLogger(FormWizard.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox chk_sequencial;
    private javax.swing.JTextField composicao;
    private javax.swing.JLabel error1;
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JTextField peso;
    // End of variables declaration//GEN-END:variables
}
