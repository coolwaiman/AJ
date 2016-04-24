package com.advance.java.server.command;

import com.advance.java.server.dao.StoreProductDAO;
import com.advance.java.server.model.Orderline;
import com.advance.java.server.model.Storeproduct;
import com.advance.java.server.socket.PortSession;

import java.io.IOException;

/**
 * Created by User on 24/4/2016.
 */
public class AddItemToOrderCommand implements Command{
    public static final String TAG = "aito";

    PortSession session = null;

    public AddItemToOrderCommand(PortSession session){
        this.session = session;
    }
    @Override
    public void execute() throws IOException {
        String inputStr;
        Orderline ol = new Orderline();
        Storeproduct p = null;
        while(p==null) {
            session.out.print(session.getString("enterProductSN")+": ");
            String sn = session.in.readLine();
            try {
                p = StoreProductDAO.getBySn(Integer.parseInt(sn));
            }catch (Exception e){
                /*pass*/
            }
            if(p!=null)
                break;
            session.out.print(session.getString("productSNNotFound"));
        }
        ol.setStoreProduct(p);

        session.out.printf(session.getString("enterProductPriceSuggest")+": ", p.getProduct().getProductPrice());
        boolean isValid = false;
        inputStr = session.in.readLine();
        double d = 0;
        try {
            d = Double.parseDouble(inputStr);
            isValid = true;
        }catch (Exception e){}

        while(!isValid){
            session.out.printf(session.getString("productPriceNotValidSuggest")+": ", p.getProduct().getProductPrice());
            inputStr = session.in.readLine();
            try {
                d = Double.parseDouble(inputStr);
                isValid = true;
            }catch (Exception e){}
        }
        ol.setProductPrice(d);

        session.processingOrder.getOrderlines().add(ol);
        session.out.println(session.getString("productInsertedMsg"));
    }

    @Override
    public String getName() {
        return session.getString("AddItemToOrder");
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
