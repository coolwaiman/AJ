package com.advance.java.server.command;

import com.advance.java.server.dao.CusOrderDAO;
import com.advance.java.server.dao.ProductDAO;
import com.advance.java.server.dao.ProductrepairworkDAO;
import com.advance.java.server.dao.StoreProductDAO;
import com.advance.java.server.model.*;
import com.advance.java.server.socket.PortSession;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Calendar;

/**
 * Created by User on 23/4/2016.
 */
public class RegisterProductRepairCommand implements Command{
    public static final String TAG = "rr";

    PortSession session = null;

    public RegisterProductRepairCommand(PortSession session){
        this.session = session;
    }

    @Override
    public void execute() throws IOException {
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
            session.out.print(session.getString("productNotFoundMsg"));
        }

        Productrepairwork prw = new Productrepairwork();
        prw.setCreatedDate(new Timestamp(Calendar.getInstance().getTime().getTime()));
        prw.setCreatedStaff(session.getStaff());
        prw.setOrder(p.getOrderline().getCusOrder());
        prw.setStoreProduct(p);

        ProductrepairworkDAO.save(prw);
        session.out.print(session.getString("ProductRepairRegistered"));
    }

    @Override
    public String getName() {
        return session.getString("RegisterProductRepair");
    }

    @Override
    public String getTag() {
        return TAG;
    }

    @Override
    public String getDescription() {
        return session.getString("RegisterProductRepairDesc");
    }

    @Override
    public String getShortDescription() {
        return null;
    }
}
