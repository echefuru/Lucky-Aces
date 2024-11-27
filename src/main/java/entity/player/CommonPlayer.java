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
    private int bankroll;

    public CommonPlayer(String playerID) {
        this.playerID = playerID;
        this.cards = new ArrayList<Card>();
        this.bankroll = PlayerConstants.INITIAL_BANKROLL;
    }

    @Override
    public String getPlayerID() {
        return playerID;
    }

    public List<Card> getCards() {
        return cards;
    }

    @Override
    public int getBankroll() {
        return 0;
    }

}
