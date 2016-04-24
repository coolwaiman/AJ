package com.advance.java.server.command;

import java.io.IOException;

import com.advance.java.server.dao.ProductDAO;
import com.advance.java.server.dao.ProductrepairworkDAO;
import com.advance.java.server.model.Product;
import com.advance.java.server.model.Productrepairwork;
import com.advance.java.server.socket.PortSession;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * Created by Calvin on 23/4/2016.
 */
public class RepairCommand implements Command  {
    public static final String TAG = "repair";
    private static final String TABLE_ROW_FORMAT = "%-10s  %-11s  %-11s  %-18s %-20s\n";
    PortSession session = null;
    private List<Productrepairwork> list;
    List<Productrepairwork> staffWork;
    List<Productrepairwork> staffNonDoneWork;
    private static Map<String, Class<? extends Command>> AVAILABLE_COMMAND = new HashMap<>();
    static{
        AVAILABLE_COMMAND.put(SelectRepairWork.TAG, SelectRepairWork.class);

    }

    public RepairCommand(PortSession session){
        this.session = session;
    }
    @Override
    public void execute() throws IOException {
        while(true) {
            getJob();
            session.out.println("All Non-Complete Repair Works");
            printRecord(staffNonDoneWork);
            session.out.println(getMenu());
            String input=session.in.readLine();
            Map<String, Class<? extends Command>> cmds = AVAILABLE_COMMAND;
            try{
                if(input.equals("b")||input.equals("B"))
                    return;
                if(input.endsWith("?")){
                    input = input.substring(0, input.length()-1);
                    if(cmds.containsKey(input)) {
                        Constructor<?> ctor = cmds.get(input).getConstructor(PortSession.class);
                        Command cmdObj = (Command) ctor.newInstance(new Object[]{this});
                        session.out.println(session.messages.getString("helpinfo") +": "+ cmdObj.getDescription());
                    }else{
                        session.out.println(session.messages.getString("cmdNotRecognize"));
                    }
                }else{
                    if(cmds.containsKey(input)) {
                        Constructor<?> ctor = cmds.get(input).getConstructor(PortSession.class);
                        Command cmdObj = (Command) ctor.newInstance(new Object[]{this});
                        cmdObj.execute();
                    }else{
                        session.out.println(session.messages.getString("cmdNotRecognize"));
                    }
                }
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } finally {

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
        for(Productrepairwork work: list) {
            if (work.getAssignedTechnician().getStaffId() == session.getStaff().getStaffId()) {
                staffWork.add(work);
                if (work.getSolvedDate() == null || work.getSolvedDate().getTime() == 0)
                    staffNonDoneWork.add(work);
            }
        }
    }
    private String getMenu(){
        List<String> cmdList = new ArrayList<String>();
        for(Map.Entry<String, Class<? extends Command>> cmd : AVAILABLE_COMMAND.entrySet()){
            try {
                Constructor<?> ctor = cmd.getValue().getConstructor(PortSession.class);
                Command cmdObj = (Command)ctor.newInstance(new Object[] { session });
                String tag = cmdObj.getTag();
                String name = cmdObj.getName();
                cmdList.add("["+tag+"]"+name);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        String joinedStr = String.join(" | ", cmdList);
        return "\n<== "+joinedStr+ " |[b]"+session.messages.getString("back")+"==>\n";
    }
    private void printRecord(List<Productrepairwork> list) {
        session.out.printf(TABLE_ROW_FORMAT,"Repair ID","Product SN","Product ID","Product Name","Create Date","Solved Date");
        for(Productrepairwork work:list) {
            String createDate=work.getCreatedDate().toLocalDateTime().format(DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm",session.getLocale()));
            String solvedDate="";
            if(work.getSolvedDate()!=null||work.getSolvedDate().getTime()!=0)
            solvedDate=work.getCreatedDate().toLocalDateTime().format(DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm",session.getLocale()));
            else
                solvedDate="N/A";
            session.out.printf(TABLE_ROW_FORMAT,work.getRepairWorkId(),work.getStoreProduct().getProductSn(),work.getStoreProduct().getProduct().getProductId(),work.getStoreProduct().getProduct().getProductName(),createDate,solvedDate);
        }
    }
}
