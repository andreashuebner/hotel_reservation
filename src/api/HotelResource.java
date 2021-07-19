package api;

import model.Customer;
import model.IRoom;
import model.Reservation;
import service.CustomerService;
import service.ReservationService;

import java.util.Collection;
import java.util.Date;

public class HotelResource {
    private static HotelResource hotelresource = null;
    private HotelResource() {

    }

    public static HotelResource getInstance() {
        if (hotelresource == null) {
            hotelresource = new HotelResource();
        }
        return hotelresource;
    }

    public Customer getCustomer(String email) {
        CustomerService customerService = CustomerService.getInstance();
        return customerService.getCustomer(email);
    }

    public void createACustomer(String email, String firstName, String lastName) {
        CustomerService customerService = CustomerService.getInstance();
        customerService.addCustomer(email, firstName, lastName);
    }

    public IRoom getRoom(String roomNumber) {
        ReservationService reservationService = ReservationService.getInstance();
        return reservationService.getARoom(roomNumber);
    }

    public Reservation bookARoom(String customerEmail, IRoom room, Date checkInDate,
                                 Date checkOutDate) {
        ReservationService reservationService = ReservationService.getInstance();
        CustomerService customerService = CustomerService.getInstance();
        Customer customer = customerService.getCustomer(customerEmail);
        try {
            return reservationService.reserveARoom(customer, room, checkInDate, checkOutDate);
        } catch(Exception exp) {
            return null;
        }


    }
    public Collection<Reservation> getCustomersReservations(String customerEmail) {
        ReservationService reservationService = ReservationService.getInstance();
        CustomerService customerService = CustomerService.getInstance();
        Customer customer = customerService.getCustomer(customerEmail);
        return reservationService.getCustomersReservation(customer);
    }

    public Collection<IRoom> findARoom(Date checkIn, Date checkOut) {
        ReservationService reservationService = ReservationService.getInstance();
        return reservationService.findRooms(checkIn, checkOut);
    }

}
