/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.advance.java.server.command;

import com.advance.java.server.dao.AccountDAO;
import com.advance.java.server.model.Account;
import com.sun.org.apache.xpath.internal.operations.Bool;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;

/**
 *
 * @author User
 */
public class LoginCommand implements Command {

    DataInputStream in = null;
    PrintStream out = null;

    public LoginCommand(DataInputStream in, PrintStream out) {
        this.in = in;
        this.out = out;
    }

    @Override
    public void execute() throws IOException {
        String username = "", password = "";
        Account a;
        while(true) {
            out.print("login as: ");
            username = in.readLine();
            out.print("password: ");
            password = in.readLine();
            a = AccountDAO.getByUsername(username);
            if(a!=null) {
                if (a.getPasswd().equals(password)) {
                    break;
                }
            }
            out.println("Username / Password set not match");
        }
        out.println("Welcome "+a.getUsername());
    }

}
