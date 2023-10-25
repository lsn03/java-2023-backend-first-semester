package edu.hw3.task5;

import java.util.ArrayList;
import java.util.List;

public class Contact {
    private String firstName;
    private String lastName;

    public Contact(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Contact() {
    }

    public static Contact getContact(String fullName) {
        String[] names = fullName.split(" ");
        Contact contact = new Contact();
        contact.setFirstName(names[0]);
        if (names.length == 2) {
            contact.setLastName(names[1]);
        }
        return contact;

    }

    @Override
    public String toString() {
        return "Contact{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


}
