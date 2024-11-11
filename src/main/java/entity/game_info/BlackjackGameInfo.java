package entity.game_info;

/**
 * Stores the information of Blackjack.
 */
public class BlackjackGameInfo implements GameInfo {

    private final String name;
    private final String description;

    public BlackjackGameInfo() {
        this.name = "Blackjack";
        // TODO: Write a better description
        this.description = "Also named \"21\". The goal of the game is to have cards that add up to"
                           + "points lower than or equal to 21, but higher than the dealer.";
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public Boolean isLegal(String[] move) {
        // TODO: Implement this
        return true;
    }
}
