package entity.player;

import java.util.List;

import entity.BlackjackPlayerRecord;
import entity.Card;
import entity.Rank;

/**
 * The representation of a player in the game Blackjack.
 */
public class BlackjackPlayer extends AbstractPlayer {
    public static final int INFINITY = -1;

    private static final int ACE_ADJ_THRESHOLD = 12;
    private static final int ACE_ADJUSTMENT = 10;

    private int bankroll;
    private int currentBet;
    private BlackjackPlayerRecord blackjackPlayerRecord;

    public BlackjackPlayer(int bankroll) {
        this.bankroll = bankroll;
    }

    public BlackjackPlayerRecord getBlackjackPlayerRecord() {
        return blackjackPlayerRecord;
    }

    /**
     * Create a new Blackjack Player Record for this player.
     * @return the new Blackjack Player Record.
     */
    public BlackjackPlayerRecord createBlackjackPlayerRecord() {
        blackjackPlayerRecord = new BlackjackPlayerRecord();
        return blackjackPlayerRecord;
    }

    /**
     * Update the current hand value of the human player to the player's record.
     * @param handVal the current hand value.
     */
    public void updateHandValRecord(int handVal) {
        blackjackPlayerRecord.updateHandValueRecord(handVal);
    }

    /**
     * Record whether won in this round.
     * @param won whether the player won in this round.
     */
    public void recordRound(int won) {
        blackjackPlayerRecord.recordRound(won);
    }

    public void setBankroll(int bankroll) {
        this.bankroll = bankroll;
    }

    /**
     * Update the player's bankroll and current bet of the player after placing a bet.
     * @param bet the amount of bet placed
     */
    public void placeBet(int bet) {
        this.currentBet = bet;
        this.bankroll -= bet;
    }

    /**
     * Update the player's bankroll and current bet after winning a game.
     * @param multiplier the multiplier applied to the win
     */
    public void win(int multiplier) {
        this.bankroll += currentBet * multiplier;
        this.currentBet = 0;
    }

    public int getBankroll() {
        return bankroll;
    }

    /**
     * Change the bankroll by amount.
     * @param amount an integer holding the amount of change the bankroll by
     */
    public void changeBankroll(int amount) {
        bankroll += amount;
    }

    /**
     * Return the hand value of the player according to Blackjack rules:
     * - Number cards (2-10) are worth the value of their number.
     * - Face cards (J-K) are worth 10.
     * - Ace is worth 1 or 11, whichever benefits the hand.
     * @return the hand value of the player.
     */
    public int getHandValue() {
        int value = 0;
        for (Card card : this.getCards()) {
            value += card.getRank().getRankValue();
        }
        if (hasAce(this.getCards()) && value < ACE_ADJ_THRESHOLD) {
            value += ACE_ADJUSTMENT;
        }
        return value;
    }

    private boolean hasAce(List<Card> cards) {
        boolean hasAce = false;
        for (Card card : cards) {
            if (card.getRank() == Rank.ACE) {
                hasAce = true;
                break;
            }
        }
        return hasAce;
    }
}

