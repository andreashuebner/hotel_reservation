package model;

import java.util.Locale;

public final class Customer {
    private String firstName;
    private String lastName;
    private String email;
private final static boolean isValidEmail(String email) {
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

    @Override
    public boolean equals(Object obj) {
    if (this == obj) {
        return true;
    }
    if (obj == null || obj.getClass() != this.getClass()) {
        return false;
    }
    Customer customer = (Customer) obj;

    return (customer.firstName.equals(this.firstName) && customer.lastName.equals(this.lastName) &&
            customer.email.equals(this.email));
    }

    @Override
    public int hashCode() {
    // We are just using the combination of first Name, last name and email has the hash code
        return (int) firstName.hashCode() * lastName.hashCode() * email.hashCode();
    }

    public final String getFirstName() {
        return firstName;
    }

    public final String getLastName() {
        return lastName;
    }

    public final String getEmail() {
        return email;
    }

    @Override
    public final String toString() {
    return "First Name: " + firstName + " Last Name: " + lastName +
            "Email: " + email;
    }
}
