package data_type;

/**
 * The representation of a type of card game. Stores all descriptions, rules, etc. of the game.
 */
public class GameInfo {
    private final String name;
    private final String description;
    private final String rules;
    private final int maxPlayers;
    private final int minPlayers;
    private final boolean isAvailable;

    public GameInfo(String name, String description, String rules, int maxPlayers, int minPlayers,
                    boolean isAvailable) {
        this.name = name;
        this.description = description;
        this.rules = rules;
        this.maxPlayers = maxPlayers;
        this.minPlayers = minPlayers;
        this.isAvailable = isAvailable;
    }

    /**
     * Return the name of the game.
     * @return the name of the game
     */
    public String getName() {
        return name;
    }

    /**
     * Return the description of the game.
     * @return the description of the game
     */
    public String getDescription() {
        return description;
    }

    public String getRules() {
        return rules;
    }

    /**
     * Return whether the game is available.
     * @return true if and only if the game is ready to be played.
     */
    public boolean isAvailable() {
        return isAvailable;
    }
}
