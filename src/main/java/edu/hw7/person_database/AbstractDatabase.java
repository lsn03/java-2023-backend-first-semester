package edu.hw7.person_database;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

abstract class AbstractDatabase implements PersonDatabase {
    protected Map<Integer, Person> inMemoryDB = new HashMap<>();
    protected Map<String, List<Person>> nameIndex = new HashMap<>();
    protected Map<String, List<Person>> addressIndex = new HashMap<>();
    protected Map<String, List<Person>> phoneIndex = new HashMap<>();

    protected void addPerson(Person person) {
        addToIndex(nameIndex, person, person.name());
        addToIndex(addressIndex, person, person.address());
        addToIndex(phoneIndex, person, person.phoneNumber());
    }

    protected void removePerson(Person person) {
        removeFromIndex(nameIndex, person, person.name());
        removeFromIndex(addressIndex, person, person.address());
        removeFromIndex(phoneIndex, person, person.phoneNumber());
    }

    protected void addToIndex(Map<String, List<Person>> index, Person person, String key) {
        index.computeIfAbsent(key, s -> new ArrayList<>()).add(person);
    }

    protected void removeFromIndex(Map<String, List<Person>> index, Person person, String key) {
        var result = index.get(key);
        if (result != null) {
            result.remove(person);
            if (result.isEmpty()) {
                index.remove(key);
            }
        }
    }

    protected List<Person> getIndexValue(Map<String, List<Person>> index, String key) {
        return index.get(key);
    }
}
