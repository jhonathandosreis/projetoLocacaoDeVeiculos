/*
 *  -------------------------------------------------------------------------------------------------->
 *  Licença    : MIT - Copyright 2019 Jhonathan, Gustavo e Miguel 
 *  Criado em  : 21/11/2020 01:06:15 
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

import br.com.pi.bll.CategoriasBll;
import br.com.pi.bll.MarcasBll;
import br.com.pi.bll.ModelosBll;
import br.com.pi.bll.TiposDeVeiculosBll;
import br.com.pi.model.Categorias;
import br.com.pi.model.Marcas;
import br.com.pi.model.Modelos;
import br.com.pi.model.TiposDeVeiculos;
import br.com.pi.util.Valida;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author jhonlinux
 */
public class TelaModelos extends javax.swing.JFrame {

    //--- BLL´S---------------------------------------------------------------------------------------->
    private ModelosBll modelosBll = null;
    private CategoriasBll categoriasBll = null;
    private MarcasBll marcasBll = null;
    private TiposDeVeiculosBll tiposDeVeiculosBll = null;
    //--- FIM BLL'S ----------------------------------------------------------------------------------->
    //

    //--- CLASSES -------------------------------------------------------------------------------------> 
    private Modelos modelo = null;
    private Marcas marca = null;
    private Categorias categoria = null;
    private TiposDeVeiculos tipoDeVeiculo = null;
    //--- FIM CLASSES --------------------------------------------------------------------------------->
    //

    public TelaModelos() {
        initComponents();

        try {
            modelosBll = new ModelosBll();
            categoriasBll = new CategoriasBll();
            marcasBll = new MarcasBll();
            tiposDeVeiculosBll = new TiposDeVeiculosBll();
            modelo = new Modelos();
            marca = new Marcas();
            categoria = new Categorias();
            tipoDeVeiculo = new TiposDeVeiculos();
            preencherGridModelos();
            preencherComboboxModelos();
        } catch (Exception error) {
            JOptionPane.showMessageDialog(rootPane, error.getMessage(), "Menssagem", JOptionPane.ERROR_MESSAGE);
        }
        this.setLocationRelativeTo(null);
    }

