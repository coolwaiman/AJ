package com.advance.java.server.command;

import com.advance.java.server.dao.AccountDAO;
import com.advance.java.server.dao.CustomerDAO;
import com.advance.java.server.model.Account;
import com.advance.java.server.model.Customer;
import com.advance.java.server.socket.PortSession;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Calendar;

/**
 * Created by User on 24/4/2016.
 */
public class CreateCustomerCommand implements Command {
    public static final String TAG = "ccus";

    PortSession session = null;

    public CreateCustomerCommand(PortSession session){
        this.session = session;
    }
    @Override
    public void execute() throws IOException {
        Customer c = new Customer();
        Account a = new Account();
        boolean isPassed = false;
        String inputStr;

        while (!isPassed) {
            session.out.println(session.getString("enterCustomerUserName"));
            inputStr = session.in.readLine();
            if (AccountDAO.getByUsername(inputStr) != null) {
                session.out.print(session.getString("userNameTaken"));
            } else {
                a.setUsername(inputStr);
                isPassed = true;
            }
        }



        session.out.print(session.getString("enterCustomerPassword")+": ");
        inputStr = session.in.readLine();
        a.setPasswd(inputStr);
        a.setBalance(0);
        a.setCreatedOn(new Timestamp(Calendar.getInstance().getTimeInMillis()));


        session.out.print(session.getString("enterCustomerName")+": ");
        inputStr = session.in.readLine();
        c.setCusName(inputStr);

        session.out.print(session.getString("enterCustomerAddress")+": ");
        inputStr = session.in.readLine();
        c.setCusAddress(inputStr);

        session.out.print(session.getString("enterCustomerEmail")+": ");
        inputStr = session.in.readLine();
        c.setCusEmail(inputStr);

        session.out.print(session.getString("enterCustomerPhone")+": ");
        inputStr = session.in.readLine();
        c.setCusPhone(inputStr);

        session.out.print(session.getString("enterCustomerGender")+": ");
        char gender = ' ';
        while(true) {
            inputStr = session.in.readLine();
            inputStr = inputStr.toLowerCase();
            if(inputStr.equals("m")){
                gender = 'M';
                break;
            }else if(inputStr.equals("f")){
                gender = 'F';
                break;
            }else{
                session.out.print(session.getString("genderNotExist")+":");
            }
        }
        c.setGender(gender);
        c.setCusAccount(a);
        if(CustomerDAO.insertCus(c,a))
            session.out.println(session.getString("customerCreated"));
        else {
            session.out.println(session.getString("customerCannotCreate"));
        }
    }

    @Override
    public String getName() {
        return "CreateCustomerCommand";
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
