package view;

import java.awt.Component;
import java.awt.Dimension;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.Box;
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

    private final String viewName = LoginViewModel.VIEW_NAME;
    private final LoginViewModel loginViewModel;

    private final JTextField playerIDInputField = new JTextField(ViewConstants.INPUT_COLUMNS);
    private final JPasswordField passwordInputField = new JPasswordField(ViewConstants.INPUT_COLUMNS);

    private final JLabel errorField = new JLabel();

    private final JButton logIn;
    private final JButton goToSignup;
    private LoginController loginController;

    public LoginView(LoginViewModel loginViewModel) {
        this.loginViewModel = loginViewModel;
        this.loginViewModel.addPropertyChangeListener(this);

        final JLabel title = new JLabel(LoginViewModel.TITLE_LABEL);

        final LabelTextPanel playerInputPanel =
                new LabelTextPanel(new JLabel(LoginViewModel.PLAYER_ID_LABEL),
                                   playerIDInputField,
                                   ViewConstants.INPUT_PADDING,
                                   ViewConstants.LINE_HEIGHT);
        playerInputPanel.addLabelText(new JLabel(LoginViewModel.PASSWORD_LABEL), passwordInputField);

        final JPanel buttons = new JPanel();
        logIn = new JButton(LoginViewModel.LOGIN_BUTTON_LABEL);
        buttons.add(logIn);
        goToSignup = new JButton(LoginViewModel.TO_SIGNUP_BUTTON_LABEL);
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

        addComponents(title, playerInputPanel, buttons);
    }

    private void addComponents(JLabel title, LabelTextPanel playerInputPanel, JPanel buttons) {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(Box.createRigidArea(new Dimension(ViewConstants.WINDOW_WIDTH, ViewConstants.THIRD_HEIGHT)));

        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(title);
        this.add(playerInputPanel);
        errorField.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(errorField);
        buttons.setMaximumSize(new Dimension(ViewConstants.WINDOW_WIDTH, ViewConstants.LINE_HEIGHT));
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
