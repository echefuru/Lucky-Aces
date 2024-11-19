package entity.game_info;

/**
 * The representation of a type of card game. Stores all descriptions, rules, etc. of the game.
 */
public class GameInfo {
    private final String name;
    private final String description;
    private final int maxPlayers;
    private final int minPlayers;

    public GameInfo(String name, String description, int maxPlayers, int minPlayers) {
        this.name = name;
        this.description = description;
        this.maxPlayers = maxPlayers;
        this.minPlayers = minPlayers;
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
}
