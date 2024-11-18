package entity.game_info;

/**
 * The representation of a type of card game. Stores all descriptions, rules, etc. of the game.
 */
public interface GameInfo {

    /**
     * Return the name of the game.
     * @return the name of the game
     */
    String getName();

    /**
     * Return the description of the game.
     * @return the description of the game
     */
    String getDescription();

    // TODO: Determine other functions and/or parameters as needed by the actual game implementation.
    /**
     * Return true if and only if the move is legal.
     * @param move the proposed move
     *             TODO: Maybe create a Move object??
     * @return legality of a move
     */
    Boolean isLegal(String[] move);
}
