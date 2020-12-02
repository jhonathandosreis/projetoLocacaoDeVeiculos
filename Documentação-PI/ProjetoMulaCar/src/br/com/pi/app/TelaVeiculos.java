/*
 *  -------------------------------------------------------------------------------------------------->
 *  Licença    : MIT - Copyright 2019 Jhonathan, Gustavo e Miguel 
 *  Criado em  : 20/11/2020 19:43:49 
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
import br.com.pi.bll.MarcasBll;
import br.com.pi.bll.ModelosBll;
import br.com.pi.bll.TiposDeVeiculosBll;
import br.com.pi.bll.VeiculosBll;
import br.com.pi.model.Modelos;
import br.com.pi.model.Veiculos;
import br.com.pi.util.Valida;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author jhonlinux
 */
public class TelaVeiculos extends javax.swing.JFrame {

    //--- BLL´S --------------------------------------------------------------------------------------->
    private VeiculosBll veiculosBll = null;
    private ModelosBll modelosBll = null;
    private CategoriasBll categoriasBll = null;
    private MarcasBll marcasBll = null;
    private TiposDeVeiculosBll tiposDeVeiculosBll = null;
    //--- FIM BLL'S ----------------------------------------------------------------------------------->
    //

    //--- CLASSES -------------------------------------------------------------------------------------> 
    private Veiculos veiculo = null;
    private Modelos modelo = null;
    //--- FIM CLASSES --------------------------------------------------------------------------------->
    //

    public TelaVeiculos() {
        initComponents();

        try {
            veiculosBll = new VeiculosBll();
            modelosBll = new ModelosBll();
            categoriasBll = new CategoriasBll();
            marcasBll = new MarcasBll();
            tiposDeVeiculosBll = new TiposDeVeiculosBll();
            veiculo = new Veiculos();
            modelo = new Modelos();

            preencherGridVeiculos();
            preencherCombobox();
            jTextFieldPlaca.setEnabled(false);
        } catch (Exception error) {
            JOptionPane.showMessageDialog(null, error.getMessage(), "Menssagem", JOptionPane.ERROR_MESSAGE);
        }
        this.setLocationRelativeTo(null);
    }

