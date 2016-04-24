package com.advance.java.server.command;

import com.advance.java.server.dao.CusOrderDAO;
import com.advance.java.server.model.Cusorder;
import com.advance.java.server.model.Orderline;
import com.advance.java.server.socket.PortSession;

import javax.persistence.criteria.Order;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

/**
 * Created by User on 24/4/2016.
 */
public class DoPurchaseOrderCommand implements Command{
    public static final String TAG = "dop";

    PortSession session = null;

    public DoPurchaseOrderCommand(PortSession session){
        this.session = session;
    }

    @Override
    public void execute() throws IOException {
        if(session.processingOrder.getCustomer()==null){
            session.out.println("Customer Information not exist.");
            return;
        }
        List<Orderline> lol = session.processingOrder.getOrderlines();
        session.processingOrder.setStaff(session.getStaff());
        session.processingOrder.setOrderDiscount(1.0);
        session.processingOrder.setOrderlines(null);
        session.processingOrder.setOrderDate(new Timestamp(Calendar.getInstance().getTime().getTime()));
        CusOrderDAO.makeOrder(session.processingOrder, lol);
        session.processingOrder = new Cusorder();
        session.out.println("order created!");
    }

    @Override
    public String getName() {
        return session.getString("DoPurchaseOrder");
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
