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
import br.com.pi.util.Valida;
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
        
        try {

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

        } catch (Exception error) {
            JOptionPane.showMessageDialog(rootPane, error.getMessage(), "Menssagem", JOptionPane.ERROR_MESSAGE);
        }

        this.setLocationRelativeTo(null);
    }

    public void preencherGridPessoaFisica() throws Exception {

        try {
            DefaultTableModel tablePessoasFisicas = (DefaultTableModel) jTableConsultaPessoaFisica.getModel();
            tablePessoasFisicas.setRowCount(0);

            Object[] coluna = new Object[9];
            
            ArrayList<PessoasFisicas> listaPessoasFisicas = pessoaFisicaBll.getAllPessoasFisicas();

            for (PessoasFisicas pessoaFisica : listaPessoasFisicas) {

                coluna[0] = pessoaFisica.getIden();
                coluna[1] = pessoaFisica.getCliente().getNome();
                coluna[2] = pessoaFisica.getCliente().getTelefone();
                coluna[3] = pessoaFisica.getCliente().getEmail();
                coluna[4] = pessoaFisica.getCliente().getEnderecos().getLogradouro();
                coluna[5] = pessoaFisica.getRg();
                coluna[6] = pessoaFisica.getCpf();
                coluna[7] = pessoaFisica.getCliente().getEnderecos().getCep();
                coluna[8] = pessoaFisica.getCliente().getStatus();
                

                tablePessoasFisicas.addRow(coluna);
            }
        } catch (Exception error) {
            JOptionPane.showMessageDialog(null, error.getMessage(), "Menssagem", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void preencherFormularioPessoaJuridica() throws Exception {

        int id = Integer.parseInt(jTableConsultaPessoaFisica.getValueAt(jTableConsultaPessoaFisica.getSelectedRow(), 0).toString());
        pessoaFisica = pessoaFisicaBll.getPessoasFisicasBy(id);

        jTextFieldIdPessoaFisica.setText("" + pessoaFisica.getIden());
        jTextFieldRGPessoaFisica.setText("" + pessoaFisica.getRg());
        jTextFieldCPFPessoaFisica.setText("" + pessoaFisica.getCpf());
        jTextFieldNomePessoaFisica.setText(pessoaFisica.getCliente().getNome());
        jTextFieldCepPessoaFisica.setText("" + pessoaFisica.getCliente().getEnderecos().getCep());
        jTextFieldLogradouroPessoaFisica.setText(pessoaFisica.getCliente().getEnderecos().getLogradouro());
        jTextFieldNumeroPessoaFisica.setText("" + pessoaFisica.getCliente().getEnderecos().getNumero());
        jTextFieldComplementoPessoaFisica.setText(pessoaFisica.getCliente().getEnderecos().getComplemento());
        jTextFieldRuaPessoaFisica.setText(pessoaFisica.getCliente().getEnderecos().getRua());
        jComboBox_Cidades.setSelectedItem(pessoaFisica.getCliente().getEnderecos().getCidade());
        jTextFieldTelefonePessoaFisica.setText("" + pessoaFisica.getCliente().getTelefone());
        jTextFieldEmailPessoaFisica.setText("" + pessoaFisica.getCliente().getEmail());

    }
    
    public void validaFormularioPessoasFisicas(){
        
       Valida.SomenteNumero(jTextFieldNumeroPessoaFisica.getText(), "Campo número do endereço deve conter somente números!");
       Valida.SomenteNumero(jTextFieldRGPessoaFisica.getText(), "Campo rg deve conter somente números!");
       Valida.campoVazio(jTextFieldNomePessoaFisica.getText(), "Campo nome deve ser preenchido!");
       Valida.notNumber(jTextFieldNomePessoaFisica.getText(), "Campo nome não deve conter números!");
       Valida.notSpecialCharacters(jTextFieldNomePessoaFisica.getText(), "Campo nome não deve conter caracteres especiais!");
    }

    public void preencherComboboxCidades() throws Exception {
        try {
            jComboBox_Cidades.removeAllItems();
            ArrayList<Cidades> listaCidades = cidadeBll.getAllCidades();

            for (Cidades cidade : listaCidades) {
                jComboBox_Cidades.addItem(cidade.getIden()+"-"+cidade.getNome());
            }
        } catch (Exception error) {
            JOptionPane.showMessageDialog(rootPane, error.getMessage(), "Menssagem", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void limparCampos() {
        jTextFieldIdPessoaFisica.setText("");
        jTextFieldRGPessoaFisica.setText("");
        jTextFieldCPFPessoaFisica.setText("");
        jTextFieldNomePessoaFisica.setText("");
        jTextFieldCepPessoaFisica.setText("");
        jTextFieldLogradouroPessoaFisica.setText("");
        jTextFieldComplementoPessoaFisica.setText("");
        jTextFieldRuaPessoaFisica.setText("");
        jTextField_UF.setText("");
        jTextFieldEmailPessoaFisica.setText("");
        jTextFieldTelefonePessoaFisica.setText("");
        jTextFieldNumeroPessoaFisica.setText("");
        jComboBox_Cidades.setSelectedIndex(0);
    } 
    
    private int SplitReturnID(String combo) {

        String[] split = combo.split("-");
        int id = Integer.parseInt(split[0]);
        return id;

    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify
     * this code. The content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2PessoaFisica = new javax.swing.JPanel();
        jLabelIdPessoaFisica = new javax.swing.JLabel();
        jTextFieldIdPessoaFisica = new javax.swing.JTextField();
        jLabelCPFPessoaFisica = new javax.swing.JLabel();
        jLabelNomePessoaFisica = new javax.swing.JLabel();
        jTextFieldNomePessoaFisica = new javax.swing.JTextField();
        jLabel3RGPessoaFisica = new javax.swing.JLabel();
        jTextFieldCPFPessoaFisica = new javax.swing.JFormattedTextField();
        jTextFieldRGPessoaFisica = new javax.swing.JFormattedTextField();
        jPanel4 = new javax.swing.JPanel();
        jLabel4CPFPessoaFisica = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jTextFieldEmailPessoaFisica = new javax.swing.JTextField();
        jTextFieldTelefonePessoaFisica = new javax.swing.JFormattedTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTableConsultaPessoaFisica = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
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
        jTextFieldCepPessoaFisica = new javax.swing.JFormattedTextField();
        jButtonCadastrar = new javax.swing.JButton();
        jButtonAlterar = new javax.swing.JButton();
        jButtonLimpar = new javax.swing.JButton();
        jButtonRemover = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("[PESSOAS FÍSICA]");
        setMaximumSize(new java.awt.Dimension(1280, 720));
        setMinimumSize(new java.awt.Dimension(1280, 720));
        setPreferredSize(new java.awt.Dimension(1280, 720));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2PessoaFisica.setBackground(new java.awt.Color(0, 0, 0));
        jPanel2PessoaFisica.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)), "Dados Gerais", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Lucida Grande", 0, 13), new java.awt.Color(255, 255, 255))); // NOI18N

        jLabelIdPessoaFisica.setForeground(new java.awt.Color(204, 204, 204));
        jLabelIdPessoaFisica.setText("ID");

        jTextFieldIdPessoaFisica.setEditable(false);

        jLabelCPFPessoaFisica.setForeground(new java.awt.Color(204, 204, 204));
        jLabelCPFPessoaFisica.setText("CPF");

        jLabelNomePessoaFisica.setForeground(new java.awt.Color(204, 204, 204));
        jLabelNomePessoaFisica.setText("NOME");

        jLabel3RGPessoaFisica.setForeground(new java.awt.Color(204, 204, 204));
        jLabel3RGPessoaFisica.setText("RG");

        try {
            jTextFieldCPFPessoaFisica.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        try {
            jTextFieldRGPessoaFisica.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("#######")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        javax.swing.GroupLayout jPanel2PessoaFisicaLayout = new javax.swing.GroupLayout(jPanel2PessoaFisica);
        jPanel2PessoaFisica.setLayout(jPanel2PessoaFisicaLayout);
        jPanel2PessoaFisicaLayout.setHorizontalGroup(
            jPanel2PessoaFisicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2PessoaFisicaLayout.createSequentialGroup()
                .addGroup(jPanel2PessoaFisicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2PessoaFisicaLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jTextFieldIdPessoaFisica, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2PessoaFisicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2PessoaFisicaLayout.createSequentialGroup()
                                .addGroup(jPanel2PessoaFisicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3RGPessoaFisica)
                                    .addComponent(jTextFieldRGPessoaFisica, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(38, 38, 38)
                                .addGroup(jPanel2PessoaFisicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextFieldCPFPessoaFisica, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabelCPFPessoaFisica)))
                            .addComponent(jTextFieldNomePessoaFisica, javax.swing.GroupLayout.PREFERRED_SIZE, 422, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2PessoaFisicaLayout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jLabelIdPessoaFisica)
                        .addGap(28, 28, 28)
                        .addComponent(jLabelNomePessoaFisica)))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        jPanel2PessoaFisicaLayout.setVerticalGroup(
            jPanel2PessoaFisicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2PessoaFisicaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2PessoaFisicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelNomePessoaFisica)
                    .addComponent(jLabelIdPessoaFisica))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2PessoaFisicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextFieldIdPessoaFisica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldNomePessoaFisica, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2PessoaFisicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelCPFPessoaFisica)
                    .addComponent(jLabel3RGPessoaFisica, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2PessoaFisicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextFieldCPFPessoaFisica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldRGPessoaFisica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 9, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel2PessoaFisica, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, 520, 140));

        jPanel4.setBackground(new java.awt.Color(0, 0, 0));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)), "Dados para Contato", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Lucida Grande", 0, 13), new java.awt.Color(255, 255, 255))); // NOI18N

        jLabel4CPFPessoaFisica.setForeground(new java.awt.Color(204, 204, 204));
        jLabel4CPFPessoaFisica.setText("TELEFONE");

        jLabel1.setForeground(new java.awt.Color(204, 204, 204));
        jLabel1.setText("E-MAIL");

        try {
            jTextFieldTelefonePessoaFisica.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##) #####-####")));
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
                    .addComponent(jTextFieldTelefonePessoaFisica, javax.swing.GroupLayout.DEFAULT_SIZE, 201, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jLabel1))
                    .addComponent(jTextFieldEmailPessoaFisica, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel4CPFPessoaFisica, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextFieldEmailPessoaFisica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldTelefonePessoaFisica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14))
        );

        getContentPane().add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 200, 520, 100));

        jTableConsultaPessoaFisica.setBackground(new java.awt.Color(0, 0, 0));
        jTableConsultaPessoaFisica.setForeground(new java.awt.Color(255, 255, 255));
        jTableConsultaPessoaFisica.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "NOME", "TELEFONE", "EMAIL", "LOGRADOURO", "RG", "CPF", "CEP", "STATUS"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableConsultaPessoaFisica.setSelectionBackground(new java.awt.Color(255, 255, 255));
        jTableConsultaPessoaFisica.setSelectionForeground(new java.awt.Color(0, 0, 0));
        jTableConsultaPessoaFisica.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableConsultaPessoaFisicaMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jTableConsultaPessoaFisica);
        if (jTableConsultaPessoaFisica.getColumnModel().getColumnCount() > 0) {
            jTableConsultaPessoaFisica.getColumnModel().getColumn(0).setMaxWidth(40);
        }

        getContentPane().add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 320, 1220, 260));

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

        jComboBox_Cidades.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_CidadesActionPerformed(evt);
            }
        });

        jTextField_UF.setEditable(false);

        try {
            jTextFieldCepPessoaFisica.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##.###-###")));
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
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jTextFieldComplementoPessoaFisica, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jTextFieldLogradouroPessoaFisica, javax.swing.GroupLayout.PREFERRED_SIZE, 501, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addComponent(jTextFieldNumeroPessoaFisica)))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGap(8, 8, 8)
                                        .addComponent(jLabel6))
                                    .addComponent(jTextFieldRuaPessoaFisica, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7)
                                    .addComponent(jComboBox_Cidades, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(26, 26, 26)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField_UF, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel8))))
                        .addGap(16, 16, 16))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jTextFieldCepPessoaFisica, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldCepPessoaFisica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldLogradouroPessoaFisica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldNumeroPessoaFisica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldComplementoPessoaFisica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8))
                .addGap(3, 3, 3)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldRuaPessoaFisica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox_Cidades, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField_UF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12))
        );

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(578, 40, 670, -1));

        jButtonCadastrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/pi/icons/salve.png"))); // NOI18N
        jButtonCadastrar.setText("CADASTRAR");
        jButtonCadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCadastrarActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonCadastrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 600, 140, 50));

        jButtonAlterar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/pi/icons/editar.png"))); // NOI18N
        jButtonAlterar.setText("ALTERAR");
        jButtonAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAlterarActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonAlterar, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 600, 140, 50));

        jButtonLimpar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/pi/icons/limpar-limpo.png"))); // NOI18N
        jButtonLimpar.setText("LIMPAR");
        jButtonLimpar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLimparActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonLimpar, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 600, 140, 50));

        jButtonRemover.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/pi/icons/lixo.png"))); // NOI18N
        jButtonRemover.setText("EXCLUIR");
        jButtonRemover.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRemoverActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonRemover, new org.netbeans.lib.awtextra.AbsoluteConstraints(1110, 600, 140, 50));

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/pi/image/fundo desfolque bmw_Easy-Resize.com.jpg"))); // NOI18N
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonRemoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRemoverActionPerformed
        try {
            if (jTableConsultaPessoaFisica.getSelectedRow() == -1) {
                throw new Exception("Selecione uma pessoa fisica na tabela para ser alterado!");
            }

            int id = Integer.parseInt(jTableConsultaPessoaFisica.getValueAt(jTableConsultaPessoaFisica.getSelectedRow(), 0).toString());
            pessoaFisica = pessoaFisicaBll.getPessoasFisicasBy(id);
            endereco = enderecoBll.getConsultaPorId(pessoaFisica.getCliente().getEnderecos().getIden());
            cliente = clienteBll.getClienteById(pessoaFisica.getCliente().getIden());

            pessoaFisicaBll.deletePessoasFisicas(pessoaFisica);
            clienteBll.deleteClientes(cliente);
            enderecoBll.deleteEndereco(endereco);
            JOptionPane.showMessageDialog(null, pessoaFisica.getCliente().getNome() + " removido com sucesso no sistema!");
            preencherGridPessoaFisica();
            limparCampos();

        } catch (Exception error) {
            JOptionPane.showMessageDialog(rootPane, error.getMessage(), "Menssagem", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButtonRemoverActionPerformed

    private void jButtonAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAlterarActionPerformed
        int chegou = 0;
        try {
            if (jTableConsultaPessoaFisica.getSelectedRow() == -1) {
                throw new Exception("Selecione uma pessoa fisica na tabela para ser alterado!");
            }
            validaFormularioPessoasFisicas();
            int id = Integer.parseInt(jTableConsultaPessoaFisica.getValueAt(jTableConsultaPessoaFisica.getSelectedRow(), 0).toString());
            pessoaFisica = pessoaFisicaBll.getPessoasFisicasBy(id);
            endereco = enderecoBll.getConsultaPorId(pessoaFisica.getCliente().getEnderecos().getIden());
            cliente = clienteBll.getClienteById(pessoaFisica.getCliente().getIden());
            cidade = cidadeBll.getCidadesById(SplitReturnID(jComboBox_Cidades.getSelectedItem().toString()));

            endereco.setCidade(cidade);
            endereco.setCep(jTextFieldCepPessoaFisica.getText());
            endereco.setLogradouro(jTextFieldLogradouroPessoaFisica.getText());
            endereco.setComplemento(jTextFieldComplementoPessoaFisica.getText());
            endereco.setNumero(jTextFieldNumeroPessoaFisica.getText());
            endereco.setRua(jTextFieldRuaPessoaFisica.getText());
            enderecoBll.updateEndereco(endereco);

            chegou = 1;
            
            cliente.setEnderecos(endereco);
            cliente.setNome(jTextFieldNomePessoaFisica.getText());
            cliente.setTelefone(jTextFieldTelefonePessoaFisica.getText());
            cliente.setEmail(jTextFieldEmailPessoaFisica.getText());
            cliente.setTipo("FISICA");
            clienteBll.updateClientes(cliente);

            chegou = 2;
            
            pessoaFisica.setCliente(cliente);
            pessoaFisica.setRg(jTextFieldRGPessoaFisica.getText());
            pessoaFisica.setCpf(jTextFieldCPFPessoaFisica.getText());
            pessoaFisicaBll.updatePessoasFisicas(pessoaFisica);
            JOptionPane.showMessageDialog(null, pessoaFisica.getCliente().getNome() + " alterado com sucesso no sistema!");
            preencherGridPessoaFisica();
            limparCampos();

        } catch (Exception error) {
           
            try{
            if(chegou == 1)enderecoBll.deleteLast();
            if(chegou == 2){
                clienteBll.deleteLast();
                enderecoBll.deleteLast();
            }
            } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro!" + e.getMessage());
        }
            JOptionPane.showMessageDialog(rootPane, error.getMessage(), "Menssagem", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButtonAlterarActionPerformed

    private void jButtonCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCadastrarActionPerformed
        int chegou = 0;
        try {
            validaFormularioPessoasFisicas();
            cidade = cidadeBll.getCidadesById(SplitReturnID(jComboBox_Cidades.getSelectedItem().toString()));
            endereco.setCidade(cidade);
            endereco.setCep(jTextFieldCepPessoaFisica.getText());
            endereco.setLogradouro(jTextFieldLogradouroPessoaFisica.getText());
            endereco.setComplemento(jTextFieldComplementoPessoaFisica.getText());
            endereco.setNumero(jTextFieldNumeroPessoaFisica.getText());
            endereco.setRua(jTextFieldRuaPessoaFisica.getText());
            enderecoBll.AddEndereco(endereco);
            String cep = endereco.getCep();
            endereco = enderecoBll.getConsultaPorCEP(cep);
            
            chegou = 1;
            
            cliente.setEnderecos(endereco);
            cliente.setNome(jTextFieldNomePessoaFisica.getText());
            cliente.setTelefone(jTextFieldTelefonePessoaFisica.getText());
            cliente.setEmail(jTextFieldEmailPessoaFisica.getText());
            cliente.setStatus("ADIMPLENTE");
            cliente.setTipo("FISICA");
            cliente.setMulta(0);
            clienteBll.addClientes(cliente);
            String clienteTelefone = cliente.getTelefone();
            cliente = clienteBll.getClienteByTelefone(clienteTelefone);

            chegou = 2;
            
            pessoaFisica.setCliente(cliente);
            pessoaFisica.setRg(jTextFieldRGPessoaFisica.getText());
            pessoaFisica.setCpf(jTextFieldCPFPessoaFisica.getText());
            pessoaFisicaBll.addPessoasFisicas(pessoaFisica);

            JOptionPane.showMessageDialog(null, pessoaFisica.getCliente().getNome() + " cadastrado com sucesso no sistema!");
            preencherGridPessoaFisica();
            limparCampos();

        } catch (Exception error) {
            
             try{
            if(chegou == 1)enderecoBll.deleteLast();
            if(chegou == 2){
                clienteBll.deleteLast();
                enderecoBll.deleteLast();
            }
            } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro! " + e.getMessage());
        }
             
            JOptionPane.showMessageDialog(rootPane, error.getMessage(), "Menssagem", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButtonCadastrarActionPerformed

    private void jComboBox_CidadesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_CidadesActionPerformed
        try {
            cidade = cidadeBll.getCidadesById(SplitReturnID(jComboBox_Cidades.getSelectedItem().toString()));
            jTextField_UF.setText(cidade.getUf().getNome());
        } catch (Exception error) {
            JOptionPane.showMessageDialog(null, "Erro combo Cidades " + error.getMessage());
        }
    }//GEN-LAST:event_jComboBox_CidadesActionPerformed

    private void jButtonLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLimparActionPerformed
      limparCampos();
    }//GEN-LAST:event_jButtonLimparActionPerformed

    private void jTableConsultaPessoaFisicaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableConsultaPessoaFisicaMouseClicked
       try{
        preencherFormularioPessoaJuridica();
         } catch (Exception error) {
            JOptionPane.showMessageDialog(rootPane, error.getMessage(), "Menssagem", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jTableConsultaPessoaFisicaMouseClicked

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
    private javax.swing.JButton jButtonLimpar;
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
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelCPFPessoaFisica;
    private javax.swing.JLabel jLabelIdPessoaFisica;
    private javax.swing.JLabel jLabelNomePessoaFisica;
    private javax.swing.JPanel jPanel2PessoaFisica;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTableConsultaPessoaFisica;
    private javax.swing.JFormattedTextField jTextFieldCPFPessoaFisica;
    private javax.swing.JFormattedTextField jTextFieldCepPessoaFisica;
    private javax.swing.JTextField jTextFieldComplementoPessoaFisica;
    private javax.swing.JTextField jTextFieldEmailPessoaFisica;
    private javax.swing.JTextField jTextFieldIdPessoaFisica;
    private javax.swing.JTextField jTextFieldLogradouroPessoaFisica;
    private javax.swing.JTextField jTextFieldNomePessoaFisica;
    private javax.swing.JTextField jTextFieldNumeroPessoaFisica;
    private javax.swing.JFormattedTextField jTextFieldRGPessoaFisica;
    private javax.swing.JTextField jTextFieldRuaPessoaFisica;
    private javax.swing.JFormattedTextField jTextFieldTelefonePessoaFisica;
    private javax.swing.JTextField jTextField_UF;
    // End of variables declaration//GEN-END:variables
}
