package use_case.game_filter;

/**
 * Output Data for the Game Filter Use Case.
 */
public class GameFilterOutputData {
    private boolean[] gamesVisible;

    public GameFilterOutputData(boolean[] gamesVisible) {
        this.gamesVisible = gamesVisible;
    }

    public boolean[] getGamesVisible() {
        return gamesVisible;
    }
}
