package com.frame;

import com.socket.SocketServer;
import java.awt.Color;
import java.io.File;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class ServerFrame extends javax.swing.JFrame {

    public SocketServer server;
    public Thread serverThread;
    public String filePath; 
    public JFileChooser busca;

    public ServerFrame() {
        initComponents();
        tfCaminhoDB.setEditable(false);
        tfCaminhoDB.setBackground(Color.WHITE);
        busca = new JFileChooser(".");
        
        try {
            tfEndereco.setText(""+InetAddress.getLocalHost().getHostAddress());
        } catch (UnknownHostException ex) {
            Logger.getLogger(ServerFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    public boolean isWin32() {
        return System.getProperty("os.name").startsWith("Windows");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btIniciarServer = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tfEventos = new javax.swing.JTextArea();
        tfCaminhoDB = new javax.swing.JTextField();
        btProcurarData = new javax.swing.JButton();
        tfEndereco = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        tfPorta = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("JChat - SERVER");

        btIniciarServer.setText("INIICIAR");
        btIniciarServer.setEnabled(false);
        btIniciarServer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btIniciarServerActionPerformed(evt);
            }
        });

        tfEventos.setEditable(false);
        tfEventos.setColumns(20);
        tfEventos.setFont(new java.awt.Font("Consolas", 0, 12)); // NOI18N
        tfEventos.setLineWrap(true);
        tfEventos.setRows(5);
        tfEventos.setWrapStyleWord(true);
        jScrollPane1.setViewportView(tfEventos);

        btProcurarData.setText(" Data XML :");
        btProcurarData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btProcurarDataActionPerformed(evt);
            }
        });

        tfEndereco.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tfEndereco.setText("localhost");
        tfEndereco.setMargin(new java.awt.Insets(2, 8, 2, 2));

        jLabel1.setText(" Endereço (IP) :");

        jLabel2.setText("Porta (I/O) :");

        tfPorta.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tfPorta.setText("13000");
        tfPorta.setMargin(new java.awt.Insets(2, 8, 2, 2));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btProcurarData, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(tfEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(tfPorta, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(tfCaminhoDB, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btIniciarServer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(0, 9, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(tfEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(tfPorta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfCaminhoDB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btProcurarData)
                    .addComponent(btIniciarServer))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 254, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btIniciarServerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btIniciarServerActionPerformed

        server = new SocketServer(this);
        btIniciarServer.setEnabled(false);
        btProcurarData.setEnabled(false);
        
    }//GEN-LAST:event_btIniciarServerActionPerformed

    public void RetryStart(int port) {
        
        if (server != null) {
            server.stop();
        }
        
        server = new SocketServer(this, port);
    }

    private void btProcurarDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btProcurarDataActionPerformed

        busca.showDialog(this, "Selecione");        
        
        File file = busca.getSelectedFile();

        if (file != null) {
            filePath = file.getPath();
            
            if (this.isWin32()) {
                filePath = filePath.replace("\\", "/");
            }

            tfCaminhoDB.setText(filePath);
            btIniciarServer.setEnabled(true);
        }
    }//GEN-LAST:event_btProcurarDataActionPerformed

    public static void main(String args[]) {

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "ERRO1 ao aplicar Look & Feel");
        } catch (InstantiationException ex) {
            JOptionPane.showMessageDialog(null, "ERRO2 ao aplicar Look & Feel");
        } catch (IllegalAccessException ex) {
            JOptionPane.showMessageDialog(null, "ERRO ao aplicar Look & Feel");
        } catch (UnsupportedLookAndFeelException ex) {
            JOptionPane.showMessageDialog(null, "ERRO ao aplicar Look & Feel");
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ServerFrame().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btIniciarServer;
    private javax.swing.JButton btProcurarData;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField tfCaminhoDB;
    private javax.swing.JTextField tfEndereco;
    public javax.swing.JTextArea tfEventos;
    private javax.swing.JTextField tfPorta;
    // End of variables declaration//GEN-END:variables
}
