package view;

import java.awt.*;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.*;

import interface_adapter.set_difficulty.SetDifficultyController;
import interface_adapter.set_difficulty.SetDifficultyState;
import interface_adapter.set_difficulty.SetDifficultyViewModel;

/**
 * The View for when the user is setting up a game.
 */
public class GameRoomView extends JPanel implements PropertyChangeListener {

    private final String viewName = "Game Room";

    private final SetDifficultyViewModel setDifficultyViewModel;
    private SetDifficultyController setDifficultyController;

    private JLabel roomName;
    private JLabel cardDeck;

    private final JLabel dealer;
    private JLabel cardsOfDealer;

    // TODO: private JLabel bankroll;

    private final JLabel player1id;
    private final JLabel player2id;

    private JLabel cardsOfPlayer1;
    private JLabel cardsOfPlayer2;

    private JTextField player1difficultyInput;
    private JTextField player2difficultyInput;

    // private JLabel betOfPlayer1;
    // private JLabel betOfPlayer2;

    private JLabel errorField;

    private final JButton exitButton;
    private final JButton saveButton;
    private final JButton hitButton;
    private final JButton stayButton;

    public GameRoomView(SetDifficultyViewModel setDifficultyViewModel) {
        this.setDifficultyViewModel = setDifficultyViewModel;
        this.setDifficultyViewModel.addPropertyChangeListener(this);

        // top part: card deck, dealer
        roomName = new JLabel("Room Name");
        roomName.setAlignmentX(Component.LEFT_ALIGNMENT);

        cardDeck = new JLabel("Card Deck");
        cardDeck.setAlignmentX(Component.LEFT_ALIGNMENT);

        dealer = new JLabel("Dealer");
        dealer.setAlignmentX(Component.CENTER_ALIGNMENT);
        cardsOfDealer = new JLabel("Cards of Dealer");
        cardsOfDealer.setAlignmentX(Component.CENTER_ALIGNMENT);

        final JPanel dealerPanel = new JPanel();
        dealerPanel.setLayout(new BoxLayout(dealerPanel, BoxLayout.Y_AXIS));
        dealerPanel.add(dealer);
        dealerPanel.add(cardsOfDealer);
        dealerPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        final JPanel deckAndDealerPanel = new JPanel();
        deckAndDealerPanel.setLayout(new BoxLayout(deckAndDealerPanel, BoxLayout.X_AXIS));
        deckAndDealerPanel.add(roomName);
        deckAndDealerPanel.add(cardDeck);
        deckAndDealerPanel.add(dealerPanel);

        // middle part: players
        player1id = new JLabel("Player 1", SwingConstants.TOP);
        cardsOfPlayer1 = new JLabel("Cards of Player1", SwingConstants.TOP);
        player1difficultyInput = new JTextField(2);
        final LabelTextPanel player1Difficulty = new LabelTextPanel(new JLabel("player difficulty (1 or 2):"),
                player1difficultyInput, 2, 8);
        final JPanel player1Panel = new JPanel();
        player1Panel.setLayout(new BoxLayout(player1Panel, BoxLayout.Y_AXIS));
        player1Panel.add(player1id);
        player1Panel.add(cardsOfPlayer1);
        player1Panel.add(player1Difficulty);

        player2id = new JLabel("Player 2", SwingConstants.TOP);
        cardsOfPlayer2 = new JLabel("Cards of Player2", SwingConstants.TOP);
        player2difficultyInput = new JTextField(2);
        final LabelTextPanel player2difficulty = new LabelTextPanel(new JLabel("player difficulty (1 or 2):"),
                player2difficultyInput, 2, 8);
        final JPanel player2Panel = new JPanel();
        player2Panel.setLayout(new BoxLayout(player2Panel, BoxLayout.Y_AXIS));
        player2Panel.add(player2id);
        player2Panel.add(cardsOfPlayer2);
        player2Panel.add(player2difficulty);

        final JPanel playersPanel = new JPanel();
        playersPanel.setLayout(new FlowLayout());
        playersPanel.add(player1Panel);
        playersPanel.add(player2Panel);
        playersPanel.add(errorField);

        // bottom part: buttons-exit, hit, stay, etc.
        exitButton = new JButton("Exit");
        exitButton.setAlignmentX(Component.LEFT_ALIGNMENT);

        saveButton = new JButton("save");
        saveButton.setAlignmentX(Component.LEFT_ALIGNMENT);

        hitButton = new JButton("Hit");
        hitButton.setAlignmentX(Component.RIGHT_ALIGNMENT);

        stayButton = new JButton("Stay");
        stayButton.setAlignmentX(Component.RIGHT_ALIGNMENT);

        final JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        buttonPanel.add(saveButton);
        buttonPanel.add(exitButton);
        buttonPanel.add(hitButton);
        buttonPanel.add(stayButton);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(deckAndDealerPanel);
        this.add(playersPanel);
        this.add(buttonPanel);

        // TODO: add ActionListener for exit button, hit button, and stay button.
        saveButton.addActionListener(
                evt -> {
                    if (evt.getSource().equals(saveButton)) {
                        setDifficultyController.execute(
                                player1difficultyInput.getText(), player1id.getText(), roomName.getText());
                    }
                }
        );
    }

    // TODO: add property change for changing of cards,changing of difficulty
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("state")) {
            final SetDifficultyState state = (SetDifficultyState) evt.getNewValue();
            this.errorField.setText(state.getErrorMessage());
        }
    }

    public void setSetDifficultyController(SetDifficultyController setDifficultyController) {
        this.setDifficultyController = setDifficultyController;
    }

    public String getViewName() {
        return viewName;
    }

}
