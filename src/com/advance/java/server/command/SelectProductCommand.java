package com.advance.java.server.command;

import com.advance.java.server.dao.ProductDAO;
import com.advance.java.server.model.Product;
import com.advance.java.server.socket.PortSession;

import java.io.IOException;
import java.util.List;
import java.util.function.BooleanSupplier;

/**
 * Created by User on 23/4/2016.
 */
public class SelectProductCommand implements Command{
    public static final String TAG = "sp";

    PortSession session = null;

    private static final String TABLE_ROW_FORMAT = "%10s | %10s | %10s | %18s | %15s\n";

    public SelectProductCommand(PortSession session){
        this.session = session;
    }

    @Override
    public void execute() throws IOException {
        session.out.print(session.getString("enterProductName")+": ");
        String name = session.in.readLine();
        List<Product> productList = ProductDAO.getByName(name);

        if(!productList.isEmpty()) {
            String inputStr = "";
            session.out.print(session.getString("doUNeedToSort") + ": ");
            while(true){
                inputStr = session.in.readLine();
                if (inputStr.toLowerCase().equals("y")) {
                    boolean isColVaild = false;
                    session.out.print(session.getString("productSelectSortColumn")+": ");
                    while(!isColVaild) {
                        isColVaild = true;
                        String sortBy = session.in.readLine();
                        final int sorter;
                        session.out.print(session.getString("askAscDesc")+" ");
                        while (true){
                            inputStr = session.in.readLine();
                            if(inputStr.toLowerCase().equals("a")){
                                sorter = 1;
                                break;
                            }else if(inputStr.toLowerCase().equals("d")){
                                sorter = -1;
                                break;
                            }else{
                                session.out.print(session.getString("inputErrorAD"));
                            }
                        }
                        switch (sortBy.toLowerCase()) {
                            case "productid":
                                productList.sort((p1,p2)-> sorter*(p1.getProductId()-p2.getProductId()));
                                break;
                            case "category":
                                productList.sort((p1,p2)-> sorter*(p1.getCategory().getCategoryName().compareTo(p2.getCategory().getCategoryName())));
                                break;
                            case "provider":
                                productList.sort((p1,p2)-> sorter*(p1.getProvider().getProviderName().compareTo(p2.getProvider().getProviderName())));
                                break;
                            case "name":
                                productList.sort((p1,p2)-> sorter*(p1.getProductName().compareTo(p2.getProductName())));
                                break;
                            case "price":
                                productList.sort((p1,p2)-> sorter*(Double.compare(p1.getProductPrice(), p2.getProductPrice())));
                                break;
                            default:
                                isColVaild = false;
                                session.out.print(session.getString("columnNotExist")+": ");
                        }
                    }
                    break;
                } else if (inputStr.toLowerCase().equals("n")) {
                    break;
                } else {
                    session.out.print(session.getString("inputNotCorrect")+": ");
                }
            }

            session.out.printf(TABLE_ROW_FORMAT, session.getString("productId"),
                    session.getString("category"), session.getString("provider"),
                    session.getString("name"), session.getString("price"));
            for (Product p : productList) {
                session.out.printf(TABLE_ROW_FORMAT, p.getProductId(),
                        p.getCategory().getCategoryName(), p.getProvider().getProviderName(),
                        p.getProductName(), "$" + p.getProductPrice());
            }
        }

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
        return session.getString("SelectProductDesc");
    }

    @Override
    public String getShortDescription() {
        return null;
    }
}
