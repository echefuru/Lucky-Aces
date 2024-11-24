package use_case.initialization;

import use_case.GameLibraryGameInfoDataAccessInterface;

public class InitializationInteractor implements InitializationInputBoundary {

    private final GameLibraryGameInfoDataAccessInterface gameInfoDataAccessObject;
    private final InitializationOutputBoundary initializationPresenter;

    public InitializationInteractor(GameLibraryGameInfoDataAccessInterface gameInfoDataAccessObject,
                                    InitializationOutputBoundary initializationPresenter) {
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
            final InitializationOutputData outputData = new InitializationOutputData(gameInfoDataAccessObject
                    .getAvailableGames());
            initializationPresenter.prepareSuccessView(outputData);
        }
    }
}
