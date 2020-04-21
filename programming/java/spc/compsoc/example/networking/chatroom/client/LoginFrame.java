package com.colin.networking.chatroom.client;

import javax.swing.*;
import java.io.IOException;

public class LoginFrame extends JFrame {
    public LoginFrame(){
        super("Login");
        setLayout(new BoxLayout(getContentPane(),BoxLayout.Y_AXIS));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(new JLabel("Welcome to Winux Zoomscord Teams! Please input your screen name:"));
        JPanel login = new JPanel();
        JTextField nameIn = new JTextField(20);
        JButton submit = new JButton("Submit");
        submit.addActionListener(ae ->{
            try {
                ChatFrame chatFrame = new ChatFrame(nameIn.getText());
            } catch (IOException e) {
                e.printStackTrace();
            }
            dispose();
        });
        login.add(nameIn);
        login.add(submit);
        add(login);
        pack();
        setVisible(true);
    }
}
