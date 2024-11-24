package app;

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
        // add the Logout Use Case to the app using the appBuilder
        final JFrame application = appBuilder
                                            // .addLoginView()
                                            // .addSignupView()
                                            .addGameLibraryView()
                                            .addGameSetupView()
                                            // .addChangePasswordView()
                                            // .addSignupUseCase()
                                            // .addLoginUseCase()
                                            // .addChangePasswordUseCase()
                                            // .addLogoutUseCase()
                                            .addInitializationUseCase() // new
                                            .addGameLibraryUseCase()
                                            .addGameSetupUseCase()
                                            .build();

        application.pack();
        application.setVisible(true);
    }
}
