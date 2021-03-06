/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Conexao.BancoDeDados;
import Controle.ControleSenha;
import Objetos.Senha;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.TimerTask;
import javax.swing.JLabel;
import javax.swing.Timer;

/**
 *
 * @author lvsm9711081
 */

//sistema inicial
public class Main extends javax.swing.JFrame{
    // Declaração inicial
    /*
    
    conex = REFERENCIA a classe BancoDeDados onde é feita toda conexão com o banco
    controle = REFERENCIA a classe ControleDeSenha onde é feita todo o controle das senhas
    FUTURAMENTE controle só ira receber OBJETO
    senhaU = REFERENCIA a classe Senha UNIVERSAL para auxilio
    geral = Array para RETORNO de busca
    
    */
    BancoDeDados conex = new BancoDeDados();
    ControleSenha controle = new ControleSenha();
    Senha senhaU = new Senha();
    ArrayList<Senha> geral = new ArrayList<Senha>();
    java.util.Timer timer = new java.util.Timer("reload");
         

    /**
     * Creates new form Main
     */
    public Main() {
        // INIT
        initComponents(); 
        // Não deixa aumentar / diminuir tamanho da tela
        this.setResizable(false);
        this.setTitle("SiS - Sistema de Senhas v1.0.2");
        
        //Timer de renovação de ciclo,  verificação do ultimo lançamento de chamada na db
        
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                int atual = controle.atual().getSenha();
                String descAtual = controle.atual().getDesc();
                senha.setText("Senha: "+ atual +" Descrição: " + descAtual);
            }
        };
        // duracao curta pra rapida verificação
        long duration = 200L;
        long delay= 50L;
        timer.schedule(task, delay,duration);
        
    }
    public void run(){
            
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mChamar = new javax.swing.JButton();
        senha = new javax.swing.JLabel();
        add = new javax.swing.JButton();
        reset = new javax.swing.JButton();
        painel = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        mChamar.setText("Chamar");
        mChamar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mChamarActionPerformed(evt);
            }
        });

        add.setText("Adicionar");
        add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addActionPerformed(evt);
            }
        });

        reset.setText("Resetar");
        reset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetActionPerformed(evt);
            }
        });

        painel.setText("Painel");
        painel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                painelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(senha, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(mChamar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(add, javax.swing.GroupLayout.DEFAULT_SIZE, 395, Short.MAX_VALUE)
                    .addComponent(reset, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(painel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(senha, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(mChamar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(add)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(reset)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(painel)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void mChamarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mChamarActionPerformed
        // TODO add your handling code here:
        //Botão CHAMAR, abre o chamar senha e fecha o Main
        new ChamarSenha().setVisible(true);
        this.dispose();
        timer.cancel();
    }//GEN-LAST:event_mChamarActionPerformed

    private void addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addActionPerformed
        // TODO add your handling code here:
        //Botão ADD abre adicionarSenha e fecha Main
        new AdicionarSenha().setVisible(true);
        this.dispose();
        timer.cancel();
    }//GEN-LAST:event_addActionPerformed

    private void resetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetActionPerformed
        // TODO add your handling code here:
        controle.Resetar();
        //Botão RESET
    }//GEN-LAST:event_resetActionPerformed

    private void painelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_painelActionPerformed
        // TODO add your handling code here:
        this.dispose();
        new Painel().setVisible(true);
        timer.cancel();
        //Botão PAINEL
    }//GEN-LAST:event_painelActionPerformed

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
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            
            public void run() {
                new Main().setVisible(true);
                
                
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton add;
    private javax.swing.JButton mChamar;
    private javax.swing.JButton painel;
    private javax.swing.JButton reset;
    private javax.swing.JLabel senha;
    // End of variables declaration//GEN-END:variables
}
