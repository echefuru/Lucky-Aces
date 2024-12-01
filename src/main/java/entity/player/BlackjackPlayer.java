package entity.player;

import java.util.List;

import data_type.Card;
import data_type.Rank;

/**
 * The representation of a player in the game Blackjack.
 */
public class BlackjackPlayer extends AbstractPlayer {
    public static final int INFINITY = -1;

    private static final int ACE_ADJ_THRESHOLD = 12;
    private static final int ACE_ADJUSTMENT = 10;

    private int bankroll;

    public BlackjackPlayer(int bankroll) {
        this.bankroll = bankroll;
    }

    public void setBankroll(int bankroll) {
        this.bankroll = bankroll;
    }

    public int getBankroll() {
        return bankroll;
    }

    /**
     * Increments the bankroll by amount.
     * @param amount an integer holding the amount of increment the bankroll by
     */
    public void incrementBankroll(int amount) {
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

