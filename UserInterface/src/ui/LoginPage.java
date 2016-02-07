package ui;

import utils.ScreenUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

/**
 * Created by hp on 07/02/2016.
 */
public class LoginPage {

    private static final int MINIMUM_USER_PASS_LENGTH = 3;
    private static final Color errorColor = Color.RED;
    private static final String USERNAME_REGISTER_ERROR_MESSAGE = "Username must be at least " + MINIMUM_USER_PASS_LENGTH + " characters !";
    private static final String PASSWORD_REGISTER_ERROR_MESSAGE = "Password must be at least " + MINIMUM_USER_PASS_LENGTH + " characters !";
    private static final String PASSWORD_LOGIN_ERROR_MESSAGE = "Password incorrect !";
    private static JFrame frame;
    private JPanel panel1;
    private JTextField usernameTextField;
    private JPasswordField passwordTextField;
    private JButton loginSignUpButton;
    private JLabel usernameError;
    private JLabel passwordError;

    public LoginPage() {
        loginSignUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                usernameError.setVisible(false);
                passwordError.setVisible(false);

                AccountManager accountManager = AccountManager.getInstance();
                AccountInfo accountInfo = accountManager.getAccountInfo(usernameTextField.getText());
                if (accountInfo == null) {
                    boolean mustReturn = false;
                    if (usernameTextField.getText().length() < MINIMUM_USER_PASS_LENGTH) {
                        usernameError.setText(USERNAME_REGISTER_ERROR_MESSAGE);
                        usernameError.setForeground(errorColor);
                        usernameError.setVisible(true);
                        mustReturn = true;
                    }
                    if (passwordTextField.getPassword().length < MINIMUM_USER_PASS_LENGTH) {
                        passwordError.setText(PASSWORD_REGISTER_ERROR_MESSAGE);
                        passwordError.setForeground(errorColor);
                        passwordError.setVisible(true);
                        mustReturn = true;
                    }
                    if (mustReturn) return;

                    String username = usernameTextField.getText();
                    char[] password = passwordTextField.getPassword();
                    AccountInfo newAccount = new AccountInfo(username, Arrays.toString(password));
                    accountManager.addAccount(newAccount);
                    login(newAccount);

                } else if (Arrays.toString(passwordTextField.getPassword()).equals(accountInfo.password)) {
                    login(accountInfo);
                } else {
                    passwordError.setText(PASSWORD_LOGIN_ERROR_MESSAGE);
                    passwordError.setForeground(errorColor);
                    passwordError.setVisible(true);
                }

            }
        });
    }

    private void login(AccountInfo accountInfo) {
        AccountManager accountManager = AccountManager.getInstance();
        accountManager.setAccountInfo(accountInfo);
        frame.dispose();
        // user logged in -> open game windows
    }

    public static void main(String[] args) {
        frame = new JFrame("Login");
        frame.setContentPane(new LoginPage().panel1);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        ScreenUtils.setCenterLocation(frame);
        frame.setVisible(true);

    }

}
