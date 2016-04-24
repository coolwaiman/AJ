package com.advance.java.server.command;

import com.advance.java.server.dao.CustomerDAO;
import com.advance.java.server.model.Account;
import com.advance.java.server.model.Customer;
import com.advance.java.server.socket.PortSession;

import java.io.IOException;

/**
 * Created by User on 24/4/2016.
 */
public class InitOrderCustomerCommand implements Command {
    public static final String TAG = "initc";

    PortSession session = null;

    public InitOrderCustomerCommand(PortSession session){
        this.session = session;
    }
    @Override
    public void execute() throws IOException {
        Account a = new Account();
        Customer c = new Customer();
        boolean isPassed = false;
        session.out.print("Enter Customer username: (with \\id<cusId> can choose by id)) ");
        String inputStr;
        while (!isPassed) {
            try {
                inputStr = session.in.readLine();
                if (inputStr.startsWith("\\id")) {
                    c = CustomerDAO.getById(Integer.parseInt(inputStr.substring(3)));
                    if(c == null) {
                        session.out.print("Customer not exist. Try again.");
                    } else {
                        isPassed = true;
                    }
                } else {
                    c = CustomerDAO.getByUsername(inputStr);
                    if(c == null) {
                        session.out.print("Customer not exist. Try again.");
                    } else {
                        isPassed = true;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                session.out.println("Error, try again.");
                session.out.print("Enter Customer username: (with \\id<cusId> can choose by id)) ");
            }
        }
        session.processingOrder.setCustomer(c);

        session.out.println("customer has been set");
    }

    @Override
    public String getName() {
        return session.getString("InitOrderCustomer");
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
