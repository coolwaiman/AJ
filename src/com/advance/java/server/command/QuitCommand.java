package com.advance.java.server.command;

import com.advance.java.server.socket.PortSession;

import java.io.IOException;

/**
 * Created by User on 23/4/2016.
 */
public class QuitCommand implements Command{
    public static final String TAG = "q";

    PortSession session = null;

    public QuitCommand(PortSession session){
        this.session = session;
    }


    @Override
    public void execute() throws IOException {
        session.out.println(session.getString("quitmsg"));
        session.stop();
    }

    @Override
    public String getName() {
        return session.getString("Quit");
    }

    @Override
    public String getTag() {
        return TAG;
    }

    @Override
    public String getDescription() {
        return session.getString("QuitDesc");
    }

    @Override
    public String getShortDescription() {
        return session.getString("QuitSDesc");
    }
}
