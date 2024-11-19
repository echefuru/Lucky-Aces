package view;

import interface_adapter.set_difficulty.SetDifficultyState;
import interface_adapter.set_difficulty.SetDifficultyController;
import interface_adapter.set_difficulty.SetDifficultyViewModel;

import java.awt.*;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.*;

/**
 * The View for when the user is setting up a game.
 */
public class GameRoomView extends JPanel implements PropertyChangeListener {

    private final String viewName = "Game Room";

    private final SetDifficultyViewModel setDifficultyViewModel;
    private SetDifficultyController setDifficultyController;

    private JLabel cardDeck;

    private final JLabel dealer;
    private JLabel cardsOfDealer;

    // TODO: private JLabel bankroll;

    private final JLabel player1id;
    private final JLabel player2id;

    private JLabel cardsOfPlayer1;
    private JLabel cardsOfPlayer2;

    // private JLabel betOfPlayer1;
    // private JLabel betOfPlayer2;

    private final JButton exitButton;
    private final JButton hitButton;
    private final JButton stayButton;

    public GameRoomView(SetDifficultyViewModel setDifficultyViewModel) {
        this.setDifficultyViewModel = setDifficultyViewModel;
        this.setDifficultyViewModel.addPropertyChangeListener(this);

        // top part: card deck, dealer
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
        deckAndDealerPanel.add(cardDeck);
        deckAndDealerPanel.add(dealerPanel);

        // middle part: players
        player1id = new JLabel("Player 1", SwingConstants.TOP);
        cardsOfPlayer1 = new JLabel("Cards of Player1", SwingConstants.BOTTOM);
        final JPanel player1Panel = new JPanel();
        player1Panel.setLayout(new BoxLayout(player1Panel, BoxLayout.Y_AXIS));
        player1Panel.add(player1id);
        player1Panel.add(cardsOfPlayer1);

        player2id = new JLabel("Player 2", SwingConstants.TOP);
        cardsOfPlayer2 = new JLabel("Cards of Player2", SwingConstants.BOTTOM);
        final JPanel player2Panel = new JPanel();
        player2Panel.setLayout(new BoxLayout(player2Panel, BoxLayout.Y_AXIS));
        player2Panel.add(player2id);
        player2Panel.add(cardsOfPlayer2);

        final JPanel playersPanel = new JPanel();
        playersPanel.setLayout(new FlowLayout());
        playersPanel.add(player1Panel);
        playersPanel.add(player2Panel);

        // bottom part: buttons-exit, hit, stay, etc.
        exitButton = new JButton("Exit");
        exitButton.setAlignmentX(Component.LEFT_ALIGNMENT);

        hitButton = new JButton("Hit");
        hitButton.setAlignmentX(Component.RIGHT_ALIGNMENT);

        stayButton = new JButton("Stay");
        stayButton.setAlignmentX(Component.RIGHT_ALIGNMENT);

        final JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        buttonPanel.add(exitButton);
        buttonPanel.add(hitButton);
        buttonPanel.add(stayButton);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(deckAndDealerPanel);
        this.add(playersPanel);
        this.add(buttonPanel);

        // TODO: add ActionListener for exit butto, hit button, and stay button.
        //        exitButton.addActionListener(
        //                evt -> {
        //                    if evt.getSource().equals(exitButton) {
        //
        //                    }
        //                }
        //        )
    }

    // TODO: add property change for changing of cards.
    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }

    public void setSetDifficultyController(SetDifficultyController setDifficultyController) {
        this.setDifficultyController = setDifficultyController;
    }
}
