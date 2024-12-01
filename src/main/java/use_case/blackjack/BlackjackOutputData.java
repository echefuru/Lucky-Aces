package use_case.blackjack;

import java.util.List;

/**
 * Output Data for the Blackjack Use Case.
 */
public class BlackjackOutputData {

    private List<String> playerCards;
    private int playerTotal;
    private List<String> dealerCards;
    private int wins;
    private int losses;

    public BlackjackOutputData(List<String> playerCards, int playerTotal, List<String> dealerCards) {
        this.playerCards = playerCards;
        this.playerTotal = playerTotal;
        this.dealerCards = dealerCards;
    }

    public List<String> getPlayerCards() {
        return playerCards;
    }

    public int getPlayerTotal() {
        return playerTotal;
    }

    public List<String> getDealerCards() {
        return dealerCards;
    }

    public int getWins() {
        return wins;
    }

    public int getLosses() {
        return losses;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public void setLosses(int losses) {
        this.losses = losses;
    }
}
