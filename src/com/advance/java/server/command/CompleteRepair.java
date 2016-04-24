package com.advance.java.server.command;

import com.advance.java.server.dao.ProductrepairworkDAO;
import com.advance.java.server.model.Productrepairwork;
import com.advance.java.server.socket.PortSession;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

/**
 * Created by Calvin on 24/4/2016.
 */
public class CompleteRepair  implements Command  {
    public static final String TAG = "com";
    private static final String TABLE_ROW_FORMAT = "%-28s:  %-30s \n";
    PortSession session = null;


    public CompleteRepair(PortSession session) {
        this.session = session;
    }

    @Override
    public void execute() throws IOException {
        session.out.println(session.messages.getString("askcomrwid"));
        String input=session.in.readLine();
        int id=0;
        try{
            Productrepairwork work= ProductrepairworkDAO.getById(Integer.parseInt(input));
            if(work!=null) {
                work.setSolvedDate(new Timestamp(Calendar.getInstance().getTimeInMillis()));
                if(ProductrepairworkDAO.update(work))
                printWork(work);
                else
                    session.out.println(session.messages.getString("updateUnsuccess"));
            }
            else
                session.out.println(session.messages.getString("repairnotfound"));
        }catch(NumberFormatException e){
            session.out.println(session.messages.getString("repairnotfound"));
        }
    }

    @Override
    public String getName() {
        return session.messages.getString("completeRepair");
    }

    @Override
    public String getDescription() {
        return session.messages.getString("completeRepairDesc");
    }

    @Override
    public String getShortDescription() {
        return session.messages.getString("completeRepairSDesc");
    }

    @Override
    public String getTag() {
        return TAG;
    }
    private void printWork(Productrepairwork work){
        String createDate=work.getCreatedDate().toLocalDateTime().format(DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm",session.getLocale()));
        String solvedDate="";
        if(work.getSolvedDate()!=null||work.getSolvedDate().getTime()!=0)
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
