package entity.room;

import java.util.List;

import entity.player.Player;

/**
 * The representation of a game room in our program.
 */
public interface Room {
    /**
     * Returns playerID of the current player to take action.
     * @return the playerID of the current turn player.
     */
    String getCurTurnPlayer();

    /**
     * Returns the list of current players in the room.
     * @return the list of players.
     */
    List<Player> getPlayers();

}
