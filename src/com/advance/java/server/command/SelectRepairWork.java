package com.advance.java.server.command;

import com.advance.java.server.dao.ProductrepairworkDAO;
import com.advance.java.server.model.Productrepairwork;
import com.advance.java.server.socket.PortSession;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Calvin on 24/4/2016.
 */
public class SelectRepairWork implements Command  {
    public static final String TAG = "sel";
    private static final String TABLE_ROW_FORMAT = "%-28s:  %-30s \n";
    PortSession session = null;

    public SelectRepairWork(PortSession session) {
        this.session = session;
    }

    @Override
    public void execute() throws IOException {
        session.out.println(session.messages.getString("askrepair"));
        String input=session.in.readLine();
        int id=0;
        try{
            Productrepairwork work= ProductrepairworkDAO.getById(Integer.parseInt(input));
            if(work!=null)
                printWork(work);
            else
                session.out.println(session.messages.getString("repairnotfound"));
        }catch(NumberFormatException e){
            session.out.println(session.messages.getString("repairnotfound"));
        }
    }

    @Override
    public String getName() {
        return session.messages.getString("selectRepair");
    }

    @Override
    public String getDescription() {
        return session.messages.getString("selectRepairDesc");
    }

    @Override
    public String getShortDescription() {
        return session.messages.getString("selectRepairSDesc");
    }

    @Override
    public String getTag() {
        return TAG;
    }
    private void printWork(Productrepairwork work){
        String createDate=work.getCreatedDate().toLocalDateTime().format(DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm",session.getLocale()));
        String solvedDate="";
        if(work.getSolvedDate()!=null&&work.getSolvedDate().getTime()!=0)
            solvedDate=work.getSolvedDate().toLocalDateTime().format(DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm",session.getLocale()));
        else
            solvedDate="N/A";
        session.out.printf(TABLE_ROW_FORMAT,session.messages.getString("rwid"),work.getRepairWorkId());
        session.out.printf(TABLE_ROW_FORMAT,session.messages.getString("oid"),work.getOrder().getOrderId());
        session.out.printf(TABLE_ROW_FORMAT,session.messages.getString("productSN"),work.getStoreProduct().getProductSn());
        session.out.printf(TABLE_ROW_FORMAT,session.messages.getString("productId"),work.getStoreProduct().getProduct().getProductId());
        session.out.printf(TABLE_ROW_FORMAT,session.messages.getString("pn"),work.getStoreProduct().getProduct().getProductName());
        session.out.printf(TABLE_ROW_FORMAT,session.messages.getString("ati"),work.getAssignedTechnician().getStaffId());
        session.out.printf(TABLE_ROW_FORMAT,session.messages.getString("atn"),work.getAssignedTechnician().getStaffName());
        session.out.printf(TABLE_ROW_FORMAT,session.messages.getString("rcd"),createDate);
        session.out.printf(TABLE_ROW_FORMAT,session.messages.getString("sd"),solvedDate);
    }
}
