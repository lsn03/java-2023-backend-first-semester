package edu.hw3;

import edu.hw3.task5.Contact;
import edu.hw3.task5.ContactList;
import edu.hw3.task5.SortType;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import java.util.List;

public class ContactsTest {
    @Test
    public void testThatASCCorrect() {
//        Arrange
        ContactList contactList = new ContactList();
        String[] str = new String[]{"John Locke", "Thomas Aquinas", "David Hume", "Rene Descartes"};
        SortType sortType = SortType.ASC;

        List<Contact> listExpected = List.of(
                Contact.getContact("Thomas Aquinas"),
                Contact.getContact("Rene Descartes"),
                Contact.getContact("David Hume"),
                Contact.getContact("John Locke")
        );

//        Act
        List<Contact> result = contactList.parseContacts(str, sortType);
//        Assert

        assertEquals(listExpected, result);
    }

    @Test
    public void testThatDESCCorrect() {
//        Arrange
        ContactList contactList = new ContactList();
        String[] strSource = new String[]{"John Locke", "Thomas Aquinas", "David Hume", "Rene Descartes"};
        SortType sortType = SortType.DESC;
        List<Contact> listExpected = List.of(
                Contact.getContact("John Locke"),
                Contact.getContact("David Hume"),
                Contact.getContact("Rene Descartes"),
                Contact.getContact("Thomas Aquinas")
        );


//        Act
        List<Contact> list = contactList.parseContacts(strSource, sortType);
//        Assert

        assertEquals(listExpected, list);
    }

    @Test
    public void testThatDESCWithoutSomeLastNameCorrect() {
//        Arrange
        ContactList contactList = new ContactList();
        String[] strSource = new String[]{"John", "Thomas Aquinas", "David", "Rene Descartes"};
        SortType sortType = SortType.DESC;

        List<Contact> listExpected = List.of(
                Contact.getContact("John"),
                Contact.getContact("Rene Descartes"),
                Contact.getContact("David"),
                Contact.getContact("Thomas Aquinas")
        );

//        Act
        List<Contact> list = contactList.parseContacts(strSource, sortType);
//        Assert

        assertEquals(listExpected, list);
    }

    @Test
    public void testThatAscWithoutSomeLastNameCorrect() {
//        Arrange
        ContactList contactList = new ContactList();
        String[] strSource = new String[]{"John", "Thomas Aquinas", "David", "Rene Descartes"};
        SortType sortType = SortType.ASC;


        List<Contact> listExpected = List.of(
                Contact.getContact("Thomas Aquinas"),
                Contact.getContact("David"),
                Contact.getContact("Rene Descartes"),
                Contact.getContact("John")
        );


//        Act
        List<Contact> list = contactList.parseContacts(strSource, sortType);
//        Assert

        assertEquals(listExpected, list);
    }
}
