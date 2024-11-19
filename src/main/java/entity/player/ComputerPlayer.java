package entity.player;

import java.util.ArrayList;
import java.util.List;

import entity.Card;

/**
 * Computer player version of the player interface.
 */
public class ComputerPlayer implements Player {

    private final String playerID;
    private List<Card> cards;
    private String difficulty;

    public ComputerPlayer(String playerID) {
        this.playerID = playerID;
        this.difficulty = "Easy";
        this.cards = new ArrayList<Card>();
    }

    @Override
    public String getPlayerID() {
        return playerID;
    }

    @Override
    public String getPassword() {
        return "";
    }

    @Override
    public List<Card> getCards() {
        return cards;
    }

    public String getDifficulty() {
        return difficulty;
    }

    private void setCards(List<Card> cards) {
        this.cards = cards;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }
}
