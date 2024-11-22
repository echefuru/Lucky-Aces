package view;

import java.awt.Component;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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

    private final String filterText = "Filter";
    private final JButton filter = new JButton(filterText);

    private final JLabel errorField = new JLabel();

    private JButton[] games;
    private final JPanel gameSelection = new JPanel();

    private final JButton logOut = new JButton("Log Out");
    private final JButton changePassword = new JButton("Change Password");

    // @SuppressWarnings("checkstyle:UnusedLocalVariable")
    public GameLibraryView(GameLibraryViewModel gameLibraryViewModel) {
        this.gameLibraryViewModel = gameLibraryViewModel;
        this.gameLibraryViewModel.addPropertyChangeListener(this);

        final JLabel title = new JLabel("Game Library Screen");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        final JLabel playerIDInfo = new JLabel("Currently logged in as: ");
        playerIDInfo.setAlignmentX(Component.CENTER_ALIGNMENT);

        playerID = new JLabel();

        final JPanel searchPanel = new JPanel();
        searchPanel.add(searchInputField);
        searchPanel.add(search);
        searchPanel.add(filter);

        final JPanel errorPanel = new JPanel();
        errorPanel.add(errorField);

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

        search.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                evt -> {
                    if (evt.getSource().equals(search)) {
                        final String info = searchInputField.getText();
                        gameLibraryController.search(info);
                    }
                }
        );

        filter.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                evt -> {
                    if (evt.getSource().equals(filter)) {
                        gameLibraryController.executeFilter();
                    }
                }
        );

        this.add(title);
        this.add(playerIDInfo);
        this.add(playerID);

        this.add(searchPanel);
        this.add(errorPanel);
        this.add(gameSelection);
        this.add(passwordErrorField);
        this.add(buttons);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("state")) {
            final GameLibraryState state = (GameLibraryState) evt.getNewValue();
            playerID.setText(state.getPlayerID());
            this.errorField.setText(state.getSelectGameError());

            this.setGameSelection(state.getAvailableGames(), state.getAvailableGamesVisible());
        }
        else if (evt.getPropertyName().equals(filterText.toLowerCase())) {
            final GameLibraryState state = (GameLibraryState) evt.getNewValue();
            final String[] options = state.getGameTypes();

            final int typeInput = JOptionPane.showOptionDialog(
                    null,
                    "<html>Please select the type you want to play:<br>"
                            + "(If you don't want to limit the type of games, you can just close the window.)</html>",
                    filterText, JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
            final String type;
            if (typeInput == -1) {
                type = "";
            }
            else {
                type = options[typeInput];
            }

            while (true) {
                final String playerInput = JOptionPane.showInputDialog(
                        null,
                        "<html>Please enter the specific number of players you want:<br>"
                                + "(If you don't want to limit the number of players, "
                                + "you can just close or cancel the window.)</html>",
                        filterText, JOptionPane.QUESTION_MESSAGE
                );

                if (playerInput == null) {
                    gameLibraryController.filter(type, -1);
                    break;
                }
                try {
                    final int playerCount = Integer.parseInt(playerInput);
                    if (playerCount >= 0) {
                        gameLibraryController.filter(type, playerCount);
                        break;
                    }
                    else {
                        JOptionPane.showMessageDialog(
                                null, "Please enter a number greater or equal to 0!",
                                "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
                catch (NumberFormatException exception) {
                    JOptionPane.showMessageDialog(
                            null, "Invalid input, please enter an integer!",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
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

    private void setGameSelection(String[] availableGames, boolean[] availableGamesVisible) {
        this.gameSelection.removeAll();
        this.gameSelection.revalidate();
        this.gameSelection.repaint();

        this.games = new JButton[availableGames.length];
        for (int i = 0; i < availableGames.length; i++) {
            games[i] = new JButton(availableGames[i]);
            games[i].setAlignmentX(Component.CENTER_ALIGNMENT);
            games[i].setVisible(availableGamesVisible[i]);
            gameSelection.add(games[i]);
        }

        gameSelection.setLayout(new BoxLayout(gameSelection, BoxLayout.Y_AXIS));
        gameSelection.setAlignmentX(Component.CENTER_ALIGNMENT);

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
