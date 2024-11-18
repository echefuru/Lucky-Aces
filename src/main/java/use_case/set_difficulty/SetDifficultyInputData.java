package use_case.set_difficulty;

/**
 * The Input Data for the SetDifficulty Use Case.
 */
public class SetDifficultyInputData {

    private final int difficulty;
    private final String playerID;
    private final String roomName;

    public SetDifficultyInputData(int difficulty, String playerID, String roomName) {
        this.difficulty = difficulty;
        this.playerID = playerID;
        this.roomName = roomName;
    }

    int getDifficulty() {
        return difficulty;
    }

    String getPlayerID() {
        return playerID;
    }

    String getRoomName() {
        return roomName;
    }
}
