package use_case.blackjack;

/**
 * The Blackjack Interactor.
 */
public class BlackjackInteractor implements BlackjackInputBoundary {
    // private final SignupUserDataAccessInterface userDataAccessObject;
    private final BlackjackOutputBoundary blackjackPresenter;

    public BlackjackInteractor(// SignupUserDataAccessInterface signupDataAccessInterface,
                               BlackjackOutputBoundary blackjackOutputBoundary) {
        // this.userDataAccessObject = signupDataAccessInterface;
        this.blackjackPresenter = blackjackOutputBoundary;
    }

//    @Override
//    public void execute(SignupInputData signupInputData) {
//        if (userDataAccessObject.existsByName(signupInputData.getUsername())) {
//            userPresenter.prepareFailView("User already exists.");
//        }
//        else if (!signupInputData.getPassword().equals(signupInputData.getRepeatPassword())) {
//            userPresenter.prepareFailView("Passwords don't match.");
//        }
//        else {
//            final User user = userFactory.create(signupInputData.getUsername(), signupInputData.getPassword());
//            userDataAccessObject.save(user);
//
//            final SignupOutputData signupOutputData = new SignupOutputData(user.getName(), false);
//            userPresenter.prepareSuccessView(signupOutputData);
//        }
//    }

    @Override
    public void switchToGameLibraryView() {
        blackjackPresenter.switchToGameLibraryView();
    }
}

