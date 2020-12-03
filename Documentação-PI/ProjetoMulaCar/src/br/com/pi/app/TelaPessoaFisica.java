/*
 *  -------------------------------------------------------------------------------------------------->
 *  Licença    : MIT - Copyright 2019 Jhonathan, Gustavo e Miguel 
 *  Criado em  : 19/11/2020 19:18:36 
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

import br.com.pi.bll.CidadesBll;
import br.com.pi.bll.ClientesBll;
import br.com.pi.bll.EnderecosBll;
import br.com.pi.bll.PessoasFisicasBll;
import br.com.pi.model.Cidades;
import br.com.pi.model.Clientes;
import br.com.pi.model.Enderecos;
import br.com.pi.model.PessoasFisicas;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author jhonlinux
 */
public class TelaPessoaFisica extends javax.swing.JFrame {
    
    PessoasFisicas pessoaFisica;
    PessoasFisicasBll pessoaFisicaBll;
    Clientes cliente;
    ClientesBll clienteBll;
    Enderecos endereco;
    EnderecosBll enderecoBll;
    Cidades cidade;
    CidadesBll cidadeBll;
    
    public TelaPessoaFisica() {
        initComponents();
        
        try{
            
        pessoaFisica = new PessoasFisicas();
        pessoaFisicaBll = new PessoasFisicasBll();
        cliente = new Clientes();
        clienteBll = new ClientesBll();
        endereco = new Enderecos();
        enderecoBll = new EnderecosBll();
        cidade = new Cidades();
        cidadeBll = new CidadesBll();
        
        preencherComboboxCidades();
        preencherGridPessoaFisica();
        
        }catch(Exception error){
            JOptionPane.showMessageDialog(rootPane, error.getMessage(), "Menssagem", JOptionPane.ERROR_MESSAGE);
        }
        
        this.setLocationRelativeTo(null);
    }
    
     public void preencherGridPessoaFisica() throws Exception {

        try {
            DefaultTableModel tablePessoasFisicas = (DefaultTableModel) jTableConsultaPessoaFisica.getModel();
            tablePessoasFisicas.setRowCount(0);

            Object[] coluna = new Object[8];

            ArrayList<PessoasFisicas> listaPessoasFisicas = pessoaFisicaBll.getAllPessoasFisicas();

            for (PessoasFisicas pessoaFisica : listaPessoasFisicas) {

                coluna[0] = pessoaFisica.getIden();
                coluna[1] = pessoaFisica.getCliente().getNome();
                coluna[2] = pessoaFisica.getRg();
                coluna[3] = pessoaFisica.getCpf();
                coluna[4] = pessoaFisica.getCliente().getEmail();
                coluna[5] = pessoaFisica.getCliente().getTelefone();
                coluna[6] = pessoaFisica.getCliente().getEnderecos().getLogradouro();
                coluna[7] = pessoaFisica.getCliente().getEnderecos().getCep();
           
                tablePessoasFisicas.addRow(coluna);
            }
        } catch (Exception error) {
            JOptionPane.showMessageDialog(null, error.getMessage(), "Menssagem", JOptionPane.ERROR_MESSAGE);
        }
    }
     
     public void preencherFormularioPessoaJuridica() throws Exception {
       
        int id = Integer.parseInt(jTableConsultaPessoaFisica.getValueAt(jTableConsultaPessoaFisica.getSelectedRow(), 0).toString());
        pessoaFisica = pessoaFisicaBll.getPessoasFisicasBy(id);
        
        jTextFieldIdPessoaFisica.setText(""+pessoaFisica.getIden());
        jTextFieldRGPessoaFisica.setText(""+pessoaFisica.getRg());
        jTextFieldCPFPessoaFisica.setText(""+pessoaFisica.getCpf());
        jTextFieldNomePessoaFisica.setText(pessoaFisica.getCliente().getNome());
        jTextFieldCepPessoaFisica.setText(""+pessoaFisica.getCliente().getEnderecos().getCep());
        jTextFieldLogradouroPessoaFisica.setText(pessoaFisica.getCliente().getEnderecos().getLogradouro());
        jTextFieldNumeroPessoaFisica.setText(""+pessoaFisica.getCliente().getEnderecos().getNumero());
        jTextFieldComplementoPessoaFisica.setText(pessoaFisica.getCliente().getEnderecos().getComplemento());
        jTextFieldRuaPessoaFisica.setText(pessoaFisica.getCliente().getEnderecos().getRua());
        jComboBox_Cidades.setSelectedItem(pessoaFisica.getCliente().getEnderecos().getCidade());
        jTextFieldTelefonePessoaFisica.setText(""+pessoaFisica.getCliente().getTelefone());
        jTextFieldEmailPessoaFisica.setText(""+pessoaFisica.getCliente().getTelefone());
        
    }
    
