package edu.hw3.task5;

import java.util.Objects;

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
        return "Contact{" + "firstName='" + firstName + '\''
                + ", lastName='" + lastName + '\''
                + '}';
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Contact contact = (Contact) o;
        return Objects.equals(firstName, contact.firstName) && Objects.equals(lastName, contact.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName);
    }
}
