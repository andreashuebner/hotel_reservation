package service;

import model.Customer;
import model.IRoom;
import model.Reservation;
import model.Room;

import java.util.Collection;
import java.util.Date;
import java.util.Vector;

public class ReservationService {
    private Vector<IRoom> rooms = new Vector<>();
    private Vector<Reservation> reservations = new Vector<>();

    private static ReservationService reservationService = null;

    private ReservationService() {

    }

    public static ReservationService getInstance() {
        if (reservationService == null) {
            reservationService = new ReservationService();
        }
        return reservationService;
    }

    private static boolean isWithinRange(Date testDate, Date startDate, Date endDate) {
        // Taken from
        // https://stackoverflow.com/questions/494180/java-how-do-i-check-if-a-date-is-within-a-certain-range
        return !(testDate.before(startDate) || testDate.after(endDate));
    }

    public Reservation reserveARoom(Customer customer, IRoom room,
                                    Date checkInDate, Date checkOutDate) throws RoomAlreadyBookedException {
        // First Check whether a Reservation for this room is possible
        // at the requested dates. If not, throw an exception
        for (Reservation reservation : reservations) {
            // First check if checkin Date conflicts with any reservation
            // Usually the first condition (comparison on room number)
            // will be false and the conditions on dates wont be checked anymore
            // so this loop will be comparably fast despite iterating through
            // all rooms
            if (reservation.getiRoom().getRoomNumber() == room.getRoomNumber() &&
                    isWithinRange(checkInDate, reservation.getCheckInDate(),
                            reservation.getCheckOutDate())) {
                throw new RoomAlreadyBookedException("Room already booked.");
            }
            // Now check if checkout Date conficts with any reservation
            if (reservation.getiRoom().getRoomNumber() == room.getRoomNumber() &&
                    isWithinRange(checkOutDate, reservation.getCheckInDate(),
                            reservation.getCheckOutDate())) {
                throw new RoomAlreadyBookedException("Room already booked.");
            }


        }

        // If we are here, then the room can be booked
        Reservation reservation = new Reservation(customer, room,
                checkInDate, checkOutDate);
        reservations.add(reservation);
        return reservation;
    }

    public Collection<IRoom> findRooms(Date checkInDate, Date checkOutDate) {
        // Iterate through list of rooms and returns the ones
        // with reservation data not conflicting with provided
        // check in and check out dates
        Vector<IRoom> rooms = new Vector<>();
        for (Reservation reservation: reservations) {
            IRoom room = reservation.getiRoom();
            // Check if room is free for provided dates
            if (!isWithinRange(checkOutDate, reservation.getCheckInDate(),
                    reservation.getCheckOutDate())) {
                rooms.add(room);
            }
            }
        return rooms;
        }

        public Collection<Reservation> getCustomersReservation(Customer customer) {
            Vector<Reservation> customerReservations = new Vector<>();
            for (Reservation reservation: reservations) {
                if (reservation.getCustomer().equals(customer)) {
                    customerReservations.add(reservation);
                }
            }
            return customerReservations;
        }

        public void printAllReservations() {
        for (Reservation reservation: reservations) {
            System.out.println(reservation);
        }
        }



    public void addRoom(IRoom roomToAdd) throws RoomExistsException {
        // If room already exists, throw exception
        for (IRoom room: rooms) {
            if (room.getRoomNumber() == roomToAdd.getRoomNumber()) {
                throw new RoomExistsException("Room " + roomToAdd + " already exists");
            }
        }
        rooms.add(roomToAdd);
    }

    public IRoom getARoom(String roomId) {
        for (IRoom room: rooms) {
            if (room.getRoomNumber() == roomId) {
                return room;
            }
        }
        return null;
    }

    public Collection<IRoom> getAllRooms() {
        return rooms;
    }
    public void printAllRooms() {
        for (IRoom room: rooms) {
            System.out.println(room);
        }
    }

}




