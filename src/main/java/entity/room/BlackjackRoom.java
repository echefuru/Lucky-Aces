package entity.room;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import data_access.ApiDataAccess;
import entity.Deck;
import entity.player.Player;
import use_case.ApiDataAccessInterface;

/**
 * A Blackjack-implementation of the Room interface.
 */
public class BlackjackRoom implements Room {

    private final String roomName;
    private final String gameName;
    private List<Player> curPlayers;
    private int round;
    private String curTurnPlayer;
    private String status;
    private Deck deck;
    // TODO: should also contain an attribute to store the bet.
    // TODO: should create ComputerPlayer class.
    // TODO: finally, update the initiator and other affected classes.

    public BlackjackRoom(String roomName, String gameName, List<Player> curPlayers) throws IOException {
        this.roomName = roomName;
        this.gameName = gameName;
        this.curPlayers = curPlayers;
        this.round = 1;
        this.curTurnPlayer = curPlayers.get(0).getPlayerID();
        this.status = "Initializing";

        // TODO: create deck here...I hope this is how it's supposed to be:
        final ApiDataAccessInterface apiDataAccessInterface = new ApiDataAccess();
        this.deck = apiDataAccessInterface.createDeck();
    }

    @Override
    public String getStatus() {
        return this.status;
    }

    // TODO: need to get Bankroll to make a bet each round. Fix this.
    // public int getBankroll(){ }

}
