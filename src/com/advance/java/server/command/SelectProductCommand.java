package com.advance.java.server.command;

import com.advance.java.server.socket.PortSession;

import java.io.IOException;

/**
 * Created by User on 23/4/2016.
 */
public class SelectProductCommand implements Command{
    public static final String TAG = "sp";

    PortSession session = null;

    public SelectProductCommand(PortSession session){
        this.session = session;
    }

    @Override
    public void execute() throws IOException {

    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public String getTag() {
        return TAG;
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