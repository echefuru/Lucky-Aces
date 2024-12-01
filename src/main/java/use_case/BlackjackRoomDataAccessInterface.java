package use_case;

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
     * @param minimumBet the minimum bet of each round
     */
    void createRoom(Deck deck, int bankroll, int minimumBet);

    /**
     * Return the BlackjackRoom in context.
     * @return the BlackjackRoom in context.
     */
    BlackjackRoom getRoom();

    /**
     * New Round for a Blackjack Game.
     */
    void newRound();

    /**
     * Exit and remove the room from the memory.
     */
    void exitRoom();

}
