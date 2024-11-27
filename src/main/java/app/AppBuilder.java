package app;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import data_access.GameInfoDataAccessObject;
import entity.player.CommonPlayerFactory;
import entity.player.PlayerFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.game_library_select.GameFilterController;
import interface_adapter.game_library_select.GameFilterPresenter;
import interface_adapter.game_library_select.GameLibraryController;
import interface_adapter.game_library_select.GameLibraryPresenter;
import interface_adapter.game_library_select.GameLibraryViewModel;
import interface_adapter.game_library_select.GameSearchController;
import interface_adapter.game_library_select.GameSearchPresenter;
import interface_adapter.game_library_select.GameSelectController;
import interface_adapter.game_library_select.GameSelectPresenter;
import interface_adapter.game_setup.GameSetConfigController;
import interface_adapter.game_setup.GameSetConfigPresenter;
import interface_adapter.game_setup.GameSetupViewModel;
import interface_adapter.game_setup.GameStartController;
import interface_adapter.game_setup.GameStartPresenter;
import use_case.game_filter.GameFilterInputBoundary;
import use_case.game_filter.GameFilterInteractor;
import use_case.game_filter.GameFilterOutputBoundary;
import use_case.game_library.GameLibraryInputBoundary;
import use_case.game_library.GameLibraryInteractor;
import use_case.game_library.GameLibraryOutputBoundary;
import use_case.game_search.GameSearchInputBoundary;
import use_case.game_search.GameSearchInteractor;
import use_case.game_search.GameSearchOutputBoundary;
import use_case.game_select.GameSelectInputBoundary;
import use_case.game_select.GameSelectInteractor;
import use_case.game_select.GameSelectOutputBoundary;
import use_case.game_set_config.GameSetConfigInputBoundary;
import use_case.game_set_config.GameSetConfigInteractor;
import use_case.game_set_config.GameSetConfigOutputBoundary;
import use_case.game_start.GameStartInputBoundary;
import use_case.game_start.GameStartInteractor;
import use_case.game_start.GameStartOutputBoundary;
import view.GameLibraryView;
import view.GameSetupView;
import view.ViewManager;

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
        gameSetupView = new GameSetupView(gameSetupViewModel, gameLibraryViewModel, viewManagerModel);
        mainPanel.add(gameSetupView, gameSetupView.getViewName());
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
     * Adds the Game Filter Use Case to the application.
     * @return this builder
     */
    public AppBuilder addGameFilterUseCase() {
        final GameFilterOutputBoundary gameFilterPresenter = new GameFilterPresenter(viewManagerModel,
                gameLibraryViewModel);
        final GameFilterInputBoundary gameFilterInteractor = new GameFilterInteractor(gameFilterPresenter,
                gameInfoDataAccessObject);

        final GameFilterController gameFilterController = new GameFilterController(gameFilterInteractor);
        gameLibraryView.setGameFilterController(gameFilterController);
        return this;
    }

    /**
     * Adds the Game Start Use Case to the application.
     * @return this builder
     */
    public AppBuilder addGameStartUseCase() {
        final GameStartOutputBoundary gameSetupPresenter = new GameStartPresenter();
        final GameStartInputBoundary gameSetupInteractor = new GameStartInteractor(gameSetupPresenter);
        final GameStartController gameStartController = new GameStartController(gameSetupInteractor);

        gameSetupView.setGameStartController(gameStartController);

        return this;
    }

    /**
     * Adds tue Set Config Use Case to the application.
     * @return this builder
     */
    public AppBuilder addGameSetConfigUseCase() {
        final GameSetConfigOutputBoundary gameSetConfigPresenter = new GameSetConfigPresenter(gameSetupViewModel);
        final GameSetConfigInputBoundary gameSetConfigInteractor = new GameSetConfigInteractor(gameSetConfigPresenter);
        final GameSetConfigController gameSetConfigController = new GameSetConfigController(gameSetConfigInteractor);

        gameSetupView.setGameSetConfigController(gameSetConfigController);

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

        viewManagerModel.setState(gameLibraryView.getViewName());
        viewManagerModel.firePropertyChanged();
        gameLibraryViewModel.firePropertyChanged("build");

        return application;
    }
}
