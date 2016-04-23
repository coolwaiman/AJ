package com.advance.java.server.model.role;

import com.advance.java.server.command.Command;

import java.util.Map;

/**
 * Created by User on 22/4/2016.
 */
public interface Role {
    public String getName();
    public Map<String, Class<? extends Command>> getRoleCommands();
}
