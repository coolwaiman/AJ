/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.advance.java.client.socket;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author User
 */
public class ConnectionClient implements Runnable, ConnectionSubject{
    private static final String host = "127.0.0.1";
    private int port = 7000;
    private Socket socket = null;
    
    private List<ConnectionObserver> lstObservers = new LinkedList<ConnectionObserver>();
    
    public ConnectionClient(int port){
        this.port = port;
    }
    @Override
    public void run(){
        try
        {
            socket = new Socket( host, port );
            InputStreamReader input = null;
            OutputStreamWriter output = null;
            
            try
            {
                input = new InputStreamReader( socket.getInputStream() );
                output = new OutputStreamWriter( socket.getOutputStream() );
                Scanner scanner = new Scanner(socket.getInputStream());
                while ( scanner.hasNext() )
                {
                    notifyObservers(scanner.next());
                    break;
                }
            }
            catch ( IOException e )
            {
            }
            finally 
            {
                if ( input != null )
                    input.close();
                if ( output != null )
                    output.close();
            }
        }
        catch ( IOException e )
        {
            System.out.println("Server not available");
            //e.printStackTrace();
        }
        finally
        {
            if ( socket != null ){
                try{
                    socket.close();
                }catch(IOException ex){
                    ex.printStackTrace();
                }finally{}
            }
        }
    }

    @Override
    public void registerObserver(ConnectionObserver observer) {
        lstObservers.add(observer);
    }

    @Override
    public void removeObserver(ConnectionObserver observer) {
        lstObservers.remove(observer);
    }

    @Override
    public void notifyObservers(String msg) {
        for(ConnectionObserver observer: lstObservers){
            observer.update(msg);
        }
    }
}
