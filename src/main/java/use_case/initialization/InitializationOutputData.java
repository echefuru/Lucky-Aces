package use_case.initialization;

/**
 * Output Data for the Initialization Use Case.
 */
public class InitializationOutputData {

    private final String[] games;

    public InitializationOutputData(String[] games) {
        this.games = games;
    }

    public String[] getGames() {
        return games;
    }
}
