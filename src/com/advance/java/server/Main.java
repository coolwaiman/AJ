/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.advance.java.server;

import com.advance.java.server.socket.ConnectionServer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author User
 */
public class Main {
    public static void main(String[] args){
        Thread t = null;
        try{
            t = new Thread(new ConnectionServer());
        t.start();
        }catch(IOException e){
        e.printStackTrace();
        }
        try {
            Thread.sleep(500);
        } catch (InterruptedException ex) {
        }
        System.out.println("Press enter to quit...");
        try {
            System.in.read();
        } catch (IOException ex) {
        }
        System.out.println("Quiting...");
        if(t!=null){
            t.interrupt();
        }
    }    
}
