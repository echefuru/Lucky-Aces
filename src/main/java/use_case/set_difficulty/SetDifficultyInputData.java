package use_case.set_difficulty;

/**
 * The Input Data for the SetDifficulty Use Case.
 */
public class SetDifficultyInputData {

    private final String newDifficulty;
    private final String playerID;
    private final String roomName;

    public SetDifficultyInputData(String newDifficulty, String playerID, String roomName) {
        this.newDifficulty = newDifficulty;
        this.playerID = playerID;
        this.roomName = roomName;
    }

    String getNewDifficulty() {
        return newDifficulty;
    }

    String getPlayerID() {
        return playerID;
    }

    String getRoomName() {
        return roomName;
    }
}
