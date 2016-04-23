package com.advance.java.server.command;

import com.advance.java.server.model.role.Role;
import com.advance.java.server.model.role.RoleHandler;
import com.advance.java.server.socket.PortSession;
import com.sun.prism.paint.Stop;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by User on 23/4/2016.
 */
public class ProductManageCommand implements Command, StopableCommand{
    public static final String TAG = "pm";

    public boolean isContinue = true;

    PortSession session = null;

    private static Map<String, Class<? extends Command>> AVAILABLE_COMMAND = new HashMap<>();
    static{
        AVAILABLE_COMMAND.put(BackCommand.TAG, BackCommand.class);
        AVAILABLE_COMMAND.put(InsertProductCommand.TAG, InsertProductCommand.class);
        AVAILABLE_COMMAND.put(RemoveProductCommand.TAG, RemoveProductCommand.class);
        AVAILABLE_COMMAND.put(SelectProductCommand.TAG, SelectProductCommand.class);
        AVAILABLE_COMMAND.put(UpdateProductCommand.TAG, UpdateProductCommand.class);
    }

    public ProductManageCommand(PortSession session){
        this.session = session;
    }

    @Override
    public void execute() throws IOException {
        while(isContinue){
            session.out.print(getMenu());
            String input = session.in.readLine();
            try{
                if(input.endsWith("?")){
                    input = input.substring(0, input.length()-1);
                    if(AVAILABLE_COMMAND.containsKey(input)) {
                        Constructor<?> ctor = null;
                        Command cmdObj = null;
                        if(!input.equals(BackCommand.TAG)) {
                            ctor = AVAILABLE_COMMAND.get(input).getConstructor(PortSession.class);
                            cmdObj = (Command) ctor.newInstance(new Object[]{session, this});
                        }else{
                            ctor = AVAILABLE_COMMAND.get(input).getConstructor(PortSession.class, StopableCommand.class);
                            cmdObj = (Command) ctor.newInstance(new Object[]{session, this});
                        }
                        session.out.println(session.getString("helpinfo") +": "+ cmdObj.getDescription());
                    }else{
                        session.out.println(session.getString("cmdNotRecognize"));
                    }
                }else{
                    if(AVAILABLE_COMMAND.containsKey(input)) {
                        Constructor<?> ctor = null;
                        Command cmdObj = null;
                        if(!input.equals(BackCommand.TAG)) {
                            ctor = AVAILABLE_COMMAND.get(input).getConstructor(PortSession.class);
                            cmdObj = (Command) ctor.newInstance(new Object[]{session, this});
                        }else{
                            ctor = AVAILABLE_COMMAND.get(input).getConstructor(PortSession.class, StopableCommand.class);
                            cmdObj = (Command) ctor.newInstance(new Object[]{session, this});
                        }
                        cmdObj.execute();
                    }else{
                        session.out.println(session.getString("cmdNotRecognize"));
                    }
                }
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
        List<String> cmdList = new ArrayList<String>();
        for(Map.Entry<String, Class<? extends Command>> cmd : AVAILABLE_COMMAND.entrySet()){
            try {
                Constructor<?> ctor = cmd.getValue().getConstructor(PortSession.class);
                Command cmdObj = (Command)ctor.newInstance(new Object[] { session });
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
        return "\n<== "+joinedStr+ " ==>\n";
    }

    @Override
    public String getName() {
        return session.getString("ProductManage");
    }

    @Override
    public String getTag() {
        return TAG;
    }

    @Override
    public String getDescription() {
        return session.getString("ProductManageDesc");
    }

    @Override
    public String getShortDescription() {
        return session.getString("ProductManageSDesc");
    }

    @Override
    public void stop() {
        isContinue = false;
    }
}
