/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.advance.java.server.command;

import com.advance.java.server.socket.PortSession;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;

/**
 *
 * @author User
 */
public class EchoCommand implements Command {
    public static final String TAG = "ec";

    PortSession session = null;

    public EchoCommand(PortSession session){
        this.session = session;
    }


    @Override
    public void execute() throws IOException {
        session.out.print("Start to Echo: ");
        String inputStr = "";
        while ((inputStr = session.in.readLine()) != null) {
            session.out.println("Echo: "+inputStr);
        }
    }

    @Override
    public String getName() {
        return session.getString("Echo");
    }

    @Override
    public String getTag() {
        return TAG;
    }

    @Override
    public String getDescription() {
        return session.getString("EchoDesc");
    }

    @Override
    public String getShortDescription() {
        return session.getString("EchoSDesc");
    }


}
