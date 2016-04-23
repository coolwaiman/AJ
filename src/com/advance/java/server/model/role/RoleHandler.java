package com.advance.java.server.model.role;

import com.advance.java.server.command.*;
import com.advance.java.server.model.Account;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by User on 22/4/2016.
 */
public class RoleHandler {
    public static Map<String, Map<String, Class<? extends Command>>>RoleFunction = new HashMap<>();
    static{
        RoleFunction.put("CEO", new HashMap<>());
        RoleFunction.put("Sales", new HashMap<>());
        RoleFunction.put("Technician", new HashMap<>());
        RoleFunction.put("HR Manager", new HashMap<>());
        RoleFunction.get("HR Manager").put(EchoCommand.TAG, EchoCommand.class);
        RoleFunction.get("HR Manager").put(ProductManageCommand.TAG, ProductManageCommand.class);

        RoleFunction.put("Store Manager", new HashMap<>());
        RoleFunction.put("Stock Manager", new HashMap<>());
        RoleFunction.put("Game Researcher", new HashMap<>());

        Map<String, Map<String, Class<? extends Command>>>map = RoleFunction;
        for (Map.Entry<String, Map<String, Class<? extends Command>>> entry : map.entrySet())
        {
            entry.getValue().put(ChangeLanguageCommand.TAG, ChangeLanguageCommand.class);
            entry.getValue().put(QuitCommand.TAG, QuitCommand.class);
        }
    }
    public static Role getRole(String roleName){
        return new Role() {
            @Override
            public String getName() {
                return roleName;
            }

            @Override
            public Map<String, Class<? extends Command>> getRoleCommands() {
                Map<String, Class<? extends Command>> m = new HashMap<>();
                try{
                    m = RoleFunction.get(roleName);
                }finally {

                }
                return m;
            }
        };
    }
}
