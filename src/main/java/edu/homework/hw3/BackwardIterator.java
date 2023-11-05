package edu.homework.hw3;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class BackwardIterator<T> implements Iterator<T> {
    private final List<T> elements;
    private int currentIndex;

    public BackwardIterator(List<T> elements) {
        this.elements = elements;
        currentIndex = elements.size() - 1;
    }

    @Override
    public boolean hasNext() {
        return currentIndex >= 0;

    }

    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
       var iterator =  elements.listIterator(currentIndex--);
       return iterator.next();
    }
}