     public void preencherComboboxCidades() throws Exception{
           try{
            jComboBox_Cidades.removeAllItems();
            ArrayList<Cidades> listaCidades = cidadeBll.getAllCidades();
            
            for (Cidades cidade : listaCidades) {
               jComboBox_Cidades.addItem(cidade.getNome());
            }  
           }catch(Exception error){
                JOptionPane.showMessageDialog(rootPane, error.getMessage(), "Menssagem", JOptionPane.ERROR_MESSAGE);
           }
    }
     public void  limparCampos(){
         
     }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify
     * this code. The content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1PessoaFisica = new javax.swing.JPanel();
        jTabbedPane1PessoaFisica = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel2PessoaFisica = new javax.swing.JPanel();
        jLabelIdPessoaFisica = new javax.swing.JLabel();
        jTextFieldIdPessoaFisica = new javax.swing.JTextField();
        jLabelCPFPessoaFisica = new javax.swing.JLabel();
        jTextFieldCPFPessoaFisica = new javax.swing.JTextField();
        jLabelNomePessoaFisica = new javax.swing.JLabel();
        jTextFieldNomePessoaFisica = new javax.swing.JTextField();
        jLabel3RGPessoaFisica = new javax.swing.JLabel();
        jTextFieldRGPessoaFisica = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jTextFieldCepPessoaFisica = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTextFieldLogradouroPessoaFisica = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTextFieldNumeroPessoaFisica = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTextFieldComplementoPessoaFisica = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jTextFieldRuaPessoaFisica = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jComboBox_Cidades = new javax.swing.JComboBox<>();
        jTextField_UF = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jLabel4CPFPessoaFisica = new javax.swing.JLabel();
        jTextFieldTelefonePessoaFisica = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jTextFieldEmailPessoaFisica = new javax.swing.JTextField();
        jButtonCadastrar = new javax.swing.JButton();
        jButtonAlterar = new javax.swing.JButton();
        jButtonRemover = new javax.swing.JButton();
        jButtonLimpar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jTextFieldPesquisarPessoaFisica = new javax.swing.JTextField();
        jButtonConsultar = new javax.swing.JButton();
        jButtonLimparConsulta = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableConsultaPessoaFisica = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("[PESSOAS FÍSICA]");

        jPanel2PessoaFisica.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Dados Gerais"));

        jLabelIdPessoaFisica.setText("ID");

        jTextFieldIdPessoaFisica.setEditable(false);

        jLabelCPFPessoaFisica.setText("CPF");

        jTextFieldCPFPessoaFisica.setText("000.000.000-00");

        jLabelNomePessoaFisica.setText("Nome");

        jLabel3RGPessoaFisica.setText("RG");

        jTextFieldRGPessoaFisica.setText("000000-0");

