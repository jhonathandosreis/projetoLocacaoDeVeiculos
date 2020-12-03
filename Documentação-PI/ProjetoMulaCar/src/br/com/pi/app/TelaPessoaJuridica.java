/*
 *  -------------------------------------------------------------------------------------------------->
 *  Licença    : MIT - Copyright 2019 Jhonathan, Gustavo e Miguel 
 *  Criado em  : 20/11/2020 18:58:00 
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
import br.com.pi.bll.PessoasJuridicasBll;
import br.com.pi.model.Cidades;
import br.com.pi.model.Clientes;
import br.com.pi.model.Enderecos;
import br.com.pi.model.PessoasJuridicas;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author jhonlinux
 */
public class TelaPessoaJuridica extends javax.swing.JFrame {

    //--- BLL´S --------------------------------------------------------------------------------------->
    private PessoasJuridicasBll pessoaJuridicaBll = null;
    private EnderecosBll enderecoBll = null;
    private CidadesBll cidadesBll = null;
    

    //--- FIM BLL'S ----------------------------------------------------------------------------------->
    //
    //--- CLASSES -------------------------------------------------------------------------------------> 
    private PessoasJuridicas pessoaJuridica = null;
    private Clientes cliente;
    private ClientesBll clienteBll;
    private Enderecos endereco = null;
    private Cidades cidade = null;
 

    //--- FIM CLASSES --------------------------------------------------------------------------------->
    //
    public TelaPessoaJuridica() {
        initComponents();

        try {

            pessoaJuridica = new PessoasJuridicas();
            pessoaJuridicaBll = new PessoasJuridicasBll();
            
            enderecoBll = new EnderecosBll();
            cidadesBll = new CidadesBll();
            cliente = new Clientes();
            clienteBll = new ClientesBll();
            endereco = new Enderecos();
            cidade = new Cidades();
          
            preencherComboboxCidades();
            preencherGridPessoaJuridica();

        } catch (Exception error) {
            JOptionPane.showMessageDialog(null, error.getMessage(), "Menssagem", JOptionPane.ERROR_MESSAGE);
        }
        this.setLocationRelativeTo(null);
    }

