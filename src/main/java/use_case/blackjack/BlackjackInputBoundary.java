package use_case.blackjack;

/**
 * Input Boundary for actions which are related to Blackjack.
 */
public interface BlackjackInputBoundary {

    /**
     * Executes the Play use case.
     */
    void play();

    /**
     * Executes the Hit use case.
     */
    void hit();

    /**
     * Executes the Hold use case.
     */
    void hold();

    /**
     * Executes the switch to GameLibraryView use case.
     */
    void switchToGameLibraryView();
}
