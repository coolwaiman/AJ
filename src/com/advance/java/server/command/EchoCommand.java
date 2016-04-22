/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.advance.java.server.command;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;

/**
 *
 * @author User
 */
public class EchoCommand implements Command {

    DataInputStream in = null;
    PrintStream out = null;

    public EchoCommand(DataInputStream in, PrintStream out) {
        this.in = in;
        this.out = out;
    }

    @Override
    public void execute() throws IOException {
        out.print("Start to Echo: ");
        String inputStr = "";
        while ((inputStr = in.readLine()) != null) {
            out.println("Echo: "+inputStr);
        }
    }


}
