/*
 *  -------------------------------------------------------------------------------------------------->
 *  Licença    : MIT - Copyright 2019 Jhonathan, Gustavo e Miguel 
 *  Criado em  : 20/11/2020 19:16:01 
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
import br.com.pi.bll.MotoristasBll;
import br.com.pi.bll.UfsBll;
import br.com.pi.model.Cidades;
import br.com.pi.model.Clientes;
import br.com.pi.model.Enderecos;
import br.com.pi.model.Motoristas;
import br.com.pi.model.Ufs;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author jhonlinux
 */
public class TelaMotoristas extends javax.swing.JFrame {
    SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy", new Locale("pt", "BR"));
    private Motoristas motorista;
    private MotoristasBll motoristabll;
    private Clientes cliente;
    private ClientesBll clienteBll;
    private Enderecos endereco;
    private EnderecosBll enderecoBll;
    private Cidades cidade;
    private CidadesBll cidadesBll;
    private UfsBll ufbll;
    private Ufs uf;
    
    public TelaMotoristas() {
        initComponents();
        
        try{
            
           motorista = new Motoristas();
           motoristabll = new MotoristasBll();
           cliente = new Clientes();
           clienteBll = new ClientesBll();
           endereco = new Enderecos();
           enderecoBll = new EnderecosBll();
           cidade = new Cidades();
           cidadesBll = new  CidadesBll();
           uf = new Ufs();
           ufbll = new UfsBll();
           
           preencherComboboxCidades();
           preencherGridMotorista();
           
        }catch(Exception error){
           JOptionPane.showMessageDialog(rootPane, error.getMessage(), "Menssagem", JOptionPane.ERROR_MESSAGE);
        }
        
        this.setLocationRelativeTo(null);
    }
    
