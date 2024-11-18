package interface_adapter.game_setup;

/**
 * The state for the game setup view model.
 */
public class GameSetupState {
    private String selectedGame = "";

    public void setSelectedGame(String selectedGame) {
        this.selectedGame = selectedGame;
    }

    public String getSelectedGame() {
        return selectedGame;
    }
}
