package use_case.change_password;

/**
 * Output Data for the Change Password Use Case.
 */
public class ChangePasswordOutputData {

    private final String playerID;

    private final boolean useCaseFailed;

    public ChangePasswordOutputData(String playerID, boolean useCaseFailed) {
        this.playerID = playerID;
        this.useCaseFailed = useCaseFailed;
    }

    public String getPlayerID() {
        return playerID;
    }

    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }
}
