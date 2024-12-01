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
    public void createRoom(Deck deck, int bankroll, int minimumBet) {
        blackjackRoom = new BlackjackRoom(deck, bankroll, minimumBet);
    }

    @Override
    public BlackjackRoom getRoom() {
        return blackjackRoom;
    }

    @Override
    public void newRound() {
        blackjackRoom.newRound();
    }

    @Override
    public void exitRoom() {
        blackjackRoom = null;
    }

}
