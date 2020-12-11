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
import br.com.pi.bll.FotosBll;
import br.com.pi.bll.MotoristasBll;
import br.com.pi.bll.UfsBll;
import br.com.pi.model.Cidades;
import br.com.pi.model.Clientes;
import br.com.pi.model.Enderecos;
import br.com.pi.model.Fotos;
import br.com.pi.model.Motoristas;
import br.com.pi.model.PessoasFisicas;
import br.com.pi.model.Ufs;
import br.com.pi.util.Valida;
import java.awt.Image;
import java.io.File;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author jhonlinux
 */
public class TelaMotoristas extends javax.swing.JFrame {

    SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy", new Locale("pt", "BR"));
    private Motoristas motorista;
    private PessoasFisicas pessoaFisica;
    private MotoristasBll motoristabll;
    private Clientes cliente;
    private ClientesBll clienteBll;
    private Enderecos endereco;
    private EnderecosBll enderecoBll;
    private Cidades cidade;
    private CidadesBll cidadesBll;
    private UfsBll ufbll;
    private Ufs uf;
    private FotosBll fotoBll = null;
    private Fotos fotos = null;

    JFileChooser chooser = new JFileChooser();
    File f, destino;

    public TelaMotoristas() {
        initComponents();
        this.setExtendedState(MAXIMIZED_BOTH);
        try {

            motorista = new Motoristas();
            fotoBll = new FotosBll();
            pessoaFisica = new PessoasFisicas();
            motoristabll = new MotoristasBll();
            cliente = new Clientes();
            clienteBll = new ClientesBll();
            endereco = new Enderecos();
            enderecoBll = new EnderecosBll();
            cidade = new Cidades();
            cidadesBll = new CidadesBll();
            uf = new Ufs();
            ufbll = new UfsBll();

            preencherComboboxCidades();
            preencherGridMotorista();

        } catch (Exception error) {
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

    
    
    public void exibirFotoGrid(int iden_PessoaFisica) throws Exception {
            fotos = fotoBll.getFotosById(iden_PessoaFisica);
            File f = new File(fotos.getFot_caminho());

            /* OPCIONAL - Código para definir o tamanho da imagem na tela */
            ImageIcon imageIcon = new ImageIcon(f.getPath()); // load the image to a imageIcon
            Image image = imageIcon.getImage(); // transform it 
            Image newimg = image.getScaledInstance(303, 206, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way 
            /* Fim do código do redimensionamento */
            ImageIcon icon = new ImageIcon(newimg);
            jLabelIcone_CNHgrid.setIcon(icon);
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
        motorista = motoristabll.getMotoristaById(id);

        jTextFieldIDMotorista.setText("" + motorista.getIden());
        jTextFieldNomeMotorista.setText(motorista.getCliente().getNome());
        jTextField_rgMotorista.setText("" + motorista.getRg());
        jTextField_CpfMotorista.setText("" + motorista.getCpf());
        jTextFieldCNHMotorista.setText("" + motorista.getNumeroCnh());
        jFormattedTextField_Data_validade.setText(convertDate(motorista.getDataValidade()));
        jComboBox_CategoriaCnh.setSelectedItem(motorista.getCategoriaCnh());
        jTextFieldCepMotorista.setText("" + motorista.getCliente().getEnderecos().getCep());
        jTextFieldLogradouroMotorista.setText(motorista.getCliente().getEnderecos().getLogradouro());
        jTextFieldNumeroMotorista.setText("" + motorista.getCliente().getEnderecos().getNumero());
        jTextFieldComplementoMotorista.setText(motorista.getCliente().getEnderecos().getComplemento());
        jTextFieldRua.setText(motorista.getCliente().getEnderecos().getRua());
        jComboBox_Cidade.setSelectedItem(motorista.getCliente().getEnderecos().getCidade());
        jTextFieldTelefoneMotorista.setText("" + motorista.getCliente().getTelefone());
        jTextFieldEmailMotorista.setText("" + motorista.getCliente().getTelefone());
        jTextFieldCaminhoDoArquivo.setText(motorista.getFotos().getFot_caminho());
        exibirFotoGrid(motorista.getFotos().getFot_iden());
        
    }

    public void preencherComboboxCidades() throws Exception {
        try {
            jComboBox_Cidade.removeAllItems();
            ArrayList<Cidades> listaCidades = cidadesBll.getAllCidades();

            for (Cidades cidade : listaCidades) {
                jComboBox_Cidade.addItem(cidade.getNome());
            }
        } catch (Exception error) {
            JOptionPane.showMessageDialog(rootPane, error.getMessage(), "Menssagem", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void ValidaMotoristas() {
        Valida.SomenteNumero(jTextFieldNumeroMotorista.getText(), "Campo número do endereço deve conter somente números!");
        Valida.SomenteNumero(jTextField_rgMotorista.getText(), "Campo rg deve conter somente números!");
        Valida.campoVazio(jTextFieldNomeMotorista.getText(), "Campo nome deve ser preenchido!");
        Valida.notNumber(jTextFieldNomeMotorista.getText(), "Campo nome não deve conter números!");
        Valida.notSpecialCharacters(jTextFieldNomeMotorista.getText(), "Campo nome não deve conter caracteres especiais!");

    }

    public void limparCampos() {
        jTextFieldIDMotorista.setText("");
        jTextFieldNomeMotorista.setText("");
        jTextField_rgMotorista.setText("");
        jTextField_CpfMotorista.setText("");
        jTextFieldCNHMotorista.setText("");
        jFormattedTextField_Data_validade.setText("");
        jComboBox_CategoriaCnh.setSelectedIndex(0);
        jTextFieldCepMotorista.setText("");
        jTextFieldLogradouroMotorista.setText("");
        jTextFieldNumeroMotorista.setText("");
        jTextFieldComplementoMotorista.setText("");
        jTextFieldRua.setText("");
        jTextField_uf.setText("");
        jTextFieldTelefoneMotorista.setText("");
        jTextFieldEmailMotorista.setText("");
        jTextFieldCaminhoDoArquivo.setText("");
        jComboBox_Cidade.setSelectedIndex(0);
        jLabelIcone_CNHgrid.setIcon(null);
        jLabelFotos.setIcon(null);

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
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jFormattedTextField_Data_validade = new javax.swing.JFormattedTextField();
        jTextField_CpfMotorista = new javax.swing.JFormattedTextField();
        jTextField_rgMotorista = new javax.swing.JFormattedTextField();
        jTextFieldCNHMotorista = new javax.swing.JFormattedTextField();
        jLabel12 = new javax.swing.JLabel();
        jTextFieldCaminhoDoArquivo = new javax.swing.JTextField();
        jButtonSelecionar = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabelFotos = new javax.swing.JLabel();
        jComboBox_CategoriaCnh = new javax.swing.JComboBox<>();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
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
        jTextFieldCepMotorista = new javax.swing.JFormattedTextField();
        jTextField_uf = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jLabel4CPFPessoaFisica = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jTextFieldEmailMotorista = new javax.swing.JTextField();
        jTextFieldTelefoneMotorista = new javax.swing.JFormattedTextField();
        jButtonAlterar = new javax.swing.JButton();
        jButtonRemover = new javax.swing.JButton();
        jButtonLimpar = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTableConsultarMotorista = new javax.swing.JTable();
        jButtonCadastrar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabelIcone_CNHgrid = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("[MOTORISTAS]");

        jPanel2PessoaFisica.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Dados Gerais"));

        jLabelIdPessoaFisica.setText("ID");

        jTextFieldIDMotorista.setEditable(false);

        jLabelNomePessoaFisica.setText("Nome");

        jLabel3RGPessoaFisica.setText("CNH");

        jLabel9.setText("RG");

        jLabel10.setText("CPF");

        jLabel6.setText("CAT. CNH");

        jLabel13.setText("DATA DE VALIDADE DA CNH");

        try {
            jFormattedTextField_Data_validade.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        try {
            jTextField_CpfMotorista.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        try {
            jTextField_rgMotorista.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("#######")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        try {
            jTextFieldCNHMotorista.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###########")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        jLabel12.setText("Caminho da foto");

        jButtonSelecionar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/pi/icons/botao-de-login.png"))); // NOI18N
        jButtonSelecionar.setText("SELECIONE O ARQUIVO");
        jButtonSelecionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSelecionarActionPerformed(evt);
            }
        });

        jLabel16.setText("CNH");

        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabelFotos, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabelFotos, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jComboBox_CategoriaCnh.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "B", "C", "D", "E" }));

        javax.swing.GroupLayout jPanel2PessoaFisicaLayout = new javax.swing.GroupLayout(jPanel2PessoaFisica);
        jPanel2PessoaFisica.setLayout(jPanel2PessoaFisicaLayout);
        jPanel2PessoaFisicaLayout.setHorizontalGroup(
            jPanel2PessoaFisicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2PessoaFisicaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2PessoaFisicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2PessoaFisicaLayout.createSequentialGroup()
                        .addComponent(jLabelIdPessoaFisica)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldIDMotorista, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonSelecionar)
                        .addGap(55, 55, 55))
                    .addGroup(jPanel2PessoaFisicaLayout.createSequentialGroup()
                        .addComponent(jFormattedTextField_Data_validade, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel12)
                        .addGap(84, 84, 84))
                    .addGroup(jPanel2PessoaFisicaLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(jPanel2PessoaFisicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2PessoaFisicaLayout.createSequentialGroup()
                                .addGroup(jPanel2PessoaFisicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel9)
                                    .addComponent(jTextField_rgMotorista, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3RGPessoaFisica))
                                .addGroup(jPanel2PessoaFisicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2PessoaFisicaLayout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel2PessoaFisicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jTextField_CpfMotorista)
                                            .addGroup(jPanel2PessoaFisicaLayout.createSequentialGroup()
                                                .addComponent(jLabel10)
                                                .addGap(0, 0, Short.MAX_VALUE))))
                                    .addGroup(jPanel2PessoaFisicaLayout.createSequentialGroup()
                                        .addGap(109, 109, 109)
                                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel2PessoaFisicaLayout.createSequentialGroup()
                                .addComponent(jTextFieldCNHMotorista)
                                .addGap(18, 18, 18)
                                .addComponent(jComboBox_CategoriaCnh, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2PessoaFisicaLayout.createSequentialGroup()
                                .addGroup(jPanel2PessoaFisicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2PessoaFisicaLayout.createSequentialGroup()
                                        .addComponent(jLabelNomePessoaFisica)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTextFieldNomeMotorista, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel13))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGroup(jPanel2PessoaFisicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2PessoaFisicaLayout.createSequentialGroup()
                                .addGroup(jPanel2PessoaFisicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2PessoaFisicaLayout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(jTextFieldCaminhoDoArquivo, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel2PessoaFisicaLayout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(22, 22, 22))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2PessoaFisicaLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel16)
                                .addGap(126, 126, 126))))))
        );
        jPanel2PessoaFisicaLayout.setVerticalGroup(
            jPanel2PessoaFisicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2PessoaFisicaLayout.createSequentialGroup()
                .addGroup(jPanel2PessoaFisicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2PessoaFisicaLayout.createSequentialGroup()
                        .addComponent(jButtonSelecionar, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2PessoaFisicaLayout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addGap(5, 5, 5)))
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(10, 10, 10)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldCaminhoDoArquivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26))
            .addGroup(jPanel2PessoaFisicaLayout.createSequentialGroup()
                .addGroup(jPanel2PessoaFisicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelIdPessoaFisica)
                    .addComponent(jTextFieldIDMotorista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(jPanel2PessoaFisicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelNomePessoaFisica)
                    .addComponent(jTextFieldNomeMotorista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2PessoaFisicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2PessoaFisicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField_CpfMotorista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField_rgMotorista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2PessoaFisicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3RGPessoaFisica, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2PessoaFisicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldCNHMotorista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox_CategoriaCnh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jFormattedTextField_Data_validade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(52, 52, 52))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Dados de Endereço"));

        jLabel2.setText("CEP");

        jLabel3.setText("LOGRADOURO");

        jLabel4.setText("N°");

        jTextFieldNumeroMotorista.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldNumeroMotoristaActionPerformed(evt);
            }
        });

        jLabel5.setText("COMPLEMENTO");

        jLabel7.setText("CIDADE");

        jLabel8.setText("UF");

        jComboBox_Cidade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_CidadeActionPerformed(evt);
            }
        });

        jLabel14.setText("RUA");

        jTextField_UF.setEditable(false);

        try {
            jTextFieldCepMotorista.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##.###-###")));
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
                            .addComponent(jTextFieldComplementoMotorista, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jTextFieldCepMotorista, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGap(7, 7, 7)
                                        .addComponent(jLabel3))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTextFieldLogradouroMotorista, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jTextFieldNumeroMotorista, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel14)
                                .addGap(124, 124, 124)
                                .addComponent(jLabel7)))
                        .addGap(182, 182, 182))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jTextFieldRua, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jComboBox_Cidade, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jTextField_uf)
                        .addGap(128, 128, 128)
                        .addComponent(jTextField_UF, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldCepMotorista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldLogradouroMotorista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldNumeroMotorista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldComplementoMotorista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldRua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox_Cidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField_UF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField_uf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(33, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Dados para Contato"));

        jLabel4CPFPessoaFisica.setText("TELEFONE");

        jLabel1.setText("E-Mail");

        try {
            jTextFieldTelefoneMotorista.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##) #####-####")));
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
                    .addComponent(jLabel4CPFPessoaFisica)
                    .addComponent(jTextFieldTelefoneMotorista, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jTextFieldEmailMotorista, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(14, 14, 14)
                        .addComponent(jTextFieldEmailMotorista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel4CPFPessoaFisica)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextFieldTelefoneMotorista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButtonAlterar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/pi/icons/editar.png"))); // NOI18N
        jButtonAlterar.setText("ALTERAR");
        jButtonAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAlterarActionPerformed(evt);
            }
        });

        jButtonRemover.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/pi/icons/lixo.png"))); // NOI18N
        jButtonRemover.setText("EXCLUIR");
        jButtonRemover.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRemoverActionPerformed(evt);
            }
        });

        jButtonLimpar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/pi/icons/limpar-limpo.png"))); // NOI18N
        jButtonLimpar.setText("LIMPAR");
        jButtonLimpar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLimparActionPerformed(evt);
            }
        });

        jTableConsultarMotorista.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "NOME", "Nº CNH", "TELEFONE", "LOGRADOURO", "CEP"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableConsultarMotorista.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableConsultarMotoristaMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jTableConsultarMotorista);

        jButtonCadastrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/pi/icons/salve.png"))); // NOI18N
        jButtonCadastrar.setText("CADASTRAR");
        jButtonCadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCadastrarActionPerformed(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabelIcone_CNHgrid, javax.swing.GroupLayout.DEFAULT_SIZE, 303, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabelIcone_CNHgrid, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jPanel2PessoaFisica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(29, 29, 29)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 532, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jButtonCadastrar, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButtonLimpar, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(679, 679, 679)
                                .addComponent(jButtonRemover, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane3)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 4, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel2PessoaFisica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButtonCadastrar, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButtonLimpar, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButtonRemover, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonAlterar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jTabbedPane1PessoaFisica.addTab("Dados Gerais", jPanel1);

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

    private void jTableConsultarMotoristaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableConsultarMotoristaMouseClicked
       try{
        preencherFormularioMotoristas();
       } catch (Exception error) {
            JOptionPane.showMessageDialog(rootPane, error.getMessage(), "Menssagem", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jTableConsultarMotoristaMouseClicked

    private void jButtonLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLimparActionPerformed
        limparCampos();
    }//GEN-LAST:event_jButtonLimparActionPerformed

    private void jButtonRemoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRemoverActionPerformed
        try {
            if (jTableConsultarMotorista.getSelectedRow() == -1) {
                throw new Exception("Selecione um motorista na tabela para ser alterado!");
            }

            int id = Integer.parseInt(jTableConsultarMotorista.getValueAt(jTableConsultarMotorista.getSelectedRow(), 0).toString());
            motorista = motoristabll.getMotoristaById(id);
            endereco = enderecoBll.getConsultaPorId(motorista.getCliente().getEnderecos().getIden());
            cliente = clienteBll.getClienteById(motorista.getCliente().getIden());

            motoristabll.deleteMotoristas(motorista);
            clienteBll.deleteClientes(cliente);
            enderecoBll.deleteEndereco(endereco);
            JOptionPane.showMessageDialog(null, motorista.getCliente().getNome() + " removido com sucesso no sistema!");
            preencherGridMotorista();
            limparCampos();

        } catch (Exception error) {
            JOptionPane.showMessageDialog(rootPane, error.getMessage(), "Menssagem", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButtonRemoverActionPerformed

    private void jButtonAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAlterarActionPerformed
        int chegou = 0;
        try {
            if (jTableConsultarMotorista.getSelectedRow() == -1) {
                throw new Exception("Selecione um motorista na tabela para ser alterado!");
            }
            ValidaMotoristas();
            
            String NomeDoArquivo = f.getName();
            File destino = new File("C:\\Users\\Gustavo Gabriel\\Pictures\\Saved Pictures" + NomeDoArquivo);

            destino = destino.getAbsoluteFile();

            Fotos foto = new Fotos();
            foto.setFot_caminho(destino.getAbsoluteFile().toString());
            fotoBll.addFotos(foto);
            if (!destino.exists()) {
                Files.copy(f.toPath(), destino.toPath());
            }
            
            foto = fotoBll.getFotosByCaminho(foto.getFot_caminho());
            
            int id = Integer.parseInt(jTableConsultarMotorista.getValueAt(jTableConsultarMotorista.getSelectedRow(), 0).toString());
            motorista = motoristabll.getMotoristaById(id);
            endereco = enderecoBll.getConsultaPorId(motorista.getCliente().getEnderecos().getIden());
            cliente = clienteBll.getClienteById(motorista.getCliente().getIden());
            cidade = cidadesBll.getCidadeNome(jComboBox_Cidade.getSelectedItem().toString());

            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
            Date data = formato.parse(jFormattedTextField_Data_validade.getText());

            endereco.setCidade(cidade);
            endereco.setCep(jTextFieldCepMotorista.getText());
            endereco.setLogradouro(jTextFieldLogradouroMotorista.getText());
            endereco.setComplemento(jTextFieldComplementoMotorista.getText());
            endereco.setNumero(jTextFieldNumeroMotorista.getText());
            endereco.setRua(jTextFieldRua.getText());
            enderecoBll.updateEndereco(endereco);

            chegou = 1;

            cliente.setEnderecos(endereco);
            cliente.setNome(jTextFieldNomeMotorista.getText());
            cliente.setTelefone(jTextFieldTelefoneMotorista.getText());
            cliente.setEmail(jTextFieldEmailMotorista.getText());
            cliente.setTipo("MOTORISTA");
            clienteBll.updateClientes(cliente);

            chegou = 2;
            
            motorista.setFotos(foto);
            motorista.setCliente(cliente);
            motorista.setRg(jTextField_rgMotorista.getText());
            motorista.setCpf(jTextField_CpfMotorista.getText());
            motorista.setNumeroCnh(jTextFieldCNHMotorista.getText());
            motorista.setCategoriaCnh(jComboBox_CategoriaCnh.getSelectedItem().toString());
            motorista.setDataValidade(data);
            motoristabll.updateMotorista(motorista);

            JOptionPane.showMessageDialog(null, motorista.getCliente().getNome() + " alterado com sucesso no sistema!");
            preencherGridMotorista();
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

    private void jButtonCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCadastrarActionPerformed
        int chegou = 0;
        try {
            ValidaMotoristas();

            String NomeDoArquivo = f.getName();
            File destino = new File("C:\\Users\\Gustavo Gabriel\\Pictures\\Saved Pictures" + NomeDoArquivo);

            destino = destino.getAbsoluteFile();

            Fotos foto = new Fotos();
            foto.setFot_caminho(destino.getAbsoluteFile().toString());
            fotoBll.addFotos(foto);
            if (!destino.exists()) {
                Files.copy(f.toPath(), destino.toPath());
            }
            
            foto = fotoBll.getFotosByCaminho(foto.getFot_caminho());
            
            Date data = formato.parse(jFormattedTextField_Data_validade.getText());

            cidade = cidadesBll.getCidadeNome(jComboBox_Cidade.getSelectedItem().toString());
            endereco.setCidade(cidade);
            endereco.setCep(jTextFieldCepMotorista.getText());
            endereco.setLogradouro(jTextFieldLogradouroMotorista.getText());
            endereco.setComplemento(jTextFieldComplementoMotorista.getText());
            endereco.setNumero(jTextFieldNumeroMotorista.getText());
            endereco.setRua(jTextFieldRua.getText());
            enderecoBll.AddEndereco(endereco);
            String cep = endereco.getCep();
            endereco = enderecoBll.getConsultaPorCEP(cep);

            chegou = 1;

            cliente.setEnderecos(endereco);
            cliente.setNome(jTextFieldNomeMotorista.getText());
            cliente.setTelefone(jTextFieldTelefoneMotorista.getText());
            cliente.setEmail(jTextFieldEmailMotorista.getText());
            cliente.setStatus("ADIMPLENTE");
            cliente.setTipo("MOTORISTA");
            cliente.setMulta(0);
            clienteBll.addClientes(cliente);
            String clienteTelefone = cliente.getTelefone();
            cliente = clienteBll.getClienteByTelefone(clienteTelefone);

            chegou = 2;
            
            motorista.setFotos(foto);
            motorista.setCliente(cliente);
            motorista.setRg(jTextField_rgMotorista.getText());
            motorista.setCpf(jTextField_CpfMotorista.getText());
            motorista.setNumeroCnh(jTextFieldCNHMotorista.getText());
            motorista.setCategoriaCnh(jComboBox_CategoriaCnh.getSelectedItem().toString());
            motorista.setDataValidade(data);

            motoristabll.addMotoristas(motorista);

            JOptionPane.showMessageDialog(null, motorista.getCliente().getNome() + " cadastrado com sucesso no sistema!");
            preencherGridMotorista();
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
    }//GEN-LAST:event_jButtonCadastrarActionPerformed

    private void jComboBox_CidadeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_CidadeActionPerformed
        try {
            cidade = cidadesBll.getCidadeNome(jComboBox_Cidade.getSelectedItem().toString());
            jTextField_uf.setText(cidade.getUf().getNome());
        } catch (Exception error) {
            JOptionPane.showMessageDialog(null, "Erro na combo Cidades " + error.getMessage());
        }
    }//GEN-LAST:event_jComboBox_CidadeActionPerformed

    private void jButtonSelecionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSelecionarActionPerformed
        String titulo = "Selecione uma imagem";
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("JPG & PNG Imagens", "jpg", "png");
        chooser.setDialogTitle(titulo);
        chooser.setFileFilter(filtro);

        chooser.showOpenDialog(null);
        f = chooser.getSelectedFile();
        String foto = f.getAbsolutePath();
        jTextFieldCaminhoDoArquivo.setText(foto);

        /* OPCIONAL - Código para definir o tamanho da imagem na tela */
        ImageIcon imageIcon = new ImageIcon(f.getPath()); // load the image to a imageIcon
        Image image = imageIcon.getImage(); // transform it
        Image newimg = image.getScaledInstance(240, 176, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        /* Fim do código do redimensionamento */

        ImageIcon icon = new ImageIcon(newimg);
        jLabelFotos.setIcon(icon);
        jTextFieldCaminhoDoArquivo.setEditable(false);
    }//GEN-LAST:event_jButtonSelecionarActionPerformed
    
    
    private void jTextFieldNumeroMotoristaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldNumeroMotoristaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldNumeroMotoristaActionPerformed

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
    private javax.swing.JButton jButtonLimpar;
    private javax.swing.JButton jButtonRemover;
    private javax.swing.JButton jButtonSelecionar;
    private javax.swing.JComboBox<String> jComboBox_CategoriaCnh;
    private javax.swing.JComboBox<String> jComboBox_Cidade;
    private javax.swing.JFormattedTextField jFormattedTextField_Data_validade;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
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
    private javax.swing.JLabel jLabelFotos;
    private javax.swing.JLabel jLabelIcone_CNHgrid;
    private javax.swing.JLabel jLabelIdPessoaFisica;
    private javax.swing.JLabel jLabelNomePessoaFisica;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel2PessoaFisica;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1PessoaFisica;
    private javax.swing.JTable jTableConsultarMotorista;
    private javax.swing.JFormattedTextField jTextFieldCNHMotorista;
    private javax.swing.JTextField jTextFieldCaminhoDoArquivo;
    private javax.swing.JFormattedTextField jTextFieldCepMotorista;
    private javax.swing.JTextField jTextFieldComplementoMotorista;
    private javax.swing.JTextField jTextFieldEmailMotorista;
    private javax.swing.JTextField jTextFieldIDMotorista;
    private javax.swing.JTextField jTextFieldLogradouroMotorista;
    private javax.swing.JTextField jTextFieldNomeMotorista;
    private javax.swing.JTextField jTextFieldNumeroMotorista;
    private javax.swing.JTextField jTextFieldRua;
    private javax.swing.JFormattedTextField jTextFieldTelefoneMotorista;
    private javax.swing.JFormattedTextField jTextField_CpfMotorista;
    private javax.swing.JTextField jTextField_UF;
    private javax.swing.JFormattedTextField jTextField_rgMotorista;
    private javax.swing.JTextField jTextField_uf;
    // End of variables declaration//GEN-END:variables
}
