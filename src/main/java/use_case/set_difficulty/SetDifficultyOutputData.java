package use_case.set_difficulty;

/**
 * Output Data for the Set Difficulty Use Case.
 */
public class SetDifficultyOutputData {

    private final String newDifficulty;
    private final String playerID;
    private final String roomName;

    public SetDifficultyOutputData(String newDifficulty, String playerID, String roomName) {
        this.newDifficulty = newDifficulty;
        this.playerID = playerID;
        this.roomName = roomName;
    }

    public String getNewDifficulty() {
        return newDifficulty;
    }

    public String getPlayerID() {
        return playerID;
    }

    public String getRoomName() {
        return roomName;
    }

}
