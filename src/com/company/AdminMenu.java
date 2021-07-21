package com.company;

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
        if (choiceNumeric == 1) {
            System.out.println("See all Customers");
        }
        if (choiceNumeric == 2) {
            System.out.println("See all Rooms");
        }
        if (choiceNumeric == 3) {
            System.out.println("See all Reservations");
        }
        if (choiceNumeric == 4) {
            System.out.println("Add a Room");
        }
        if (choiceNumeric == 5) {
            System.out.println("Back to Main Menu");
        }

    }


}
