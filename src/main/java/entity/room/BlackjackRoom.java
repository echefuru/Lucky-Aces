package entity.room;

import java.util.List;

import entity.player.CommonPlayer;

/**
 * A Blackjack-implementation of the Room interface.
 */
public class BlackjackRoom implements Room {

    private final String roomName;
    private final String gameName;
    private List<CommonPlayer> curPlayers;
    private int round;
    private String curTurnPlayer;
    private String status;
    // TODO: add attribute--the deck of cards.
    // TODO: should also contain an attribute to store:
    //  1. the cards the players have
    //  2. the player's bet.
    // TODO: should create ComputerPlayer class.
    // TODO: finally, update the initiator and other affected classes.

    public BlackjackRoom(String roomName, String gameName, List<CommonPlayer> curPlayers) {
        this.roomName = roomName;
        this.gameName = gameName;
        this.curPlayers = curPlayers;
        this.round = 1;
        this.curTurnPlayer = curPlayers.get(0).getPlayerID();
        this.status = "In Progress";
    }

    @Override
    public String getStatus() {
        return this.status;
    }

    public int getBankroll() {
        // TODO: need to get Bankroll to make a bet each round. Fix this.
        return 1;
    }

}
