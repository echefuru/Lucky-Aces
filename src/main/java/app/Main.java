package app;

import java.awt.Dimension;

import javax.swing.JFrame;

/**
 * The Main class of our application.
 */
public class Main {
    /**
     * Builds and runs the CA architecture of the application.
     * @param args unused arguments
     */
    public static void main(String[] args) {
        final AppBuilder appBuilder = new AppBuilder();
        final int width = 1280;
        final int height = 720;
        final JFrame application = appBuilder
                                            // Views
                                            .addGameLibraryView()
                                            .addGameSetupView()
                                            .addBlackjackView()
                                            // Use Cases
                                            .addGameLibraryUseCase()
                                            .addGameSelectUseCase()
                                            .addGameStartUseCase()
                                            .addGameSetConfigUseCase()
                                            .addGameSearchUseCase()
                                            .addGameFilterUseCase()
                                            .addBlackjackUseCases()
                                            // Build
                                            .build();

        application.pack();
        application.setSize(new Dimension(width, height));
        application.setVisible(true);
    }
}
