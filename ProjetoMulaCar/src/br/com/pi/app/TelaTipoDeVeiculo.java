/*
 *  -------------------------------------------------------------------------------------------------->
 *  Licença    : MIT - Copyright 2019 Jhonathan, Gustavo e Miguel 
 *  Criado em  : 21/11/2020 02:39:04 
 *  Instituição: FACULDADE SENAI FATESG
 *  Curso      : Análise e Desenvolvimento de sistemas - Módulo 3 - 2020/11
 *  Disciplina : Projeto Integrador
 *  Alunos     : Jhonathan dos Reis, Gustavo Gabriel e Miguel Neto
 *  Projeto    : Projeto Locação de Veículos
 *  Exercício  : Mula Car
 *  ---------------------------------------------------------------------------------------------------
 *  Propósito do arquivo: 
 *  ---------------------------------------------------------------------------------------------------| 
 */
package br.com.pi.app;

import br.com.pi.bll.TiposDeVeiculosBll;
import br.com.pi.model.TiposDeVeiculos;
import br.com.pi.util.Valida;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author jhonlinux
 */
public class TelaTipoDeVeiculo extends javax.swing.JFrame {

    //--- BLL´S---------------------------------------------------------------------------------------->
    TiposDeVeiculosBll tiposVeiculosBll = null;
    //--- FIM BLL'S ----------------------------------------------------------------------------------->
    //

    //--- CLASSES -------------------------------------------------------------------------------------> 
    TiposDeVeiculos tipoDeVeiculo = null;
    //--- FIM CLASSES --------------------------------------------------------------------------------->
    //

    public TelaTipoDeVeiculo() {
        initComponents();

        try {
            tiposVeiculosBll = new TiposDeVeiculosBll();
            tipoDeVeiculo = new TiposDeVeiculos();
            preencherGridTiposDeVeiculos();
        } catch (Exception error) {
            JOptionPane.showMessageDialog(null, error.getMessage(), "Menssagem", JOptionPane.ERROR_MESSAGE);
        }
        this.setLocationRelativeTo(null);
    }

