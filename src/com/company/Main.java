package com.company;

import model.Customer;
import service.CustomerService;
import service.ReservationService;

public class Main {

    public static void main(String[] args) {
        // Create a dummy customer that is needed
        Customer customer = new Customer("dummy","dummy","dummy@dummy.com");
        ReservationService reservationService = ReservationService.getInstance();
        CustomerService customerService = CustomerService.getInstance();
        customerService.addCustomer(customer);
	    MainMenu mainMenu = MainMenu.getInstance();
	    mainMenu.showMenu();


    }
}
