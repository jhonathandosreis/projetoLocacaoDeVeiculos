/*
 *  -------------------------------------------------------------------------------------------------->
 *  Licença    : MIT - Copyright 2019 Jhonathan, Gustavo e Miguel 
 *  Criado em  : 20/11/2020 22:11:36 
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
import br.com.pi.bll.ClientesBll;
import br.com.pi.bll.LocacoesBll;
import br.com.pi.bll.MotoristasBll;
import br.com.pi.bll.PessoasFisicasBll;
import br.com.pi.bll.PessoasJuridicasBll;
import br.com.pi.bll.VeiculosBll;
import br.com.pi.model.Categorias;
import br.com.pi.model.Clientes;
import br.com.pi.model.Locacoes;
import br.com.pi.model.Motoristas;
import br.com.pi.model.PessoasFisicas;
import br.com.pi.model.PessoasJuridicas;
import br.com.pi.model.Veiculos;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import br.com.pi.util.*;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author GustavoDev
 */
public class TelaLocacao extends javax.swing.JFrame {
    
    Locacoes locacao;
    LocacoesBll locacaoBll;
    Clientes cliente;
    ClientesBll clienteBll;
    Motoristas motorista;
    MotoristasBll motoristabll;
    PessoasFisicas pessoaFisica;
    PessoasFisicasBll pessoaFisicaBll;
    PessoasJuridicas pessoaJuridica;     
    PessoasJuridicasBll pessoaJuridicaBll;
    Veiculos veiculo;
    VeiculosBll veiculoBll;
    Categorias categoria;
    CategoriasBll categoriaBll;
    
    public TelaLocacao() {
        initComponents();
        try{
           locacao = new Locacoes();
           locacaoBll = new LocacoesBll();
           cliente = new Clientes();
           clienteBll = new ClientesBll();
           motorista = new Motoristas();
           motoristabll = new MotoristasBll();
           pessoaFisica = new PessoasFisicas();
           pessoaFisicaBll = new PessoasFisicasBll();
           pessoaJuridica = new PessoasJuridicas();
           pessoaJuridicaBll = new PessoasJuridicasBll();
           veiculo = new Veiculos();
           veiculoBll = new VeiculosBll();
           categoria = new Categorias();
           categoriaBll = new CategoriasBll();
           
           PreencherComboboxMotorista();
            
        }catch(Exception error){
            JOptionPane.showMessageDialog(rootPane, error.getMessage(), "Menssagem", JOptionPane.ERROR_MESSAGE);
        }
        this.setLocationRelativeTo(null);
    }
     private void imprimirNaGrid(ArrayList<Veiculos> listaveiculos){
        try{
            DefaultTableModel model =  (DefaultTableModel) jTable_VEICULOS.getModel();
            model.setNumRows(0);
           
            for(Veiculos veiculo : listaveiculos){
                String[] coluna = new String[6];
                coluna[0]= ""+veiculo.getIden();
                coluna[1]= veiculo.getModelo().getCategoria().getNome();
                coluna[2]= veiculo.getModelo().getTiposDeVeiculos().getNome();
                coluna[3]= veiculo.getModelo().getNome();
                coluna[4]= veiculo.getModelo().getMarcas().getNome();   
                coluna[5]= veiculo.getPlaca(); 
                
                model.addRow(coluna);                
            }
        }catch(Exception erro){
            JOptionPane.showMessageDialog(rootPane, erro);
        }
    }
    public void PreencherComboboxMotorista() throws Exception{
        jComboBox_MotoristaLocacao.removeAllItems();
        try{
        ArrayList<Motoristas> listaMotoristas = motoristabll.getAllMotoristas();
           // jComboBox_Cliente_Locacao.addItem("<SELECIONE>");
            for(Motoristas motorista : listaMotoristas){
                jComboBox_MotoristaLocacao.addItem( motorista.getIden()+"-"+motorista.getCliente().getNome());
            }
            } catch (Exception error) {
            JOptionPane.showMessageDialog(rootPane, error.getMessage(), "Menssagem", JOptionPane.ERROR_MESSAGE);
        }
    }
   
