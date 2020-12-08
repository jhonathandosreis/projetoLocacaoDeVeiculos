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

import br.com.pi.bll.DevolucoesBll;
import br.com.pi.bll.LocacoesBll;
import br.com.pi.bll.VeiculosBll;
import br.com.pi.model.Devolucoes;
import br.com.pi.model.Locacoes;
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
    //--- FIM BLL'S ----------------------------------------------------------------------------------->
    //
    //--- CLASSES -------------------------------------------------------------------------------------> 
    private Devolucoes devolucao = null;
    private Locacoes locacao = null;
    private Veiculos veiculo = null;

    //--- FIM CLASSES --------------------------------------------------------------------------------->
    //
    public TelaDevolucoes() {
        initComponents();

        try {

            devolucao = new Devolucoes();
            locacao = new Locacoes();
            veiculo = new Veiculos();

            devolucaoBll = new DevolucoesBll();
            locacaoBll = new LocacoesBll();
            veiculoBll = new VeiculosBll();

            preencherComboboxLocacoes();
            preencherGridDevolucoes();

            jTextFieldKMInicial.setEnabled(false);
            jTextFieldCapacidadeLitroVeiculo.setEnabled(false);
            jTextFieldIDDevolucao.setEnabled(false);
            jFormattedTextFieldDataPreLocacao.setEnabled(false);
            jTextFieldMultaPorAtraso.setEnabled(false);
            jButtonDevolver.setEnabled(false);
            jButtonCancelar.setEnabled(false);

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

            Object[] linha = new Object[4];

            ArrayList<Devolucoes> devolucao = new DevolucoesBll().getAllDevolucao();

            for (Devolucoes devolucoes : devolucao) {

                linha[0] = devolucoes.getIden();
                linha[1] = devolucoes.getLocacao().getIden();
                linha[2] = devolucoes.getDataDevolucao();
                linha[3] = devolucoes.getMultaPorAtraso();

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

            jTextFieldIDDevolucao.setText("" + devolucao.getIden());
            jFormattedDataDevolucao.setText(convertDate(devolucao.getDataDevolucao()));
            jTextFieldMultaPorAtraso.setText("" + devolucao.getMultaPorAtraso());

        } catch (Exception error) {
            JOptionPane.showMessageDialog(null, error.getMessage(), "Menssagem", JOptionPane.ERROR_MESSAGE);
        }
    }


    public int DiferencaEntreDatas(Date data1, Date data2) throws Exception{
            if(data1.compareTo(data2) >=0 ) throw new Exception("Data de devolução inválida!");
            long diferencaMS =  data2.getTime() - data1.getTime();
            long diferencaSegundos = diferencaMS / 1000;
            long diferencaMinutos = diferencaSegundos / 60;
            long diferencaHoras = diferencaMinutos / 60;
            long diferencaDias = diferencaHoras / 24;
            int dias = (int) diferencaDias;
            return dias;
    }

    public void preencherComboboxLocacoes() throws Exception {

        try {
            
            ArrayList<Locacoes> lista = locacaoBll.getAllLocacoes();
            jComboBoxLocacaoCliente.removeAllItems();
            jComboBoxLocacaoCliente.addItem("<SELECIONE>");

            for (Locacoes locacao : lista) {
                jComboBoxLocacaoCliente.addItem(locacao.getIden() + "");
            }
        } catch (Exception error) {
            JOptionPane.showMessageDialog(rootPane, error.getMessage(), "Menssagem", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void ValidaDevolucoes() throws Exception {

        Valida.campoVazio(jFormattedDataDevolucao.getText(), "Digite uma data válida ! \n Campo Vazio ");
      //  Valida.numberInteger(jFormattedDataDevolucao.getText(), "Campo data aceita apenas números ");
     //   Valida.notSpecialCharacters(jFormattedDataDevolucao.getText(), "Campo data não é permitido caracteres especiais! ");

        Valida.campoVazio(jTextFieldKmNaEntrega.getText(), "Digite um Km válida ! \n Campo Vazio ");
        Valida.numberInteger(jTextFieldKmNaEntrega.getText(), "Campo Km aceita apenas números ");
        Valida.notSpecialCharacters(jTextFieldKmNaEntrega.getText(), "Campo Km não é permitido caracteres especiais! ");

        Valida.campoVazio(jTextFieldLitroVeiculoEntrega.getText(), "Digite uma capacidade de combustivél/Litro válida! \n Campo Vazio ");
        Valida.numberInteger(jTextFieldLitroVeiculoEntrega.getText(), "Campo capacidade de combustivél/Litro aceita apenas números ");
        Valida.notSpecialCharacters(jTextFieldLitroVeiculoEntrega.getText(), "Campo capacidade de combustivél/Litro não é permitido caracteres especiais! ");

        if (jComboBoxLocacaoCliente.getSelectedItem() == "<SELECIONE>") {
            throw new Exception("Selecione um Código de Locação!");
        }

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

        jComboBoxLocacaoCliente.setSelectedIndex(0);
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
        jButtonCancelar = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jButtonGerarCalculo = new javax.swing.JButton();
        jFormattedDataDevolucao = new javax.swing.JFormattedTextField();
        jFormattedTextFieldDataPreLocacao = new javax.swing.JFormattedTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableDevolucao = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("[DEVOLUÇÃO DO VEÍCULO]");

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Devolução"));

        jTextFieldIDDevolucao.setEditable(false);

        jLabelIdPessoaFisica.setText("ID");

        jLabel1.setText("DATA DA DEVOLUÇÃO");

        jLabel2.setText("KM NA ENTREGA");

        jLabel3.setText("MULTA POR ATRASO");

        jLabel6.setText("DADOS LOCATÁRIO");

        jLabel7.setText("DATA PRE/LOCAÇÃO");

        jLabel8.setText("REGISTRO DA DEVOLUÇÃO");

        jLabel9.setText("KM INICIAL ");

        jLabel10.setText("CAP/LIT/VEÍCULO");

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
        jButtonDevolver.setText("DEVOLVER");
        jButtonDevolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDevolverActionPerformed(evt);
            }
        });

        jButtonCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/pi/icons/retroceder.png"))); // NOI18N
        jButtonCancelar.setText("CANCELAR");

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

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextFieldCapacidadeLitroVeiculo)
                            .addComponent(jTextFieldKMInicial)
                            .addComponent(jFormattedTextFieldDataPreLocacao, javax.swing.GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE))
                        .addGap(42, 42, 42))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabelIdPessoaFisica)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextFieldIDDevolucao, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(155, 155, 155)
                                .addComponent(jLabel6)))
                        .addGap(125, 125, 125))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jComboBoxLocacaoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(74, 74, 74)))
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(113, 113, 113)
                        .addComponent(jLabel8))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addGap(62, 62, 62))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)))
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextFieldLitroVeiculoEntrega)
                                    .addComponent(jTextFieldKmNaEntrega)
                                    .addComponent(jFormattedDataDevolucao, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 54, Short.MAX_VALUE)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(94, 94, 94)
                                .addComponent(jButtonDevolver)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonCancelar))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(44, 44, 44)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextFieldMultaPorAtraso, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(48, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(161, 161, 161))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jButtonGerarCalculo)
                                .addGap(126, 126, 126))))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jComboBoxLocacaoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(123, 123, 123))
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonGerarCalculo)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextFieldMultaPorAtraso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButtonDevolver, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonCancelar))
                        .addGap(39, 39, 39))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabelIdPessoaFisica)
                                    .addComponent(jTextFieldIDDevolucao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap())
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(49, 49, 49)
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
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
        );

        jTableDevolucao.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "LOCAÇÃO / CLIENTE", "DATA DA DEVOLUÇÃO", "MULTA PAGA POR ATRASO"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true, true
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
            jTableDevolucao.getColumnModel().getColumn(0).setMinWidth(40);
            jTableDevolucao.getColumnModel().getColumn(0).setPreferredWidth(40);
            jTableDevolucao.getColumnModel().getColumn(0).setMaxWidth(40);
        }

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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

    private void jButtonDevolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDevolverActionPerformed

        try {
            ValidaDevolucoes();

            Date data = formato.parse(jFormattedDataDevolucao.getText());
            devolucao.setDataDevolucao(data);
            
            devolucao.setMultaPorAtraso(Integer.parseInt(jTextFieldMultaPorAtraso.getText()));
            locacao = locacaoBll.getLocacoesBy(Integer.parseInt(jComboBoxLocacaoCliente.getSelectedItem().toString()));
            devolucao.setLocacao(locacao);

            devolucao.setKmNaEntrega(Integer.parseInt(jTextFieldKmNaEntrega.getText()));

            //--- UPDATE KM NA ENTREGA ----------------------------------------------------------------------------->
            //
            veiculo = locacao.getVeiculos();
            veiculo.setQuilometragem(Integer.parseInt(jTextFieldKmNaEntrega.getText()));
            veiculoBll.updateVeiculos(veiculo);

            //--- DADOS LOCATÁRIO ---------------------------------------------------------------------------------->
            //
            jFormattedTextFieldDataPreLocacao.setText(convertDate(locacao.getDataDeLocacao()));
            jTextFieldKMInicial.setText(locacao.getKmInicial() + "");
            jTextFieldCapacidadeLitroVeiculo.setText(locacao.getVeiculos().getCapacidadeCombustivel() + "");

            devolucaoBll.addDevolucoes(devolucao);

            preencherGridDevolucoes();
            limparCampos();
            JOptionPane.showMessageDialog(null, "Devolução Registrado com sucesso!");
        } catch (Exception error) {
            JOptionPane.showMessageDialog(null, error.getMessage(), "Menssagem", JOptionPane.ERROR_MESSAGE);
        }

        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonDevolverActionPerformed

    private void jTableDevolucaoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableDevolucaoMouseClicked

        try {
            preencherFormularioDevolucao();
        } catch (Exception error) {
            JOptionPane.showMessageDialog(rootPane, error.getMessage(), "Menssagem", JOptionPane.ERROR_MESSAGE);
        }

        // TODO add your handling code here:
    }//GEN-LAST:event_jTableDevolucaoMouseClicked

    private void jButtonGerarCalculoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGerarCalculoActionPerformed

        try {
            jButtonDevolver.setEnabled(true);
            jButtonCancelar.setEnabled(true);

            ValidaDevolucoes();

            locacao = locacaoBll.getLocacoesBy(Integer.parseInt(jComboBoxLocacaoCliente.getSelectedItem().toString()));

            //--- CÁLCULO ATRASO/DIAS ----------------------------------------------------------------------------->
            //
            Date data = formato.parse(jFormattedDataDevolucao.getText());

            float valorCategoria = locacao.getVeiculos().getModelo().getCategoria().getValorDiarioLocacao();
            float diferencaData = DiferencaEntreDatas(locacao.getDataPrevistDeDevolucao(), data);
            float multaAtraso = (diferencaData * valorCategoria);

            //--- CÁLCULO DE LITROS COMBUSTÍVEL R$ 6,00 L -------------------------------------------------------->
            //
            float multaLitro;
            float litroEntregue = Float.parseFloat(jTextFieldLitroVeiculoEntrega.getText());
            float capacidadeLitro = locacao.getVeiculos().getCapacidadeCombustivel();
            multaLitro = (capacidadeLitro - litroEntregue) * 6;

            //--- CÁLCULO TOTAL MULTA POR ATRASO ----------------------------------------------------------------->
            //
            float total = multaAtraso + multaLitro;

            jTextFieldMultaPorAtraso.setText("R$ " + total);

        } catch (Exception error) {
            JOptionPane.showMessageDialog(null, "Erro ao registrar devolução " + error.getMessage());
        }

        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonGerarCalculoActionPerformed

    private void jFormattedTextFieldDataPreLocacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFormattedTextFieldDataPreLocacaoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jFormattedTextFieldDataPreLocacaoActionPerformed

    private void jTextFieldCapacidadeLitroVeiculoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldCapacidadeLitroVeiculoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldCapacidadeLitroVeiculoActionPerformed

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
    private javax.swing.JButton jButtonCancelar;
    private javax.swing.JButton jButtonDevolver;
    private javax.swing.JButton jButtonGerarCalculo;
    private javax.swing.JComboBox<String> jComboBoxLocacaoCliente;
    private javax.swing.JFormattedTextField jFormattedDataDevolucao;
    private javax.swing.JFormattedTextField jFormattedTextFieldDataPreLocacao;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelIdPessoaFisica;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTable jTableDevolucao;
    private javax.swing.JTextField jTextFieldCapacidadeLitroVeiculo;
    private javax.swing.JTextField jTextFieldIDDevolucao;
    private javax.swing.JTextField jTextFieldKMInicial;
    private javax.swing.JTextField jTextFieldKmNaEntrega;
    private javax.swing.JTextField jTextFieldLitroVeiculoEntrega;
    private javax.swing.JTextField jTextFieldMultaPorAtraso;
    // End of variables declaration//GEN-END:variables
}
