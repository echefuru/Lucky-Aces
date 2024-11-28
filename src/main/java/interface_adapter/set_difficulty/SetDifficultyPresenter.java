package interface_adapter.set_difficulty;

import interface_adapter.ViewManagerModel;
import use_case.set_difficulty.SetDifficultyOutputBoundary;
import use_case.set_difficulty.SetDifficultyOutputData;

/**
 * The Presenter for the Set Difficulty Use Case.
 */
public class SetDifficultyPresenter implements SetDifficultyOutputBoundary {

    private final ViewManagerModel viewManagerModel;
    private final SetDifficultyViewModel setDifficultyViewModel;

    public SetDifficultyPresenter(ViewManagerModel viewManagerModel, SetDifficultyViewModel setDifficultyViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.setDifficultyViewModel = setDifficultyViewModel;
    }

    @Override
    public void prepareSuccessView(SetDifficultyOutputData setDifficultyOutputData) {
        // On success, switch to the game room view.
        final SetDifficultyState setDifficultyState = setDifficultyViewModel.getState();
        setDifficultyState.setNewDifficulty(setDifficultyOutputData.getNewDifficulty());
        this.setDifficultyViewModel.setState(setDifficultyState);
        this.setDifficultyViewModel.firePropertyChanged();

        this.viewManagerModel.setState(setDifficultyViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String errorMessage) {
        final SetDifficultyState setDifficultyState = setDifficultyViewModel.getState();
        setDifficultyState.setErrorMessage(errorMessage);
        setDifficultyViewModel.firePropertyChanged();
    }
}
