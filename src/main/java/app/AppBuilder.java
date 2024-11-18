package app;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import data_access.GameListDataAccessObject;
import data_access.InMemoryUserDataAccessObject;
import entity.game_info.GameInfo;
import entity.game_info.GameInfoFactory;
import entity.player.CommonPlayerFactory;
import entity.player.PlayerFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.change_password.ChangePasswordController;
import interface_adapter.change_password.ChangePasswordPresenter;
import interface_adapter.change_password.ChangePasswordViewModel;
import interface_adapter.game_setup.GameSetupController;
import interface_adapter.game_setup.GameSetupPresenter;
import interface_adapter.game_setup.GameSetupViewModel;
import interface_adapter.gamelibrary.GameLibraryController;
import interface_adapter.gamelibrary.GameLibraryPresenter;
import interface_adapter.gamelibrary.GameLibraryViewModel;
import interface_adapter.login.LoginController;
import interface_adapter.login.LoginPresenter;
import interface_adapter.login.LoginViewModel;
import interface_adapter.logout.LogoutController;
import interface_adapter.logout.LogoutPresenter;
import interface_adapter.signup.SignupController;
import interface_adapter.signup.SignupPresenter;
import interface_adapter.signup.SignupViewModel;
import use_case.change_password.ChangePasswordInputBoundary;
import use_case.change_password.ChangePasswordInteractor;
import use_case.change_password.ChangePasswordOutputBoundary;
import use_case.game_setup.GameSetupInputBoundary;
import use_case.game_setup.GameSetupInteractor;
import use_case.game_setup.GameSetupOutputBoundary;
import use_case.gamelibrary.GameLibraryInputBoundary;
import use_case.gamelibrary.GameLibraryInteractor;
import use_case.gamelibrary.GameLibraryOutputBoundary;
import use_case.login.LoginInputBoundary;
import use_case.login.LoginInteractor;
import use_case.login.LoginOutputBoundary;
import use_case.logout.LogoutInputBoundary;
import use_case.logout.LogoutInteractor;
import use_case.logout.LogoutOutputBoundary;
import use_case.signup.SignupInputBoundary;
import use_case.signup.SignupInteractor;
import use_case.signup.SignupOutputBoundary;
import view.ChangePasswordView;
import view.GameLibraryView;
import view.GameSetupView;
import view.LoginView;
import view.SignupView;
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
    private final JPanel cardPanel = new JPanel();
    private final CardLayout cardLayout = new CardLayout();
    // thought question: is the hard dependency below a problem?
    private final PlayerFactory playerFactory = new CommonPlayerFactory();
    private final GameInfoFactory gameInfoFactory = new GameInfoFactory();
    private final ViewManagerModel viewManagerModel = new ViewManagerModel();
    private final ViewManager viewManager = new ViewManager(cardPanel, cardLayout, viewManagerModel);

    // thought question: is the hard dependency below a problem?
    private final InMemoryUserDataAccessObject userDataAccessObject = new InMemoryUserDataAccessObject();
    private final GameListDataAccessObject gameListDataAccessObject = new GameListDataAccessObject();

    private SignupView signupView;
    private SignupViewModel signupViewModel;
    private LoginView loginView;
    private LoginViewModel loginViewModel;
    private GameLibraryView gameLibraryView;
    private GameLibraryViewModel gameLibraryViewModel;
    private GameSetupView gameSetupView;
    private GameSetupViewModel gameSetupViewModel;
    private ChangePasswordView changepasswordView;
    private ChangePasswordViewModel changepasswordViewModel;

    public AppBuilder() {
        cardPanel.setLayout(cardLayout);
    }

    /**
     * Adds the Change Password View to the application.
     * @return this builder
     */
    public AppBuilder addChangePasswordView() {
        changepasswordViewModel = new ChangePasswordViewModel();
        changepasswordView = new ChangePasswordView(changepasswordViewModel);
        cardPanel.add(changepasswordView, changepasswordView.getViewName());
        return this;
    }

    /**
     * Adds the Signup View to the application.
     * @return this builder
     */
    public AppBuilder addSignupView() {
        signupViewModel = new SignupViewModel();
        signupView = new SignupView(signupViewModel);
        cardPanel.add(signupView, signupView.getViewName());
        return this;
    }

    /**
     * Adds the Login View to the application.
     * @return this builder
     */
    public AppBuilder addLoginView() {
        loginViewModel = new LoginViewModel();
        loginView = new LoginView(loginViewModel);
        cardPanel.add(loginView, loginView.getViewName());
        return this;
    }

    /**
     * Adds the Game Library View to the application.
     * @return this builder
     */
    public AppBuilder addGameLibraryView() {
        gameLibraryViewModel = new GameLibraryViewModel();
        gameLibraryView = new GameLibraryView(gameLibraryViewModel);
        cardPanel.add(gameLibraryView, gameLibraryView.getViewName());
        return this;
    }

    /**
     * Adds the Game Setup View to the application.
     * @return this builder
     */
    public AppBuilder addGameSetupView() {
        gameSetupViewModel = new GameSetupViewModel();
        gameSetupView = new GameSetupView(gameSetupViewModel);
        cardPanel.add(gameSetupView, gameSetupView.getViewName());
        return this;
    }

    /**
     * Adds the Signup Use Case to the application.
     * @return this builder
     */
    public AppBuilder addSignupUseCase() {
        final SignupOutputBoundary signupOutputBoundary = new SignupPresenter(viewManagerModel,
                signupViewModel, loginViewModel);
        final SignupInputBoundary userSignupInteractor = new SignupInteractor(
                userDataAccessObject, signupOutputBoundary, playerFactory);

        final SignupController controller = new SignupController(userSignupInteractor);
        signupView.setSignupController(controller);
        return this;
    }

    /**
     * Adds the Login Use Case to the application.
     * @return this builder
     */
    public AppBuilder addLoginUseCase() {
        final LoginOutputBoundary loginOutputBoundary = new LoginPresenter(viewManagerModel,
                gameLibraryViewModel, loginViewModel, signupViewModel);
        final LoginInputBoundary loginInteractor = new LoginInteractor(
                userDataAccessObject, loginOutputBoundary, gameListDataAccessObject);

        final LoginController loginController = new LoginController(loginInteractor);
        loginView.setLoginController(loginController);
        return this;
    }

    /**
     * Adds the Change Password Use Case to the application.
     * @return this builder
     */
    public AppBuilder addChangePasswordUseCase() {
        final ChangePasswordOutputBoundary changePasswordOutputBoundary =
                new ChangePasswordPresenter(viewManagerModel, changepasswordViewModel, loginViewModel,
                        gameLibraryViewModel);

        final ChangePasswordInputBoundary changePasswordInteractor =
                new ChangePasswordInteractor(userDataAccessObject, changePasswordOutputBoundary, playerFactory);

        final ChangePasswordController changePasswordController =
                new ChangePasswordController(changePasswordInteractor);

        changepasswordView.setChangePasswordController(changePasswordController);
        return this;
    }

    /**
     * Adds the Game Library Case to the application.
     * @return this builder
     */
    public AppBuilder addGameLibraryUseCase() {
        final GameLibraryOutputBoundary gameLibraryOutputBoundary = new GameLibraryPresenter(viewManagerModel,
                changepasswordViewModel,
                gameSetupViewModel,
                gameLibraryViewModel);
        final GameLibraryInputBoundary gameLibraryInteractor = new GameLibraryInteractor(gameLibraryOutputBoundary,
                gameListDataAccessObject, gameInfoFactory);

        final GameLibraryController gameLibraryController = new GameLibraryController(gameLibraryInteractor);
        gameLibraryView.setGameLibraryController(gameLibraryController);
        return this;
    }

    /**
     * Adds the Game Setup Use Case to the application.
     * @return this builder
     */
    public AppBuilder addGameSetupUseCase() {
        final GameSetupOutputBoundary gameSetupOutputBoundary = new GameSetupPresenter(viewManagerModel,
                gameLibraryViewModel);
        final GameSetupInputBoundary gameSetupInteractor = new GameSetupInteractor(gameSetupOutputBoundary);
        final GameSetupController gameSetupController = new GameSetupController(gameSetupInteractor);

        gameSetupView.setGameSetupController(gameSetupController);

        return this;
    }

    /**
     * Adds the Logout Use Case to the application.
     * @return this builder
     */
    public AppBuilder addLogoutUseCase() {
        final LogoutOutputBoundary logoutOutputBoundary = new LogoutPresenter(viewManagerModel,
                gameLibraryViewModel, loginViewModel);

        final LogoutInputBoundary logoutInteractor =
                new LogoutInteractor(userDataAccessObject, logoutOutputBoundary);

        final LogoutController logoutController = new LogoutController(logoutInteractor);
        gameLibraryView.setLogoutController(logoutController);
        return this;
    }

    /**
     * Creates the JFrame for the application and initially sets the SignupView to be displayed.
     * @return the application
     */
    public JFrame build() {
        final JFrame application = new JFrame("Lucky Aces");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        application.add(cardPanel);

        viewManagerModel.setState(signupView.getViewName());
        viewManagerModel.firePropertyChanged();

        return application;
    }
}
