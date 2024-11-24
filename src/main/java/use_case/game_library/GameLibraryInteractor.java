package use_case.game_library;

import use_case.GameLibraryGameInfoDataAccessInterface;

public class GameLibraryInteractor implements GameLibraryInputBoundary {

    private final GameLibraryGameInfoDataAccessInterface gameInfoDataAccessObject;
    private final GameLibraryOutputBoundary initializationPresenter;

    public GameLibraryInteractor(GameLibraryGameInfoDataAccessInterface gameInfoDataAccessObject,
                                 GameLibraryOutputBoundary initializationPresenter) {
        this.gameInfoDataAccessObject = gameInfoDataAccessObject;
        this.initializationPresenter = initializationPresenter;
    }

    @Override
    public void execute() {

        final String[] availableGames = gameInfoDataAccessObject.getAvailableGames();
        if (availableGames.length == 0) {
            initializationPresenter.prepareFailView("No available games, check game_info.json.");
        }
        else {
            final GameLibraryOutputData outputData = new GameLibraryOutputData(gameInfoDataAccessObject
                    .getAvailableGames());
            initializationPresenter.prepareSuccessView(outputData);
        }
    }
}
