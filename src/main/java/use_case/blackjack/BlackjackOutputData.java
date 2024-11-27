package use_case.blackjack;

/**
 * Output Data for the Blackjack Use Case.
 */
public class BlackjackOutputData {

    private String[] playerCards;
    private int playerTotal;
    private String[] dealerCards;
    private String stage;

    public BlackjackOutputData(String[] playerCards, int playerTotal, String[] dealerCards, String stage) {
        this.playerCards = playerCards;
        this.playerTotal = playerTotal;
        this.dealerCards = dealerCards;
        this.stage = stage;
    }
}
