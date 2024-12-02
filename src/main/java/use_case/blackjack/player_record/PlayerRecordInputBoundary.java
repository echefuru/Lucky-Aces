package use_case.blackjack.player_record;

/**
 * Input Boundary for the Player Record Use Case in Blackjack.
 */
public interface PlayerRecordInputBoundary {

    /**
     * Executes the Player Record Use Case in Blackjack.
     */
    void executeCheck();

    /**
     * Execute the Update method for the Player Record Use Case in Blackjack.
     */
    void executeUpdate();

    /**
     * Create a Player Record Entity for the Player Record Use Case in Blackjack.
     */
    void executeCreate();

    /**
     * Execute record round for the Player Record Use Case in Blackjack.
     * @param won if the player wins in this round.
     */
    void executeRound(int won);

    /**
     * Execute reset for the Player Record Use Case in Blackjack.
     */
    void executeResetHandValRecord();
}
