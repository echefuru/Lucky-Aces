package view;

import java.awt.Component;
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
import interface_adapter.logout.LogoutController;

/**
 * The View for when the user is logged into the program.
 */
public class GameLibraryView extends JPanel implements PropertyChangeListener {

    private final String viewName = "Game Library";
    private final GameLibraryViewModel gameLibraryViewModel;
    private final JLabel passwordErrorField = new JLabel();
    private GameLibraryController gameLibraryController;
    private LogoutController logoutController;

    private final JLabel playerID;

    private final JButton search = new JButton("Search");
    private final JTextField searchInputField = new JTextField(15);

    private final JButton logOut = new JButton("Log Out");
    private final JButton changePassword = new JButton("Change Password");

    private JButton[] games;
    private final JPanel gameSelection = new JPanel();

    // @SuppressWarnings("checkstyle:UnusedLocalVariable")
    public GameLibraryView(GameLibraryViewModel gameLibraryViewModel) {
        this.gameLibraryViewModel = gameLibraryViewModel;
        this.gameLibraryViewModel.addPropertyChangeListener(this);

        final String[] availableGames = gameLibraryViewModel.getState().getAvailableGames();

        final JLabel title = new JLabel("Game Library Screen");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        gameSelection.setLayout(new BoxLayout(gameSelection, BoxLayout.Y_AXIS));
        gameSelection.setAlignmentX(Component.CENTER_ALIGNMENT);
        setGameSelection(availableGames);

        final JLabel playerIDInfo = new JLabel("Currently logged in as: ");
        playerIDInfo.setAlignmentX(Component.CENTER_ALIGNMENT);

        playerID = new JLabel();

        final JPanel searchPanel = new JPanel();
        searchPanel.add(searchInputField);
        searchPanel.add(search);

        final JPanel buttons = new JPanel();
        buttons.add(logOut);
        buttons.add(changePassword);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        changePassword.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                evt -> {
                    if (evt.getSource().equals(changePassword)) {
                        final ChangePasswordState changePasswordState = new ChangePasswordState();
                        changePasswordState.setPlayerID(playerID.getText());

                        gameLibraryController.switchToChangePasswordView(changePasswordState);
                    }
                }
        );

        logOut.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                evt -> {
                    if (evt.getSource().equals(logOut)) {
                        // execute the logout use case through the Controller
                        // 1. get the state out of the loggedInViewModel. It contains the playerID.
                        // 2. Execute the logout Controller.
                        final GameLibraryState currentState = gameLibraryViewModel.getState();
                        logoutController.execute(currentState.getPlayerID());
                    }
                }
        );

        for (JButton game : games) {
            game.addActionListener(
                    evt -> {
                        if (evt.getSource().equals(game)) {
                            gameLibraryController.execute(game.getText());
                        }
                    }
            );
        }

        search.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                evt -> {
                    if (evt.getSource().equals(search)) {
                        final String info = searchInputField.getText();
                        for (JButton button: games) {
                            setVisible(info, button);
                        }
                    }
                }
        );

        this.add(title);
        this.add(playerIDInfo);
        this.add(playerID);

        this.add(searchPanel);
        this.add(gameSelection);
        this.add(passwordErrorField);
        this.add(buttons);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("state")) {
            final GameLibraryState state = (GameLibraryState) evt.getNewValue();
            playerID.setText(state.getPlayerID());
            this.setGameSelection(state.getAvailableGames());
        }
    }

    public String getViewName() {
        return viewName;
    }

    public void setGameLibraryController(GameLibraryController gameLibraryController) {
        this.gameLibraryController = gameLibraryController;
    }

    public void setLogoutController(LogoutController logoutController) {
        // save the logout controller in the instance variable.
        this.logoutController = logoutController;
    }

    private void setGameSelection(String[] availableGames) {
        this.gameSelection.removeAll();

        this.games = new JButton[availableGames.length];
        for (int i = 0; i < availableGames.length; i++) {
            games[i] = new JButton(availableGames[i]);
            games[i].setAlignmentX(Component.CENTER_ALIGNMENT);
            gameSelection.add(games[i]);
        }
    }

    private void setVisible(String info, JButton button) {
        if ("".equals(info)) {
            button.setVisible(true);
        }
        else if (button.getText().toLowerCase().contains(info.toLowerCase())) {
            button.setVisible(true);
        }
        else {
            button.setVisible(false);
        }
    }
}
