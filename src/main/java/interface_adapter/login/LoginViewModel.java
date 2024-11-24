package interface_adapter.login;

import interface_adapter.ViewModel;

/**
 * The View Model for the Login View.
 */
public class LoginViewModel extends ViewModel<LoginState> {

    public static final String VIEW_NAME = "log in";

    public static final String TITLE_LABEL = "Player Login";
    public static final String PLAYER_ID_LABEL = "Player ID";
    public static final String PASSWORD_LABEL = "Password";

    public static final String LOGIN_BUTTON_LABEL = "Login";
    public static final String TO_SIGNUP_BUTTON_LABEL = "Go to Sign Up";

    public LoginViewModel() {
        super(VIEW_NAME);
        setState(new LoginState());
    }

}
