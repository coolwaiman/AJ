package com.advance.java.server.command;

import com.advance.java.server.dao.ProductDAO;
import com.advance.java.server.model.Product;
import com.advance.java.server.socket.PortSession;

import java.io.IOException;

/**
 * Created by User on 23/4/2016.
 */
public class RemoveProductCommand implements Command{
    public static final String TAG = "rp";

    PortSession session = null;

    public RemoveProductCommand(PortSession session){
        this.session = session;
    }


    @Override
    public void execute() throws IOException {
        Product p = null;
        while(p==null) {
            session.out.print(session.getString("enterProductId")+": ");
            String id = session.in.readLine();
            try {
                p = ProductDAO.getById(Integer.parseInt(id));
            }catch (Exception e){
                /*pass*/
            }
            if(p!=null)
                break;
            session.out.print(session.getString("productNotFoundMsg"));
        }
    }

    @Override
    public String getName() {
        return session.getString("RemoveProduct");
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
