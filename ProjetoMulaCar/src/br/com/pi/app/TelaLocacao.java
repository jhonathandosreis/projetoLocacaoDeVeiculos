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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author GustavoDev
 */
public class TelaLocacao extends javax.swing.JFrame {
    
    SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy", new Locale("pt", "BR"));
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
    public static String convertDate(Date dtConsulta) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy", new Locale("pt", "BR"));
            return formatter.format(dtConsulta);
        } catch (Exception e) {
            return null;
        }
    }
    
    public int DiferencaEntreDatas(Date data1, Date data2) throws Exception{
            if(data1.compareTo(data2) >=0 ) throw new Exception("Data de devolução inválida!");
            
            long diferencaMS =  data2.getTime() - data1.getTime();
            long diferencaSegundos = diferencaMS / 1000;
            long diferencaMinutos = diferencaSegundos / 60;
            long diferencaHoras = diferencaMinutos / 60;
            long diferencaDias = diferencaHoras / 24;
            int dias = (int) diferencaDias;
            return dias;
    }
     private void imprimirNaGridVeiculos(ArrayList<Veiculos> listaveiculos){
        try{
            DefaultTableModel model =  (DefaultTableModel) jTable_VEICULOS.getModel();
            model.setNumRows(0);
           
            for(Veiculos veiculo : listaveiculos){
                if(veiculo.getStatus().equals("DISPONIVEL")){
                String[] coluna = new String[6];
                coluna[0]= ""+veiculo.getIden();
                coluna[1]= veiculo.getModelo().getCategoria().getNome();
                coluna[2]= veiculo.getModelo().getTiposDeVeiculos().getNome();
                coluna[3]= veiculo.getModelo().getNome();
                coluna[4]= veiculo.getModelo().getMarcas().getNome();   
                coluna[5]= veiculo.getPlaca();   
                
                model.addRow(coluna);    
                }
                
            }
        }catch(Exception erro){
            JOptionPane.showMessageDialog(rootPane, erro);
        }
    }
    public void PreencherComboboxMotorista() throws Exception{
        jComboBox_MotoristaLocacao.removeAllItems();
        try{
         ArrayList<Motoristas> listaMotoristas = motoristabll.getAllMotoristas();
            for(Motoristas motorista : listaMotoristas){
                if(motorista.getCliente().getStatus().equals("ADIMPLENTE")){
                jComboBox_MotoristaLocacao.addItem(motorista.getIden()+"-"+motorista.getCliente().getNome());
            }
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
             jComboBox_MotoristaLocacao.setEnabled(true);
           // jComboBox_Cliente_Locacao.addItem("<SELECIONE>");
            for (PessoasFisicas pessoaFisica : listaPessoasFisicas) {
                if(pessoaFisica.getCliente().getStatus().equals("ADIMPLENTE")){
               jComboBox_Cliente_Locacao.addItem(pessoaFisica.getCliente().getIden()+"-"+pessoaFisica.getCliente().getNome());
                }
            }      
        }
        
        if(jRadioButtonPJuridica.isSelected()){
            ArrayList<PessoasJuridicas> listaPessoasJuridicas = pessoaJuridicaBll.getAllPessoasJuridicas();
             jComboBox_MotoristaLocacao.setEnabled(true);
            //jComboBox_Cliente_Locacao.addItem("<SELECIONE>");
            for (PessoasJuridicas pessoJurica : listaPessoasJuridicas) {
                 if(pessoJurica.getCliente().getStatus().equals("ADIMPLENTE")){
               jComboBox_Cliente_Locacao.addItem(pessoJurica.getCliente().getIden()+"-"+pessoJurica.getRazaoSocial());
            }
            }
        }
        
        if(JradioButonMotodista.isSelected()){
            ArrayList<Motoristas> listaMotoristas = motoristabll.getAllMotoristas();
            jComboBox_MotoristaLocacao.setEnabled(false);
            //jComboBox_Cliente_Locacao.addItem("<SELECIONE>");
            for(Motoristas motorista : listaMotoristas){
                 if(motorista.getCliente().getStatus().equals("ADIMPLENTE")){
                jComboBox_Cliente_Locacao.addItem(motorista.getCliente().getIden()+"-"+motorista.getCliente().getNome());
            }
            }
        }
        } catch (Exception error) {
            JOptionPane.showMessageDialog(rootPane, error.getMessage(), "Menssagem", JOptionPane.ERROR_MESSAGE);
        }
    }
    public void LimparTelaLocacao(){
        jComboBox_Cliente_Locacao.setSelectedIndex(0);
        jComboBox_MotoristaLocacao.setSelectedIndex(0);
        jTextFieldDataLocacao.setText("");
        jTextFieldDataDevolucao.setText("");
        jTextFieldIDLocacao.setText("");
        jTextArea_informacoesVeiculo.setText("");
        JradioButonMotodista.setSelected(false);
        jRadioButtonPFisica.setSelected(false);
        jRadioButtonPJuridica.setSelected(false);
    }
    public void respostaFinalizacao(String resposta){
      JOptionPane.showMessageDialog(null, resposta);
    }
    TelaFinalizarLocacao fimLocacao = new TelaFinalizarLocacao();
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextFieldIDLocacao = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jComboBox_Cliente_Locacao = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        jRadioButtonPFisica = new javax.swing.JRadioButton();
        jRadioButtonPJuridica = new javax.swing.JRadioButton();
        JradioButonMotodista = new javax.swing.JRadioButton();
        jLabel3 = new javax.swing.JLabel();
        jComboBox_MotoristaLocacao = new javax.swing.JComboBox<>();
        jTextFieldDataLocacao = new javax.swing.JFormattedTextField();
        jTextFieldDataDevolucao = new javax.swing.JFormattedTextField();
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
        jLabel8 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable_locacao = new javax.swing.JTable();
        jButton_Limpar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("[LOCAÇÃO DO VEÍCULO]");

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Locação"));

        jLabel1.setText("ID");

        jTextFieldIDLocacao.setEditable(false);

        jLabel2.setText("CLIENTE");

        jLabel5.setText("CNH MOTORISTA");

        jLabel7.setText("DATA LOCAÇÃO");

        jLabel11.setText("DATA PREVISTA DEVOLUÇÃO");

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

        try {
            jTextFieldDataLocacao.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        try {
            jTextFieldDataDevolucao.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

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
                                    .addComponent(jLabel1)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jTextFieldIDLocacao, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(29, 29, 29)
                                        .addComponent(jRadioButtonPFisica)
                                        .addGap(18, 18, 18)
                                        .addComponent(jRadioButtonPJuridica)
                                        .addGap(18, 18, 18)
                                        .addComponent(JradioButonMotodista)))
                                .addGap(156, 156, 156))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(3, 3, 3)
                                        .addComponent(jComboBox_Cliente_Locacao, javax.swing.GroupLayout.PREFERRED_SIZE, 394, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 100, Short.MAX_VALUE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7)
                                    .addComponent(jTextFieldDataLocacao, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(54, 54, 54)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel11)
                                    .addComponent(jTextFieldDataDevolucao, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(163, 163, 163)))
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
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jTextFieldDataLocacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextFieldDataDevolucao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jTextFieldIDLocacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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

        jButtonCadastrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/pi/icons/salve.png"))); // NOI18N
        jButtonCadastrar.setText("CADASTRAR");
        jButtonCadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCadastrarActionPerformed(evt);
            }
        });

        jButtonAlterar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/pi/icons/editar.png"))); // NOI18N
        jButtonAlterar.setText("ALTERAR");
        jButtonAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAlterarActionPerformed(evt);
            }
        });

        jButtonRemover.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/pi/icons/lixo.png"))); // NOI18N
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

        jLabel8.setText("Filtrar tabela de veículos por:");

        jTable_locacao.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CÓDIGO", "PLACA DO VEÍCULO", "CPF/CNPJ", "KM INICIAL", "DATA DA LOCAÇÃO", "DATA PREVISTA", "VALOR FINAL", "STATUS"
            }
        ));
        jScrollPane1.setViewportView(jTable_locacao);

        jButton_Limpar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/pi/icons/limpar-limpo.png"))); // NOI18N
        jButton_Limpar.setText("LIMPAR");
        jButton_Limpar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_LimparActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel8)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jButton_listarPorCategoria)
                                        .addGap(74, 74, 74)
                                        .addComponent(jButton_listarPorTipo)
                                        .addGap(59, 59, 59)
                                        .addComponent(jButton_listarPorModelo)
                                        .addGap(62, 62, 62)
                                        .addComponent(jButton__listarPorMarca))
                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 784, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 372, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(23, 23, 23)
                                        .addComponent(jLabel6))))
                            .addComponent(jScrollPane1))
                        .addGap(0, 19, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButtonCadastrar)
                        .addGap(190, 190, 190)
                        .addComponent(jButtonAlterar)
                        .addGap(204, 204, 204)
                        .addComponent(jButtonRemover)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton_Limpar)
                        .addGap(174, 174, 174)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jButton_listarPorCategoria)
                    .addComponent(jButton_listarPorTipo)
                    .addComponent(jButton_listarPorModelo)
                    .addComponent(jButton__listarPorMarca)
                    .addComponent(jLabel8))
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonCadastrar)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButtonAlterar)
                        .addComponent(jButtonRemover))
                    .addComponent(jButton_Limpar, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(65, Short.MAX_VALUE))
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
            if(jComboBox_MotoristaLocacao.getSelectedIndex() == -1){
                throw new Exception("Para locar deve indicar quem será o motorista!");
            }
            if(JradioButonMotodista.isSelected()){
                motorista = motoristabll.getMotoristaById(SplitReturnID(jComboBox_Cliente_Locacao.getSelectedItem().toString()));
                cliente = motorista.getCliente();
                locacao.setCliente(cliente);
                locacao.setMotoristas(motorista);
            }
            else{
                Motoristas motorista2 = motoristabll.getMotoristaById(SplitReturnID(jComboBox_MotoristaLocacao.getSelectedItem().toString()));
                locacao.setMotoristas(motorista2);
            }
            if(jRadioButtonPFisica.isSelected()){
                pessoaFisica = pessoaFisicaBll.getPessoasFisicasBy(SplitReturnID(jComboBox_Cliente_Locacao.getSelectedItem().toString()));
                cliente = pessoaFisica.getCliente();
                locacao.setCliente(cliente);
            }
            if(jRadioButtonPJuridica.isSelected()){
                pessoaJuridica = pessoaJuridicaBll.getPessoasJuridicasBy(SplitReturnID(jComboBox_Cliente_Locacao.getSelectedItem().toString()));
                locacao.setCliente(pessoaJuridica.getCliente());
            }
            
            int id = Integer.parseInt(jTable_VEICULOS.getValueAt(jTable_VEICULOS.getSelectedRow(), 0).toString());
            veiculo = veiculoBll.getVeiculosById(id);
            locacao.setVeiculos(veiculo);
           
            Date dataLocacao = formato.parse(jTextFieldDataLocacao.getText());
            Date dataPrevista = formato.parse(jTextFieldDataDevolucao.getText());
            locacao.setDataDeLocacao(dataLocacao);
            locacao.setDataPrevistDeDevolucao(dataPrevista);
            locacao.setKmInicial(veiculo.getQuilometragem());
            // CALCULO
            int dias = DiferencaEntreDatas(dataLocacao,dataPrevista);
            float valorDiaria = veiculo.getModelo().getCategoria().getValorDiarioLocacao();
            float valorLocacao = valorDiaria * dias;
            float valorCaucao = (float) (valorLocacao * 1.5);
            float valorSeguro =  (float) (valorLocacao * 0.009);
            float valorTotal = valorLocacao + valorCaucao + valorSeguro;
            
            locacao.setValorLocacao(valorLocacao);
            locacao.setValorCaucao(valorCaucao);
            locacao.setValorSeguro(valorSeguro);
            locacao.setValorTotalPago(valorTotal); 
            locacao.setStatus("ATIVA");
            
            
            if(fimLocacao == null){
                fimLocacao = new TelaFinalizarLocacao();
                fimLocacao.setLocationRelativeTo(null);
                fimLocacao.setVisible(true);
                fimLocacao.setResizable(false);
            }
            else{
                fimLocacao.setLocationRelativeTo(null);
                fimLocacao.setVisible(true);
                fimLocacao.setResizable(false);
            }
                fimLocacao.enviaLocacao(this,locacao);
            
           
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
        imprimirNaGridVeiculos(objeto.OrdenarListaVeiculos());
        } catch (Exception error) {
            JOptionPane.showMessageDialog(rootPane, error.getMessage(), "Menssagem", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton_listarPorCategoriaActionPerformed

    private void jButton_listarPorTipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_listarPorTipoActionPerformed
        try{
        TemplateOrdenadoPorMarca objeto = new TemplateOrdenadoPorMarca();
        imprimirNaGridVeiculos(objeto.OrdenarListaVeiculos());
        } catch (Exception error) {
            JOptionPane.showMessageDialog(rootPane, error.getMessage(), "Menssagem", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton_listarPorTipoActionPerformed

    private void jButton_listarPorModeloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_listarPorModeloActionPerformed
        try{
         TemplateOrdenadoPorModelo objeto = new TemplateOrdenadoPorModelo();
         imprimirNaGridVeiculos(objeto.OrdenarListaVeiculos());
        } catch (Exception error) {
            JOptionPane.showMessageDialog(rootPane, error.getMessage(), "Menssagem", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton_listarPorModeloActionPerformed

    private void jButton__listarPorMarcaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton__listarPorMarcaActionPerformed
        try{
         TemplateOrdenadoPorMarca objeto = new TemplateOrdenadoPorMarca();
         imprimirNaGridVeiculos(objeto.OrdenarListaVeiculos());
        } catch (Exception error) {
            JOptionPane.showMessageDialog(rootPane, error.getMessage(), "Menssagem", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton__listarPorMarcaActionPerformed

    private void jTable_VEICULOSMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_VEICULOSMouseClicked
        try{ 
        int id = Integer.parseInt(jTable_VEICULOS.getValueAt(jTable_VEICULOS.getSelectedRow(), 0).toString());
         veiculo = veiculoBll.getVeiculosById(id);
         String saida = "MODELO: "+veiculo.getModelo().getNome();
         saida+= "\nMARCA: "+veiculo.getModelo().getMarcas().getNome();
         saida+= "\nPLACA: "+veiculo.getPlaca();
         saida+= "\nKM ATUAL: "+veiculo.getQuilometragem();
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

    private void jButton_LimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_LimparActionPerformed
        LimparTelaLocacao();
    }//GEN-LAST:event_jButton_LimparActionPerformed

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
    private javax.swing.JButton jButton_Limpar;
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
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JRadioButton jRadioButtonPFisica;
    private javax.swing.JRadioButton jRadioButtonPJuridica;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable_VEICULOS;
    private javax.swing.JTable jTable_locacao;
    private javax.swing.JTextArea jTextArea_informacoesVeiculo;
    private javax.swing.JFormattedTextField jTextFieldDataDevolucao;
    private javax.swing.JFormattedTextField jTextFieldDataLocacao;
    private javax.swing.JTextField jTextFieldIDLocacao;
    // End of variables declaration//GEN-END:variables
}
