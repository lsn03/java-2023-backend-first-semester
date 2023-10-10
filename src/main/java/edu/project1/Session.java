package edu.project1;

import java.util.Arrays;

public class Session {
    private final String answer;
    private char[] userAnswer;
    private final int maxAttempts;
    private int attempts;
    private String gameState;

    public String getGameState() {
        return gameState;
    }

    public void giveUp() {
        gameState = GameState.GAME_OVER.name();
    }

    public String getAnswer() {
        return answer;
    }

    public char[] getUserAnswer() {
        return userAnswer;
    }

    public int getMaxAttempts() {
        return maxAttempts;
    }

    public int getAttempts() {
        return attempts;
    }

    private boolean isSymbolInAnswer(char symbol) {
        boolean flag = false;
        for (int i = 0; i < answer.length(); i++) {
            if (answer.charAt(i) == symbol) {
                userAnswer[i] = symbol;
                flag = true;
            }
        }
        return flag;
    }

    public int validationInput(String result) {
        int returnResult = Integer.MIN_VALUE;

        if (result.equals("-1")) {
            giveUp();
            returnResult = -1;

        } else if (result.length() == 1) {
            char symbol = result.charAt(0);

            if (isSymbolInAnswer(symbol)) {
                returnResult = 1;

            } else {
                attempts += 1;
                if (attempts == maxAttempts) {
                    giveUp();
                    returnResult = -1;

                } else {
                    returnResult = 0;
                }

            }
        }
        return returnResult;
    }

    public Session(String answer, int maxAttempts) {
        this.answer = answer;
        this.maxAttempts = maxAttempts;
        attempts = 0;

        userAnswer = new char[answer.length()];

        Arrays.fill(userAnswer, '*');

        gameState = GameState.IS_PLAYING.name();
    }
}

