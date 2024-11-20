package use_case.change_password;

import entity.player.Player;
import entity.player.PlayerFactory;

/**
 * The Change Password Interactor.
 */
public class ChangePasswordInteractor implements ChangePasswordInputBoundary {
    private final ChangePasswordUserDataAccessInterface userDataAccessObject;
    private final ChangePasswordOutputBoundary userPresenter;
    private final PlayerFactory playerFactory;

    public ChangePasswordInteractor(ChangePasswordUserDataAccessInterface changePasswordDataAccessInterface,
                                    ChangePasswordOutputBoundary changePasswordOutputBoundary,
                                    PlayerFactory playerFactory) {
        this.userDataAccessObject = changePasswordDataAccessInterface;
        this.userPresenter = changePasswordOutputBoundary;
        this.playerFactory = playerFactory;
    }

    @Override
    public void execute(ChangePasswordInputData changePasswordInputData) {
        final Player player = playerFactory.create(changePasswordInputData.getPlayerID(),
                                             changePasswordInputData.getPassword());
        userDataAccessObject.changePassword(player);

        final ChangePasswordOutputData changePasswordOutputData = new ChangePasswordOutputData(player.getPlayerID(),
                                                                                  false);
        userPresenter.prepareSuccessView(changePasswordOutputData);
    }

    @Override
    public void switchToLoginView() {
        userPresenter.switchToLoginView();
    }

    @Override
    public void switchToGameLibraryView() {
        userPresenter.switchToGameLibraryView();
    }
}