    public void PreencherComboboxCliente() throws Exception{
        jComboBox_Cliente_Locacao.removeAllItems();
        try{
        if(jRadioButtonPFisica.isSelected()){
            
            ArrayList<PessoasFisicas> listaPessoasFisicas = pessoaFisicaBll.getAllPessoasFisicas();
           // jComboBox_Cliente_Locacao.addItem("<SELECIONE>");
            for (PessoasFisicas pessoaFisica : listaPessoasFisicas) {
               jComboBox_Cliente_Locacao.addItem(pessoaFisica.getCliente().getIden()+"-"+pessoaFisica.getCliente().getNome());
            }      
        }
        
        if(jRadioButtonPJuridica.isSelected()){
            ArrayList<PessoasJuridicas> listaPessoasJuridicas = pessoaJuridicaBll.getAllPessoasJuridicas();
            //jComboBox_Cliente_Locacao.addItem("<SELECIONE>");
            for (PessoasJuridicas pessoJurica : listaPessoasJuridicas) {
               jComboBox_Cliente_Locacao.addItem(pessoJurica.getCliente().getIden()+"-"+pessoJurica.getRazaoSocial());
            }
        }
        
        if(JradioButonMotodista.isSelected()){
            ArrayList<Motoristas> listaMotoristas = motoristabll.getAllMotoristas();
            //jComboBox_Cliente_Locacao.addItem("<SELECIONE>");
            for(Motoristas motorista : listaMotoristas){
                jComboBox_Cliente_Locacao.addItem(motorista.getCliente().getIden()+"-"+motorista.getCliente().getNome());
            }
        }
        } catch (Exception error) {
            JOptionPane.showMessageDialog(rootPane, error.getMessage(), "Menssagem", JOptionPane.ERROR_MESSAGE);
        }
    }
    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify
     * this code. The content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextFieldLocacao = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jTextFieldDataLocacao = new javax.swing.JTextField();
        jComboBox_Cliente_Locacao = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        jTextFieldDataDevolucao = new javax.swing.JTextField();
        jRadioButtonPFisica = new javax.swing.JRadioButton();
        jRadioButtonPJuridica = new javax.swing.JRadioButton();
        JradioButonMotodista = new javax.swing.JRadioButton();
        jLabel3 = new javax.swing.JLabel();
        jComboBox_MotoristaLocacao = new javax.swing.JComboBox<>();
        jButtonCadastrar = new javax.swing.JButton();
        jButtonAlterar = new javax.swing.JButton();
        jButtonRemover = new javax.swing.JButton();
        jButton_listarPorCategoria = new javax.swing.JButton();
        jButton_listarPorTipo = new javax.swing.JButton();
        jButton_listarPorModelo = new javax.swing.JButton();
        jButton__listarPorMarca = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea_informacoesVeiculo = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable_VEICULOS = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("[LOCAÇÃO DO VEÍCULO]");

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Locação"));

        jLabel1.setText("ID");

        jTextFieldLocacao.setEditable(false);

        jLabel2.setText("CLIENTE");

        jLabel5.setText("CNH MOTORISTA");

        jLabel7.setText("DATA LOCAÇÃO");

        jTextFieldDataLocacao.setText("/  /");

        jLabel11.setText("DATA PREVISTA DEVOLUÇÃO");

        jTextFieldDataDevolucao.setText("/  /");

