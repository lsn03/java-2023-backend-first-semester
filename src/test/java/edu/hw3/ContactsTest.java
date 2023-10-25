package edu.hw3;
import edu.hw3.task5.Contact;
import edu.hw3.task5.ContactList;
import edu.hw3.task5.SortType;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

public class ContactsTest {
    @Test
    public void testThatASCCorrect(){
//        Arrange
        ContactList contactList = new ContactList();
        String[] str = new String[]{"John Locke", "Thomas Aquinas", "David Hume", "Rene Descartes"};
        SortType sortType = SortType.ASC;
        String[] str2 = new String[]{"Thomas Aquinas","Rene Descartes", "David Hume", "John Locke"};
        List<Contact> listExpected = ContactList.getListOfContacts(str2);

//        Act
        List<Contact> list = contactList.parseContacts(str,sortType);
//        Assert

        assertEquals(listExpected,list);
    }
}