        javax.swing.GroupLayout jPanel2PessoaFisicaLayout = new javax.swing.GroupLayout(jPanel2PessoaFisica);
        jPanel2PessoaFisica.setLayout(jPanel2PessoaFisicaLayout);
        jPanel2PessoaFisicaLayout.setHorizontalGroup(
            jPanel2PessoaFisicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2PessoaFisicaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2PessoaFisicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelNomePessoaFisica)
                    .addGroup(jPanel2PessoaFisicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(jPanel2PessoaFisicaLayout.createSequentialGroup()
                            .addGroup(jPanel2PessoaFisicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabelIdPessoaFisica)
                                .addComponent(jTextFieldIdPessoaFisica, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(18, 18, 18)
                            .addGroup(jPanel2PessoaFisicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel3RGPessoaFisica)
                                .addComponent(jTextFieldRGPessoaFisica, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel2PessoaFisicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabelCPFPessoaFisica)
                                .addComponent(jTextFieldCPFPessoaFisica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(492, 492, 492))
                        .addComponent(jTextFieldNomePessoaFisica, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 799, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2PessoaFisicaLayout.setVerticalGroup(
            jPanel2PessoaFisicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2PessoaFisicaLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel2PessoaFisicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelIdPessoaFisica)
                    .addComponent(jLabelCPFPessoaFisica)
                    .addComponent(jLabel3RGPessoaFisica))
                .addGap(14, 14, 14)
                .addGroup(jPanel2PessoaFisicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldIdPessoaFisica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldCPFPessoaFisica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldRGPessoaFisica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabelNomePessoaFisica)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextFieldNomePessoaFisica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Dados de Endereço"));

        jLabel2.setText("CEP");

        jTextFieldCepPessoaFisica.setText("00-000-000");

        jLabel3.setText("LOGRADOURO");

        jLabel4.setText("N°");

        jLabel5.setText("COMPLEMENTO");

        jLabel6.setText("RUA");

        jLabel7.setText("CIDADE");

        jLabel8.setText("UF");

        jComboBox_Cidades.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_CidadesActionPerformed(evt);
            }
        });

