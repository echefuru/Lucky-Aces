package view;

import java.awt.Component;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import interface_adapter.change_password.ChangePasswordController;
import interface_adapter.change_password.ChangePasswordState;
import interface_adapter.change_password.ChangePasswordViewModel;
import interface_adapter.logout.LogoutController;

/**
 * The View for when the user is changing password.
 */
public class ChangePasswordView extends JPanel implements PropertyChangeListener {

    private final String viewName = "Change Password";
    private final ChangePasswordViewModel changePasswordViewModel;
    private final JLabel passwordErrorField = new JLabel();
    private ChangePasswordController changePasswordController;
    private LogoutController logoutController;

    private final JLabel playerID;

    private final JButton confirm;
    private final JTextField passwordInputField = new JTextField(15);
    private final JButton cancel;

    public ChangePasswordView(ChangePasswordViewModel changePasswordViewModel) {
        this.changePasswordViewModel = changePasswordViewModel;
        this.changePasswordViewModel.addPropertyChangeListener(this);

        final JLabel title = new JLabel("Change Password Screen");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        final LabelTextPanel playerInputPanel =
                new LabelTextPanel(new JLabel("Password"),
                                   passwordInputField,
                                   ViewConstants.INPUT_PADDING,
                                   ViewConstants.LINE_HEIGHT);

        final JLabel playerIDInfo = new JLabel("Currently logged in:");
        playerID = new JLabel();

        final JPanel buttons = new JPanel();
        confirm = new JButton("Confirm");
        buttons.add(confirm);

        cancel = new JButton("Cancel");
        buttons.add(cancel);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        passwordInputField.getDocument().addDocumentListener(new DocumentListener() {

            private void documentListenerHelper() {
                final ChangePasswordState currentState = changePasswordViewModel.getState();
                currentState.setPassword(passwordInputField.getText());
                changePasswordViewModel.setState(currentState);
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

        confirm.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                evt -> {
                    if (evt.getSource().equals(confirm)) {
                        final ChangePasswordState currentState = changePasswordViewModel.getState();

                        this.changePasswordController.execute(
                                currentState.getPlayerID(),
                                currentState.getPassword());
                    }
                }
        );

        cancel.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                evt -> {
                    if (evt.getSource().equals(cancel)) {
                        changePasswordController.switchToGameLibraryView();
                    }
                }
        );
        this.add(title);
        this.add(playerIDInfo);
        this.add(playerID);

        this.add(playerInputPanel);
        this.add(passwordErrorField);
        this.add(buttons);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("state")) {
            final ChangePasswordState state = (ChangePasswordState) evt.getNewValue();
            playerID.setText(state.getPlayerID());
        }
        else if (evt.getPropertyName().equals("password")) {
            final ChangePasswordState state = (ChangePasswordState) evt.getNewValue();
            JOptionPane.showMessageDialog(null, "password updated for " + state.getPlayerID());
        }
    }

    public String getViewName() {
        return viewName;
    }

    public void setChangePasswordController(ChangePasswordController changePasswordController) {
        this.changePasswordController = changePasswordController;
    }

    public void setLogoutController(LogoutController logoutController) {
        // save the logout controller in the instance variable.
        this.logoutController = logoutController;
    }
}
