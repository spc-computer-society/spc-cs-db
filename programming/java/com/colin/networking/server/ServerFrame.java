package com.colin.networking.server;

import javax.swing.*;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Paths;
import java.time.OffsetDateTime;

public class ServerFrame extends JFrame {
    private Thread t;
    private ServerSocket ss;
    public ServerFrame(){
        super("Server Panel");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BoxLayout(getContentPane(),BoxLayout.Y_AXIS));
        JPanel service = new JPanel();
        JButton start = new JButton("Start");
        JTextField portIn = new JTextField(6);
        service.add(start);
        service.add(new JLabel("Port:"));
        service.add(portIn);
        add(service);
        if(!Paths.get("./downloaded").toFile().exists()){
            Paths.get("./downloaded").toFile().mkdir();
        }
        t = new Thread(() ->{
            System.out.println("Starting!");
            try {
                ss = new ServerSocket(Integer.parseInt(portIn.getText()));
            } catch (IOException e) {
                e.printStackTrace();
            }
            while(true){
                try {
                    Socket in = ss.accept();
                    InputStream read = in.getInputStream();
                    int inC = read.read();
                    int inS;
                    StringBuilder sb = new StringBuilder();
                    int i = 0;
                    while(inC > i && (inS = read.read()) != -1){
                        sb.append((char) inS);
                        i++;
                    }
                    String s = sb.toString();
                    System.out.println(s);
                    int ans = JOptionPane.showConfirmDialog(null,"Accept this connection: " + in.getInetAddress().toString() + ", which is sending the file " + s + "?","Accept a connection",JOptionPane.YES_NO_OPTION);
                    if(ans == JOptionPane.YES_OPTION){
                        File f = Paths.get("./downloaded/" + s).toFile();
                        if(f.exists()){
                            s += OffsetDateTime.now().toString();
                        }
                        FileOutputStream fos = new FileOutputStream("./downloaded/" + s);
                        byte[] buffer = new byte[8 * 1024];
                        int bytesRead;
                        while ((bytesRead = read.read(buffer)) != -1) {
                            fos.write(buffer, 0, bytesRead);
                            System.out.println(bytesRead);
                        }
                        read.close();
                        fos.close();
                        System.out.println("Done!");
                    }
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        start.addActionListener(ae ->{
            t.start();
        });
        pack();
        setVisible(true);
    }
}
