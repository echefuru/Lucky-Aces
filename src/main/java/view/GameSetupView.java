package view;

import java.awt.Component;
import java.awt.Dimension;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;

import org.json.JSONObject;

import interface_adapter.ViewManagerModel;
import interface_adapter.game_library_select.GameLibraryViewModel;
import interface_adapter.game_setup.GameSetConfigController;
import interface_adapter.game_setup.GameSetupState;
import interface_adapter.game_setup.GameSetupViewModel;
import interface_adapter.game_setup.GameStartController;

/**
 * The View for when the user is setting up a game.
 */
public class GameSetupView extends JPanel implements PropertyChangeListener {

    private final String viewName;
    private final String parentViewName;
    private final GameSetupViewModel gameSetupViewModel;
    private final ViewManagerModel viewManagerModel;
    private GameStartController gameStartController;
    private GameSetConfigController gameSetConfigController;

    private String selectedGame;
    private String rules;

    private final JLabel gameName;
    private final JLabel gameDescription;

    private final JButton gameRules;
    private final JButton config;

    private final JButton play;

    private final JButton exit;

    public GameSetupView(GameSetupViewModel gameSetupViewModel, GameLibraryViewModel gameLibraryViewModel,
                         ViewManagerModel viewManagerModel) {
        this.gameSetupViewModel = gameSetupViewModel;
        this.gameSetupViewModel.addPropertyChangeListener(this);

        this.viewManagerModel = viewManagerModel;

        this.viewName = gameSetupViewModel.getViewName();
        this.parentViewName = gameLibraryViewModel.getViewName();

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
                        viewManagerModel.setState(parentViewName);
                        viewManagerModel.firePropertyChanged();
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

        config.addActionListener(
                evt -> {
                    if (evt.getSource().equals(config)) {
                        final GameSetupState state = gameSetupViewModel.getState();
                        final JSONObject gameConfig = state.getGameConfig();

                        // Show the config screen through a pop-up window.
                        showConfigPanel(gameConfig);
                        gameSetConfigController.execute(gameConfig);
                    }
                }
        );

        addContents(options, actions);
    }

    private void showConfigPanel(JSONObject gameConfig) {
        final CenterAlignedPanel configPanel = new CenterAlignedPanel(ViewConstants.PADDING,
                ViewConstants.LINE_HEIGHT);
        final Map<String, JSlider> sliders = new HashMap<>(gameConfig.length());

        for (String param : gameConfig.keySet()) {
            final JSONObject paramObj = gameConfig.getJSONObject(param);
            final String paramName = paramObj.getString("name");
            final int maxValue = paramObj.getInt("max_value");
            final int minValue = paramObj.getInt("min_value");
            final int currValue = paramObj.getInt("curr_value");

            final JLabel label = new JLabel(paramName);
            final JSlider slider = new JSlider(JSlider.HORIZONTAL, minValue, maxValue, currValue);

            slider.setMajorTickSpacing(paramObj.getInt("interval"));
            slider.setMinorTickSpacing(paramObj.getInt("interval") / ViewConstants.NUM_MINOR_TICKS_PER_MAJOR);
            slider.setPaintTicks(true);
            slider.setPaintLabels(true);
            slider.setSnapToTicks(true);
            slider.setFont(slider.getFont().deriveFont(ViewConstants.SLIDER_FONT_SIZE));

            sliders.put(param, slider);
            configPanel.addNewLine(label, slider);
        }
        final int result = JOptionPane.showConfirmDialog(null, configPanel, gameName.getText() + "Config",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            for (String param : gameConfig.keySet()) {
                final JSONObject paramObj = gameConfig.getJSONObject(param);
                paramObj.put("curr_value", sliders.get(param).getValue());
            }

            gameSetConfigController.execute(gameConfig);
        }
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

    public void setGameStartController(GameStartController gameStartController) {
        this.gameStartController = gameStartController;
    }

    public void setGameSetConfigController(GameSetConfigController gameSetConfigController) {
        this.gameSetConfigController = gameSetConfigController;
    }
}
