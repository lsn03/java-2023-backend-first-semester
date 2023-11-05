package edu.homework.hw3.task5;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ContactList {


    public static List<Contact> getListOfContacts(String[] stringContacts) {
        List<Contact> contact = new ArrayList<>();
        for (int i = 0; i < stringContacts.length; i++) {
            contact.add(Contact.getContact(stringContacts[i]));
        }
        return contact;

    }

    public static List<Contact> sortContacts(List<Contact> contacts, SortType sortType) {
        if (contacts == null || contacts.isEmpty()) {
            return List.of();
        }

        Comparator<Contact> comparator;
        if (sortType == SortType.ASC) {
            comparator = new AscContactComparator();
        } else {
            comparator = new DescContactComparator();
        }

        Collections.sort(contacts, comparator);

        return contacts;

    }

    public List<Contact> parseContacts(String[] stringContacts, SortType sortType) {

        if (stringContacts == null) {
            return new ArrayList<>();
        }

        List<Contact> contacts = ContactList.getListOfContacts(stringContacts);

        contacts = sortContacts(contacts, sortType);


        return contacts;
    }

}
