package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import interface_adapter.blackjack.AgainController;
import interface_adapter.blackjack.BlackjackState;
import interface_adapter.blackjack.BlackjackViewModel;
import interface_adapter.blackjack.ExitController;
import interface_adapter.blackjack.HitController;
import interface_adapter.blackjack.HoldController;
import interface_adapter.blackjack.PlayController;
import interface_adapter.blackjack.PlayerRecordController;

/**
 * The View for all the Blackjack Use Cases.
 */
public class BlackjackView extends JPanel implements PropertyChangeListener {

    private static final int GENERAL_HEIGHT = 50;
    private static final int CARDS_HEIGHT = 270;
    private static final int TWENTY_ONE = 21;

    private final String viewName;

    private final BlackjackViewModel blackjackViewModel;

    private final JLabel dealerLabel = new JLabel("DEALER | Wins: 0");
    private final JPanel dealerPanel = new JPanel();
    private final JLabel statusLabel = new JLabel();
    private final JPanel playerPanel = new JPanel();
    private final JLabel playerLabel = new JLabel("PLAYER | Wins: 0");

    private final JPanel buttons = new JPanel();
    private final JButton play = new JButton("PLAY");
    private final JButton hit = new JButton("HIT!");
    private final JButton hold = new JButton("HOLD");
    private final JButton playAgain = new JButton("PLAY");
    private final JButton exit = new JButton("EXIT");
    private final JButton playerRecord = new JButton("CHECK RECORD");

    private final JLabel cardBack = new JLabel(ViewConstants.STRING_IMAGEICON_MAP.get("BACK"));

    private PlayController playController;
    private HitController hitController;
    private HoldController holdController;
    private AgainController againController;
    private ExitController exitController;
    private PlayerRecordController playerRecordController;

