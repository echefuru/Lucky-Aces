package interface_adapter.blackjack;

import use_case.blackjack.play.PlayInputBoundary;

/**
 * Controller for the Play Use Case in Blackjack.
 */
public class PlayController {

    private final PlayInputBoundary playInteractor;

    public PlayController(PlayInputBoundary playInteractor) {
        this.playInteractor = playInteractor;
    }

    /**
     * Executes the Play Use Case in Blackjack.
     */
    public void execute() {
        playInteractor.execute();
    }
}
