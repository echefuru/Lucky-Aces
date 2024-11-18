package entity;

/**
 * Enumeration of Card ranks.
 */
public enum Rank {

    ONE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8),
    NINE(9),
    TEN(10),
    JACK(10),
    QUEEN(10),
    KING(10),
    ACE(11);

    private final int rankValue;

    Rank(int rankValue) {
        this.rankValue = rankValue;
    }

    public int getRankValue() {
        return rankValue;
    }

    /**
     * A method that returns the appropriate Rank from integer strings like "1".
     * @param string The String to convert to a Rank.
     * @return The Rank converted from the given String.
     * @throws IllegalArgumentException if the given String not in ["1","ACE"].
     */
    public static Rank fromString(String string) throws IllegalArgumentException {
        final Rank formattedRank;
        switch (string) {
            case "1":
                formattedRank = ONE;
                break;
            case "2":
                formattedRank = TWO;
                break;
            case "3":
                formattedRank = THREE;
                break;
            case "4":
                formattedRank = FOUR;
                break;
            case "5":
                formattedRank = FIVE;
                break;
            case "6":
                formattedRank = SIX;
                break;
            case "7":
                formattedRank = SEVEN;
                break;
            case "8":
                formattedRank = EIGHT;
                break;
            case "9":
                formattedRank = NINE;
                break;
            case "10":
                formattedRank = TEN;
                break;
            case "JACK":
            case "QUEEN":
            case "KING":
            case "ACE":
                formattedRank = Rank.valueOf(string);
                break;
            default:
                throw new IllegalArgumentException("Invalid rank: " + string);
        }
        return formattedRank;
    }
}
