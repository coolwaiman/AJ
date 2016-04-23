package com.advance.java.server.command;

import com.advance.java.server.socket.PortSession;

import java.io.IOException;
import java.util.Locale;

/**
 * Created by User on 23/4/2016.
 */
public class ChangeLanguageCommand implements UndoableCommand{
    public static final String TAG = "cl";
    PortSession session = null;

    public ChangeLanguageCommand(PortSession session){
        this.session = session;
    }

    @Override
    public void undo() throws IOException {

    }

    @Override
    public void execute() throws IOException {
        if(session.getLocale().equals(Locale.US)){
            session.setLocale(Locale.TRADITIONAL_CHINESE);
        }else if(session.getLocale().equals(Locale.TRADITIONAL_CHINESE)){
            session.setLocale(Locale.US);
        }
    }

    @Override
    public String getName() {
        return session.messages.getString("ChangeLanguage");
    }

    @Override
    public String getTag() {
        return TAG;
    }

    @Override
    public String getDescription() {
        return session.getString("ChangeLanguageDesc");
    }

    @Override
    public String getShortDescription() {
        return session.getString("ChangeLanguageSDesc");
    }
}
