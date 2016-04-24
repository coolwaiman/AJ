package com.advance.java.server.model.role;

import com.advance.java.server.command.*;
import com.advance.java.server.model.Account;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by User on 22/4/2016.
 */
public class RoleHandler {
    public static Map<String, Map<String, Class<? extends Command>>>RoleFunction = new HashMap<>();
    static{
        RoleFunction.put("CEO", new HashMap<>());
        RoleFunction.get("CEO").put(EchoCommand.TAG, EchoCommand.class);
        RoleFunction.get("CEO").put(RegisterProductRepairCommand.TAG, RegisterProductRepairCommand.class);
        RoleFunction.get("CEO").put(ProductManageCommand.TAG, ProductManageCommand.class);
        RoleFunction.get("CEO").put(PurchaseOrderCommand.TAG, PurchaseOrderCommand.class);
        RoleFunction.get("CEO").put(CreateCustomerCommand.TAG, CreateCustomerCommand.class);

        RoleFunction.put("Sales", new HashMap<>());
        RoleFunction.get("Sales").put(SelectProductCommand.TAG, SelectProductCommand.class);
        RoleFunction.get("Sales").put(RegisterProductRepairCommand.TAG, RegisterProductRepairCommand.class);
        RoleFunction.get("Sales").put(PurchaseOrderCommand.TAG, PurchaseOrderCommand.class);
        RoleFunction.get("Sales").put(CreateCustomerCommand.TAG, CreateCustomerCommand.class);

        RoleFunction.put("Technician", new HashMap<>());
        RoleFunction.get("Technician").put(RepairCommand.TAG, RepairCommand.class);

        RoleFunction.put("HR Manager", new HashMap<>());
        RoleFunction.get("HR Manager").put(EchoCommand.TAG, EchoCommand.class); //TODO: test code
        RoleFunction.get("HR Manager").put(RegisterProductRepairCommand.TAG, RegisterProductRepairCommand.class); //TODO: test code
        RoleFunction.get("HR Manager").put(ProductManageCommand.TAG, ProductManageCommand.class); //TODO: test code
        RoleFunction.get("HR Manager").put(PurchaseOrderCommand.TAG, PurchaseOrderCommand.class);//TODO: test code
        RoleFunction.get("HR Manager").put(CreateCustomerCommand.TAG, CreateCustomerCommand.class);//TODO: test code
        RoleFunction.get("HR Manager").put(HRManageCommand.TAG, HRManageCommand.class);//TODO: test code

        RoleFunction.put("Store Manager", new HashMap<>());
        RoleFunction.get("Store Manager").put(ProductManageCommand.TAG, ProductManageCommand.class);

        RoleFunction.put("Stock Manager", new HashMap<>());
        RoleFunction.get("Stock Manager").put(StockCommand.TAG, StockCommand.class);

        RoleFunction.put("Game Researcher", new HashMap<>());
        RoleFunction.get("Game Researcher").put(ProductManageCommand.TAG, ProductManageCommand.class);

        Map<String, Map<String, Class<? extends Command>>>map = RoleFunction;
        for (Map.Entry<String, Map<String, Class<? extends Command>>> entry : map.entrySet())
        {
            entry.getValue().put(ChangeLanguageCommand.TAG, ChangeLanguageCommand.class);
            entry.getValue().put(QuitCommand.TAG, QuitCommand.class);
        }
    }
    public static Role getRole(String roleName){
        return new Role() {
            @Override
            public String getName() {
                return roleName;
            }

            @Override
            public Map<String, Class<? extends Command>> getRoleCommands() {
                Map<String, Class<? extends Command>> m = new HashMap<>();
                try{
                    m = RoleFunction.get(roleName);
                }finally {

                }
                return m;
            }
        };
    }
}
