package use_case.set_difficulty;

/**
 * Output Data for the Set Difficulty Use Case.
 */
public class SetDifficultyOutputData {

    private final int difficulty;
    private final String playerID;

    public SetDifficultyOutputData(int difficulty, String playerID) {
        this.difficulty = difficulty;
        this.playerID = playerID;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public String getPlayerID() {
        return playerID;
    }

}
