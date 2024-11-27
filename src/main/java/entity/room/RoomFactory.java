package entity.room;

import java.io.IOException;
import java.util.List;

import entity.player.Player;

/**
 * Factory for creating game rooms.
 */
public interface RoomFactory {
    /**
     * Creates a new game room.
     * @param type the type of the game of the room
     * @param curPlayers the current Players in the room
     * @return the new room
     * @throws IOException for failed call to the method
     */
    Room createRoom(String type, List<Player> curPlayers) throws IOException;

}
