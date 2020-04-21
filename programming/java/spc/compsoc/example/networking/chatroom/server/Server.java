package com.colin.networking.chatroom.server;

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
