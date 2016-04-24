package com.advance.java.server.command;

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
        Customer c = new Customer();

        session.out.print("Enter Customer Name: ");
        String inputStr = session.in.readLine();
        c.setCusName(inputStr);

        session.out.print("Enter Customer Address: ");
        inputStr = session.in.readLine();
        c.setCusAddress(inputStr);

        session.out.print("Enter Customer Email: ");
        inputStr = session.in.readLine();
        c.setCusEmail(inputStr);

        session.out.print("Enter Customer Phone: ");
        inputStr = session.in.readLine();
        c.setCusPhone(inputStr);

        session.out.print("Enter Customer Gender (M / F): ");
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
                session.out.print("Gender not Exist ( M / F ): ");
            }
        }
        c.setGender(gender);

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
