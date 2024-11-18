package entity;

/**
 * Enumeration of Card suits.
 */
public enum Suit {

    CLUBS("CLUBS"),
    DIAMONDS("DIAMONDS"),
    HEARTS("HEARTS"),
    SPADES("SPADES");

    private final String suitValue;

    Suit(String suitValue) {
        this.suitValue = suitValue;
    }

    public String getSuitValue() {
        return suitValue;
    }
}
