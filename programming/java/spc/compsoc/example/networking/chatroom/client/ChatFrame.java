package com.colin.networking.chatroom.client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class ChatFrame extends JFrame {
    private Socket s = new Socket("61.18.35.95",18823);
    private OutputStream out = s.getOutputStream();
    private InputStream in = s.getInputStream();
    private JTextArea textArea;
    private JScrollPane textScroll;
    private PrintStream ps;
    private boolean isInit = false;
    public ChatFrame(String text) throws IOException{
        super("Chat window");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        textArea = new JTextArea();
        JPanel pane = new JPanel();
        pane.setLayout(new BorderLayout());
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textScroll = new JScrollPane(textArea);
        setLayout(new BorderLayout());
        pane.add(textScroll);
        add(pane,BorderLayout.CENTER);
        ps = new PrintStream(new OutputStream() {
            @Override
            public void write(int b) {
                SwingUtilities.invokeLater(() -> textArea.append(String.valueOf((char) b)));
                //not localizing
            }

            @Override
            public void write(byte[] b) {
                if (b == null) {
                    throw new NullPointerException();
                }
                SwingUtilities.invokeLater(() -> textArea.append(new String(b, StandardCharsets.UTF_8)));
            }

            @Override
            public void write(byte[] b, int off, int len) {
                if (b == null) {
                    throw new NullPointerException();
                } else if ((off < 0) || (off > b.length) || (len < 0) || ((off + len) > b.length) || ((off + len) < 0)) {
                    throw new IndexOutOfBoundsException();
                } else if (len == 0) {
                    return;
                }
                String actual = new String(Arrays.copyOfRange(b, off, off + len), StandardCharsets.UTF_8);
                SwingUtilities.invokeLater(() -> textArea.append(actual));
            }
        });
        JPanel sendP = new JPanel();
        JTextField msg = new JTextField(80);
        JButton send = new JButton("Send");
        sendP.add(msg);
        sendP.add(send);
        add(sendP,BorderLayout.PAGE_END);
        Thread inc = new Thread(() ->{
            try(BufferedReader read = new BufferedReader(new InputStreamReader(in))){
                String str;
                while((str = read.readLine()) != null){
                    ps.println(str);
                    ps.flush();
                }
            }catch(IOException e){
                e.printStackTrace();
            }
        });
        msg.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    send.doClick();
                }
            }
        });
        send.setMnemonic(KeyEvent.VK_ENTER);
        send.addActionListener(ae ->{
            try {
                String output = "[" + text + "]: " + msg.getText();
                out.write(output.concat("\n").getBytes(StandardCharsets.UTF_8));
                msg.setText("");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        Runtime.getRuntime().addShutdownHook(new Thread(() ->{
            try {
                out.write(text.concat(" has left the chat!\n").getBytes(StandardCharsets.UTF_8));
                s.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }));
        pack();
        setVisible(true);
        inc.start();
    }
}
