package view;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import interface_adapter.game_library_select.GameFilterController;
import interface_adapter.game_library_select.GameLibraryController;
import interface_adapter.game_library_select.GameLibraryState;
import interface_adapter.game_library_select.GameLibraryViewModel;
import interface_adapter.game_library_select.GameSearchController;
import interface_adapter.game_library_select.GameSelectController;

/**
 * The View for when the user first opens the program.
 */
public class GameLibraryView extends JPanel implements ActionListener, PropertyChangeListener {

    private final String viewName;
    private final GameLibraryViewModel gameLibraryViewModel;
    private GameLibraryController gameLibraryController;
    private GameSelectController gameSelectController;
    private GameSearchController gameSearchController;
    private GameFilterController gameFilterController;

    private final JButton search = new JButton("Search");
    private final JTextField searchInputField = new JTextField(15);

    private final String filterText = "Filter";
    private final JButton filter = new JButton("Filter");
    private JLabel selectLabel = new JLabel("<html>Search is not activated.<br>Filter is not activated.<html>");

    private final JLabel errorField = new JLabel();

    private final JPanel gameSelection = new JPanel();
    private JButton[] games;

    // @SuppressWarnings("checkstyle:UnusedLocalVariable")
    public GameLibraryView(GameLibraryViewModel gameLibraryViewModel) {

        this.gameLibraryViewModel = gameLibraryViewModel;
        this.gameLibraryViewModel.addPropertyChangeListener(this);
        this.viewName = gameLibraryViewModel.getViewName();

        final JLabel title = new JLabel("Game Library");
        title.setFont(title.getFont().deriveFont(ViewConstants.TITLE_FONT_SIZE));

        final JLabel titleLogo = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("logo.png")));

        final JPanel searchPanel = new JPanel();
        searchPanel.add(searchInputField);
        searchPanel.add(search);
        searchPanel.add(filter);

        search.addActionListener(
                 // This creates an anonymous subclass of ActionListener and instantiates it.
                 evt -> {
                    if (evt.getSource().equals(search)) {
                        final String searchInput = searchInputField.getText();
                        gameSearchController.execute(searchInput);
                    }
                 }
        );

        filter.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                evt -> {
                    if (evt.getSource().equals(filter)) {
                        final GameLibraryState state = gameLibraryViewModel.getState();
                        final String[] options = state.getGameTypes();
                        executeFilter(options);
                    }
                }
        );

        addContent(title, searchPanel, titleLogo);
    }

    private void addContent(JLabel title, JPanel searchPanel, JLabel titleLogo) {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        titleLogo.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(titleLogo);

        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        this.add(title);

        searchPanel.setMaximumSize(new Dimension(ViewConstants.WINDOW_WIDTH, ViewConstants.LINE_HEIGHT));
        this.add(searchPanel);

        selectLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        selectLabel.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(selectLabel);

        errorField.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(errorField);

        gameSelection.setLayout(new BoxLayout(gameSelection, BoxLayout.Y_AXIS));
        this.add(gameSelection);

    }

    // Placeholder for checking button clicks
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
        if (evt.getPropertyName().equals("state")) {
            // TODO: Update this.
            errorField.setText(gameLibraryViewModel.getState().getSelectGameError());
        }
        else if (evt.getPropertyName().equals("gameSelect")) {
            final GameLibraryState state = gameLibraryViewModel.getState();
            final boolean[] availableGamesVisible = state.getAvailableGamesVisible();
            setGameVisible(availableGamesVisible);
            selectLabel.setText(state.getFilterMessage());
            searchInputField.setText("");

            // Clears the error field
            state.setSelectGameError(null);
            errorField.setText("");
        }
        else if (evt.getPropertyName().equals("build")) {
            // Initialization Use Case which runs before any user action; on start-up action fired from the AppBuilder.
            gameLibraryController.execute();
            final GameLibraryState state = gameLibraryViewModel.getState();
            setGameSelection(state.getAvailableGames(), state.getAvailableGameNames());
        }
    }

    public void setGameLibraryController(GameLibraryController gameLibraryController) {
        this.gameLibraryController = gameLibraryController;
    }

    public void setGameSelectController(GameSelectController gameSelectController) {
        this.gameSelectController = gameSelectController;
    }

    public void setGameSearchController(GameSearchController gameSearchController) {
        this.gameSearchController = gameSearchController;
    }

    public void setGameFilterController(GameFilterController gameFilterController) {
        this.gameFilterController = gameFilterController;
    }

    public String getViewName() {
        return viewName;
    }

    private void setGameSelection(String[] availableGames, String[] availableGamesNames) {
        this.gameSelection.removeAll();
        this.gameSelection.revalidate();
        this.gameSelection.repaint();

        games = new JButton[availableGames.length];
        for (int i = 0; i < availableGames.length; i++) {
            final JButton gameButton = new JButton(availableGamesNames[i]);
            final String game = availableGames[i];

            gameButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            gameSelection.add(gameButton);
            games[i] = gameButton;

            // TODO: These actions are next steps in program.

            gameButton.addActionListener(
                    evt -> {
                        if (evt.getSource().equals(gameButton)) {
                            gameSelectController.execute(game);
                        }
                    }
            );
        }
    }

    private void setGameVisible(boolean[] availableGamesVisible) {
        for (int i = 0; i < games.length; i++) {
            games[i].setVisible(availableGamesVisible[i]);
        }
    }

    private void executeFilter(String[] options) {
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
                gameFilterController.execute(type, -1);
                break;
            }
            try {
                final int playerCount = Integer.parseInt(playerInput);
                if (playerCount >= 0) {
                    gameFilterController.execute(type, playerCount);
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
