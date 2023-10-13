package edu.project1;

import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SuppressWarnings("MultipleStringLiterals")
public class InputOutputSystemImpl implements InputOutputSystem {

    private final static Logger LOGGER = LogManager.getLogger();
    private static final String SYSTEM_GAME_OVER = "[SYSTEM]: GAME OVER";
    private static final String SYSTEM_THE_WORD = "[SYSTEM]: The word: ";

    private final Session session;
    private final Scanner scanner;

    public InputOutputSystemImpl(Session session) {
        scanner = new Scanner(System.in);
        this.session = session;
    }


    @Override
    public void scannerUserCharacter(String inputChar) {
        ValidationInputState res = session.validationInput(inputChar);
        processValidationResult(res);
    }

    @Override
    public void scannerUserCharacter() {
        String result = scanner.next();
        ValidationInputState res = session.validationInput(result);
        processValidationResult(res);
    }

    @Override
    public void printGuessLetter() {
        LOGGER.info("[SYSTEM]: Guess a letter:");
        LOGGER.info("[USER]: ");
    }

    @Override
    public void printGuessFailedLetter() {
        LOGGER.info("[SYSTEM]: Missed, mistake {} out of {}.",
                session.getAttempts(), session.getMaxAttempts());
        LOGGER.info("{} {}", SYSTEM_THE_WORD, session.getUserAnswer());
    }

    @Override
    public void printGuessSuccessLetter() {
        LOGGER.info("[SYSTEM]: Hit!");
        LOGGER.info("{} {}", SYSTEM_THE_WORD, session.getUserAnswer());
    }

    @Override
    public void printUserWin() {
        LOGGER.info(SYSTEM_GAME_OVER);
        LOGGER.info("{} {}", SYSTEM_THE_WORD, session.getAnswer());
        LOGGER.info("");
        LOGGER.info("[SYSTEM]: You won!");

    }

    @Override
    public void printUserLost() {
        LOGGER.info(SYSTEM_GAME_OVER);
        LOGGER.info("{} {}", SYSTEM_THE_WORD, session.getUserAnswer());
        LOGGER.info("");
        LOGGER.info("[SYSTEM]: You lost!");

    }

    private void processValidationResult(ValidationInputState validationResult) {
        switch (validationResult) {
            case RESULT_THAT_WORD_IS_GUESSED:
                printUserWin();
                break;
            case RESULT_THAT_CHAR_IN_WORD:
                printGuessSuccessLetter();
                break;
            case RESULT_THAT_CHAR_NOT_IN_WORD:
                printGuessFailedLetter();
                break;
            case RESULT_FOR_LOST:
                printUserLost();
                break;
            default:
                printThatIncorrectInput();
                break;
        }
    }

    private void printThatIncorrectInput() {
        LOGGER.info("[SYSTEM]: The input is incorrect. You should enter a one symbol. For exit enter -1");
    }

}
