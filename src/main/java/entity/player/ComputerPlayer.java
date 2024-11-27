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
    private int difficulty;
    private int bankroll;

    public ComputerPlayer(String playerID) {
        this.playerID = playerID;
        this.difficulty = 1;
        this.cards = new ArrayList<>();
        this.bankroll = PlayerConstants.INITIAL_BANKROLL;
    }

    @Override
    public String getPlayerID() {
        return playerID;
    }

    @Override
    public List<Card> getCards() {
        return cards;
    }

    @Override
    public int getBankroll() {
        return this.bankroll;
    }

    public int getDifficulty() {
        return difficulty;
    }

    private void setCards(List<Card> cards) {
        this.cards = cards;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }
}
