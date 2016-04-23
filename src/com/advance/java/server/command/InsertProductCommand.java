package com.advance.java.server.command;

import com.advance.java.server.dao.CategoryDAO;
import com.advance.java.server.dao.ProductDAO;
import com.advance.java.server.dao.ProviderDAO;
import com.advance.java.server.model.Category;
import com.advance.java.server.model.Product;
import com.advance.java.server.model.Provider;
import com.advance.java.server.socket.PortSession;

import java.io.IOException;
import java.util.DoubleSummaryStatistics;
import java.util.Random;

/**
 * Created by User on 23/4/2016.
 */
public class InsertProductCommand implements Command{
    public static final String TAG = "ip";

    PortSession session = null;

    public InsertProductCommand(PortSession session){
        this.session = session;
    }

    @Override
    public void execute() throws IOException {
        String inputStr = "";
        Product p = new Product();

        session.out.print("Enter Product Name: ");
        inputStr = session.in.readLine();
        p.setProductName(inputStr);

        session.out.print("Enter Category Name: ");
        inputStr = session.in.readLine();
        Category c = CategoryDAO.getByName(inputStr);
        if(c == null) {
            c = new Category();
            c.setCategoryName(inputStr);
            CategoryDAO.save(c);
        }
        p.setCategory(c);

        session.out.print("Enter Provider Name: ");
        inputStr = session.in.readLine();
        Provider pr = ProviderDAO.getByName(inputStr);
        if(pr == null) {
            pr = new Provider();
            pr.setProviderName(inputStr);
            ProviderDAO.save(pr);
        }
        p.setProvider(pr);

        session.out.print("Enter Product Description: ");
        inputStr = session.in.readLine();
        p.setProductDescription(inputStr);

        session.out.print("Enter Product Price: ");
        boolean isValid = false;
        inputStr = session.in.readLine();
        double d = 0;
        try {
            d = Double.parseDouble(inputStr);
            isValid = true;
        }catch (Exception e){}

        while(!isValid){
            session.out.print("Product Price Not Valid: ");
            inputStr = session.in.readLine();
            try {
                d = Double.parseDouble(inputStr);
                isValid = true;
            }catch (Exception e){}
        }
        p.setProductPrice(d);
        ProductDAO.save(p);
        session.out.print("Product Added");
    }

    @Override
    public String getName() {
        return session.getString("InsertProduct");
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
