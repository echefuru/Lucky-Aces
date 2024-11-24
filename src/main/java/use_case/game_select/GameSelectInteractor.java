package use_case.game_select;

import use_case.GameLibraryGameInfoDataAccessInterface;

/**
 * The game library Interactor.
 */
public class GameSelectInteractor implements GameSelectInputBoundary {

    private final GameSelectOutputBoundary gameLibraryPresenter;
    private final GameLibraryGameInfoDataAccessInterface gameInfoDataAccessObject;

    public GameSelectInteractor(GameSelectOutputBoundary gameLibraryPresenter,
                                GameLibraryGameInfoDataAccessInterface gameInfoDataAccessObject) {
        this.gameLibraryPresenter = gameLibraryPresenter;
        this.gameInfoDataAccessObject = gameInfoDataAccessObject;
    }

    @Override
    public void execute(GameSelectInputData gameSelectInputData) {
        final String selectedGame = gameSelectInputData.getSelectedGame();

        // TODO: Can we remove the is available check
        if (gameInfoDataAccessObject.isAvailable(selectedGame)) {
            final String gameName = gameInfoDataAccessObject.getName(selectedGame);
            final String gameDescription = gameInfoDataAccessObject.getDescription(selectedGame);
            final GameSelectOutputData gameSelectOutputData = new GameSelectOutputData(gameName, gameDescription);
            gameLibraryPresenter.prepareSuccessView(gameSelectOutputData);
        }
        else {
            gameLibraryPresenter.prepareFailView("Game not available yet, please stay tuned!");
        }
    }

//    @Override
//    public void search(String info) {
//        final String[] availableGames = gameInfoDataAccessObject.getAvailableGames();
//        final boolean[] availableGamesVisible = new boolean[availableGames.length];
//        for (int i = 0; i < availableGames.length; i++) {
//            availableGamesVisible[i] = availableGames[i].toLowerCase().contains(info.toLowerCase());
//        }
//        gameLibraryPresenter.search(availableGamesVisible);
//    }
}
