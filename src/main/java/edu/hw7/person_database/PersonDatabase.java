package edu.hw7.person_database;

import java.util.List;

public interface PersonDatabase {
    void add(Person person);

    void delete(Integer id);

    List<Person> findByName(String name);

    List<Person> findByAddress(String address);

    List<Person> findByPhone(String phone);
}
