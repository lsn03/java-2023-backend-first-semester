package edu.project1;

public class ConsoleHangman {


    private final Session session;
    private final InputOutputSystem iOSystem;

    public ConsoleHangman(Session session) {
        this.session = session;
        iOSystem = new InputOutputSystemImpl(session);
    }

    public void setCharacter(String inputChar) {
        if (!session.getGameState().equals(GameState.GAME_OVER.name())) {

            iOSystem.printGuessLetter();
            iOSystem.scannerUserCharacter(inputChar);
        }
    }

    public void run() {

        while (!session.getGameState().equals(GameState.GAME_OVER.name())) {

            iOSystem.printGuessLetter();
            iOSystem.scannerUserCharacter();

        }

    }

    public Session getSession() {
        return session;
    }

}
