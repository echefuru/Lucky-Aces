package use_case.game_start;

import java.util.Map;

/**
 * The input data for the Start Game Use Case.
 */
public class GameStartInputData {
    private final String selectedGame;
    private final Map<String, Integer> config;

    public GameStartInputData(String selectedGame, Map<String, Integer> config) {
        this.selectedGame = selectedGame;
        this.config = config;
    }

    public String getSelectedGame() {
        return selectedGame;
    }

    public Map<String, Integer> getConfig() {
        return config;
    }
}
