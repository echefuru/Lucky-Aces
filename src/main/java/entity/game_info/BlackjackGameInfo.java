package entity.game_info;

/**
 * Stores the information of Blackjack.
 */
public class BlackjackGameInfo implements GameInfo {

    @Override
    public String getName() {
        return "Blackjack";
    }

    @Override
    public String getDescription() {
        // TODO: Write a better description
        return "Also named \"21\". The goal of the game is to have cards that add up to"
                + "points lower than 21, but higher than the dealer.";
    }

    @Override
    public Boolean isLegal(String[] move) {
        // TODO: Implement this
        return true;
    }
}
