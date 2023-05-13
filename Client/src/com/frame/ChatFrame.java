package com.frame;

import com.socket.History;
import com.socket.Message;
import com.socket.SocketClient;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.util.logging.Level;
import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;

public class ChatFrame extends javax.swing.JFrame {

    public SocketClient client;
    public int port;
    public String serverAddr, user, pass;
    public Thread clientThread;
    public DefaultListModel model;
    public File file;
    public String historyFile = "Data.xml";
    public HistoryFrame historia;
    public History hist;

    public ChatFrame() {
        initComponents();
        model.addElement("All");
        listaConects.setSelectedIndex(0);

        this.addWindowListener(new WindowListener() {

            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    client.send(new Message("message", user, ".bye", "SERVER"));
                    clientThread.stop();
                } catch (Exception ex) {
                }
            }

            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
            }

            @Override
            public void windowIconified(WindowEvent e) {
            }

            @Override
            public void windowDeiconified(WindowEvent e) {
            }

            @Override
            public void windowActivated(WindowEvent e) {
            }

            @Override
            public void windowDeactivated(WindowEvent e) {
            }
        });

        hist = new History(historyFile);

    }

    public boolean isWin32() {
        return System.getProperty("os.name").startsWith("Windows");
    }

    public void enviarMSG() {

        String msg = tfAreaMsg.getText();
        String target = listaConects.getSelectedValue().toString();

        if (!msg.isEmpty() && !target.isEmpty()) {
            tfAreaMsg.setText("");
            client.send(new Message("message", user, msg, target));
        }
    }

    public void importarHST() {

        JFileChooser jf = new JFileChooser(".");
        jf.showDialog(this, "Select File");

        if (!jf.getSelectedFile().getPath().isEmpty()) {
            historyFile = jf.getSelectedFile().getPath();

            if (this.isWin32()) {
                historyFile = historyFile.replace("/", "\\");
            }

//            tfXMLHistoria.setText(historyFile);
//            tfXMLHistoria.setEditable(false);
//            btBuscaHistoria.setEnabled(false);
//            btAbriHistoria.setEnabled(true);
            hist = new History(historyFile);

            historia = new HistoryFrame(hist);

//            historia.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
//            historia.setVisible(false);
//            
            historia.setLocation(this.getLocation());
            historia.setVisible(true);

        }
    }

    public final void aplicarTema(String skin) {

        try {
            javax.swing.UIManager.setLookAndFeel(skin);
            javax.swing.SwingUtilities.updateComponentTreeUI(this);
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ChatFrame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ChatFrame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ChatFrame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ChatFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        btLogoff = new javax.swing.JButton();
        tfSenha = new javax.swing.JPasswordField();
        btLogin = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        tf_IPSERVER = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        tf_PORTA = new javax.swing.JTextField();
        btConectar = new javax.swing.JButton();
        tfUsuario = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel6 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tfAreaChat = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        listaConects = new javax.swing.JList();
        jLabel5 = new javax.swing.JLabel();
        btEnviar = new javax.swing.JButton();
        jTextField5 = new javax.swing.JTextField();
        btAnexar = new javax.swing.JButton();
        btEncaminhar = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tfAreaMsg = new javax.swing.JTextArea();
        checkEnviar = new javax.swing.JCheckBox();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("JChat - CLIENTE");
        setMinimumSize(new java.awt.Dimension(587, 471));
        setResizable(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setLayout(null);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Usuário :");
        jPanel1.add(jLabel4);
        jLabel4.setBounds(210, 150, 60, 14);

        btLogoff.setText("Logoff");
        btLogoff.setEnabled(false);
        btLogoff.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btLogoffActionPerformed(evt);
            }
        });
        jPanel1.add(btLogoff);
        btLogoff.setBounds(288, 250, 63, 23);

        tfSenha.setEnabled(false);
        jPanel1.add(tfSenha);
        tfSenha.setBounds(210, 220, 142, 20);

        btLogin.setText("Login");
        btLogin.setEnabled(false);
        btLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btLoginActionPerformed(evt);
            }
        });
        jPanel1.add(btLogin);
        btLogin.setBounds(210, 250, 70, 23);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("IP Servidor :");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(180, 20, 70, 20);

        tf_IPSERVER.setText("localhost");
        tf_IPSERVER.setMargin(new java.awt.Insets(2, 6, 2, 2));
        jPanel1.add(tf_IPSERVER);
        tf_IPSERVER.setBounds(180, 45, 139, 25);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Porta :");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(330, 20, 37, 20);

        tf_PORTA.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tf_PORTA.setText("13000");
        jPanel1.add(tf_PORTA);
        tf_PORTA.setBounds(320, 45, 80, 25);

        btConectar.setBackground(new java.awt.Color(0, 102, 51));
        btConectar.setForeground(new java.awt.Color(255, 255, 255));
        btConectar.setText("CONECTAR");
        btConectar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btConectarActionPerformed(evt);
            }
        });
        jPanel1.add(btConectar);
        btConectar.setBounds(180, 80, 220, 23);

        tfUsuario.setEnabled(false);
        jPanel1.add(tfUsuario);
        tfUsuario.setBounds(210, 170, 142, 20);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Senha :");
        jPanel1.add(jLabel3);
        jLabel3.setBounds(210, 200, 50, 18);
        jPanel1.add(jSeparator1);
        jSeparator1.setBounds(0, 120, 594, 2);
        jPanel1.add(jSeparator2);
        jSeparator2.setBounds(2, 310, 594, 2);

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/src/back2.png"))); // NOI18N
        jPanel1.add(jLabel6);
        jLabel6.setBounds(0, 0, 580, 420);

        jTabbedPane1.addTab("Conectar", jPanel1);

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        tfAreaChat.setEditable(false);
        tfAreaChat.setColumns(20);
        tfAreaChat.setFont(new java.awt.Font("Microsoft JhengHei", 0, 12)); // NOI18N
        tfAreaChat.setLineWrap(true);
        tfAreaChat.setRows(5);
        tfAreaChat.setWrapStyleWord(true);
        jScrollPane1.setViewportView(tfAreaChat);

        listaConects.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Conectados", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));
        listaConects.setModel((model = new DefaultListModel()));
        jScrollPane2.setViewportView(listaConects);

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/src/profle.png"))); // NOI18N
        jLabel5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        btEnviar.setBackground(new java.awt.Color(0, 153, 153));
        btEnviar.setText("ENVIAR");
        btEnviar.setEnabled(false);
        btEnviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btEnviarActionPerformed(evt);
            }
        });

        btAnexar.setText("Anexar");
        btAnexar.setEnabled(false);
        btAnexar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAnexarActionPerformed(evt);
            }
        });

        btEncaminhar.setText("Upload");
        btEncaminhar.setEnabled(false);
        btEncaminhar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btEncaminharActionPerformed(evt);
            }
        });

        tfAreaMsg.setColumns(20);
        tfAreaMsg.setFont(new java.awt.Font("Microsoft JhengHei", 0, 13)); // NOI18N
        tfAreaMsg.setLineWrap(true);
        tfAreaMsg.setRows(5);
        tfAreaMsg.setWrapStyleWord(true);
        tfAreaMsg.setMargin(new java.awt.Insets(2, 4, 2, 2));
        tfAreaMsg.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tfAreaMsgKeyPressed(evt);
            }
        });
        jScrollPane3.setViewportView(tfAreaMsg);

        checkEnviar.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        checkEnviar.setSelected(true);
        checkEnviar.setText("Enviar com ENTER");
        checkEnviar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jScrollPane1))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 363, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jTextField5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btAnexar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btEncaminhar)
                                .addGap(2, 2, 2)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(checkEnviar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btEnviar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE))
                        .addContainerGap())
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 271, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane3)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btEnviar, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(checkEnviar)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btAnexar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btEncaminhar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(23, 23, 23))
        );

        jTabbedPane1.addTab("Chat", jPanel2);

        jMenu1.setText("Arquivo");

        jMenuItem2.setText("Importar História");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuItem1.setText("Sair");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Temas (Skins)");

        jMenuItem4.setText("McWin");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem4);

        jMenuItem3.setText("Aero");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem3);

        jMenuItem5.setText("Smart");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem5);

        jMenuItem6.setText("Graphite");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem6);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btConectarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btConectarActionPerformed

        serverAddr = tf_IPSERVER.getText();
        port = Integer.parseInt(tf_PORTA.getText());

        if (!serverAddr.isEmpty() && !tf_PORTA.getText().isEmpty()) {
            try {
                client = new SocketClient(this);
                clientThread = new Thread(client);
                clientThread.start();
                client.send(new Message("test", "testUser", "testContent", "SERVER"));
            } catch (Exception ex) {
                tfAreaChat.append("[Application > Me] : Server not found\n");
            }
        }
    }//GEN-LAST:event_btConectarActionPerformed

    private void btLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btLoginActionPerformed

        user = tfUsuario.getText();
        pass = tfSenha.getText();

        if (!user.isEmpty() && !pass.isEmpty()) {
            client.send(new Message("login", user, pass, "SERVER"));
        }
    }//GEN-LAST:event_btLoginActionPerformed

    private void btEnviarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btEnviarActionPerformed

        enviarMSG();

    }//GEN-LAST:event_btEnviarActionPerformed

    private void btLogoffActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btLogoffActionPerformed

        user = tfUsuario.getText();
        pass = tfSenha.getText();

        if (!user.isEmpty() && !pass.isEmpty()) {
            client.send(new Message("signup", user, pass, "SERVER"));
        }
    }//GEN-LAST:event_btLogoffActionPerformed

    private void btAnexarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAnexarActionPerformed

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.showDialog(this, "Select File");
        file = fileChooser.getSelectedFile();

        if (file != null) {
            if (!file.getName().isEmpty()) {
                btEncaminhar.setEnabled(true);
                String str;

                if (jTextField5.getText().length() > 30) {
                    String t = file.getPath();
                    str = t.substring(0, 20) + " [...] " + t.substring(t.length() - 20, t.length());
                } else {
                    str = file.getPath();
                }
                jTextField5.setText(str);
            }
        }
    }//GEN-LAST:event_btAnexarActionPerformed

    private void btEncaminharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btEncaminharActionPerformed
        long size = file.length();
        if (size < 120 * 1024 * 1024) {
            client.send(new Message("upload_req", user, file.getName(), listaConects.getSelectedValue().toString()));
        } else {
            tfAreaChat.append("[Application > Me] : File is size too large\n");
        }
    }//GEN-LAST:event_btEncaminharActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void tfAreaMsgKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfAreaMsgKeyPressed
        // TODO add your handling code here:

        if (evt.getKeyCode() == KeyEvent.VK_ENTER && checkEnviar.isSelected()) {
            enviarMSG();
        }

    }//GEN-LAST:event_tfAreaMsgKeyPressed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:

        importarHST();

    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        // TODO add your handling code here:

        aplicarTema("com.jtattoo.plaf.mcwin.McWinLookAndFeel");

    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        // TODO add your handling code here:

        aplicarTema("com.jtattoo.plaf.aero.AeroLookAndFeel");
        
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        // TODO add your handling code here:
         aplicarTema("com.jtattoo.plaf.smart.SmartLookAndFeel");
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        // TODO add your handling code here:
         aplicarTema("com.jtattoo.plaf.graphite.GraphiteLookAndFeel");
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    public static void main(String args[]) {

        /*Aplication of Theme Classic Java
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ChatFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ChatFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ChatFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ChatFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }*/
        
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ChatFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btAnexar;
    public javax.swing.JButton btConectar;
    public javax.swing.JButton btEncaminhar;
    public javax.swing.JButton btEnviar;
    public javax.swing.JButton btLogin;
    public javax.swing.JButton btLogoff;
    private javax.swing.JCheckBox checkEnviar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTabbedPane jTabbedPane1;
    public javax.swing.JTextField jTextField5;
    public javax.swing.JList listaConects;
    public javax.swing.JTextArea tfAreaChat;
    private javax.swing.JTextArea tfAreaMsg;
    public javax.swing.JPasswordField tfSenha;
    public javax.swing.JTextField tfUsuario;
    public javax.swing.JTextField tf_IPSERVER;
    public javax.swing.JTextField tf_PORTA;
    // End of variables declaration//GEN-END:variables
}
