package com.company;

import model.Customer;
import model.IRoom;
import model.Reservation;
import model.Room;
import service.CustomerService;
import service.ReservationService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

public class MainMenu {
    private static MainMenu mainMenu = null;
    private ReservationService reservationService = ReservationService.getInstance();
    private CustomerService customerService = CustomerService.getInstance();
    private MainMenu() {

    }
    public static MainMenu getInstance() {
        if (mainMenu == null) {
            mainMenu = new MainMenu();
        }
        return mainMenu;
    }

    public void showMenu() {
        System.out.println("1. Find and reserve a room");
        System.out.println("2. See my reservations");
        System.out.println("3. Create an account");
        System.out.println("4. Admin");
        System.out.println("5. Exit");
        waitForInput();
    }

    private Customer getCustomerInformation() throws InvalidCustomerSetUp {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter your first name:");
        String firstName = scanner.nextLine();
        if (firstName.equals("")) {
            throw new InvalidCustomerSetUp("First name cannot be empty.");
        }
        System.out.println("Please enter your last name:");
        String lastName = scanner.nextLine();
        if (lastName.equals("")) {
            throw new InvalidCustomerSetUp("Last name cannot be empty.");
        }
        System.out.println("Please enter your email address:");
        String email = scanner.nextLine();
        if (email.equals("")) {
            throw new InvalidCustomerSetUp("Email cannot be empty.");
        }
        Customer customer = new Customer(firstName, lastName, email);
        return customer;
    }

    private void performBooking(Collection<IRoom> rooms, Date checkInDate,
                                Date checkOutDate) {
        // System.out.println("Check in date at perform booking");
        // System.out.println((String)checkInDate);
        // System.out.println("Check out date at perform booking");
        // System.out.println((String)checkOutDate);
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the number of the choice you want to make");
        String choiceStringRoom = scanner.nextLine();
        int choiceRoom = 0;
        try {
            choiceRoom = Integer.parseInt(choiceStringRoom);
        } catch(Exception ex) {
            System.out.println("Invalid choice");
            return;

        }
        if (choiceRoom < 1 || choiceRoom > rooms.size()) {
            System.out.println("Invalid choice");
            return;

        }
        IRoom roomChosen = (IRoom) rooms.toArray()[0];
        Reservation reservation = null;
        String customerEmail = null;
        System.out.println("Please enter your email address for your customer account");
        customerEmail = scanner.nextLine();
        Customer customer = customerService.getCustomer(customerEmail);
        try {
            reservation = reservationService.reserveARoom(customer, roomChosen,
                    checkInDate, checkOutDate);
        } catch(Exception ex) {
            System.out.println(ex.getLocalizedMessage());
            System.out.println("Sorry, the room was booked in between");
            return;

        }
        if (reservation == null) {
            System.out.println("A technical error occured. Please try again");
            return;

        } else {
            System.out.println("Reservation successful. Thank you.");
            System.out.println(reservation);
            return;

        }
    }
    private void waitForInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter you choice as a number from 1 - 5");
        String choice = scanner.nextLine();
        int choiceNumeric = 0;
        try {
            choiceNumeric = Integer.parseInt(choice);
        } catch(Exception ex) {
            System.out.println("Invalid choice");
            showMenu();
        }
        if (choiceNumeric < 1 || choiceNumeric > 5) {
            System.out.println("Invalid choice");
            showMenu();
        }
        switch (choiceNumeric) {
            case 1:
                System.out.println("Enter your desired check in date in format mm/dd/yyyy");
                String checkInDateString = scanner.nextLine();
                System.out.println("Enter your desired check out date in format mm/dd/yyyy");
                String checkOutDateString = scanner.nextLine();
                Date checkInDate;
                Date checkOutDate;
                try {
                    checkInDate = new SimpleDateFormat("MM/dd/yyyy").parse(checkInDateString);
                } catch(Exception ex) {
                    System.out.println("Check in Date must be in format mm/dd/yyyy");
                    showMenu();
                    break;
                }
                try {
                    checkOutDate = new SimpleDateFormat("MM/dd/yyyy").parse(checkOutDateString);
                } catch(Exception ex) {
                    System.out.println("Check out Date must be in format mm/dd/yyyy");
                    showMenu();
                    break;
                }

                Collection<IRoom> rooms = reservationService.findRooms(checkInDate, checkOutDate);
                int numberRoomsAvailable = rooms.size();
                // System.out.println(numberRoomsAvailable);
                if (numberRoomsAvailable == 0) {
                    System.out.println("Unfortunately no rooms available for desired dates.");
                    System.out.println("We are searching for rooms for you at a later check in date (one week later)");
                    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
                    Calendar c = Calendar.getInstance();
                    try {
                        c.setTime(sdf.parse(checkInDateString));
                    } catch(ParseException ex) {
                        System.out.println(ex.getLocalizedMessage());
                    }
                    c.add(Calendar.DAY_OF_MONTH, 7);
                    Date alternativeCheckInDate = c.getTime();
                    try {
                        c.setTime(sdf.parse(checkOutDateString));
                    } catch(ParseException ex) {
                        System.out.println(ex.getLocalizedMessage());
                    }
                        c.add(Calendar.DAY_OF_MONTH, 7);
                        Date alternativeCheckOutDate = c.getTime();

                    // Date alternativeCheckInDate =
                    rooms = reservationService.findRooms(alternativeCheckInDate, alternativeCheckOutDate);
                    numberRoomsAvailable = rooms.size();
                    if (numberRoomsAvailable == 0) {
                        System.out.println("Unfortunately, we cannot find rooms at the alternative date.");
                        System.out.println("Please try alternative dates.");
                    } else {
                        System.out.println("The following rooms are available at alternative dates:");
                        int counter = 0;
                        for (IRoom room: rooms) {
                            System.out.println("Choice: " + (counter + 1) + " " + room);
                            counter += 1;
                        }
                        System.out.println("Do you want to continue with booking for the alternative date (y/n)?");
                        String answer = scanner.nextLine();
                        if (answer.equals("y")) {
                            performBooking(rooms, alternativeCheckInDate, alternativeCheckOutDate);
                        } else {
                            showMenu();
                            break;
                        }
                    }



                } else {
                    System.out.println("The following rooms are available:");
                    int counter = 0;
                    for (IRoom room: rooms) {
                        System.out.println("Choice: " + (counter + 1) + " " + room);
                        counter += 1;
                    }
                    performBooking(rooms, checkInDate, checkOutDate);


                }

                showMenu();
                break;
            case 2:
                System.out.println("Please enter your account email");
                String email = scanner.nextLine();
                Customer customer = customerService.getCustomer(email);
                if (customer == null) {
                    System.out.println("email not found.");
                    showMenu();
                    break;
                }
                Collection<Reservation> reservations= reservationService.getCustomersReservation(customer);
                if (reservations.size() == 0) {
                    System.out.println("You currently do not have any reservations");
                    showMenu();
                    break;
                }
                System.out.println("Your current reservations:");
                for (Reservation reservation: reservations) {
                    System.out.println(reservations);
                }
                showMenu();
                break;
            case 3:
                System.out.println("Create an account");
                try {
                    customer = getCustomerInformation();
                    customerService.addCustomer(customer);
                    System.out.println("Customer " + customer + " added");
                    showMenu();
                } catch(Exception ex) {
                    System.out.println(ex.getLocalizedMessage());
                    showMenu();
                }
                break;


            case 4:
                AdminMenu adminMenu = AdminMenu.getInstance();
                adminMenu.showMenu();
                break;
            case 5:
                System.exit(0);
        }

    }


}
