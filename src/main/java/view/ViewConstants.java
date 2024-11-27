package view;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Constants related to views.
 */
public class ViewConstants {
    static final int WINDOW_WIDTH = 1280;
    static final int WINDOW_HEIGHT = 720;

    static final int HALF_HEIGHT = WINDOW_HEIGHT / 2;
    static final int THIRD_HEIGHT = WINDOW_HEIGHT / 3;
    static final int QUARTER_HEIGHT = WINDOW_HEIGHT / 4;

    static final int THIRD_WIDTH = WINDOW_WIDTH / 3;

    static final int LINE_HEIGHT = 35;

    static final int INPUT_COLUMNS = 15;
    static final int INPUT_PADDING = 5;

    static final float TITLE_FONT_SIZE = 20;

    // Creating constants of card images.
    static final int TARGET_SIZE = 200;

    static final ImageIcon CARD_BACK = createImageIcon("card_images/back.png");

    static final ImageIcon CARD_2H = createImageIcon("card_images/2H.png");
    static final ImageIcon CARD_3H = createImageIcon("card_images/3H.png");
    static final ImageIcon CARD_4H = createImageIcon("card_images/4H.png");
    static final ImageIcon CARD_5H = createImageIcon("card_images/5H.png");
    static final ImageIcon CARD_6H = createImageIcon("card_images/6H.png");
    static final ImageIcon CARD_7H = createImageIcon("card_images/7H.png");
    static final ImageIcon CARD_8H = createImageIcon("card_images/8H.png");
    static final ImageIcon CARD_9H = createImageIcon("card_images/9H.png");
    static final ImageIcon CARD_0H = createImageIcon("card_images/0H.png");
    static final ImageIcon CARD_JH = createImageIcon("card_images/JH.png");
    static final ImageIcon CARD_QH = createImageIcon("card_images/QH.png");
    static final ImageIcon CARD_KH = createImageIcon("card_images/KH.png");
    static final ImageIcon CARD_AH = createImageIcon("card_images/AH.png");

    static final ImageIcon CARD_2D = createImageIcon("card_images/2D.png");
    static final ImageIcon CARD_3D = createImageIcon("card_images/3D.png");
    static final ImageIcon CARD_4D = createImageIcon("card_images/4D.png");
    static final ImageIcon CARD_5D = createImageIcon("card_images/5D.png");
    static final ImageIcon CARD_6D = createImageIcon("card_images/6D.png");
    static final ImageIcon CARD_7D = createImageIcon("card_images/7D.png");
    static final ImageIcon CARD_8D = createImageIcon("card_images/8D.png");
    static final ImageIcon CARD_9D = createImageIcon("card_images/9D.png");
    static final ImageIcon CARD_0D = createImageIcon("card_images/0D.png");
    static final ImageIcon CARD_JD = createImageIcon("card_images/JD.png");
    static final ImageIcon CARD_QD = createImageIcon("card_images/QD.png");
    static final ImageIcon CARD_KD = createImageIcon("card_images/KD.png");
    static final ImageIcon CARD_AD = createImageIcon("card_images/AD.png");

    static final ImageIcon CARD_2C = createImageIcon("card_images/2C.png");
    static final ImageIcon CARD_3C = createImageIcon("card_images/3C.png");
    static final ImageIcon CARD_4C = createImageIcon("card_images/4C.png");
    static final ImageIcon CARD_5C = createImageIcon("card_images/5C.png");
    static final ImageIcon CARD_6C = createImageIcon("card_images/6C.png");
    static final ImageIcon CARD_7C = createImageIcon("card_images/7C.png");
    static final ImageIcon CARD_8C = createImageIcon("card_images/8C.png");
    static final ImageIcon CARD_9C = createImageIcon("card_images/9C.png");
    static final ImageIcon CARD_0C = createImageIcon("card_images/0C.png");
    static final ImageIcon CARD_JC = createImageIcon("card_images/JC.png");
    static final ImageIcon CARD_QC = createImageIcon("card_images/QC.png");
    static final ImageIcon CARD_KC = createImageIcon("card_images/KC.png");
    static final ImageIcon CARD_AC = createImageIcon("card_images/AC.png");

    static final ImageIcon CARD_2S = createImageIcon("card_images/2S.png");
    static final ImageIcon CARD_3S = createImageIcon("card_images/3S.png");
    static final ImageIcon CARD_4S = createImageIcon("card_images/4S.png");
    static final ImageIcon CARD_5S = createImageIcon("card_images/5S.png");
    static final ImageIcon CARD_6S = createImageIcon("card_images/6S.png");
    static final ImageIcon CARD_7S = createImageIcon("card_images/7S.png");
    static final ImageIcon CARD_8S = createImageIcon("card_images/8S.png");
    static final ImageIcon CARD_9S = createImageIcon("card_images/9S.png");
    static final ImageIcon CARD_0S = createImageIcon("card_images/0S.png");
    static final ImageIcon CARD_JS = createImageIcon("card_images/JS.png");
    static final ImageIcon CARD_QS = createImageIcon("card_images/QS.png");
    static final ImageIcon CARD_KS = createImageIcon("card_images/KS.png");
    static final ImageIcon CARD_AS = createImageIcon("card_images/AS.png");

    private static ImageIcon createImageIcon(String path) {
        try {
            return new ImageIcon(resize(ImageIO.read(
                    ViewConstants.class.getClassLoader().getResource(path)), TARGET_SIZE));
        }
        catch (IOException ex) {
            throw new RuntimeException("Error initializing card images in ViewConstants.");
        }
    }

    // TODO: Rewrite this and make it cleaner and clearer.
    private static BufferedImage resize(BufferedImage src, int targetSize) {
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
