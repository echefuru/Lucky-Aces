package use_case;

import entity.BlackjackPlayerRecord;
import entity.Deck;
import entity.room.BlackjackRoom;

/**
 * DAO for the Set Difficulty Use Case.
 */
public interface BlackjackRoomDataAccessInterface {

    /**
     * Create a new BlackjackRoom.
     * @param deck the deck for the room to be instantiated with.
     * @param bankroll the initial bankroll of each player
     * @param minimum_bet the minimum bet of each round
     */
    void createRoom(Deck deck, int bankroll, int minimum_bet);

    /**
     * Return the BlackjackRoom in context.
     * @return the BlackjackRoom in context.
     */
    BlackjackRoom getRoom();

    /**
     * Create a new BlackjackPlayerRecord.
     */
    void createBlackjackPlayerRecord();

    /**
     * Get the current BlackjackPlayerRecord.
     * @return BlackjackPlayerRecord object
     */
    BlackjackPlayerRecord getBlackjackPlayerRecord();

    /**
     * Update BlackjackPlayerRecord: its totalWins, totalRounds.
     * @param won if won this round.
     */
    void recordRound(int won);

    /**
     * Update BlackjackPlayerRecord's handValueRecord.
     * @param curHandValue current hand value to be documented.
     */
    void updateHandValueRecord(int curHandValue);

    void newRound();

    /**
     * Exit and remove the room from the memory.
     */
    void exitRoom();

}
