package data_access;

import entity.Deck;
import entity.room.BlackjackRoom;
import use_case.BlackjackRoomDataAccessInterface;

/**
 * In-memory implementation of the DAI of the Blackjack game room.
 */
public class BlackjackRoomDataAccessObject implements BlackjackRoomDataAccessInterface {
    private BlackjackRoom blackjackRoom;

    @Override
    public void createRoom(Deck deck) {
        blackjackRoom = new BlackjackRoom(deck);
    }

    @Override
    public BlackjackRoom getRoom() {
        return blackjackRoom;
    }

    @Override
    public void resetRoom() {
        blackjackRoom = null;
    }
}
