package edu.project1;

public class ConsoleHangman {

    private Dictionary dictionary;
    private final Session session;
    private String answer;
    private int maxAttemps;


    public ConsoleHangman(Dictionary dictionary, Session session, int maxAttemps) {
        this.dictionary = dictionary;
        this.session = session;
        this.maxAttemps = maxAttemps;
        this.answer = dictionary.randomWord();
    }

    public void run() {

        InputOutputSystem iOSystem = new InputOutputSystemImpl(session);

        while (!session.getGameState().equals(GameState.GAME_OVER.name())) {
//            System.out.println(session.getAnswer() + "\t" + Arrays.toString(session.getUserAnswer()));

            iOSystem.printGuessLetter();
            iOSystem.scannerUserCharacter();

        }

    }
}
