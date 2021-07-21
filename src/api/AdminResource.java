package api;

import model.Customer;
import model.IRoom;
import service.CustomerService;
import service.ReservationService;

import java.util.Collection;
import java.util.List;

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

    public void addRoom(List<IRoom> rooms) {
        ReservationService reservationService = ReservationService.getInstance();
        for (IRoom room: rooms) {
            try {
                reservationService.addRoom(room);
            } catch(Exception e) {
                System.out.println(e.getLocalizedMessage());
            }
        }
    }

    public Collection<IRoom> getAllRooms() {
        ReservationService reservationService = ReservationService.getInstance();
        return reservationService.getAllRooms();
    }

    public Collection<Customer> getAllCustomers() {
        CustomerService customerService = CustomerService.getInstance();
        return customerService.getAllCustomers();
    }

    public void displayAllReservation() {
        ReservationService reservationService = ReservationService.getInstance();
        reservationService.printAllReservations();
    }
}
