package com.advance.java.server.command;

import com.advance.java.server.socket.PortSession;

import java.io.IOException;

/**
 * Created by User on 23/4/2016.
 */
public class HRManageCommand implements Command {
    public static final String TAG = "hr";

    PortSession session = null;

    public HRManageCommand(PortSession session){
        this.session = session;
    }

    @Override
    public void execute() throws IOException {

    }

    @Override
    public String getName() {return session.getString("HRManage");}

    @Override
    public String getTag() {
        return TAG;
    }

    @Override
    public String getDescription() {
        return session.getString("HRManageDesc");
    }

    @Override
    public String getShortDescription() {
        return session.getString("HRManageSDesc");
    }
}
