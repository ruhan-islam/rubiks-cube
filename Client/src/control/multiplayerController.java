/*
 * Sample Skeleton for 'multiplayer.fxml' Controller Class
 */

package control;

import java.net.URL;
import java.util.ResourceBundle;

import client.Client;
import design.Login;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import embeddedServer.Server;

public class multiplayerController {

    public static Button startBtn;
    public static Button joinBtn;
    public static Button createBtn;
    public static Label [] createLabels = new Label[5];
    public static Label [] joinLabels = new Label[5];

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="createButton"
    private Button createButton; // Value injected by FXMLLoader

    @FXML // fx:id="joinButton"
    private Button joinButton; // Value injected by FXMLLoader

    @FXML // fx:id="createLabel1"
    private Label createLabel1; // Value injected by FXMLLoader

    @FXML // fx:id="createLabel2"
    private Label createLabel2; // Value injected by FXMLLoader

    @FXML // fx:id="createLabel3"
    private Label createLabel3; // Value injected by FXMLLoader

    @FXML // fx:id="createLabel4"
    private Label createLabel4; // Value injected by FXMLLoader

    @FXML // fx:id="createMsg"
    private Label createMsg; // Value injected by FXMLLoader

    @FXML // fx:id="joinLabel1"
    private Label joinLabel1; // Value injected by FXMLLoader

    @FXML // fx:id="joinLabel2"
    private Label joinLabel2; // Value injected by FXMLLoader

    @FXML // fx:id="joinLabel3"
    private Label joinLabel3; // Value injected by FXMLLoader

    @FXML // fx:id="joinLabel4"
    private Label joinLabel4; // Value injected by FXMLLoader

    @FXML // fx:id="joinMsg"
    private Label joinMsg; // Value injected by FXMLLoader

    @FXML // fx:id="startButton"
    private Button startButton; // Value injected by FXMLLoader

    @FXML // fx:id="logoutButton"
    private Button logoutButton; // Value injected by FXMLLoader

    @FXML
    void createButtonPressed(ActionEvent event) {
        joinButton.setDisable(true);
        createButton.setDisable(true);
        logoutButton.setDisable(true);
        Server.create();
        try {
            Client.create();
        } catch (Exception e) {
            e.printStackTrace();
        }
        createMsg.setVisible(true);
        joinMsg.setVisible(false);
        if(Server.clientSerial > 1) startButton.setDisable(false);
    }

    @FXML
    void joinButtonPressed(ActionEvent event) {
        createButton.setDisable(true);
        joinButton.setDisable(true);
        logoutButton.setDisable(true);
        Client.create();
    }

    @FXML
    void logoutButtonPressed(ActionEvent event) {
        Login.showLoginPage();
    }

    @FXML
    void startButtonPressed(ActionEvent event) {
        Server.serverNeedNotAcceptAnymore = true;
        System.out.println("mpc: " + Server.serverNeedNotAcceptAnymore);
        //Server.clientSerial = 5;

        for (int i = 0; i < Server.clientSerial; i++) {
            Server.nus[i].write(Server.ints);
            Server.nus[i].write(Server.clientSerial);
        }

//        try {
//            Play.showPlayPage(Server.clientSerial);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert createButton != null : "fx:id=\"createButton\" was not injected: check your FXML file 'multiplayer.fxml'.";
        assert joinButton != null : "fx:id=\"joinButton\" was not injected: check your FXML file 'multiplayer.fxml'.";
        assert createLabel1 != null : "fx:id=\"createLabel1\" was not injected: check your FXML file 'multiplayer.fxml'.";
        assert createLabel2 != null : "fx:id=\"createLabel2\" was not injected: check your FXML file 'multiplayer.fxml'.";
        assert createLabel3 != null : "fx:id=\"createLabel3\" was not injected: check your FXML file 'multiplayer.fxml'.";
        assert createLabel4 != null : "fx:id=\"createLabel4\" was not injected: check your FXML file 'multiplayer.fxml'.";
        assert createMsg != null : "fx:id=\"createMsg\" was not injected: check your FXML file 'multiplayer.fxml'.";
        assert joinLabel1 != null : "fx:id=\"joinLabel1\" was not injected: check your FXML file 'multiplayer.fxml'.";
        assert joinLabel2 != null : "fx:id=\"joinLabel2\" was not injected: check your FXML file 'multiplayer.fxml'.";
        assert joinLabel3 != null : "fx:id=\"joinLabel3\" was not injected: check your FXML file 'multiplayer.fxml'.";
        assert joinLabel4 != null : "fx:id=\"joinLabel4\" was not injected: check your FXML file 'multiplayer.fxml'.";
        assert joinMsg != null : "fx:id=\"createMsg\" was not injected: check your FXML file 'multiplayer.fxml'.";
        assert startButton != null : "fx:id=\"startButton\" was not injected: check your FXML file 'multiplayer.fxml'.";
        assert logoutButton != null : "fx:id=\"logoutButton\" was not injected: check your FXML file 'multiplayer.fxml'.";

        startBtn = startButton;
        createLabels[0] = createMsg;
        createLabels[1] = createLabel1;
        createLabels[2] = createLabel2;
        createLabels[3] = createLabel3;
        createLabels[4] = createLabel4;
        joinBtn = joinButton;
        joinLabels[0] = joinMsg;
        joinLabels[1] = joinLabel1;
        joinLabels[2] = joinLabel2;
        joinLabels[3] = joinLabel3;
        joinLabels[4] = joinLabel4;
        createBtn = createButton;
    }
}
