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
import br.com.pi.util.Valida;
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

            Object[] coluna = new Object[8];

            ArrayList<PessoasJuridicas> listaDePessoasJuridicas = new PessoasJuridicasBll().getAllPessoasJuridicas();

            for (PessoasJuridicas pessoasJuridicas : listaDePessoasJuridicas) {

                coluna[0] = pessoasJuridicas.getIden();
                coluna[1] = pessoasJuridicas.getNomeFantasia();
                coluna[2] = pessoasJuridicas.getCliente().getTelefone();
                coluna[3] = pessoasJuridicas.getCliente().getEmail();
                coluna[4] = pessoasJuridicas.getCliente().getEnderecos().getLogradouro();
                coluna[5] = pessoasJuridicas.getCnpj();
                coluna[6] = pessoasJuridicas.getCliente().getEnderecos().getCep();
                coluna[7] = pessoasJuridicas.getCliente().getStatus();

                tablePessoasJuridicas.addRow(coluna);
            }
        } catch (Exception error) {
            JOptionPane.showMessageDialog(null, error.getMessage(), "Menssagem", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void preencherFormularioPessoaJuridica() throws Exception {

        int id = Integer.parseInt(jTable_PessoasJuridicas.getValueAt(jTable_PessoasJuridicas.getSelectedRow(), 0).toString());
        pessoaJuridica = pessoaJuridicaBll.getPessoasJuridicasBy(id);

        jTextFieldIdPessoaJuridica.setText("" + pessoaJuridica.getIden());
        jTextFieldCNPJPessoaJuridica.setText(pessoaJuridica.getCnpj());
        jTextFieldRazaoSocialPessoaJuridica.setText(pessoaJuridica.getRazaoSocial());
        jTextFieldNomeFantasiaPessoaJuridica.setText(pessoaJuridica.getNomeFantasia());
        jTextFieldCepPessoaJuridica.setText("" + pessoaJuridica.getCliente().getEnderecos().getCep());
        jTextFieldLogradouroPessoaJuridica.setText(pessoaJuridica.getCliente().getEnderecos().getLogradouro());
        jTextFieldNumeroPessoaJuridica.setText("" + pessoaJuridica.getCliente().getEnderecos().getNumero());
        jTextFieldComplementoPessoaJuridica.setText(pessoaJuridica.getCliente().getEnderecos().getComplemento());
        jTextFieldRuaPessoaJuridica.setText(pessoaJuridica.getCliente().getEnderecos().getRua());
        jComboBoxCidade.setSelectedItem(pessoaJuridica.getCliente().getEnderecos().getCidade());
        jTextFieldTelefonePessoaJuridica.setText(pessoaJuridica.getCliente().getTelefone());
        jTextFieldEmailPessoaJuridica.setText(pessoaJuridica.getCliente().getEmail());

    }

    public void validaFormularioPessoasJuridicas() {
        Valida.SomenteNumero(jTextFieldNumeroPessoaJuridica.getText(), "Campo número de endereço deve conter somente números!");
    }

    public void preencherComboboxCidades() throws Exception {
        try {
            jComboBoxCidade.removeAllItems();
            ArrayList<Cidades> listaCidades = cidadesBll.getAllCidades();

            for (Cidades cidade : listaCidades) {
                jComboBoxCidade.addItem(cidade.getIden()+"-"+cidade.getNome());
            }
        } catch (Exception error) {
            JOptionPane.showMessageDialog(rootPane, error.getMessage(), "Menssagem", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private int SplitReturnID(String combo) {

        String[] split = combo.split("-");
        int id = Integer.parseInt(split[0]);
        return id;

    }

    public void limparCampos() {
        jTextFieldCNPJPessoaJuridica.setText("");
        jTextFieldRazaoSocialPessoaJuridica.setText("");
        jTextFieldNomeFantasiaPessoaJuridica.setText("");
        jTextFieldIdPessoaJuridica.setText("");
        jTextFieldCepPessoaJuridica.setText("");
        jTextFieldLogradouroPessoaJuridica.setText("");
        jTextFieldNumeroPessoaJuridica.setText("");
        jTextFieldComplementoPessoaJuridica.setText("");
        jTextFieldRuaPessoaJuridica.setText("");
        jTextFieldUf.setText("");
        jTextFieldTelefonePessoaJuridica.setText("");
        jTextFieldEmailPessoaJuridica.setText("");
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

        jPanel2PessoaFisica = new javax.swing.JPanel();
        jLabelIdPessoaFisica = new javax.swing.JLabel();
        jTextFieldIdPessoaJuridica = new javax.swing.JTextField();
        jLabelCPFPessoaFisica = new javax.swing.JLabel();
        jTextFieldRazaoSocialPessoaJuridica = new javax.swing.JTextField();
        jLabelNomePessoaFisica = new javax.swing.JLabel();
        jTextFieldNomeFantasiaPessoaJuridica = new javax.swing.JTextField();
        jLabel3RGPessoaFisica = new javax.swing.JLabel();
        jTextFieldCNPJPessoaJuridica = new javax.swing.JFormattedTextField();
        jPanel4 = new javax.swing.JPanel();
        jLabel4CPFPessoaFisica = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jTextFieldEmailPessoaJuridica = new javax.swing.JTextField();
        jTextFieldTelefonePessoaJuridica = new javax.swing.JFormattedTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable_PessoasJuridicas = new javax.swing.JTable();
        jButtonRemover = new javax.swing.JButton();
        jButtonLimpar = new javax.swing.JButton();
        jButtonAlterar = new javax.swing.JButton();
        jButtonCadastrar = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
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
        jTextFieldCepPessoaJuridica = new javax.swing.JFormattedTextField();
        jLabel9 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("[PESSOAS JURIDICA]");
        setMaximumSize(new java.awt.Dimension(1280, 720));
        setMinimumSize(new java.awt.Dimension(1280, 720));
        setPreferredSize(new java.awt.Dimension(1280, 720));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2PessoaFisica.setBackground(new java.awt.Color(0, 0, 0));
        jPanel2PessoaFisica.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)), "Dados Gerais", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Lucida Grande", 0, 13), new java.awt.Color(255, 255, 255))); // NOI18N

        jLabelIdPessoaFisica.setForeground(new java.awt.Color(204, 204, 204));
        jLabelIdPessoaFisica.setText("ID");

        jTextFieldIdPessoaJuridica.setEditable(false);

        jLabelCPFPessoaFisica.setForeground(new java.awt.Color(204, 204, 204));
        jLabelCPFPessoaFisica.setText("RAZÃO SOCIAL");

        jLabelNomePessoaFisica.setForeground(new java.awt.Color(204, 204, 204));
        jLabelNomePessoaFisica.setText("NOME FANTASIA");

        jLabel3RGPessoaFisica.setForeground(new java.awt.Color(204, 204, 204));
        jLabel3RGPessoaFisica.setText("CNPJ");

        try {
            jTextFieldCNPJPessoaJuridica.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##.###.###/####-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        javax.swing.GroupLayout jPanel2PessoaFisicaLayout = new javax.swing.GroupLayout(jPanel2PessoaFisica);
        jPanel2PessoaFisica.setLayout(jPanel2PessoaFisicaLayout);
        jPanel2PessoaFisicaLayout.setHorizontalGroup(
            jPanel2PessoaFisicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2PessoaFisicaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2PessoaFisicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextFieldNomeFantasiaPessoaJuridica)
                    .addComponent(jLabelNomePessoaFisica)
                    .addGroup(jPanel2PessoaFisicaLayout.createSequentialGroup()
                        .addGroup(jPanel2PessoaFisicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldIdPessoaJuridica, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2PessoaFisicaLayout.createSequentialGroup()
                                .addGap(9, 9, 9)
                                .addComponent(jLabelIdPessoaFisica)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2PessoaFisicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3RGPessoaFisica)
                            .addComponent(jTextFieldCNPJPessoaJuridica, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2PessoaFisicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelCPFPessoaFisica)
                            .addComponent(jTextFieldRazaoSocialPessoaJuridica, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(16, Short.MAX_VALUE))
        );
        jPanel2PessoaFisicaLayout.setVerticalGroup(
            jPanel2PessoaFisicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2PessoaFisicaLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel2PessoaFisicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelCPFPessoaFisica)
                    .addComponent(jLabel3RGPessoaFisica)
                    .addComponent(jLabelIdPessoaFisica))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2PessoaFisicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldIdPessoaJuridica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldRazaoSocialPessoaJuridica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldCNPJPessoaJuridica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addComponent(jLabelNomePessoaFisica)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldNomeFantasiaPessoaJuridica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(13, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel2PessoaFisica, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 520, 170));

        jPanel4.setBackground(new java.awt.Color(0, 0, 0));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)), "Dados para Contato", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Lucida Grande", 0, 13), new java.awt.Color(255, 255, 255))); // NOI18N

        jLabel4CPFPessoaFisica.setForeground(new java.awt.Color(204, 204, 204));
        jLabel4CPFPessoaFisica.setText("TELEFONE");

        jLabel1.setForeground(new java.awt.Color(204, 204, 204));
        jLabel1.setText("E-MAIL");

        try {
            jTextFieldTelefonePessoaJuridica.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##) #####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel4CPFPessoaFisica)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jTextFieldTelefonePessoaJuridica, javax.swing.GroupLayout.DEFAULT_SIZE, 187, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jTextFieldEmailPessoaJuridica, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextFieldEmailPessoaJuridica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldTelefonePessoaJuridica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel4CPFPessoaFisica)
                        .addGap(34, 34, 34)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 210, 520, 100));

        jTable_PessoasJuridicas.setBackground(new java.awt.Color(0, 0, 0));
        jTable_PessoasJuridicas.setForeground(new java.awt.Color(255, 255, 255));
        jTable_PessoasJuridicas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "NOME FANTASIA", "TELEFONE", "E-MAIL", "LOGRADOURO", "CNPJ", "CEP", "STATUS"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable_PessoasJuridicas.setSelectionBackground(new java.awt.Color(255, 255, 255));
        jTable_PessoasJuridicas.setSelectionForeground(new java.awt.Color(0, 0, 0));
        jTable_PessoasJuridicas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable_PessoasJuridicasMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jTable_PessoasJuridicas);
        if (jTable_PessoasJuridicas.getColumnModel().getColumnCount() > 0) {
            jTable_PessoasJuridicas.getColumnModel().getColumn(0).setMaxWidth(40);
        }

        getContentPane().add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 337, 1220, 240));

        jButtonRemover.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/pi/icons/lixo.png"))); // NOI18N
        jButtonRemover.setText("EXCLUIR");
        jButtonRemover.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRemoverActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonRemover, new org.netbeans.lib.awtextra.AbsoluteConstraints(1110, 600, 140, 50));

        jButtonLimpar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/pi/icons/limpar-limpo.png"))); // NOI18N
        jButtonLimpar.setText("LIMPAR");
        jButtonLimpar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLimparActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonLimpar, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 600, 140, 50));

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

        jPanel3.setBackground(new java.awt.Color(0, 0, 0));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)), "Dados de Endereço", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Lucida Grande", 0, 13), new java.awt.Color(255, 255, 255))); // NOI18N

        jLabel2.setForeground(new java.awt.Color(204, 204, 204));
        jLabel2.setText("CEP");

        jLabel3.setForeground(new java.awt.Color(204, 204, 204));
        jLabel3.setText("LOGRADOURO");

        jLabel4.setForeground(new java.awt.Color(204, 204, 204));
        jLabel4.setText("N°");

        jLabel5.setForeground(new java.awt.Color(204, 204, 204));
        jLabel5.setText("COMPLEMENTO");

        jLabel6.setForeground(new java.awt.Color(204, 204, 204));
        jLabel6.setText("RUA");

        jLabel7.setForeground(new java.awt.Color(204, 204, 204));
        jLabel7.setText("CIDADE");

        jLabel8.setForeground(new java.awt.Color(204, 204, 204));
        jLabel8.setText("UF");

        jComboBoxCidade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxCidadeActionPerformed(evt);
            }
        });

        jTextFieldUf.setEditable(false);

        try {
            jTextFieldCepPessoaJuridica.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##.###-###")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldComplementoPessoaJuridica, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextFieldRuaPessoaJuridica, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel7)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addComponent(jComboBoxCidade, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextFieldUf, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel8)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextFieldLogradouroPessoaJuridica)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jTextFieldCepPessoaJuridica, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel3))
                                        .addGap(0, 324, Short.MAX_VALUE)))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jTextFieldNumeroPessoaJuridica, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(19, 19, 19))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(8, 8, 8)
                .addComponent(jTextFieldCepPessoaJuridica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldLogradouroPessoaJuridica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldNumeroPessoaJuridica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextFieldComplementoPessoaJuridica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel7)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxCidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldUf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldRuaPessoaJuridica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 30, 670, 280));

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/pi/image/fundo desfolque bmw_Easy-Resize.com.jpg"))); // NOI18N
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCadastrarActionPerformed
        int chegou = 0;
        try {
            validaFormularioPessoasJuridicas();
            cidade = cidadesBll.getCidadesById(SplitReturnID(jComboBoxCidade.getSelectedItem().toString()));
            endereco.setCidade(cidade);
            endereco.setCep(jTextFieldCepPessoaJuridica.getText());
            endereco.setLogradouro(jTextFieldLogradouroPessoaJuridica.getText());
            endereco.setComplemento(jTextFieldComplementoPessoaJuridica.getText());
            endereco.setNumero(jTextFieldNumeroPessoaJuridica.getText());
            endereco.setRua(jTextFieldRuaPessoaJuridica.getText());
            enderecoBll.AddEndereco(endereco);
            String cep = endereco.getCep();
            endereco = enderecoBll.getConsultaPorCEP(cep);

            chegou = 1;

            cliente.setEnderecos(endereco);
            cliente.setNome("");
            cliente.setTelefone(jTextFieldTelefonePessoaJuridica.getText());
            cliente.setEmail(jTextFieldEmailPessoaJuridica.getText());
            cliente.setStatus("ADIMPLENTE");
            cliente.setTipo("JURIDICA");
            cliente.setMulta(0);
            clienteBll.addClientes(cliente);
            String clienteTelefone = cliente.getTelefone();
            cliente = clienteBll.getClienteByTelefone(clienteTelefone);

            chegou = 2;

            pessoaJuridica.setCliente(cliente);
            pessoaJuridica.setCnpj(jTextFieldCNPJPessoaJuridica.getText());
            pessoaJuridica.setNomeFantasia(jTextFieldNomeFantasiaPessoaJuridica.getText());
            pessoaJuridica.setRazaoSocial(jTextFieldRazaoSocialPessoaJuridica.getText());
            pessoaJuridicaBll.addPessoasJuridicas(pessoaJuridica);

            JOptionPane.showMessageDialog(null, pessoaJuridica.getNomeFantasia() + " cadastrado com sucesso no sistema!");
            preencherGridPessoaJuridica();
            limparCampos();

        } catch (Exception error) {

            try {
                if (chegou == 1) {
                    enderecoBll.deleteLast();
                }
                if (chegou == 2) {
                    clienteBll.deleteLast();
                    enderecoBll.deleteLast();
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erro na combo Cidades " + e.getMessage());
            }

            JOptionPane.showMessageDialog(null, error.getMessage(), "Menssagem", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButtonCadastrarActionPerformed

    private void jComboBoxCidadeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxCidadeActionPerformed
        try {
            cidade = cidadesBll.getCidadesById(SplitReturnID(jComboBoxCidade.getSelectedItem().toString()));
            jTextFieldUf.setText(cidade.getUf().getNome());
        } catch (Exception error) {
            JOptionPane.showMessageDialog(null, "Erro na combo Cidade " + error.getMessage());
        }
    }//GEN-LAST:event_jComboBoxCidadeActionPerformed

    private void jButtonAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAlterarActionPerformed
        int chegou = 0;
        try {
            if (jTable_PessoasJuridicas.getSelectedRow() == -1) {
                throw new Exception("Selecione uma pessoa juridica na tabela para ser alterado!");
            }
            validaFormularioPessoasJuridicas();
            int id = Integer.parseInt(jTable_PessoasJuridicas.getValueAt(jTable_PessoasJuridicas.getSelectedRow(), 0).toString());
            pessoaJuridica = pessoaJuridicaBll.getPessoasJuridicasBy(id);
            endereco = enderecoBll.getConsultaPorId(pessoaJuridica.getCliente().getEnderecos().getIden());
            cliente = clienteBll.getClienteById(pessoaJuridica.getCliente().getIden());
            cidade = cidadesBll.getCidadesById(SplitReturnID(jComboBoxCidade.getSelectedItem().toString()));

            endereco.setCidade(cidade);
            endereco.setCep(jTextFieldCepPessoaJuridica.getText());
            endereco.setLogradouro(jTextFieldLogradouroPessoaJuridica.getText());
            endereco.setComplemento(jTextFieldComplementoPessoaJuridica.getText());
            endereco.setNumero(jTextFieldNumeroPessoaJuridica.getText());
            cliente.setTipo("JURIDICA");
            endereco.setRua(jTextFieldRuaPessoaJuridica.getText());
            enderecoBll.updateEndereco(endereco);

            chegou = 1;

            cliente.setEnderecos(endereco);
            cliente.setNome(null);
            cliente.setTelefone(jTextFieldTelefonePessoaJuridica.getText());
            cliente.setEmail(jTextFieldEmailPessoaJuridica.getText());
            clienteBll.updateClientes(cliente);

            chegou = 2;

            pessoaJuridica.setCliente(cliente);
            pessoaJuridica.setCnpj(jTextFieldCNPJPessoaJuridica.getText());
            pessoaJuridica.setNomeFantasia(jTextFieldNomeFantasiaPessoaJuridica.getText());
            pessoaJuridica.setRazaoSocial(jTextFieldRazaoSocialPessoaJuridica.getText());
            pessoaJuridicaBll.updatePessoasJuridicas(pessoaJuridica);

            JOptionPane.showMessageDialog(null, pessoaJuridica.getNomeFantasia() + " alterado com sucesso no sistema!");
            preencherGridPessoaJuridica();
            limparCampos();

        } catch (Exception error) {

            try {
                if (chegou == 1) {
                    enderecoBll.deleteLast();
                }
                if (chegou == 2) {
                    clienteBll.deleteLast();
                    enderecoBll.deleteLast();
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erro na combo Cidades " + e.getMessage());
            }

            JOptionPane.showMessageDialog(rootPane, error.getMessage(), "Menssagem", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButtonAlterarActionPerformed

    private void jButtonRemoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRemoverActionPerformed
        try {
            if (jTable_PessoasJuridicas.getSelectedRow() == -1) {
                throw new Exception("Selecione uma pessoa juridica na tabela para ser alterado!");
            }

            int id = Integer.parseInt(jTable_PessoasJuridicas.getValueAt(jTable_PessoasJuridicas.getSelectedRow(), 0).toString());
            pessoaJuridica = pessoaJuridicaBll.getPessoasJuridicasBy(id);
            endereco = enderecoBll.getConsultaPorId(pessoaJuridica.getCliente().getEnderecos().getIden());
            cliente = clienteBll.getClienteById(pessoaJuridica.getCliente().getIden());

            pessoaJuridicaBll.deletePessoasJuridicas(pessoaJuridica);
            clienteBll.deleteClientes(cliente);
            enderecoBll.deleteEndereco(endereco);
            JOptionPane.showMessageDialog(null, pessoaJuridica.getNomeFantasia() + " removido com sucesso no sistema!");
            preencherGridPessoaJuridica();
            limparCampos();

        } catch (Exception error) {
            JOptionPane.showMessageDialog(rootPane, error.getMessage(), "Menssagem", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButtonRemoverActionPerformed

    private void jButtonLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLimparActionPerformed
        limparCampos();
    }//GEN-LAST:event_jButtonLimparActionPerformed

    private void jTable_PessoasJuridicasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_PessoasJuridicasMouseClicked
        try {
            preencherFormularioPessoaJuridica();
        } catch (Exception error) {
            JOptionPane.showMessageDialog(rootPane, error.getMessage(), "Menssagem", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jTable_PessoasJuridicasMouseClicked

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
    private javax.swing.JButton jButtonLimpar;
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
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelCPFPessoaFisica;
    private javax.swing.JLabel jLabelIdPessoaFisica;
    private javax.swing.JLabel jLabelNomePessoaFisica;
    private javax.swing.JPanel jPanel2PessoaFisica;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable_PessoasJuridicas;
    private javax.swing.JFormattedTextField jTextFieldCNPJPessoaJuridica;
    private javax.swing.JFormattedTextField jTextFieldCepPessoaJuridica;
    private javax.swing.JTextField jTextFieldComplementoPessoaJuridica;
    private javax.swing.JTextField jTextFieldEmailPessoaJuridica;
    private javax.swing.JTextField jTextFieldIdPessoaJuridica;
    private javax.swing.JTextField jTextFieldLogradouroPessoaJuridica;
    private javax.swing.JTextField jTextFieldNomeFantasiaPessoaJuridica;
    private javax.swing.JTextField jTextFieldNumeroPessoaJuridica;
    private javax.swing.JTextField jTextFieldRazaoSocialPessoaJuridica;
    private javax.swing.JTextField jTextFieldRuaPessoaJuridica;
    private javax.swing.JFormattedTextField jTextFieldTelefonePessoaJuridica;
    private javax.swing.JTextField jTextFieldUf;
    // End of variables declaration//GEN-END:variables
}
