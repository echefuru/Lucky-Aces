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
        final int width = 1080;
        final int height = 720;
        // add the Logout Use Case to the app using the appBuilder
        final JFrame application = appBuilder
                                            .addLoginView()
                                            .addSignupView()
                                            .addGameLibraryView()
                                            .addGameSetupView()
                                            .addChangePasswordView()
                                            .addSignupUseCase()
                                            .addLoginUseCase()
                                            .addChangePasswordUseCase()
                                            .addLogoutUseCase()
                                            .addGameLibraryUseCase()
                                            .addGameSetupUseCase()
                                            .build();

        application.pack();
        application.setSize(new Dimension(width, height));
        application.setVisible(true);
    }
}
