package com.advance.java.server.command;

import com.advance.java.server.model.Cusorder;
import com.advance.java.server.model.Orderline;
import com.advance.java.server.socket.PortSession;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 * Created by User on 23/4/2016.
 */
public class PurchaseOrderCommand implements Command, StopableCommand {
    public static final String TAG = "p";

    public boolean isContinue = true;

    PortSession session = null;

    private static Map<String, Class<? extends Command>> AVAILABLE_COMMAND = new HashMap<>();
    static{
        AVAILABLE_COMMAND.put(BackCommand.TAG, BackCommand.class);
        AVAILABLE_COMMAND.put(AddItemToOrderCommand.TAG, AddItemToOrderCommand.class);
        AVAILABLE_COMMAND.put(RemoveItemFromOrderCommand.TAG, RemoveItemFromOrderCommand.class);
        AVAILABLE_COMMAND.put(InitOrderCustomerCommand.TAG, InitOrderCustomerCommand.class);
        AVAILABLE_COMMAND.put(DoPurchaseOrderCommand.TAG, DoPurchaseOrderCommand.class);
    }


    public PurchaseOrderCommand(PortSession session){
        this.session = session;
    }

    @Override
    public void execute() throws IOException {
        session.processingOrder = new Cusorder();
        session.processingOrder.setOrderlines(new LinkedList<Orderline>());
        while(isContinue){
            session.out.print("\n<== "+getName()+" ==>\n");
            session.out.print(getOrderStatus());
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
                            cmdObj = (Command) ctor.newInstance(new Object[]{session});
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
                            cmdObj = (Command) ctor.newInstance(new Object[]{session});
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
        session.processingOrder = null;
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
        return "<== "+joinedStr+ " ==>\n";
    }

    private String getOrderStatus() {
        String FORMAT = "%10s | %15s | %10s\n";
        StringBuilder sb = new StringBuilder();
        if(session.processingOrder.getCustomer() != null) {
            sb.append(session.getString("customer") + " : " + session.processingOrder.getCustomer().getCusName()+"\n");
        }else{
            sb.append(session.getString("customerNotDefined")+"\n");
        }
        sb.append(String.format(FORMAT, session.getString("productSN"), session.getString("name"), session.getString("price")));
        for (Orderline ol : session.processingOrder.getOrderlines()) {
            sb.append(String.format(FORMAT, ol.getStoreProduct().getProductSn(), ol.getStoreProduct().getProduct().getProductName(), "$" + ol.getProductPrice()));
        }
        return sb.toString();
    }

    @Override
    public String getName() {
        return session.getString("PurchaseOrder");
    }

    @Override
    public String getTag() {
        return TAG;
    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public String getShortDescription() {
        return null;
    }

    @Override
    public void stop() {
        isContinue = false;
    }
}
