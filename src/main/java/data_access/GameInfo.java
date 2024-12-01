package data_access;

import org.json.JSONArray;
import org.json.JSONObject;

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
    private final JSONArray type;
    private final JSONObject defaultConfig;

    public GameInfo(GameInfoBuilder builder) {
        this.name = builder.getName();
        this.description = builder.getDescription();
        this.rules = builder.getRules();
        this.maxPlayers = builder.getMaxPlayers();
        this.minPlayers = builder.getMinPlayers();
        this.isAvailable = builder.isAvailable();
        this.type = builder.getType();
        this.defaultConfig = builder.getDefaultConfig();
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getRules() {
        return rules;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public JSONArray getType() {
        return type;
    }

    public int getMaxPlayers() {
        return maxPlayers;
    }

    public int getMinPlayers() {
        return minPlayers;
    }

    public JSONObject getDefaultConfig() {
        return defaultConfig;
    }
}
