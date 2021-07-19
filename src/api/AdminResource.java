package api;

import model.Customer;
import service.CustomerService;

public class AdminResource {
    private static AdminResource adminResource = null;

    private AdminResource() {

    }

    public static AdminResource getInstance() {
        if (adminResource == null) {
            adminResource = new AdminResource();
        }
        return adminResource;
    }

    public Customer getCustomer(String email) {
        CustomerService customerService = CustomerService.getInstance();
        return customerService.getCustomer(email);
    }

    
}
