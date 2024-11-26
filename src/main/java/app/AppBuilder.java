package app;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import data_access.GameInfoDataAccessObject;
import entity.player.CommonPlayerFactory;
import entity.player.PlayerFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.blackjack.BlackjackController;
import interface_adapter.blackjack.BlackjackPresenter;
import interface_adapter.blackjack.BlackjackViewModel;
import interface_adapter.game_library_select.GameLibraryController;
import interface_adapter.game_library_select.GameLibraryPresenter;
import interface_adapter.game_library_select.GameLibraryViewModel;
import interface_adapter.game_library_select.GameSearchController;
import interface_adapter.game_library_select.GameSearchPresenter;
import interface_adapter.game_library_select.GameSelectController;
import interface_adapter.game_library_select.GameSelectPresenter;
import interface_adapter.game_setup.GameSetupController;
import interface_adapter.game_setup.GameSetupPresenter;
import interface_adapter.game_setup.GameSetupViewModel;
import use_case.blackjack.BlackjackInputBoundary;
import use_case.blackjack.BlackjackInteractor;
import use_case.blackjack.BlackjackOutputBoundary;
import use_case.game_library.GameLibraryInputBoundary;
import use_case.game_library.GameLibraryInteractor;
import use_case.game_library.GameLibraryOutputBoundary;
import use_case.game_search.GameSearchInputBoundary;
import use_case.game_search.GameSearchInteractor;
import use_case.game_search.GameSearchOutputBoundary;
import use_case.game_select.GameSelectInputBoundary;
import use_case.game_select.GameSelectInteractor;
import use_case.game_select.GameSelectOutputBoundary;
import use_case.game_setup.GameSetupInputBoundary;
import use_case.game_setup.GameSetupInteractor;
import use_case.game_setup.GameSetupOutputBoundary;
import view.*;

/**
 * The AppBuilder class is responsible for putting together the pieces of
 * our CA architecture; piece by piece.
 * <p/>
 * This is done by adding each View and then adding related Use Cases.
 */
// Checkstyle note: you can ignore the "Class Data Abstraction Coupling"
//                  and the "Class Fan-Out Complexity" issues for this lab; we encourage
//                  your team to think about ways to refactor the code to resolve these
//                  if your team decides to work with this as your starter code
//                  for your final project this term.
public class AppBuilder {
    private final JPanel mainPanel = new JPanel();
    private final CardLayout mainLayout = new CardLayout();
    // thought question: is the hard dependency below a problem?
    private final PlayerFactory playerFactory = new CommonPlayerFactory();
    private final ViewManagerModel viewManagerModel = new ViewManagerModel();
    private final ViewManager viewManager = new ViewManager(mainPanel, mainLayout, viewManagerModel);

    // thought question: is the hard dependency below a problem?
    private final GameInfoDataAccessObject gameInfoDataAccessObject = new GameInfoDataAccessObject("game_info.json");

    private GameLibraryView gameLibraryView;
    private GameLibraryViewModel gameLibraryViewModel;
    private GameSetupView gameSetupView;
    private GameSetupViewModel gameSetupViewModel;
    private BlackjackView blackjackView;
    private BlackjackViewModel blackjackViewModel;

    public AppBuilder() {
        mainPanel.setLayout(mainLayout);
    }

    /**
     * Adds the Game Library View to the application.
     * @return this builder
     */
    public AppBuilder addGameLibraryView() {
        gameLibraryViewModel = new GameLibraryViewModel();
        gameLibraryView = new GameLibraryView(gameLibraryViewModel);
        mainPanel.add(gameLibraryView, gameLibraryView.getViewName());
        return this;
    }

    /**
     * Adds the Game Setup View to the application.
     * @return this builder
     */
    public AppBuilder addGameSetupView() {
        gameSetupViewModel = new GameSetupViewModel();
        gameSetupView = new GameSetupView(gameSetupViewModel);
        mainPanel.add(gameSetupView, gameSetupView.getViewName());
        return this;
    }

    /**
     * Adds the Blackjack View to the application.
     * @return this builder
     */
    public AppBuilder addBlackjackView() {
        blackjackViewModel = new BlackjackViewModel();
        blackjackView = new BlackjackView(blackjackViewModel);
        mainPanel.add(blackjackView, blackjackView.getViewName());
        return this;
    }

