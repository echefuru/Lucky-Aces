package use_case.signup;

/**
 * Output Data for the Signup Use Case.
 */
public class SignupOutputData {

    private final String playerID;

    private final boolean useCaseFailed;

    public SignupOutputData(String playerID, boolean useCaseFailed) {
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
