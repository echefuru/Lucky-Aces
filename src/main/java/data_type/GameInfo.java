package data_type;

import org.json.JSONArray;

/**
 * The representation of a type of card game. Stores all descriptions, rules, etc. of the game.
 */
public class GameInfo {
    private final String name;
    private final String description;
    private final int maxPlayers;
    private final int minPlayers;
    private final boolean isAvailable;
    private final JSONArray type;

    public GameInfo(String name, String description, int maxPlayers, int minPlayers, boolean isAvailable,
                    JSONArray type) {
        this.name = name;
        this.description = description;
        this.maxPlayers = maxPlayers;
        this.minPlayers = minPlayers;
        this.isAvailable = isAvailable;
        this.type = type;
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

    /**
     * Return whether the game is available.
     * @return true if and only if the game is ready to be played.
     */
    public boolean isAvailable() {
        return isAvailable;
    }

    /**
     * Return the type of the game.
     * @return the type of the game.
     */
    public JSONArray getType() {
        return type;
    }

    /**
     * Return the maxPlayers of the game.
     * @return the maxPlayers of the game
     */
    public int getMaxPlayers() {
        return maxPlayers;
    }

    /**
     * Return the minPlayers of the game.
     * @return the minPlayers of the game
     */
    public int getMinPlayers() {
        return minPlayers;
    }
}
