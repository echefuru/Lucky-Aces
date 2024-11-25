package data_access;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import data_type.GameInfo;
import use_case.GameLibraryGameInfoDataAccessInterface;

/**
 * In-memory implementation of the DAO of the list of available games in the program.
 */
public class GameInfoDataAccessObject implements GameLibraryGameInfoDataAccessInterface {
    private final Map<String, GameInfo> games;
    private final String[] availableGames;

    public GameInfoDataAccessObject(String filePath) {
        try {
            final JSONArray data = new JSONArray(Files.readString(Paths.get(
                    getClass().getClassLoader().getResource(filePath).toURI())));

            games = new HashMap<String, GameInfo>(data.length());
            for (int i = 0; i < data.length(); i++) {
                final JSONObject game = data.getJSONObject(i);
                final String name = game.getString("name");
                final String description = game.getString("description");
                final int maxPlayers = game.getInt("max_players");
                final int minPlayers = game.getInt("min_players");
                final boolean isAvailable = game.getBoolean("is_available");
                final JSONArray type = game.getJSONArray("type");

                final GameInfo gameInfo = new GameInfo(name, description, maxPlayers, minPlayers, isAvailable, type);

                games.put(name, gameInfo);
            }

            availableGames = games.keySet().toArray(new String[0]);
            Arrays.sort(availableGames);
        }
        catch (IOException | URISyntaxException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public String getName(String game) {
        return games.get(game).getName();
    }

    @Override
    public String getDescription(String game) {
        return games.get(game).getDescription();
    }

    @Override
    public boolean isAvailable(String game) {
        return games.get(game).isAvailable();
    }

    @Override
    public String[] getAvailableGames() {
        return availableGames;
    }

    @Override
    public JSONArray getType(String game) {
        return games.get(game).getType();
    }

    @Override
    public int getMaxPlayer(String game) {
        return games.get(game).getMaxPlayers();
    }

    @Override
    public int getMinPlayer(String game) {
        return games.get(game).getMinPlayers();
    }
}
