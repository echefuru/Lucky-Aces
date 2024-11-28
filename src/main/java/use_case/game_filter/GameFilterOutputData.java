package use_case.game_filter;

/**
 * Output Data for the Game Filter Use Case.
 */
public class GameFilterOutputData {
    private boolean[] gamesVisible;
    private String gamesType;
    private int playerCount;

    public GameFilterOutputData(boolean[] gamesVisible, String gamesType, int playerCount) {
        this.gamesVisible = gamesVisible;
        this.gamesType = gamesType;
        this.playerCount = playerCount;
    }

    public boolean[] getGamesVisible() {
        return gamesVisible;
    }

    public String getGamesType() {
        return gamesType;
    }

    public int getPlayerCount() {
        return playerCount;
    }
}
