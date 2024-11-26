package view;

import java.awt.Component;
import java.awt.Dimension;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import interface_adapter.game_setup.GameSetupController;
import interface_adapter.game_setup.GameSetupState;
import interface_adapter.game_setup.GameSetupViewModel;

/**
 * The View for when the user is setting up a game.
 */
public class GameSetupView extends JPanel implements PropertyChangeListener {

    private final String viewName;
    private final GameSetupViewModel gameSetupViewModel;
    private GameSetupController gameSetupController;

    private String selectedGame;
    private String rules;

    private final JLabel gameName;
    private final JLabel gameDescription;

    private final JButton gameRules;
    private final JButton config;

    private final JButton play;

    private final JButton exit;

    public GameSetupView(GameSetupViewModel gameSetupViewModel) {
        this.gameSetupViewModel = gameSetupViewModel;
        this.gameSetupViewModel.addPropertyChangeListener(this);
        this.viewName = gameSetupViewModel.getViewName();

        gameName = new JLabel();
        gameName.setFont(gameName.getFont().deriveFont(ViewConstants.TITLE_FONT_SIZE));

        gameDescription = new JLabel();

        final JPanel options = new JPanel();
        options.setLayout(new BoxLayout(options, BoxLayout.Y_AXIS));

        gameRules = new JButton("Game Rules");
        gameRules.setAlignmentX(Component.CENTER_ALIGNMENT);
        options.add(gameRules);

        config = new JButton("Config");
        config.setAlignmentX(Component.CENTER_ALIGNMENT);
        options.add(config);

        final JPanel actions = new JPanel();
        actions.setAlignmentX(Component.CENTER_ALIGNMENT);

        exit = new JButton("Return to Game Library");
        exit.setAlignmentX(Component.LEFT_ALIGNMENT);
        actions.add(exit);

        play = new JButton("Play");
        play.setAlignmentX(Component.RIGHT_ALIGNMENT);
        actions.add(play);

        exit.addActionListener(
                evt -> {
                    if (evt.getSource().equals(exit)) {
                        gameSetupController.switchToGameLibraryView();
                    }
                }
        );

        gameRules.addActionListener(
                evt -> {
                    if (evt.getSource().equals(gameRules)) {
                        // Show the game rules through a pop-up window.
                        JOptionPane.showMessageDialog(null, rules, gameName.getText() + " Rules",
                                JOptionPane.PLAIN_MESSAGE);
                    }
                }
        );

        addContents(options, actions);
    }

    private void addContents(JPanel options, JPanel actions) {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(Box.createRigidArea(new Dimension(ViewConstants.WINDOW_WIDTH, ViewConstants.QUARTER_HEIGHT)));

        gameName.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(gameName);

        gameDescription.setAlignmentX(Component.CENTER_ALIGNMENT);
        gameDescription.setMaximumSize(new Dimension(ViewConstants.THIRD_WIDTH, ViewConstants.WINDOW_HEIGHT));
        this.add(gameDescription);

        this.add(options);

        this.add(actions);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("state")) {
            final GameSetupState state = (GameSetupState) evt.getNewValue();
            selectedGame = state.getSelectedGame();
            rules = state.getGameRules();
            gameName.setText(state.getGameName());
            gameDescription.setText("<html>" + state.getGameDescription() + "</html>");
        }
    }

    public String getViewName() {
        return viewName;
    }

    public void setGameSetupController(GameSetupController gameSetupController) {
        this.gameSetupController = gameSetupController;
    }
}
