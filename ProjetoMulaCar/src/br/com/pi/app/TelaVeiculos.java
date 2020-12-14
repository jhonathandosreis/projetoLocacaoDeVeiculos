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
        this.setExtendedState(MAXIMIZED_BOTH);
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

            Object[] linha = new Object[10];

            ArrayList<Veiculos> veiculos = new VeiculosBll().getAllVeiculos();

            for (Veiculos modelo1 : veiculos) {

                linha[0] = modelo1.getIden();
                linha[1] = modelo1.getPlaca();
                linha[2] = modelo1.getQuilometragem();
                linha[3] = modelo1.getRenavam();
                linha[4] = modelo1.getAnoFabricacao();
                linha[5] = modelo1.getModelo().getNome();
                linha[6] = modelo1.getModelo().getMarcas().getNome();
                linha[7] = modelo1.getModelo().getCategoria().getNome();
                linha[8] = modelo1.getModelo().getTiposDeVeiculos().getNome();
                linha[9] = modelo1.getStatus();
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
            veiculo = veiculosBll.getVeiculosById(id);

            jComboBoxModelo.setSelectedItem(veiculo.getModelo().getNome());
            jTextFieldTipoDoVeiculo.setText(veiculo.getModelo().getTiposDeVeiculos().getNome());
            jTextFieldMarca.setText(veiculo.getModelo().getMarcas().getNome());
            jTextFieldCategoria.setText(veiculo.getModelo().getCategoria().getNome());

            jTextFieldIDVeiculo.setText(id + "");
            jTextFieldPlaca.setText(veiculo.getPlaca());
            jTextFieldRenavam.setText(veiculo.getRenavam());
            jTextFieldAno.setText(veiculo.getAnoFabricacao() + "");
            jTextFieldKM.setText(veiculo.getQuilometragem() + "");
            jTextFieldValorDeCompra.setText(veiculo.getPrecoDeCompra() + "");
            jTextFieldQuantidadePassageiros.setText(veiculo.getCapacidade() + "");
            jTextFieldTipoDeCombustivel.setText(veiculo.getTipoDeCombustivel());
            jTextFieldCapacidadeTanque.setText(veiculo.getCapacidadeCombustivel() + "");
            jTextAreaObservacoes.setText(veiculo.getObservacoes());

            jTextFieldPlaca.setEnabled(true);

        } catch (Exception error) {
            JOptionPane.showMessageDialog(null, error.getMessage(), "Menssagem", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void limparCampos() {

        jTextFieldIDVeiculo.setText("");
        jTextFieldPlaca.setText("");
        jTextFieldPlaca.setEnabled(false);
        jTextFieldRenavam.setText("");
        jTextFieldAno.setText("");
        jTextFieldKM.setText("");
        jTextFieldValorDeCompra.setText("");
        jTextFieldQuantidadePassageiros.setText("");
        jTextFieldTipoDeCombustivel.setText("");
        jComboBoxModelo.setSelectedItem("");
        jTextFieldMarca.removeAll();
        jTextFieldTipoDoVeiculo.setText("");
        jTextFieldCategoria.setText("");
        jTextAreaObservacoes.setText("");
        jTextFieldCapacidadeTanque.setText("");
        buttonGroupPlacas.clearSelection();
        jComboBoxModelo.setSelectedIndex(0);
    }

    public void ValidaVeículo() throws Exception {

        if (jRadioButtonMercosul.isSelected()) {
            Valida.validarPlacaMercosul(jTextFieldPlaca.getText(), "Ex: Mercosul BRA2E19");
        }

        if (jRadioButtonNacional.isSelected()) {
            Valida.validaPlacaNacional(jTextFieldPlaca.getText(), "Ex: Nacional PHL-4508");
        }

        if (jTextFieldTipoDeCombustivel.getText() != "GASOLINA") {
            throw new Exception("Tipo de combustível invalido!\nVeículo não possui este tipo de combustível!");
        }

        Valida.campoVazio(jTextFieldPlaca.getText(), "Selecione o modelo da placa\nLogo em seguida digite a placa do veículo!");
        Valida.campoVazio(jTextFieldRenavam.getText(), "Digite o renavam do veículo!\n");
        Valida.campoVazio(jTextFieldAno.getText(), "Ditie o ano do veículo!\n");
        Valida.campoVazio(jTextFieldKM.getText(), "Digite a quilometragem do veículo!\n");
        Valida.campoVazio(jTextFieldValorDeCompra.getText(), "Digite o valor da compra do veículo!\n");
        Valida.campoVazio(jTextFieldQuantidadePassageiros.getText(), "Digite a quantidade de passageiros!\n");
        Valida.campoVazio(jTextFieldTipoDeCombustivel.getText(), "Digite o tipo do combustível!\n");
        Valida.campoVazio(jTextAreaObservacoes.getText(), "Digite os detalhes do veículo!\n");
        Valida.campoVazio(jTextFieldCapacidadeTanque.getText(), "Digite a capacidade do tanque do veículo!\n");

        Valida.notSpecialCharacters(jTextFieldRenavam.getText(), "Renavam do veículo não é permitido caracteres especiais!\n");
        Valida.notSpecialCharacters(jTextFieldAno.getText(), "Ano do veículo não é permitido caracteres especiais!\n");
        Valida.notSpecialCharacters(jTextFieldValorDeCompra.getText(), "Valor de compra do veículo não é permitido caracteres especiais!\n");
        Valida.notSpecialCharacters(jTextFieldQuantidadePassageiros.getText(), "Quantidade de passageiros do veículo não é permitido caracteres especiais!\n");
        Valida.notSpecialCharacters(jTextFieldTipoDeCombustivel.getText(), "Tipo do combustivel do veículo não é permitido caracteres especiais!\n");
        Valida.notSpecialCharacters(jTextAreaObservacoes.getText(), "O campo observações do veículo não é permitido caracteres especiais!\n");
        Valida.notSpecialCharacters(jTextFieldCapacidadeTanque.getText(), "O campo capacidade do tanque do veículo não é permitido caracteres especiais!\n");

        Valida.numberInteger(jTextFieldRenavam.getText(), "Campo renavam aceita somente números!\n");
        Valida.numberInteger(jTextFieldAno.getText(), "Campo ano aceita somente números!\n");
        Valida.numberInteger(jTextFieldValorDeCompra.getText(), "Campo valor de compra aceita somente números!\n");
        Valida.numberInteger(jTextFieldQuantidadePassageiros.getText(), "Campo capacidade aceita somente números!\n");

        Valida.notNumber(jTextFieldTipoDeCombustivel.getText(), "Campo tipo de combustivel não é permitido números!\n");
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
        jLabel15 = new javax.swing.JLabel();
        jTextFieldCapacidadeTanque = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jButtonCadastrar = new javax.swing.JButton();
        jButtonAlterar = new javax.swing.JButton();
        jButtonRemover = new javax.swing.JButton();
        jButtonLimpar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableVeiculo = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("[VEÍCULOS]");

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Veículo"));

        jLabel1.setText("ID");

        jTextFieldIDVeiculo.setEditable(false);

        jLabel2.setText("Placa");

        jLabel3.setText("Renavam");

        jLabel4.setText("Ano");

        jLabel5.setText("Quilometragem");

        jLabel7.setText("Valor de Compra");

        jLabel8.setText("Categoria");

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

        jLabel15.setText("Capacidade do Tanque");

        jSeparator1.setBackground(new java.awt.Color(153, 153, 153));
        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 557, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10))
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jTextFieldIDVeiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jRadioButtonNacional)
                                    .addComponent(jRadioButtonMercosul)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel4))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextFieldQuantidadePassageiros)
                                    .addComponent(jTextFieldRenavam, javax.swing.GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE)
                                    .addComponent(jTextFieldPlaca)
                                    .addComponent(jTextFieldAno))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel14))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextFieldValorDeCompra, javax.swing.GroupLayout.DEFAULT_SIZE, 215, Short.MAX_VALUE)
                                    .addComponent(jTextFieldKM)
                                    .addComponent(jTextFieldTipoDeCombustivel)
                                    .addComponent(jTextFieldCapacidadeTanque))))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 68, Short.MAX_VALUE)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(110, 110, 110)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(jComboBoxModelo, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(jTextFieldMarca, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(jTextFieldCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(jTextFieldTipoDoVeiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(68, 68, 68))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 362, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(9, 9, 9))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jTextFieldKM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel11)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jTextFieldMarca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel8)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jTextFieldCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jTextFieldValorDeCompra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(62, 62, 62)
                                        .addComponent(jTextFieldCapacidadeTanque, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(82, 82, 82)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel2)
                                            .addComponent(jTextFieldPlaca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel5))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel3)
                                            .addComponent(jTextFieldRenavam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel7)))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addComponent(jLabel1)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jTextFieldIDVeiculo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                                .addComponent(jRadioButtonNacional)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                    .addComponent(jRadioButtonMercosul)
                                                    .addComponent(jLabel10))))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jComboBoxModelo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(21, 21, 21)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextFieldAno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel4)
                                        .addComponent(jLabel14)
                                        .addComponent(jTextFieldTipoDeCombustivel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel13)
                                    .addComponent(jTextFieldQuantidadePassageiros, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel15))))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextFieldTipoDoVeiculo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(32, 32, 32)
                                .addComponent(jLabel12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane2)))))
                .addContainerGap())
        );

        jButtonCadastrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/pi/icons/salve.png"))); // NOI18N
        jButtonCadastrar.setText("CADASTRAR");
        jButtonCadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCadastrarActionPerformed(evt);
            }
        });

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

        jTableVeiculo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "PLACA", "KM", "RENAVAM", "ANO", "MODELO", "MARCA", "CATEGORIA", "TIPO DE VEICULO", "STATUS"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
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
            jTableVeiculo.getColumnModel().getColumn(0).setMinWidth(40);
            jTableVeiculo.getColumnModel().getColumn(0).setPreferredWidth(40);
            jTableVeiculo.getColumnModel().getColumn(0).setMaxWidth(40);
            jTableVeiculo.getColumnModel().getColumn(2).setMinWidth(80);
            jTableVeiculo.getColumnModel().getColumn(2).setPreferredWidth(80);
            jTableVeiculo.getColumnModel().getColumn(2).setMaxWidth(80);
            jTableVeiculo.getColumnModel().getColumn(4).setMinWidth(60);
            jTableVeiculo.getColumnModel().getColumn(4).setPreferredWidth(60);
            jTableVeiculo.getColumnModel().getColumn(4).setMaxWidth(60);
        }

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButtonCadastrar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonLimpar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonRemover)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1536, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jButtonCadastrar, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(jButtonLimpar, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonRemover, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

    private void jButtonAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAlterarActionPerformed
        try {
            if (jTableVeiculo.getSelectedRow() == -1) {
                throw new Exception("Selecione um veículo a ser alterado!");
            }

            ValidaVeículo();
            String nome = jComboBoxModelo.getSelectedItem().toString();
            modelo = modelosBll.getModeloByNome(nome);
            int id = Integer.parseInt(jTableVeiculo.getValueAt(jTableVeiculo.getSelectedRow(), 0).toString());
            veiculo = veiculosBll.getVeiculosById(id);

            veiculo.setModelo(modelo);
            veiculo.setIden(Integer.parseInt(jTextFieldIDVeiculo.getText()));
            veiculo.setPlaca(jTextFieldPlaca.getText());
            veiculo.setRenavam(jTextFieldRenavam.getText());
            veiculo.setAnoFabricacao(Integer.parseInt(jTextFieldAno.getText()));
            veiculo.setQuilometragem(Double.parseDouble(jTextFieldKM.getText()));
            veiculo.setPrecoDeCompra(Integer.parseInt(jTextFieldValorDeCompra.getText()));
            veiculo.setCapacidade(Integer.parseInt(jTextFieldQuantidadePassageiros.getText()));
            veiculo.setTipoDeCombustivel(jTextFieldTipoDeCombustivel.getText());
            veiculo.setCapacidadeCombustivel(Integer.parseInt(jTextFieldCapacidadeTanque.getText()));
            veiculo.setObservacoes(jTextAreaObservacoes.getText());
            veiculosBll.updateVeiculos(veiculo);
            preencherGridVeiculos();
            limparCampos();

            JOptionPane.showMessageDialog(null, "Veículo alterado com sucesso!");
        } catch (Exception error) {
            JOptionPane.showMessageDialog(null, error.getMessage(), "Menssagem", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButtonAlterarActionPerformed

    private void jButtonRemoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRemoverActionPerformed
        try {
            if (jTableVeiculo.getSelectedRow() == -1) {
                throw new Exception("Selecione um veículo a ser removido!");
            }

            veiculo.setIden(Integer.parseInt(jTextFieldIDVeiculo.getText()));
            veiculosBll.deleteVeiculos(veiculo);
            preencherGridVeiculos();
            limparCampos();

            JOptionPane.showMessageDialog(null, "Veículo removido com sucesso!");
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

    private void jRadioButtonMercosulActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonMercosulActionPerformed
        jTextFieldPlaca.setEnabled(true);
    }//GEN-LAST:event_jRadioButtonMercosulActionPerformed

    private void jRadioButtonNacionalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonNacionalActionPerformed
        jTextFieldPlaca.setEnabled(true);
    }//GEN-LAST:event_jRadioButtonNacionalActionPerformed

    private void jButtonCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCadastrarActionPerformed
        try {
            ValidaVeículo();
            String nome = jComboBoxModelo.getSelectedItem().toString();
            modelo = modelosBll.getModeloByNome(nome);
            veiculo.setModelo(modelo);

            veiculo.setPlaca(jTextFieldPlaca.getText());
            veiculo.setRenavam(jTextFieldRenavam.getText());
            veiculo.setAnoFabricacao(Integer.parseInt(jTextFieldAno.getText()));
            veiculo.setQuilometragem(Double.parseDouble(jTextFieldKM.getText()));
            veiculo.setPrecoDeCompra(Integer.parseInt(jTextFieldValorDeCompra.getText()));
            veiculo.setCapacidade(Integer.parseInt(jTextFieldQuantidadePassageiros.getText()));
            veiculo.setTipoDeCombustivel(jTextFieldTipoDeCombustivel.getText());
            veiculo.setCapacidadeCombustivel(Integer.parseInt(jTextFieldCapacidadeTanque.getText()));
            veiculo.setStatus("DISPONIVEL");
            veiculo.setObservacoes(jTextAreaObservacoes.getText());
            veiculosBll.addVeiculos(veiculo);
            preencherGridVeiculos();
            limparCampos();

            JOptionPane.showMessageDialog(null, "Veículo cadastrado com sucesso!");
        } catch (Exception error) {
            JOptionPane.showMessageDialog(null, error.getMessage(), "Menssagem", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButtonCadastrarActionPerformed

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
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JRadioButton jRadioButtonMercosul;
    private javax.swing.JRadioButton jRadioButtonNacional;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTableVeiculo;
    private javax.swing.JTextArea jTextAreaObservacoes;
    private javax.swing.JTextField jTextFieldAno;
    private javax.swing.JTextField jTextFieldCapacidadeTanque;
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
