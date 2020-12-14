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
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableVeiculo = new javax.swing.JTable();
        jButtonCadastrar = new javax.swing.JButton();
        jButtonAlterar = new javax.swing.JButton();
        jButtonLimpar = new javax.swing.JButton();
        jButtonRemover = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("[VEÍCULOS]");
        setMaximumSize(new java.awt.Dimension(1280, 720));
        setMinimumSize(new java.awt.Dimension(1280, 720));
        setPreferredSize(new java.awt.Dimension(1280, 720));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(0, 0, 0));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)), "Veículo", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Lucida Grande", 0, 13), new java.awt.Color(255, 255, 255))); // NOI18N

        jLabel1.setForeground(new java.awt.Color(204, 204, 204));
        jLabel1.setText("ID");

        jTextFieldIDVeiculo.setEditable(false);

        jLabel2.setForeground(new java.awt.Color(204, 204, 204));
        jLabel2.setText("PLACA");

        jLabel3.setForeground(new java.awt.Color(204, 204, 204));
        jLabel3.setText("RENAVAM");

        jLabel4.setForeground(new java.awt.Color(204, 204, 204));
        jLabel4.setText("ANO");

        jLabel5.setForeground(new java.awt.Color(204, 204, 204));
        jLabel5.setText("QUILOMETRAGEM");

        jLabel7.setForeground(new java.awt.Color(204, 204, 204));
        jLabel7.setText("VALOR DE COMPRA");

        jLabel8.setForeground(new java.awt.Color(204, 204, 204));
        jLabel8.setText("CATEGORIA");

        jLabel10.setForeground(new java.awt.Color(204, 204, 204));
        jLabel10.setText("MODELO");

        jLabel11.setForeground(new java.awt.Color(204, 204, 204));
        jLabel11.setText("MARCA");

        jLabel13.setForeground(new java.awt.Color(204, 204, 204));
        jLabel13.setText("CAPACIDADE");

        jLabel12.setForeground(new java.awt.Color(204, 204, 204));
        jLabel12.setText("OBSERVAÇÕES");

        jTextAreaObservacoes.setColumns(20);
        jTextAreaObservacoes.setRows(5);
        jScrollPane2.setViewportView(jTextAreaObservacoes);

        jLabel6.setForeground(new java.awt.Color(204, 204, 204));
        jLabel6.setText("TIPO DE VEÍCULO");

        jTextFieldTipoDoVeiculo.setEditable(false);

        jTextFieldCategoria.setEditable(false);

        jTextFieldMarca.setEditable(false);

        jComboBoxModelo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxModeloActionPerformed(evt);
            }
        });

        jLabel14.setForeground(new java.awt.Color(204, 204, 204));
        jLabel14.setText("TIPO DE COMBUSTÍVEL");

        buttonGroupPlacas.add(jRadioButtonNacional);
        jRadioButtonNacional.setForeground(new java.awt.Color(204, 204, 204));
        jRadioButtonNacional.setText("PLACA NACIONAL");
        jRadioButtonNacional.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonNacionalActionPerformed(evt);
            }
        });

        buttonGroupPlacas.add(jRadioButtonMercosul);
        jRadioButtonMercosul.setForeground(new java.awt.Color(204, 204, 204));
        jRadioButtonMercosul.setText("PLACA MERCOSUL");
        jRadioButtonMercosul.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonMercosulActionPerformed(evt);
            }
        });

        jLabel15.setForeground(new java.awt.Color(204, 204, 204));
        jLabel15.setText("CAPACIDADE DO TANQUE");

        jSeparator1.setBackground(new java.awt.Color(153, 153, 153));
        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(34, 34, 34)
                        .addComponent(jTextFieldRenavam, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(55, 55, 55)
                        .addComponent(jTextFieldPlaca, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(9, 9, 9)
                                .addComponent(jLabel1)
                                .addGap(29, 29, 29))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jTextFieldIDVeiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jRadioButtonNacional)
                            .addComponent(jRadioButtonMercosul)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(67, 67, 67))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel13)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldAno, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldQuantidadePassageiros, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(24, 24, 24)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14)
                    .addComponent(jLabel15))
                .addGap(6, 6, 6)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextFieldKM, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldValorDeCompra, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldTipoDeCombustivel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldCapacidadeTanque, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel8))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel11))
                    .addComponent(jComboBoxModelo, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldMarca, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldTipoDoVeiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jLabel10))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel6))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jRadioButtonNacional))
                        .addGap(2, 2, 2)
                        .addComponent(jTextFieldIDVeiculo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addComponent(jRadioButtonMercosul)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                        .addComponent(jLabel12)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(5, 5, 5)
                                .addComponent(jLabel2))
                            .addComponent(jTextFieldPlaca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(6, 6, 6)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(5, 5, 5)
                                .addComponent(jLabel3))
                            .addComponent(jTextFieldRenavam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(7, 7, 7)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jTextFieldAno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextFieldQuantidadePassageiros, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15)
                            .addComponent(jTextFieldCapacidadeTanque, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jLabel5)
                        .addGap(16, 16, 16)
                        .addComponent(jLabel7))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jTextFieldKM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(jTextFieldValorDeCompra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldTipoDeCombustivel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBoxModelo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldMarca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldTipoDoVeiculo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(16, 16, 16))
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, 1230, 290));

        jTableVeiculo.setBackground(new java.awt.Color(0, 0, 0));
        jTableVeiculo.setForeground(new java.awt.Color(255, 255, 255));
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
        jTableVeiculo.setSelectionBackground(new java.awt.Color(255, 255, 255));
        jTableVeiculo.setSelectionForeground(new java.awt.Color(0, 0, 0));
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

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 350, 1230, 230));

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
        getContentPane().add(jButtonRemover, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 600, 140, 50));

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/pi/image/fundo desfolque bmw_Easy-Resize.com.jpg"))); // NOI18N
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
        setLocationRelativeTo(null);
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
    private javax.swing.JLabel jLabel9;
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
