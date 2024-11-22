package use_case.gamelibrary;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.json.JSONArray;

import interface_adapter.change_password.ChangePasswordState;

/**
 * The game library Interactor.
 */
public class GameLibraryInteractor implements GameLibraryInputBoundary {

    private final GameLibraryOutputBoundary gameLibraryPresenter;
    private final GameLibraryGameInfoDataAccessInterface gameInfoDataAccessObject;

    public GameLibraryInteractor(GameLibraryOutputBoundary gameLibraryPresenter,
                                 GameLibraryGameInfoDataAccessInterface gameInfoDataAccessObject) {
        this.gameLibraryPresenter = gameLibraryPresenter;
        this.gameInfoDataAccessObject = gameInfoDataAccessObject;
    }

    @Override
    public void execute(GameLibraryInputData gameLibraryInputData) {
        final String selectedGame = gameLibraryInputData.getSelectedGame();

        if (gameInfoDataAccessObject.isAvailable(selectedGame)) {
            final String gameName = gameInfoDataAccessObject.getName(selectedGame);
            final String gameDescription = gameInfoDataAccessObject.getDescription(selectedGame);
            final GameLibraryOutputData gameLibraryOutputData = new GameLibraryOutputData(gameName, gameDescription);
            gameLibraryPresenter.prepareSuccessView(gameLibraryOutputData);
        }
        else {
            gameLibraryPresenter.prepareFailView("Game not available yet, please stay tuned!");
        }
    }

    @Override
    public void switchToChangePasswordView(ChangePasswordState changePasswordState) {
        gameLibraryPresenter.switchToChangePasswordView(changePasswordState);
    }

    @Override
    public void search(String info) {
        final String[] availableGames = gameInfoDataAccessObject.getAvailableGames();
        final boolean[] availableGamesVisible = new boolean[availableGames.length];
        for (int i = 0; i < availableGames.length; i++) {
            availableGamesVisible[i] = availableGames[i].toLowerCase().contains(info.toLowerCase());
        }
        gameLibraryPresenter.search(availableGamesVisible);
    }

    @Override
    public void executeFilter() {
        final String[] availableGames = gameInfoDataAccessObject.getAvailableGames();
        final List<String> types = new ArrayList<>();
        for (int i = 0; i < availableGames.length; i++) {
            final JSONArray type = gameInfoDataAccessObject.getType(availableGames[i]);
            for (int j = 0; j < type.length(); j++) {
                if (!(types.contains(type.getString(j)))) {
                    types.add(type.getString(j));
                }
            }
        }
        final String[] gameTypes = types.toArray(new String[types.size()]);
        Arrays.sort(gameTypes);
        gameLibraryPresenter.executeFilter(gameTypes);
    }

    @Override
    public void filter(String typeInput, int playerCount) {
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

        gameLibraryPresenter.filter(availableGamesVisible);
    }
}
