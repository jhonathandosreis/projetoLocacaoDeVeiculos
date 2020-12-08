/*
 *  -------------------------------------------------------------------------------------------------->
 *  Licença    : MIT - Copyright 2019 Jhonathan, Gustavo e Miguel 
 *  Criado em  : 21/11/2020 01:30:09 
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

import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;

/**
 *
 * @author jhonlinux
 */
public class TelaLogin extends javax.swing.JFrame {

    TelaMenuPrincipal menu = new TelaMenuPrincipal();
    
    public TelaLogin() {
        initComponents();
        this.setLocationRelativeTo(null);
    }

    private boolean checkLogin(String login, String senha) {
        return login.equals("MulaCar") && senha.equals("1234");
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jTextFieldLogin = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jPasswordField1 = new javax.swing.JPasswordField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("[LOGIN]");
        setMinimumSize(new java.awt.Dimension(1280, 855));
        setPreferredSize(new java.awt.Dimension(1280, 855));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("LOGIN");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 340, -1, -1));

        jTextFieldLogin.setText("MulaCar");
        jTextFieldLogin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextFieldLoginMouseClicked(evt);
            }
        });
        jTextFieldLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldLoginActionPerformed(evt);
            }
        });
        getContentPane().add(jTextFieldLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 360, 230, 30));

        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("SENHA");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 410, -1, -1));

        jPasswordField1.setText("1234");
        jPasswordField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jPasswordField1KeyPressed(evt);
            }
        });
        getContentPane().add(jPasswordField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 430, 230, 30));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/pi/icons/botao-de-login.png"))); // NOI18N
        jButton1.setText("ENTRAR");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 520, 150, 50));

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/pi/icons/backspace.png"))); // NOI18N
        jButton2.setText("SAIR");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 520, 150, 50));
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 270, -1, -1));

        jLabel5.setFont(new java.awt.Font("Swis721 Blk BT", 0, 48)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("M u l a C a r");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 230, 350, 70));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/pi/image/fundo login.jpg"))); // NOI18N
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            
            if (this.checkLogin(jTextFieldLogin.getText(), new String(jPasswordField1.getPassword()).toLowerCase())) {
                menu.setVisible(true);
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(rootPane, "Dados Invalidos");

            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "Erro ao tentar autenticar o usurio.\n" + e);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTextFieldLoginMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextFieldLoginMouseClicked
       jTextFieldLogin.setText("");
    }//GEN-LAST:event_jTextFieldLoginMouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jPasswordField1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPasswordField1KeyPressed
        
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

            if (this.checkLogin(jTextFieldLogin.getText(), new String(jPasswordField1.getPassword()).toLowerCase())) {

                menu.setVisible(true);
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(rootPane, "Dados Invalidos");
//teste
            }
        }
    }//GEN-LAST:event_jPasswordField1KeyPressed

    private void jTextFieldLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldLoginActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldLoginActionPerformed

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
            java.util.logging.Logger.getLogger(TelaLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaLogin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JTextField jTextFieldLogin;
    // End of variables declaration//GEN-END:variables
}
