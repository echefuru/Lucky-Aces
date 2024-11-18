package use_case.gamelibrary;

/**
 * Output Data for the game library Use Case.
 */
public class GameLibraryOutputData {
    private final String selectedGame;

    public GameLibraryOutputData(String selectedGame) {
        this.selectedGame = selectedGame;
    }

    public String getSelectedGame() {
        return selectedGame;
    }
}
