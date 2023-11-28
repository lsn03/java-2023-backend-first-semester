package edu.hw7.person_database;

import java.util.List;

public class CacheServiceDatabase extends AbstractDatabase {

    @Override
    public synchronized void add(Person person) {
        inMemoryDB.put(person.id(), person);
        addPerson(person);
    }

    @Override
    public synchronized void delete(Integer id) {
        Person person = inMemoryDB.remove(id);
        removePerson(person);
    }

    @Override
    public synchronized List<Person> findByName(String name) {
        return getIndexValue(nameIndex, name);
    }

    @Override
    public synchronized List<Person> findByAddress(String address) {
        return getIndexValue(addressIndex, address);
    }

    @Override
    public synchronized List<Person> findByPhone(String phone) {
        return getIndexValue(phoneIndex, phone);
    }


}
