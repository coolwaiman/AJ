/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.advance.java.server.socket;

import com.advance.java.server.command.Command;
import com.advance.java.server.command.EchoCommand;
import com.advance.java.server.command.LoginCommand;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

/**
 *
 * @author User
 */
public class ClientHandlerServer implements Runnable {

    private static final String host = "127.0.0.1";
    private int port = -1;
    private ServerSocket ss;

    public ClientHandlerServer(int port) throws IOException {
        this.port = port;
        ss = new ServerSocket(port);
        ConnectionServer.CLIENT_COUNTER++;
    }

    public void accept() throws IOException {
        System.out.println("Accepting connections on port " + ss.getLocalPort());
        Socket ms = ss.accept();
        try {
            DataInputStream input;
            PrintStream output;
            input = new DataInputStream(ms.getInputStream());
            output = new PrintStream(ms.getOutputStream(),true, "UTF-8");
            
            Command c = new LoginCommand(input, output);
            c.execute();
        }finally{
            System.out.println("Done accepting");
            ms.close();
            ss.close();
        }
    }
    @Override
    public void run() {
        try {
            accept();
        } catch (IOException ex) {
            //ex.printStackTrace();
        }finally{
            System.out.println("released port "+port);
            ConnectionServer.CLIENT_COUNTER--;
        }
    }

}
