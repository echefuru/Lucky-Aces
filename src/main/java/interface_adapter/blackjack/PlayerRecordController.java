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
     * Execute Check for the Player Record Use Case which displays the player record (as a pop-up window)
     * in the Blackjack View.
     */
    public void executeCheck() {
        playerRecordInteractor.executeCheck();
    }

    /**
     * Execute Update (record player hand value) for the Player Record Use Case in Blackjack (no display).
     */
    public void executeUpdate() {
        playerRecordInteractor.executeUpdate();
    }

    /**
     * Execute Create for the Player Record Use Case in Blackjack.
     */
    public void executeCreate() {
        playerRecordInteractor.executeCreate();
    }

    /**
     * Execute Record (win) Round for the Player Record Use Case in Blackjack.
     * @param won if the player won this round
     */
    public void executeRound(Integer won) {
        playerRecordInteractor.executeRound(won);
    }

    /**
     * Execute Reset Hand Value for the Player Record Use Case in Blackjack.
     */
    public void executeResetHandValRecord() {
        playerRecordInteractor.executeResetHandValRecord();
    }
}
