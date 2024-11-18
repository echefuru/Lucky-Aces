package entity.player;

import java.util.List;

import entity.Card;

/**
 * A simple implementation of the User interface.
 */
public class CommonPlayer implements Player {

    private final String playerID;
    private final String password;
    private List<Card> cards;

    public CommonPlayer(String playerID, String password) {
        this.playerID = playerID;
        this.password = password;
    }

    @Override
    public String getPlayerID() {
        return playerID;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public List<Card> getCards() {
        return cards;
    }

}
