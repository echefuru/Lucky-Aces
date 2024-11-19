package interface_adapter.set_difficulty;

/**
 * The State information representing the set difficulty use case.
 */
public class SetDifficultyState {

    private String newDifficulty;
    private String playerID;
    private String roomName;
    private String errorMessage;

    public SetDifficultyState(SetDifficultyState copy) {
        this.newDifficulty = copy.newDifficulty;
        this.playerID = copy.playerID;
        this.roomName = copy.roomName;
        this.errorMessage = copy.errorMessage;
    }

    public SetDifficultyState() {

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

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setNewDifficulty(String newDifficulty) {
        this.newDifficulty = newDifficulty;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

}
