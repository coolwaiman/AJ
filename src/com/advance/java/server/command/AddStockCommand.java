package com.advance.java.server.command;

import com.advance.java.server.dao.DAO;
import com.advance.java.server.dao.ProductDAO;
import com.advance.java.server.dao.StoreDAO;
import com.advance.java.server.dao.StoreProductDAO;
import com.advance.java.server.model.Product;
import com.advance.java.server.model.Store;
import com.advance.java.server.model.Storeproduct;
import com.advance.java.server.socket.PortSession;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Calvin on 24/4/2016.
 */
public class AddStockCommand implements Command {
    public static final String TAG = "add";
    PortSession session = null;
    public static int STOREID=0;
    private static final String STORE_TABLE_ROW_FORMAT = "%-10s  %-20s  %-11s  %-50s \n";
    private static final String PRODUCT_TABLE_ROW_FORMAT = "%-10s  %-20s  %-11s  %-10s %-10s\n";
    public AddStockCommand(PortSession session) {
        this.session = session;
    }
    @Override
    public void execute() throws IOException {
        int staffid=session.getStaff().getStaffId();
        String in="";
        while(true) {
            try {
                session.out.print(session.messages.getString("askpid"));
                in = session.in.readLine();
                int proId = 0;
                proId = Integer.parseInt(in);
                Product pro = ProductDAO.getById(proId);
                if (pro == null) {
                    continue;
                }
                session.out.print(session.messages.getString("askqty"));
                in = session.in.readLine();
                int quantity = 0;
                quantity=Integer.parseInt(in);
                for (int n = 0; n < quantity; n++) {
                    addStock(pro);
                }
                break;
            }catch(NumberFormatException e){
                session.out.print(session.messages.getString("entcor"));
                continue;
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
    private boolean addStock(Product pro){
        Storeproduct newStock=new Storeproduct();
        newStock.setStore(StoreDAO.getById(STOREID));
        newStock.setProduct(pro);
        return DAO.save(newStock);
    }
}
