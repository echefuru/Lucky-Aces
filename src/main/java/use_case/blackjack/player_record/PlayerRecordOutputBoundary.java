package use_case.blackjack.player_record;

/**
 * The Output Boundary for the Player Record Use Case in Blackjack.
 */
public interface PlayerRecordOutputBoundary {

    /**
     * Display the player records.
     */
    void preparePlayerRecordView(PlayerRecordOutputData playerRecordOutputData);
}