    /**
     * Adds the Game Library Use Case to the application.
     * @return this builder
     */
    public AppBuilder addGameLibraryUseCase() {
        final GameLibraryOutputBoundary gameLibraryPresenter = new GameLibraryPresenter(
                gameLibraryViewModel,
                viewManagerModel);
        final GameLibraryInputBoundary gameLibraryInteractor = new GameLibraryInteractor(
                gameInfoDataAccessObject,
                gameLibraryPresenter);
        final GameLibraryController gameLibraryController = new GameLibraryController(
                gameLibraryInteractor);
        gameLibraryView.setGameLibraryController(gameLibraryController);
        return this;
    }

    /**
     * Adds the Game Select Use Case to the application.
     * @return this builder
     */
    public AppBuilder addGameSelectUseCase() {
        final GameSelectOutputBoundary gameSelectPresenter = new GameSelectPresenter(viewManagerModel,
                gameSetupViewModel,
                gameLibraryViewModel);
        final GameSelectInputBoundary gameSelectInteractor = new GameSelectInteractor(gameSelectPresenter,
                gameInfoDataAccessObject);

        final GameSelectController gameSelectController = new GameSelectController(gameSelectInteractor);
        gameLibraryView.setGameSelectController(gameSelectController);
        return this;
    }

    /**
     * Adds the Game Search Use Case to the application.
     * @return this builder
     */
    public AppBuilder addGameSearchUseCase() {
        final GameSearchOutputBoundary gameSearchPresenter = new GameSearchPresenter(viewManagerModel, 
                                                                                     gameLibraryViewModel);
        final GameSearchInputBoundary gameSearchInteractor = new GameSearchInteractor(gameSearchPresenter,
                gameInfoDataAccessObject);

        final GameSearchController gameSearchController = new GameSearchController(gameSearchInteractor);
        gameLibraryView.setGameSearchController(gameSearchController);
        return this;
    }

    /**
     * Adds the Game Setup Use Case to the application.
     * @return this builder
     */
    public AppBuilder addGameSetupUseCase() {
        final GameSetupOutputBoundary gameSetupPresenter = new GameSetupPresenter(viewManagerModel,
                gameLibraryViewModel);
        final GameSetupInputBoundary gameSetupInteractor = new GameSetupInteractor(gameSetupPresenter);
        final GameSetupController gameSetupController = new GameSetupController(gameSetupInteractor);

        gameSetupView.setGameSetupController(gameSetupController);

        return this;
    }

    /**
     * Adds the Blackjack Use Case to the application.
     * @return this builder
     */
    public AppBuilder addBlackjackUseCase() {
        final BlackjackOutputBoundary blackjackPresenter = new BlackjackPresenter(viewManagerModel, blackjackViewModel,
                gameLibraryViewModel);
        final BlackjackInputBoundary blackjackInteractor = new BlackjackInteractor(blackjackPresenter);
        final BlackjackController blackjackController = new BlackjackController(blackjackInteractor);

        blackjackView.setBlackjackController(blackjackController);

        return this;
    }

    /**
     * Creates the JFrame for the application and initially sets the SignupView to be displayed.
     * @return the application
     */
    public JFrame build() {
        final JFrame application = new JFrame("Lucky Aces");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        application.setResizable(false);

        application.add(mainPanel);

//        viewManagerModel.setState(gameLibraryView.getViewName());
//        viewManagerModel.firePropertyChanged();
//        gameLibraryViewModel.firePropertyChanged("build");

        // Quick add blackjack view

//        final BlackjackViewModel blackjackViewModel = new BlackjackViewModel();
//        final BlackjackView blackjackView = new BlackjackView(blackjackViewModel);
//        mainPanel.add(blackjackView, blackjackView.getViewName());
//
//        // Quick add blackjack use cases
//        final BlackjackOutputBoundary blackjackPresenter = new BlackjackPresenter(viewManagerModel, blackjackViewModel,
//                gameLibraryViewModel);
//        final BlackjackInputBoundary blackjackInteractor = new BlackjackInteractor(blackjackPresenter);
//        final BlackjackController blackjackController = new BlackjackController(blackjackInteractor);
//        blackjackView.setBlackjackController(blackjackController);

        // Quick startup
        viewManagerModel.setState(blackjackView.getViewName());
        viewManagerModel.firePropertyChanged();

        return application;
    }
}
