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
     */
    void createRoom(Deck deck);

    /**
     * Return the BlackjackRoom in context.
     * @return the BlackjackRoom in context.
     */
    BlackjackRoom getRoom();

    /**
     * Reset the BlackjackRoom in context to a new room.
     */
    void resetRoom();
}
