/*
 *  -------------------------------------------------------------------------------------------------->
 *  Licença    : MIT - Copyright 2019 Jhonathan, Gustavo e Miguel 
 *  Criado em  : 21/11/2020 01:23:10 
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
import br.com.pi.bll.LocacoesBll;
import br.com.pi.bll.VeiculosBll;
import br.com.pi.model.Clientes;
import br.com.pi.model.Locacoes;
import br.com.pi.model.Motoristas;
import br.com.pi.model.Veiculos;
import java.text.DecimalFormat;
import javax.swing.JOptionPane;

/**
 *
 * @author jhonlinux
 */
public class TelaFinalizarLocacao extends javax.swing.JFrame {

    private TelaLocacao telalocacao;
    private Locacoes locacao;
    private LocacoesBll locacaoBll;
    private Veiculos veiculo = null;
    private VeiculosBll veiculoBll;
    private Clientes cliente = null;
    private Clientes cliente2 = null;
    private ClientesBll clienteBll;
    private Motoristas motorista = null;
    private String pessoa = "";

    public TelaFinalizarLocacao() {
        initComponents();
       
        try {
            locacaoBll = new LocacoesBll();
            veiculoBll = new VeiculosBll();
            clienteBll = new ClientesBll();
        } catch (Exception error) {
            JOptionPane.showMessageDialog(rootPane, error.getMessage(), "Menssagem", JOptionPane.ERROR_MESSAGE);
            this.setLocationRelativeTo(null);
        }
    }

    public void enviaLocacao(TelaLocacao tela, Locacoes locacao, String tipoPessoa) {
        this.pessoa = tipoPessoa;
        this.locacao = locacao;
        this.telalocacao = tela;
        DecimalFormat df = new DecimalFormat("###,##0.00");
        jTextFieldValorLocacao.setText(df.format(locacao.getValorLocacao()) + " R$");
        jTextFieldValorDoCaucao.setText(df.format(locacao.getValorCaucao()) + " R$");
        jTextFieldValorDoSeguro.setText(df.format(locacao.getValorSeguro()) + " R$");
        jTextFieldValorPago.setText(df.format(locacao.getValorTotalPago()) + " R$");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextFieldValorLocacao = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jTextFieldValorDoCaucao = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTextFieldValorDoSeguro = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTextFieldValorPago = new javax.swing.JTextField();
        jButtonPagar = new javax.swing.JButton();
        jButtonCancelar = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("[FINALIZAR LOCAÇÃO DO VEÍCULO]");
        setMaximumSize(new java.awt.Dimension(1280, 720));
        setMinimumSize(new java.awt.Dimension(1280, 720));
        setResizable(false);
        setSize(new java.awt.Dimension(1280, 720));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(0, 0, 0));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)), "Finalizar Locação", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Lucida Grande", 0, 13), new java.awt.Color(255, 255, 255))); // NOI18N

        jLabel1.setForeground(new java.awt.Color(204, 204, 204));
        jLabel1.setText("VALOR DA LOCAÇÃO");

        jLabel2.setForeground(new java.awt.Color(204, 204, 204));
        jLabel2.setText("VALOR DO CAUÇÃO");

        jLabel3.setForeground(new java.awt.Color(204, 204, 204));
        jLabel3.setText("VALOR DO SEGURO");

        jLabel4.setForeground(new java.awt.Color(204, 204, 204));
        jLabel4.setText("VALOR TOTAL A SER PAGO");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addComponent(jLabel1))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jTextFieldValorLocacao, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldValorDoCaucao, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(32, 32, 32)
                                .addComponent(jLabel2))
                            .addComponent(jTextFieldValorPago, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jTextFieldValorDoSeguro, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(44, 44, 44)
                                .addComponent(jLabel3))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(227, 227, 227)
                        .addComponent(jLabel4)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldValorLocacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldValorDoCaucao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldValorDoSeguro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldValorPago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 200, -1, -1));

        jButtonPagar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/pi/icons/bolsa-de-dinheiro.png"))); // NOI18N
        jButtonPagar.setText("PAGAR");
        jButtonPagar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPagarActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonPagar, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 400, 140, 50));

        jButtonCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/pi/icons/retroceder.png"))); // NOI18N
        jButtonCancelar.setText("CANCELAR");
        jButtonCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelarActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 400, 140, 50));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/pi/icons/limpar-limpo.png"))); // NOI18N
        jButton1.setText("LIMPAR");
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 400, 140, 50));
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 525, -1, -1));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/pi/image/fundo desfolque bmw_Easy-Resize.com.jpg"))); // NOI18N
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonPagarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPagarActionPerformed
        try {
            if (telalocacao != null) {
                cliente = locacao.getCliente();
                if (pessoa.equals("F")) {
                    cliente.setStatus("LOCANDO");
                    clienteBll.updateClientes(cliente);
                }
                motorista = locacao.getMotoristas();
                cliente2 = motorista.getCliente();
                cliente2.setStatus("LOCANDO");
                clienteBll.updateClientes(cliente2);

                veiculo = locacao.getVeiculos();
                veiculo.setStatus("LOCADO");
                veiculoBll.updateVeiculos(veiculo);

                locacaoBll.addLocacoes(locacao);
                telalocacao.LimparTelaLocacao();
                telalocacao.preencherGridLocacoes();
                telalocacao.respostaFinalizacao("Locação finalizada com sucesso!");

                this.dispose();
            }
        } catch (Exception error) {
            JOptionPane.showMessageDialog(rootPane, error.getMessage(), "Menssagem", JOptionPane.ERROR_MESSAGE);
            this.setLocationRelativeTo(null);

        }
    }//GEN-LAST:event_jButtonPagarActionPerformed

    private void jButtonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelarActionPerformed
        try {
            if (telalocacao != null) {
                telalocacao.LimparTelaLocacao();
                telalocacao.respostaFinalizacao("Locação cancelada");

                this.dispose();
            }
        } catch (Exception error) {
            JOptionPane.showMessageDialog(rootPane, error.getMessage(), "Menssagem", JOptionPane.ERROR_MESSAGE);
            this.setLocationRelativeTo(null);

        }
    }//GEN-LAST:event_jButtonCancelarActionPerformed

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
            java.util.logging.Logger.getLogger(TelaFinalizarLocacao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaFinalizarLocacao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaFinalizarLocacao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaFinalizarLocacao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaFinalizarLocacao().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButtonCancelar;
    private javax.swing.JButton jButtonPagar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField jTextFieldValorDoCaucao;
    private javax.swing.JTextField jTextFieldValorDoSeguro;
    private javax.swing.JTextField jTextFieldValorLocacao;
    private javax.swing.JTextField jTextFieldValorPago;
    // End of variables declaration//GEN-END:variables
}
