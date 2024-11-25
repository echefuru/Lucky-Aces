package use_case.game_filter;

/**
 * The input data for the game filter Use Case.
 */
public class GameFilterInputData {
    private final String gameType;
    private final int playerCount;

    public GameFilterInputData(String gameType, int playerCount) {
        this.gameType = gameType;
        this.playerCount = playerCount;
    }

    public String getGameType() {
        return gameType;
    }

    public int getPlayerCount() {
        return playerCount;
    }
}
