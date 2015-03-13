/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conferencing;

import java.awt.Font;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrator
 */
public class DC_UI extends javax.swing.JFrame {

    /*Global Variables*/
    public Font ui_font;
    public String nick_error1;
    public String nick_error2;
    public String nick_error3;
    public String nick_error4;
    /**
     * Creates new form DC_UI
     */
    String ServerIP;
    public DC_UI() {
        this.nick_error1 = "* No Nickname given";
        this.nick_error2 = "* Nickname should be within 25 characters";
        this.nick_error3 = "* Nickname should be alphanumeric";
        this.nick_error4 = "* Nickname already taken. Please choose a different username";
        /*Initialize global font here*/
        ui_font = new Font("Tahoma",Font.PLAIN,12);
        initComponents();
        nick_error_label.setText("");
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        close_btn = new javax.swing.JButton();
        nickname_text_field = new javax.swing.JTextField();
        nickname_text_label = new javax.swing.JLabel();
        submit_btn = new javax.swing.JButton();
        nick_error_label = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        close_btn.setText("x");
        close_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                close_btnActionPerformed(evt);
            }
        });

        nickname_text_label.setFont(ui_font);
        nickname_text_label.setText("Nickname");

        submit_btn.setFont(ui_font);
        submit_btn.setText("Submit");
        submit_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submit_btnActionPerformed(evt);
            }
        });

        nick_error_label.setFont(ui_font);
        nick_error_label.setForeground(new java.awt.Color(255, 0, 0));
        nick_error_label.setText("* Error");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(close_btn))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(160, 160, 160)
                        .addComponent(nickname_text_label, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(nick_error_label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(nickname_text_field, javax.swing.GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(269, 269, 269)
                        .addComponent(submit_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(215, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(close_btn)
                .addGap(129, 129, 129)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nickname_text_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nickname_text_label))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nick_error_label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(submit_btn)
                .addGap(0, 233, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void close_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_close_btnActionPerformed
        System.exit(0);
    }//GEN-LAST:event_close_btnActionPerformed

    private void submit_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submit_btnActionPerformed
        
        String nick = this.nickname_text_field.getText();
        //System.out.println("Nick : " + nick + ";");
        if(nick.isEmpty())
        {
            //System.out.println("No NickName given!");
            this.nick_error_label.setText(this.nick_error1);
            return;
        }
        else if(nick.length() > 25)
        {
            //nickname should be less than 25 characters
            this.nick_error_label.setText(this.nick_error2);
            return;
        }
        else if(!nick.matches("[A-Za-z0-9]+"))
        {
            //nickname should be alpha numeric
            this.nick_error_label.setText(this.nick_error3);
            return;
        }
        try {
            Socket soc = new Socket("10.141.212.64",8080);
            BufferedReader stdIn = new BufferedReader(new InputStreamReader(soc.getInputStream()));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(soc.getOutputStream()));
            bw.write("N" + nick + "\n" );
            bw.flush();
            String line = stdIn.readLine();
            if(line.equals("NAK"))
            {
                // Reject
                this.nick_error_label.setText(this.nick_error4);
                return;
            }
            else
            {
                // Accepted
                this.nick_error_label.setText("");
                // move to next UI page
            }
        } catch (IOException ex) {
            Logger.getLogger(DC_UI.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Registered!");
    }//GEN-LAST:event_submit_btnActionPerformed

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
            java.util.logging.Logger.getLogger(DC_UI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DC_UI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DC_UI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DC_UI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DC_UI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton close_btn;
    private javax.swing.JLabel nick_error_label;
    private javax.swing.JTextField nickname_text_field;
    private javax.swing.JLabel nickname_text_label;
    private javax.swing.JButton submit_btn;
    // End of variables declaration//GEN-END:variables
}
