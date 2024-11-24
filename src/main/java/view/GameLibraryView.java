package view;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import interface_adapter.change_password.ChangePasswordState;
import interface_adapter.gamelibrary.GameLibraryController;
import interface_adapter.gamelibrary.GameLibraryState;
import interface_adapter.gamelibrary.GameLibraryViewModel;
import interface_adapter.gamelibrary.InitializationController;
import interface_adapter.logout.LogoutController;
import use_case.initialization.InitializationOutputData;

/**
 * The View for when the user first opens the program.
 */
public class GameLibraryView extends JPanel implements ActionListener, PropertyChangeListener {

    private final String viewName = "game_library_view";
    private final GameLibraryViewModel gameLibraryViewModel;
    private InitializationController initializationController;
    private GameLibraryController gameLibraryController;

    private final JButton search = new JButton("Search");
    private final JTextField searchInputField = new JTextField(15);

    private final JLabel errorField = new JLabel();

    private final JPanel gameSelection = new JPanel();
    private JButton[] games;

    // @SuppressWarnings("checkstyle:UnusedLocalVariable")
    public GameLibraryView(GameLibraryViewModel gameLibraryViewModel) {

        this.gameLibraryViewModel = gameLibraryViewModel;
        this.gameLibraryViewModel.addPropertyChangeListener(this);

        final JLabel title = new JLabel("Game Library Screen");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        // final JPanel errorPanel = new JPanel();
        // errorPanel.add(errorField);

        final JPanel searchPanel = new JPanel();
        searchPanel.add(searchInputField);
        searchPanel.add(search);

        search.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                evt -> {
                    if (evt.getSource().equals(search)) {
                        final String info = searchInputField.getText();
                        gameLibraryController.search(info);
                    }
                }
        );

//        changePassword.addActionListener(
//                // This creates an anonymous subclass of ActionListener and instantiates it.
//                evt -> {
//                    if (evt.getSource().equals(changePassword)) {
//                        final ChangePasswordState changePasswordState = new ChangePasswordState();
//                        changePasswordState.setPlayerID(playerID.getText());
//
//                        gameLibraryController.switchToChangePasswordView(changePasswordState);
//                    }
//                }
//        );

//        logOut.addActionListener(
//                // This creates an anonymous subclass of ActionListener and instantiates it.
//                evt -> {
//                    if (evt.getSource().equals(logOut)) {
//                        // execute the logout use case through the Controller
//                        // 1. get the state out of the loggedInViewModel. It contains the playerID.
//                        // 2. Execute the logout Controller.
//                        final GameLibraryState currentState = gameLibraryViewModel.getState();
//                        logoutController.execute(currentState.getPlayerID());
//                    }
//                }
//        );

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(searchPanel);
        this.add(gameSelection);
        this.add(errorField);
        // this.add(buttons);
    }

    // TODO: Why was this is loginView in lab5? Just because some buttons had listeners without impl where they just
    // passed the view itself as action listener, so this just sends the button click text out to show that no impl yet?
    /**
     * React to a button click that results in evt.
     * @param evt the ActionEvent to react to
     */
    @Override
    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
//        if (evt.getPropertyName().equals("state")) {
//            final GameLibraryState state = (GameLibraryState) evt.getNewValue();
//            this.errorField.setText(state.getSelectGameError());
//
//            // TODO why was this here?
//            // this.setGameSelection(state.getAvailableGames());
//        }
        if (evt.getPropertyName().equals("initialization")) {
            // Initialization Use Case which runs before any user action; on start-up action fired from the AppBuilder.
            initializationController.execute();
            final String[] availableGames = gameLibraryViewModel.getState().getAvailableGames();
            setGameSelection(availableGames);
        }
    }

    public String getViewName() {
        return viewName;
    }

    public void setInitializationController(InitializationController initializationController) {
        this.initializationController = initializationController;
    }

    public void setGameLibraryController(GameLibraryController gameLibraryController) {
        this.gameLibraryController = gameLibraryController;
    }

    private void setGameSelection(String[] availableGames) {
        games = new JButton[availableGames.length];
        for (int i = 0; i < availableGames.length; i++) {
            games[i] = new JButton(availableGames[i]);
            games[i].setAlignmentX(Component.CENTER_ALIGNMENT);
            games[i].setVisible(true);
            gameSelection.add(games[i]);
        }

        for (JButton game : games) {
            game.addActionListener(
                    evt -> {
                        if (evt.getSource().equals(game)) {
                            gameLibraryController.execute(game.getText());
                        }
                    }
            );
        }
    }
}
