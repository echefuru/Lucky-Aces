package interface_adapter.blackjack;

import use_case.blackjack.player_record.PlayerRecordInputBoundary;

/**
 * Controller for the PlayerRecord Use Case in Blackjack.
 */
public class PlayerRecordController {
    private final PlayerRecordInputBoundary playerRecordInteractor;

    public PlayerRecordController(PlayerRecordInputBoundary playerRecordInteractor) {
        this.playerRecordInteractor = playerRecordInteractor;
    }

    /**
     * Execute the Player Record Use Case which displays the player record (as a pop-up window) in the Blackjack View.
     */
    public void execute() {
        playerRecordInteractor.execute();
    }
}
