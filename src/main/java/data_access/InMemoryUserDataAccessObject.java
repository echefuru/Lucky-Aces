package data_access;

import java.util.HashMap;
import java.util.Map;

import entity.player.Player;
import use_case.change_password.ChangePasswordUserDataAccessInterface;
import use_case.login.LoginUserDataAccessInterface;
import use_case.logout.LogoutUserDataAccessInterface;
import use_case.signup.SignupUserDataAccessInterface;

/**
 * In-memory implementation of the DAO for storing user data. This implementation does
 * NOT persist data between runs of the program.
 */
public class InMemoryUserDataAccessObject implements SignupUserDataAccessInterface,
        LoginUserDataAccessInterface,
        ChangePasswordUserDataAccessInterface,
        LogoutUserDataAccessInterface {

    private final Map<String, Player> users = new HashMap<>();

    private String currentUsername;

    @Override
    public boolean existsByName(String identifier) {
        return users.containsKey(identifier);
    }

    @Override
    public void save(Player player) {
        users.put(player.getPlayerID(), player);
    }

    @Override
    public Player get(String username) {
        return users.get(username);
    }

    @Override
    public void changePassword(Player player) {
        // Replace the old entry with the new password
        users.put(player.getPlayerID(), player);
    }

    @Override
    public void setCurrentPlayerID(String name) {
        this.currentUsername = name;
    }

    @Override
    public String getCurrentPlayerID() {
        return this.currentUsername;
    }
}
