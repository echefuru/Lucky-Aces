package view;

import interface_adapter.blackjack.BlackjackViewModel;
import interface_adapter.blackjack.BlackjackController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;

/**
 * The View for all the Blackjack Use Cases.
 */
public class BlackjackView extends JPanel implements ActionListener {

    private static final int GENERAL_HEIGHT = 50;
    private static final int CARDS_HEIGHT = 290;

    private final String viewName;

    private final BlackjackViewModel blackjackViewModel;

    private final JLabel dealerLabel = new JLabel("DEALER | Wins: -");
    private final JPanel dealerPanel = new JPanel();
    private final JPanel playerPanel = new JPanel();
    private final JLabel playerLabel = new JLabel("PLAYER | Wins: -");

    private final JPanel buttons = new JPanel();
    private final JButton hit = new JButton("HIT!");
    private final JButton hold = new JButton("HOLD");
    private final JButton dd = new JButton("DOUBLE!!");
    private final JButton exit = new JButton("EXIT");

    private BlackjackController blackjackController;

    public BlackjackView(BlackjackViewModel blackjackViewModel) {
        // Inject the viewModel
        this.blackjackViewModel = blackjackViewModel;
        // this.blackjackViewModel.addPropertyChangeListener(this);
        this.viewName = blackjackViewModel.getViewName();

        // UI work in private function.
        paintUi();

        // Action listeners
        hit.addActionListener(this);

        hold.addActionListener(this);

        dd.addActionListener(this);

        exit.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        blackjackController.switchToGameLibraryView();
                    }
                }
        );
    }

//    @Override
//    public void propertyChange(PropertyChangeEvent evt) {
//        final BlackjackState state = (BlackjackState) evt.getNewValue();
//        if (state.getUsernameError() != null) {
//            JOptionPane.showMessageDialog(this, state.getUsernameError());
//        }
//    }

    /**
     * React to a button
     * click that results in evt.
     * @param evt the ActionEvent to react to
     */
    @Override
    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());
    }

    // TODO: Impl for changing what is drawn here, or how?
    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
    }

    public void setBlackjackController(BlackjackController blackjackController) {
        this.blackjackController = blackjackController;
    }

    public String getViewName() {
        return viewName;
    }

    private void paintUi() {
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

        // Initialize buttons and their panel.
        buttons.setLayout(new FlowLayout());
        buttons.setPreferredSize(new Dimension(ViewConstants.WINDOW_WIDTH, GENERAL_HEIGHT));

        buttons.add(hit);
        buttons.add(hold);
        buttons.add(dd);
        buttons.add(exit);

        this.add(buttons);
    }
}
