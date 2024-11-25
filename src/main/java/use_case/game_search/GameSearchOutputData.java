package use_case.game_search;

/**
 * Output Data for the Game Search Use Case.
 */
public class GameSearchOutputData {
    private final boolean[] gamesVisible;

    public GameSearchOutputData(boolean[] gamesVisible) {
        this.gamesVisible = gamesVisible;
    }

    public boolean[] getGameVisible() {
        return gamesVisible;
    }
}
