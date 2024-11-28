package interface_adapter.set_difficulty;

import interface_adapter.ViewModel;

/**
 * The View Model for the Set Difficulty View.
 */
public class SetDifficultyViewModel extends ViewModel<SetDifficultyState> {

    public SetDifficultyViewModel() {
        super("Game Room");
        setState(new SetDifficultyState());
    }

}
