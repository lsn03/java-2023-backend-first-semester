package edu.hw3;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

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

    public static void main(String[] args) {
        Clasterization clasterization = new Clasterization();
        List<String> res = clasterization.clusterize("((())())(()(()()))");
        System.out.println(res);
    }
}
