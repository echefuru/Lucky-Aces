package use_case.game_select;

/**
 * The input data for the select game use case.
 */
public class GameSelectInputData {

    private final String selectedGame;

    public GameSelectInputData(String selectedGame) {
        this.selectedGame = selectedGame;
    }

    String getSelectedGame() {
        return this.selectedGame;
    }
}
