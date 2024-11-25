package use_case.game_filter;

/**
 * Output Data for the Game Filter Use Case.
 */
public class GameFilterOutputData {
    private boolean[] gamesVisible = {};
    private String[] gameTypes = {};

    public GameFilterOutputData(String[] gameTypes) {
        this.gameTypes = gameTypes;
    }

    public GameFilterOutputData(boolean[] gamesVisible) {
        this.gamesVisible = gamesVisible;
    }

    public boolean[] getGamesVisible() {
        return gamesVisible;
    }

    public String[] getGameTypes() {
        return gameTypes;
    }
}
