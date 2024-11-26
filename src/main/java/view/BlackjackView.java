package view;

import interface_adapter.blackjack.BlackjackViewModel;

import javax.swing.*;
import java.awt.*;

/**
 * The View for all the Blackjack Use Cases.
 */
public class BlackjackView extends JPanel {

    private static final int GENERAL_HEIGHT = 50;
    private static final int CARDS_HEIGHT = 290;

    private final String viewName = "blackjack_view";

    private final BlackjackViewModel blackjackViewModel;

    private final JLabel dealerLabel = new JLabel("DEALER | Wins: -");
    private final JPanel dealerPanel = new JPanel();
    private final JPanel playerPanel = new JPanel();
    private final JLabel playerLabel = new JLabel("PLAYER | Wins: -");

    private final JPanel buttons = new JPanel();
    private final JButton hit;
    private final JButton hold;
    private final JButton dd;
    private final JButton exit;

    public BlackjackView(BlackjackViewModel blackjackViewModel) {
        // Inject the viewModel
        this.blackjackViewModel = blackjackViewModel;

        // Layout for outermost panel and preferred sizes to force BoxLayout of fill.
        this.setSize(ViewConstants.WINDOW_WIDTH, ViewConstants.WINDOW_HEIGHT);
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.setVisible(true);

        // Dealer panel initially just shows back of card, to suggest a deck.
        dealerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        dealerLabel.setPreferredSize(new Dimension(ViewConstants.WINDOW_WIDTH, GENERAL_HEIGHT));
        dealerPanel.setPreferredSize(new Dimension(ViewConstants.WINDOW_WIDTH, CARDS_HEIGHT));

        this.add(dealerLabel);
        this.add(dealerPanel);

        final JLabel dealerCardLabel1 = new JLabel(ViewConstants.CARD_BACK);
        dealerCardLabel1.setAlignmentX(Component.CENTER_ALIGNMENT);
        dealerPanel.add(dealerCardLabel1);

        // Player panel is initially empty.
        playerPanel.setPreferredSize(new Dimension(ViewConstants.WINDOW_WIDTH, CARDS_HEIGHT));
        playerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        playerLabel.setPreferredSize(new Dimension(ViewConstants.WINDOW_WIDTH, GENERAL_HEIGHT));

        this.add(playerPanel);
        this.add(playerLabel);

        // Initialize credits and all buttons and their panel.
        buttons.setLayout(new FlowLayout());
        buttons.setPreferredSize(new Dimension(ViewConstants.WINDOW_WIDTH, GENERAL_HEIGHT));

        hit = new JButton(blackjackViewModel.HIT_LABEL);
        hold = new JButton(blackjackViewModel.HOLD_LABEL);
        dd = new JButton(blackjackViewModel.DOUBLE_LABEL);
        exit = new JButton(blackjackViewModel.EXIT_LABEL);
        buttons.add(hit);
        buttons.add(hold);
        buttons.add(dd);
        buttons.add(exit);

        this.add(buttons);
    }

    // TODO: Impl for changing what is drawn here, or how?
    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
    }

    public String getViewName() {
        return viewName;
    }
}
