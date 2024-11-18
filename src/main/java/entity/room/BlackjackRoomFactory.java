package entity.room;

import java.util.List;

import entity.player.CommonPlayer;

/**
 * Factory for creating Blackjack rooms.
 */
public class BlackjackRoomFactory implements RoomFactory {

    @Override
    public Room createRoom(String roomName, String gameName, List<CommonPlayer> curPlayers) {
        return new BlackjackRoom(roomName, gameName, curPlayers);
    }
}
