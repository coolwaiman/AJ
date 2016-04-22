/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.advance.java.server.socket;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.Random;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author User
 */
public class ConnectionServer implements Runnable {

    private static final ExecutorService clientServices = Executors.newCachedThreadPool();
    private static final int INIT_PORT = 7000;
    private static final int MIN_CLIENT_PORT = 7001;
    private static final int MAX_CLIENT_PORT = 7099;
    private static final int CLIENT_NUMBER = 99;
    protected static int CLIENT_COUNTER = 0;
    private static int Current_Port = 7000;
    //private static final Executor executor = Executors.newFixedThreadPool(CLIENT_NUMBER+1);  
    private ServerSocket ss;

    private static int getNextPort() throws NoAvailableSocketException {
        if (CLIENT_COUNTER > CLIENT_NUMBER) {
            throw new NoAvailableSocketException();
        }
        int port = Current_Port + 1;
        if (port > MAX_CLIENT_PORT) {
            port = MIN_CLIENT_PORT;
        }
        while (!available(port)) {
            port++;
            if (port > MAX_CLIENT_PORT) {
                port = MIN_CLIENT_PORT;
            }
        }
        return port;
    }

    public static boolean available(int port) {
        if (port < MIN_CLIENT_PORT || port > MAX_CLIENT_PORT) {
            throw new IllegalArgumentException("Invalid start port: " + port);
        }
        ServerSocket ss = null;
        try {
            ss = new ServerSocket(port);
            ss.setReuseAddress(true);
            return true;
        } catch (IOException e) {
        } finally {
            if (ss != null) {
                try {
                    ss.close();
                } catch (IOException e) {
                    /* pass */
                }
            }
        }
        return false;
    }

    public ConnectionServer() throws IOException {
        ss = new ServerSocket(INIT_PORT);
        ss.setSoTimeout(250);
    }

    public void accept() throws IOException {
        System.out.println("Accepting connections on port " + ss.getLocalPort());
        while (!Thread.interrupted()) {
            try (Socket sock = ss.accept();
                    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(sock.getOutputStream()))) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException ex) {
                    return;
                }
                try {
                    int port = getNextPort();
                    clientServices.execute(new ClientHandlerServer(port));
                    bw.write(""+port);
                } catch (NoAvailableSocketException ex) {
                    bw.write("-1");
                }
            } catch (SocketTimeoutException ste) {
                //timeout every .25 seconds to see if interrupted
            }
        }
        System.out.println("Done accepting");
    }

    @Override
    public void run() {
        try {
            accept();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