    //--- METODOS ------------------------------------------------------------------------------------->
    public void preencherGridVeiculos() throws Exception {

        try {
            DefaultTableModel tableVeiculos = (DefaultTableModel) jTableVeiculo.getModel();
            tableVeiculos.setRowCount(0);

            Object[] linha = new Object[14];

            ArrayList<Veiculos> veiculos = new VeiculosBll().getAllVeiculos();

            for (Veiculos modelo1 : veiculos) {

                linha[0] = modelo1.getIden();
                linha[1] = modelo1.getPlaca();
                linha[2] = modelo1.getQuilimetragem();
                linha[3] = modelo1.getRenavam();
                linha[4] = modelo1.getStatus();
                linha[5] = modelo1.getObservacoes();
                linha[6] = modelo1.getPrecoDeCompra();
                linha[7] = modelo1.getAnoFabricacao();
                linha[8] = modelo1.getCapacidade();
                linha[9] = modelo1.getTipoDeCombustivel();
                linha[10] = modelo1.getModelo().getNome();
                linha[11] = modelo1.getModelo().getMarcas().getNome();
                linha[12] = modelo1.getModelo().getCategoria().getNome();
                linha[13] = modelo1.getModelo().getTiposDeVeiculos().getNome();
                tableVeiculos.addRow(linha);
            }
        } catch (Exception error) {
            JOptionPane.showMessageDialog(null, error.getMessage(), "Menssagem", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void preencherCombobox() throws Exception {

        try {
            ArrayList<Modelos> lista = modelosBll.getAllModelos();
            jComboBoxModelo.removeAllItems();

            for (Modelos modelo1 : lista) {
                jComboBoxModelo.addItem(modelo1.getNome());
            }
        } catch (Exception error) {
            JOptionPane.showMessageDialog(null, error.getMessage(), "Menssagem", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void preencherFormularioVeiculo() throws Exception {

        try {

            int id = Integer.parseInt(jTableVeiculo.getValueAt(jTableVeiculo.getSelectedRow(), 0).toString());
            String placa = jTableVeiculo.getValueAt(jTableVeiculo.getSelectedRow(), 1).toString();
            int quilometragem = Integer.parseInt(jTableVeiculo.getValueAt(jTableVeiculo.getSelectedRow(), 2).toString());
            String renavam = jTableVeiculo.getValueAt(jTableVeiculo.getSelectedRow(), 3).toString();
            String status = jTableVeiculo.getValueAt(jTableVeiculo.getSelectedRow(), 4).toString();
            String observacoes = jTableVeiculo.getValueAt(jTableVeiculo.getSelectedRow(), 5).toString();
            int precoCompra = Integer.parseInt(jTableVeiculo.getValueAt(jTableVeiculo.getSelectedRow(), 6).toString());
            int anoFabricacao = Integer.parseInt(jTableVeiculo.getValueAt(jTableVeiculo.getSelectedRow(), 7).toString());
            int capacidade = Integer.parseInt(jTableVeiculo.getValueAt(jTableVeiculo.getSelectedRow(), 8).toString());
            String tipoDeCombustivel = jTableVeiculo.getValueAt(jTableVeiculo.getSelectedRow(), 9).toString();
            String marcacombobox = jTableVeiculo.getValueAt(jTableVeiculo.getSelectedRow(), 10).toString();
            String categoriacombobox = jTableVeiculo.getValueAt(jTableVeiculo.getSelectedRow(), 11).toString();
            String tiposDeVeiculoscombobox = jTableVeiculo.getValueAt(jTableVeiculo.getSelectedRow(), 12).toString();
            String modeloscombobox = jTableVeiculo.getValueAt(jTableVeiculo.getSelectedRow(), 13).toString();

            jComboBoxModelo.setSelectedItem(modelosBll.getModelosById(id).getNome());
            jTextFieldTipoDoVeiculo.setText(tiposDeVeiculosBll.getTiposDeVeiculosById(id).getNome());
            jTextFieldMarca.setText(marcasBll.getMarcasById(id).getNome());
            jTextFieldCategoria.setText(categoriasBll.getCategoriasById(id).getNome());

            jTextFieldIDVeiculo.setText(id + "");
            jTextFieldPlaca.setText(placa);
            jTextFieldRenavam.setText(renavam);
            jTextFieldAno.setText(anoFabricacao + "");
            jTextFieldKM.setText(quilometragem + "");
            jTextFieldValorDeCompra.setText(precoCompra + "");
            jTextFieldQuantidadePassageiros.setText(capacidade + "");
            jTextFieldTipoDeCombustivel.setText(tipoDeCombustivel);
            jComboBoxStatus.setSelectedItem(status);
            jTextAreaObservacoes.setText(observacoes);
            jTextFieldMarca.setText(marcacombobox);
            jTextFieldCategoria.setText(categoriacombobox);
            jTextFieldTipoDoVeiculo.setText(tiposDeVeiculoscombobox);
            jComboBoxModelo.setSelectedItem(modeloscombobox);

        } catch (Exception error) {
            JOptionPane.showMessageDialog(null, error.getMessage(), "Menssagem", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void limparCampos() {

        jTextFieldIDVeiculo.setText("");
        jTextFieldPlaca.setText("");
        jTextFieldRenavam.setText("");
        jTextFieldAno.setText("");
        jTextFieldKM.setText("");
        jTextFieldValorDeCompra.setText("");
        jTextFieldQuantidadePassageiros.setText("");
        jTextFieldTipoDeCombustivel.setText("");
        jComboBoxStatus.setSelectedItem("<SELECIONE>");
        jComboBoxModelo.setSelectedItem("");
        jTextFieldMarca.setText("");
        jTextFieldTipoDoVeiculo.setText("");
        jTextFieldCategoria.setText("");
        jTextAreaObservacoes.setText("");
    }
    
    public void ValidaVeículo() {
        if (jRadioButtonMercosul.isSelected()) {
            Valida.validarPlacaMercosul(jTextFieldPlaca.getText(), "Aceitamos somente modelo Mercusol");
        }
        
        if (jRadioButtonMercosul.isSelected()) {
            Valida.validaPlacaNacional(jTextFieldPlaca.getText(), "Aceitamos somente modelo Nacional");
        }
    }
    //--- FIM METODOS --------------------------------------------------------------------------------->
    //
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroupPlacas = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextFieldIDVeiculo = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jTextFieldPlaca = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTextFieldRenavam = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTextFieldAno = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTextFieldKM = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jTextFieldValorDeCompra = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jComboBoxStatus = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jTextFieldQuantidadePassageiros = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextAreaObservacoes = new javax.swing.JTextArea();
        jLabel6 = new javax.swing.JLabel();
        jTextFieldTipoDoVeiculo = new javax.swing.JTextField();
        jTextFieldCategoria = new javax.swing.JTextField();
        jTextFieldMarca = new javax.swing.JTextField();
        jComboBoxModelo = new javax.swing.JComboBox<>();
        jLabel14 = new javax.swing.JLabel();
        jTextFieldTipoDeCombustivel = new javax.swing.JTextField();
        jRadioButtonNacional = new javax.swing.JRadioButton();
        jRadioButtonMercosul = new javax.swing.JRadioButton();
        jButtonCadastrar = new javax.swing.JButton();
        jButtonAlterar = new javax.swing.JButton();
        jButtonRemover = new javax.swing.JButton();
        jButtonLimpar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableVeiculo = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("[VEÍCULOS]");

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Veículo"));

        jLabel1.setText("ID");

        jTextFieldIDVeiculo.setEditable(false);

        jLabel2.setText("Placa");

        jLabel3.setText("Renavam");

        jLabel4.setText("Ano");

        jLabel5.setText("Km");

        jLabel7.setText("Valor de Compra");

        jLabel8.setText("Categoria");

        jComboBoxStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "<SELECIONE>", "ATIVO", "INATIVO" }));

        jLabel9.setText("Status");

        jLabel10.setText("Modelo");

        jLabel11.setText("Marca");

        jLabel13.setText("Capacidade");

        jLabel12.setText("Observações");

        jTextAreaObservacoes.setColumns(20);
        jTextAreaObservacoes.setRows(5);
        jScrollPane2.setViewportView(jTextAreaObservacoes);

        jLabel6.setText("Tipo do Véiculo");

        jTextFieldTipoDoVeiculo.setEditable(false);

        jTextFieldCategoria.setEditable(false);

        jTextFieldMarca.setEditable(false);
        jTextFieldMarca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldMarcaActionPerformed(evt);
            }
        });

        jComboBoxModelo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxModeloActionPerformed(evt);
            }
        });

