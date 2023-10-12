package edu.project1;

import java.util.Arrays;
import java.util.Objects;

public class Session {
    private final String answer;
    private final char[] userAnswer;
    private final int maxAttempts;
    private int attempts = 0;
    private GameState gameState = GameState.IS_PLAYING;

    private void constructorArgumentValidation(String answer, int maxAttempts) {
        Objects.requireNonNull(answer);
        if (maxAttempts >= answer.length() || maxAttempts < 0) {
            throw new IllegalArgumentException("The maxAttempts should be in range [0; answer.lenght())");
        }
    }

    public Session(String answer, int maxAttempts) {
        constructorArgumentValidation(answer, maxAttempts);

        this.answer = answer;
        this.maxAttempts = maxAttempts;
        userAnswer = new char[answer.length()];

        Arrays.fill(userAnswer, '*');
    }

    public Session(String answer) {
        this(answer, answer.length() / 2);
    }

    public GameState getGameState() {
        return gameState;
    }

    public void giveUp() {
        gameState = GameState.GAME_OVER;
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

    private boolean checkThatWordContainsStar() {
        return new String(userAnswer).contains("*");
    }

    public ValidationInputState validationInput(String result) {
        ValidationInputState returnResult = ValidationInputState.RESULT_THAT_INCORRECT_VALIDATION;
        if ("-1".equals(result)) {
            giveUp();
            returnResult = ValidationInputState.RESULT_FOR_LOST;

        } else if (result.length() == 1) {
            char symbol = result.charAt(0);
            if (isSymbolInAnswer(symbol)) {
                if (checkThatWordContainsStar()) {
                    returnResult = ValidationInputState.RESULT_THAT_CHAR_IN_WORD;
                } else {
                    gameState = GameState.GAME_OVER;
                    returnResult = ValidationInputState.RESULT_THAT_WORD_IS_GUESSED;
                }
            } else {
                attempts += 1;
                if (attempts == maxAttempts) {
                    giveUp();
                    returnResult = ValidationInputState.RESULT_FOR_LOST;
                } else {
                    returnResult = ValidationInputState.RESULT_THAT_CHAR_NOT_IN_WORD;
                }
            }
        }
        return returnResult;
    }


}

