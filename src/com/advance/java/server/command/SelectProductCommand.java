package com.advance.java.server.command;

import com.advance.java.server.dao.ProductDAO;
import com.advance.java.server.model.Product;
import com.advance.java.server.socket.PortSession;

import java.io.IOException;
import java.util.List;

/**
 * Created by User on 23/4/2016.
 */
public class SelectProductCommand implements Command{
    public static final String TAG = "sp";

    PortSession session = null;

    public SelectProductCommand(PortSession session){
        this.session = session;
    }

    @Override
    public void execute() throws IOException {
        session.out.print("Enter Product Name");
        String name = session.in.readLine();
        /**List<Product> productList = ProductDAO.getByName(name);
        for(Product p : productList){
            session.out.println(p.getProductName());
        }**/
    }

    @Override
    public String getName() {
        return session.getString("SelectProduct");
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
