package entity.room;

/**
 * The representation of a game room in our program.
 */
public interface Room {
    /**
     * Returns the status of the room: "Initializing", "In Progress", or "Over".
     * @return the status of the room.
     */
    String getStatus();

}
