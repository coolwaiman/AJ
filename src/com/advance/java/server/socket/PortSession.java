package com.advance.java.server.socket;

import com.advance.java.server.command.StopableCommand;
import com.advance.java.server.command.UndoableCommand;
import com.advance.java.server.model.Staff;

import java.io.DataInputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Stack;

/**
 * Created by User on 23/4/2016.
 */
public class PortSession implements StopableCommand{
    private static final String MESSAGE_BUNDLE = "MsgBundle";

    public DataInputStream in = null;
    public PrintStream out = null;

    public boolean isContinue = true;

    protected Staff currentStaff = null;

    //protected Stack<UndoableCommand> undoableCommandStack = new Stack<>();

    private Locale currentLocale = Locale.US;
    public ResourceBundle messages = ResourceBundle.getBundle(MESSAGE_BUNDLE, currentLocale);

    public void setStaff(Staff s){
        currentStaff = s;
    }
    public Staff getStaff(){return currentStaff;}

    public String getString(String key){
        String str = messages.getString(key);
        try{
            str =  new String(str.getBytes("UTF-8"), "UTF-8");
        }catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return str;
    }

    public Locale getLocale(){
        return currentLocale;
    }

    public void setLocale(Locale locale){
        currentLocale = locale;
        messages = ResourceBundle.getBundle(MESSAGE_BUNDLE, currentLocale);
    }

    @Override
    public void stop(){
        isContinue = false;
    }
}
