package edu.hw3;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FreqDictionary {
    public <T> Map<T, Integer> freqDict(List<T> collection) {
        Map<T, Integer> freqMap = new HashMap<>();

        for (int i = 0; i < collection.size(); i++) {
            T item = collection.get(i);
            Integer cnt = freqMap.get(item);
            if (cnt == null) {
                cnt = 0;
            }
            cnt++;
            freqMap.put(item, cnt);
        }

        return freqMap;
    }

}
