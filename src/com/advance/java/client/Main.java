/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.advance.java.client;

import com.advance.java.client.socket.ConnectionClient;
import com.advance.java.client.socket.ConnectionObserver;
import com.advance.java.client.socket.RequestingClient;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author User
 */
public class Main {
    public static void main(String[] args) throws IOException{
        ConnectionClient initcc = new ConnectionClient(7000);
        initcc.registerObserver(new ConnectionObserver() {
            @Override
            public void update(String msg) {
                System.out.println("received port "+msg);
                Thread t = new Thread(new RequestingClient(Integer.parseInt(msg)));
                t.start();
            }
        });
        Thread t = new Thread(initcc);
        t.start();
    }    
}
