package use_case.signup;

/**
 * The Input Data for the Signup Use Case.
 */
public class SignupInputData {

    private final String playerID;
    private final String password;
    private final String repeatPassword;

    public SignupInputData(String playerID, String password, String repeatPassword) {
        this.playerID = playerID;
        this.password = password;
        this.repeatPassword = repeatPassword;
    }

    String getPlayerID() {
        return playerID;
    }

    String getPassword() {
        return password;
    }

    public String getRepeatPassword() {
        return repeatPassword;
    }
}