        jTextField_UF.setEditable(false);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jTextFieldCepPessoaFisica, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(33, 33, 33)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jTextFieldLogradouroPessoaFisica, javax.swing.GroupLayout.PREFERRED_SIZE, 677, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldComplementoPessoaFisica, javax.swing.GroupLayout.PREFERRED_SIZE, 439, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7)
                            .addComponent(jComboBox_Cidades, javax.swing.GroupLayout.PREFERRED_SIZE, 439, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jTextFieldRuaPessoaFisica)
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel8)
                                        .addComponent(jTextField_UF, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(264, 264, 264))))))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextFieldNumeroPessoaFisica, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addContainerGap(215, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldCepPessoaFisica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldLogradouroPessoaFisica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldNumeroPessoaFisica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldComplementoPessoaFisica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldRuaPessoaFisica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox_Cidades, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField_UF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Dados para Contato"));

        jLabel4CPFPessoaFisica.setText("TELEFONE");

        jTextFieldTelefonePessoaFisica.setText("(62) 9 9999-9999");

        jLabel1.setText("E-Mail");

        jTextFieldEmailPessoaFisica.setText("prototipação@hotmail.com");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4CPFPessoaFisica)
                    .addComponent(jTextFieldTelefonePessoaFisica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(74, 74, 74)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jTextFieldEmailPessoaFisica, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(14, 14, 14)
                        .addComponent(jTextFieldEmailPessoaFisica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel4CPFPessoaFisica)
                        .addGap(14, 14, 14)
                        .addComponent(jTextFieldTelefonePessoaFisica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(29, Short.MAX_VALUE))
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

        jButtonRemover.setText("EXCLUIR");

        jButtonLimpar.setText("LIMPAR");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel2PessoaFisica, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addComponent(jButtonCadastrar)
                        .addGap(150, 150, 150)
                        .addComponent(jButtonAlterar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonRemover)
                        .addGap(162, 162, 162)
                        .addComponent(jButtonLimpar)
                        .addGap(57, 57, 57)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jPanel2PessoaFisica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 73, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonCadastrar)
                    .addComponent(jButtonAlterar)
                    .addComponent(jButtonRemover)
                    .addComponent(jButtonLimpar))
                .addGap(26, 26, 26))
        );

        jTabbedPane1PessoaFisica.addTab("Dados Gerais", jPanel1);

        jTextFieldPesquisarPessoaFisica.setText("Digite aqui o objeto de sua pesquisa.....");

        jButtonConsultar.setText("CONSULTAR");

        jButtonLimparConsulta.setText("LIMPAR");

        jTableConsultaPessoaFisica.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "NOME", "RG", "CPF", "EMAIL", "TELEFONE", "LOGRADOURO", "CEP"
            }
        ));
        jTableConsultaPessoaFisica.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableConsultaPessoaFisicaMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTableConsultaPessoaFisica);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 747, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jTextFieldPesquisarPessoaFisica, javax.swing.GroupLayout.PREFERRED_SIZE, 561, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonConsultar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonLimparConsulta)))
                .addContainerGap(352, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldPesquisarPessoaFisica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonConsultar)
                    .addComponent(jButtonLimparConsulta))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(151, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1PessoaFisica.addTab("Consulta Cliente", jPanel2);

        javax.swing.GroupLayout jPanel1PessoaFisicaLayout = new javax.swing.GroupLayout(jPanel1PessoaFisica);
        jPanel1PessoaFisica.setLayout(jPanel1PessoaFisicaLayout);
        jPanel1PessoaFisicaLayout.setHorizontalGroup(
            jPanel1PessoaFisicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1PessoaFisicaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1PessoaFisica)
                .addContainerGap())
        );
        jPanel1PessoaFisicaLayout.setVerticalGroup(
            jPanel1PessoaFisicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1PessoaFisicaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1PessoaFisica)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1PessoaFisica, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1PessoaFisica, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCadastrarActionPerformed
       
           try{
           cidade = cidadeBll.getCidadeNome(jComboBox_Cidades.getSelectedItem().toString());
           endereco.setCidade(cidade);
           endereco.setCep(Double.parseDouble(jTextFieldCepPessoaFisica.getText()));
           endereco.setLogradouro(jTextFieldLogradouroPessoaFisica.getText());
           endereco.setComplemento(jTextFieldComplementoPessoaFisica.getText());
           endereco.setNumero(Float.parseFloat(jTextFieldNumeroPessoaFisica.getText()));
           endereco.setRua(jTextFieldRuaPessoaFisica.getText());
           enderecoBll.AddEndereco(endereco);
           double cep = endereco.getCep();
           endereco = enderecoBll.getConsultaPorCEP(cep);
           
           cliente.setEnderecos(endereco);
           cliente.setNome(jTextFieldNomePessoaFisica.getText());
           cliente.setTelefone(Double.parseDouble(jTextFieldTelefonePessoaFisica.getText()));
           cliente.setEmail(jTextFieldEmailPessoaFisica.getText());
           clienteBll.addClientes(cliente);
           double clienteTelefone = cliente.getTelefone();
           cliente = clienteBll.getClienteByTelefone(clienteTelefone);
           
           pessoaFisica.setCliente(cliente);
           pessoaFisica.setRg(Integer.parseInt(jTextFieldRGPessoaFisica.getText()));
           pessoaFisica.setCpf(Double.parseDouble(jTextFieldCPFPessoaFisica.getText()));       
           pessoaFisicaBll.addPessoasFisicas(pessoaFisica);
           
           JOptionPane.showMessageDialog(null, pessoaFisica.getCliente().getNome()+" cadastrado com sucesso no sistema!");
           preencherGridPessoaFisica();
           limparCampos();
            }catch(Exception error){
                JOptionPane.showMessageDialog(rootPane, error.getMessage(), "Menssagem", JOptionPane.ERROR_MESSAGE);
           }
    }//GEN-LAST:event_jButtonCadastrarActionPerformed

    private void jComboBox_CidadesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_CidadesActionPerformed
         try{
        cidade = cidadeBll.getCidadeNome(jComboBox_Cidades.getSelectedItem().toString());
        jTextField_UF.setText(cidade.getUf().getNome());
        }catch(Exception error){
            JOptionPane.showMessageDialog(null, "Erro combo Cidades "+error.getMessage());
        }
    }//GEN-LAST:event_jComboBox_CidadesActionPerformed

    private void jTableConsultaPessoaFisicaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableConsultaPessoaFisicaMouseClicked
        try {
            preencherFormularioPessoaJuridica();
        } catch (Exception error) {
            JOptionPane.showMessageDialog(rootPane, error.getMessage(), "Menssagem", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jTableConsultaPessoaFisicaMouseClicked

    private void jButtonAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAlterarActionPerformed
        try{ 
        if (jTableConsultaPessoaFisica.getSelectedRow() == -1) {
                throw new Exception("Selecione uma pessoa fisica na tabela para ser alterado!");
            }
            
           int id = Integer.parseInt(jTableConsultaPessoaFisica.getValueAt(jTableConsultaPessoaFisica.getSelectedRow(), 0).toString());
           pessoaFisica = pessoaFisicaBll.getPessoasFisicasBy(id);
           endereco = enderecoBll.getConsultaPorId(pessoaFisica.getCliente().getEnderecos().getIden());
           cliente = clienteBll.getClienteById(pessoaFisica.getCliente().getIden());
           cidade = cidadeBll.getCidadeNome(jComboBox_Cidades.getSelectedItem().toString());
           
           endereco.setCidade(cidade);
           endereco.setCep(Double.parseDouble(jTextFieldCepPessoaFisica.getText()));
           endereco.setLogradouro(jTextFieldLogradouroPessoaFisica.getText());
           endereco.setComplemento(jTextFieldComplementoPessoaFisica.getText());
           endereco.setNumero(Float.parseFloat(jTextFieldNumeroPessoaFisica.getText()));
           endereco.setRua(jTextFieldRuaPessoaFisica.getText());
           enderecoBll.updateEndereco(endereco);

           cliente.setEnderecos(endereco);
           cliente.setNome(jTextFieldNomePessoaFisica.getText());
           cliente.setTelefone(Double.parseDouble(jTextFieldTelefonePessoaFisica.getText()));
           cliente.setEmail(jTextFieldEmailPessoaFisica.getText());
           clienteBll.updateClientes(cliente);

           pessoaFisica.setCliente(cliente);
           pessoaFisica.setRg(Integer.parseInt(jTextFieldRGPessoaFisica.getText()));
           pessoaFisica.setCpf(Double.parseDouble(jTextFieldCPFPessoaFisica.getText()));       
           pessoaFisicaBll.updatePessoasFisicas(pessoaFisica);
           
           JOptionPane.showMessageDialog(null, pessoaFisica.getCliente().getNome()+" alterado com sucesso no sistema!");
           preencherGridPessoaFisica();
           limparCampos();
           
         } catch (Exception error) {
            JOptionPane.showMessageDialog(rootPane, error.getMessage(), "Menssagem", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButtonAlterarActionPerformed

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
            java.util.logging.Logger.getLogger(TelaPessoaFisica.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaPessoaFisica.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaPessoaFisica.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaPessoaFisica.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaPessoaFisica().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAlterar;
    private javax.swing.JButton jButtonCadastrar;
    private javax.swing.JButton jButtonConsultar;
    private javax.swing.JButton jButtonLimpar;
    private javax.swing.JButton jButtonLimparConsulta;
    private javax.swing.JButton jButtonRemover;
    private javax.swing.JComboBox<String> jComboBox_Cidades;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel3RGPessoaFisica;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel4CPFPessoaFisica;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabelCPFPessoaFisica;
    private javax.swing.JLabel jLabelIdPessoaFisica;
    private javax.swing.JLabel jLabelNomePessoaFisica;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel1PessoaFisica;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel2PessoaFisica;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1PessoaFisica;
    private javax.swing.JTable jTableConsultaPessoaFisica;
    private javax.swing.JTextField jTextFieldCPFPessoaFisica;
    private javax.swing.JTextField jTextFieldCepPessoaFisica;
    private javax.swing.JTextField jTextFieldComplementoPessoaFisica;
    private javax.swing.JTextField jTextFieldEmailPessoaFisica;
    private javax.swing.JTextField jTextFieldIdPessoaFisica;
    private javax.swing.JTextField jTextFieldLogradouroPessoaFisica;
    private javax.swing.JTextField jTextFieldNomePessoaFisica;
    private javax.swing.JTextField jTextFieldNumeroPessoaFisica;
    private javax.swing.JTextField jTextFieldPesquisarPessoaFisica;
    private javax.swing.JTextField jTextFieldRGPessoaFisica;
    private javax.swing.JTextField jTextFieldRuaPessoaFisica;
    private javax.swing.JTextField jTextFieldTelefonePessoaFisica;
    private javax.swing.JTextField jTextField_UF;
    // End of variables declaration//GEN-END:variables
}
