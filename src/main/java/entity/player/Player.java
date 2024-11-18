package entity.player;

import java.util.List;

import entity.Card;

/**
 * The representation of a user in our program.
 */
public interface Player {

    /**
     * Returns the playerID of the player.
     * @return the playerID of the player.
     */
    String getPlayerID();

    /**
     * Returns the password of the player.
     * @return the password of the player.
     */
    String getPassword();

    /**
     * Returns the cards the player holds.
     * @return the list of Cards of the player.
     */
    List<Card> getCards();

}
