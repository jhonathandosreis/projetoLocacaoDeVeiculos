/*
 *  -------------------------------------------------------------------------------------------------->
 *  Licença    : MIT - Copyright 2019 Jhonathan, Gustavo e Miguel 
 *  Criado em  : 20/11/2020 23:49:34 
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

import br.com.pi.bll.ClientesBll;
import br.com.pi.bll.DevolucoesBll;
import br.com.pi.bll.LocacoesBll;
import br.com.pi.bll.VeiculosBll;
import br.com.pi.model.Clientes;
import br.com.pi.model.Devolucoes;
import br.com.pi.model.Locacoes;
import br.com.pi.model.Motoristas;
import br.com.pi.model.Veiculos;
import br.com.pi.util.Valida;
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
public class TelaDevolucoes extends javax.swing.JFrame {

    SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy", new Locale("pt", "BR"));
    //--- BLL´S --------------------------------------------------------------------------------------->
    private DevolucoesBll devolucaoBll = null;
    private LocacoesBll locacaoBll = null;
    private VeiculosBll veiculoBll = null;
    private ClientesBll clienteBll = null;
    //--- FIM BLL'S ----------------------------------------------------------------------------------->
    //
    //--- CLASSES -------------------------------------------------------------------------------------> 
    private Devolucoes devolucao = null;
    private Locacoes locacao = null;
    private Veiculos veiculo = null;
    private Clientes cliente = null;
    private Motoristas motorista = null;

    //--- FIM CLASSES --------------------------------------------------------------------------------->
    //
    public TelaDevolucoes() {
        initComponents();
        this.setExtendedState(MAXIMIZED_BOTH);
        try {
            //classes
            devolucao = new Devolucoes();
            locacao = new Locacoes();
            veiculo = new Veiculos();
            cliente = new Clientes();
            motorista = new Motoristas();

            devolucaoBll = new DevolucoesBll();
            locacaoBll = new LocacoesBll();
            veiculoBll = new VeiculosBll();
            clienteBll = new ClientesBll();

            preencherComboboxLocacoes();
            preencherGridDevolucoes();

            jTextFieldIDDevolucao.setEnabled(false);
            jButtonDevolver.setEnabled(false);

        } catch (Exception error) {
            JOptionPane.showMessageDialog(null, error.getMessage(), "Menssagem", JOptionPane.ERROR_MESSAGE);
        }
        this.setLocationRelativeTo(null);
    }

    //--- METODOS ------------------------------------------------------------------------------------->
    public void preencherGridDevolucoes() throws Exception {

        try {
            DefaultTableModel tableDevolucao = (DefaultTableModel) jTableDevolucao.getModel();
            tableDevolucao.setRowCount(0);

            Object[] linha = new Object[9];

            ArrayList<Devolucoes> devolucao = new DevolucoesBll().getAllDevolucao();

            for (Devolucoes devolucoes : devolucao) {

                linha[0] = devolucoes.getIden();
                linha[1] = devolucoes.getLocacao().getIden();
                linha[2] = devolucoes.getDataDevolucao();
                linha[3] = devolucoes.getDiferencaDias();
                linha[4] = devolucoes.getMultaPorAtraso();
                linha[5] = devolucoes.getDiferencaLitros();
                linha[6] = devolucoes.getMultaPorLitro();
                linha[7] = devolucoes.getValorTotal();
                linha[8] = devolucoes.getLocacao().getStatus();

                tableDevolucao.addRow(linha);
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

    public void preencherFormularioDevolucao() throws Exception {

        try {

            int id = Integer.parseInt(jTableDevolucao.getValueAt(jTableDevolucao.getSelectedRow(), 0).toString());
            devolucao = devolucaoBll.getDevolucaoById(id);

            locacao = locacaoBll.getLocacoesById(devolucao.getLocacao().getIden());
            jFormattedTextFieldDataPreLocacao.setText(convertDate(locacao.getDataPrevistDeDevolucao()));
            jTextFieldKMInicial.setText(locacao.getKmInicial() + "");
            jTextFieldCapacidadeLitroVeiculo.setText(locacao.getVeiculos().getCapacidadeCombustivel() + "");

            jTextFieldIDDevolucao.setText("" + devolucao.getIden());
            jFormattedDataDevolucao.setText(convertDate(devolucao.getDataDevolucao()));
            jTextField_DanosVeiculo.setText("" + devolucao.getDanoVeiculo());
            jTextFieldMultaTransito.setText("" + devolucao.getMultaTransito());
            jTextFieldMultaPorAtraso.setText("" + devolucao.getMultaPorAtraso());
            jTextFieldMultaporCombustivel.setText("" + devolucao.getMultaPorLitro());
            jTextFieldDiasAtraso.setText("" + devolucao.getDiferencaDias());
            jTextFieldDiferecaCombustivel.setText("" + devolucao.getDiferencaLitros());
            jTextFieldMultaTotal.setText("" + devolucao.getValorTotal());
            jTextField_Caucao.setText("" + devolucao.getLocacao().getValorCaucao());
            double troco = 0;
            double debito = 0;
            if (devolucao.getValorTotal() <= locacao.getValorCaucao()) {
                troco = locacao.getValorCaucao() - devolucao.getValorTotal();
                debito = 0;
            } else {
                debito = devolucao.getValorTotal() - locacao.getValorCaucao();
                troco = 0;
            }
            jTextField_Troco.setText("" + troco);
            jTextField_Debito.setText("" + debito);

            double litroEntregue = locacao.getVeiculos().getCapacidadeCombustivel() - devolucao.getDiferencaLitros();
            jTextFieldLitroVeiculoEntrega.setText("" + litroEntregue);

        } catch (Exception error) {
            JOptionPane.showMessageDialog(null, error.getMessage(), "Menssagem", JOptionPane.ERROR_MESSAGE);
        }
    }

    public int DiferencaEntreDatas(Date data1, Date data2) throws Exception {

        long diferencaMS = data2.getTime() - data1.getTime();
        long diferencaSegundos = diferencaMS / 1000;
        long diferencaMinutos = diferencaSegundos / 60;
        long diferencaHoras = diferencaMinutos / 60;
        long diferencaDias = diferencaHoras / 24;
        int dias = (int) diferencaDias;
        return dias;
    }

    public void preencherComboboxLocacoes() throws Exception {
        ArrayList<Locacoes> lista = new ArrayList<Locacoes>();
        try {

            lista = locacaoBll.getAllLocacoes();
        } catch (Exception error) {
            JOptionPane.showMessageDialog(rootPane, error.getMessage(), "Menssagem", JOptionPane.ERROR_MESSAGE);
        }
        jComboBoxLocacaoCliente.removeAllItems();

        for (Locacoes locacao : lista) {
            if (locacao.getStatus().equals("ATIVA")) {
                jComboBoxLocacaoCliente.addItem(locacao.getIden() + "");
            }
        }

    }

    public void ValidaDevolucoes() throws Exception {

        Valida.campoVazio(jFormattedDataDevolucao.getText(), "Digite uma data válida ! \n Campo Vazio ");
        Valida.campoVazio(jTextFieldMultaTransito.getText(), "Multa trânsito inválida ! \n Campo Vazio ");
        Valida.campoVazio(jTextField_DanosVeiculo.getText(), "Danos no veículo inválido ! \n Campo Vazio ");
        Valida.campoVazio(jTextFieldKmNaEntrega.getText(), "Digite um Km válido ! \n Campo Vazio ");
        Valida.numberInteger(jTextFieldKmNaEntrega.getText(), "Campo Km aceita apenas números ");
        Valida.notSpecialCharacters(jTextFieldKmNaEntrega.getText(), "Campo Km não é permitido caracteres especiais! ");

        Valida.campoVazio(jTextFieldLitroVeiculoEntrega.getText(), "Digite uma capacidade de combustivel/Litro válida! \n Campo Vazio ");
        Valida.numberInteger(jTextFieldLitroVeiculoEntrega.getText(), "Campo capacidade de combustivel/Litro aceita apenas números ");
        Valida.notSpecialCharacters(jTextFieldLitroVeiculoEntrega.getText(), "Campo capacidade de combustivel/Litro não é permitido caracteres especiais! ");

    }

    public void limparCampos() {

        jTextFieldIDDevolucao.setText("");
        jFormattedDataDevolucao.setText("");
        jTextFieldKmNaEntrega.setText("");
        jTextFieldCapacidadeLitroVeiculo.setText("");
        jTextFieldMultaPorAtraso.setText("");
        jFormattedTextFieldDataPreLocacao.setText("");
        jTextFieldKMInicial.setText("");
        jTextFieldLitroVeiculoEntrega.setText("");
        jTextField_DanosVeiculo.setText("");
        jTextFieldMultaTransito.setText("");
        jTextFieldMultaporCombustivel.setText("");
        jTextFieldDiasAtraso.setText("");
        jTextFieldDiferecaCombustivel.setText("");
        jTextFieldMultaTotal.setText("");
        jTextField_Caucao.setText("");
        jTextField_Troco.setText("");
        jTextField_Debito.setText("");
        jButtonGerarCalculo.setEnabled(true);

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

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jTextFieldIDDevolucao = new javax.swing.JTextField();
        jLabelIdPessoaFisica = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextFieldKmNaEntrega = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTextFieldMultaPorAtraso = new javax.swing.JTextField();
        jComboBoxLocacaoCliente = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jTextFieldKMInicial = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jTextFieldCapacidadeLitroVeiculo = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jTextFieldLitroVeiculoEntrega = new javax.swing.JTextField();
        jSeparator2 = new javax.swing.JSeparator();
        jButtonDevolver = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jButtonGerarCalculo = new javax.swing.JButton();
        jFormattedDataDevolucao = new javax.swing.JFormattedTextField();
        jFormattedTextFieldDataPreLocacao = new javax.swing.JFormattedTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jTextFieldMultaporCombustivel = new javax.swing.JTextField();
        jTextFieldMultaTotal = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jTextFieldDiasAtraso = new javax.swing.JTextField();
        jTextFieldDiferecaCombustivel = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jTextFieldMultaTransito = new javax.swing.JTextField();
        jLabel_DanosVeiculo = new javax.swing.JLabel();
        jTextField_DanosVeiculo = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jTextField_Caucao = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jTextField_Troco = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jTextField_Debito = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableDevolucao = new javax.swing.JTable();
        jButtonQuitarDebito = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("[DEVOLUÇÃO DO VEÍCULO]");

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Devolução"));

        jTextFieldIDDevolucao.setEditable(false);

        jLabelIdPessoaFisica.setText("ID");

        jLabel1.setText("DATA DA DEVOLUÇÃO");

        jLabel2.setText("KM NA ENTREGA");

        jLabel3.setText("MULTA POR ATRASO");

        jTextFieldMultaPorAtraso.setEditable(false);

        jComboBoxLocacaoCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxLocacaoClienteActionPerformed(evt);
            }
        });

        jLabel6.setText("ESCOLHA A LOCAÇÃO PELO SEU CÓDIGO");

        jLabel7.setText("PREVISÃO P/ DEVOLUÇÃO");

        jLabel8.setText("REGISTRO DA DEVOLUÇÃO");

        jLabel9.setText("KM INICIAL ");

        jTextFieldKMInicial.setEditable(false);

        jLabel10.setText("CAP/LIT/VEÍCULO");

        jTextFieldCapacidadeLitroVeiculo.setEditable(false);
        jTextFieldCapacidadeLitroVeiculo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldCapacidadeLitroVeiculoActionPerformed(evt);
            }
        });

        jLabel11.setText("LIT/VEÍCULO/ENTREGA");

        jSeparator1.setBackground(new java.awt.Color(204, 204, 204));
        jSeparator1.setForeground(new java.awt.Color(153, 153, 153));
        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jSeparator2.setBackground(new java.awt.Color(204, 204, 204));
        jSeparator2.setForeground(new java.awt.Color(153, 153, 153));
        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jButtonDevolver.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/pi/icons/botao-de-login.png"))); // NOI18N
        jButtonDevolver.setText("FINALIZAR DEVOLUÇÃO");
        jButtonDevolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDevolverActionPerformed(evt);
            }
        });

        jLabel5.setText("PAGAMENTO");

        jButtonGerarCalculo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/pi/icons/bolsa-de-dinheiro.png"))); // NOI18N
        jButtonGerarCalculo.setText("GERAR CALCULO");
        jButtonGerarCalculo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonGerarCalculoActionPerformed(evt);
            }
        });

        try {
            jFormattedDataDevolucao.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        jFormattedTextFieldDataPreLocacao.setEditable(false);
        try {
            jFormattedTextFieldDataPreLocacao.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jFormattedTextFieldDataPreLocacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFormattedTextFieldDataPreLocacaoActionPerformed(evt);
            }
        });

        jLabel4.setText("MULTA POR COMBUSTIVEL");

        jLabel12.setText("VALOR TOTAL");

        jTextFieldMultaporCombustivel.setEditable(false);

        jTextFieldMultaTotal.setEditable(false);

        jLabel13.setText("DIAS DE ATRASO");

        jLabel14.setText("DIFERENÇA DE COMBUSTIVEL");

        jTextFieldDiasAtraso.setEditable(false);

        jTextFieldDiferecaCombustivel.setEditable(false);

        jLabel15.setText("MULTA TRÂNSITO");

        jLabel_DanosVeiculo.setText("DANOS AO VEÍCULO");

        jLabel16.setText("CAUÇÃO");

        jLabel17.setText("TROCO");

        jLabel18.setText("DEBITO");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jComboBoxLocacaoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(74, 74, 74))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabelIdPessoaFisica)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextFieldIDDevolucao, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.LEADING))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextFieldCapacidadeLitroVeiculo)
                                    .addComponent(jTextFieldKMInicial)
                                    .addComponent(jFormattedTextFieldDataPreLocacao, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(42, 42, 42)))
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(113, 113, 113)
                        .addComponent(jLabel8)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel_DanosVeiculo)
                                .addGap(157, 223, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel11)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel2)
                                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jLabel15)))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jButtonGerarCalculo)
                                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(jTextFieldMultaTransito)
                                                .addComponent(jTextFieldLitroVeiculoEntrega)
                                                .addComponent(jTextFieldKmNaEntrega)
                                                .addComponent(jFormattedDataDevolucao, javax.swing.GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE)
                                                .addComponent(jTextField_DanosVeiculo)))))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(129, 129, 129)
                                .addComponent(jTextFieldDiasAtraso, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(23, 23, 23)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel14)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel18)
                                        .addComponent(jLabel16)))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextFieldDiferecaCombustivel, javax.swing.GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE)
                                    .addComponent(jTextFieldMultaporCombustivel)
                                    .addComponent(jTextField_Caucao)
                                    .addComponent(jTextField_Debito)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(156, 156, 156)
                                        .addComponent(jLabel5))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jLabel13)
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addComponent(jLabel3)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jTextFieldMultaPorAtraso, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel12)
                                                    .addComponent(jLabel17))
                                                .addGap(43, 43, 43)
                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jTextFieldMultaTotal)
                                                    .addComponent(jTextField_Troco))))))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonDevolver)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(8, 8, 8)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(jTextFieldDiasAtraso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14)
                            .addComponent(jTextFieldDiferecaCombustivel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jTextFieldMultaPorAtraso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldMultaporCombustivel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(jTextFieldMultaTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16)
                            .addComponent(jTextField_Caucao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel17)
                            .addComponent(jTextField_Troco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel18)
                            .addComponent(jTextField_Debito, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabelIdPessoaFisica)
                                    .addComponent(jTextFieldIDDevolucao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap())
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jFormattedDataDevolucao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel1))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel2)
                                    .addComponent(jTextFieldKmNaEntrega, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel11)
                                    .addComponent(jTextFieldLitroVeiculoEntrega, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel15)
                                    .addComponent(jTextFieldMultaTransito, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel_DanosVeiculo)
                                    .addComponent(jTextField_DanosVeiculo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(jButtonGerarCalculo)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jComboBoxLocacaoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel7)
                                            .addComponent(jFormattedTextFieldDataPreLocacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel9)
                                            .addComponent(jTextFieldKMInicial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel10)
                                            .addComponent(jTextFieldCapacidadeLitroVeiculo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(33, 33, 33))
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 46, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jButtonDevolver, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())))
        );

        jTableDevolucao.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "CÓDIGO DA LOCAÇÃO", "DATA DA DEVOLUÇÃO", "DIAS DE ATRASO", "MULTA POR ATRASO", "LITROS A MENOS", "MULTA POR LITROS", "VALOR TOTAL", "STATUS"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true, true, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableDevolucao.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableDevolucaoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTableDevolucao);
        if (jTableDevolucao.getColumnModel().getColumnCount() > 0) {
            jTableDevolucao.getColumnModel().getColumn(0).setMaxWidth(40);
        }

        jButtonQuitarDebito.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/pi/icons/retroceder.png"))); // NOI18N
        jButtonQuitarDebito.setText("QUITAR DÉBITO");
        jButtonQuitarDebito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonQuitarDebitoActionPerformed(evt);
            }
        });

        jButton1.setText("LIMPAR");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(62, 62, 62)
                        .addComponent(jButtonQuitarDebito)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonQuitarDebito))
                .addGap(91, 91, 91))
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

    private void jButtonDevolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDevolverActionPerformed

        try {
            double troco;
            double debito;
            locacao = locacaoBll.getLocacoesById(Integer.parseInt(jComboBoxLocacaoCliente.getSelectedItem().toString()));
            if (devolucao.getValorTotal() <= locacao.getValorCaucao()) {
                troco = locacao.getValorCaucao() - devolucao.getValorTotal();
                debito = 0;

                devolucaoBll.addDevolucoes(devolucao);

                //--- UPDATE KM NA ENTREGA ------------------------------------------------------------>
                veiculo = locacao.getVeiculos();
                veiculo.setQuilometragem(Double.parseDouble(jTextFieldKmNaEntrega.getText()));
                veiculo.setStatus("DISPONIVEL");
                veiculoBll.updateVeiculos(veiculo);

                cliente = locacao.getCliente();
                cliente.setStatus("ADIMPLENTE");
                clienteBll.updateClientes(cliente);

                motorista = locacao.getMotoristas();
                Clientes cliente2 = motorista.getCliente();
                cliente2.setStatus("ADIMPLENTE");
                clienteBll.updateClientes(cliente2);

                locacao.setStatus("FINALIZADA");
                locacaoBll.updateLocacoes(locacao);

                JOptionPane.showMessageDialog(null, "Devolução Registrada com sucesso!"
                        + "\nValor total da devolução = " + devolucao.getValorTotal()
                        + "\nValor total do caução pago foi = " + locacao.getValorCaucao()
                        + "\nValor do troco para o cliente de = " + troco
                        + "\nObrigado por locar com a Mula Car!");

            } else {
                debito = devolucao.getValorTotal() - locacao.getValorCaucao();
                troco = 0;
                int x = JOptionPane.showConfirmDialog(null, "Valor do caução insuficiente para quitar as multas, você deseja pagar o valor de = " + debito + " e ficar Adimplmente?"
                        + "\nCaso não pague ficará em debito com a locadora e impedido de realizar futuras locações enquanto não quitar o débito!", "Pagar/Devolver", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (x == 0) {
                    debito = devolucao.getValorTotal() - locacao.getValorCaucao();
                    troco = 0;

                    devolucaoBll.addDevolucoes(devolucao);

                    //--- UPDATE KM NA ENTREGA ------------------------------------------------------------>
                    veiculo = locacao.getVeiculos();
                    veiculo.setQuilometragem(Double.parseDouble(jTextFieldKmNaEntrega.getText()));
                    veiculo.setStatus("DISPONIVEL");
                    veiculoBll.updateVeiculos(veiculo);

                    cliente = locacao.getCliente();
                    cliente.setStatus("ADIMPLENTE");
                    clienteBll.updateClientes(cliente);

                    motorista = locacao.getMotoristas();
                    Clientes cliente2 = motorista.getCliente();
                    cliente2.setStatus("ADIMPLENTE");
                    clienteBll.updateClientes(cliente2);

                    locacao.setStatus("FINALIZADA");
                    locacaoBll.updateLocacoes(locacao);

                    JOptionPane.showMessageDialog(null, "Devolução Registrada com sucesso!"
                            + "\nValor total da devolução = " + devolucao.getValorTotal()
                            + "\nValor total do caução pago foi = " + locacao.getValorCaucao()
                            + "\nValor do troco para o cliente de = " + troco
                            + "\nObrigado por locar com a Mula Car!");

                } else {
                    debito = devolucao.getValorTotal() - locacao.getValorCaucao();
                    troco = 0;
                    devolucaoBll.addDevolucoes(devolucao);
                    veiculo = locacao.getVeiculos();
                    veiculo.setQuilometragem(Double.parseDouble(jTextFieldKmNaEntrega.getText()));
                    veiculo.setStatus("DISPONIVEL");
                    veiculoBll.updateVeiculos(veiculo);

                    cliente = locacao.getCliente();
                    cliente.setStatus("INADIMPLENTE");
                    cliente.setMulta(debito);
                    clienteBll.updateClientes(cliente);

                    if (!cliente.getTipo().equals("MOTORISTA")) {
                        motorista = locacao.getMotoristas();
                        Clientes cliente2 = motorista.getCliente();
                        cliente2.setStatus("INADIMPLENTE");
                        clienteBll.updateClientes(cliente2);
                    }

                    locacao.setStatus("PENDENTE");
                    locacaoBll.updateLocacoes(locacao);

                    preencherComboboxLocacoes();
                    JOptionPane.showMessageDialog(null, "Devolução cancelada, cliente agora esta inadimplente até o pagamento do debito!");
                }

            }

            preencherGridDevolucoes();
            limparCampos();

        } catch (Exception error) {
            JOptionPane.showMessageDialog(null, error.getMessage(), "Erro ao finalizar Devolução", JOptionPane.ERROR_MESSAGE);
        }

        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonDevolverActionPerformed

    private void jTableDevolucaoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableDevolucaoMouseClicked

        try {
            jButtonGerarCalculo.setEnabled(false);
            jButtonDevolver.setEnabled(false);
            preencherFormularioDevolucao();
        } catch (Exception error) {
            JOptionPane.showMessageDialog(rootPane, error.getMessage(), "Menssagem", JOptionPane.ERROR_MESSAGE);
        }

        // TODO add your handling code here:
    }//GEN-LAST:event_jTableDevolucaoMouseClicked

    private void jButtonGerarCalculoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGerarCalculoActionPerformed

        try {
           
            jButtonDevolver.setEnabled(true);
             if (jComboBoxLocacaoCliente.getSelectedIndex() == -1) {
                throw new RuntimeException("Selecione uma locação para gerar o calculo!");
            }
           
            if (!locacao.getStatus().equals("ATIVA")) {
                throw new RuntimeException("Esta locação ja foi encerrada!");
            }

            ValidaDevolucoes();
             locacao = locacaoBll.getLocacoesById(Integer.parseInt(jComboBoxLocacaoCliente.getSelectedItem().toString()));

            //--- CÁLCULO ATRASO/DIAS ----------------------------------------------------------------------------->
            //
            Date data = formato.parse(jFormattedDataDevolucao.getText());

            float valorCategoria = locacao.getVeiculos().getModelo().getCategoria().getValorDiarioLocacao();
            int diferencaData = DiferencaEntreDatas(locacao.getDataPrevistDeDevolucao(), data);
            float multaAtraso = (diferencaData * valorCategoria);

            //--- CÁLCULO DE LITROS COMBUSTÍVEL R$ 6,00 L -------------------------------------------------------->
            //
            float multaLitro;
            float litroEntregue = Float.parseFloat(jTextFieldLitroVeiculoEntrega.getText());
            float capacidadeLitro = locacao.getVeiculos().getCapacidadeCombustivel();
            multaLitro = (capacidadeLitro - litroEntregue) * 6;
            float diferecaLitro = (capacidadeLitro - litroEntregue);
            // -- VALIDAÇÕES
            double km = Double.parseDouble(jTextFieldKmNaEntrega.getText());
            if (locacao.getDataPrevistDeDevolucao().compareTo(data) > 0) {
                throw new RuntimeException("Data de devolução inválida, a data deve ser igual ou superior a data prevista para devolução");
            }
            if (km <= locacao.getKmInicial()) {
                throw new RuntimeException("KM de entrega inválido, KM na entrega deve ser maior que KM inicial da locação");
            }
            if (km > 300000) {
                throw new RuntimeException("KM de entrega inválido, KM na entrega não deve exceder 300.000 km por padrão");
            }
            if (litroEntregue > capacidadeLitro || litroEntregue <= 0) {
                throw new RuntimeException("Litros entregue inválido, quantidade deve ser menor ou igual a capacidade de litros no tanque");
            }

            //--- CÁLCULO TOTAL MULTA POR ATRASO ----------------------------------------------------------------->
            //
            float multatransito = Float.parseFloat(jTextFieldMultaTransito.getText());
            float danocarro = Float.parseFloat(jTextField_DanosVeiculo.getText());
            float total = multaAtraso + multaLitro + multatransito + danocarro;
            locacao = locacaoBll.getLocacoesById(Integer.parseInt(jComboBoxLocacaoCliente.getSelectedItem().toString()));
            devolucao.setDataDevolucao(data);
            devolucao.setDiferencaDias(diferencaData);
            devolucao.setMultaPorAtraso(multaAtraso);
            devolucao.setDiferencaLitros(diferecaLitro);
            devolucao.setMultaPorLitro(multaLitro);
            devolucao.setValorTotal(total);
            devolucao.setMultaTransito(multatransito);
            devolucao.setDanosVeiculo(danocarro);
            devolucao.setLocacao(locacao);
            double troco;
            double debito;

            locacao = locacaoBll.getLocacoesById(Integer.parseInt(jComboBoxLocacaoCliente.getSelectedItem().toString()));
            if (devolucao.getValorTotal() <= locacao.getValorCaucao()) {
                troco = locacao.getValorCaucao() - devolucao.getValorTotal();
                debito = 0;
            } else {
                debito = devolucao.getValorTotal() - locacao.getValorCaucao();
                troco = 0;
            }

            jTextFieldDiasAtraso.setText("" + diferencaData);
            jTextFieldDiferecaCombustivel.setText("" + diferecaLitro);
            jTextFieldMultaPorAtraso.setText("" + multaAtraso);
            jTextFieldMultaporCombustivel.setText("" + multaLitro);
            jTextFieldMultaTotal.setText("" + total);
            jTextField_Caucao.setText("" + locacao.getValorCaucao());
            jTextField_Troco.setText("" + troco);
            jTextField_Debito.setText("" + debito);

        } catch (Exception error) {
            JOptionPane.showMessageDialog(null, "Erro ao gerar calculo " + error.getMessage());
        }

        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonGerarCalculoActionPerformed

    private void jFormattedTextFieldDataPreLocacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFormattedTextFieldDataPreLocacaoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jFormattedTextFieldDataPreLocacaoActionPerformed

    private void jTextFieldCapacidadeLitroVeiculoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldCapacidadeLitroVeiculoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldCapacidadeLitroVeiculoActionPerformed

    private void jComboBoxLocacaoClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxLocacaoClienteActionPerformed
        try {
            locacao = locacaoBll.getLocacoesById(Integer.parseInt(jComboBoxLocacaoCliente.getSelectedItem().toString()));
            jFormattedTextFieldDataPreLocacao.setText(convertDate(locacao.getDataPrevistDeDevolucao()));
            jTextFieldKMInicial.setText(locacao.getKmInicial() + "");
            jTextFieldCapacidadeLitroVeiculo.setText(locacao.getVeiculos().getCapacidadeCombustivel() + "");
        } catch (Exception error) {
            JOptionPane.showMessageDialog(rootPane, error.getMessage(), "Menssagem", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jComboBoxLocacaoClienteActionPerformed

    private void jButtonQuitarDebitoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonQuitarDebitoActionPerformed
        try {
            if (jTableDevolucao.getSelectedRow() == -1) {
                throw new Exception("Selecione uma devolução na tabela para ser quitata!");
            }
            int id = Integer.parseInt(jTableDevolucao.getValueAt(jTableDevolucao.getSelectedRow(), 0).toString());
            devolucao = devolucaoBll.getDevolucaoById(id);
            if (devolucao.getLocacao().getCliente().getMulta() == 0) {
                JOptionPane.showMessageDialog(null, "Não existe débitos para esta Devolução");
            } else {
                int x = JOptionPane.showConfirmDialog(null, "Deseja quitar o débito de = " + devolucao.getLocacao().getCliente().getMulta() + "R$ para ficar adimplmente com a locadora?", "Pagar/Devolver", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (x == 0) {
                    Clientes cliente2 = devolucao.getLocacao().getCliente();
                    cliente2.setStatus("ADIMPLENTE");
                    cliente2.setMulta(0);
                    clienteBll.updateClientes(cliente2);
                    if (!cliente2.getTipo().equals("MOTORISTA")) {
                        motorista = devolucao.getLocacao().getMotoristas();
                        Clientes cliente3 = motorista.getCliente();
                        cliente3.setStatus("ADIMPLENTE");
                        clienteBll.updateClientes(cliente3);
                    }
                    Locacoes locacao2 = devolucao.getLocacao();
                    locacao2.setStatus("FINALIZADA");
                    locacaoBll.updateLocacoes(locacao2);
                    JOptionPane.showMessageDialog(null, "Cliente agora está Adimplente com a locadora, devolução finalizada");

                } else {
                    JOptionPane.showMessageDialog(null, "Cliente continua Inadimplente com a locadora, operação cancelada");
                }

                preencherGridDevolucoes();
                limparCampos();
            }

        } catch (Exception error) {
            JOptionPane.showMessageDialog(rootPane, error.getMessage(), "Menssagem", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButtonQuitarDebitoActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        limparCampos();
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(TelaDevolucoes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaDevolucoes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaDevolucoes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaDevolucoes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaDevolucoes().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButtonDevolver;
    private javax.swing.JButton jButtonGerarCalculo;
    private javax.swing.JButton jButtonQuitarDebito;
    private javax.swing.JComboBox<String> jComboBoxLocacaoCliente;
    private javax.swing.JFormattedTextField jFormattedDataDevolucao;
    private javax.swing.JFormattedTextField jFormattedTextFieldDataPreLocacao;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelIdPessoaFisica;
    private javax.swing.JLabel jLabel_DanosVeiculo;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTable jTableDevolucao;
    private javax.swing.JTextField jTextFieldCapacidadeLitroVeiculo;
    private javax.swing.JTextField jTextFieldDiasAtraso;
    private javax.swing.JTextField jTextFieldDiferecaCombustivel;
    private javax.swing.JTextField jTextFieldIDDevolucao;
    private javax.swing.JTextField jTextFieldKMInicial;
    private javax.swing.JTextField jTextFieldKmNaEntrega;
    private javax.swing.JTextField jTextFieldLitroVeiculoEntrega;
    private javax.swing.JTextField jTextFieldMultaPorAtraso;
    private javax.swing.JTextField jTextFieldMultaTotal;
    private javax.swing.JTextField jTextFieldMultaTransito;
    private javax.swing.JTextField jTextFieldMultaporCombustivel;
    private javax.swing.JTextField jTextField_Caucao;
    private javax.swing.JTextField jTextField_DanosVeiculo;
    private javax.swing.JTextField jTextField_Debito;
    private javax.swing.JTextField jTextField_Troco;
    // End of variables declaration//GEN-END:variables
}
