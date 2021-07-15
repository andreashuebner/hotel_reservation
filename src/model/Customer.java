package model;

import java.util.Locale;

public class Customer {
    private String firstName;
    private String lastName;
    private String email;
private static boolean isValidEmail(String email) {
    String emailtoMatch = email.toLowerCase();
    if (emailtoMatch.matches("\\S+@\\S+\\.com")) {
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

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
    return "First Name: " + firstName + " Last Name: " + lastName +
            "Email: " + email;
    }
}
