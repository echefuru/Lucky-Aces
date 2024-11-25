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

import interface_adapter.game_library_select.GameSearchController;
import interface_adapter.game_library_select.GameSelectController;
import interface_adapter.game_library_select.GameLibraryViewModel;
import interface_adapter.game_library_select.GameLibraryController;

/**
 * The View for when the user first opens the program.
 */
public class GameLibraryView extends JPanel implements ActionListener, PropertyChangeListener {

    private final String viewName = "game_library_view";
    private final GameLibraryViewModel gameLibraryViewModel;
    private GameLibraryController gameLibraryController;
    private GameSelectController gameSelectController;
    private GameSearchController gameSearchController;

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
                        final String searchInput = searchInputField.getText();
                        gameSearchController.execute(searchInput);
                    }
                 }
        );

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(searchPanel);
        this.add(gameSelection);
        this.add(errorField);
    }

    // Why was this is loginView in lab5? Just because some buttons had listeners without impl where they just
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
        if (evt.getPropertyName().equals("state")) {
            final String[] availableGames = gameLibraryViewModel.getState().getAvailableGames();
            setGameSelection(availableGames);
            // TODO: Update this.
            errorField.setText(gameLibraryViewModel.getState().getSelectGameError());
        }
        else if (evt.getPropertyName().equals("search")) {
            final String[] availableGames = gameLibraryViewModel.getState().getAvailableGames();
            final boolean[] availableGamesVisible = gameLibraryViewModel.getState().getAvailableGamesVisible();
            setSearchSelection(availableGames, availableGamesVisible);
        }
        else if (evt.getPropertyName().equals("build")) {
            // Initialization Use Case which runs before any user action; on start-up action fired from the AppBuilder.
            gameLibraryController.execute();
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
            games[i].setVisible(true);
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

    private void setSearchSelection(String[] availableGames, boolean[] availableGamesVisible) {
        this.gameSelection.removeAll();
        this.gameSelection.revalidate();
        this.gameSelection.repaint();

        games = new JButton[availableGames.length];
        for (int i = 0; i < availableGames.length; i++) {
            games[i] = new JButton(availableGames[i]);
            games[i].setAlignmentX(Component.CENTER_ALIGNMENT);
            games[i].setVisible(availableGamesVisible[i]);
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
}