        jLabel14.setText("Tipo de Combustível");

        buttonGroupPlacas.add(jRadioButtonNacional);
        jRadioButtonNacional.setText("Placa Nacional ");
        jRadioButtonNacional.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonNacionalActionPerformed(evt);
            }
        });

        buttonGroupPlacas.add(jRadioButtonMercosul);
        jRadioButtonMercosul.setText("Placa Mercosul");
        jRadioButtonMercosul.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonMercosulActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBoxModelo, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10))
                        .addGap(34, 34, 34)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(jLabel6))
                            .addComponent(jTextFieldTipoDoVeiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jTextFieldIDVeiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(38, 38, 38)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jRadioButtonNacional)
                            .addComponent(jRadioButtonMercosul))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 130, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jTextFieldPlaca, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(50, 50, 50)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jTextFieldRenavam, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jTextFieldAno, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jTextFieldKM, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTextFieldValorDeCompra, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                            .addComponent(jTextFieldQuantidadePassageiros, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextFieldTipoDeCombustivel)
                            .addComponent(jLabel14))
                        .addGap(28, 28, 28))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(135, 135, 135)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jTextFieldCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addComponent(jTextFieldMarca, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(33, 33, 33)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBoxStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9))
                        .addGap(71, 71, 71)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(87, 87, 87))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel1)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jTextFieldIDVeiculo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel2)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jTextFieldPlaca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel3)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jTextFieldRenavam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabel14)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(27, 27, 27)
                                .addComponent(jTextFieldTipoDeCombustivel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel7)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jTextFieldValorDeCompra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel13)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jTextFieldQuantidadePassageiros, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextFieldKM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextFieldAno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(9, 9, 9)
                                .addComponent(jRadioButtonNacional)))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel9)
                                    .addGap(35, 35, 35))
                                .addComponent(jComboBoxStatus, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jRadioButtonMercosul)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(16, 16, 16)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addComponent(jLabel10)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jComboBoxModelo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addComponent(jLabel8)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jTextFieldCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addComponent(jLabel11)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jTextFieldMarca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                            .addGap(27, 27, 27)
                                            .addComponent(jTextFieldTipoDoVeiculo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING)))))))
                .addContainerGap(11, Short.MAX_VALUE))
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
        jButtonLimpar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLimparActionPerformed(evt);
            }
        });

        jTableVeiculo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "PLACA", "KM", "RENAVAM", "STATUS", "OBSERVAÇÕES", "VALOR DE COMPRA", "ANO", "CAPACIDADE", "TIPO DE COMBUSTIVEL", "MODELO", "MARCA", "CATEGORIA", "TIPO DE VEICULO"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableVeiculo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableVeiculoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTableVeiculo);
        if (jTableVeiculo.getColumnModel().getColumnCount() > 0) {
            jTableVeiculo.getColumnModel().getColumn(0).setPreferredWidth(40);
            jTableVeiculo.getColumnModel().getColumn(1).setPreferredWidth(100);
            jTableVeiculo.getColumnModel().getColumn(2).setMinWidth(80);
            jTableVeiculo.getColumnModel().getColumn(2).setPreferredWidth(80);
            jTableVeiculo.getColumnModel().getColumn(2).setMaxWidth(80);
            jTableVeiculo.getColumnModel().getColumn(3).setPreferredWidth(140);
            jTableVeiculo.getColumnModel().getColumn(4).setPreferredWidth(80);
            jTableVeiculo.getColumnModel().getColumn(5).setPreferredWidth(150);
            jTableVeiculo.getColumnModel().getColumn(6).setPreferredWidth(200);
            jTableVeiculo.getColumnModel().getColumn(7).setPreferredWidth(100);
            jTableVeiculo.getColumnModel().getColumn(8).setPreferredWidth(130);
            jTableVeiculo.getColumnModel().getColumn(9).setMinWidth(160);
            jTableVeiculo.getColumnModel().getColumn(9).setPreferredWidth(160);
            jTableVeiculo.getColumnModel().getColumn(9).setMaxWidth(160);
            jTableVeiculo.getColumnModel().getColumn(10).setPreferredWidth(130);
            jTableVeiculo.getColumnModel().getColumn(11).setPreferredWidth(150);
            jTableVeiculo.getColumnModel().getColumn(12).setMinWidth(100);
            jTableVeiculo.getColumnModel().getColumn(12).setPreferredWidth(100);
            jTableVeiculo.getColumnModel().getColumn(12).setMaxWidth(100);
            jTableVeiculo.getColumnModel().getColumn(13).setPreferredWidth(170);
        }

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(jButtonCadastrar)
                        .addGap(255, 255, 255)
                        .addComponent(jButtonAlterar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonRemover)
                        .addGap(209, 209, 209)
                        .addComponent(jButtonLimpar)
                        .addGap(45, 45, 45))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane1))
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonCadastrar)
                    .addComponent(jButtonAlterar)
                    .addComponent(jButtonRemover)
                    .addComponent(jButtonLimpar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 516, Short.MAX_VALUE)
                .addContainerGap())
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
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCadastrarActionPerformed

        try {
            ValidaVeículo();
            String nome = jComboBoxModelo.getSelectedItem().toString();
            modelo = modelosBll.getModeloByNome(nome);
            veiculo.setModelo(modelo);
            
            veiculo.setPlaca(jTextFieldPlaca.getText());
            veiculo.setRenavam(jTextFieldRenavam.getText());
            veiculo.setAnoFabricacao(Integer.parseInt(jTextFieldAno.getText()));
            veiculo.setQuilimetragem(Integer.parseInt(jTextFieldKM.getText()));
            veiculo.setPrecoDeCompra(Integer.parseInt(jTextFieldValorDeCompra.getText()));
            veiculo.setCapacidade(Integer.parseInt(jTextFieldQuantidadePassageiros.getText()));
            veiculo.setTipoDeCombustivel(jTextFieldTipoDeCombustivel.getText());
            veiculo.setStatus(jComboBoxStatus.getSelectedItem().toString());
            veiculo.setObservacoes(jTextAreaObservacoes.getText());
            veiculosBll.addVeiculos(veiculo);
            preencherGridVeiculos();
            limparCampos();
        } catch (Exception error) {
            JOptionPane.showMessageDialog(null, error.getMessage(), "Menssagem", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButtonCadastrarActionPerformed

    private void jButtonAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAlterarActionPerformed

        try {
            String nome = jComboBoxModelo.getSelectedItem().toString();
            modelo = modelosBll.getModeloByNome(nome);
            veiculo.setModelo(modelo);

            veiculo.setIden(Integer.parseInt(jTextFieldIDVeiculo.getText()));
            veiculo.setPlaca(jTextFieldPlaca.getText());
            veiculo.setRenavam(jTextFieldRenavam.getText());
            veiculo.setAnoFabricacao(Integer.parseInt(jTextFieldAno.getText()));
            veiculo.setQuilimetragem(Integer.parseInt(jTextFieldKM.getText()));
            veiculo.setPrecoDeCompra(Integer.parseInt(jTextFieldValorDeCompra.getText()));
            veiculo.setCapacidade(Integer.parseInt(jTextFieldQuantidadePassageiros.getText()));
            veiculo.setTipoDeCombustivel(jTextFieldTipoDeCombustivel.getText());
            veiculo.setStatus(jComboBoxStatus.getSelectedItem().toString());
            veiculo.setObservacoes(jTextAreaObservacoes.getText());
            veiculosBll.updateVeiculos(veiculo);
            preencherGridVeiculos();
            limparCampos();
        } catch (Exception error) {
            JOptionPane.showMessageDialog(null, error.getMessage(), "Menssagem", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButtonAlterarActionPerformed

    private void jButtonRemoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRemoverActionPerformed

        try {
            veiculo.setIden(Integer.parseInt(jTextFieldIDVeiculo.getText()));
            veiculosBll.deleteVeiculos(veiculo);
            preencherGridVeiculos();
            limparCampos();
        } catch (Exception error) {
            JOptionPane.showMessageDialog(null, error.getMessage(), "Menssagem", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButtonRemoverActionPerformed

    private void jTableVeiculoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableVeiculoMouseClicked

        try {
            preencherFormularioVeiculo();
        } catch (Exception error) {
            JOptionPane.showMessageDialog(null, error.getMessage(), "Menssagem", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jTableVeiculoMouseClicked

    private void jButtonLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLimparActionPerformed
        limparCampos();
    }//GEN-LAST:event_jButtonLimparActionPerformed

    private void jComboBoxModeloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxModeloActionPerformed

        try {
            modelo = modelosBll.getModeloByNome(jComboBoxModelo.getSelectedItem().toString());
            jTextFieldMarca.setText(modelo.getMarcas().getNome());
            jTextFieldCategoria.setText(modelo.getCategoria().getNome());
            jTextFieldTipoDoVeiculo.setText(modelo.getTiposDeVeiculos().getNome());
        } catch (Exception error) {
            JOptionPane.showMessageDialog(null, error.getMessage(), "Menssagem", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jComboBoxModeloActionPerformed

    private void jTextFieldMarcaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldMarcaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldMarcaActionPerformed

    private void jRadioButtonNacionalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonNacionalActionPerformed
        jTextFieldPlaca.setEnabled(rootPaneCheckingEnabled);
    }//GEN-LAST:event_jRadioButtonNacionalActionPerformed

    private void jRadioButtonMercosulActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonMercosulActionPerformed
       jTextFieldPlaca.setEnabled(true);
    }//GEN-LAST:event_jRadioButtonMercosulActionPerformed

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
            java.util.logging.Logger.getLogger(TelaVeiculos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaVeiculos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaVeiculos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaVeiculos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaVeiculos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroupPlacas;
    private javax.swing.JButton jButtonAlterar;
    private javax.swing.JButton jButtonCadastrar;
    private javax.swing.JButton jButtonLimpar;
    private javax.swing.JButton jButtonRemover;
    private javax.swing.JComboBox<String> jComboBoxModelo;
    private javax.swing.JComboBox<String> jComboBoxStatus;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JRadioButton jRadioButtonMercosul;
    private javax.swing.JRadioButton jRadioButtonNacional;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTableVeiculo;
    private javax.swing.JTextArea jTextAreaObservacoes;
    private javax.swing.JTextField jTextFieldAno;
    private javax.swing.JTextField jTextFieldCategoria;
    private javax.swing.JTextField jTextFieldIDVeiculo;
    private javax.swing.JTextField jTextFieldKM;
    private javax.swing.JTextField jTextFieldMarca;
    private javax.swing.JTextField jTextFieldPlaca;
    private javax.swing.JTextField jTextFieldQuantidadePassageiros;
    private javax.swing.JTextField jTextFieldRenavam;
    private javax.swing.JTextField jTextFieldTipoDeCombustivel;
    private javax.swing.JTextField jTextFieldTipoDoVeiculo;
    private javax.swing.JTextField jTextFieldValorDeCompra;
    // End of variables declaration//GEN-END:variables
}
