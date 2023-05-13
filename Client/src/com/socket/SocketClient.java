package com.socket;

import com.frame.ChatFrame;
import java.io.*;
import java.net.*;
import java.util.Date;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class SocketClient implements Runnable{
    
    public int port;
    public String serverAddr;
    public Socket socket;
    public ChatFrame ui;
    public ObjectInputStream In;
    public ObjectOutputStream Out;
    public History hist;
    
    public SocketClient(ChatFrame frame) throws IOException{
        
        this.ui = frame; 
        this.serverAddr = ui.serverAddr; 
        this.port = ui.port;
        
        socket = new Socket(InetAddress.getByName(serverAddr), port);
        Out = new ObjectOutputStream(socket.getOutputStream());

        Out.flush();

        In = new ObjectInputStream(socket.getInputStream());
        
        hist = ui.hist;
    }

    @Override
    public void run() {
        
        boolean status = true;
        
        while(status){
            
            try {
                
                Message msg = (Message) In.readObject();
                System.out.println("Incoming : "+msg.toString());
                
                if(msg.type.equals("message")){
                    if(msg.recipient.equals(ui.user)){
                        ui.tfAreaChat.append("["+msg.sender +" > Me] : " + msg.content + "\n");
                    }
                    else{
                        ui.tfAreaChat.append("["+ msg.sender +" > "+ msg.recipient +"] : " + msg.content + "\n");
                    }
                                            
                    if(!msg.content.equals(".bye") && !msg.sender.equals(ui.user)){
                        String msgTime = (new Date()).toString();
                        
                        try{
                            hist.addMessage(msg, msgTime);
                            DefaultTableModel table = (DefaultTableModel) ui.historia.jTable1.getModel();
                            table.addRow(new Object[]{msg.sender, msg.content, "Me", msgTime});
                        }
                        catch(Exception ex){}  
                    }
                }else if(msg.type.equals("login")){
                    
                    if(msg.content.equals("TRUE")){
                        ui.btLogin.setEnabled(false); 
                        ui.btLogoff.setEnabled(false);                        
                        ui.btEnviar.setEnabled(true); 
                        ui.btAnexar.setEnabled(true);
                        ui.tfAreaChat.append("[SERVER > Me] : Login Successful\n");
                        ui.tfUsuario.setEnabled(false); ui.tfSenha.setEnabled(false);
                    }
                    else{
                        ui.tfAreaChat.append("[SERVER > Me] : Login Failed\n");
                    }
                    
                }else if(msg.type.equals("test")){
                    ui.btConectar.setEnabled(false);
                    ui.btLogin.setEnabled(true); ui.btLogoff.setEnabled(true);
                    ui.tfUsuario.setEnabled(true); ui.tfSenha.setEnabled(true);
                    ui.tf_IPSERVER.setEditable(false); ui.tf_PORTA.setEditable(false);
//                    ui.btBuscaHistoria.setEnabled(true);
                }else if(msg.type.equals("newuser")){
                    if(!msg.content.equals(ui.user)){
                        boolean exists = false;
                        for(int i = 0; i < ui.model.getSize(); i++){
                            if(ui.model.getElementAt(i).equals(msg.content)){
                                exists = true; break;
                            }
                        }
                        if(!exists){ ui.model.addElement(msg.content); }
                    }
                }else if(msg.type.equals("signup")){
                    if(msg.content.equals("TRUE")){
                        ui.btLogin.setEnabled(false); ui.btLogoff.setEnabled(false);
                        ui.btEnviar.setEnabled(true); ui.btAnexar.setEnabled(true);
                        ui.tfAreaChat.append("[SERVER > Me] : Singup Successful\n");
                    }
                    else{
                        ui.tfAreaChat.append("[SERVER > Me] : Signup Failed\n");
                    }
                }else if(msg.type.equals("signout")){
                    if(msg.content.equals(ui.user)){
                        ui.tfAreaChat.append("["+ msg.sender +" > Me] : Bye\n");
                        ui.btConectar.setEnabled(true); ui.btEnviar.setEnabled(false); 
                        ui.tf_IPSERVER.setEditable(true); ui.tf_PORTA.setEditable(true);
                        
                        for(int i = 1; i < ui.model.size(); i++){
                            ui.model.removeElementAt(i);
                        }
                        
                        ui.clientThread.stop();
                    }else{
                        ui.model.removeElement(msg.content);
                        ui.tfAreaChat.append("["+ msg.sender +" > All] : "+ msg.content +" has signed out\n");
                    }
                }else if(msg.type.equals("upload_req")){
                    
                    if(JOptionPane.showConfirmDialog(ui, ("Accept '"+msg.content+"' from "+msg.sender+" ?")) == 0){
                        
                        JFileChooser jf = new JFileChooser();
                        jf.setSelectedFile(new File(msg.content));
                        int returnVal = jf.showSaveDialog(ui);
                       
                        String saveTo = jf.getSelectedFile().getPath();
                        if(saveTo != null && returnVal == JFileChooser.APPROVE_OPTION){
                            Download dwn = new Download(saveTo, ui);
                            Thread t = new Thread(dwn);
                            t.start();
                            //send(new Message("upload_res", (""+InetAddress.getLocalHost().getHostAddress()), (""+dwn.port), msg.sender));
                            send(new Message("upload_res", ui.user, (""+dwn.port), msg.sender));
                        }else{
                            send(new Message("upload_res", ui.user, "NO", msg.sender));
                        }
                    }else{
                        send(new Message("upload_res", ui.user, "NO", msg.sender));
                    }
                }else if(msg.type.equals("upload_res")){
                    if(!msg.content.equals("NO")){
                        int port  = Integer.parseInt(msg.content);
                        String addr = msg.sender;
                        
                        ui.btAnexar.setEnabled(false); ui.btEncaminhar.setEnabled(false);
                        Upload upl = new Upload(addr, port, ui.file, ui);
                        Thread t = new Thread(upl);
                        t.start();
                    }else{
                        ui.tfAreaChat.append("[SERVER > Me] : "+msg.sender+" rejected file request\n");
                    }
                }else{
                    ui.tfAreaChat.append("[SERVER > Me] : Unknown message type\n");
                }
            }
            catch(Exception ex) {
                
                status = false;
                
                ui.tfAreaChat.append("[Application > Me] : Connection Failure\n");
                ui.btConectar.setEnabled(true); ui.tf_IPSERVER.setEditable(true); ui.tf_PORTA.setEditable(true);
                ui.btEnviar.setEnabled(false); ui.btAnexar.setEnabled(false); ui.btAnexar.setEnabled(false);
                
                for(int i = 1; i < ui.model.size(); i++){
                    ui.model.removeElementAt(i);
                }
                
                ui.clientThread.stop();
                
                System.out.println("Exception SocketClient run()");
                ex.printStackTrace();
            }
        }
    }
    
    public void send(Message msg){
        try {
            Out.writeObject(msg);
            Out.flush();
            System.out.println("Outgoing : "+msg.toString());
            
            if(msg.type.equals("message") && !msg.content.equals(".bye")){
                String msgTime = (new Date()).toString();
                try{
                    hist.addMessage(msg, msgTime);               
                    DefaultTableModel table = (DefaultTableModel) ui.historia.jTable1.getModel();
                    table.addRow(new Object[]{"Me", msg.content, msg.recipient, msgTime});
                }
                catch(Exception ex){}
            }
        } 
        catch (IOException ex) {
            System.out.println("Exception SocketClient send()");
        }
    }
    
    public void closeThread(Thread t){
        t = null;
    }
}
