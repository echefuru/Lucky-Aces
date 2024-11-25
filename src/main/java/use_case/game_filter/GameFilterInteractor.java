package use_case.game_filter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.json.JSONArray;

import use_case.GameLibraryGameInfoDataAccessInterface;

/**
 * The game filter Interactor.
 */
public class GameFilterInteractor implements GameFilterInputBoundary {
    private final GameFilterOutputBoundary gameFilterPresenter;
    private final GameLibraryGameInfoDataAccessInterface gameInfoDataAccessObject;

    public GameFilterInteractor(GameFilterOutputBoundary gameFilterPresenter,
                                GameLibraryGameInfoDataAccessInterface gameInfoDataAccessObject) {
        this.gameFilterPresenter = gameFilterPresenter;
        this.gameInfoDataAccessObject = gameInfoDataAccessObject;
    }

    @Override
    public void execute() {
        final String[] availableGames = gameInfoDataAccessObject.getAvailableGames();
        final List<String> types = new ArrayList<>();
        for (String game : availableGames) {
            final JSONArray type = gameInfoDataAccessObject.getType(game);
            for (int j = 0; j < type.length(); j++) {
                if (!(types.contains(type.getString(j)))) {
                    types.add(type.getString(j));
                }
            }
        }
        final String[] gameTypes = types.toArray(new String[types.size()]);
        Arrays.sort(gameTypes);

        final GameFilterOutputData outputData = new GameFilterOutputData(gameTypes);
        gameFilterPresenter.prepareSuccessView(outputData);
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

        final GameFilterOutputData outputData = new GameFilterOutputData(availableGamesVisible);
        gameFilterPresenter.prepareSuccessView(outputData);
    }
}