        buttonGroup1.add(jRadioButtonPFisica);
        jRadioButtonPFisica.setText("Pessoa Fisica");
        jRadioButtonPFisica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonPFisicaActionPerformed(evt);
            }
        });

        buttonGroup1.add(jRadioButtonPJuridica);
        jRadioButtonPJuridica.setText("Pessoa Juridica");
        jRadioButtonPJuridica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonPJuridicaActionPerformed(evt);
            }
        });

        buttonGroup1.add(JradioButonMotodista);
        JradioButonMotodista.setText("Motorista");
        JradioButonMotodista.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JradioButonMotodistaActionPerformed(evt);
            }
        });

        jLabel3.setText("MOTORISTA");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(3, 3, 3)
                                        .addComponent(jComboBox_Cliente_Locacao, javax.swing.GroupLayout.PREFERRED_SIZE, 394, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7)
                                    .addComponent(jTextFieldDataLocacao, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(76, 76, 76)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel11)
                                    .addComponent(jTextFieldDataDevolucao, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(163, 163, 163))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jTextFieldLocacao, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(29, 29, 29)
                                        .addComponent(jRadioButtonPFisica)
                                        .addGap(18, 18, 18)
                                        .addComponent(jRadioButtonPJuridica)
                                        .addGap(18, 18, 18)
                                        .addComponent(JradioButonMotodista)))
                                .addGap(156, 156, 156)))
                        .addComponent(jLabel5)
                        .addGap(124, 124, 124))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jComboBox_MotoristaLocacao, javax.swing.GroupLayout.PREFERRED_SIZE, 397, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 318, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addGap(24, 24, 24)
                                    .addComponent(jTextFieldDataLocacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel7)
                                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jTextFieldDataDevolucao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jTextFieldLocacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jRadioButtonPFisica)
                                    .addComponent(jRadioButtonPJuridica)
                                    .addComponent(JradioButonMotodista))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(jComboBox_Cliente_Locacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(29, 29, 29)
                        .addComponent(jLabel3)
                        .addGap(30, 30, 30)
                        .addComponent(jComboBox_MotoristaLocacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(133, 133, 133))
        );

        jButtonCadastrar.setText("CADASTRAR");
        jButtonCadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCadastrarActionPerformed(evt);
            }
        });

        jButtonAlterar.setText("ALTERAR");
        jButtonAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAlterarActionPerformed(evt);
            }
        });

        jButtonRemover.setText("REMOVER");
        jButtonRemover.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRemoverActionPerformed(evt);
            }
        });

        jButton_listarPorCategoria.setText("CATEGORIA");
        jButton_listarPorCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_listarPorCategoriaActionPerformed(evt);
            }
        });

        jButton_listarPorTipo.setText("TIPO DE VEICULO");
        jButton_listarPorTipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_listarPorTipoActionPerformed(evt);
            }
        });

        jButton_listarPorModelo.setText("MODELO");
        jButton_listarPorModelo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_listarPorModeloActionPerformed(evt);
            }
        });

        jButton__listarPorMarca.setText("MARCA");
        jButton__listarPorMarca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton__listarPorMarcaActionPerformed(evt);
            }
        });

        jLabel6.setText("DADOS DO VEICULO SELECIONADO");

        jTextArea_informacoesVeiculo.setColumns(20);
        jTextArea_informacoesVeiculo.setRows(5);
        jScrollPane2.setViewportView(jTextArea_informacoesVeiculo);

        jTable_VEICULOS.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "CATEGORIA", "TIPO DE VEÍCULO", "MODELO", "MARCA", "PLACA"
            }
        ));
        jTable_VEICULOS.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable_VEICULOSMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jTable_VEICULOS);

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
                        .addGap(29, 29, 29)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 784, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jButtonCadastrar)
                                .addGap(256, 256, 256)
                                .addComponent(jButtonAlterar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButtonRemover))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jButton_listarPorCategoria)
                                .addGap(42, 42, 42)
                                .addComponent(jButton_listarPorTipo)
                                .addGap(46, 46, 46)
                                .addComponent(jButton_listarPorModelo)
                                .addGap(63, 63, 63)
                                .addComponent(jButton__listarPorMarca)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 372, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton_listarPorCategoria)
                    .addComponent(jButton_listarPorTipo)
                    .addComponent(jButton_listarPorModelo)
                    .addComponent(jButton__listarPorMarca)
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(41, 41, 41)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonCadastrar)
                    .addComponent(jButtonAlterar)
                    .addComponent(jButtonRemover))
                .addContainerGap(269, Short.MAX_VALUE))
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
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCadastrarActionPerformed
         try {
            if (jTable_VEICULOS.getSelectedRow() == -1) {
                throw new Exception("Selecione veículo na tabela para ser locado!");
            }
            if(JradioButonMotodista.isSelected()){
                motorista = motoristabll.getMotoristaById(SplitReturnID(jComboBox_Cliente_Locacao.getSelectedItem().toString()));
                locacao.setCliente(motorista.getCliente());
            }
            if(jRadioButtonPFisica.isSelected()){
                pessoaFisica = pessoaFisicaBll.getPessoasFisicasBy(SplitReturnID(jComboBox_Cliente_Locacao.getSelectedItem().toString()));
                locacao.setCliente(pessoaFisica.getCliente());
            }
            if(jRadioButtonPJuridica.isSelected()){
                pessoaJuridica = pessoaJuridicaBll.getPessoasJuridicasBy(SplitReturnID(jComboBox_Cliente_Locacao.getSelectedItem().toString()));
                locacao.setCliente(pessoaJuridica.getCliente());
            }
            
             } catch (Exception error) {
            JOptionPane.showMessageDialog(rootPane, error.getMessage(), "Menssagem", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButtonCadastrarActionPerformed

    private void jButtonAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAlterarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonAlterarActionPerformed

    private void jButtonRemoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRemoverActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonRemoverActionPerformed
    
    private int SplitReturnID (String combo){
  
            String[] split = combo.split("-");
            int id = Integer.parseInt(split[0]);
            return id;
          
    }
    
    private void jButton_listarPorCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_listarPorCategoriaActionPerformed
       try{
        TemplateOrdenadoPorCategoria objeto = new TemplateOrdenadoPorCategoria();
        imprimirNaGrid(objeto.OrdenarListaVeiculos());
        } catch (Exception error) {
            JOptionPane.showMessageDialog(rootPane, error.getMessage(), "Menssagem", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton_listarPorCategoriaActionPerformed

    private void jButton_listarPorTipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_listarPorTipoActionPerformed
        try{
        TemplateOrdenadoPorMarca objeto = new TemplateOrdenadoPorMarca();
        imprimirNaGrid(objeto.OrdenarListaVeiculos());
        } catch (Exception error) {
            JOptionPane.showMessageDialog(rootPane, error.getMessage(), "Menssagem", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton_listarPorTipoActionPerformed

    private void jButton_listarPorModeloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_listarPorModeloActionPerformed
        try{
         TemplateOrdenadoPorModelo objeto = new TemplateOrdenadoPorModelo();
         imprimirNaGrid(objeto.OrdenarListaVeiculos());
        } catch (Exception error) {
            JOptionPane.showMessageDialog(rootPane, error.getMessage(), "Menssagem", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton_listarPorModeloActionPerformed

    private void jButton__listarPorMarcaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton__listarPorMarcaActionPerformed
        try{
         TemplateOrdenadoPorMarca objeto = new TemplateOrdenadoPorMarca();
         imprimirNaGrid(objeto.OrdenarListaVeiculos());
        } catch (Exception error) {
            JOptionPane.showMessageDialog(rootPane, error.getMessage(), "Menssagem", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton__listarPorMarcaActionPerformed

    private void jTable_VEICULOSMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_VEICULOSMouseClicked
        try{ 
        int id = Integer.parseInt(jTable_VEICULOS.getValueAt(jTable_VEICULOS.getSelectedRow(), 0).toString());
         veiculo = veiculoBll.getVeiculosById(id);
         String saida = "MODELO: "+veiculo.getModelo().getNome();
         saida+= "\nMARCA: "+veiculo.getModelo().getMarcas();
         saida+= "\nPLACA: "+veiculo.getPlaca();
         jTextArea_informacoesVeiculo.setText(saida);
         } catch (Exception error) {
            JOptionPane.showMessageDialog(rootPane, error.getMessage(), "Menssagem", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jTable_VEICULOSMouseClicked

    private void JradioButonMotodistaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JradioButonMotodistaActionPerformed
        try{
            PreencherComboboxCliente();
        } catch (Exception error) {
            JOptionPane.showMessageDialog(rootPane, error.getMessage(), "Menssagem", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_JradioButonMotodistaActionPerformed

    private void jRadioButtonPJuridicaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonPJuridicaActionPerformed
        try{
            PreencherComboboxCliente();
        } catch (Exception error) {
            JOptionPane.showMessageDialog(rootPane, error.getMessage(), "Menssagem", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jRadioButtonPJuridicaActionPerformed

    private void jRadioButtonPFisicaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonPFisicaActionPerformed
        try{
            PreencherComboboxCliente();
        } catch (Exception error) {
            JOptionPane.showMessageDialog(rootPane, error.getMessage(), "Menssagem", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jRadioButtonPFisicaActionPerformed

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
            java.util.logging.Logger.getLogger(TelaLocacao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaLocacao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaLocacao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaLocacao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaLocacao().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton JradioButonMotodista;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButtonAlterar;
    private javax.swing.JButton jButtonCadastrar;
    private javax.swing.JButton jButtonRemover;
    private javax.swing.JButton jButton__listarPorMarca;
    private javax.swing.JButton jButton_listarPorCategoria;
    private javax.swing.JButton jButton_listarPorModelo;
    private javax.swing.JButton jButton_listarPorTipo;
    private javax.swing.JComboBox<String> jComboBox_Cliente_Locacao;
    private javax.swing.JComboBox<String> jComboBox_MotoristaLocacao;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JRadioButton jRadioButtonPFisica;
    private javax.swing.JRadioButton jRadioButtonPJuridica;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable_VEICULOS;
    private javax.swing.JTextArea jTextArea_informacoesVeiculo;
    private javax.swing.JTextField jTextFieldDataDevolucao;
    private javax.swing.JTextField jTextFieldDataLocacao;
    private javax.swing.JTextField jTextFieldLocacao;
    // End of variables declaration//GEN-END:variables
}
