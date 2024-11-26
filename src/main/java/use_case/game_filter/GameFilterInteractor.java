package use_case.game_filter;

import org.json.JSONArray;

import use_case.GameInfoDataAccessInterface;

/**
 * The game filter Interactor.
 */
public class GameFilterInteractor implements GameFilterInputBoundary {
    private final GameFilterOutputBoundary gameFilterPresenter;
    private final GameInfoDataAccessInterface gameInfoDataAccessObject;

    public GameFilterInteractor(GameFilterOutputBoundary gameFilterPresenter,
                                GameInfoDataAccessInterface gameInfoDataAccessObject) {
        this.gameFilterPresenter = gameFilterPresenter;
        this.gameInfoDataAccessObject = gameInfoDataAccessObject;
    }

    @Override
    public void execute(GameFilterInputData gameFilterInputData) {
        final String typeInput = gameFilterInputData.getGameType();
        final int playerCount = gameFilterInputData.getPlayerCount();
        final String[] availableGames = gameInfoDataAccessObject.getAvailableGames();
        final boolean[] availableGamesVisible = new boolean[availableGames.length];
        for (int i = 0; i < availableGames.length; i++) {

            final JSONArray type = gameInfoDataAccessObject.getType(availableGames[i]);
            boolean typeBool = false;
            for (int j = 0; j < type.length(); j++) {
                if ("".equals(typeInput)) {
                    typeBool = true;
                    break;
                }
                if (type.getString(j).equals(typeInput)) {
                    typeBool = true;
                }
            }

            final int maxPlayers = gameInfoDataAccessObject.getMaxPlayer(availableGames[i]);
            final int minPlayers = gameInfoDataAccessObject.getMinPlayer(availableGames[i]);
            boolean playerBool = false;
            if (minPlayers <= playerCount && playerCount <= maxPlayers || playerCount == -1) {
                playerBool = true;
            }
            availableGamesVisible[i] = typeBool && playerBool;
        }

        final GameFilterOutputData outputData = new GameFilterOutputData(availableGamesVisible, typeInput, playerCount);
        gameFilterPresenter.prepareSuccessView(outputData);
    }
}
