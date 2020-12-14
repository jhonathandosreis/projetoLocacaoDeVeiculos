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

            Object[] coluna = new Object[8];

            ArrayList<Motoristas> listaMotoristas = motoristabll.getAllMotoristas();

            for (Motoristas motorista : listaMotoristas) {

                coluna[0] = motorista.getIden();
                coluna[1] = motorista.getCliente().getNome();
                coluna[2] = motorista.getCliente().getTelefone();
                coluna[3] = motorista.getCliente().getEnderecos().getLogradouro();
                coluna[4] = motorista.getCpf();
                coluna[5] = motorista.getNumeroCnh();
                coluna[6] = motorista.getCliente().getEnderecos().getCep();
                coluna[7] = motorista.getCliente().getStatus();

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
        jTextFieldEmailMotorista.setText("" + motorista.getCliente().getEmail());
        jTextFieldCaminhoDoArquivo.setText(motorista.getFotos().getFot_caminho());
        exibirFotoGrid(motorista.getFotos().getFot_iden());
        
    }

    public void preencherComboboxCidades() throws Exception {
        try {
            jComboBox_Cidade.removeAllItems();
            ArrayList<Cidades> listaCidades = cidadesBll.getAllCidades();

            for (Cidades cidade : listaCidades) {
                jComboBox_Cidade.addItem(cidade.getIden()+"-"+cidade.getNome());
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

    public void ValidaMotoristas() { 
       
        if(jTextFieldCNHMotorista.getText().equals("           ")) throw new RuntimeException("Campo CNH deve ser preenchido");
        Valida.SomenteNumero(jTextFieldNumeroMotorista.getText(), "Campo número do endereço deve conter somente números!");
        Valida.campoVazio(jTextFieldCaminhoDoArquivo.getText(), "Selecione uma foto para avançar no cadastro!");
        if(jComboBox_CategoriaCnh.getSelectedItem().equals("<SELECIONE>"))throw new RuntimeException("Selecione uma categoria para cnh!");
        if(jFormattedTextField_Data_validade.getText().equals("  /  /    ")){
            throw new RuntimeException("Campo data de válidade da CNH deve ser preenchido");
        }
        
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
         jButtonCadastrar.setEnabled(true);
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
        jLabel12 = new javax.swing.JLabel();
        jTextFieldCaminhoDoArquivo = new javax.swing.JTextField();
        jButtonSelecionar = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        jComboBox_CategoriaCnh = new javax.swing.JComboBox<>();
        jTextFieldCNHMotorista = new javax.swing.JFormattedTextField();
        jLabelFotos = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTableConsultarMotorista = new javax.swing.JTable();
        jButtonRemover = new javax.swing.JButton();
        jButtonLimpar = new javax.swing.JButton();
        jButtonAlterar = new javax.swing.JButton();
        jButtonCadastrar = new javax.swing.JButton();
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
        jTextFieldCepMotorista = new javax.swing.JFormattedTextField();
        jTextField_uf = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jLabel4CPFPessoaFisica = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jTextFieldEmailMotorista = new javax.swing.JTextField();
        jTextFieldTelefoneMotorista = new javax.swing.JFormattedTextField();
        jLabelIcone_CNHgrid = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("[MOTORISTAS]");
        setMaximumSize(new java.awt.Dimension(1280, 720));
        setMinimumSize(new java.awt.Dimension(1280, 720));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2PessoaFisica.setBackground(new java.awt.Color(0, 0, 0));
        jPanel2PessoaFisica.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)), "Dados Gerais", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(255, 255, 255))); // NOI18N

        jLabelIdPessoaFisica.setForeground(new java.awt.Color(204, 204, 204));
        jLabelIdPessoaFisica.setText("ID");

        jTextFieldIDMotorista.setEditable(false);

        jLabelNomePessoaFisica.setForeground(new java.awt.Color(204, 204, 204));
        jLabelNomePessoaFisica.setText("NOME");

        jLabel3RGPessoaFisica.setForeground(new java.awt.Color(204, 204, 204));
        jLabel3RGPessoaFisica.setText("CNH");

        jLabel9.setForeground(new java.awt.Color(204, 204, 204));
        jLabel9.setText("RG");

        jLabel10.setForeground(new java.awt.Color(204, 204, 204));
        jLabel10.setText("CPF");

        jLabel6.setForeground(new java.awt.Color(204, 204, 204));
        jLabel6.setText("CAT. CNH");

        jLabel13.setForeground(new java.awt.Color(204, 204, 204));
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

        jLabel12.setForeground(new java.awt.Color(204, 204, 204));
        jLabel12.setText("CAMINHO DA FOTO");

        jTextFieldCaminhoDoArquivo.setEditable(false);

        jButtonSelecionar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/pi/icons/botao-de-login.png"))); // NOI18N
        jButtonSelecionar.setText("SELECIONE O ARQUIVO");
        jButtonSelecionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSelecionarActionPerformed(evt);
            }
        });

        jLabel16.setForeground(new java.awt.Color(204, 204, 204));
        jLabel16.setText("CNH");

        jComboBox_CategoriaCnh.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "<SELECIONE>", "AB", "B", "C", "D", "E" }));

        try {
            jTextFieldCNHMotorista.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###########")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        jLabelFotos.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        javax.swing.GroupLayout jPanel2PessoaFisicaLayout = new javax.swing.GroupLayout(jPanel2PessoaFisica);
        jPanel2PessoaFisica.setLayout(jPanel2PessoaFisicaLayout);
        jPanel2PessoaFisicaLayout.setHorizontalGroup(
            jPanel2PessoaFisicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2PessoaFisicaLayout.createSequentialGroup()
                .addGroup(jPanel2PessoaFisicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2PessoaFisicaLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jPanel2PessoaFisicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13)
                            .addComponent(jTextFieldNomeMotorista, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2PessoaFisicaLayout.createSequentialGroup()
                                .addGroup(jPanel2PessoaFisicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField_rgMotorista, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel9))
                                .addGroup(jPanel2PessoaFisicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2PessoaFisicaLayout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel10))
                                    .addGroup(jPanel2PessoaFisicaLayout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addComponent(jTextField_CpfMotorista, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(jLabelNomePessoaFisica)
                            .addComponent(jFormattedTextField_Data_validade, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2PessoaFisicaLayout.createSequentialGroup()
                                .addGroup(jPanel2PessoaFisicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextFieldCNHMotorista, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3RGPessoaFisica))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel2PessoaFisicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jComboBox_CategoriaCnh, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jTextFieldIDMotorista, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2PessoaFisicaLayout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jLabelIdPessoaFisica)))
                .addGroup(jPanel2PessoaFisicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2PessoaFisicaLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2PessoaFisicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2PessoaFisicaLayout.createSequentialGroup()
                                .addComponent(jTextFieldCaminhoDoArquivo, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2PessoaFisicaLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel16)
                                .addGap(140, 140, 140))
                            .addGroup(jPanel2PessoaFisicaLayout.createSequentialGroup()
                                .addGroup(jPanel2PessoaFisicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2PessoaFisicaLayout.createSequentialGroup()
                                        .addGap(54, 54, 54)
                                        .addComponent(jLabel12))
                                    .addComponent(jLabelFotos, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(32, Short.MAX_VALUE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2PessoaFisicaLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonSelecionar, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(59, 59, 59))))
        );
        jPanel2PessoaFisicaLayout.setVerticalGroup(
            jPanel2PessoaFisicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2PessoaFisicaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButtonSelecionar, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabelFotos, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldCaminhoDoArquivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28))
            .addGroup(jPanel2PessoaFisicaLayout.createSequentialGroup()
                .addComponent(jLabelIdPessoaFisica)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldIDMotorista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabelNomePessoaFisica)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldNomeMotorista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2PessoaFisicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2PessoaFisicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField_rgMotorista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField_CpfMotorista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2PessoaFisicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3RGPessoaFisica)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2PessoaFisicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldCNHMotorista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox_CategoriaCnh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jFormattedTextField_Data_validade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel2PessoaFisica, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, -1, 360));

        jTableConsultarMotorista.setBackground(new java.awt.Color(0, 0, 0));
        jTableConsultarMotorista.setForeground(new java.awt.Color(255, 255, 255));
        jTableConsultarMotorista.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "NOME", "TELEFONE", "LOGRADOURO", "CPF", "Nº CNH", "CEP", "STATUS"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableConsultarMotorista.setSelectionBackground(new java.awt.Color(255, 255, 255));
        jTableConsultarMotorista.setSelectionForeground(new java.awt.Color(0, 0, 0));
        jTableConsultarMotorista.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableConsultarMotoristaMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jTableConsultarMotorista);
        if (jTableConsultarMotorista.getColumnModel().getColumnCount() > 0) {
            jTableConsultarMotorista.getColumnModel().getColumn(0).setMaxWidth(50);
        }

        getContentPane().add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 400, 880, 210));

        jButtonRemover.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/pi/icons/lixo.png"))); // NOI18N
        jButtonRemover.setText("EXCLUIR");
        jButtonRemover.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRemoverActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonRemover, new org.netbeans.lib.awtextra.AbsoluteConstraints(1110, 620, 140, 50));

        jButtonLimpar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/pi/icons/limpar-limpo.png"))); // NOI18N
        jButtonLimpar.setText("LIMPAR");
        jButtonLimpar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLimparActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonLimpar, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 620, 140, 50));

        jButtonAlterar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/pi/icons/editar.png"))); // NOI18N
        jButtonAlterar.setText("ALTERAR");
        jButtonAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAlterarActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonAlterar, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 620, 140, 50));

        jButtonCadastrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/pi/icons/salve.png"))); // NOI18N
        jButtonCadastrar.setText("CADASTRAR");
        jButtonCadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCadastrarActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonCadastrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 620, 140, 50));

        jPanel3.setBackground(new java.awt.Color(0, 0, 0));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)), "Dados de Endereço", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(255, 255, 255))); // NOI18N

        jLabel2.setForeground(new java.awt.Color(204, 204, 204));
        jLabel2.setText("CEP");

        jLabel3.setForeground(new java.awt.Color(204, 204, 204));
        jLabel3.setText("LOGRADOURO");

        jLabel4.setForeground(new java.awt.Color(204, 204, 204));
        jLabel4.setText("N°");

        jTextFieldNumeroMotorista.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldNumeroMotoristaActionPerformed(evt);
            }
        });

        jLabel5.setForeground(new java.awt.Color(204, 204, 204));
        jLabel5.setText("COMPLEMENTO");

        jLabel7.setForeground(new java.awt.Color(204, 204, 204));
        jLabel7.setText("CIDADE");

        jLabel8.setForeground(new java.awt.Color(204, 204, 204));
        jLabel8.setText("UF");

        jComboBox_Cidade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_CidadeActionPerformed(evt);
            }
        });

        jLabel14.setForeground(new java.awt.Color(204, 204, 204));
        jLabel14.setText("RUA");

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
                                    .addComponent(jTextFieldCepMotorista, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextFieldLogradouroMotorista, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3))
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel4)
                                        .addGap(39, 39, 39))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTextFieldNumeroMotorista)))))
                        .addGap(182, 182, 182))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addGap(124, 124, 124)
                        .addComponent(jLabel7)
                        .addGap(241, 241, 241)
                        .addComponent(jLabel8)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jTextFieldRua, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jComboBox_Cidade, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField_uf, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addGap(6, 6, 6)
                .addComponent(jTextFieldComplementoMotorista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldRua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox_Cidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField_uf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(64, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 20, 532, 230));

        jPanel4.setBackground(new java.awt.Color(0, 0, 0));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)), "Dados para Contato", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(255, 255, 255))); // NOI18N

        jLabel4CPFPessoaFisica.setBackground(new java.awt.Color(255, 255, 255));
        jLabel4CPFPessoaFisica.setForeground(new java.awt.Color(204, 204, 204));
        jLabel4CPFPessoaFisica.setText("TELEFONE");

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setForeground(new java.awt.Color(204, 204, 204));
        jLabel1.setText("E-MAIL");

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
                .addContainerGap(14, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel4CPFPessoaFisica))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldEmailMotorista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldTelefoneMotorista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 268, -1, 110));

        jLabelIcone_CNHgrid.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        getContentPane().add(jLabelIcone_CNHgrid, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 400, 303, 206));

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/pi/image/fundo desfolque bmw_Easy-Resize.com.jpg"))); // NOI18N
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jTableConsultarMotoristaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableConsultarMotoristaMouseClicked
       try{
           jButtonCadastrar.setEnabled(false);
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
            fotos = fotoBll.getFotosById(motorista.getFotos().getFot_iden());

            motoristabll.deleteMotoristas(motorista);
            clienteBll.deleteClientes(cliente);
            enderecoBll.deleteEndereco(endereco);
            fotoBll.deleteFotos(fotos);
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

            int id = Integer.parseInt(jTableConsultarMotorista.getValueAt(jTableConsultarMotorista.getSelectedRow(), 0).toString());
            motorista = motoristabll.getMotoristaById(id);
            Fotos foto = fotoBll.getFotosById(motorista.getFotos().getFot_iden());
            foto.setFot_caminho(jTextFieldCaminhoDoArquivo.getText());
            fotoBll.updateFotos(foto);
            endereco = enderecoBll.getConsultaPorId(motorista.getCliente().getEnderecos().getIden());
            cliente = clienteBll.getClienteById(motorista.getCliente().getIden());
            cidade = cidadesBll.getCidadesById(SplitReturnID(jComboBox_Cidade.getSelectedItem().toString()));

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


            JOptionPane.showMessageDialog(rootPane, error.getMessage(), "Menssagem", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButtonAlterarActionPerformed

    private void jButtonCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCadastrarActionPerformed
        int chegou = 0;
        try {
            ValidaMotoristas();

            String NomeDoArquivo = f.getName();
            File destino = new File("C:\\Users\\Gustavo Gabriel\\Documents\\LocaçãoProjeto\\projetoLocacaoDeVeiculos\\ProjetoMulaCar\\Imagem_CNH" + NomeDoArquivo);

            destino = destino.getAbsoluteFile();

            Fotos foto = new Fotos();
            foto.setFot_caminho(destino.getAbsoluteFile().toString());
            fotoBll.addFotos(foto);
            if (!destino.exists()) {
                Files.copy(f.toPath(), destino.toPath());
            }
            
            foto = fotoBll.getFotosByCaminho(foto.getFot_caminho());
            
            Date data = formato.parse(jFormattedTextField_Data_validade.getText());

             cidade = cidadesBll.getCidadesById(SplitReturnID(jComboBox_Cidade.getSelectedItem().toString()));
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
              cidade = cidadesBll.getCidadesById(SplitReturnID(jComboBox_Cidade.getSelectedItem().toString()));
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
    private javax.swing.JLabel jLabel11;
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
    private javax.swing.JPanel jPanel2PessoaFisica;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane3;
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
    private javax.swing.JFormattedTextField jTextField_rgMotorista;
    private javax.swing.JTextField jTextField_uf;
    // End of variables declaration//GEN-END:variables
}
