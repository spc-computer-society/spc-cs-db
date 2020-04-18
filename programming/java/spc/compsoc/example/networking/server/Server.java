package spc.compsoc.example.networking.server;

import javax.swing.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

public class Server {
    public static void main(String[] args){
        try{
            UIManager.setLookAndFeel(new NimbusLookAndFeel());
        }catch (UnsupportedLookAndFeelException ulafe){
            ulafe.printStackTrace();
        }
        new ServerFrame();
    }
}
