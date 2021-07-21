package com.company;

import java.util.Scanner;

public class MainMenu {
    private static MainMenu mainMenu = null;
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
                System.out.println("Find and reserve a room");
                break;
            case 2:
                System.out.println("See my reservations");
                break;
            case 3:
                System.out.println("Create an account");
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
