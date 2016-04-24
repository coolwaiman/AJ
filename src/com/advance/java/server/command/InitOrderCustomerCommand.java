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

    public InitOrderCustomerCommand(PortSession session) {
        this.session = session;
    }

    @Override
    public void execute() throws IOException {
        Account a = new Account();
        Customer c = new Customer();
        boolean isPassed = false;
        session.out.print(session.getString("enterCustomerUserNameORid"));
        String inputStr;
        while (!isPassed) {
            try {
                inputStr = session.in.readLine();
                if (inputStr.startsWith("\\id")) {
                    c = CustomerDAO.getById(Integer.parseInt(inputStr.substring(3)));
                    if (c == null) {
                        session.out.print(session.getString("customerNotExist"));
                    } else {
                        isPassed = true;
                    }
                } else {
                    c = CustomerDAO.getByUsername(inputStr);
                    if (c == null) {
                        session.out.print(session.getString("customerNotExist"));
                    } else {
                        isPassed = true;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                session.out.println(session.getString("errorTryAgain"));
                session.out.print(session.getString("enterCustomerUserNameORid"));
            }
        }

        session.processingOrder.setCustomer(c);
        session.out.println(session.getString("customerHasSet"));
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
        return session.getString("InitOrderCustomerDesc");
    }

    @Override
    public String getShortDescription() {
        return session.getString("InitOrderCustomerSDesc");
    }
}