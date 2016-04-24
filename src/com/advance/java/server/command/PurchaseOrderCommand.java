package com.advance.java.server.command;

import com.advance.java.server.socket.PortSession;

import java.io.IOException;

/**
 * Created by User on 23/4/2016.
 */
public class PurchaseOrderCommand implements Command {
    public static final String TAG = "p";

    PortSession session = null;

    public PurchaseOrderCommand(PortSession session){
        this.session = session;
    }

    @Override
    public void execute() throws IOException {

    }

    @Override
    public String getName() {
        return session.getString("PurchaseOrder");
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
