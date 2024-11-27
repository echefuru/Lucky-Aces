package data_type;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * The builder for a GameInfo object.
 */
public class GameInfoBuilder {
    private String name;
    private String description;
    private String rules;
    private int maxPlayers;
    private int minPlayers;
    private boolean isAvailable;
    private JSONArray type;
    private JSONObject defaultConfig;

    /**
     * Sets the name of the GameInfo object.
     * @param name the name of the game
     * @return this builder
     */
    public GameInfoBuilder setName(String name) {
        this.name = name;
        return this;
    }

    /**
     * Sets the description of the GameInfo object.
     * @param description the description of the game
     * @return this builder
     */
    public GameInfoBuilder setDescription(String description) {
        this.description = description;
        return this;
    }

    /**
     * Sets the rules of the GameInfo object.
     * @param rules the rules of the game
     * @return this builder
     */
    public GameInfoBuilder setRules(String rules) {
        this.rules = rules;
        return this;
    }

    /**
     * Sets the max players of the GameInfo object.
     * @param maxPlayers the maximum number of players of the game
     * @return this builder
     */
    public GameInfoBuilder setMaxPlayers(int maxPlayers) {
        this.maxPlayers = maxPlayers;
        return this;
    }

    /**
     * Sets the min players of the GameInfo object.
     * @param minPlayers the minimum number of players of the game
     * @return this builder
     */
    public GameInfoBuilder setMinPlayers(int minPlayers) {
        this.minPlayers = minPlayers;
        return this;
    }

    /**
     * Sets the availability of the GameInfo object.
     * @param isAvailable the availability of the game
     * @return this builder
     */
    public GameInfoBuilder setIsAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
        return this;
    }

    /**
     * Sets the type of the GameInfo object.
     * @param type the type(s) of the game
     * @return this builder
     */
    public GameInfoBuilder setType(JSONArray type) {
        this.type = type;
        return this;
    }

    /**
     * Sets the default config of the GameInfo object.
     * @param defaultConfig the default configs of the game
     * @return this builder
     */
    public GameInfoBuilder setDefaultConfig(JSONObject defaultConfig) {
        this.defaultConfig = defaultConfig;
        return this;
    }

    /**
     * Creates a GameInfo object.
     * @return a GameInfo object containing the given information
     */
    public GameInfo createGameInfo() {
        return new GameInfo(this);
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

    public int getMaxPlayers() {
        return maxPlayers;
    }

    public int getMinPlayers() {
        return minPlayers;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public JSONArray getType() {
        return type;
    }

    public JSONObject getDefaultConfig() {
        return defaultConfig;
    }
}
