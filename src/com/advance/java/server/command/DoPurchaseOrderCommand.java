package com.advance.java.server.command;

import com.advance.java.server.dao.CusOrderDAO;
import com.advance.java.server.model.Cusorder;
import com.advance.java.server.model.Orderline;
import com.advance.java.server.socket.PortSession;

import javax.persistence.criteria.Order;
import java.io.IOException;
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
        List<Orderline> lol = session.processingOrder.getOrderlines();
        session.processingOrder.setOrderlines(null);
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
