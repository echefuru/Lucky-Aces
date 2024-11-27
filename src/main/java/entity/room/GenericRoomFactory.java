package entity.room;

import java.io.IOException;
import java.util.List;

import entity.player.Player;

/**
 * Factory for creating game rooms based on the gameName given.
 */
public class GenericRoomFactory implements RoomFactory {

    @Override
    public Room createRoom(String roomName, String gameName, List<Player> curPlayers) throws IOException {
        switch (gameName) {
            case "blackjack":
                return new BlackjackRoom(roomName, gameName, curPlayers);
            default:
                throw new IllegalArgumentException("Invalid game name, try again!");
        }
    }
}
