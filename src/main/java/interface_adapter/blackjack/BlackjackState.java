package interface_adapter.blackjack;

import java.util.List;

/**
 * The state for the Blackjack View Model.
 */
public class BlackjackState {

    private static final String HTML_OPEN_PARAGRAPH = "<p>";
    private static final String HTML_CLOSE_PARAGRAPH = "</p>";

    private List<String> playerCards;
    private int playerTotal;
    private List<String> dealerCards;
    private String stage = "init";

    private List<Integer> playerHandValRecord;
    private int totalRounds;
    private int wins;
    private int losses;
    private int playerBankroll;
    private int currentBet;

    /**
     * Increment losses by one.
     */
    public void incrementLosses() {
        losses++;
    }

    /**
     * Increment wins by one.
     */
    public void incrementWins() {
        wins++;
    }

    /**
     * Return the HTML message of the player records.
     * @return a String message of HTML format for the player's record.
     */
    public String getPlayerRecordMessage() {

        final String message = "<html>"
                + "<h2 style='color:blue;'>Player Statistics</h2>"
                + HTML_OPEN_PARAGRAPH + "<strong># of Wins:</strong> " + this.wins + HTML_CLOSE_PARAGRAPH
                + HTML_OPEN_PARAGRAPH + "<strong># of Losses:</strong> " + this.losses + HTML_CLOSE_PARAGRAPH
                + HTML_OPEN_PARAGRAPH + "<strong># of Rounds:</strong> " + this.totalRounds + HTML_CLOSE_PARAGRAPH
                + HTML_OPEN_PARAGRAPH + "<strong>All of your hand values:</strong> " + this.playerHandValRecord
                .toString() + HTML_CLOSE_PARAGRAPH + "</html>";
        return message;
    }

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
  
    /**
     * Resets the object's fields, except stage which must be set back to "init" explicitly.
     */
    public void reset() {
        playerCards = null;
        playerTotal = 0;
        dealerCards = null;
        playerHandValRecord = List.of();
        totalRounds = 0;
        wins = 0;
        losses = 0;
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
  
    public void setPlayerHandValRecord(List<Integer> playerHandValRecord) {
        this.playerHandValRecord = playerHandValRecord;
    }

    public void setTotalRounds(int totalRounds) {
        this.totalRounds = totalRounds;
    }

}
