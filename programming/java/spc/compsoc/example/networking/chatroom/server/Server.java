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
package spc.compsoc.networking.chatroom.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class Server {
    private static final List<Socket> toSend = new ArrayList<>();
    private static final List<String> output = new ArrayList<>();
    private static final AtomicBoolean locks = new AtomicBoolean(false);
    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(18823);
        Thread sendOut = new Thread(() ->{
            while(true){
                List<String> snapshot = new ArrayList<>(output);
                Iterator<Socket> iter = toSend.listIterator();
                while(iter.hasNext()){
                    Socket next = iter.next();
                    if(next.isClosed()){
                        iter.remove();
                        continue;
                    }
                    for(String str : snapshot){
                        try {
                            next.getOutputStream().write(str.concat("\n").getBytes(StandardCharsets.UTF_8));
                            next.getOutputStream().flush();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
                if(locks.get()) {
                    output.clear();
                    locks.set(false);
                }
            }
        });
        sendOut.start();
        while(true){
            Socket s = ss.accept();
            toSend.add(s);
            System.out.println(s.getInetAddress());
            Thread t = new Thread(() ->{
                try(BufferedReader read = new BufferedReader(new InputStreamReader(s.getInputStream(), StandardCharsets.UTF_8))){
                    String str;
                    while(!s.isClosed() && ((str = read.readLine()) != null)){
                        output.add(str);
                        locks.set(true);
                    }
                }catch(IOException e){
                    e.printStackTrace();
                }
            });
            t.start();
        }
    }
}
