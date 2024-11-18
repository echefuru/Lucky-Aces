package view;

import java.awt.Component;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import interface_adapter.game_setup.GameSetupController;
import interface_adapter.game_setup.GameSetupState;
import interface_adapter.game_setup.GameSetupViewModel;

/**
 * The View for when the user is setting up a game.
 */
public class GameSetupView extends JPanel implements PropertyChangeListener {

    private final String viewName = "Game Setup";
    private final GameSetupViewModel gameSetupViewModel;
    private GameSetupController gameSetupController;

    private final JLabel gameName;

    private final JButton gameRules;
    private final JButton config;

    private final JButton play;

    private final JButton exit;

    public GameSetupView(GameSetupViewModel gameSetupViewModel) {
        this.gameSetupViewModel = gameSetupViewModel;
        this.gameSetupViewModel.addPropertyChangeListener(this);

        gameName = new JLabel();
        gameName.setAlignmentX(Component.CENTER_ALIGNMENT);

        final JPanel options = new JPanel();
        options.setLayout(new BoxLayout(options, BoxLayout.Y_AXIS));
        options.setAlignmentX(Component.CENTER_ALIGNMENT);

        gameRules = new JButton("Game Rules");
        gameRules.setAlignmentX(Component.CENTER_ALIGNMENT);
        options.add(gameRules);

        config = new JButton("Config");
        config.setAlignmentX(Component.CENTER_ALIGNMENT);
        options.add(config);

        final JPanel actions = new JPanel();
        actions.setLayout(new BoxLayout(actions, BoxLayout.X_AXIS));
        actions.setAlignmentX(Component.CENTER_ALIGNMENT);

        exit = new JButton("Return to Game Library");
        exit.setAlignmentX(Component.LEFT_ALIGNMENT);
        actions.add(exit);

        play = new JButton("Play");
        play.setAlignmentX(Component.RIGHT_ALIGNMENT);
        actions.add(play);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        exit.addActionListener(
                evt -> {
                    if (evt.getSource().equals(exit)) {
                        gameSetupController.switchToGameLibraryView();
                    }
                }
        );

        this.add(gameName);
        this.add(options);
        this.add(actions);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("state")) {
            final GameSetupState state = (GameSetupState) evt.getNewValue();
            gameName.setText(state.getSelectedGame());
        }
    }

    public String getViewName() {
        return viewName;
    }

    public void setGameSetupController(GameSetupController gameSetupController) {
        this.gameSetupController = gameSetupController;
    }
}
