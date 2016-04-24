package com.advance.java.server.command;

import com.advance.java.server.dao.ProductrepairworkDAO;
import com.advance.java.server.dao.StoreDAO;
import com.advance.java.server.dao.StoreProductDAO;
import com.advance.java.server.model.Product;
import com.advance.java.server.model.Productrepairwork;
import com.advance.java.server.model.Store;
import com.advance.java.server.model.Storeproduct;
import com.advance.java.server.socket.PortSession;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * Created by Calvin on 23/4/2016.
 */
public class StockCommand implements Command  {
    public static final String TAG = "stk";
    PortSession session = null;
    private static final String STORE_TABLE_ROW_FORMAT = "%-10s  %-20s  %-11s  %-50s \n";
    private static final String PRODUCT_TABLE_ROW_FORMAT = "%-10s  %-20s  %-11s  %-10s %-10s\n";
    private static Map<String, Class<? extends Command>> AVAILABLE_COMMAND = new HashMap<>();
    static{
        AVAILABLE_COMMAND.put(AddStockCommand.TAG, AddStockCommand.class);
    }
    private List<Store> storeList;
    public StockCommand(PortSession session) {
        this.session = session;
    }
    private int selectedStore=0;
    @Override
    public void execute() throws IOException {
        int staffid=session.getStaff().getStaffId();
        while(true) {
            while(true){
            getStore();
            getMenu();
            String input = session.in.readLine();
            try{
                if (input.equals("b") || input.equals("B"))
                    return;
                selectedStore=Integer.parseInt(input);
                if(StoreDAO.getById(selectedStore)==null){
                    session.out.println(session.messages.getString("storeNotFound"));
                    selectedStore=0;
                }
                else
                    break;
            }catch(NumberFormatException e){
                session.out.println(session.messages.getString("storeNotFound"));
            }
            }
            while(true) {
                getFunctionMenu();
                String input = session.in.readLine();
                Map<String, Class<? extends Command>> cmds = AVAILABLE_COMMAND;
                try {
                    if (input.equals("b") || input.equals("B"))
                        return;
                    if (input.endsWith("?")) {
                        input = input.substring(0, input.length() - 1);
                        if (cmds.containsKey(input)) {
                            Constructor<?> ctor = cmds.get(input).getConstructor(PortSession.class);
                            Command cmdObj = (Command) ctor.newInstance(session);
                            session.out.println(session.messages.getString("helpinfo") + ": " + cmdObj.getDescription());
                        } else {
                            session.out.println(session.messages.getString("cmdNotRecognize"));
                        }
                    } else {
                        if (cmds.containsKey(input)) {
                            Constructor<?> ctor = cmds.get(input).getConstructor(PortSession.class);
                            Command cmdObj = (Command) ctor.newInstance(session);
                            if(cmdObj instanceof AddStockCommand)
                                ((AddStockCommand)cmdObj).STOREID=selectedStore;
                            cmdObj.execute();
                        } else {
                            session.out.println(session.messages.getString("cmdNotRecognize"));
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

    }

    @Override
    public String getName() {
        return session.messages.getString("stockP");
    }

    @Override
    public String getDescription() {
        return session.messages.getString("stockPDesc");
    }

    @Override
    public String getShortDescription() {
        return session.messages.getString("stockPSDesc");
    }

    @Override
    public String getTag() {
        return TAG;
    }
    private void getStore(){
        storeList= StoreDAO.getAll();
    }
    private void getMenu(){
        session.out.println(session.messages.getString("askStoreIDForStock")+" [b] "+session.messages.getString("back"));
        session.out.printf(STORE_TABLE_ROW_FORMAT,session.messages.getString("storeID"),session.messages.getString("name"),session.messages.getString("phone"),session.messages.getString("location"));
        for(Store s:storeList){
            session.out.printf(STORE_TABLE_ROW_FORMAT,s.getStoreId(),s.getStoreName(),s.getStoreContact(),s.getStoreLocation());
        }
    }
    private void getFunctionMenu(){
        session.out.println(session.messages.getString("inStockAndStockBefore"));
        session.out.printf(PRODUCT_TABLE_ROW_FORMAT,session.messages.getString("productId"),session.messages.getString("pn"),session.messages.getString("quantity")," "," ");
        //session.out.printf(PRODUCT_TABLE_ROW_FORMAT,session.messages.getString("productId"),session.messages.getString("pn"),session.messages.getString("quantity"),session.messages.getString("warning"),session.messages.getString("warningLevel"));
        List<Storeproduct> instock= StoreProductDAO.getByStore(selectedStore);
        List<Product> uniProduct=new ArrayList<>();
        HashMap<Product,Integer> count= new HashMap<>();
        for(Storeproduct is:instock){
            if(count.containsKey(is.getProduct())){
                int curr=count.get(is.getProduct())+1;
                count.replace(is.getProduct(),curr);
            }
            else{
                count.put(is.getProduct(),1);
                uniProduct.add(is.getProduct());
            }
        }
        for(Product is:uniProduct){
        session.out.printf(PRODUCT_TABLE_ROW_FORMAT,is.getProductId(),is.getProductName(),count.get(is)," "," ");
        }
        session.out.println(getFunction());
    }
    private String getFunction(){
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
        return "\n<== "+joinedStr+ " | [b]"+session.messages.getString("back")+"==>\n";
    }
}