    //--- METODOS ------------------------------------------------------------------------------------->
    public void preencherGridModelos() {

        try {
            DefaultTableModel tableVeiculos = (DefaultTableModel) jTableModelo.getModel();
            tableVeiculos.setRowCount(0);

            Object[] linha = new Object[5];

            ArrayList<Modelos> modelos = new ModelosBll().getAllModelos();

            for (Modelos modelo1 : modelos) {
                linha[0] = modelo1.getIden();
                linha[1] = modelo1.getNome();
                linha[2] = modelo1.getMarcas().getNome();
                linha[3] = modelo1.getCategoria().getNome();
                linha[4] = modelo1.getTiposDeVeiculos().getNome();
                tableVeiculos.addRow(linha);
            }
        } catch (Exception error) {
            JOptionPane.showMessageDialog(rootPane, error.getMessage(), "Menssagem", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void preencherFormularioModelos() throws Exception {

        int id = Integer.parseInt(jTableModelo.getValueAt(jTableModelo.getSelectedRow(), 0).toString());
        String nome = jTableModelo.getValueAt(jTableModelo.getSelectedRow(), 1).toString();
        String marcacombobox = jTableModelo.getValueAt(jTableModelo.getSelectedRow(), 2).toString();
        String categoriacombobox = jTableModelo.getValueAt(jTableModelo.getSelectedRow(), 3).toString();
        String tiposDeVeiculoscombobox = jTableModelo.getValueAt(jTableModelo.getSelectedRow(), 4).toString();

        jComboBoxMarca.setSelectedItem(marcasBll.getMarcasById(id).getNome());
        jComboBoxCategoria.setSelectedItem(categoriasBll.getCategoriasById(id).getNome());
        jComboBoxTipoDeVeiculos.setSelectedItem(tiposDeVeiculosBll.getTiposDeVeiculosById(id).getNome());

        jTextFieldIdModelo.setText(id + "");
        jTextFieldNome.setText(nome);
        jComboBoxMarca.setSelectedItem(marcacombobox);
        jComboBoxCategoria.setSelectedItem(categoriacombobox);
        jComboBoxTipoDeVeiculos.setSelectedItem(tiposDeVeiculoscombobox);
    }

    public void preencherComboboxModelos() throws Exception {

        ArrayList<Categorias> lista = categoriasBll.getAllCategorias();
        jComboBoxCategoria.removeAllItems();
        jComboBoxCategoria.addItem("<SELECIONE>");
        

        for (Categorias categorias : lista) {
            jComboBoxCategoria.addItem(categorias.getNome());
        }

        ArrayList<Marcas> lista1 = marcasBll.getAllMarcas();
        jComboBoxMarca.removeAllItems();
        jComboBoxMarca.addItem("<SELECIONE>");

        for (Marcas marcas : lista1) {
            jComboBoxMarca.addItem(marcas.getNome());
        }

        ArrayList<TiposDeVeiculos> lista2 = tiposDeVeiculosBll.getAllTiposDeVeiculos();
        jComboBoxTipoDeVeiculos.removeAllItems();
        jComboBoxTipoDeVeiculos.addItem("<SELECIONE>");

        for (TiposDeVeiculos tipos : lista2) {
            jComboBoxTipoDeVeiculos.addItem(tipos.getNome());
        }
    }

    public void limparCampos() {
        jTextFieldIdModelo.setText("");
        jTextFieldNome.setText("");
        jComboBoxCategoria.removeAll();
        jComboBoxMarca.removeAll();
        jComboBoxTipoDeVeiculos.removeAll();
    }
    
    public void ValidaModelos() {
        Valida.campoVazio(jTextFieldNome.getText(), "Digite o nome do modelo!");
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
        jTextFieldIdModelo = new javax.swing.JTextField();
        jLabelIdPessoaFisica = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jTextFieldNome = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jComboBoxMarca = new javax.swing.JComboBox<>();
        jComboBoxTipoDeVeiculos = new javax.swing.JComboBox<>();
        jComboBoxCategoria = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jButtonCadastrar = new javax.swing.JButton();
        jButtonAlterar = new javax.swing.JButton();
        jButtonRemover = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableModelo = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("[MODELOS DOS VEÍCULOS]");

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Modelo"));

        jTextFieldIdModelo.setEditable(false);

        jLabelIdPessoaFisica.setText("ID");

        jLabel1.setText("NOME");

        jLabel2.setText("MARCA");

        jLabel3.setText("CATEGORIA");

        jLabel4.setText("TIPO DO VEÍCULO");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelIdPessoaFisica)
                    .addComponent(jTextFieldIdModelo, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jTextFieldNome, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(60, 60, 60)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jComboBoxMarca, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(41, 41, 41)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jComboBoxCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(46, 46, 46)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jComboBoxTipoDeVeiculos, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(306, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelIdPessoaFisica)
                .addGap(14, 14, 14)
                .addComponent(jTextFieldIdModelo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxMarca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxTipoDeVeiculos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(67, Short.MAX_VALUE))
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

        jTableModelo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "NOME", "MARCA", "CATEGORIA", "TIPO DO VEÍCULO"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableModelo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableModeloMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTableModelo);
        if (jTableModelo.getColumnModel().getColumnCount() > 0) {
            jTableModelo.getColumnModel().getColumn(0).setMinWidth(40);
            jTableModelo.getColumnModel().getColumn(0).setPreferredWidth(40);
            jTableModelo.getColumnModel().getColumn(0).setMaxWidth(40);
        }

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(jButtonCadastrar)
                        .addGap(299, 299, 299)
                        .addComponent(jButtonAlterar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 338, Short.MAX_VALUE)
                        .addComponent(jButtonRemover)
                        .addGap(44, 44, 44))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonCadastrar)
                    .addComponent(jButtonAlterar)
                    .addComponent(jButtonRemover))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 367, Short.MAX_VALUE)
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
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCadastrarActionPerformed
        try {
            if (jComboBoxMarca.getSelectedItem() == "<SELECIONE>") {
                throw new Exception ("Favor selecione uma marca!");
            }
            
            if (jComboBoxCategoria.getSelectedItem() == "<SELECIONE>") {
                throw new Exception ("Favor selecione uma categoria!");
            }
                      
            if (jComboBoxTipoDeVeiculos.getSelectedItem() == "<SELECIONE>") {
                throw new Exception ("Favor selecione um tipo de veículo!");
            }
            
            ValidaModelos();
            String marcas = jComboBoxMarca.getSelectedItem().toString();
            marca = marcasBll.getMarcasByNome(marcas);
            modelo.setMarcas(marca);

            String categorias = jComboBoxCategoria.getSelectedItem().toString();
            categoria = categoriasBll.getCategoriaByNome(categorias);
            modelo.setCategoria(categoria);

            String tiposDeVeiculos = jComboBoxTipoDeVeiculos.getSelectedItem().toString();
            tipoDeVeiculo = tiposDeVeiculosBll.getTiposDeVeiculosByNome(tiposDeVeiculos);
            modelo.setTiposDeVeiculos(tipoDeVeiculo);

            modelo.setNome(jTextFieldNome.getText());
            modelosBll.addModelos(modelo);

            preencherGridModelos();
            limparCampos();
            
            JOptionPane.showMessageDialog(null, "Modelo cadastrado com sucesso!");
        } catch (Exception error) {
            JOptionPane.showMessageDialog(rootPane, error.getMessage(), "Menssagem", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButtonCadastrarActionPerformed

    private void jButtonAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAlterarActionPerformed
        try {
            if (jTableModelo.getSelectedRow() == -1) {
                throw new Exception("Selecione um modelo a ser alterado!");
            }
            
            if (jComboBoxMarca.getSelectedItem() == "<SELECIONE>") {
                throw new Exception ("Favor selecione uma marca!");
            }
            
            if (jComboBoxCategoria.getSelectedItem() == "<SELECIONE>") {
                throw new Exception ("Favor selecione uma categoria!");
            }
                      
            if (jComboBoxTipoDeVeiculos.getSelectedItem() == "<SELECIONE>") {
                throw new Exception ("Favor selecione um tipo de veículo!");
            }
            
            String marcas = jComboBoxMarca.getSelectedItem().toString();
            marca = marcasBll.getMarcasByNome(marcas);
            modelo.setMarcas(marca);

            String categorias = jComboBoxCategoria.getSelectedItem().toString();
            categoria = categoriasBll.getCategoriaByNome(categorias);
            modelo.setCategoria(categoria);

            String tiposDeVeiculos = jComboBoxTipoDeVeiculos.getSelectedItem().toString();
            tipoDeVeiculo = tiposDeVeiculosBll.getTiposDeVeiculosByNome(tiposDeVeiculos);
            modelo.setTiposDeVeiculos(tipoDeVeiculo);

            modelo.setIden(Integer.parseInt(jTextFieldIdModelo.getText()));
            modelo.setNome(jTextFieldNome.getText());
            modelosBll.updateModelos(modelo);

            preencherGridModelos();
            limparCampos();
            
            JOptionPane.showMessageDialog(null, "Modelo alterado com sucesso!");
        } catch (Exception error) {
            JOptionPane.showMessageDialog(rootPane, error.getMessage(), "Menssagem", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButtonAlterarActionPerformed

    private void jButtonRemoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRemoverActionPerformed
        try {
            if (jTableModelo.getSelectedRow() == -1) {
                throw new Exception("Selecione um modelo a ser removido!");
            }
            
            modelo.setIden(Integer.parseInt(jTextFieldIdModelo.getText()));
            modelosBll.deleteModelos(modelo);
            preencherGridModelos();
            limparCampos();
            
            JOptionPane.showMessageDialog(null, "Modelo removido com sucesso!");
        } catch (Exception error) {
            JOptionPane.showMessageDialog(rootPane, error.getMessage(), "Menssagem", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButtonRemoverActionPerformed

    private void jTableModeloMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableModeloMouseClicked
        try {
            preencherFormularioModelos();
        } catch (Exception error) {
            JOptionPane.showMessageDialog(rootPane, error.getMessage(), "Menssagem", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jTableModeloMouseClicked

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
            java.util.logging.Logger.getLogger(TelaModelos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaModelos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaModelos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaModelos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaModelos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAlterar;
    private javax.swing.JButton jButtonCadastrar;
    private javax.swing.JButton jButtonRemover;
    private javax.swing.JComboBox<String> jComboBoxCategoria;
    private javax.swing.JComboBox<String> jComboBoxMarca;
    private javax.swing.JComboBox<String> jComboBoxTipoDeVeiculos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabelIdPessoaFisica;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableModelo;
    private javax.swing.JTextField jTextFieldIdModelo;
    private javax.swing.JTextField jTextFieldNome;
    // End of variables declaration//GEN-END:variables
}
