package com.advance.java.server.command;

import java.io.IOException;

import com.advance.java.server.dao.ProductDAO;
import com.advance.java.server.dao.ProductrepairworkDAO;
import com.advance.java.server.model.Product;
import com.advance.java.server.model.Productrepairwork;
import com.advance.java.server.socket.PortSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
/**
 * Created by Calvin on 23/4/2016.
 */
public class RepairCommand extends PortSession implements Command  {
    public static final String TAG = "repair";
    PortSession session = null;
    private List<Productrepairwork> list;
    List<Productrepairwork> staffWork;
    List<Productrepairwork> staffNonDoneWork;
    HashMap<Integer,Product>productList;
    @Override
    public void execute() throws IOException {
        getJob();
        getProduct();
        out.println("Non-completed Repair Works");
        printRecord(staffNonDoneWork);
        while(true) {


            }
        }

    }

    @Override
    public String getName() {
        return session.messages.getString("repairProcess");
    }

    @Override
    public String getDescription() {
        return session.messages.getString("repairProcessDesc");
    }

    @Override
    public String getShortDescription() {
        return session.messages.getString("repairProcessSDesc");
    }

    @Override
    public String getTag() {
        return TAG;
    }
    private void getJob(){
        list=ProductrepairworkDAO.getAll();
        staffWork=new ArrayList<>();
        staffNonDoneWork=new ArrayList<>();
        for(Productrepairwork work: list){
            if(work.getAssignedTechnician().getStaffId()==currentStaff.getStaffId()) {
                staffWork.add(work);
                if(work.getSolvedDate()==null||work.getSolvedDate().getTime()==0)
                    staffNonDoneWork.add(work);
            }
    }
    private void getProduct(){
    List<Product> pList=ProductDAO.getAll();
    for(Product p:pList){
        productList.put(p.getProductId(),p);
    }
    }
    private void printRecord(List<Productrepairwork> list) {
        out.println("ID SN Product ID Product Name");
        for(Productrepairwork work:list) {
            out.print(""+work.getRepairWorkId()+work.getStoreProduct().getProductSn()+work.getStoreProduct().getProduct().getProductId()+work.getStoreProduct().getProduct().getProductName());
        }
    }
}
