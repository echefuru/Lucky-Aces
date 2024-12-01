package entity.room;

import java.util.List;

import entity.Card;
import entity.Deck;
import entity.Rank;

/**
 * A Blackjack extension of the AbstractRoom.
 */
public class BlackjackRoom extends AbstractRoom {

    private static final int ACE_ADJ_THRESHOLD = 12;
    private static final int ACE_ADJUSTMENT = 10;

    public BlackjackRoom(Deck deck) {
        this.setDeck(deck);
    }

    @Override
    public int getPlayerTotal() {
        return handValue(getPlayerCards());
    }

    @Override
    public int getDealerTotal() {
        return handValue(getDealerCards());
    }

    private int handValue(List<Card> cards) {
        int value = 0;
        for (Card card : cards) {
            value += card.getRank().getRankValue();
        }
        if (hasAce(cards) && value < ACE_ADJ_THRESHOLD) {
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
