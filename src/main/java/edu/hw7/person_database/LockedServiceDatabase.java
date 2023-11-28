package edu.hw7.person_database;

import java.util.List;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class LockedServiceDatabase extends AbstractDatabase {
    private ReadWriteLock lock = new ReentrantReadWriteLock();

    @Override
    public void add(Person person) {
        lock.writeLock().lock();
        try {
            inMemoryDB.put(person.id(), person);
            addPerson(person);
        } finally {
            lock.writeLock().unlock();
        }
    }

    @Override
    public void delete(Integer id) {
        lock.writeLock().lock();
        try {
            Person person = inMemoryDB.remove(id);
            removePerson(person);
        } finally {
            lock.writeLock().unlock();
        }
    }

    @Override
    public List<Person> findByName(String name) {
        lock.readLock().lock();

        try {
            return getIndexValue(nameIndex, name);
        } finally {
            lock.readLock().unlock();
        }

    }

    @Override
    public List<Person> findByAddress(String address) {
        lock.readLock().lock();

        try {
            return getIndexValue(addressIndex, address);
        } finally {
            lock.readLock().unlock();
        }
    }

    @Override
    public List<Person> findByPhone(String phone) {
        lock.readLock().lock();

        try {
            return getIndexValue(phoneIndex, phone);
        } finally {
            lock.readLock().unlock();
        }
    }
}
