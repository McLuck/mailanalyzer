/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ListaPendentes.java
 *
 * Created on 25/09/2011, 21:24:17
 */
package br.com.mailanalyzer.grafico;

import br.com.mailanalyzer.analise2.GerenciamentoAnalisador;
import br.com.mailanalyzer.analise2.GerenciamentoAnalisador.Analisador;
import br.com.mailanalyzer.dao.AssuntosPendentesDAO;
import br.com.mailanalyzer.domain.ActiveReceiver;
import br.com.mailanalyzer.domain.AssuntosPendentes;
import br.com.mailanalyzer.domain.Message;
import br.com.mailanalyzer.log.L;
import br.com.mailanalyzer.main.Base;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author McLuck
 */
public class ListaPendentes extends javax.swing.JFrame {

    public static final String TAG = "Lista de assuntos nao encontrados (Tela)";

    /** Creates new form ListaPendentes */
    public ListaPendentes(List<AssuntosPendentes> lista) {
        initComponents();
        assuntos = lista;
        initTabela();
    }
    private List<AssuntosPendentes> assuntos;
    private AssuntosTableModel model;

    public void initTabela() {
        model = new AssuntosTableModel(assuntos);
        jtable.setModel(model);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jtable = new javax.swing.JTable();
        aprender = new javax.swing.JButton();
        reanalisar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jtable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Origem", "Mensagem", "Objeto"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jtable);

        aprender.setText("Aprender com a Mensagem selecionada");
        aprender.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aprenderActionPerformed(evt);
            }
        });

        reanalisar.setText("Analisar estas Mensagens novamente");
        reanalisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reanalisarActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, reanalisar, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 554, Short.MAX_VALUE)
                    .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 554, Short.MAX_VALUE)
                    .add(aprender, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 554, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(9, 9, 9)
                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 275, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(18, 18, 18)
                .add(aprender)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(reanalisar)
                .addContainerGap(26, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void reanalisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reanalisarActionPerformed
        L.a(TAG, this, "Iniciando re-an�lise para as mensagens n�o encontradas.");

        AssuntosPendentesDAO apdao = new AssuntosPendentesDAO();
        for (AssuntosPendentes ap : assuntos) {
            Analisador analisador = new GerenciamentoAnalisador.Analisador();
            ActiveReceiver a = Base.ACTIVE_SERVICES[0].getReceiver();
            analisador.setReceiver(a);
            Message m = ap.getMessage();
            analisador.run(m);
            apdao.excluir(ap);
            apdao.commit();
            apdao.close();
        }

        //Recarregando lista de assuntos nao encontrados
        apdao.clear();
        assuntos = apdao.obterTodos();
        model.setData(assuntos);
        jtable.setModel(model);
    }//GEN-LAST:event_reanalisarActionPerformed

    private void aprenderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aprenderActionPerformed
        try {
            int row = jtable.getSelectedRow();
            AssuntosPendentes ap = assuntos.get(row);
            Pendentes pendente = new Pendentes(ap.getMessage());
            pendente.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Nenhuma mensagem foi selecionada.\nSelecione a mensagem na tabela acima antes de escolher esta op��o.");
        }

    }//GEN-LAST:event_aprenderActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton aprender;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jtable;
    private javax.swing.JButton reanalisar;
    // End of variables declaration//GEN-END:variables
}