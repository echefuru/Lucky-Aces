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

    public void setPlayerHandValRecord(List<Integer> playerHandValRecord) {
        this.playerHandValRecord = playerHandValRecord;
    }

    public void setTotalRounds(int totalRounds) {
        this.totalRounds = totalRounds;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    /**
     * Return the HTML message of the player records.
     * @return a String message of HTML format for the player's record.
     */
    public String getPlayerRecordMessage() {
        final String win = "# of Wins: " + this.wins;
        final String rounds = "# of Total Rounds: " + this.totalRounds;
        final String playerHandValRec = "All of your hand values:" + this.playerHandValRecord.toString();

        final String message = "<html>"
                + "<h2 style='color:blue;'>Player Statistics</h2>"
                + HTML_OPEN_PARAGRAPH + "<strong># of Wins:</strong> " + win + HTML_CLOSE_PARAGRAPH
                + HTML_OPEN_PARAGRAPH + "<strong># of Rounds:</strong> " + rounds + HTML_CLOSE_PARAGRAPH
                + HTML_OPEN_PARAGRAPH + "<strong>All of your hand values:</strong> " + playerHandValRec.toString()
                + HTML_CLOSE_PARAGRAPH + "</html>";
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

    public int getTotalRounds() {
        return totalRounds;
    }

    public List<Integer> getPlayerHandValRecord() {
        return playerHandValRecord;
    }

    public int getLosses() {
        return totalRounds - wins;
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
        playerHandValRecord = null;
        totalRounds = 0;
        wins = 0;
    }
}
