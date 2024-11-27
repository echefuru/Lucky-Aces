package entity.player;

import java.util.ArrayList;
import java.util.List;

import entity.Card;

/**
 * A simple implementation of the Player interface.
 */
public class CommonPlayer implements Player {

    private final String playerID;
    private List<Card> cards;

    public CommonPlayer(String playerID, String password) {
        this.playerID = playerID;
        this.cards = new ArrayList<Card>();
    }

    @Override
    public String getPlayerID() {
        return playerID;
    }

    public List<Card> getCards() {
        return cards;
    }

}
