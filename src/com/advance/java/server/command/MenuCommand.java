package com.advance.java.server.command;

import com.advance.java.server.model.role.Role;
import com.advance.java.server.model.role.RoleHandler;
import com.advance.java.server.socket.PortSession;
import com.mysql.jdbc.StringUtils;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

/**
 * Created by User on 23/4/2016.
 */
public class MenuCommand extends PortSession implements Command{
    public static final String TAG = "menu";

    public MenuCommand(DataInputStream in, PrintStream out) {
        this.in = in;
        this.out = out;
    }


    @Override
    public void execute() throws IOException {
        while (true){
            out.print(getMenu());
            String input = in.readLine();
            Role r = RoleHandler.getRole(currentStaff.getPosition().getPositionName());
            Map<String, Class<? extends Command>> cmds = r.getRoleCommands();
            try{
                Constructor<?> ctor = cmds.get(input).getConstructor(PortSession.class);
                Command cmdObj = (Command)ctor.newInstance(new Object[] { this });
                cmdObj.execute();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } finally {

            }
        }
    }

    private String getMenu(){
        Role r = RoleHandler.getRole(currentStaff.getPosition().getPositionName());
        Map<String, Class<? extends Command>> cmds = r.getRoleCommands();
        List<String> cmdList = new ArrayList<String>();
        for(Map.Entry<String, Class<? extends Command>> cmd : cmds.entrySet()){
            try {
                Constructor<?> ctor = cmd.getValue().getConstructor(PortSession.class);
                Command cmdObj = (Command)ctor.newInstance(new Object[] { this });
                String tag = cmdObj.getTag();
                String name = cmdObj.getName();
                cmdList.add("["+tag+"]"+name);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        String joinedStr = String.join(" | ", cmdList);
        return "<== "+joinedStr+ " ==>\n";
    }
    @Override
    public String getName() {
        return "Menu";
    }

    @Override
    public String getTag() {
        return TAG;
    }

    @Override
    public String getDescription() {
        return "Menu";
    }

    @Override
    public String getShortDescription() {
        return "Menu";
    }
}
