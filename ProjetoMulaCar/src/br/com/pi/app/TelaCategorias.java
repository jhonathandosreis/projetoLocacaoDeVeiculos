package br.com.pi.app;

import br.com.pi.bll.CategoriasBll;
import br.com.pi.model.Categorias;
import br.com.pi.util.Valida;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author jhonlinux
 */
public class TelaCategorias extends javax.swing.JFrame {

    //--- BLL´S---------------------------------------------------------------------------------------->
    private CategoriasBll categoriasBll = null;
    //--- FIM BLL'S ----------------------------------------------------------------------------------->
    //

    //--- CLASSES -------------------------------------------------------------------------------------> 
    private Categorias categoria = null;
    //--- FIM CLASSES --------------------------------------------------------------------------------->
    //

    public TelaCategorias() {
        initComponents();
       
        try {
            categoriasBll = new CategoriasBll();
            categoria = new Categorias();
            preencherGridCategorias();
        } catch (Exception error) {
            JOptionPane.showMessageDialog(rootPane, error.getMessage(), "Menssagem", JOptionPane.ERROR_MESSAGE);
        }
        this.setLocationRelativeTo(null);
    }

    //--- METODOS ------------------------------------------------------------------------------------->
    public void preencherGridCategorias() throws Exception {

        try {
            DefaultTableModel tableVeiculos = (DefaultTableModel) jTableCategoria.getModel();
            tableVeiculos.setRowCount(0);

            Object[] linha = new Object[3];

            ArrayList<Categorias> categoria = new CategoriasBll().getAllCategorias();

            for (Categorias categorias : categoria) {

                linha[0] = categorias.getIden();
                linha[1] = categorias.getNome();
                linha[2] = categorias.getValorDiarioLocacao();
                tableVeiculos.addRow(linha);
            }
        } catch (Exception error) {
            JOptionPane.showMessageDialog(rootPane, error.getMessage(), "Menssagem", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void preencherFormularioCategoria() {

        int id = Integer.parseInt(jTableCategoria.getValueAt(jTableCategoria.getSelectedRow(), 0).toString());
        String nome = jTableCategoria.getValueAt(jTableCategoria.getSelectedRow(), 1).toString();
        float valorDiarioLocacao = Float.parseFloat(jTableCategoria.getValueAt(jTableCategoria.getSelectedRow(), 2).toString());

        jTextFieldIDCategoria.setText(id + "");
        jTextFieldNomeCategoria.setText(nome);
        jTextFieldValorDiarioLocacao.setText(valorDiarioLocacao + "");
    }

    public void limparCampos() {
        jTextFieldIDCategoria.setText("");
        jTextFieldNomeCategoria.setText("");
        jTextFieldValorDiarioLocacao.setText("");
    }
    
    public void validacaoCategorias() {
        
        Valida.campoVazio(jTextFieldNomeCategoria.getText(), "Digite uma categoria para o veículo!");
        Valida.campoVazio(jTextFieldValorDiarioLocacao.getText(), "Digite um valor para locação!");
        Valida.notNumber(jTextFieldNomeCategoria.getText(), "");
        Valida.numberFloat(jTextFieldValorDiarioLocacao.getText(), "");
    }
    //--- FIM METODOS --------------------------------------------------------------------------------->
    //

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextFieldNomeCategoria = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jTextFieldValorDiarioLocacao = new javax.swing.JTextField();
        jTextFieldIDCategoria = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableCategoria = new javax.swing.JTable();
        jButtonRemover = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButtonAlterar = new javax.swing.JButton();
        jButtonCadastrar = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("[CATEGORIA]");
        setMaximumSize(new java.awt.Dimension(1280, 720));
        setMinimumSize(new java.awt.Dimension(1280, 720));
        setPreferredSize(new java.awt.Dimension(1280, 720));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)), "CATEGORIA", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Lucida Grande", 0, 13), new java.awt.Color(255, 255, 255))); // NOI18N

        jLabel1.setForeground(new java.awt.Color(204, 204, 204));
        jLabel1.setText("ID");

        jLabel3.setForeground(new java.awt.Color(204, 204, 204));
        jLabel3.setText("CATEGORIA");

        jLabel2.setForeground(new java.awt.Color(204, 204, 204));
        jLabel2.setText("VALOR DIÁRIO DA LOCAÇÃO");

        jTextFieldIDCategoria.setEditable(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jTextFieldIDCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(jTextFieldNomeCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(jTextFieldValorDiarioLocacao, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jLabel1)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(3, 3, 3)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldIDCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jTextFieldNomeCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jTextFieldValorDiarioLocacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, 1220, 100));

        jTableCategoria.setBackground(new java.awt.Color(0, 0, 0));
        jTableCategoria.setForeground(new java.awt.Color(255, 255, 255));
        jTableCategoria.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "NOME", "VALOR DIÁRIO DA LOCAÇÃO"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableCategoria.setSelectionBackground(new java.awt.Color(255, 255, 255));
        jTableCategoria.setSelectionForeground(new java.awt.Color(0, 0, 0));
        jTableCategoria.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableCategoriaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTableCategoria);
        if (jTableCategoria.getColumnModel().getColumnCount() > 0) {
            jTableCategoria.getColumnModel().getColumn(0).setMinWidth(40);
            jTableCategoria.getColumnModel().getColumn(0).setPreferredWidth(40);
            jTableCategoria.getColumnModel().getColumn(0).setMaxWidth(40);
        }

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 160, 1220, 420));

        jButtonRemover.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/pi/icons/lixo.png"))); // NOI18N
        jButtonRemover.setText("REMOVER");
        jButtonRemover.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRemoverActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonRemover, new org.netbeans.lib.awtextra.AbsoluteConstraints(1110, 600, 140, 50));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/pi/icons/limpar-limpo.png"))); // NOI18N
        jButton1.setText("LIMPAR");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 600, 140, 50));

        jButtonAlterar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/pi/icons/editar.png"))); // NOI18N
        jButtonAlterar.setText("ALTERAR");
        jButtonAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAlterarActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonAlterar, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 600, 140, 50));

        jButtonCadastrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/pi/icons/salve.png"))); // NOI18N
        jButtonCadastrar.setText("CADASTRAR");
        jButtonCadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCadastrarActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonCadastrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 600, 140, 50));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/pi/image/fundo desfolque bmw_Easy-Resize.com.jpg"))); // NOI18N
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCadastrarActionPerformed

        try {
            validacaoCategorias();
            categoria.setNome(jTextFieldNomeCategoria.getText());
            categoria.setValorDiarioLocacao(Float.parseFloat(jTextFieldValorDiarioLocacao.getText()));
            categoriasBll.addCategorias(categoria);
            preencherGridCategorias();
            limparCampos();
            
            JOptionPane.showMessageDialog(null, "Categoria cadastrada com sucesso!");
        } catch (Exception error) {
            JOptionPane.showMessageDialog(null, error.getMessage(), "Menssagem", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButtonCadastrarActionPerformed

    private void jButtonAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAlterarActionPerformed

        try {
            if (jTableCategoria.getSelectedRow() == -1) {
                throw new Exception("Selecione uma categoria a ser alterada!");
            }
            
            validacaoCategorias();
            categoria.setNome(jTextFieldNomeCategoria.getText());
            categoria.setValorDiarioLocacao(Float.parseFloat(jTextFieldValorDiarioLocacao.getText()));
            categoria.setIden(Integer.parseInt(jTextFieldIDCategoria.getText()));
            categoriasBll.updateCategorias(categoria);
            preencherGridCategorias();
            limparCampos();
            
            JOptionPane.showMessageDialog(null, "Categoria alterada com sucesso!");
        } catch (Exception error) {
            JOptionPane.showMessageDialog(rootPane, error.getMessage(), "Menssagem", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButtonAlterarActionPerformed

    private void jButtonRemoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRemoverActionPerformed

        try {
            if (jTableCategoria.getSelectedRow() == -1) {
                throw new Exception("Selecione uma categoria a ser removida!");
            }
            
            categoria.setIden(Integer.parseInt(jTextFieldIDCategoria.getText()));
            categoriasBll.deleteCategorias(categoria);
            preencherGridCategorias();
            limparCampos();
            
            JOptionPane.showMessageDialog(null, "Categoria removida com sucesso!");
        } catch (Exception error) {
            JOptionPane.showMessageDialog(rootPane, error.getMessage(), "Menssagem", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButtonRemoverActionPerformed

    private void jTableCategoriaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableCategoriaMouseClicked

        try {
            preencherFormularioCategoria();
        } catch (Exception error) {
            JOptionPane.showMessageDialog(rootPane, error.getMessage(), "Menssagem", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jTableCategoriaMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        limparCampos();
    }//GEN-LAST:event_jButton1ActionPerformed

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TelaCategorias.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaCategorias.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaCategorias.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaCategorias.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaCategorias().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButtonAlterar;
    private javax.swing.JButton jButtonCadastrar;
    private javax.swing.JButton jButtonRemover;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableCategoria;
    private javax.swing.JTextField jTextFieldIDCategoria;
    private javax.swing.JTextField jTextFieldNomeCategoria;
    private javax.swing.JTextField jTextFieldValorDiarioLocacao;
    // End of variables declaration//GEN-END:variables
}
