/*
 * Sample Skeleton for 'homepage.fxml' Controller Class
 */

package control;

import java.net.URL;
import java.util.ResourceBundle;

import design.Login;
import design.Play;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import sample.Main;

public class homepageController {
    private Stage stage = Main.stage;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="SinglePlayerButton"
    private Button SinglePlayerButton; // Value injected by FXMLLoader

    @FXML // fx:id="MultiPlayerButton"
    private Button MultiPlayerButton; // Value injected by FXMLLoader

    @FXML // fx:id="logoutButton"
    private Button logoutButton; // Value injected by FXMLLoader

    @FXML // fx:id="exitButton"
    private Button exitButton; // Value injected by FXMLLoader

    @FXML
    void MultiPlayerButtonPressed(ActionEvent event) {
        try {
            design.Multiplayer.showMultiplayerPage();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void SinglePlayerButtonPressed(ActionEvent event) {
        try {
            Play.showPlayPage(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void exitButtonPressed(ActionEvent event) {
        stage.close();
    }

    @FXML
    void logoutButtonPressed(ActionEvent event) {
        Login.showLoginPage();
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert SinglePlayerButton != null : "fx:id=\"SinglePlayerButton\" was not injected: check your FXML file 'homepage.fxml'.";
        assert MultiPlayerButton != null : "fx:id=\"MultiPlayerButton\" was not injected: check your FXML file 'homepage.fxml'.";
        assert logoutButton != null : "fx:id=\"logoutButton\" was not injected: check your FXML file 'homepage.fxml'.";
        assert exitButton != null : "fx:id=\"exitButton\" was not injected: check your FXML file 'homepage.fxml'.";
    }
}
