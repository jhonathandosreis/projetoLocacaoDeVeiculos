/*
 *  -------------------------------------------------------------------------------------------------->
 *  Licença    : MIT - Copyright 2019 Jhonathan, Gustavo e Miguel 
 *  Criado em  : 15/11/2020 17:52:59 
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

import javax.swing.JOptionPane;

/**
 *
 * @author jhonlinux
 */


public class TelaMenuPrincipal extends javax.swing.JFrame {

    public TelaMenuPrincipal() {
        initComponents();
        this.setExtendedState(MAXIMIZED_BOTH);
        this.setLocationRelativeTo(null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuBar2 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem15 = new javax.swing.JMenuItem();
        jDesktopPanePrincipal = new javax.swing.JDesktopPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenuFuncionario = new javax.swing.JMenu();
        jMenuItemCadastroFuncionario = new javax.swing.JMenuItem();
        jMenuItemLoginFuncionario = new javax.swing.JMenuItem();
        jMenuItemSair = new javax.swing.JMenuItem();
        jMenuCliente = new javax.swing.JMenu();
        jMenuItemPessoaFisica = new javax.swing.JMenuItem();
        jMenuItemPessoaJuridica = new javax.swing.JMenuItem();
        jMenuItemMotorista = new javax.swing.JMenuItem();
        jMenuVeiculo = new javax.swing.JMenu();
        jMenuItemVeiculo = new javax.swing.JMenuItem();
        jMenuItemMarcas = new javax.swing.JMenuItem();
        jMenuItemModelo = new javax.swing.JMenuItem();
        jMenuItemCategoria = new javax.swing.JMenuItem();
        jMenuItemTIpoDeVeiculo = new javax.swing.JMenuItem();
        jMenuLocacao = new javax.swing.JMenu();
        jMenuItemLocacao = new javax.swing.JMenuItem();
        jMenuItemDevolucao = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenuItemDesenvolvedores = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();

        jMenuItem1.setText("jMenuItem1");

        jMenuItem2.setText("jMenuItem2");

        jMenu1.setText("File");
        jMenuBar2.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar2.add(jMenu2);

        jMenu3.setText("jMenu3");

        jMenuItem4.setText("jMenuItem4");

        jMenuItem5.setText("jMenuItem5");

        jMenuItem15.setText("jMenuItem15");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("[MENU PRINCIPAL]");

        javax.swing.GroupLayout jDesktopPanePrincipalLayout = new javax.swing.GroupLayout(jDesktopPanePrincipal);
        jDesktopPanePrincipal.setLayout(jDesktopPanePrincipalLayout);
        jDesktopPanePrincipalLayout.setHorizontalGroup(
            jDesktopPanePrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1131, Short.MAX_VALUE)
        );
        jDesktopPanePrincipalLayout.setVerticalGroup(
            jDesktopPanePrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 651, Short.MAX_VALUE)
        );

        jMenuFuncionario.setIcon(new javax.swing.ImageIcon("/home/jhonlinux/Documentos/Repositorio/projetoLocacaoDeVeiculos/ProjetoMulaCar/src/br/com/pi/icons/funcionario-cad.png")); // NOI18N
        jMenuFuncionario.setText("Funcionario");
        jMenuFuncionario.setToolTipText("");

