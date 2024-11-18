package use_case.set_difficulty;

/**
 * The Input Data for the SetDifficulty Use Case.
 */
public class SetDifficultyInputData {

    private final int difficulty;
    private final String playerID;

    public SetDifficultyInputData(int difficulty, String playerID) {
        this.difficulty = difficulty;
        this.playerID = playerID;
    }

    int getDifficulty() {
        return difficulty;
    }

    String getPlayerID() {
        return playerID;
    }
}
