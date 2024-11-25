package view;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.*;

import interface_adapter.game_library_select.GameLibraryController;
import interface_adapter.game_library_select.GameLibraryState;
import interface_adapter.game_library_select.GameLibraryViewModel;
import interface_adapter.game_library_select.GameSelectController;

/**
 * The View for when the user first opens the program.
 */
public class GameLibraryView extends JPanel implements ActionListener, PropertyChangeListener {

    private final String viewName;
    private final GameLibraryViewModel gameLibraryViewModel;
    private GameLibraryController gameLibraryController;
    private GameSelectController gameSelectController;

    private final JButton search = new JButton("Search");
    private final JTextField searchInputField = new JTextField(15);

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

        search.addActionListener(
                 // This creates an anonymous subclass of ActionListener and instantiates it.
                 evt -> {
                    if (evt.getSource().equals(search)) {
                        final String info = searchInputField.getText();
                        gameLibraryController.search(info);
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
        else if (evt.getPropertyName().equals("search")) {
            final GameLibraryState state = gameLibraryViewModel.getState();
            final boolean[] availableGamesVisible = state.getAvailableGamesVisible();
            setGameVisible(availableGamesVisible);

            // Clears the error field
            state.setSelectGameError(null);
            errorField.setText("");
        }
        else if (evt.getPropertyName().equals("build")) {
            // Initialization Use Case which runs before any user action; on start-up action fired from the AppBuilder.
            gameLibraryController.execute();
            final String[] availableGames = gameLibraryViewModel.getState().getAvailableGames();
            setGameSelection(availableGames);
        }
    }

    public void setGameLibraryController(GameLibraryController gameLibraryController) {
        this.gameLibraryController = gameLibraryController;
    }

    public void setGameSelectController(GameSelectController gameSelectController) {
        this.gameSelectController = gameSelectController;
    }

    public String getViewName() {
        return viewName;
    }

    private void setGameSelection(String[] availableGames) {
        this.gameSelection.removeAll();
        this.gameSelection.revalidate();
        this.gameSelection.repaint();

        games = new JButton[availableGames.length];
        for (int i = 0; i < availableGames.length; i++) {
            games[i] = new JButton(availableGames[i]);
            games[i].setAlignmentX(Component.CENTER_ALIGNMENT);
            gameSelection.add(games[i]);
        }

        // TODO: These actions are next steps in program.
        for (JButton game : games) {
            game.addActionListener(
                    evt -> {
                        if (evt.getSource().equals(game)) {
                            gameSelectController.execute(game.getText());
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
}
