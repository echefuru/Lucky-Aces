package view;

import java.awt.Component;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import interface_adapter.login.LoginController;
import interface_adapter.login.LoginState;
import interface_adapter.login.LoginViewModel;

/**
 * The View for when the user is logging into the program.
 */
public class LoginView extends JPanel implements PropertyChangeListener {

    private final String viewName = "log in";
    private final LoginViewModel loginViewModel;

    private final JTextField playerIDInputField = new JTextField(15);
    private final JPasswordField passwordInputField = new JPasswordField(15);

    private final JLabel errorField = new JLabel();

    private final JButton logIn;
    private final JButton goToSignup;
    private LoginController loginController;

    public LoginView(LoginViewModel loginViewModel) {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.loginViewModel = loginViewModel;
        this.loginViewModel.addPropertyChangeListener(this);

        final JLabel title = new JLabel("Player Login");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        final LabelTextPanel playerIDInfo = new LabelTextPanel(
                new JLabel("Player ID"), playerIDInputField);
        final LabelTextPanel passwordInfo = new LabelTextPanel(
                new JLabel("Password"), passwordInputField);

        playerIDInfo.setAlignmentX(Component.CENTER_ALIGNMENT);
        passwordInfo.setAlignmentX(Component.CENTER_ALIGNMENT);

        final JPanel buttons = new JPanel();
        logIn = new JButton("Log in");
        buttons.add(logIn);
        goToSignup = new JButton("Go to signup");
        buttons.add(goToSignup);

        logIn.addActionListener(evt -> {
            if (evt.getSource().equals(logIn)) {
                final LoginState currentState = loginViewModel.getState();

                loginController.execute(
                        currentState.getPlayerID(),
                        currentState.getPassword()
                );
            }
        });

        goToSignup.addActionListener(evt -> {
            if (evt.getSource().equals(goToSignup)) {
                loginController.switchToSignupView();
            }
        });

        playerIDInputField.getDocument().addDocumentListener(new DocumentListener() {

            private void documentListenerHelper() {
                final LoginState currentState = loginViewModel.getState();
                currentState.setPlayerID(playerIDInputField.getText());
                loginViewModel.setState(currentState);
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                documentListenerHelper();
            }
        });

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        passwordInputField.getDocument().addDocumentListener(new DocumentListener() {

            private void documentListenerHelper() {
                final LoginState currentState = loginViewModel.getState();
                currentState.setPassword(new String(passwordInputField.getPassword()));
                loginViewModel.setState(currentState);
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                documentListenerHelper();
            }
        });

        this.add(title);
        this.add(playerIDInfo);
        this.add(passwordInfo);
        this.add(errorField);
        this.add(buttons);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final LoginState state = (LoginState) evt.getNewValue();
        setFields(state);
        errorField.setText(state.getLoginError());
    }

    private void setFields(LoginState state) {
        playerIDInputField.setText(state.getPlayerID());
        passwordInputField.setText(state.getPassword());
    }

    public String getViewName() {
        return viewName;
    }

    public void setLoginController(LoginController loginController) {
        this.loginController = loginController;
    }
}
