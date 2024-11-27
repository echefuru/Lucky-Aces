package use_case.game_start;

/**
 * The output data of the Game Start Use Case.
 */
public class GameStartOutputData {
    private String selectedGame;

    public GameStartOutputData(String selectedGame) {
        this.selectedGame = selectedGame;
    }

    public String getSelectedGame() {
        return selectedGame;
    }
}
