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
    private int playerBankroll;
    private int currentBet;

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

    public int getPlayerBankroll() {
        return playerBankroll;
    }

    public int getCurrentBet() {
        return currentBet;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public void setLosses(int losses) {
        this.losses = losses;
    }

    public void setPlayerBankroll(int playerBankroll) {
        this.playerBankroll = playerBankroll;
    }

    public void setCurrentBet(int currentBet) {
        this.currentBet = currentBet;
    }
}
