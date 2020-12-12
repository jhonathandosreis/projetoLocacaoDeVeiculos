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
import br.com.pi.bll.FotosBll;
import br.com.pi.bll.LocacoesBll;
import br.com.pi.bll.MotoristasBll;
import br.com.pi.bll.PessoasFisicasBll;
import br.com.pi.bll.PessoasJuridicasBll;
import br.com.pi.bll.VeiculosBll;
import br.com.pi.model.Categorias;
import br.com.pi.model.Clientes;
import br.com.pi.model.Fotos;
import br.com.pi.model.Locacoes;
import br.com.pi.model.Motoristas;
import br.com.pi.model.PessoasFisicas;
import br.com.pi.model.PessoasJuridicas;
import br.com.pi.model.Veiculos;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import br.com.pi.util.*;
import java.awt.Image;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import javax.swing.ImageIcon;
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
    Fotos fotos;
    FotosBll fotoBll;

    public TelaLocacao() {
        initComponents();
        this.setExtendedState(MAXIMIZED_BOTH);
        try {
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
            fotos = new Fotos();
            fotoBll = new FotosBll();

            PreencherComboboxMotorista();
            preencherGridLocacoes();
           jTextFieldDataLocacao.setEnabled(false);

        } catch (Exception error) {
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

    public int DiferencaEntreDatas(Date data1, Date data2) throws Exception {
        Date hoje = new Date();
        Date formatada = formato.parse(convertDate(hoje));
        if (data1.compareTo(formatada) < 0) {
            throw new Exception("Data de locação inválida!");
        }
        if (data1.compareTo(data2) >= 0) {
            throw new Exception("Data de devolução inválida!");
        }

        long diferencaMS = data2.getTime() - data1.getTime();
        long diferencaSegundos = diferencaMS / 1000;
        long diferencaMinutos = diferencaSegundos / 60;
        long diferencaHoras = diferencaMinutos / 60;
        long diferencaDias = diferencaHoras / 24;
        int dias = (int) diferencaDias;
        return dias;
    }

    private void imprimirNaGridVeiculos(ArrayList<Veiculos> listaveiculos) {
        try {
            DefaultTableModel model = (DefaultTableModel) jTable_VEICULOS.getModel();
            model.setNumRows(0);

            for (Veiculos veiculo : listaveiculos) {
                if (veiculo.getStatus().equals("DISPONIVEL")) {
                    String[] coluna = new String[6];
                    coluna[0] = "" + veiculo.getIden();
                    coluna[1] = veiculo.getModelo().getCategoria().getNome();
                    coluna[2] = veiculo.getModelo().getTiposDeVeiculos().getNome();
                    coluna[3] = veiculo.getModelo().getNome();
                    coluna[4] = veiculo.getModelo().getMarcas().getNome();
                    coluna[5] = veiculo.getPlaca();

                    model.addRow(coluna);
                }

            }
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(rootPane, erro);
        }
    }

    public void preencherGridLocacoes() throws Exception {
        try {
            ArrayList<Locacoes> listaLocacoes = locacaoBll.getAllLocacoes();
            DefaultTableModel model = (DefaultTableModel) jTable_locacao.getModel();
            model.setNumRows(0);

            for (Locacoes locacao : listaLocacoes) {

                Object[] coluna = new Object[9];
                coluna[0] = locacao.getIden();

                if (locacao.getCliente().getTipo().equals("FISICA")) {
                    PessoasFisicas fisica = pessoaFisicaBll.getPessoasFisicasByCliente(locacao.getCliente().getIden());
                    coluna[1] = fisica.getCliente().getNome();
                    coluna[2] = fisica.getCpf();
                }
                if (locacao.getCliente().getTipo().equals("MOTORISTA")) {
                    Motoristas motorista = motoristabll.getMotoristaBycCliente(locacao.getCliente().getIden());
                    coluna[1] = motorista.getCliente().getNome();
                    coluna[2] = motorista.getCpf();
                }
                if (locacao.getCliente().getTipo().equals("JURIDICA")) {
                    PessoasJuridicas juridica = pessoaJuridicaBll.getPessoasJuridicasByCliente(locacao.getCliente().getIden());
                    coluna[1] = juridica.getRazaoSocial();
                    coluna[2] = juridica.getCnpj();
                }

                coluna[3] = locacao.getVeiculos().getModelo().getNome();
                coluna[4] = locacao.getVeiculos().getPlaca();
                coluna[5] = convertDate(locacao.getDataDeLocacao());
                coluna[6] = convertDate(locacao.getDataPrevistDeDevolucao());
                coluna[7] = locacao.getValorTotalPago();
                coluna[8] = locacao.getStatus();

                model.addRow(coluna);

            }
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(rootPane, erro);
        }
    }

    public void preencherFormularioLocacao() {
        try {

            int id = Integer.parseInt(jTable_locacao.getValueAt(jTable_locacao.getSelectedRow(), 0).toString());
            locacao = locacaoBll.getLocacoesById(id);
            jComboBox_Cliente_Locacao.removeAllItems();
            jComboBox_MotoristaLocacao.removeAllItems();

            if (locacao.getCliente().getTipo().equals("FISICA")) {
                PessoasFisicas fisica = pessoaFisicaBll.getPessoasFisicasByCliente(locacao.getCliente().getIden());
                jRadioButtonPFisica.setSelected(true);
                jComboBox_Cliente_Locacao.addItem(fisica.getIden() + "-" + fisica.getCliente().getNome());
            }
            if (locacao.getCliente().getTipo().equals("MOTORISTA")) {
                Motoristas motorista = motoristabll.getMotoristaBycCliente(locacao.getCliente().getIden());
                JradioButonMotodista.setSelected(true);
                jComboBox_Cliente_Locacao.addItem(motorista.getIden() + "-" + motorista.getCliente().getNome());
            }
            if (locacao.getCliente().getTipo().equals("JURIDICA")) {
                PessoasJuridicas juridica = pessoaJuridicaBll.getPessoasJuridicasByCliente(locacao.getCliente().getIden());
                jRadioButtonPJuridica.setSelected(true);
                jComboBox_Cliente_Locacao.addItem(juridica.getIden() + "-" + juridica.getRazaoSocial());
            }

            jComboBox_MotoristaLocacao.addItem(locacao.getMotoristas().getIden() + "-" + locacao.getMotoristas().getCliente().getNome());

            jTextFieldDataLocacao.setText(convertDate(locacao.getDataDeLocacao()));
            jTextFieldDataDevolucao.setText(convertDate(locacao.getDataPrevistDeDevolucao()));
            veiculo = locacao.getVeiculos();
            String saida = "MODELO: " + veiculo.getModelo().getNome();
            saida += "\nMARCA: " + veiculo.getModelo().getMarcas().getNome();
            saida += "\nPLACA: " + veiculo.getPlaca();
            saida += "\nKM ATUAL: " + veiculo.getQuilometragem();
            jTextArea_informacoesVeiculo.setText(saida);

        } catch (Exception erro) {
            JOptionPane.showMessageDialog(rootPane, erro);
        }
    }

    public void exibirFotoGrid(int iden_PessoaFisica) throws Exception {
        fotos = fotoBll.getFotosById(iden_PessoaFisica);
        File f = new File(fotos.getFot_caminho());

        /* OPCIONAL - Código para definir o tamanho da imagem na tela */
        ImageIcon imageIcon = new ImageIcon(f.getPath()); // load the image to a imageIcon
        Image image = imageIcon.getImage(); // transform it 
        Image newimg = image.getScaledInstance(246, 210, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way 
        /* Fim do código do redimensionamento */
        ImageIcon icon = new ImageIcon(newimg);
        jLabel_cnh.setIcon(icon);
    }

    public void PreencherComboboxMotorista() throws Exception {
        try {
            jComboBox_MotoristaLocacao.removeAllItems();
            jComboBox_MotoristaLocacao.addItem("<SELECIONE>");
            ArrayList<Motoristas> listaMotoristas = motoristabll.getAllMotoristas();
            for (Motoristas motorista : listaMotoristas) {
                if (motorista.getCliente().getStatus().equals("ADIMPLENTE")) {
                    jComboBox_MotoristaLocacao.addItem(motorista.getIden() + "-" + motorista.getCliente().getNome());
                }
            }
        } catch (Exception error) {
            JOptionPane.showMessageDialog(rootPane, error.getMessage(), "Menssagem", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void PreencherComboboxCliente() throws Exception {

        jComboBox_Cliente_Locacao.removeAllItems();
        try {
            if (jRadioButtonPFisica.isSelected()) {

                ArrayList<PessoasFisicas> listaPessoasFisicas = pessoaFisicaBll.getAllPessoasFisicas();
                jComboBox_MotoristaLocacao.setEnabled(true);
                // jComboBox_Cliente_Locacao.addItem("<SELECIONE>");
                for (PessoasFisicas pessoaFisica : listaPessoasFisicas) {
                    if (pessoaFisica.getCliente().getStatus().equals("ADIMPLENTE")) {
                        jComboBox_Cliente_Locacao.addItem(pessoaFisica.getIden() + "-" + pessoaFisica.getCliente().getNome());
                    }
                }
            }

            if (jRadioButtonPJuridica.isSelected()) {
                ArrayList<PessoasJuridicas> listaPessoasJuridicas = pessoaJuridicaBll.getAllPessoasJuridicas();
                jComboBox_MotoristaLocacao.setEnabled(true);
                //jComboBox_Cliente_Locacao.addItem("<SELECIONE>");
                for (PessoasJuridicas pessoJuridica : listaPessoasJuridicas) {
                    if (pessoJuridica.getCliente().getStatus().equals("ADIMPLENTE")) {
                        jComboBox_Cliente_Locacao.addItem(pessoJuridica.getIden() + "-" + pessoJuridica.getRazaoSocial());
                    }
                }
            }

            if (JradioButonMotodista.isSelected()) {
                ArrayList<Motoristas> listaMotoristas = motoristabll.getAllMotoristas();
                jComboBox_MotoristaLocacao.setEnabled(false);
                //jComboBox_Cliente_Locacao.addItem("<SELECIONE>");
                for (Motoristas motorista : listaMotoristas) {
                    if (motorista.getCliente().getStatus().equals("ADIMPLENTE")) {
                        jComboBox_Cliente_Locacao.addItem(motorista.getIden() + "-" + motorista.getCliente().getNome());
                    }
                }
            }
        } catch (Exception error) {
            JOptionPane.showMessageDialog(rootPane, error.getMessage(), "Menssagem", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void LimparTelaLocacao() {
        try {
            DefaultTableModel model = (DefaultTableModel) jTable_VEICULOS.getModel();
            model.setNumRows(0);
            jComboBox_Cliente_Locacao.removeAllItems();
            PreencherComboboxMotorista();
            jTextFieldDataLocacao.setText("");
            jTextFieldDataDevolucao.setText("");
            jTextFieldIDLocacao.setText("");
            jTextArea_informacoesVeiculo.setText("");
            buttonGroup1.clearSelection();
            jLabel_cnh.setIcon(null);
            JradioButonMotodista.setEnabled(true);
            jRadioButtonPFisica.setEnabled(true);
            jRadioButtonPJuridica.setEnabled(true);
            jComboBox_Cliente_Locacao.setEnabled(true);
            jComboBox_MotoristaLocacao.setEnabled(true);
            jButton__listarPorMarca.setEnabled(true);
            jButton_listarPorCategoria.setEnabled(true);
            jButton_listarPorModelo.setEnabled(true);
            jButton_listarPorTipo.setEnabled(true);
            jButtonCadastrar.setEnabled(true);
            jTextFieldDataDevolucao.setEnabled(true);
            jTextFieldDataLocacao.setEnabled(false);
          
            jTextArea_informacoesVeiculo.setEnabled(true);
        } catch (Exception error) {
            JOptionPane.showMessageDialog(rootPane, error.getMessage(), "Menssagem", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void respostaFinalizacao(String resposta) {
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
        jRadioButton_reserva = new javax.swing.JRadioButton();
        jButtonCadastrar = new javax.swing.JButton();
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
        jLabel5 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel_cnh = new javax.swing.JLabel();
        jButton_Limpar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("[LOCAÇÃO DO VEÍCULO]");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Locação"));

        jLabel1.setText("ID");

        jTextFieldIDLocacao.setEditable(false);

        jLabel2.setText("CLIENTE");

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

        jRadioButton_reserva.setText("RESERVA");
        jRadioButton_reserva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton_reservaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addGap(18, 18, 18)
                        .addComponent(jTextFieldDataDevolucao))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jTextFieldIDLocacao, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(29, 29, 29)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jRadioButtonPFisica)
                                        .addGap(18, 18, 18)
                                        .addComponent(jRadioButtonPJuridica)
                                        .addGap(18, 18, 18)
                                        .addComponent(JradioButonMotodista)
                                        .addGap(11, 11, 11))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jComboBox_MotoristaLocacao, javax.swing.GroupLayout.Alignment.TRAILING, 0, 274, Short.MAX_VALUE)
                                        .addComponent(jComboBox_Cliente_Locacao, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel1)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(18, 18, 18)
                                .addComponent(jTextFieldDataLocacao, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jRadioButton_reserva)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextFieldDataDevolucao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(137, 137, 137))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextFieldIDLocacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jRadioButtonPFisica)
                            .addComponent(jRadioButtonPJuridica)
                            .addComponent(JradioButonMotodista))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jComboBox_Cliente_Locacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jComboBox_MotoristaLocacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(21, 21, 21)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(jTextFieldDataLocacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jRadioButton_reserva))
                        .addGap(181, 181, 181))))
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(11, 6, -1, 265));

        jButtonCadastrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/pi/icons/salve.png"))); // NOI18N
        jButtonCadastrar.setText("CADASTRAR");
        jButtonCadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCadastrarActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonCadastrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 550, -1, 60));

        jButtonRemover.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/pi/icons/lixo.png"))); // NOI18N
        jButtonRemover.setText("REMOVER");
        jButtonRemover.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRemoverActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonRemover, new org.netbeans.lib.awtextra.AbsoluteConstraints(1090, 550, 120, 60));

        jButton_listarPorCategoria.setText("CATEGORIA");
        jButton_listarPorCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_listarPorCategoriaActionPerformed(evt);
            }
        });
        jPanel1.add(jButton_listarPorCategoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 30, -1, -1));

        jButton_listarPorTipo.setText("TIPO DE VEICULO");
        jButton_listarPorTipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_listarPorTipoActionPerformed(evt);
            }
        });
        jPanel1.add(jButton_listarPorTipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 30, -1, -1));

        jButton_listarPorModelo.setText("MODELO");
        jButton_listarPorModelo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_listarPorModeloActionPerformed(evt);
            }
        });
        jPanel1.add(jButton_listarPorModelo, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 30, -1, -1));

        jButton__listarPorMarca.setText("MARCA");
        jButton__listarPorMarca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton__listarPorMarcaActionPerformed(evt);
            }
        });
        jPanel1.add(jButton__listarPorMarca, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 30, -1, -1));

        jLabel6.setText("DADOS DO VEICULO SELECIONADO");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 30, -1, -1));

        jTextArea_informacoesVeiculo.setColumns(20);
        jTextArea_informacoesVeiculo.setRows(5);
        jScrollPane2.setViewportView(jTextArea_informacoesVeiculo);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 80, 330, 190));

        jTable_VEICULOS.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "CATEGORIA", "TIPO DE VEÍCULO", "MODELO", "MARCA", "PLACA"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable_VEICULOS.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable_VEICULOSMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jTable_VEICULOS);
        if (jTable_VEICULOS.getColumnModel().getColumnCount() > 0) {
            jTable_VEICULOS.getColumnModel().getColumn(0).setPreferredWidth(10);
            jTable_VEICULOS.getColumnModel().getColumn(2).setPreferredWidth(100);
        }

        jPanel1.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 80, 500, 190));

        jLabel8.setText("Filtrar tabela de veículos por:");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 0, -1, -1));

        jTable_locacao.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CÓDIGO", "LOCATARIO", "CPF/CNPJ", "VEÍCULO", "PLACA DO VEÍCULO", "DATA DA LOCAÇÃO", "DATA PREVISTA", "VALOR FINAL", "STATUS"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable_locacao.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable_locacaoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable_locacao);
        if (jTable_locacao.getColumnModel().getColumnCount() > 0) {
            jTable_locacao.getColumnModel().getColumn(0).setMaxWidth(60);
        }

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 311, 950, 300));

        jLabel5.setText("CNH MOTORISTA RESPONSÁVEL");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 290, 160, -1));

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel_cnh, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel_cnh, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 320, 260, 220));

        jButton_Limpar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/pi/icons/limpar-limpo.png"))); // NOI18N
        jButton_Limpar.setText("LIMPAR");
        jButton_Limpar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_LimparActionPerformed(evt);
            }
        });
        jPanel1.add(jButton_Limpar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1210, 550, 120, 60));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 11, 1350, 640));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCadastrarActionPerformed

        try {
            String tipoPessoa = "";
            if (jTable_VEICULOS.getSelectedRow() == -1) {
                throw new Exception("Selecione um veículo na tabela para ser locado!");
            }
            if (!JradioButonMotodista.isSelected()) {
                if (jComboBox_MotoristaLocacao.getSelectedIndex() == 0) {
                    throw new Exception("Para locar deve indicar quem será o motorista!");
                }
            }
            if (JradioButonMotodista.isSelected()) {
                int id = SplitReturnID(jComboBox_Cliente_Locacao.getSelectedItem().toString());
                motorista = motoristabll.getMotoristaById(id);
                cliente = motorista.getCliente();
                locacao.setCliente(cliente);
                locacao.setMotoristas(motorista);
                tipoPessoa = "F";
            } else {
                Motoristas motorista2 = motoristabll.getMotoristaById(SplitReturnID(jComboBox_MotoristaLocacao.getSelectedItem().toString()));
                locacao.setMotoristas(motorista2);

                if (jRadioButtonPFisica.isSelected()) {
                    int id = SplitReturnID(jComboBox_Cliente_Locacao.getSelectedItem().toString());
                    pessoaFisica = pessoaFisicaBll.getPessoasFisicasBy(id);
                    cliente = pessoaFisica.getCliente();
                    locacao.setCliente(cliente);
                    tipoPessoa = "F";
                }
                if (jRadioButtonPJuridica.isSelected()) {
                    int id = SplitReturnID(jComboBox_Cliente_Locacao.getSelectedItem().toString());
                    pessoaJuridica = pessoaJuridicaBll.getPessoasJuridicasBy(id);
                    cliente = pessoaJuridica.getCliente();
                    locacao.setCliente(cliente);
                    tipoPessoa = "J";
                }

            }

            int id = Integer.parseInt(jTable_VEICULOS.getValueAt(jTable_VEICULOS.getSelectedRow(), 0).toString());
            veiculo = veiculoBll.getVeiculosById(id);
            locacao.setVeiculos(veiculo);
           Date hoje = new Date();
           int dias = 0;
            Date dataPrevista = formato.parse(jTextFieldDataDevolucao.getText());
            if (!jRadioButton_reserva.isSelected()) {
                
                Date formatada = formato.parse(convertDate(hoje));
                locacao.setDataDeLocacao(hoje);
                dias = DiferencaEntreDatas(hoje, dataPrevista);
            }else{
                  Date formatada = formato.parse(convertDate(hoje));
                Date dataLocacao = formato.parse(jTextFieldDataLocacao.getText());
                if (dataLocacao.compareTo(formatada) <= 0) {
            throw new Exception("Data de reserva deva ser superior ao dia atual!");
        }
                locacao.setDataDeLocacao(dataLocacao);
                 dias = DiferencaEntreDatas(dataLocacao, dataPrevista);
            }
            
            locacao.setDataPrevistDeDevolucao(dataPrevista);
            locacao.setKmInicial(veiculo.getQuilometragem());
            // CALCULO
           
            double valorDiaria = veiculo.getModelo().getCategoria().getValorDiarioLocacao();
            double valorLocacao = valorDiaria * dias;
            double valorCaucao = (valorLocacao * 1.5);
            double valorSeguro = (valorLocacao * 0.009);
            double valorTotal = valorLocacao + valorCaucao + valorSeguro;

            locacao.setValorLocacao(valorLocacao);
            locacao.setValorCaucao(valorCaucao);
            locacao.setValorSeguro(valorSeguro);
            locacao.setValorTotalPago(valorTotal);
            locacao.setStatus("ATIVA");

            if (fimLocacao == null) {
                fimLocacao = new TelaFinalizarLocacao();
                fimLocacao.setLocationRelativeTo(null);
                fimLocacao.setVisible(true);
                fimLocacao.setResizable(false);
            } else {
                fimLocacao.setLocationRelativeTo(null);
                fimLocacao.setVisible(true);
                fimLocacao.setResizable(false);
            }
            fimLocacao.enviaLocacao(this, locacao, tipoPessoa);

        } catch (Exception error) {
            JOptionPane.showMessageDialog(rootPane, error.getMessage(), "Menssagem", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButtonCadastrarActionPerformed

    private void jButtonRemoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRemoverActionPerformed
        try {
            int id = Integer.parseInt(jTable_locacao.getValueAt(jTable_locacao.getSelectedRow(), 0).toString());
            locacao = locacaoBll.getLocacoesById(id);
            if (jTable_locacao.getSelectedRow() == -1) {
                throw new Exception("Selecione uma locação na tabela para ser removida!");
            }
            if (locacao.getStatus().equals("FINALIZADA")) {
                int x = JOptionPane.showConfirmDialog(null, "Voçê realmente deseja excluir esta locação?", "Excluir Locação", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (x == 0) {

                    cliente = locacao.getCliente();

                    cliente.setStatus("ADIMPLENTE");
                    clienteBll.updateClientes(cliente);

                    motorista = locacao.getMotoristas();
                    Clientes cliente2 = motorista.getCliente();
                    cliente2.setStatus("ADIMPLENTE");
                    clienteBll.updateClientes(cliente2);

                    veiculo = locacao.getVeiculos();
                    veiculo.setStatus("DISPONIVEL");
                    veiculoBll.updateVeiculos(veiculo);

                    locacaoBll.deleteLocacoes(locacao);

                    JOptionPane.showMessageDialog(null, "Locação excluida com sucesso\nstatus do cliente e do veículo foram atualizados!");

                } else {
                    JOptionPane.showMessageDialog(null, "Locação mantida");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Locação não pode ser excluida pois esta com status " + locacao.getStatus());
            }
        } catch (Exception error) {
            JOptionPane.showMessageDialog(rootPane, error.getMessage(), "Menssagem", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButtonRemoverActionPerformed

    private int SplitReturnID(String combo) {

        String[] split = combo.split("-");
        int id = Integer.parseInt(split[0]);
        return id;

    }

    private void jButton_listarPorCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_listarPorCategoriaActionPerformed
        try {
            TemplateOrdenadoPorCategoria objeto = new TemplateOrdenadoPorCategoria();
            imprimirNaGridVeiculos(objeto.OrdenarListaVeiculos());
        } catch (Exception error) {
            JOptionPane.showMessageDialog(rootPane, error.getMessage(), "Menssagem", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton_listarPorCategoriaActionPerformed

    private void jButton_listarPorTipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_listarPorTipoActionPerformed
        try {
            TemplateOrdenadoPorMarca objeto = new TemplateOrdenadoPorMarca();
            imprimirNaGridVeiculos(objeto.OrdenarListaVeiculos());
        } catch (Exception error) {
            JOptionPane.showMessageDialog(rootPane, error.getMessage(), "Menssagem", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton_listarPorTipoActionPerformed

    private void jButton_listarPorModeloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_listarPorModeloActionPerformed
        try {
            TemplateOrdenadoPorModelo objeto = new TemplateOrdenadoPorModelo();
            imprimirNaGridVeiculos(objeto.OrdenarListaVeiculos());
        } catch (Exception error) {
            JOptionPane.showMessageDialog(rootPane, error.getMessage(), "Menssagem", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton_listarPorModeloActionPerformed

    private void jButton__listarPorMarcaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton__listarPorMarcaActionPerformed
        try {
            TemplateOrdenadoPorMarca objeto = new TemplateOrdenadoPorMarca();
            imprimirNaGridVeiculos(objeto.OrdenarListaVeiculos());
        } catch (Exception error) {
            JOptionPane.showMessageDialog(rootPane, error.getMessage(), "Menssagem", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton__listarPorMarcaActionPerformed

    private void jTable_VEICULOSMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_VEICULOSMouseClicked
        try {
            int id = Integer.parseInt(jTable_VEICULOS.getValueAt(jTable_VEICULOS.getSelectedRow(), 0).toString());
            veiculo = veiculoBll.getVeiculosById(id);
            String saida = "MODELO: " + veiculo.getModelo().getNome();
            saida += "\nMARCA: " + veiculo.getModelo().getMarcas().getNome();
            saida += "\nPLACA: " + veiculo.getPlaca();
            saida += "\nKM ATUAL: " + veiculo.getQuilometragem();
            saida += "\nDIÁRIA DO VEÍCULO: " + veiculo.getModelo().getCategoria().getValorDiarioLocacao() + " R$";
            jTextArea_informacoesVeiculo.setText(saida);
        } catch (Exception error) {
            JOptionPane.showMessageDialog(rootPane, error.getMessage(), "Menssagem", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jTable_VEICULOSMouseClicked

    private void JradioButonMotodistaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JradioButonMotodistaActionPerformed
        try {
            PreencherComboboxCliente();
        } catch (Exception error) {
            JOptionPane.showMessageDialog(rootPane, error.getMessage(), "Menssagem", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_JradioButonMotodistaActionPerformed

    private void jRadioButtonPJuridicaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonPJuridicaActionPerformed
        try {
            PreencherComboboxCliente();
        } catch (Exception error) {
            JOptionPane.showMessageDialog(rootPane, error.getMessage(), "Menssagem", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jRadioButtonPJuridicaActionPerformed

    private void jRadioButtonPFisicaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonPFisicaActionPerformed
        try {
            PreencherComboboxCliente();
        } catch (Exception error) {
            JOptionPane.showMessageDialog(rootPane, error.getMessage(), "Menssagem", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jRadioButtonPFisicaActionPerformed

    private void jButton_LimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_LimparActionPerformed
        LimparTelaLocacao();
    }//GEN-LAST:event_jButton_LimparActionPerformed

    private void jTable_locacaoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_locacaoMouseClicked
        try {
            DefaultTableModel model = (DefaultTableModel) jTable_VEICULOS.getModel();
            model.setNumRows(0);
            jButtonCadastrar.setEnabled(false);
            JradioButonMotodista.setEnabled(false);
            jRadioButtonPFisica.setEnabled(false);
            jRadioButtonPJuridica.setEnabled(false);
            jButton__listarPorMarca.setEnabled(false);
            jButton_listarPorCategoria.setEnabled(false);
            jButton_listarPorModelo.setEnabled(false);
            jButton_listarPorTipo.setEnabled(false);
            jComboBox_Cliente_Locacao.setEnabled(false);
            jComboBox_MotoristaLocacao.setEnabled(false);
            jTextFieldDataDevolucao.setEnabled(false);
            jTextFieldDataLocacao.setEnabled(false);
            jTextArea_informacoesVeiculo.setEnabled(false);

            int id = Integer.parseInt(jTable_locacao.getValueAt(jTable_locacao.getSelectedRow(), 0).toString());
            locacao = locacaoBll.getLocacoesById(id);
            jTextFieldIDLocacao.setText("" + locacao.getIden());
            exibirFotoGrid(locacao.getMotoristas().getFotos().getFot_iden());
            preencherFormularioLocacao();
        } catch (Exception error) {
            JOptionPane.showMessageDialog(rootPane, error.getMessage(), "Menssagem", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jTable_locacaoMouseClicked

    private void jRadioButton_reservaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton_reservaActionPerformed
      jTextFieldDataLocacao.setEnabled(true);
    }//GEN-LAST:event_jRadioButton_reservaActionPerformed

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
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel_cnh;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JRadioButton jRadioButtonPFisica;
    private javax.swing.JRadioButton jRadioButtonPJuridica;
    private javax.swing.JRadioButton jRadioButton_reserva;
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
