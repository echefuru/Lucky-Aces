package interface_adapter.blackjack;

import java.util.List;

/**
 * The state for the Blackjack View Model.
 */
public class BlackjackState {

    private List<String> playerCards;
    private int playerTotal;
    private List<String> dealerCards;
    private String stage = "init";
    private int wins;
    private int losses;
    private int playerBankroll;
    private int currentBet;

    public List<String> getPlayerCards() {
        return playerCards;
    }

    public List<String> getDealerCards() {
        return dealerCards;
    }

    public int getPlayerTotal() {
        return playerTotal;
    }

    public String getStage() {
        return stage;
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

    public void setPlayerTotal(int playerTotal) {
        this.playerTotal = playerTotal;
    }

    public void setPlayerCards(List<String> playerCards) {
        this.playerCards = playerCards;
    }

    public void setDealerCards(List<String> dealerCards) {
        this.dealerCards = dealerCards;
    }

    /**
     * Sets the stage variable to string.
     * @param string the new stage.
     */
    public void setStage(String string) {
        this.stage = string;
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
