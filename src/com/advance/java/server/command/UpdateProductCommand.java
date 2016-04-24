package com.advance.java.server.command;

import com.advance.java.server.dao.CategoryDAO;
import com.advance.java.server.dao.ProductDAO;
import com.advance.java.server.dao.ProviderDAO;
import com.advance.java.server.model.Category;
import com.advance.java.server.model.Product;
import com.advance.java.server.model.Provider;
import com.advance.java.server.socket.PortSession;

import java.io.IOException;
import java.util.List;

/**
 * Created by User on 23/4/2016.
 */
public class UpdateProductCommand implements Command{
    public static final String TAG = "up";

    PortSession session = null;

    public UpdateProductCommand(PortSession session){
        this.session = session;
    }


    @Override
    public void execute() throws IOException {
        Product p = null;
        while(p==null) {
            session.out.print("Enter Product Id: ");
            String id = session.in.readLine();
            try {
                p = ProductDAO.getById(Integer.parseInt(id));
            }catch (Exception e){
                /*pass*/
            }
            if(p!=null)
                break;
            session.out.print("Product not Found, ");
        }
        session.out.println("Start to Modify, Leave Empty to not doing change");
        String inputStr = "";
        session.out.print("Enter Product Name: ");
        inputStr = session.in.readLine();
        if(!inputStr.isEmpty())
            p.setProductName(inputStr);

        session.out.print("Enter Category Name: ");
        inputStr = session.in.readLine();
        if(!inputStr.isEmpty()){
            Category c = CategoryDAO.getByName(inputStr);
            if (c == null) {
                c = new Category();
                c.setCategoryName(inputStr);
                CategoryDAO.save(c);
            }
            p.setCategory(c);
        }

        session.out.print("Enter Provider Name: ");
        inputStr = session.in.readLine();
        if(!inputStr.isEmpty()) {
            Provider pr = ProviderDAO.getByName(inputStr);
            if (pr == null) {
                pr = new Provider();
                pr.setProviderName(inputStr);
                ProviderDAO.save(pr);
            }
            p.setProvider(pr);
        }

        session.out.print("Enter Product Description: ");
        inputStr = session.in.readLine();
        if(!inputStr.isEmpty())
            p.setProductDescription(inputStr);

        session.out.print("Enter Product Price: ");
        boolean isValid = false;
        inputStr = session.in.readLine();
        if(!inputStr.isEmpty()){
            double d = 0;
            try {
                d = Double.parseDouble(inputStr);
                isValid = true;
            } catch (Exception e) {
            }

            while (!isValid) {
                session.out.print("Product Price Not Valid: ");
                inputStr = session.in.readLine();
                if(inputStr.isEmpty())
                    break;
                try {
                    d = Double.parseDouble(inputStr);
                    isValid = true;
                } catch (Exception e) {
                }
            }
            if(!inputStr.isEmpty())
                p.setProductPrice(d);
        }

        ProductDAO.save(p);
        session.out.print("Product Saved");
    }

    @Override
    public String getName() {
        return session.getString("UpdateProduct");
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
