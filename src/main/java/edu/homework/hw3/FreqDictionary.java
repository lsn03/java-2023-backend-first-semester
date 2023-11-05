package edu.homework.hw3;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class FreqDictionary {
    public <T> Map<T, Integer> freqDict(List<T> collection) {
        Objects.requireNonNull(collection);
        Map<T, Integer> freqMap = new HashMap<>();

        for (int i = 0; i < collection.size(); i++) {
            T item = collection.get(i);
            int cnt = freqMap.getOrDefault(item, 0);
            cnt++;
            freqMap.put(item, cnt);
        }

        return freqMap;
    }

}
