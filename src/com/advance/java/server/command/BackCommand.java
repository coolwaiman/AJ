package com.advance.java.server.command;

import com.advance.java.server.socket.PortSession;

import java.io.IOException;

/**
 * Created by User on 23/4/2016.
 */
public class BackCommand implements Command{

    public static final String TAG = "b";

    PortSession session = null;
    StopableCommand target = null;

    public BackCommand(PortSession session){
        this.session = session;
    }

    public BackCommand(PortSession session, StopableCommand target){
        this.session = session;
        this.target = target;
    }


    @Override
    public void execute() throws IOException {
        if(target!=null)
            target.stop();
    }

    @Override
    public String getName() {
        return session.getString("Back");
    }

    @Override
    public String getTag() {
        return TAG;
    }

    @Override
    public String getDescription() {
        return session.getString("BackDesc");
    }

    @Override
    public String getShortDescription() {
        return session.getString("BackSDesc");
    }
}
