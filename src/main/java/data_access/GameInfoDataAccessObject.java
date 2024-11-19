package data_access;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import entity.game_info.GameInfo;
import org.json.JSONArray;
import org.json.JSONObject;
import use_case.gamelibrary.GameLibraryGameInfoDataAccessInterface;
import use_case.login.LoginGameInfoDataAccessInterface;

/**
 * In-memory implementation of the DAO of the list of available games in the program.
 */
public class GameInfoDataAccessObject implements GameLibraryGameInfoDataAccessInterface,
        LoginGameInfoDataAccessInterface {
    private final Map<String, GameInfo> games;
    private String[] availableGames;

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

                final GameInfo gameInfo = new GameInfo(name, description, maxPlayers, minPlayers);

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
    public GameInfo getGame(String game) {
        return games.get(game);
    }

    @Override
    public String[] getAvailableGames() {
        return availableGames;
    }
}
