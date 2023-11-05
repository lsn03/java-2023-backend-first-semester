package edu.homework.hw3;

import java.util.ArrayList;
import java.util.List;

public class Clasterization {
    public List<String> clusterize(String cluster) {
        List<String> result = new ArrayList<>();
        String currentSequence = "";
        int depthCounter = 0;
        for (int i = 0; i < cluster.length(); i++) {
            char symbol = cluster.charAt(i);
            if (symbol == '(') {
                depthCounter += 1;
            } else {
                depthCounter -= 1;
            }
            currentSequence = currentSequence + (symbol);
            if (depthCounter == 0) {
                result.add(currentSequence);
                currentSequence = "";
            }
        }
        return result;
    }

}