    public BlackjackView(BlackjackViewModel blackjackViewModel) {
        // Inject the viewModel
        this.blackjackViewModel = blackjackViewModel;
        this.blackjackViewModel.addPropertyChangeListener(this);
        this.viewName = blackjackViewModel.getViewName();
        this.cardBack.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Layout for outermost panel and preferred sizes to force BoxLayout of fill.
        this.setSize(ViewConstants.WINDOW_WIDTH, ViewConstants.WINDOW_HEIGHT);
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.setVisible(true);

        dealerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        dealerLabel.setPreferredSize(new Dimension(ViewConstants.WINDOW_WIDTH, GENERAL_HEIGHT));
        dealerPanel.setPreferredSize(new Dimension(ViewConstants.WINDOW_WIDTH, CARDS_HEIGHT));

        this.add(dealerLabel);
        this.add(dealerPanel);

        statusLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        statusLabel.setPreferredSize(new Dimension(ViewConstants.WINDOW_WIDTH, GENERAL_HEIGHT));
        this.add(statusLabel);

        // Player panel is initially empty.
        playerPanel.setPreferredSize(new Dimension(ViewConstants.WINDOW_WIDTH, CARDS_HEIGHT));
        playerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        playerLabel.setPreferredSize(new Dimension(ViewConstants.WINDOW_WIDTH, GENERAL_HEIGHT));

        this.add(playerPanel);
        this.add(playerLabel);

        // Initialize buttons and their panel.
        buttons.setLayout(new FlowLayout());
        buttons.setPreferredSize(new Dimension(ViewConstants.WINDOW_WIDTH, GENERAL_HEIGHT));
        this.add(buttons);

        // Action listeners
        play.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        playController.execute();
                        playerRecordController.executeCreate();
                    }
                }
        );

        hit.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        hitController.execute();
                        playerRecordController.executeUpdate();
                    }
                }
        );

        hold.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        holdController.execute();
                        playerRecordController.executeUpdate();
                    }
                }
        );

        playAgain.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        againController.execute();
                        playerRecordController.executeUpdate();
                    }
                }
        );

        exit.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        final int response = JOptionPane.showConfirmDialog(
                                null,
                                "Are you sure you want to exit?",
                                "Confirm Exit",
                                JOptionPane.YES_NO_OPTION,
                                JOptionPane.QUESTION_MESSAGE
                        );
                        if (response == JOptionPane.YES_OPTION) {
                            exitController.switchToGameLibraryView();
                        }
                    }
                }
        );

        playerRecord.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        playerRecordController.executeCheck();
                        final String message = blackjackViewModel.getState().getPlayerRecordMessage();

                        // Show the message (player record) in a pop-up window.
                        JOptionPane.showMessageDialog(null, message, "Player statistics",
                                JOptionPane.INFORMATION_MESSAGE);
                    }
                }
        );
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final BlackjackState state = (BlackjackState) evt.getNewValue();
        switch (state.getStage()) {
            case "init":
                clearCardUi();
                initUi();
                break;
            case "play":
                clearCardUi();
                paintPlayUi(state.getPlayerCards(), state.getPlayerTotal(), state.getDealerCards(),
                        state.getWins(), state.getPlayerBankroll(), state.getCurrentBet());
                break;
            case "21":
                clearCardUi();
                paintPlayUi(state.getPlayerCards(), state.getPlayerTotal(), state.getDealerCards(),
                        state.getWins(), state.getPlayerBankroll(), state.getCurrentBet());
                holdController.execute();
                break;
            case "win":
                clearCardUi();
                paintEndUi(state.getPlayerCards(), state.getDealerCards(), state.getWins(), state.getLosses(),
                        state.getPlayerBankroll(), state.getStage());
                playerRecordController.executeRound(1);
                break;
            case "draw":
                clearCardUi();
                paintEndUi(state.getPlayerCards(), state.getDealerCards(), state.getWins(), state.getLosses(),
                        state.getPlayerBankroll(), state.getStage());
                playerRecordController.executeRound(2);
                break;
            case "loss":
                clearCardUi();
                paintEndUi(state.getPlayerCards(), state.getDealerCards(), state.getWins(), state.getLosses(),
                           state.getPlayerBankroll(), state.getStage());
                playerRecordController.executeRound(0);
                break;
            case "bust":
                paintBustUi(state.getPlayerCards(), state.getLosses());
                playerRecordController.executeRound(0);
                break;
            case "again":
                clearCardUi();
                playController.execute();
                break;
            case "gameOver":
                clearCardUi();
                paintEndUi(state.getPlayerCards(), state.getDealerCards(), state.getWins(), state.getLosses(),
                        state.getPlayerBankroll(), state.getStage());
                showGameOverDialogue("You don't have enough money to keep playing!\n"
                        + "You will be returned to the Game Library menu.");
                break;
            default:
                throw new RuntimeException("Stage mismatched: " + state.getStage());
        }
    }

    public void setPlayController(PlayController playController) {
        this.playController = playController;
    }

    public void setHitController(HitController hitController) {
        this.hitController = hitController;
    }

    public void setHoldController(HoldController holdController) {
        this.holdController = holdController;
    }

    public void setAgainController(AgainController againController) {
        this.againController = againController;
    }

    public void setExitController(ExitController exitController) {
        this.exitController = exitController;
    }

    public void setPlayerRecordController(PlayerRecordController playerRecordController) {
        this.playerRecordController = playerRecordController;
    }

    public String getViewName() {
        return viewName;
    }

    private void initUi() {
        dealerLabel.setText("DEALER | Wins: 0");

        // Dealer panel initially just shows back of card, to suggest a deck.
        dealerPanel.add(cardBack);

        statusLabel.setForeground(Color.BLACK);
        statusLabel.setText("Press PLAY to start the round");

        updatePlayerLabel(0, blackjackViewModel.getState().getPlayerBankroll());

        buttons.removeAll();
        buttons.revalidate();
        buttons.repaint();
        buttons.add(play);
        buttons.add(exit);
    }

    private void showGameOverDialogue(String message) {
        JOptionPane.showMessageDialog(null, message, "Game Over", JOptionPane.INFORMATION_MESSAGE);
        exitController.switchToGameLibraryView();
    }

    private void paintPlayUi(List<String> playerCards, int playerTotal, List<String> dealerCards,
                             int wins, int bankroll, int currentBet) {
        // Paint dealer card.
        dealerPanel.add(cardBack);
        final JLabel dealerCard = new JLabel(ViewConstants.STRING_IMAGEICON_MAP.get(dealerCards.get(0)));
        dealerCard.setAlignmentX(Component.CENTER_ALIGNMENT);
        dealerPanel.add(dealerCard);

        // Show player total.
        statusLabel.setForeground(Color.BLACK);
        statusLabel.setText("Current bet: " + currentBet + " | You have " + playerTotal);

        // Update player bankroll.
        updatePlayerLabel(wins, bankroll);

        // Paint player cards.
        for (String card : playerCards) {
            final JLabel playerCard = new JLabel(ViewConstants.STRING_IMAGEICON_MAP.get(card));
            playerCard.setAlignmentX(Component.CENTER_ALIGNMENT);
            playerPanel.add(playerCard);
        }

        if (playerTotal < TWENTY_ONE) {
            buttons.removeAll();
            buttons.revalidate();
            buttons.repaint();
            buttons.add(hit);
            buttons.add(hold);
            buttons.add(playerRecord);
            buttons.add(exit);
        }
    }

    private void paintEndUi(List<String> playerCards, List<String> dealerCards,
                            int wins, int losses, int bankroll, String stage) {
        // Paint dealer cards.
        for (String card : dealerCards) {
            final JLabel dealerCard = new JLabel(ViewConstants.STRING_IMAGEICON_MAP.get(card));
            dealerCard.setAlignmentX(Component.CENTER_ALIGNMENT);
            dealerPanel.add(dealerCard);
        }

        // Show outcome of round.
        if ("win".equals(stage)) {
            statusLabel.setForeground(Color.GREEN);
            statusLabel.setText("YOU WIN!");

            // Increment player wins and update player bankroll.
            updatePlayerLabel(wins, bankroll);
        }
        else if ("draw".equals(stage)) {
            statusLabel.setForeground(Color.ORANGE);
            statusLabel.setText("DRAW");

            // Update player bankroll.
            updatePlayerLabel(wins, bankroll);
        }
        else {
            statusLabel.setForeground(Color.RED);
            statusLabel.setText("YOU LOSE!");

            // Increment dealer wins.
            dealerLabel.setText("DEALER | Wins: " + losses);
        }

        // Paint player cards.
        for (String card : playerCards) {
            final JLabel playerCard = new JLabel(ViewConstants.STRING_IMAGEICON_MAP.get(card));
            playerCard.setAlignmentX(Component.CENTER_ALIGNMENT);
            playerPanel.add(playerCard);
        }

        buttons.removeAll();
        buttons.revalidate();
        buttons.repaint();
        buttons.add(playAgain);
        buttons.add(playerRecord);
        buttons.add(exit);
    }

    private void updatePlayerLabel(int wins, int bankroll) {
        playerLabel.setText("PLAYER | Wins: " + wins + " | Bankroll: " + bankroll);
    }

    private void paintBustUi(List<String> playerCards, int losses) {
        // Increment dealer wins.
        dealerLabel.setText("DEALER | Wins: " + losses);

        // Show outcome of round.
        statusLabel.setForeground(Color.RED);
        statusLabel.setText("BUST!");

        // Paint additional player card.
        final JLabel playerCard = new JLabel(ViewConstants.STRING_IMAGEICON_MAP.get(playerCards
                .get(playerCards.size() - 1)));
        playerCard.setAlignmentX(Component.CENTER_ALIGNMENT);
        playerPanel.add(playerCard);

        buttons.removeAll();
        buttons.revalidate();
        buttons.repaint();
        buttons.add(playAgain);
        buttons.add(playerRecord);
        buttons.add(exit);
    }

    private void clearCardUi() {
        dealerPanel.removeAll();
        dealerPanel.revalidate();
        dealerPanel.repaint();

        playerPanel.removeAll();
        playerPanel.revalidate();
        playerPanel.repaint();
    }
}
