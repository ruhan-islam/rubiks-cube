/*
 * Sample Skeleton for 'login.fxml' Controller Class
 */

package control;

import java.net.URL;
import java.util.ResourceBundle;

import client.Client;
import design.Selection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import sample.Main;
import util.NetworkUtil;

public class loginController {
    private NetworkUtil nc;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="registrationMaterial"
    private AnchorPane registrationMaterial; // Value injected by FXMLLoader

    @FXML // fx:id="usernameField"
    private TextField usernameField; // Value injected by FXMLLoader

    @FXML // fx:id="nameField"
    private TextField nameField; // Value injected by FXMLLoader

    @FXML // fx:id="emailField"
    private TextField emailField; // Value injected by FXMLLoader

    @FXML // fx:id="submitButton"
    private Button submitButton; // Value injected by FXMLLoader

    @FXML // fx:id="usernameMsg"
    private Label usernameMsg; // Value injected by FXMLLoader

    @FXML // fx:id="confirmPasswordMsg"
    private Label confirmPasswordMsg; // Value injected by FXMLLoader

    @FXML // fx:id="passwordField"
    private PasswordField passwordField; // Value injected by FXMLLoader

    @FXML // fx:id="confirmPasswordField"
    private PasswordField confirmPasswordField; // Value injected by FXMLLoader

    @FXML // fx:id="nameMsg"
    private Label nameMsg; // Value injected by FXMLLoader

    @FXML // fx:id="emailMsg"
    private Label emailMsg; // Value injected by FXMLLoader

    @FXML // fx:id="passwordMsg"
    private Label passwordMsg; // Value injected by FXMLLoader

    @FXML // fx:id="loginUsername"
    private TextField loginUsername; // Value injected by FXMLLoader

    @FXML // fx:id="loginPassword"
    private PasswordField loginPassword; // Value injected by FXMLLoader

    @FXML // fx:id="loginButton"
    private Button loginButton; // Value injected by FXMLLoader

    @FXML // fx:id="usernameError"
    private Label usernameError; // Value injected by FXMLLoader

    @FXML // fx:id="passwordError"
    private Label passwordError; // Value injected by FXMLLoader

    @FXML // fx:id="registerButton"
    private Button registerButton; // Value injected by FXMLLoader

    @FXML // fx:id="registrationSuccessMsg"
    private Label registrationSuccessMsg; // Value injected by FXMLLoader

