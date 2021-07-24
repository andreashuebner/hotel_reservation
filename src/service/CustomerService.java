package service;

import model.Customer;

import java.util.Collection;
import java.util.Vector;

public class CustomerService {
    private static CustomerService customerService = null;
    private Vector<Customer> allCustomers = new Vector<>();
    private CustomerService() {

    }

    public void addCustomer(String email, String firstName, String lastName) {
        Customer customer = new Customer(firstName, lastName, email);
        allCustomers.add(customer);
    }

    public void addCustomer(Customer customer) {
        allCustomers.add(customer);
    }

    public Customer getCustomer(String customerEmail) {
        for (Customer customer: allCustomers) {
            if (customer.getEmail().equals(customerEmail)) {
                return customer;
            }
        }
        return null;
    }

    public Collection<Customer> getAllCustomers() {
        return allCustomers;
    }
    public void printAllCustomers() {
        for (Customer customer: allCustomers) {
            System.out.println(customer);
        }
    }

    public static CustomerService getInstance() {
        if (customerService == null) {
            customerService = new CustomerService();
        }
        return customerService;
    }
}
