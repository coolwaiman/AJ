package com.advance.java.server.command;

import com.advance.java.server.model.Productrepairwork;
import com.advance.java.server.socket.PortSession;

import java.io.IOException;
import java.util.List;

/**
 * Created by Calvin on 23/4/2016.
 */
public class StockCommand extends PortSession implements Command  {
    public static final String TAG = "repair";
    PortSession session = null;
    @Override
    public void execute() throws IOException {
        int staffid=currentStaff.getStaffId();
        while(true) {
            Productrepairwork rpt = new Productrepairwork();

        }

    }

    @Override
    public String getName() {
        return session.messages.getString("repairProcess");
    }

    @Override
    public String getDescription() {
        return session.messages.getString("repairProcessDesc");
    }

    @Override
    public String getShortDescription() {
        return session.messages.getString("repairProcessSDesc");
    }

    @Override
    public String getTag() {
        return TAG;
    }

    private void printRecord(List<Productrepairwork> list){
        out.println();
        out.print("");
    }
}
