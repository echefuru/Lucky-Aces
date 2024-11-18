package entity.room;

import java.io.IOException;
import java.util.List;

import entity.player.Player;

/**
 * Factory for creating Blackjack rooms.
 */
public class BlackjackRoomFactory implements RoomFactory {

    @Override
    public Room createRoom(String roomName, String gameName, List<Player> curPlayers) throws IOException {
        return new BlackjackRoom(roomName, gameName, curPlayers);
    }
}
