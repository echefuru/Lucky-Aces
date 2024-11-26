package use_case.game_library;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.json.JSONArray;

import use_case.GameInfoDataAccessInterface;

/**
 * The interactor for the initialization use case.
 */
public class GameLibraryInteractor implements GameLibraryInputBoundary {

    private final GameInfoDataAccessInterface gameInfoDataAccessObject;
    private final GameLibraryOutputBoundary gameLibraryPresenter;

    public GameLibraryInteractor(GameInfoDataAccessInterface gameInfoDataAccessObject,
                                 GameLibraryOutputBoundary gameLibraryPresenter) {
        this.gameInfoDataAccessObject = gameInfoDataAccessObject;
        this.gameLibraryPresenter = gameLibraryPresenter;
    }

    @Override
    public void execute() {
        final String[] availableGames = gameInfoDataAccessObject.getAvailableGames();

        // setting the game type and name
        final List<String> types = new ArrayList<>();
        final String[] gameNames = new String[availableGames.length];

        int i = 0;
        for (String game : availableGames) {
            gameNames[i] = gameInfoDataAccessObject.getName(game);
            i++;
            final JSONArray type = gameInfoDataAccessObject.getType(game);
            for (int j = 0; j < type.length(); j++) {
                if (!(types.contains(type.getString(j)))) {
                    types.add(type.getString(j));
                }
            }
        }

        final String[] gameTypes = types.toArray(new String[types.size()]);
        Arrays.sort(gameTypes);

        // game library build
        if (availableGames.length == 0) {
            gameLibraryPresenter.prepareFailView("No available games, check game_info.json.");
        }
        else {
            final GameLibraryOutputData outputData = new GameLibraryOutputData(
                    gameInfoDataAccessObject.getAvailableGames(), gameTypes, gameNames);
            gameLibraryPresenter.prepareSuccessView(outputData);
        }
    }
}
