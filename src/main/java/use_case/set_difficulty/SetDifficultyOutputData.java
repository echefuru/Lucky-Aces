package use_case.set_difficulty;

/**
 * Output Data for the Set Difficulty Use Case.
 */
public class SetDifficultyOutputData {

    private final int difficulty;
    private final String playerID;
    private final String roomName;

    public SetDifficultyOutputData(int difficulty, String playerID, String roomName) {
        this.difficulty = difficulty;
        this.playerID = playerID;
        this.roomName = roomName;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public String getPlayerID() {
        return playerID;
    }

//    public String getRoomName() {
//        return roomName;
    }

}