    @FXML
    void loginButtonPressed(ActionEvent event) {
        boolean check = false;
        usernameError.setVisible(false);
        passwordError.setVisible(false);

        if(loginUsername.getText().trim().equals("")) {
            usernameError.setText("Username can't be empty");
            usernameError.setVisible(true);
            check = true;
        }
        else {
            usernameError.setVisible(false);
        }

        if(loginPassword.getText().trim().equals("")) {
            passwordError.setText("Password can't be empty");
            passwordError.setVisible(true);
            check = true;
        }
        else {
            passwordError.setVisible(false);
        }

        if (check) return;

        nc.write("Login");
        nc.write(loginUsername.getText());
        nc.write(loginPassword.getText());

        String serverMsg = (String) nc.read();

        if (serverMsg.equals("login successful")) {
            Main.username = loginUsername.getText();
            try {
                Selection.showSelectionPage();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else if (serverMsg.equals("invalid username")) {
            usernameError.setText("Username not found. Please register.");
            usernameError.setVisible(true);
        }
        else if (serverMsg.equals("already logged in")) {
            usernameError.setText("You are already logged in somewhere else.");
            usernameError.setVisible(true);
        }
        else if (serverMsg.equals("password mismatch")) {
            passwordError.setText("Password does not match.");
            passwordError.setVisible(true);
        }
    }

    @FXML
    void registerButtonPressed(ActionEvent event) {
        registrationMaterial.setVisible(true);
        registrationSuccessMsg.setVisible(false);
        loginUsername.setText("");
        loginPassword.setText("");
        usernameError.setVisible(false);
        passwordError.setVisible(false);
    }

    @FXML
    void submitButtonPressed(ActionEvent event) {
        boolean check = false;

        if(usernameField.getText().trim().equals("")) {
            usernameMsg.setText("Username can't be empty");
            check = true;
        } else {
            usernameMsg.setText("Username must be unique.");
        }

        if(nameField.getText().trim().equals("")) {
            nameMsg.setText("Name can't be empty");
            nameMsg.setVisible(true);
            check = true;
        } else {
            nameMsg.setVisible(false);
        }

        if(emailField.getText().trim().equals("")) {
            emailMsg.setText("Email can't be empty");
            emailMsg.setVisible(true);
            check = true;
        }
        else if (!emailField.getText().contains("@") || !emailField.getText().contains("@")) {
            emailMsg.setText("invalid Email address");
            emailMsg.setVisible(true);
            check = true;
        }
        else {
            emailMsg.setVisible(false);
        }

        if(passwordField.getText().trim().equals("")) {
            passwordMsg.setText("Password can't be empty");
            passwordMsg.setVisible(true);
            check = true;
        } else {
            passwordMsg.setVisible(false);
        }

        if(confirmPasswordField.getText().trim().equals("")) {
            confirmPasswordMsg.setText("Password can't be empty");
            confirmPasswordMsg.setVisible(true);
            check = true;
        } else {
            confirmPasswordMsg.setVisible(false);
        }

        if(!(confirmPasswordField.getText().equals(passwordField.getText()))) {
            confirmPasswordMsg.setText("Password does not match.");
            confirmPasswordMsg.setVisible(true);
            check = true;
        } else {
            confirmPasswordMsg.setVisible(false);
        }

        if(check) return;

        nc.write("Register");
        nc.write(usernameField.getText());
        nc.write(passwordField.getText());
        nc.write(nameField.getText());
        nc.write(emailField.getText());

        String serverMsg = (String) nc.read();

        if (serverMsg.equalsIgnoreCase("username already in use")) {
            usernameMsg.setText("Username already in use.");
            return;
        }



        usernameField.setText("");
        nameField.setText("");
        emailField.setText("");
        passwordField.setText("");
        confirmPasswordField.setText("");

        registrationMaterial.setVisible(false);
        registrationSuccessMsg.setVisible(true);
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert registrationMaterial != null : "fx:id=\"registrationMaterial\" was not injected: check your FXML file 'login.fxml'.";
        assert usernameField != null : "fx:id=\"usernameField\" was not injected: check your FXML file 'login.fxml'.";
        assert nameField != null : "fx:id=\"nameField\" was not injected: check your FXML file 'login.fxml'.";
        assert emailField != null : "fx:id=\"emailField\" was not injected: check your FXML file 'login.fxml'.";
        assert submitButton != null : "fx:id=\"submitButton\" was not injected: check your FXML file 'login.fxml'.";
        assert usernameMsg != null : "fx:id=\"usernameMsg\" was not injected: check your FXML file 'login.fxml'.";
        assert confirmPasswordMsg != null : "fx:id=\"confirmPasswordMsg\" was not injected: check your FXML file 'login.fxml'.";
        assert passwordField != null : "fx:id=\"passwordField\" was not injected: check your FXML file 'login.fxml'.";
        assert confirmPasswordField != null : "fx:id=\"confirmPasswordField\" was not injected: check your FXML file 'login.fxml'.";
        assert nameMsg != null : "fx:id=\"nameMsg\" was not injected: check your FXML file 'login.fxml'.";
        assert emailMsg != null : "fx:id=\"emailMsg\" was not injected: check your FXML file 'login.fxml'.";
        assert passwordMsg != null : "fx:id=\"passwordMsg\" was not injected: check your FXML file 'login.fxml'.";
        assert loginUsername != null : "fx:id=\"loginUsername\" was not injected: check your FXML file 'login.fxml'.";
        assert loginPassword != null : "fx:id=\"loginPassword\" was not injected: check your FXML file 'login.fxml'.";
        assert loginButton != null : "fx:id=\"loginButton\" was not injected: check your FXML file 'login.fxml'.";
        assert usernameError != null : "fx:id=\"usernameError\" was not injected: check your FXML file 'login.fxml'.";
        assert passwordError != null : "fx:id=\"passwordError\" was not injected: check your FXML file 'login.fxml'.";
        assert registerButton != null : "fx:id=\"registerButton\" was not injected: check your FXML file 'login.fxml'.";
        assert registrationSuccessMsg != null : "fx:id=\"registrationSuccessMsg\" was not injected: check your FXML file 'login.fxml'.";

        nc = Client.client.nc;
    }
}
