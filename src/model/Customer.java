package model;

import java.util.Locale;

public class Customer {
    private String firstName;
    private String lastName;
    private String email;
private static boolean isValidEmail(String email) {
    String emailtoMatch = email.toLowerCase();
    if (emailtoMatch.matches("\\w+@\\w+\\.com")) {
        return true;
    } else {
        return false;
    }
}
    public Customer(String firstName, String lastName, String email) {
    if (!isValidEmail(email)) {
        throw new IllegalArgumentException("Invalid email");
    }
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }
}
