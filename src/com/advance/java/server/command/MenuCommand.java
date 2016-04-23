package com.advance.java.server.command;

import com.advance.java.server.model.Account;
import com.advance.java.server.model.Staff;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;

/**
 * Created by User on 23/4/2016.
 */
public class MenuCommand extends PortSession implements Command{

    DataInputStream in = null;
    PrintStream out = null;



    public MenuCommand(DataInputStream in, PrintStream out) {
        this.in = in;
        this.out = out;
    }


    @Override
    public void execute() throws IOException {
        while (true){
            out.print(currentStaff.getPosition().getPositionName());
            String input = in.readLine();
        }
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public String getTag() {
        return null;
    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public String getShortDescription() {
        return null;
    }
}
