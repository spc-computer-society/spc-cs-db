package com.colin.networking.chatroom.client;

import javax.swing.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

public class Client {
    public static void main(String[] args){
        try {
            UIManager.setLookAndFeel(new NimbusLookAndFeel());
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        new LoginFrame();
    }
}