        jMenuItemCadastroFuncionario.setIcon(new javax.swing.ImageIcon("/home/jhonlinux/Documentos/Repositorio/projetoLocacaoDeVeiculos/ProjetoMulaCar/src/br/com/pi/icons/funcionarios.png")); // NOI18N
        jMenuItemCadastroFuncionario.setText("Cadastro  ");
        jMenuItemCadastroFuncionario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemCadastroFuncionarioActionPerformed(evt);
            }
        });
        jMenuFuncionario.add(jMenuItemCadastroFuncionario);

        jMenuItemLoginFuncionario.setIcon(new javax.swing.ImageIcon("/home/jhonlinux/Documentos/Repositorio/projetoLocacaoDeVeiculos/ProjetoMulaCar/src/br/com/pi/icons/perfil.png")); // NOI18N
        jMenuItemLoginFuncionario.setText("Login");
        jMenuItemLoginFuncionario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemLoginFuncionarioActionPerformed(evt);
            }
        });
        jMenuFuncionario.add(jMenuItemLoginFuncionario);

        jMenuItemSair.setIcon(new javax.swing.ImageIcon("/home/jhonlinux/Documentos/Repositorio/projetoLocacaoDeVeiculos/ProjetoMulaCar/src/br/com/pi/icons/botao-x.png")); // NOI18N
        jMenuItemSair.setText("Sair");
        jMenuItemSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemSairActionPerformed(evt);
            }
        });
        jMenuFuncionario.add(jMenuItemSair);

        jMenuBar1.add(jMenuFuncionario);

        jMenuCliente.setIcon(new javax.swing.ImageIcon("/home/jhonlinux/Documentos/Repositorio/projetoLocacaoDeVeiculos/ProjetoMulaCar/src/br/com/pi/icons/grupo-de-usuarios.png")); // NOI18N
        jMenuCliente.setText("CLiente");

        jMenuItemPessoaFisica.setIcon(new javax.swing.ImageIcon("/home/jhonlinux/Documentos/Repositorio/projetoLocacaoDeVeiculos/ProjetoMulaCar/src/br/com/pi/icons/adicionar-usuario (1).png")); // NOI18N
        jMenuItemPessoaFisica.setText("Pessoal Física");
        jMenuItemPessoaFisica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemPessoaFisicaActionPerformed(evt);
            }
        });
        jMenuCliente.add(jMenuItemPessoaFisica);

        jMenuItemPessoaJuridica.setIcon(new javax.swing.ImageIcon("/home/jhonlinux/Documentos/Repositorio/projetoLocacaoDeVeiculos/ProjetoMulaCar/src/br/com/pi/icons/adicionar-usuario (2).png")); // NOI18N
        jMenuItemPessoaJuridica.setText("Pessoa Jurídica");
        jMenuItemPessoaJuridica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemPessoaJuridicaActionPerformed(evt);
            }
        });
        jMenuCliente.add(jMenuItemPessoaJuridica);

        jMenuItemMotorista.setIcon(new javax.swing.ImageIcon("/home/jhonlinux/Documentos/Repositorio/projetoLocacaoDeVeiculos/ProjetoMulaCar/src/br/com/pi/icons/adicionar-usuario (3).png")); // NOI18N
        jMenuItemMotorista.setText("Motorista");
        jMenuItemMotorista.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemMotoristaActionPerformed(evt);
            }
        });
        jMenuCliente.add(jMenuItemMotorista);

        jMenuBar1.add(jMenuCliente);

        jMenuVeiculo.setIcon(new javax.swing.ImageIcon("/home/jhonlinux/Documentos/Repositorio/projetoLocacaoDeVeiculos/ProjetoMulaCar/src/br/com/pi/icons/carro-sedan-na-frente.png")); // NOI18N
        jMenuVeiculo.setText("Veículo");

        jMenuItemVeiculo.setIcon(new javax.swing.ImageIcon("/home/jhonlinux/Documentos/Repositorio/projetoLocacaoDeVeiculos/ProjetoMulaCar/src/br/com/pi/icons/sedan.png")); // NOI18N
        jMenuItemVeiculo.setText("Veículos");
        jMenuItemVeiculo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemVeiculoActionPerformed(evt);
            }
        });
        jMenuVeiculo.add(jMenuItemVeiculo);

        jMenuItemMarcas.setIcon(new javax.swing.ImageIcon("/home/jhonlinux/Documentos/Repositorio/projetoLocacaoDeVeiculos/ProjetoMulaCar/src/br/com/pi/icons/crossover (1).png")); // NOI18N
        jMenuItemMarcas.setText("Marcas");
        jMenuItemMarcas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemMarcasActionPerformed(evt);
            }
        });
        jMenuVeiculo.add(jMenuItemMarcas);

        jMenuItemModelo.setIcon(new javax.swing.ImageIcon("/home/jhonlinux/Documentos/Repositorio/projetoLocacaoDeVeiculos/ProjetoMulaCar/src/br/com/pi/icons/crossover.png")); // NOI18N
        jMenuItemModelo.setText("Modelos");
        jMenuItemModelo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemModeloActionPerformed(evt);
            }
        });
        jMenuVeiculo.add(jMenuItemModelo);

        jMenuItemCategoria.setIcon(new javax.swing.ImageIcon("/home/jhonlinux/Documentos/Repositorio/projetoLocacaoDeVeiculos/ProjetoMulaCar/src/br/com/pi/icons/hatchback.png")); // NOI18N
        jMenuItemCategoria.setText("Categorias");
        jMenuItemCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemCategoriaActionPerformed(evt);
            }
        });
        jMenuVeiculo.add(jMenuItemCategoria);

        jMenuItemTIpoDeVeiculo.setIcon(new javax.swing.ImageIcon("/home/jhonlinux/Documentos/Repositorio/projetoLocacaoDeVeiculos/ProjetoMulaCar/src/br/com/pi/icons/carrinho-de-bebe.png")); // NOI18N
        jMenuItemTIpoDeVeiculo.setText("Tipo de Veículos");
        jMenuItemTIpoDeVeiculo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemTIpoDeVeiculoActionPerformed(evt);
            }
        });
        jMenuVeiculo.add(jMenuItemTIpoDeVeiculo);

        jMenuBar1.add(jMenuVeiculo);

        jMenuLocacao.setIcon(new javax.swing.ImageIcon("/home/jhonlinux/Documentos/Repositorio/projetoLocacaoDeVeiculos/ProjetoMulaCar/src/br/com/pi/icons/chaves.png")); // NOI18N
        jMenuLocacao.setText("Locação");

        jMenuItemLocacao.setIcon(new javax.swing.ImageIcon("/home/jhonlinux/Documentos/Repositorio/projetoLocacaoDeVeiculos/ProjetoMulaCar/src/br/com/pi/icons/combinado (1).png")); // NOI18N
        jMenuItemLocacao.setText("Locação");
        jMenuItemLocacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemLocacaoActionPerformed(evt);
            }
        });
        jMenuLocacao.add(jMenuItemLocacao);

        jMenuItemDevolucao.setIcon(new javax.swing.ImageIcon("/home/jhonlinux/Documentos/Repositorio/projetoLocacaoDeVeiculos/ProjetoMulaCar/src/br/com/pi/icons/combinado2 (1).png")); // NOI18N
        jMenuItemDevolucao.setText("Devolução");
        jMenuItemDevolucao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemDevolucaoActionPerformed(evt);
            }
        });
        jMenuLocacao.add(jMenuItemDevolucao);

        jMenuBar1.add(jMenuLocacao);

        jMenu4.setIcon(new javax.swing.ImageIcon("/home/jhonlinux/Documentos/Repositorio/projetoLocacaoDeVeiculos/ProjetoMulaCar/src/br/com/pi/icons/botao-de-informacao.png")); // NOI18N
        jMenu4.setText("Sobre");

        jMenuItemDesenvolvedores.setIcon(new javax.swing.ImageIcon("/home/jhonlinux/Documentos/Repositorio/projetoLocacaoDeVeiculos/ProjetoMulaCar/src/br/com/pi/icons/computador-desktop.png")); // NOI18N
        jMenuItemDesenvolvedores.setText("Desenvolvedores");
        jMenu4.add(jMenuItemDesenvolvedores);

        jMenuBar1.add(jMenu4);

        jMenu5.setIcon(new javax.swing.ImageIcon("/home/jhonlinux/Documentos/Repositorio/projetoLocacaoDeVeiculos/ProjetoMulaCar/src/br/com/pi/icons/desconhecido.png")); // NOI18N
        jMenu5.setText("Ajuda");
        jMenuBar1.add(jMenu5);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jDesktopPanePrincipal)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPanePrincipal)
        );

        jDesktopPanePrincipal.getAccessibleContext().setAccessibleDescription("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItemCadastroFuncionarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemCadastroFuncionarioActionPerformed
        try {

        } catch (Exception error) {
            JOptionPane.showMessageDialog(rootPane, error.getMessage(), "Menssagem", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jMenuItemCadastroFuncionarioActionPerformed

    private void jMenuItemLoginFuncionarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemLoginFuncionarioActionPerformed
        try {
            TelaLogin telaLogin = new TelaLogin();
            telaLogin.setVisible(true);
        } catch (Exception error) {
            JOptionPane.showMessageDialog(rootPane, error.getMessage(), "Menssagem", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jMenuItemLoginFuncionarioActionPerformed

    private void jMenuItemSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemSairActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jMenuItemSairActionPerformed

    private void jMenuItemPessoaFisicaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemPessoaFisicaActionPerformed
        try {
            TelaPessoaFisica telaPessoaFisica = new TelaPessoaFisica();
            telaPessoaFisica.setVisible(true);
        } catch (Exception error) {
            JOptionPane.showMessageDialog(rootPane, error.getMessage(), "Menssagem", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jMenuItemPessoaFisicaActionPerformed

    private void jMenuItemPessoaJuridicaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemPessoaJuridicaActionPerformed
        try {
            TelaPessoaJuridica telaPessoaJuridica = new TelaPessoaJuridica();
            telaPessoaJuridica.setVisible(true);
        } catch (Exception error) {
            JOptionPane.showMessageDialog(rootPane, error.getMessage(), "Menssagem", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jMenuItemPessoaJuridicaActionPerformed

    private void jMenuItemMotoristaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemMotoristaActionPerformed
        try {
            TelaMotoristas telaMotorista = new TelaMotoristas();
            telaMotorista.setVisible(true);
        } catch (Exception error) {
            JOptionPane.showMessageDialog(rootPane, error.getMessage(), "Menssagem", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jMenuItemMotoristaActionPerformed

    private void jMenuItemMarcasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemMarcasActionPerformed
        try {
            TelaMarcas telaMarcas = new TelaMarcas();
            telaMarcas.setVisible(true);
        } catch (Exception error) {
            JOptionPane.showMessageDialog(rootPane, error.getMessage(), "Menssagem", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jMenuItemMarcasActionPerformed

    private void jMenuItemModeloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemModeloActionPerformed
        try {
            TelaModelos telaModelos = new TelaModelos();
            telaModelos.setVisible(true);
        } catch (Exception error) {
            JOptionPane.showMessageDialog(rootPane, error.getMessage(), "Menssagem", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jMenuItemModeloActionPerformed

    private void jMenuItemCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemCategoriaActionPerformed
        try {
            TelaCategorias telaCategorias = new TelaCategorias();
            telaCategorias.setVisible(true);
        } catch (Exception error) {
            JOptionPane.showMessageDialog(rootPane, error.getMessage(), "Menssagem", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jMenuItemCategoriaActionPerformed

    private void jMenuItemTIpoDeVeiculoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemTIpoDeVeiculoActionPerformed

        try {
            TelaTipoDeVeiculo telaTipoDeVeiculo = new TelaTipoDeVeiculo();
            telaTipoDeVeiculo.setVisible(true);
        } catch (Exception error) {
            JOptionPane.showMessageDialog(rootPane, error.getMessage(), "Menssagem", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jMenuItemTIpoDeVeiculoActionPerformed

    private void jMenuItemLocacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemLocacaoActionPerformed
        try {
            TelaLocacao telaLocacao = new TelaLocacao();
            telaLocacao.setVisible(true);
        } catch (Exception error) {
            JOptionPane.showMessageDialog(rootPane, error.getMessage(), "Menssagem", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jMenuItemLocacaoActionPerformed

    private void jMenuItemDevolucaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemDevolucaoActionPerformed
        try {
            TelaDevolucoes telaDevolucoes = new TelaDevolucoes();
            telaDevolucoes.setVisible(true);
        } catch (Exception error) {
            JOptionPane.showMessageDialog(rootPane, error.getMessage(), "Menssagem", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jMenuItemDevolucaoActionPerformed

    private void jMenuItemVeiculoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemVeiculoActionPerformed
        try {
            TelaVeiculos telaVeiculos = new TelaVeiculos();
            telaVeiculos.setVisible(true);
        } catch (Exception error) {
            JOptionPane.showMessageDialog(rootPane, error.getMessage(), "Menssagem", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jMenuItemVeiculoActionPerformed

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
            java.util.logging.Logger.getLogger(TelaMenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaMenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaMenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaMenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaMenuPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane jDesktopPanePrincipal;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JMenu jMenuCliente;
    private javax.swing.JMenu jMenuFuncionario;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem15;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItemCadastroFuncionario;
    private javax.swing.JMenuItem jMenuItemCategoria;
    private javax.swing.JMenuItem jMenuItemDesenvolvedores;
    private javax.swing.JMenuItem jMenuItemDevolucao;
    private javax.swing.JMenuItem jMenuItemLocacao;
    private javax.swing.JMenuItem jMenuItemLoginFuncionario;
    private javax.swing.JMenuItem jMenuItemMarcas;
    private javax.swing.JMenuItem jMenuItemModelo;
    private javax.swing.JMenuItem jMenuItemMotorista;
    private javax.swing.JMenuItem jMenuItemPessoaFisica;
    private javax.swing.JMenuItem jMenuItemPessoaJuridica;
    private javax.swing.JMenuItem jMenuItemSair;
    private javax.swing.JMenuItem jMenuItemTIpoDeVeiculo;
    private javax.swing.JMenuItem jMenuItemVeiculo;
    private javax.swing.JMenu jMenuLocacao;
    private javax.swing.JMenu jMenuVeiculo;
    // End of variables declaration//GEN-END:variables
}