    //--- METODOS ------------------------------------------------------------------------------------->
    public void preencherGridPessoaJuridica() throws Exception {

        try {
            DefaultTableModel tablePessoasJuridicas = (DefaultTableModel) jTable_PessoasJuridicas.getModel();
            tablePessoasJuridicas.setRowCount(0);

            Object[] coluna = new Object[7];

            ArrayList<PessoasJuridicas> listaDePessoasJuridicas = new PessoasJuridicasBll().getAllPessoasJuridicas();

            for (PessoasJuridicas pessoasJuridicas : listaDePessoasJuridicas) {

                coluna[0] = pessoasJuridicas.getIden();
                coluna[1] = pessoasJuridicas.getNomeFantasia();
                coluna[2] = pessoasJuridicas.getCnpj();
                coluna[3] = pessoasJuridicas.getCliente().getTelefone();
                coluna[4] = pessoasJuridicas.getCliente().getEmail();
                coluna[5] = pessoasJuridicas.getCliente().getEnderecos().getLogradouro();
                coluna[6] = pessoasJuridicas.getCliente().getEnderecos().getCep();
             
                tablePessoasJuridicas.addRow(coluna);
            }
        } catch (Exception error) {
            JOptionPane.showMessageDialog(null, error.getMessage(), "Menssagem", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void preencherFormularioPessoaJuridica() throws Exception {
       
        int id = Integer.parseInt(jTable_PessoasJuridicas.getValueAt(jTable_PessoasJuridicas.getSelectedRow(), 0).toString());
        pessoaJuridica = pessoaJuridicaBll.getPessoasJuridicasBy(id);
        
        jTextFieldIdPessoaJuridica.setText(""+pessoaJuridica.getIden());
        jTextFieldCNPJPessoaJuridica.setText(""+pessoaJuridica.getCnpj());
        jTextFieldRazaoSocialPessoaJuridica.setText(pessoaJuridica.getRazaoSocial());
        jTextFieldNomeFantasiaPessoaJuridica.setText(pessoaJuridica.getNomeFantasia());
        jTextFieldCepPessoaJuridica.setText(""+pessoaJuridica.getCliente().getEnderecos().getCep());
        jTextFieldLogradouroPessoaJuridica.setText(pessoaJuridica.getCliente().getEnderecos().getLogradouro());
        jTextFieldNumeroPessoaJuridica.setText(""+pessoaJuridica.getCliente().getEnderecos().getNumero());
        jTextFieldComplementoPessoaJuridica.setText(pessoaJuridica.getCliente().getEnderecos().getComplemento());
        jTextFieldRuaPessoaJuridica.setText(pessoaJuridica.getCliente().getEnderecos().getRua());
        jComboBoxCidade.setSelectedItem(pessoaJuridica.getCliente().getEnderecos().getCidade());
        jTextFieldTelefonePessoaJuridica.setText(""+pessoaJuridica.getCliente().getTelefone());
        jTextFieldEmailPessoaJuridica.setText(""+pessoaJuridica.getCliente().getTelefone());
        
    }
    
    public void preencherComboboxCidades() throws Exception{
           try{
            jComboBoxCidade.removeAllItems();
            ArrayList<Cidades> listaCidades = cidadesBll.getAllCidades();
            
            for (Cidades cidade : listaCidades) {
               jComboBoxCidade.addItem(cidade.getNome());
            }  
           }catch(Exception error){
                JOptionPane.showMessageDialog(rootPane, error.getMessage(), "Menssagem", JOptionPane.ERROR_MESSAGE);
           }
    }

    public void limparCampos() {

            jTextFieldIdPessoaJuridica.setText("");
            jTextFieldCepPessoaJuridica.setText("");
            jTextFieldLogradouroPessoaJuridica.setText("");
            jTextFieldNumeroPessoaJuridica.setText("");
            jTextFieldComplementoPessoaJuridica.setText("");
            jTextFieldRuaPessoaJuridica.setText("");
            jTextFieldUf.setText("");
            jComboBoxCidade.setSelectedIndex(0);
    }

    //--- FIM METODOS --------------------------------------------------------------------------------->
    //
    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify
     * this code. The content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1PessoaFisica = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel2PessoaFisica = new javax.swing.JPanel();
        jLabelIdPessoaFisica = new javax.swing.JLabel();
        jTextFieldIdPessoaJuridica = new javax.swing.JTextField();
        jLabelCPFPessoaFisica = new javax.swing.JLabel();
        jTextFieldRazaoSocialPessoaJuridica = new javax.swing.JTextField();
        jLabelNomePessoaFisica = new javax.swing.JLabel();
        jTextFieldNomeFantasiaPessoaJuridica = new javax.swing.JTextField();
        jLabel3RGPessoaFisica = new javax.swing.JLabel();
        jTextFieldCNPJPessoaJuridica = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jTextFieldCepPessoaJuridica = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTextFieldLogradouroPessoaJuridica = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTextFieldNumeroPessoaJuridica = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTextFieldComplementoPessoaJuridica = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jTextFieldRuaPessoaJuridica = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jComboBoxCidade = new javax.swing.JComboBox<>();
        jTextFieldUf = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jLabel4CPFPessoaFisica = new javax.swing.JLabel();
        jTextFieldTelefonePessoaJuridica = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jTextFieldEmailPessoaJuridica = new javax.swing.JTextField();
        jButtonCadastrar = new javax.swing.JButton();
        jButtonAlterar = new javax.swing.JButton();
        jButtonRemover = new javax.swing.JButton();
        jButtonLimpar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jTextFieldPesquisarPessoaJuridica = new javax.swing.JTextField();
        jButtonConsultarPessoaJuridica = new javax.swing.JButton();
        jButtonLimparPessoaJuridica = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable_PessoasJuridicas = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("[PESSOAS JURIDICA]");

        jPanel2PessoaFisica.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Dados Gerais"));

        jLabelIdPessoaFisica.setText("ID");

        jTextFieldIdPessoaJuridica.setEditable(false);

        jLabelCPFPessoaFisica.setText("RAZÃO SOCIAL");

        jLabelNomePessoaFisica.setText("NOME FANTASIA");

        jLabel3RGPessoaFisica.setText("CNPJ");

        jTextFieldCNPJPessoaJuridica.setText("00.000.000/0000-00");

        javax.swing.GroupLayout jPanel2PessoaFisicaLayout = new javax.swing.GroupLayout(jPanel2PessoaFisica);
        jPanel2PessoaFisica.setLayout(jPanel2PessoaFisicaLayout);
        jPanel2PessoaFisicaLayout.setHorizontalGroup(
            jPanel2PessoaFisicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2PessoaFisicaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2PessoaFisicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2PessoaFisicaLayout.createSequentialGroup()
                        .addGroup(jPanel2PessoaFisicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelIdPessoaFisica)
                            .addComponent(jTextFieldIdPessoaJuridica, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2PessoaFisicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3RGPessoaFisica)
                            .addComponent(jTextFieldCNPJPessoaJuridica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2PessoaFisicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelCPFPessoaFisica)
                            .addComponent(jTextFieldRazaoSocialPessoaJuridica, javax.swing.GroupLayout.PREFERRED_SIZE, 687, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jTextFieldNomeFantasiaPessoaJuridica, javax.swing.GroupLayout.PREFERRED_SIZE, 799, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelNomePessoaFisica))
                .addContainerGap(215, Short.MAX_VALUE))
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
                    .addComponent(jTextFieldIdPessoaJuridica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldRazaoSocialPessoaJuridica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldCNPJPessoaJuridica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabelNomePessoaFisica)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextFieldNomeFantasiaPessoaJuridica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Dados de Endereço"));

        jLabel2.setText("CEP");

        jTextFieldCepPessoaJuridica.setText("00-000-000");

        jLabel3.setText("LOGRADOURO");

        jLabel4.setText("N°");

        jLabel5.setText("COMPLEMENTO");

        jLabel6.setText("Rua");

        jLabel7.setText("CIDADE");

        jLabel8.setText("UF");

        jComboBoxCidade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxCidadeActionPerformed(evt);
            }
        });

        jTextFieldUf.setEditable(false);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextFieldComplementoPessoaJuridica, javax.swing.GroupLayout.DEFAULT_SIZE, 439, Short.MAX_VALUE)
                            .addComponent(jLabel7)
                            .addComponent(jComboBoxCidade, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jTextFieldUf, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextFieldRuaPessoaJuridica))))
                    .addComponent(jLabel5)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jTextFieldCepPessoaJuridica, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(33, 33, 33)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jTextFieldLogradouroPessoaJuridica, javax.swing.GroupLayout.PREFERRED_SIZE, 677, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldNumeroPessoaJuridica, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))))
                .addContainerGap(244, Short.MAX_VALUE))
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
                    .addComponent(jTextFieldCepPessoaJuridica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldLogradouroPessoaJuridica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldNumeroPessoaJuridica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldComplementoPessoaJuridica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldRuaPessoaJuridica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(27, 27, 27)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxCidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldUf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 7, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Dados para Contato"));

        jLabel4CPFPessoaFisica.setText("TELEFONE");

        jTextFieldTelefonePessoaJuridica.setText("(62) 9 9999-9999");

        jLabel1.setText("E-Mail");

        jTextFieldEmailPessoaJuridica.setText("prototipação@hotmail.com");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4CPFPessoaFisica)
                    .addComponent(jTextFieldTelefonePessoaJuridica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(74, 74, 74)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jTextFieldEmailPessoaJuridica, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                        .addComponent(jTextFieldEmailPessoaJuridica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel4CPFPessoaFisica)
                        .addGap(14, 14, 14)
                        .addComponent(jTextFieldTelefonePessoaJuridica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2PessoaFisica, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButtonCadastrar)
                        .addGap(215, 215, 215)
                        .addComponent(jButtonAlterar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonRemover)
                        .addGap(279, 279, 279)
                        .addComponent(jButtonLimpar)))
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
                .addGap(32, 32, 32)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonCadastrar)
                    .addComponent(jButtonAlterar)
                    .addComponent(jButtonRemover)
                    .addComponent(jButtonLimpar))
                .addContainerGap(228, Short.MAX_VALUE))
        );

        jTabbedPane1PessoaFisica.addTab("Dados Gerais", jPanel1);

        jTextFieldPesquisarPessoaJuridica.setText("Digite aqui o objeto de sua pesquisa.....");

        jButtonConsultarPessoaJuridica.setText("CONSULTAR");

        jButtonLimparPessoaJuridica.setText("LIMPAR");

        jTable_PessoasJuridicas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "NOME FANTASIA", "CNPJ", "TELEFONE", "E-MAIL", "LOGRADOURO", "CEP"
            }
        ));
        jTable_PessoasJuridicas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable_PessoasJuridicasMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable_PessoasJuridicas);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextFieldPesquisarPessoaJuridica, javax.swing.GroupLayout.PREFERRED_SIZE, 561, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButtonConsultarPessoaJuridica)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonLimparPessoaJuridica)
                .addContainerGap(389, Short.MAX_VALUE))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jScrollPane2)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldPesquisarPessoaJuridica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonConsultarPessoaJuridica)
                    .addComponent(jButtonLimparPessoaJuridica))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 647, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(102, Short.MAX_VALUE))
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1PessoaFisica)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1PessoaFisica))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCadastrarActionPerformed

        
try {
           cidade = cidadesBll.getCidadeNome(jComboBoxCidade.getSelectedItem().toString());
           endereco.setCidade(cidade);
           endereco.setCep(Double.parseDouble(jTextFieldCepPessoaJuridica.getText()));
           endereco.setLogradouro(jTextFieldLogradouroPessoaJuridica.getText());
           endereco.setComplemento(jTextFieldComplementoPessoaJuridica.getText());
           endereco.setNumero(Float.parseFloat(jTextFieldNumeroPessoaJuridica.getText()));
           endereco.setRua(jTextFieldRuaPessoaJuridica.getText());
           enderecoBll.AddEndereco(endereco);
           double cep = endereco.getCep();
           endereco = enderecoBll.getConsultaPorCEP(cep);
           
           cliente.setEnderecos(endereco);
           cliente.setNome(null);
           cliente.setTelefone(Double.parseDouble(jTextFieldTelefonePessoaJuridica.getText()));
           cliente.setEmail(jTextFieldEmailPessoaJuridica.getText());
           clienteBll.addClientes(cliente);
           double clienteTelefone = cliente.getTelefone();
           cliente = clienteBll.getClienteByTelefone(clienteTelefone);
           
           pessoaJuridica.setCliente(cliente);
           pessoaJuridica.setCnpj(Double.parseDouble(jTextFieldCNPJPessoaJuridica.getText()));
           pessoaJuridica.setNomeFantasia(jTextFieldNomeFantasiaPessoaJuridica.getText());
           pessoaJuridica.setRazaoSocial(jTextFieldRazaoSocialPessoaJuridica.getText());
           pessoaJuridicaBll.addPessoasJuridicas(pessoaJuridica);

           JOptionPane.showMessageDialog(null, pessoaJuridica.getNomeFantasia()+" cadastrado com sucesso no sistema!");
           preencherGridPessoaJuridica();

            limparCampos();
        } catch (Exception error) {
            JOptionPane.showMessageDialog(null, error.getMessage(), "Menssagem", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_jButtonCadastrarActionPerformed

    private void jTable_PessoasJuridicasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_PessoasJuridicasMouseClicked

  try {
            preencherFormularioPessoaJuridica();
        } catch (Exception error) {
            JOptionPane.showMessageDialog(rootPane, error.getMessage(), "Menssagem", JOptionPane.ERROR_MESSAGE);
        }



        // TODO add your handling code here:
    }//GEN-LAST:event_jTable_PessoasJuridicasMouseClicked

    private void jComboBoxCidadeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxCidadeActionPerformed
      try{
        cidade = cidadesBll.getCidadeNome(jComboBoxCidade.getSelectedItem().toString());
        jTextFieldUf.setText(cidade.getUf().getNome());
        }catch(Exception error){
            JOptionPane.showMessageDialog(null, "Erro na combo Cidade "+error.getMessage());
        }
    }//GEN-LAST:event_jComboBoxCidadeActionPerformed

    private void jButtonAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAlterarActionPerformed
    try{
          if (jTable_PessoasJuridicas.getSelectedRow() == -1) {
                throw new Exception("Selecione uma pessoa juridica na tabela para ser alterado!");
          }
          
           int id = Integer.parseInt(jTable_PessoasJuridicas.getValueAt(jTable_PessoasJuridicas.getSelectedRow(), 0).toString());
           pessoaJuridica = pessoaJuridicaBll.getPessoasJuridicasBy(id);
           endereco = enderecoBll.getConsultaPorId(pessoaJuridica.getCliente().getEnderecos().getIden());
           cliente = clienteBll.getClienteById(pessoaJuridica.getCliente().getIden());
           cidade = cidadesBll.getCidadeNome(jComboBoxCidade.getSelectedItem().toString());
           
           endereco.setCidade(cidade);
           endereco.setCep(Double.parseDouble(jTextFieldCepPessoaJuridica.getText()));
           endereco.setLogradouro(jTextFieldLogradouroPessoaJuridica.getText());
           endereco.setComplemento(jTextFieldComplementoPessoaJuridica.getText());
           endereco.setNumero(Float.parseFloat(jTextFieldNumeroPessoaJuridica.getText()));
           endereco.setRua(jTextFieldRuaPessoaJuridica.getText());
           enderecoBll.updateEndereco(endereco);

           cliente.setEnderecos(endereco);
           cliente.setNome(null);
           cliente.setTelefone(Double.parseDouble(jTextFieldTelefonePessoaJuridica.getText()));
           cliente.setEmail(jTextFieldEmailPessoaJuridica.getText());
           clienteBll.updateClientes(cliente);

           pessoaJuridica.setCliente(cliente);
           pessoaJuridica.setCnpj(Double.parseDouble(jTextFieldCNPJPessoaJuridica.getText()));
           pessoaJuridica.setNomeFantasia(jTextFieldNomeFantasiaPessoaJuridica.getText());
           pessoaJuridica.setRazaoSocial(jTextFieldRazaoSocialPessoaJuridica.getText());
           pessoaJuridicaBll.updatePessoasJuridicas(pessoaJuridica);
           
           JOptionPane.showMessageDialog(null, pessoaJuridica.getNomeFantasia()+" alterado com sucesso no sistema!");
           preencherGridPessoaJuridica();
            limparCampos();
    }catch (Exception error) {
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
            java.util.logging.Logger.getLogger(TelaPessoaJuridica.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaPessoaJuridica.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaPessoaJuridica.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaPessoaJuridica.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaPessoaJuridica().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAlterar;
    private javax.swing.JButton jButtonCadastrar;
    private javax.swing.JButton jButtonConsultarPessoaJuridica;
    private javax.swing.JButton jButtonLimpar;
    private javax.swing.JButton jButtonLimparPessoaJuridica;
    private javax.swing.JButton jButtonRemover;
    private javax.swing.JComboBox<String> jComboBoxCidade;
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
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel2PessoaFisica;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1PessoaFisica;
    private javax.swing.JTable jTable_PessoasJuridicas;
    private javax.swing.JTextField jTextFieldCNPJPessoaJuridica;
    private javax.swing.JTextField jTextFieldCepPessoaJuridica;
    private javax.swing.JTextField jTextFieldComplementoPessoaJuridica;
    private javax.swing.JTextField jTextFieldEmailPessoaJuridica;
    private javax.swing.JTextField jTextFieldIdPessoaJuridica;
    private javax.swing.JTextField jTextFieldLogradouroPessoaJuridica;
    private javax.swing.JTextField jTextFieldNomeFantasiaPessoaJuridica;
    private javax.swing.JTextField jTextFieldNumeroPessoaJuridica;
    private javax.swing.JTextField jTextFieldPesquisarPessoaJuridica;
    private javax.swing.JTextField jTextFieldRazaoSocialPessoaJuridica;
    private javax.swing.JTextField jTextFieldRuaPessoaJuridica;
    private javax.swing.JTextField jTextFieldTelefonePessoaJuridica;
    private javax.swing.JTextField jTextFieldUf;
    // End of variables declaration//GEN-END:variables
}
