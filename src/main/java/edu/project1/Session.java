package edu.project1;

import java.util.Arrays;
import java.util.Objects;

public class Session {
    private final String answer;
    private char[] userAnswer;
    private final int maxAttempts;
    private int attempts;
    private String gameState;

    public Session(String answer, int maxAttempts) {

        Objects.requireNonNull(answer);

        if (maxAttempts >= answer.length() || maxAttempts < 0) {
            throw new IllegalArgumentException("The maxAttempts should be in range [0; answer.lenght())");
        }

        this.answer = answer;
        this.maxAttempts = maxAttempts;

        attempts = 0;

        userAnswer = new char[answer.length()];

        Arrays.fill(userAnswer, '*');

        gameState = GameState.IS_PLAYING.name();
    }

    public Session(String answer) {
        this(answer, answer.length() / 2);
    }

    public String getGameState() {
        return gameState;
    }

    public void giveUp() {
        gameState = GameState.GAME_OVER.name();
    }

    public String getAnswer() {
        return answer;
    }

    public String getUserAnswer() {
        return String.valueOf(userAnswer);
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

    private boolean checkThatWordCountainStar() {
        return new String(userAnswer).contains("*");
    }

    public int validationInput(String result) {
        int returnResult = Integer.MIN_VALUE;
        Objects.requireNonNull(result);
        if (result.equals("-1")) {
            giveUp();
            returnResult = -1;

        } else if (result.length() == 1) {
            char symbol = result.charAt(0);

            if (isSymbolInAnswer(symbol)) {

                if (checkThatWordCountainStar()) {

                    returnResult = 1;
                } else {
                    gameState = GameState.GAME_OVER.name();
                    returnResult = 2;
                }


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



}

