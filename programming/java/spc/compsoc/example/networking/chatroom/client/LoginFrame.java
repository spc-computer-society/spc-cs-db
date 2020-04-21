/*
 *     Comp-Soc-DB
 *     Copyright (C) 2020  Colin Chow
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package spc.compsoc.networking.chatroom.client;

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
