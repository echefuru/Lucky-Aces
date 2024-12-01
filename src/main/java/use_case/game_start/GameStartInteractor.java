package use_case.game_start;

import java.util.Map;

import use_case.ApiDataAccessInterface;
import use_case.BlackjackRoomDataAccessInterface;

/**
 * Interactor for the game setup use case.
 */
public class GameStartInteractor implements GameStartInputBoundary {
    private final GameStartOutputBoundary gameStartPresenter;
    private final BlackjackRoomDataAccessInterface blackjackRoomDataAccessObject;
    private final ApiDataAccessInterface apiDataAccessObject;

    public GameStartInteractor(GameStartOutputBoundary gameStartPresenter,
                               BlackjackRoomDataAccessInterface blackjackRoomDataAccessObject,
                               ApiDataAccessInterface apiDataAccessObject) {
        this.gameStartPresenter = gameStartPresenter;
        this.blackjackRoomDataAccessObject = blackjackRoomDataAccessObject;
        this.apiDataAccessObject = apiDataAccessObject;
    }

    @Override
    public void execute(GameStartInputData gameStartInputData) {
        final String selectedGame = gameStartInputData.getSelectedGame();
        final Map<String, Integer> config = gameStartInputData.getConfig();

        final GameStartOutputData gameStartOutputData = new GameStartOutputData(selectedGame);

        if ("blackjack".equals(selectedGame)) {
            // Create a new Blackjack room, using 2 decks of cards.
            blackjackRoomDataAccessObject.createRoom(apiDataAccessObject.createDeck(2),
                    config.get("bankroll"), config.get("min_bet"));
            gameStartPresenter.prepareSuccessView(gameStartOutputData);
        }
        else {
            gameStartPresenter.prepareFailView("Can't find selected game in program");
        }
    }
}
