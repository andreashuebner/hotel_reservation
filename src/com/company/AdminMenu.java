package com.company;

import model.IRoom;
import model.Room;
import model.RoomType;
import service.CustomerService;
import service.ReservationService;

import java.util.Scanner;

public class AdminMenu {
    private static AdminMenu adminMenu = null;
    private AdminMenu() {

    }
    public static AdminMenu getInstance() {
        if (adminMenu == null) {
            adminMenu = new AdminMenu();
        }
        return adminMenu;
    }

    public void showMenu() {
        System.out.println("1. See all Customers");
        System.out.println("2. See all Rooms");
        System.out.println("3. See all Reservations");
        System.out.println("4. Add a Room");
        System.out.println("5. Back to Main Menu");
        waitForInput();
    }

    private IRoom getRoomInformation() throws InvalidRoomSetUpException {
        String roomNumber = "";
        Double price = 0.0;
        RoomType roomType = RoomType.SINGLE;
        boolean isFree = true;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the room number");
        roomNumber = scanner.nextLine();
        System.out.println("Please enter the price");
        String priceText = scanner.nextLine();
        try {
            price = Double.parseDouble(priceText);
        } catch(Exception ex) {
            throw new InvalidRoomSetUpException("Room price needs to be a number greater than 0");
        }
        if (price <= 0.0) {
            throw new InvalidRoomSetUpException("Room price needs to be a number greater than 0");
        }
        System.out.println("Please enter the room type (single/double");
        String roomTypeString = scanner.nextLine();
        if (roomTypeString.equals("single") || roomTypeString.equals("double")) {
            if (roomTypeString.equals("double")) {
                roomType = RoomType.DOUBLE;
            }
        } else {
            throw new InvalidRoomSetUpException("Room type needs to be either single our double.");
        }
        return new Room(roomNumber, price, roomType, isFree);
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
        CustomerService customerService = CustomerService.getInstance();
        ReservationService reservationService = ReservationService.getInstance();
        switch (choiceNumeric) {
            case 1:
                customerService.printAllCustomers();
                showMenu();
            case 2:
                reservationService.printAllRooms();
                showMenu();
            case 3:
                reservationService.printAllReservations();
                showMenu();
            case 4:
                try {
                    IRoom room = getRoomInformation();
                    reservationService.addRoom(room);
                    System.out.println("Room " + room + " added.");
                    showMenu();
                } catch(Exception ex) {
                    System.out.println(ex.getLocalizedMessage());
                    showMenu();
                }
                break;
            case 5:
               MainMenu mainMenu = MainMenu.getInstance();
               mainMenu.showMenu();
        }


    }


}
