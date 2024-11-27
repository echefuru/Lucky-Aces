package data_access;

import java.util.List;

import entity.player.Player;
import entity.room.Room;
import use_case.set_difficulty.SetDifficultyDataAccessInterface;

/**
 * In-memory implementation fo the DAO of the game room.
 */
public class RoomDataAccessObject implements SetDifficultyDataAccessInterface {
    private Room room;

    @Override
    public List<Player> getPlayers(String roomName) {
        if (room.getName().equals(roomName)) {
            return room.getPlayers();
        }
        else {
            throw new IllegalArgumentException("Room does not exist, try another room name!");
        }
    }
}
