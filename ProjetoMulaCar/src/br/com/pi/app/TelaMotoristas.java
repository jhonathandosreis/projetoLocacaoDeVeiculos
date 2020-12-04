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
    private MotoristasBll motoristabll;
    private Clientes cliente;
    private ClientesBll clienteBll;
    private Enderecos endereco;
    private EnderecosBll enderecoBll;
    private Cidades cidade;
    private CidadesBll cidadesBll;
    private UfsBll ufbll;
    private Ufs uf;
    private FotosBll fotoBll;
    private ArrayList<Fotos> fotos = null;
    private int posicao = 0;
    
    JFileChooser chooser = new JFileChooser();
    File f, destino; 

    public TelaMotoristas() {
        initComponents();

        try {

            motorista = new Motoristas();
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
    
    private void buscarFotos(int iden_PessoaFisica) {
        try {
            fotos = fotoBll.getFotos(iden_PessoaFisica);
            posicao = 0;
            exibirFoto(posicao);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }
    
    public void exibirFoto(int posicao) throws Exception {
        
        if (fotos.size() > 0) {
            File f = new File(fotos.get(posicao).getFot_caminho());

            /* OPCIONAL - Código para definir o tamanho da imagem na tela */
            ImageIcon imageIcon = new ImageIcon(f.getPath()); // load the image to a imageIcon
            Image image = imageIcon.getImage(); // transform it 
            Image newimg = image.getScaledInstance(120, 120, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way 
            /* Fim do código do redimensionamento */

            ImageIcon icon = new ImageIcon(newimg);
            jLabelFotos.setIcon(icon);
        } else {
            jLabelFotos.setIcon(null);
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
        motorista = motoristabll.getMotoristaById(id);

        jTextFieldIDMotorista.setText("" + motorista.getIden());
        jTextFieldNomeMotorista.setText(motorista.getCliente().getNome());
        jTextField_rgMotorista.setText("" + motorista.getRg());
        jTextField_CpfMotorista.setText("" + motorista.getCpf());
        jTextFieldCNHMotorista.setText("" + motorista.getNumeroCnh());
        jFormattedTextField_Data_validade.setText(convertDate(motorista.getDataValidade()));
        jTextField_CategoriaCNH.setText(motorista.getCategoriaCnh());
        jTextFieldCepMotorista.setText("" + motorista.getCliente().getEnderecos().getCep());
        jTextFieldLogradouroMotorista.setText(motorista.getCliente().getEnderecos().getLogradouro());
        jTextFieldNumeroMotorista.setText("" + motorista.getCliente().getEnderecos().getNumero());
        jTextFieldComplementoMotorista.setText(motorista.getCliente().getEnderecos().getComplemento());
        jTextFieldRua.setText(motorista.getCliente().getEnderecos().getRua());
        jComboBox_Cidade.setSelectedItem(motorista.getCliente().getEnderecos().getCidade());
        jTextFieldTelefoneMotorista.setText("" + motorista.getCliente().getTelefone());
        jTextFieldEmailMotorista.setText("" + motorista.getCliente().getTelefone());

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
        Valida.campoVazio(jTextFieldNomeMotorista.getText(), "Campo nome vazio!");
        Valida.campoVazio(jTextField_rgMotorista.getText(), "Campo rg vazio!");
        Valida.campoVazio(jTextField_CpfMotorista.getText(), "Campo cpf vazio!");
        Valida.campoVazio(jTextFieldCNHMotorista.getText(), "Campo cnh vazio!");
        Valida.campoVazio(jFormattedTextField_Data_validade.getText(), "Campo data de validade vazio!");
        Valida.campoVazio(jTextField_CategoriaCNH.getText(), "Campo categoria cnh vazio!");
        Valida.campoVazio(jTextFieldCepMotorista.getText(), "Campo cep vazio!");
        Valida.campoVazio(jTextFieldLogradouroMotorista.getText(), "Campo logradouro vazio!");
        Valida.campoVazio(jTextFieldNumeroMotorista.getText(), "Campo numero do endereço vazio!");
        Valida.campoVazio(jTextFieldComplementoMotorista.getText(), "Campo complemento vazio!");
        Valida.campoVazio(jTextFieldRua.getText(), "Campo rua vazio!");
        Valida.campoVazio(jTextField_UF.getText(), "Campo uf vazio!");
        Valida.campoVazio(jTextFieldTelefoneMotorista.getText(), "Campo telefone vazio!");
        Valida.campoVazio(jTextFieldEmailMotorista.getText(), "Campo e-mail vazio!");
        Valida.notSpecialCharacters(jTextFieldNomeMotorista.getText(), "Campo nome não permite caracteres especiais!");
        Valida.notSpecialCharacters(jTextField_rgMotorista.getText(), "Campo rg não permite caracteres especiais!");
        Valida.notSpecialCharacters(jTextField_CpfMotorista.getText(), "Campo cpf não permite caracteres especiais!");
        Valida.notSpecialCharacters(jTextFieldCNHMotorista.getText(), "Campo cnh não permite caracteres especiais!");
        Valida.notSpecialCharacters(jFormattedTextField_Data_validade.getText(), "Campo data de validade não permite caracteres especiais!");
        Valida.notSpecialCharacters(jTextField_CategoriaCNH.getText(), "Campo categoria cnh não permite caracteres especiais!");
        Valida.notSpecialCharacters(jTextFieldCepMotorista.getText(), "Campo cep não permite caracteres especiais!");
        Valida.notSpecialCharacters(jTextFieldLogradouroMotorista.getText(), "Campo logradouro não permite caracteres especiais!");
        Valida.notSpecialCharacters(jTextFieldNumeroMotorista.getText(), "Campo numero do endereço não permite caracteres especiais!");
        Valida.notSpecialCharacters(jTextFieldComplementoMotorista.getText(), "Campo complemento não permite caracteres especiais!");
        Valida.notSpecialCharacters(jTextFieldRua.getText(), "Campo rua não permite caracteres especiais!");
        Valida.notSpecialCharacters(jTextField_UF.getText(), "Campo uf não permite caracteres especiais!");
        Valida.notSpecialCharacters(jTextFieldTelefoneMotorista.getText(), "Campo telefone não permite caracteres especiais!");
        Valida.notNumber(jTextFieldNomeMotorista.getText(), "Campo nome não permite números!");
      
    }

    public void limparCampos() {
        jTextFieldIDMotorista.setText("");
        jTextFieldNomeMotorista.setText("");
        jTextField_rgMotorista.setText("");
        jTextField_CpfMotorista.setText("");
        jTextFieldCNHMotorista.setText("");
        jFormattedTextField_Data_validade.setText("");
        jTextField_CategoriaCNH.setText("");
        jTextFieldCepMotorista.setText("");
        jTextFieldLogradouroMotorista.setText("");
        jTextFieldNumeroMotorista.setText("");
        jTextFieldComplementoMotorista.setText("");
        jTextFieldRua.setText("");
        jTextField_UF.setText("");
        jTextFieldTelefoneMotorista.setText("");
        jTextFieldEmailMotorista.setText("");
        jComboBox_Cidade.setSelectedIndex(0);

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
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jFormattedTextField_Data_validade = new javax.swing.JFormattedTextField();
        jTextField_CategoriaCNH = new javax.swing.JTextField();
        jTextField_rgMotorista = new javax.swing.JTextField();
        jTextField_CpfMotorista = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jTextFieldCaminhoDoArquivo = new javax.swing.JTextField();
        jButtonSalvar = new javax.swing.JButton();
        jLabelIcone = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jButtonSelecionar = new javax.swing.JButton();
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
        jLabel1 = new javax.swing.JLabel();
        jTextFieldEmailMotorista = new javax.swing.JTextField();
        jTextFieldTelefoneMotorista = new javax.swing.JTextField();
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
        jLabelFotos = new javax.swing.JLabel();
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

        jLabel9.setText("RG");

        jLabel10.setText("CPF");

        jLabel6.setText("CATEGORIA CNH");

        jLabel13.setText("DATA DE VALIDADE DA CNH");

        try {
            jFormattedTextField_Data_validade.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        jLabel12.setText("CAMINHO  DA FOTO");

        jButtonSalvar.setText("SALVAR");
        jButtonSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSalvarActionPerformed(evt);
            }
        });

        jLabel16.setText("FOTO");

        jButtonSelecionar.setText("SELECIONE O ARQUIVO");
        jButtonSelecionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSelecionarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2PessoaFisicaLayout = new javax.swing.GroupLayout(jPanel2PessoaFisica);
        jPanel2PessoaFisica.setLayout(jPanel2PessoaFisicaLayout);
        jPanel2PessoaFisicaLayout.setHorizontalGroup(
            jPanel2PessoaFisicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2PessoaFisicaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2PessoaFisicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2PessoaFisicaLayout.createSequentialGroup()
                        .addGroup(jPanel2PessoaFisicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3RGPessoaFisica)
                            .addComponent(jTextFieldCNHMotorista))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2PessoaFisicaLayout.createSequentialGroup()
                        .addGroup(jPanel2PessoaFisicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jFormattedTextField_Data_validade, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2PessoaFisicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel2PessoaFisicaLayout.createSequentialGroup()
                                .addComponent(jTextField_CategoriaCNH, javax.swing.GroupLayout.PREFERRED_SIZE, 303, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(300, 300, 300)))
                        .addGroup(jPanel2PessoaFisicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2PessoaFisicaLayout.createSequentialGroup()
                                .addGap(505, 505, 505)
                                .addComponent(jLabel16)
                                .addGap(57, 57, 57))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2PessoaFisicaLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2PessoaFisicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel12)
                                    .addComponent(jTextFieldCaminhoDoArquivo, javax.swing.GroupLayout.PREFERRED_SIZE, 408, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabelIcone, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())
                    .addGroup(jPanel2PessoaFisicaLayout.createSequentialGroup()
                        .addGroup(jPanel2PessoaFisicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelIdPessoaFisica)
                            .addComponent(jTextFieldIDMotorista, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2PessoaFisicaLayout.createSequentialGroup()
                        .addGroup(jPanel2PessoaFisicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelNomePessoaFisica)
                            .addComponent(jTextFieldNomeMotorista, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2PessoaFisicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(jTextField_rgMotorista, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(71, 71, 71)
                        .addGroup(jPanel2PessoaFisicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addGroup(jPanel2PessoaFisicaLayout.createSequentialGroup()
                                .addComponent(jTextField_CpfMotorista, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(53, 53, 53)
                                .addComponent(jButtonSelecionar)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonSalvar)
                        .addGap(223, 223, 223))))
        );
        jPanel2PessoaFisicaLayout.setVerticalGroup(
            jPanel2PessoaFisicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2PessoaFisicaLayout.createSequentialGroup()
                .addGroup(jPanel2PessoaFisicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2PessoaFisicaLayout.createSequentialGroup()
                        .addGroup(jPanel2PessoaFisicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2PessoaFisicaLayout.createSequentialGroup()
                                .addComponent(jLabelIdPessoaFisica)
                                .addGap(17, 17, 17)
                                .addComponent(jTextFieldIDMotorista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2PessoaFisicaLayout.createSequentialGroup()
                                .addGap(62, 62, 62)
                                .addGroup(jPanel2PessoaFisicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel2PessoaFisicaLayout.createSequentialGroup()
                                        .addComponent(jLabelNomePessoaFisica)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTextFieldNomeMotorista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel2PessoaFisicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jTextField_rgMotorista, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2PessoaFisicaLayout.createSequentialGroup()
                                            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addGap(26, 26, 26))
                                        .addGroup(jPanel2PessoaFisicaLayout.createSequentialGroup()
                                            .addComponent(jLabel10)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addGroup(jPanel2PessoaFisicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(jTextField_CpfMotorista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jButtonSelecionar, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                .addGap(18, 18, 18)
                                .addComponent(jLabel3RGPessoaFisica)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextFieldCNHMotorista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2PessoaFisicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2PessoaFisicaLayout.createSequentialGroup()
                                .addComponent(jLabel13)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jFormattedTextField_Data_validade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2PessoaFisicaLayout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField_CategoriaCNH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2PessoaFisicaLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel2PessoaFisicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2PessoaFisicaLayout.createSequentialGroup()
                                .addComponent(jLabel16)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabelIcone, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2PessoaFisicaLayout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextFieldCaminhoDoArquivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(26, 26, 26)
                                .addComponent(jButtonSalvar)
                                .addGap(193, 193, 193)))))
                .addGap(46, 46, 46))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Dados de Endereço"));

        jLabel2.setText("CEP");

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
                            .addComponent(jTextFieldCepMotorista, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
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

        jLabel1.setText("E-Mail");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4CPFPessoaFisica)
                    .addComponent(jTextFieldTelefoneMotorista, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
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
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextFieldEmailMotorista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldTelefoneMotorista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel4CPFPessoaFisica)
                        .addGap(34, 34, 34)))
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
        jButtonRemover.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRemoverActionPerformed(evt);
            }
        });

        jButtonLimpar.setText("LIMPAR");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jButtonCadastrar)
                .addGap(225, 225, 225)
                .addComponent(jButtonAlterar)
                .addGap(238, 238, 238)
                .addComponent(jButtonRemover)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2PessoaFisica, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonLimpar)
                        .addGap(43, 43, 43))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2PessoaFisica, javax.swing.GroupLayout.PREFERRED_SIZE, 343, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonLimpar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonCadastrar)
                    .addComponent(jButtonAlterar)
                    .addComponent(jButtonRemover))
                .addGap(30, 30, 30))
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
                        .addComponent(jLabelFotos, javax.swing.GroupLayout.PREFERRED_SIZE, 318, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                        .addComponent(jLabelFotos, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jButtonSetaEsquerda1, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jButtonSetaDireita1, javax.swing.GroupLayout.Alignment.TRAILING))
                            .addGap(97, 97, 97)))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(306, Short.MAX_VALUE))
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
        try {
            ValidaMotoristas();
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

            JOptionPane.showMessageDialog(null, motorista.getCliente().getNome() + " cadastrado com sucesso no sistema!");
            preencherGridMotorista();
            limparCampos();

        } catch (Exception error) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar motoristas " + error.getMessage());
        }
    }//GEN-LAST:event_jButtonCadastrarActionPerformed

    private void jComboBox_CidadeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_CidadeActionPerformed
        try {
            cidade = cidadesBll.getCidadeNome(jComboBox_Cidade.getSelectedItem().toString());
            jTextField_UF.setText(cidade.getUf().getNome());
        } catch (Exception error) {
            JOptionPane.showMessageDialog(null, "Erro na combo Cidades " + error.getMessage());
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
        try {
            if (jTableConsultarMotorista.getSelectedRow() == -1) {
                throw new Exception("Selecione um motorista na tabela para ser alterado!");
            }
            ValidaMotoristas();
            int id = Integer.parseInt(jTableConsultarMotorista.getValueAt(jTableConsultarMotorista.getSelectedRow(), 0).toString());
            motorista = motoristabll.getMotoristaById(id);
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

            JOptionPane.showMessageDialog(null, motorista.getCliente().getNome() + " alterado com sucesso no sistema!");
            preencherGridMotorista();
            limparCampos();

        } catch (Exception error) {
            JOptionPane.showMessageDialog(rootPane, error.getMessage(), "Menssagem", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButtonAlterarActionPerformed

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

    private void jButtonSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSalvarActionPerformed

        String NomeDoArquivo = f.getName();
        File destino = new File("\\home\\jhonlinux\\Imagens\\Papéis de parede\\" + NomeDoArquivo);

            destino = destino.getAbsoluteFile();

            try {
                Fotos foto = new Fotos();

                foto.setFot_caminho(destino.getAbsoluteFile().toString());
                

                foto.setPessoasFisicas(motorista);
                fotoBll.addFotos(foto);

                if (!destino.exists()) {
                    Files.copy(f.toPath(), destino.toPath());
                }
                JOptionPane.showMessageDialog(null, "Salvo com sucesso");

                this.dispose();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Deu erro aqui!" + e.getMessage());
            }
    }//GEN-LAST:event_jButtonSalvarActionPerformed

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
        Image newimg = image.getScaledInstance(120, 120, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        /* Fim do código do redimensionamento */

        ImageIcon icon = new ImageIcon(newimg);
        jLabelIcone.setIcon(icon);
        jTextFieldCaminhoDoArquivo.setEditable(false);
    }//GEN-LAST:event_jButtonSelecionarActionPerformed

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
    private javax.swing.JButton jButtonSalvar;
    private javax.swing.JButton jButtonSelecionar;
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
    private javax.swing.JLabel jLabelIcone;
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
    private javax.swing.JTextField jTextFieldCaminhoDoArquivo;
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
