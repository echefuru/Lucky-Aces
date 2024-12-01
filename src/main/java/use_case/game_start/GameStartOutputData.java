package use_case.game_start;

/**
 * The output data of the Game Start Use Case.
 */
public class GameStartOutputData {
    private String selectedGame;
    private int playerBankroll;

    public GameStartOutputData(String selectedGame, int playerBankroll) {
        this.selectedGame = selectedGame;
        this.playerBankroll = playerBankroll;
    }

    public String getSelectedGame() {
        return selectedGame;
    }

    public int getPlayerBankroll() {
        return playerBankroll;
    }
}
