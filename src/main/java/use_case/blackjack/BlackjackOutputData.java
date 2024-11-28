package use_case.blackjack;

import java.util.List;

/**
 * Output Data for the Blackjack Use Case.
 */
public class BlackjackOutputData {

    private List<String> playerCards;
    private int playerTotal;
    private List<String> dealerCards;
    private String stage;

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

    public String getStage() {
        return stage;
    }
}
