package edu.hw3.task5;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ContactList {


    public List<Contact> parseContacts(String[] stringContacts, SortType sortType) {

        if (stringContacts == null){
            return new ArrayList<>();
        }

        List<Contact> contacts = ContactList.getListOfContacts(stringContacts);

        contacts = sortContacts(contacts, sortType);


        return contacts;
    }

    public static List<Contact> getListOfContacts(String[] stringContacts) {
        List<Contact> contact = new ArrayList<>();
        for (int i = 0; i < stringContacts.length; i++) {
            contact.add(Contact.getContact(stringContacts[i]));
        }
        return contact;

    }

    public static List<Contact> sortContacts(List<Contact> contacts, SortType sortType) {
        if (contacts == null || contacts.isEmpty()) {
            return new ArrayList<>();
        }

        Comparator<Contact> comparator;
        if (sortType == SortType.ASC) {
            comparator = new AscContactComporator();
        } else {
            comparator = new DescContactComporator();
        }

        Collections.sort(contacts, comparator);

        return contacts;

    }

    public static void main(String[] args) {
        String[] str = new String[]{"Paul Erdos", "Leonhard Euler", "Carl Gauss"};
        ContactList contactList = new ContactList();
        List<Contact> list = contactList.parseContacts(str, SortType.DESC );
        System.out.println(list);

//        Contact contact = new Contact("Aboba bobod");
//        Contact contact2 = new Contact("boba Dolboebov");
//        Contact contact3 = new Contact("Aloba Yikogdabov");
//        Contact contact4 = new Contact("oba Nikogdabov");


    }
}
