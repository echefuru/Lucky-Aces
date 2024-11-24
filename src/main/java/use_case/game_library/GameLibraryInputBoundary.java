package use_case.game_library;

public interface GameLibraryInputBoundary {

    /**
     * Execute the game Initialization Use Case.
     */
    void execute();

    /**
     * Executes the "search" Use Case.
     * @param info search input
     */
    void search(String info);
}
