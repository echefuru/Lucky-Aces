package interface_adapter.game_library_select;

import interface_adapter.ViewManagerModel;
import use_case.game_filter.GameFilterOutputBoundary;
import use_case.game_filter.GameFilterOutputData;

/**
 * Presenter for the game filter use case.
 */
public class GameFilterPresenter implements GameFilterOutputBoundary {
    private final ViewManagerModel viewManagerModel;
    private final GameLibraryViewModel gameLibraryViewModel;

    public GameFilterPresenter(ViewManagerModel viewManagerModel, GameLibraryViewModel gameLibraryViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.gameLibraryViewModel = gameLibraryViewModel;
    }

    @Override
    public void prepareSuccessView(GameFilterOutputData gameFilterOutputData) {
        final boolean[] availableGamesVisible = gameFilterOutputData.getGamesVisible();
        final GameLibraryState gameLibraryState = gameLibraryViewModel.getState();
        gameLibraryState.setAvailableGamesVisible(availableGamesVisible);
        final String gameType;
        if ("".equals(gameFilterOutputData.getGamesType())) {
            gameType = "any";
        }
        else {
            gameType = gameFilterOutputData.getGamesType();
        }
        String filterMessage = String.format(
                "<html>Search is not activated.<br>Filter is activated:<br>"
                        + "Selected game type is: %s.<br>"
                        + "Number of players selected is: ",
                gameType);
        if (gameFilterOutputData.getPlayerCount() == -1) {
            filterMessage = filterMessage + "any.<html>";
        }
        else {
            filterMessage = filterMessage + gameFilterOutputData.getPlayerCount() + ".<html>";
        }
        gameLibraryState.setFilterMessage(filterMessage);

        this.gameLibraryViewModel.setState(gameLibraryState);
        this.gameLibraryViewModel.firePropertyChanged("gameSelect");
    }

    @Override
    public void prepareFailView(String errorMessage) {
        // note: this use case currently can't fail
    }
}
