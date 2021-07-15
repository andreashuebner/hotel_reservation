package model;

import java.util.Date;

public class Reservation {

    private Customer customer;
    private IRoom iRoom;
    private Date checkInDate;
    private Date checkOutDate;

    public Reservation(Customer customer, IRoom iRoom, Date checkInDate, Date checkOutDate) {
        this.customer = customer;
        this.iRoom = iRoom;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }

    @Override
    public String toString() {
        return "Customer: " + customer + " Room Type: " + iRoom + " Check in: "
                + checkInDate + " Check out: " + checkOutDate;
    }
}
