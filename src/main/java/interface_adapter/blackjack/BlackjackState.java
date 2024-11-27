package interface_adapter.blackjack;

import java.util.List;

/**
 * The state for the Blackjack View Model.
 */
public class BlackjackState {

    private List<String> playerCards;
    private int playerTotal;
    private List<String> dealerCards;
    private String stage;

    public List<String> getPlayerCards() {
        return playerCards;
    }

    public List<String> getDealerCards() {
        return dealerCards;
    }

    public int getPlayerTotal() {
        return playerTotal;
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

    public String getStage() {
        return stage;
    }

    /**
     * Sets the stage variable to "pre_round".
     */
    public void setStagePreRound() {
        this.stage = "pre_round";
    }

    /**
     * Sets the stage variable to "player_turn".
     */
    public void setStagePlayerTurn() {
        this.stage = "player_turn";
    }

    /**
     * Sets the stage variable to "dealer_turn".
     */
    public void setStageDealerTurn() {
        this.stage = "dealer_turn";
    }

    /**
     * Sets the stage variable to "post_round".
     */
    public void setStagePostRound() {
        this.stage = "post_round";
    }
}
