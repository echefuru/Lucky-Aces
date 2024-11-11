package use_case.gamelibrary;

/**
 * The input data for the game library Use Case.
 */
public class GameLibraryInputData {

    private final String selectedGame;

    public GameLibraryInputData(String selectedGame) {
        this.selectedGame = selectedGame;
    }

    String getSelectedGame() {
        return this.selectedGame;
    }
}
