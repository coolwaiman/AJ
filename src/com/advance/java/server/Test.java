package com.advance.java.server;

import com.advance.java.server.dao.*;
import com.advance.java.server.model.*;
import org.hibernate.Session;

import java.sql.Timestamp;
import java.util.*;

class Test {

    public static void main(String... args) {
//        insertProvider();
//        insertCategory();
//        insertGame();
//        insertStore();
//        insertStoreProduct();
//        insertPosition();
//        insertStaff();
//        insertCustomer();
      //  testInsertOrder();
        tryGetOrder();
    }
    private static void tryGetOrder() {
        Staff staff = StaffDAO.getByUsername("Raymond");
        System.out.println("Got Staff " + staff.getStaffName());
        Customer customer = CustomerDAO.getByUsername("Mary");
        System.out.println("Got Customer " + customer.getCusName());
        List<Storeproduct> storeProducts = StoreProductDAO.getByStoreAndProduct(11, 21);
        System.out.println("Got SP " + storeProducts.get(0).getProduct().getProductName());
        System.out.println(customer.getCusorders().get(0).getCustomer().getCusName());

    }
    private static void testInsertOrder() {
        Staff staff = StaffDAO.getByUsername("Raymond");
        System.out.println("Got Staff " + staff.getStaffName());
        Customer customer = CustomerDAO.getByUsername("Mary");
        System.out.println("Got Customer " + customer.getCusName());
        List<Storeproduct> storeProducts = StoreProductDAO.getByStoreAndProduct(11, 21);
        System.out.println("Got SP " + storeProducts.get(0).getProduct().getProductName());
        Cusorder cusorder = new Cusorder();
        cusorder.setCustomer(customer);
        cusorder.setStaff(staff);
        cusorder.setOrderDate(new Timestamp(Calendar.getInstance().getTimeInMillis()));
        Orderline orderline = new Orderline();
        orderline.setCusOrder(cusorder);
        orderline.setProductPrice(3);
        orderline.setStoreProduct(storeProducts.get(0));
        CusOrderDAO.makeOrder(cusorder, Arrays.asList(orderline));
    }
    private static void insertPosition() {
        String [] pos = {
                "CEO", "Sales", "Technician", "HR Manager", "Store Manager" , "Stock Manager", "Game Researcher"
        };
        for(String s : pos) {
            Position po = new Position();
            po.setPositionName(s);
            DAO.save(po);
        }
    }


    private static void insertCustomer() {
        String[] names = {
                "Mary", "Sam", "Andy", "Adele"
        };
        for(String name : names) {

            Account ac = new Account();
            ac.setBalance(new Random().nextDouble() * 10000);
            ac.setPasswd("im" + name);
            ac.setCreatedOn(new Timestamp(Calendar.getInstance().getTimeInMillis()));
            ac.setUsername(name);
            AccountDAO.save(ac);

            Customer cus = new Customer();
            cus.setCusName(name);
            cus.setCusPhone("1111 0000");
            cus.setCusAccount(ac);
            cus.setCusAddress("HK");
            cus.setCusEmail(name + "@ive.stu.hk");
            cus.setGender('M');
            CustomerDAO.save(cus);
        }
    }

    private static void insertStaff() {
        String[] names = {
                "Raymond", "Tommy", "Andrew", "Calvin", "Kennith"
                ,"Ken","Lawrence", "Peter", "Jimmy"
        };

        List<Position> positions = PositionDAO.getAll();
        int i = 0;
        for(String name : names) {
            i++;
            if(i >= positions.size()) i = 0;
            Account ac = new Account();
            ac.setBalance(new Random().nextDouble() * 10000);
            ac.setCreatedOn(new Timestamp(Calendar.getInstance().getTimeInMillis()));
            ac.setPasswd("im"+name);
            ac.setUsername(name);
            AccountDAO.save(ac);

            Staff staff = new Staff();
            staff.setStaffName(name);
            staff.setStaffPhone("1111 0000");
            staff.setStaffAccount(ac);
            staff.setPosition(positions.get(i));
            StaffDAO.save(staff);
        }
    }

    private static void insertStoreProduct() {
        ArrayList<Store> stores = new ArrayList<>(StoreDAO.getAll());
        ArrayList<Product> products = new ArrayList<>(ProductDAO.getAll());
        for(Store store : stores) {
            for(Product product : products) {
                for(int i = 0; i < new Random().nextInt() * 100; i++) {
                    Storeproduct sp = new Storeproduct();
                    sp.setProduct(product);
                    sp.setStore(store);
                    StoreProductDAO.save(sp);
                }
            }
        }
    }

    private static void insertStore() {
        String[] stores = {
                "Tsing Yi Store", "Lee Wai Lee", "MoHill Store", "Sha Tin Store"
        };
        String[] locations = {
                "Tsing Yi RD 256", "Lee Big Street", "China Estate", "Sha Tin Bridge"
        };
        int i = 0;
        for(String storeName : stores) {
            Store store = new Store();
            store.setStoreName(storeName);
            store.setStoreLocation(locations[i++]);
            store.setStoreName(storeName);
            store.setStoreContact("1111 2222");
            StoreDAO.save(store);
        }
    }



    private static void insertGame() {
        ArrayList<Provider> providers = new ArrayList<>(ProviderDAO.getAll());
        ArrayList<Category> categories = new ArrayList<>(CategoryDAO.getAll());
        String[] games = {"LOL", "Sim 4", "Sim City", "Sim Cow", "Sim Chicken"};
        for(String name : games) {
            int i = new Random().nextInt(Integer.SIZE -1) % providers.size();
            int j = new Random().nextInt(Integer.SIZE -1) % categories.size();
            Product product = new Product();
            product.setProductName(name);
            product.setCategory(categories.get(j));
            product.setProvider(providers.get(i));
            product.setProductDescription("This is a Description");
            product.setProductPrice(new Random().nextDouble() * 1000);
            ProductDAO.save(product);
        }
    }

    private static void insertProvider() {
        String[] providers = { "EA", "Sony", "Microsoft"};
        for(String name : providers) {
            Provider provider = new Provider();
            provider.setProviderName(name);
            ProviderDAO.save(provider);
        }
    }

    public static void insertCategory() {
        String[] cate = new String[]{
            "Game", "Console"
        };
        Session s = HibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
        ArrayList<Category> categoryArrayList = new ArrayList<>();
        for(String c : cate) {
            Category category = new Category();
            category.setCategoryName(c);
            s.save(category);
        }
        s.getTransaction().commit();
    }


}
