package interface_adapter.select_game;

import interface_adapter.ViewManagerModel;
import use_case.select_game.SelectGameOutputBoundary;

/**
 * Presenter for the select game use case.
 */
public class SelectGamePresenter implements SelectGameOutputBoundary {
    private final ViewManagerModel viewManagerModel;

    public SelectGamePresenter(ViewManagerModel viewManagerModel) {
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView() {

    }

    @Override
    public void prepareFailView() {

    }
}