    public void preencherGridMotorista() throws Exception {

        try {
            DefaultTableModel tableMotoristas = (DefaultTableModel) jTableConsultarMotorista.getModel();
            tableMotoristas.setRowCount(0);

            Object[] coluna = new Object[6];

            ArrayList<Motoristas> listaMotoristas = motoristabll.getAllMotoristas();

            for (Motoristas motorista : listaMotoristas) {

                coluna[0] = motorista.getIden();
                coluna[1] = motorista.getCliente().getNome();
                coluna[2] = motorista.getNumeroCnh();
                coluna[3] = motorista.getCliente().getTelefone();
                coluna[4] = motorista.getCliente().getEnderecos().getLogradouro();
                coluna[5] = motorista.getCliente().getEnderecos().getCep();

                tableMotoristas.addRow(coluna);
            }
        } catch (Exception error) {
            JOptionPane.showMessageDialog(null, error.getMessage(), "Menssagem", JOptionPane.ERROR_MESSAGE);
        }
    }
    public static String convertDate(Date dtConsulta) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy", new Locale("pt", "BR"));
            return formatter.format(dtConsulta);
        } catch (Exception e) {
            return null;
        }
    }
      public void preencherFormularioMotoristas() throws Exception {
       
        int id = Integer.parseInt(jTableConsultarMotorista.getValueAt(jTableConsultarMotorista.getSelectedRow(), 0).toString());
        motorista = motoristabll.getMotoristaBy(id);

        jTextFieldIDMotorista.setText(""+motorista.getIden());
        jTextFieldNomeMotorista.setText(motorista.getCliente().getNome());
        jTextField_rgMotorista.setText(""+motorista.getRg());
        jTextField_CpfMotorista.setText(""+motorista.getCpf());
        jTextFieldCNHMotorista.setText(""+motorista.getNumeroCnh());
        jFormattedTextField_Data_validade.setText(convertDate(motorista.getDataValidade()));
        jTextField_CategoriaCNH.setText(motorista.getCategoriaCnh());
        jTextFieldCepMotorista.setText(""+motorista.getCliente().getEnderecos().getCep());
        jTextFieldLogradouroMotorista.setText(motorista.getCliente().getEnderecos().getLogradouro());
        jTextFieldNumeroMotorista.setText(""+motorista.getCliente().getEnderecos().getNumero());
        jTextFieldComplementoMotorista.setText(motorista.getCliente().getEnderecos().getComplemento());
        jTextFieldRua.setText(motorista.getCliente().getEnderecos().getRua());
        jComboBox_Cidade.setSelectedItem(motorista.getCliente().getEnderecos().getCidade());
        jTextFieldTelefoneMotorista.setText(""+motorista.getCliente().getTelefone());
        jTextFieldEmailMotorista.setText(""+motorista.getCliente().getTelefone());
        
    }
    
    public void preencherComboboxCidades() throws Exception{
           try{
            jComboBox_Cidade.removeAllItems();
            ArrayList<Cidades> listaCidades = cidadesBll.getAllCidades();
            
            for (Cidades cidade : listaCidades) {
               jComboBox_Cidade.addItem(cidade.getNome());
            }  
           }catch(Exception error){
                JOptionPane.showMessageDialog(rootPane, error.getMessage(), "Menssagem", JOptionPane.ERROR_MESSAGE);
           }
    }
    
    public void limparCampos(){
      
    }

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
        jTextFieldIDMotorista = new javax.swing.JTextField();
        jLabelNomePessoaFisica = new javax.swing.JLabel();
        jTextFieldNomeMotorista = new javax.swing.JTextField();
        jLabel3RGPessoaFisica = new javax.swing.JLabel();
        jTextFieldCNHMotorista = new javax.swing.JTextField();
        jButtonSelecionarCNH2 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jTextField_rgMotorista = new javax.swing.JTextField();
        jTextField_CpfMotorista = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jFormattedTextField_Data_validade = new javax.swing.JFormattedTextField();
        jTextField_CategoriaCNH = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jTextFieldCepMotorista = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTextFieldLogradouroMotorista = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTextFieldNumeroMotorista = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTextFieldComplementoMotorista = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jComboBox_Cidade = new javax.swing.JComboBox<>();
        jLabel14 = new javax.swing.JLabel();
        jTextFieldRua = new javax.swing.JTextField();
        jTextField_UF = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jLabel4CPFPessoaFisica = new javax.swing.JLabel();
        jTextFieldTelefoneMotorista = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jTextFieldEmailMotorista = new javax.swing.JTextField();
        jButtonCadastrar = new javax.swing.JButton();
        jButtonAlterar = new javax.swing.JButton();
        jButtonRemover = new javax.swing.JButton();
        jButtonLimpar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jTextFieldPesquisarMotorista = new javax.swing.JTextField();
        jButtonConsultarMotorista = new javax.swing.JButton();
        jButtonLimparConsulta = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jButtonSetaEsquerda1 = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        jButtonSetaDireita1 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableConsultarMotorista = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("[MOTORISTAS]");

        jPanel2PessoaFisica.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Dados Gerais"));

        jLabelIdPessoaFisica.setText("ID");

        jTextFieldIDMotorista.setEditable(false);

        jLabelNomePessoaFisica.setText("Nome");

        jLabel3RGPessoaFisica.setText("CNH");

        jButtonSelecionarCNH2.setText("SELECIONE A FOTO DA CNH");

        jLabel9.setText("RG");

        jLabel10.setText("CPF");

        jLabel6.setText("CATEGORIA CNH");

        jLabel13.setText("DATA DE VALIDADE DA CNH");

        try {
            jFormattedTextField_Data_validade.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        javax.swing.GroupLayout jPanel2PessoaFisicaLayout = new javax.swing.GroupLayout(jPanel2PessoaFisica);
        jPanel2PessoaFisica.setLayout(jPanel2PessoaFisicaLayout);
        jPanel2PessoaFisicaLayout.setHorizontalGroup(
            jPanel2PessoaFisicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2PessoaFisicaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2PessoaFisicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelIdPessoaFisica)
                    .addComponent(jTextFieldIDMotorista, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2PessoaFisicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2PessoaFisicaLayout.createSequentialGroup()
                        .addGroup(jPanel2PessoaFisicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3RGPessoaFisica)
                            .addComponent(jTextFieldCNHMotorista))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2PessoaFisicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jFormattedTextField_Data_validade, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2PessoaFisicaLayout.createSequentialGroup()
                        .addGroup(jPanel2PessoaFisicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelNomePessoaFisica)
                            .addComponent(jTextFieldNomeMotorista, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28)
                        .addGroup(jPanel2PessoaFisicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(jTextField_rgMotorista, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(40, 40, 40)
                .addGroup(jPanel2PessoaFisicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2PessoaFisicaLayout.createSequentialGroup()
                        .addGroup(jPanel2PessoaFisicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel2PessoaFisicaLayout.createSequentialGroup()
                                .addComponent(jTextField_CategoriaCNH, javax.swing.GroupLayout.PREFERRED_SIZE, 303, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButtonSelecionarCNH2, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2PessoaFisicaLayout.createSequentialGroup()
                        .addGroup(jPanel2PessoaFisicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(jTextField_CpfMotorista, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel2PessoaFisicaLayout.setVerticalGroup(
            jPanel2PessoaFisicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2PessoaFisicaLayout.createSequentialGroup()
                .addGroup(jPanel2PessoaFisicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel2PessoaFisicaLayout.createSequentialGroup()
                        .addComponent(jLabelNomePessoaFisica)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldNomeMotorista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2PessoaFisicaLayout.createSequentialGroup()
                        .addComponent(jLabelIdPessoaFisica)
                        .addGap(17, 17, 17)
                        .addComponent(jTextFieldIDMotorista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2PessoaFisicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2PessoaFisicaLayout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jTextField_rgMotorista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2PessoaFisicaLayout.createSequentialGroup()
                            .addGroup(jPanel2PessoaFisicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel10))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jTextField_CpfMotorista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(jPanel2PessoaFisicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2PessoaFisicaLayout.createSequentialGroup()
                        .addComponent(jLabel3RGPessoaFisica)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldCNHMotorista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2PessoaFisicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2PessoaFisicaLayout.createSequentialGroup()
                            .addComponent(jLabel6)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jTextField_CategoriaCNH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2PessoaFisicaLayout.createSequentialGroup()
                            .addComponent(jLabel13)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jFormattedTextField_Data_validade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jButtonSelecionarCNH2, javax.swing.GroupLayout.Alignment.TRAILING)))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Dados de Endereço"));

        jLabel2.setText("CEP");

        jTextFieldCepMotorista.setText("00-000-000");

        jLabel3.setText("LOGRADOURO");

        jLabel4.setText("N°");

        jLabel5.setText("COMPLEMENTO");

        jLabel7.setText("CIDADE");

        jLabel8.setText("UF");

        jComboBox_Cidade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_CidadeActionPerformed(evt);
            }
        });

        jLabel14.setText("Rua");

        jTextField_UF.setEditable(false);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jTextFieldCepMotorista, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(33, 33, 33)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jTextFieldLogradouroMotorista, javax.swing.GroupLayout.PREFERRED_SIZE, 677, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jTextFieldNumeroMotorista, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jTextFieldComplementoMotorista)
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addComponent(jLabel7)
                                    .addGap(401, 401, 401))
                                .addComponent(jComboBox_Cidade, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(33, 33, 33)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel14)
                                    .addComponent(jTextFieldRua, javax.swing.GroupLayout.PREFERRED_SIZE, 406, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(34, 34, 34)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8)
                                    .addComponent(jTextField_UF, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextFieldCepMotorista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldLogradouroMotorista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextFieldNumeroMotorista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel14))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldComplementoMotorista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldRua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jComboBox_Cidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 5, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jTextField_UF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Dados para Contato"));

        jLabel4CPFPessoaFisica.setText("TELEFONE");

        jTextFieldTelefoneMotorista.setText("(62) 9 9999-9999");

        jLabel1.setText("E-Mail");

        jTextFieldEmailMotorista.setText("prototipação@hotmail.com");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4CPFPessoaFisica)
                    .addComponent(jTextFieldTelefoneMotorista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(74, 74, 74)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jTextFieldEmailMotorista, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                        .addComponent(jTextFieldEmailMotorista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel4CPFPessoaFisica)
                        .addGap(14, 14, 14)
                        .addComponent(jTextFieldTelefoneMotorista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                    .addComponent(jPanel2PessoaFisica, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addComponent(jButtonCadastrar)
                        .addGap(225, 225, 225)
                        .addComponent(jButtonAlterar)
                        .addGap(238, 238, 238)
                        .addComponent(jButtonRemover)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 498, Short.MAX_VALUE)
                        .addComponent(jButtonLimpar)
                        .addGap(43, 43, 43))
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2PessoaFisica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonCadastrar)
                    .addComponent(jButtonAlterar)
                    .addComponent(jButtonRemover)
                    .addComponent(jButtonLimpar))
                .addContainerGap(148, Short.MAX_VALUE))
        );

        jTabbedPane1PessoaFisica.addTab("Dados Gerais", jPanel1);

        jTextFieldPesquisarMotorista.setText("Digite aqui o objeto de sua pesquisa.....");

        jButtonConsultarMotorista.setText("CONSULTAR");

        jButtonLimparConsulta.setText("LIMPAR");

        jLabel11.setText("CNH");

        jButtonSetaEsquerda1.setText("<<");

        jButtonSetaDireita1.setText(">>");

        jTableConsultarMotorista.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "NOME", "Nº CNH", "TELEFONE", "LOGRADOURO", "CEP"
            }
        ));
        jTableConsultarMotorista.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableConsultarMotoristaMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTableConsultarMotorista);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jTextFieldPesquisarMotorista, javax.swing.GroupLayout.PREFERRED_SIZE, 561, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonConsultarMotorista)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonLimparConsulta))
                    .addComponent(jScrollPane2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 139, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jButtonSetaEsquerda1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 318, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonSetaDireita1))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(201, 201, 201)
                        .addComponent(jLabel11)))
                .addGap(39, 39, 39))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldPesquisarMotorista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonConsultarMotorista)
                    .addComponent(jButtonLimparConsulta)
                    .addComponent(jLabel11))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jButtonSetaEsquerda1, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jButtonSetaDireita1, javax.swing.GroupLayout.Alignment.TRAILING))
                            .addGap(97, 97, 97)))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(175, Short.MAX_VALUE))
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

        jTabbedPane1PessoaFisica.addTab("Consulta Motorista", jPanel2);

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
                .addComponent(jTabbedPane1PessoaFisica)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCadastrarActionPerformed
       try{
           
           
           Date data = formato.parse(jFormattedTextField_Data_validade.getText());
           
           cidade = cidadesBll.getCidadeNome(jComboBox_Cidade.getSelectedItem().toString());
           endereco.setCidade(cidade);
           endereco.setCep(Double.parseDouble(jTextFieldCepMotorista.getText()));
           endereco.setLogradouro(jTextFieldLogradouroMotorista.getText());
           endereco.setComplemento(jTextFieldComplementoMotorista.getText());
           endereco.setNumero(Float.parseFloat(jTextFieldNumeroMotorista.getText()));
           endereco.setRua(jTextFieldRua.getText());
           enderecoBll.AddEndereco(endereco);
           double cep = endereco.getCep();
           endereco = enderecoBll.getConsultaPorCEP(cep);
           
           cliente.setEnderecos(endereco);
           cliente.setNome(jTextFieldNomeMotorista.getText());
           cliente.setTelefone(Double.parseDouble(jTextFieldTelefoneMotorista.getText()));
           cliente.setEmail(jTextFieldEmailMotorista.getText());
           clienteBll.addClientes(cliente);
           double clienteTelefone = cliente.getTelefone();
           cliente = clienteBll.getClienteByTelefone(clienteTelefone);
           
           motorista.setCliente(cliente);
           motorista.setRg(Integer.parseInt(jTextField_rgMotorista.getText()));
           motorista.setCpf(Double.parseDouble(jTextField_CpfMotorista.getText()));       
           motorista.setNumeroCnh(Double.parseDouble(jTextFieldCNHMotorista.getText()));
           motorista.setCategoriaCnh(jTextField_CategoriaCNH.getText());
           motorista.setDataValidade(data);
           motoristabll.addMotoristas(motorista);
           
           JOptionPane.showMessageDialog(null, motorista.getCliente().getNome()+" cadastrado com sucesso no sistema!");
           preencherGridMotorista();
           limparCampos();
     
      }catch(Exception error){
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar motoristas "+error.getMessage());
        }
    }//GEN-LAST:event_jButtonCadastrarActionPerformed

    private void jComboBox_CidadeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_CidadeActionPerformed
        try{
        cidade = cidadesBll.getCidadeNome(jComboBox_Cidade.getSelectedItem().toString());
        jTextField_UF.setText(cidade.getUf().getNome());
        }catch(Exception error){
            JOptionPane.showMessageDialog(null, "Erro na combo Cidades "+error.getMessage());
        }
    }//GEN-LAST:event_jComboBox_CidadeActionPerformed

    private void jTableConsultarMotoristaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableConsultarMotoristaMouseClicked
         try {
            preencherFormularioMotoristas();
        } catch (Exception error) {
            JOptionPane.showMessageDialog(rootPane, error.getMessage(), "Menssagem", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_jTableConsultarMotoristaMouseClicked

    private void jButtonAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAlterarActionPerformed
     try{
        if (jTableConsultarMotorista.getSelectedRow() == -1) {
                throw new Exception("Selecione um motorista na tabela para ser alterado!");
            }
           int id = Integer.parseInt(jTableConsultarMotorista.getValueAt(jTableConsultarMotorista.getSelectedRow(), 0).toString());
           motorista = motoristabll.getMotoristaBy(id);
           endereco = enderecoBll.getConsultaPorId(motorista.getCliente().getEnderecos().getIden());
           cliente = clienteBll.getClienteById(motorista.getCliente().getIden());
           cidade = cidadesBll.getCidadeNome(jComboBox_Cidade.getSelectedItem().toString());
            
           SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy"); 
           Date data = formato.parse(jFormattedTextField_Data_validade.getText());

           endereco.setCidade(cidade);
           endereco.setCep(Double.parseDouble(jTextFieldCepMotorista.getText()));
           endereco.setLogradouro(jTextFieldLogradouroMotorista.getText());
           endereco.setComplemento(jTextFieldComplementoMotorista.getText());
           endereco.setNumero(Float.parseFloat(jTextFieldNumeroMotorista.getText()));
           endereco.setRua(jTextFieldRua.getText());
           enderecoBll.updateEndereco(endereco);
 
           cliente.setEnderecos(endereco);
           cliente.setNome(jTextFieldNomeMotorista.getText());
           cliente.setTelefone(Double.parseDouble(jTextFieldTelefoneMotorista.getText()));
           cliente.setEmail(jTextFieldEmailMotorista.getText());
           clienteBll.updateClientes(cliente);
           
           motorista.setCliente(cliente);
           motorista.setRg(Integer.parseInt(jTextField_rgMotorista.getText()));
           motorista.setCpf(Double.parseDouble(jTextField_CpfMotorista.getText()));       
           motorista.setNumeroCnh(Double.parseDouble(jTextFieldCNHMotorista.getText()));
           motorista.setCategoriaCnh(jTextField_CategoriaCNH.getText());
           motorista.setDataValidade(data);
           motoristabll.updateMotorista(motorista);
           
           JOptionPane.showMessageDialog(null, motorista.getCliente().getNome()+" alterado com sucesso no sistema!");
           preencherGridMotorista();
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
            java.util.logging.Logger.getLogger(TelaMotoristas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaMotoristas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaMotoristas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaMotoristas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaMotoristas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAlterar;
    private javax.swing.JButton jButtonCadastrar;
    private javax.swing.JButton jButtonConsultarMotorista;
    private javax.swing.JButton jButtonLimpar;
    private javax.swing.JButton jButtonLimparConsulta;
    private javax.swing.JButton jButtonRemover;
    private javax.swing.JButton jButtonSelecionarCNH2;
    private javax.swing.JButton jButtonSetaDireita1;
    private javax.swing.JButton jButtonSetaEsquerda1;
    private javax.swing.JComboBox<String> jComboBox_Cidade;
    private javax.swing.JFormattedTextField jFormattedTextField_Data_validade;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
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
    private javax.swing.JTable jTableConsultarMotorista;
    private javax.swing.JTextField jTextFieldCNHMotorista;
    private javax.swing.JTextField jTextFieldCepMotorista;
    private javax.swing.JTextField jTextFieldComplementoMotorista;
    private javax.swing.JTextField jTextFieldEmailMotorista;
    private javax.swing.JTextField jTextFieldIDMotorista;
    private javax.swing.JTextField jTextFieldLogradouroMotorista;
    private javax.swing.JTextField jTextFieldNomeMotorista;
    private javax.swing.JTextField jTextFieldNumeroMotorista;
    private javax.swing.JTextField jTextFieldPesquisarMotorista;
    private javax.swing.JTextField jTextFieldRua;
    private javax.swing.JTextField jTextFieldTelefoneMotorista;
    private javax.swing.JTextField jTextField_CategoriaCNH;
    private javax.swing.JTextField jTextField_CpfMotorista;
    private javax.swing.JTextField jTextField_UF;
    private javax.swing.JTextField jTextField_rgMotorista;
    // End of variables declaration//GEN-END:variables
}
