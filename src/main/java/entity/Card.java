package entity;

/**
 * A Card, with a Rank and Suit.
 */
public class Card implements Comparable<Card> {

    private final Rank rank;
    private final Suit suit;

    public Card(Rank rank, Suit suit) {
        this.suit = suit;
        this.rank = rank;
    }

    public Suit getSuit() {
        return suit;
    }

    public Rank getRank() {
        return rank;
    }

    @Override
    public int compareTo(Card card) {
        return this.rank.getRankValue() - card.rank.getRankValue();
    }
}
