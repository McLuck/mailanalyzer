/*//GEN-FIRST:event_bt_aplicarActionPerformed
 * To change this template, choose Tools | Templates//GEN-LAST:event_bt_aplicarActionPerformed
 * and open the template in the editor.
 */

/*
 * ListaLog.java
 *
 * Created on 09/10/2011, 19:20:41
 */
package br.com.mailanalyzer.grafico;

import br.com.mailanalyzer.dao.LogDAO;
import br.com.mailanalyzer.domain.Log;
import br.com.mailanalyzer.log.L;
import br.com.mailanalyzer.main.Config;
import br.com.mailanalyzer.utils.Converte;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;

/**
 *
 * @author McLuck
 */
public class ListaLog extends javax.swing.JFrame {

    /** Creates new form ListaLog */
    private ListaLog() {
        initComponents();
        try {
            dini.setFormatterFactory(new DefaultFormatterFactory(new MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        setDefaultInputs();
        startService();
        jTable1.addMouseListener(new MouseAdapter() {

            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    clickDuploLog();
                }
            }
        });
    }
    private static boolean rodar = false;

    public static void startService() {
        if (rodar) {
            return;
        }
        rodar = true;
        new Thread(new Runnable() {

            @Override
            public void run() {
                while (rodar) {
                    long now = GregorianCalendar.getInstance().getTimeInMillis();
                    if ((now - ultimaVez) >= (intervalo * 1000)) {
                        if (instancia == null) {
                            System.err.println("[DISSECT MAIL] - Serviço de LOG iniciando.");
                            continue;
                        }
                        instancia.loadLista();
                        ultimaVez = GregorianCalendar.getInstance().getTimeInMillis();
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(ListaLog.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }).start();
    }

    public static void stopService() {
        rodar = false;
    }

    public static ListaLog getinstance() {
        if (instancia == null) {
            instancia = new ListaLog();
        }
        return instancia;
    }
    private static long ultimaVez = 0;
    private static ListaLog instancia;

    private void getParameters() {
        ldao.close();
        ldao.clear();
        if (!lerInputs()) {
            return;
        }
        List<Integer> tipos = new ArrayList<Integer>();
        if (isaviso) {
            tipos.add(L.AVISO);
        }
        if (iserro) {
            tipos.add(L.ERRO);
        }
        if (isfatal) {
            tipos.add(L.FATAL);
        }
        if (isdebug) {
            tipos.add(L.DEBUG);
        }
        if (isinfo) {
            tipos.add(L.INFO);
        }
        if (tipos.isEmpty()) {
            tipos.add(L.DEBUG);
            debug.setSelected(true);
            isdebug = true;
            error_qtd.setText("Nenhum tipo selecionado. Debug selecionado automaticamente.");
        }
        Map<String, Object> mapa = new HashMap<String, Object>();
        mapa.put(LogDAO.Parameters.INICIO, inicio);
        mapa.put(LogDAO.Parameters.TIPOS, tipos.toArray());
        mapa.put(LogDAO.Parameters.NUMERO_REGISTROS, registros);
        if (buscarClasse) {
            mapa.put(LogDAO.Parameters.CLASSE_REFERENCIA, classe);
        }
        if (buscarDetalhe) {
            mapa.put(LogDAO.Parameters.DETALHES, detalhe);
        }
        if (buscarTAG) {
            mapa.put(LogDAO.Parameters.TAG, tag);
        }
        logs = ldao.getFromParameters(mapa);
    }
    private static int intervalo = 5;
    private int registros;
    private boolean isinfo, isaviso, iserro, isdebug, isfatal, buscarTAG, buscarClasse, buscarDetalhe;
    private String tag, classe, detalhe;
    private final String defaultDt = Converte.ToStringDataVisual(new java.util.Date());
    private long inicio;
    private java.util.Date dtInicio;

    private void setContentInputs() {
        input_intervalo.setText(String.valueOf(intervalo));
        input_qtd.setText(String.valueOf(registros));
        dini.setText(defaultDt);
    }

    private void setDefaultInputs() {
        intervalo = 5;
        registros = 100;
        setContentInputs();
    }

    private boolean lerInputs() {
        String qtd = input_qtd.getText();
        try {
            registros = Integer.parseInt(qtd);
        } catch (Exception e) {
            error_qtd.setText("Quantidade de registros inválida!");
            return false;
        }

        String inter = input_intervalo.getText();
        try {
            intervalo = Integer.parseInt(inter);
        } catch (Exception e) {
            error_qtd.setText("Intervalo inválido!");
            return false;
        }

        if (registros > 1000 || registros < 1) {
            error_qtd.setText("Quantidade de registros inválida!");
            return false;
        }

        if (intervalo > (1000) || intervalo < 3) {
            error_qtd.setText("Intervalo inválido. Só é permitido intervalo entre 3 e 1000 segundos!");
            return false;
        }
        try {
            isdebug = debug.isSelected();
            iserro = erro.isSelected();
            isfatal = fatal.isSelected();
            isaviso = aviso.isSelected();
            isinfo = info.isSelected();
        } catch (Exception e) {
        }
        try {
            detalhe = input_src_detalhe.getText();
            tag = input_src_tag.getText();
            classe = input_src_referencia.getText();
            buscarClasse = !classe.isEmpty();
            buscarTAG = !tag.isEmpty();
            buscarDetalhe = !detalhe.isEmpty();
        } catch (Exception e) {
        }
        try {
            String dt = dini.getText();
            dtInicio = new java.util.Date();
            dtInicio = Converte.ToDate(dt);
            inicio = dtInicio.getTime();
        } catch (Exception e) {
            try {
                dtInicio = new java.util.Date();
                dtInicio = Converte.ToDate(defaultDt);
                inicio = dtInicio.getTime();
            } catch (Exception e2) {
                dtInicio = new java.util.Date();
                inicio = dtInicio.getTime();
            }
        }
        return true;
    }

    public void clickDuploLog() {
        int row = jTable1.getSelectedRow();
        Log lg = model.getValueAt(row);
        LogDetalhe ldetails = new LogDetalhe(lg);
        ldetails.setVisible(true);
    }

    private void loadLista() {
        if (logs == null) {
            logs = new ArrayList<Log>();
        }
        if (!lerInputs()) {
            return;
        }
        ldao.setQtdRegistro(registros);
        if (model == null) {
            model = new LogTableModel();
        }
        getParameters();
        model.setData(logs);
        jTable1.setModel(model);
        int margin = 2;
        packColumns(jTable1, margin);

        addCor();
    }
    private static LogDAO ldao = new LogDAO();
    private static List<Log> logs;
    private static LogTableModel model;

    public void packColumns(JTable table, int margin) {
        for (int c = 0; c < table.getColumnCount(); c++) {
            packColumn(table, c, 2);
        }
    }

    private void addCor() {
        // minha table se chama jTable1, se precisar troque o nome
        jTable1.setDefaultRenderer(Object.class, new javax.swing.table.DefaultTableCellRenderer() {

            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                super.getTableCellRendererComponent(jTable1, value, isSelected, hasFocus, row, column);
                // aqui você coloca a condição para pintar a linha, neste caso se o valor da coluna 4 da linha for menor que 2 (table.getValueAt(row,4), aqui table é o nome que está no parametro ali em cima, row é a linha atual da verificação)
                setBackground(Color.decode(L.getColor(model.getValueAt(row).getOcorrencia())));
                
                return this;
            }
        });
    }

// Sets the preferred width of the visible column specified by vColIndex. The column
// will be just wide enough to show the column head and the widest cell in the column.
// margin pixels are added to the left and right
// (resulting in an additional width of 2*margin pixels).
    public void packColumn(JTable table, int vColIndex, int margin) {

        DefaultTableColumnModel colModel = (DefaultTableColumnModel) table.getColumnModel();
        TableColumn col = colModel.getColumn(vColIndex);
        int width = 0;

        // Get width of column header
        TableCellRenderer renderer = col.getHeaderRenderer();
        if (renderer == null) {
            renderer = table.getTableHeader().getDefaultRenderer();
        }
        Component comp = renderer.getTableCellRendererComponent(
                table, col.getHeaderValue(), false, false, 0, 0);
        width = comp.getPreferredSize().width;

        // Get maximum width of column data
        for (int r = 0; r < table.getRowCount(); r++) {
            //pinta a linha
            renderer = table.getCellRenderer(r, vColIndex);



            comp = renderer.getTableCellRendererComponent(
                    table, table.getValueAt(r, vColIndex), false, false, r, vColIndex);
            width = Math.max(width, comp.getPreferredSize().width);
            int ultimaLinha = model.getRowCount() - 1;
        }

        // Add margin
        width += 2 * margin;

        // Set the width
        col.setPreferredWidth(width);

    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        input_qtd = new javax.swing.JTextField();
        error_qtd = new javax.swing.JLabel();
        input_intervalo = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        bt_aplicar = new javax.swing.JButton();
        input_src_detalhe = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        input_src_referencia = new javax.swing.JTextField();
        input_src_tag = new javax.swing.JTextField();
        dini = new javax.swing.JFormattedTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        fatal = new javax.swing.JCheckBox();
        debug = new javax.swing.JCheckBox();
        info = new javax.swing.JCheckBox();
        erro = new javax.swing.JCheckBox();
        aviso = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Quando", "TAG", "Ocorrência"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
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
        jScrollPane1.setViewportView(jTable1);

        jLabel1.setText("Quantidade:");

        input_qtd.setText("100");

        error_qtd.setForeground(new java.awt.Color(153, 0, 0));
        error_qtd.setText(".");

        input_intervalo.setText("5");

        jLabel2.setText("Intervalo:");

        bt_aplicar.setText("Aplicar Filtro");
        bt_aplicar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_aplicarActionPerformed(evt);
            }
        });

        jLabel5.setText("Filtro detalhe:");

        dini.setToolTipText("");

        jLabel6.setText("Inicio:");

        jLabel3.setText("Buscar TAG:");

        jLabel4.setText("Buscar Classe:");

        fatal.setSelected(true);
        fatal.setText("ERRO FATAL");
        fatal.setFocusable(false);
        fatal.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        fatal.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        debug.setSelected(true);
        debug.setText("DEBUG");
        debug.setFocusable(false);
        debug.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        debug.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        info.setSelected(true);
        info.setText("INFO");
        info.setFocusable(false);
        info.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        info.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        erro.setSelected(true);
        erro.setText("ERRO");
        erro.setFocusable(false);
        erro.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        erro.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        aviso.setSelected(true);
        aviso.setText("AVISO");
        aviso.setFocusable(false);
        aviso.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        aviso.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(info)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(aviso)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(fatal)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(debug)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(erro)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(info)
                    .add(aviso)
                    .add(fatal)
                    .add(debug)
                    .add(erro))
                .addContainerGap())
        );

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                        .add(layout.createSequentialGroup()
                            .add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(18, 18, 18)
                            .add(error_qtd, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .add(layout.createSequentialGroup()
                            .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                .add(layout.createSequentialGroup()
                                    .add(114, 114, 114)
                                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                                        .add(jLabel4)
                                        .add(layout.createSequentialGroup()
                                            .add(input_qtd, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 101, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                            .add(53, 53, 53)
                                            .add(jLabel5)))
                                    .add(18, 18, 18)
                                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                                        .add(input_src_referencia)
                                        .add(input_src_detalhe, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE))
                                    .add(45, 45, 45)
                                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                                        .add(jLabel3)
                                        .add(jLabel6))
                                    .add(18, 18, 18)
                                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                                        .add(input_src_tag)
                                        .add(dini, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE)))
                                .add(layout.createSequentialGroup()
                                    .addContainerGap()
                                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                                        .add(jLabel2)
                                        .add(jLabel1))
                                    .add(18, 18, 18)
                                    .add(input_intervalo, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 101, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                            .add(18, 18, 18)
                            .add(bt_aplicar)))
                    .add(layout.createSequentialGroup()
                        .add(20, 20, 20)
                        .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 963, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(layout.createSequentialGroup()
                        .add(20, 20, 20)
                        .add(error_qtd)))
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(jLabel1)
                            .add(input_qtd, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(input_src_detalhe, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jLabel5)
                            .add(jLabel3)
                            .add(input_src_tag, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jLabel2)
                            .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                                .add(input_intervalo, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .add(jLabel4)
                                .add(input_src_referencia, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .add(jLabel6)
                                .add(dini, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))))
                    .add(layout.createSequentialGroup()
                        .add(27, 27, 27)
                        .add(bt_aplicar)))
                .add(18, 18, 18)
                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 435, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>                        

    private void bt_aplicarActionPerformed(java.awt.event.ActionEvent evt) {                                           
        loadLista();
    }                                          

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                ListaLog.getinstance().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify                     
    private javax.swing.JCheckBox aviso;
    private javax.swing.JButton bt_aplicar;
    private javax.swing.JCheckBox debug;
    private javax.swing.JFormattedTextField dini;
    private javax.swing.JCheckBox erro;
    private javax.swing.JLabel error_qtd;
    private javax.swing.JCheckBox fatal;
    private javax.swing.JCheckBox info;
    private javax.swing.JTextField input_intervalo;
    private javax.swing.JTextField input_qtd;
    private javax.swing.JTextField input_src_detalhe;
    private javax.swing.JTextField input_src_referencia;
    private javax.swing.JTextField input_src_tag;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration                   
}
