package entity.room;

import java.io.IOException;
import java.util.List;

import entity.Deck;
import entity.player.Player;

/**
 * A Blackjack-implementation of the Room interface.
 */
public class BlackjackRoom implements Room {

    private final String roomName;
    private final String gameName;
    private List<Player> curPlayers;
    private String curTurnPlayer;
    private String status;
    private Deck deck;
    // TODO: should also contain an attribute to store the bet (and update the constructor).

    public BlackjackRoom(String roomName, String gameName, List<Player> curPlayers) throws IOException {
        this.roomName = roomName;
        this.gameName = gameName;
        this.curPlayers = curPlayers;
        this.curTurnPlayer = curPlayers.get(0).getPlayerID();
        this.status = "Initializing";
    }

    @Override
    public String getStatus() {
        return this.status;
    }

    @Override
    public List<Player> getPlayers() {
        return this.curPlayers;
    }

    @Override
    public String getName() {
        return this.roomName;
    }

    public void setDeck(Deck deck) {
        this.deck = deck;
    }

    // TODO: need to get Bankroll to make a bet each round. Fix this.
    // public int getBankroll(){ }

}
