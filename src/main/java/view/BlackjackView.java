package view;

import interface_adapter.blackjack.BlackjackViewModel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * The View for all the Blackjack Use Cases.
 */
public class BlackjackView extends JPanel {

    private final String viewName = "blackjack_view";

    private final BlackjackViewModel blackjackViewModel;

    private final JButton hit;
    private final JButton hold;
    private final JButton dd;
    private final JButton exit;

    public BlackjackView(BlackjackViewModel blackjackViewModel) {
        this.blackjackViewModel = blackjackViewModel;

        hit = new JButton(blackjackViewModel.HIT_LABEL);
        hold = new JButton(blackjackViewModel.HOLD_LABEL);
        dd = new JButton(blackjackViewModel.DOUBLE_LABEL);
        exit = new JButton(blackjackViewModel.EXIT_LABEL);

        this.setSize(ViewConstants.WINDOW_WIDTH, ViewConstants.WINDOW_HEIGHT);
        this.setLayout(null);

        final JLabel dealer = new JLabel("Dealer");
        dealer.setBounds(600, 15, 100, 50);
        final JLabel player = new JLabel("Player");
        player.setBounds(600, 550, 100, 50);

        hit.setBounds(450, 600, 100, 50);
        hold.setBounds(600, 600, 100, 50);
        dd.setBounds(750, 600, 100, 50);
        exit.setBounds(1150, 15, 100, 50);

//        final BufferedImage dealerCard1;
//        final BufferedImage dealerCard2;
//        final BufferedImage playerCard1;
//        final BufferedImage playerCard2;

//        final JLabel dealerCardLabel1;
//        final JLabel dealerCardLabel2;
//        final JLabel playerCardLabel1;
//        final JLabel playerCardLabel2;

//        try {
//            dealerCard1 = resize(ImageIO.read(getClass().getClassLoader().getResource("card_images/back.png")), 200);
//            dealerCardLabel1 = new JLabel(new ImageIcon(dealerCard1));
//            dealerCardLabel2 = new JLabel(new ImageIcon(dealerCard1));
//            playerCardLabel1 = new JLabel(new ImageIcon(dealerCard1));
//            playerCardLabel2 = new JLabel(new ImageIcon(dealerCard1));
//        }
//        catch (IOException ex) {
//            throw new RuntimeException("Card image filepath issue.");
//        }

        final JLabel dealerCardLabel1 = new JLabel(ViewConstants.CARD_BACK);
        final JLabel dealerCardLabel2 = new JLabel(ViewConstants.CARD_AC);
        final JLabel playerCardLabel1 = new JLabel(ViewConstants.CARD_AH);
        final JLabel playerCardLabel2 = new JLabel(ViewConstants.CARD_AD);

        // 144 200 are dimensions of the cards
        dealerCardLabel1.setBounds(525, 50, 144, 200);
        dealerCardLabel2.setBounds(675, 50, 144, 200);
        playerCardLabel1.setBounds(525, 350, 144, 200);
        playerCardLabel2.setBounds(675, 350, 144, 200);

        this.add(dealerCardLabel1);
        this.add(dealerCardLabel2);
        this.add(playerCardLabel1);
        this.add(playerCardLabel2);

        this.add(hit);
        this.add(hold);
        this.add(dd);
        this.add(exit);

        this.add(dealer);
        this.add(player);

        this.setVisible(true);
    }

    // TODO: Impl for changing what is drawn here, or how?
    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
    }

    public String getViewName() {
        return viewName;
    }

    // TODO: Rewrite this in a way that makes sense!
    private BufferedImage resize(BufferedImage src, int targetSize) {
        int targetWidth = targetSize;
        int targetHeight = targetSize;
        float ratio = (float) src.getHeight() / (float) src.getWidth();
        if (ratio <= 1) { //square or landscape-oriented image
            targetHeight = (int) Math.ceil((float) targetWidth * ratio);
        } else { //portrait image
            targetWidth = Math.round((float) targetHeight / ratio);
        }
        BufferedImage bi = new BufferedImage(targetWidth, targetHeight, src.getTransparency() == Transparency.OPAQUE ? BufferedImage.TYPE_INT_RGB : BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = bi.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR); //produces a balanced resizing (fast and decent quality)
        g2d.drawImage(src, 0, 0, targetWidth, targetHeight, null);
        g2d.dispose();
        return bi;
    }
}