    //--- METODOS ------------------------------------------------------------------------------------->
    public void preencherGridTiposDeVeiculos() {

        try {
            DefaultTableModel tableCategoria = (DefaultTableModel) jTableTiposDeVeiculos.getModel();
            tableCategoria.setRowCount(0);

            Object[] linha = new Object[2];

            ArrayList<TiposDeVeiculos> tiposDeVeiculos = new TiposDeVeiculosBll().getAllTiposDeVeiculos();

            for (TiposDeVeiculos tiposDeVeiculo : tiposDeVeiculos) {
                linha[0] = tiposDeVeiculo.getIden();
                linha[1] = tiposDeVeiculo.getNome();
                tableCategoria.addRow(linha);
            }
        } catch (Exception error) {
            JOptionPane.showMessageDialog(null, error.getMessage(), "Menssagem", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void preencherFormularioTiposDeVeiculos() {
        int id = Integer.parseInt(jTableTiposDeVeiculos.getValueAt(jTableTiposDeVeiculos.getSelectedRow(), 0).toString());
        String nome = jTableTiposDeVeiculos.getValueAt(jTableTiposDeVeiculos.getSelectedRow(), 1).toString();

        jTextFieldIdTipoDeVeiculo.setText(id + "");
        jTextFieldNome.setText(nome);
    }

    public void limparCampos() {
        jTextFieldIdTipoDeVeiculo.setText("");
        jTextFieldNome.setText("");
    }
    
    public void ValidaTiposDeVeiculos() {
        
        Valida.campoVazio(jTextFieldNome.getText(), "Digite o tipo do veículo!");
        Valida.notNumber(jTextFieldNome.getText(), "");
        Valida.notSpecialCharacters(jTextFieldNome.getText(), "");
    }
    //--- FIM METODOS --------------------------------------------------------------------------------->
    //
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabelIdPessoaFisica = new javax.swing.JLabel();
        jTextFieldIdTipoDeVeiculo = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jTextFieldNome = new javax.swing.JTextField();
        jButtonCadastrar = new javax.swing.JButton();
        jButtonAlterar = new javax.swing.JButton();
        jButtonRemover = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableTiposDeVeiculos = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("[TIPOS DE VEÍCULOS]");

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Tipo de Veículo"));

        jLabelIdPessoaFisica.setText("ID");

        jTextFieldIdTipoDeVeiculo.setEditable(false);

        jLabel1.setText("NOME");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelIdPessoaFisica)
                    .addComponent(jTextFieldIdTipoDeVeiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jTextFieldNome, javax.swing.GroupLayout.PREFERRED_SIZE, 508, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelIdPessoaFisica)
                .addGap(14, 14, 14)
                .addComponent(jTextFieldIdTipoDeVeiculo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextFieldNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(43, Short.MAX_VALUE))
        );

        jButtonCadastrar.setIcon(new javax.swing.ImageIcon("/home/jhonlinux/Documentos/Repositorio/projetoLocacaoDeVeiculos/ProjetoMulaCar/src/br/com/pi/icons/salve.png")); // NOI18N
        jButtonCadastrar.setText("CADASTRAR");
        jButtonCadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCadastrarActionPerformed(evt);
            }
        });

        jButtonAlterar.setIcon(new javax.swing.ImageIcon("/home/jhonlinux/Documentos/Repositorio/projetoLocacaoDeVeiculos/ProjetoMulaCar/src/br/com/pi/icons/editar.png")); // NOI18N
        jButtonAlterar.setText("ALTERAR");
        jButtonAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAlterarActionPerformed(evt);
            }
        });

        jButtonRemover.setIcon(new javax.swing.ImageIcon("/home/jhonlinux/Documentos/Repositorio/projetoLocacaoDeVeiculos/ProjetoMulaCar/src/br/com/pi/icons/lixo.png")); // NOI18N
        jButtonRemover.setText("REMOVER");
        jButtonRemover.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRemoverActionPerformed(evt);
            }
        });

        jTableTiposDeVeiculos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "NOME"
            }
        ));
        jTableTiposDeVeiculos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableTiposDeVeiculosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTableTiposDeVeiculos);
        if (jTableTiposDeVeiculos.getColumnModel().getColumnCount() > 0) {
            jTableTiposDeVeiculos.getColumnModel().getColumn(0).setMinWidth(40);
            jTableTiposDeVeiculos.getColumnModel().getColumn(0).setPreferredWidth(40);
            jTableTiposDeVeiculos.getColumnModel().getColumn(0).setMaxWidth(40);
        }

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(jButtonCadastrar)
                .addGap(296, 296, 296)
                .addComponent(jButtonAlterar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 384, Short.MAX_VALUE)
                .addComponent(jButtonRemover)
                .addGap(44, 44, 44))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonCadastrar)
                    .addComponent(jButtonAlterar)
                    .addComponent(jButtonRemover))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 414, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCadastrarActionPerformed
        
        try {
            ValidaTiposDeVeiculos();
            tipoDeVeiculo.setNome(jTextFieldNome.getText());
            tiposVeiculosBll.addTiposDeVeiculos(tipoDeVeiculo);
            preencherGridTiposDeVeiculos();
            limparCampos();
            
            JOptionPane.showMessageDialog(null, "Tipo de veículo cadastrado com sucesso!");
        } catch (Exception error) {
            JOptionPane.showMessageDialog(null, error.getMessage(), "Menssagem", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButtonCadastrarActionPerformed

    private void jButtonAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAlterarActionPerformed
        
        try {
            if (jTableTiposDeVeiculos.getSelectedRow() == -1) {
                throw new Exception("Selecione um tipo de veículo a ser alterado!");
            }
            
            ValidaTiposDeVeiculos();
            tipoDeVeiculo.setNome(jTextFieldNome.getText());
            tipoDeVeiculo.setIden(Integer.parseInt(jTextFieldIdTipoDeVeiculo.getText()));
            tiposVeiculosBll.updateTiposDeVeiculos(tipoDeVeiculo);
            preencherGridTiposDeVeiculos();
            limparCampos();
            
            JOptionPane.showMessageDialog(null, "Tipo de veículo cadastrado com sucesso!");
        } catch (Exception error) {
            JOptionPane.showMessageDialog(null, error.getMessage(), "Menssagem", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButtonAlterarActionPerformed

    private void jButtonRemoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRemoverActionPerformed
        
        try {
            if (jTableTiposDeVeiculos.getSelectedRow() == -1) {
                throw new Exception ("Selecione um tipo de veículo a ser removido!");
            }
            
            ValidaTiposDeVeiculos();
            tipoDeVeiculo.setIden(Integer.parseInt(jTextFieldIdTipoDeVeiculo.getText()));
            tiposVeiculosBll.deleteTiposDeVeiculos(tipoDeVeiculo);
            preencherGridTiposDeVeiculos();
            limparCampos();
            
            JOptionPane.showMessageDialog(null, "Tipo de veículo cadastrado com sucesso!");
        } catch (Exception error) {
            JOptionPane.showMessageDialog(null, error.getMessage(), "Menssagem", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButtonRemoverActionPerformed

    private void jTableTiposDeVeiculosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableTiposDeVeiculosMouseClicked
        
        try {
            preencherFormularioTiposDeVeiculos();
        } catch (Exception error) {
            JOptionPane.showMessageDialog(null, error.getMessage(), "Menssagem", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jTableTiposDeVeiculosMouseClicked

    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(TelaTipoDeVeiculo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaTipoDeVeiculo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaTipoDeVeiculo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaTipoDeVeiculo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaTipoDeVeiculo().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAlterar;
    private javax.swing.JButton jButtonCadastrar;
    private javax.swing.JButton jButtonRemover;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabelIdPessoaFisica;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableTiposDeVeiculos;
    private javax.swing.JTextField jTextFieldIdTipoDeVeiculo;
    private javax.swing.JTextField jTextFieldNome;
    // End of variables declaration//GEN-END:variables
}
