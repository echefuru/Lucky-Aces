package view;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 * Constants related to views.
 */
public class ViewConstants {
    static final int WINDOW_WIDTH = 1280;
    static final int WINDOW_HEIGHT = 720;

    static final int HALF_HEIGHT = WINDOW_HEIGHT / 2;
    static final int THIRD_HEIGHT = WINDOW_HEIGHT / 3;
    static final int QUARTER_HEIGHT = WINDOW_HEIGHT / 4;

    static final int HALF_WIDTH = WINDOW_WIDTH / 2;
    static final int THIRD_WIDTH = WINDOW_WIDTH / 3;

    static final int LINE_HEIGHT = 35;

    static final int PADDING = 5;
    static final int NUM_MINOR_TICKS_PER_MAJOR = 5;
    static final float SLIDER_FONT_SIZE = 7;

    static final float TITLE_FONT_SIZE = 20;

    // Creating constants of card images.
    static final int TARGET_SIZE = 200;

    static final Map<String, ImageIcon> STRING_IMAGEICON_MAP = new HashMap<String, ImageIcon>();

    static {
        STRING_IMAGEICON_MAP.put("BACK", createImageIcon("card_images/back.png"));
        final String[] cardNames = {"2H", "3H", "4H", "5H", "6H", "7H", "8H", "9H", "0H", "JH", "QH", "KH", "AH",
                                    "2D", "3D", "4D", "5D", "6D", "7D", "8D", "9D", "0D", "JD", "QD", "KD", "AD",
                                    "2C", "3C", "4C", "5C", "6C", "7C", "8C", "9C", "0C", "JC", "QC", "KC", "AC",
                                    "2S", "3S", "4S", "5S", "6S", "7S", "8S", "9S", "0S", "JS", "QS", "KS", "AS"};
        for (String cardName : cardNames) {
            STRING_IMAGEICON_MAP.put(cardName, createImageIcon("card_images/" + cardName + ".png"));
        }
    }

    private static ImageIcon createImageIcon(String path) {
        try {
            return new ImageIcon(resize(ImageIO.read(
                    ViewConstants.class.getClassLoader().getResource(path)), TARGET_SIZE));
        }
        catch (IOException ex) {
            throw new RuntimeException("Error initializing card images in ViewConstants.");
        }
    }

    private static BufferedImage resize(BufferedImage bufferedImage, int newHeight) {
        final float aspectRatio = (float) bufferedImage.getHeight() / (float) bufferedImage.getWidth();
        final int newWidth = Math.round(newHeight / aspectRatio);

        final BufferedImage newBufferedImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);

        final Graphics2D graphics = newBufferedImage.createGraphics();
        graphics.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        graphics.drawImage(bufferedImage, 0, 0, newWidth, newHeight, null);
        graphics.dispose();

        return newBufferedImage;
    }
}
