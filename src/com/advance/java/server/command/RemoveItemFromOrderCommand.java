package com.advance.java.server.command;

import com.advance.java.server.dao.StoreProductDAO;
import com.advance.java.server.model.Orderline;
import com.advance.java.server.model.Storeproduct;
import com.advance.java.server.socket.PortSession;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by User on 24/4/2016.
 */
public class RemoveItemFromOrderCommand implements Command {
    public static final String TAG = "rifo";

    PortSession session = null;

    public RemoveItemFromOrderCommand(PortSession session){
        this.session = session;
    }
    @Override
    public void execute() throws IOException {
        if(session.processingOrder.getOrderlines().isEmpty()){
            session.out.println("Shopcart is empty");
            return;
        }
        session.out.print(getOrderStatus());
        Orderline tol = null;
        while(tol==null) {
            session.out.print("Enter Product SN: ");
            String sn = session.in.readLine();
            tol = null;
            try {
                List<Orderline> list = session.processingOrder.getOrderlines()
                        .stream().filter(
                                ol ->ol.getStoreProduct().getProductSn() == Integer.parseInt(sn)
                        ).collect(Collectors.toList());
                if(!list.isEmpty())
                    tol = list.get(0);

            }catch (Exception e){
                /*pass*/
            }
            if(tol!=null)
                break;
            session.out.print("Product not Found, ");
        }
        final int snInt = tol.getStoreProduct().getProductSn();

        session.processingOrder.getOrderlines().removeIf(entry -> entry.getStoreProduct().getProductSn() == snInt);
        session.out.println("Product Move outed");
    }

    private String getOrderStatus() {
        String FORMAT = "%10s | %15s | %10s\n";
        StringBuilder sb = new StringBuilder();
        sb.append(String.format(FORMAT, session.getString("productSN"), session.getString("name"), session.getString("price")));
        for (Orderline ol : session.processingOrder.getOrderlines()) {
            sb.append(String.format(FORMAT, ol.getStoreProduct().getProductSn(), ol.getStoreProduct().getProduct().getProductName(), "$" + ol.getProductPrice()));
        }
        return sb.toString();
    }
    @Override
    public String getName() {
        return session.getString("RemoveItemFromOrder");
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
}
