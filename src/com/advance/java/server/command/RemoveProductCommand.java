package com.advance.java.server.command;

import com.advance.java.server.socket.PortSession;

import java.io.IOException;

/**
 * Created by User on 23/4/2016.
 */
public class RemoveProductCommand implements Command{
    public static final String TAG = "rp";

    PortSession session = null;

    public RemoveProductCommand(PortSession session){
        this.session = session;
    }


    @Override
    public void execute() throws IOException {

    }

    @Override
    public String getName() {
        return session.getString("RemoveProduct");
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
