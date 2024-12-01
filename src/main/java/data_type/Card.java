package data_type;

/**
 * A Card, with a Rank and Suit.
 */
public class Card implements Comparable<Card> {

    private final Rank rank;
    private final Suit suit;
    private final String string;

    public Card(Rank rank, Suit suit, String string) {
        this.rank = rank;
        this.suit = suit;
        this.string = string;
    }

    public Suit getSuit() {
        return suit;
    }

    public Rank getRank() {
        return rank;
    }

    @Override
    public String toString() {
        return string;
    }

    /**
     * Override Object.hasCode(). This implementation should satisfy that any Card with the same Rank value and same
     * Suit should have the same hashCode.
     * @return the Rank value and Suit ordinal as the hasCode.
     */
    @Override
    public int hashCode() {
        return rank.toString().hashCode() + suit.ordinal();
    }

    /**
     * Override Object.equals().
     * @param obj the Object to determine equality with.
     * @return whether there is equality or not.
     */
    @Override
    public boolean equals(Object obj) {
        return this.getClass() == obj.getClass() && this.hashCode() == obj.hashCode();
    }

    @Override
    public int compareTo(Card card) {
        return this.rank.getRankValue() - card.rank.getRankValue();
    }
}
