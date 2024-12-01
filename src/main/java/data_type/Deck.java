package data_type;

/**
 * A Deck of Cards.
 */
public class Deck {

    private final String deckId;
    private int remaining;

    public Deck(String deckId, int remaining) {
        this.deckId = deckId;
        this.remaining = remaining;
    }

    public String getDeckId() {
        return deckId;
    }

    public int getRemaining() {
        return remaining;
    }

    public void setRemaining(int remaining) {
        this.remaining = remaining;
    }
}
