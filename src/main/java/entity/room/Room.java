package entity.room;

import java.util.List;

import entity.player.Player;

/**
 * The representation of a game room in our program.
 */
public interface Room {
    /**
     * Returns the status of the room: "Initializing", "In Progress", or "Over".
     * @return the status of the room.
     */
    String getStatus();

    /**
     * Returns the list of current players in the room.
     * @return the list of players.
     */
    List<Player> getPlayers();

    /**
     * Returns the name of the room.
     * @return the name of the room
     */
    String getName();
}
