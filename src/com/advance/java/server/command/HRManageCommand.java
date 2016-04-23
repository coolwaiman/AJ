package com.advance.java.server.command;

import java.io.IOException;

/**
 * Created by User on 23/4/2016.
 */
public class HRManageCommand implements Command {
    @Override
    public void execute() throws IOException {

    }

    @Override
    public String getName() {
        return "HR Managing";
    }

    @Override
    public String getTag() {
        return "hr";
    }

    @Override
    public String getDescription() {
        return "This Command is to process the Human Resource Management";
    }

    @Override
    public String getShortDescription() {
        return "Managing HR";
    }
}
