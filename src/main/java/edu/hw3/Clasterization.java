package edu.hw3;

import java.util.ArrayList;
import java.util.List;

public class Clasterization {
    public List<String> clusterize(String cluster) {
        List<String> answer = new ArrayList<>();
        String ans = "";
        int cntDepth = 0;
        for (int i = 0; i < cluster.length(); i++) {
            char symbol = cluster.charAt(i);
            if (symbol == '(') {
                cntDepth += 1;
            } else {
                cntDepth -= 1;
            }
            ans = ans + (symbol);
            if (cntDepth == 0) {
                answer.add(ans);
                ans = "";
            }
        }
        return answer;
    }

}
