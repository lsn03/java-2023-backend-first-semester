package edu.homework.hw3.task5;

import java.util.Comparator;

public class DescContactComparator implements Comparator<Contact> {

    @Override
    public int compare(Contact o1, Contact o2) {
        String lastName1 = o1.getLastName();
        String lastName2 = o2.getLastName();

        if (lastName1 == null) {
            lastName1 = o1.getFirstName();
        }
        if (lastName2 == null) {
            lastName2 = o2.getFirstName();
        }


        return lastName2.compareTo(lastName1);

    }
}
