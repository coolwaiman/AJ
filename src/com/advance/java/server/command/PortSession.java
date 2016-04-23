package com.advance.java.server.command;

import com.advance.java.server.model.Staff;

/**
 * Created by User on 23/4/2016.
 */
public class PortSession {
    Staff currentStaff = null;

    public void setStaff(Staff s){
        currentStaff = s;
    }
}
