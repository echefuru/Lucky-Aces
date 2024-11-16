package entity;

/**
 * A Deck of Cards.
 */
public class Deck {

    private final String deckId;

    public Deck(String deckId) {
        this.deckId = deckId;
    }

    public String getDeckId() {
        return deckId;
    }
}
