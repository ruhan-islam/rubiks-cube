/*
 * Sample Skeleton for 'selection.fxml' Controller Class
 */

package control;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import client.Client;
import design.History;
import design.Login;
import design.Play;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.util.Duration;
import sample.Main;
import util.NetworkUtil;

import static design.Play.gameNo;

public class selectionController {
    private NetworkUtil nc = Client.client.nc;
    public static List<String> userinfo;
    static long diff;
    static int remain = 3;
    static Timeline timeline;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="waiting"
    private Label waiting; // Value injected by FXMLLoader

    @FXML // fx:id="time"
    private Label time; // Value injected by FXMLLoader

    @FXML // fx:id="p1"
    private Button p1; // Value injected by FXMLLoader

    @FXML // fx:id="p2"
    private Button p2; // Value injected by FXMLLoader

    @FXML // fx:id="p3"
    private Button p3; // Value injected by FXMLLoader

    @FXML // fx:id="p4"
    private Button p4; // Value injected by FXMLLoader

    @FXML // fx:id="p5"
    private Button p5; // Value injected by FXMLLoader

    @FXML // fx:id="stats"
    private Button stats; // Value injected by FXMLLoader

    @FXML // fx:id="logout"
    private Button logout; // Value injected by FXMLLoader

    @FXML
    void logoutPressed(ActionEvent event) {
        nc.write("logout");
        Main.username = " ";
        Login.showLoginPage();
    }

    @FXML
    void p1Pressed(ActionEvent event) {
        nc.write("p1");
        int gameNo = (int) nc.read();
        String serverMsg = (String) nc.read();
        if (serverMsg.equalsIgnoreCase("start")) {
            waiting.setText("Starting...");
            waiting.setVisible(true);
            startGame();
            timeline.setOnFinished(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try {
                        Play.showPlayPage(gameNo);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    @FXML
    void p2Pressed(ActionEvent event) {
        waiting.setText("Waiting...");
        waiting.setVisible(true);

        p1.setDisable(true);
        p2.setDisable(true);
        p3.setDisable(true);
        p4.setDisable(true);
        p5.setDisable(true);
        logout.setDisable(true);
        stats.setDisable(true);

        nc.write("p2");

//        while (true) {
//            int added = (int) nc.read();
//            System.out.println(added);
//            if (added == 2) {
//                waiting.setText("Starting...");
//                waiting.setVisible(true);
//                break;
//            }
//            waiting.setText(added + " added");
//            waiting.setVisible(true);
//        }

        int gameNo = (int) nc.read();
        List<String> names = (List<String>) nc.read();
        int serial = (int) nc.read();
        int [] ints = (int []) nc.read();
        String serverMsg = (String) nc.read();
        if (serverMsg.equalsIgnoreCase("start")) {
            new client.ReadThreadClient(nc);
            waiting.setText("Starting...");
            waiting.setVisible(true);
            startGame();
            timeline.setOnFinished(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try {
                        Play.showMultiPlayerPage(serial, 2, ints, gameNo, names);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    @FXML
    void p3Pressed(ActionEvent event) {
        p1.setDisable(true);
        p2.setDisable(true);
        p3.setDisable(true);
        p4.setDisable(true);
        p5.setDisable(true);
        logout.setDisable(true);
        stats.setDisable(true);

        nc.write("p3");
        int gameNo = (int) nc.read();
        List<String> names = (List<String>) nc.read();
        int serial = (int) nc.read();
        int [] ints = (int []) nc.read();
        String serverMsg = (String) nc.read();
        if (serverMsg.equalsIgnoreCase("start")) {
            new client.ReadThreadClient(nc);
            waiting.setText("Starting...");
            waiting.setVisible(true);
            startGame();
            timeline.setOnFinished(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try {
                        Play.showMultiPlayerPage(serial, 3, ints, gameNo, names);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    @FXML
    void p4Pressed(ActionEvent event) {
        p1.setDisable(true);
        p2.setDisable(true);
        p3.setDisable(true);
        p4.setDisable(true);
        p5.setDisable(true);
        logout.setDisable(true);
        stats.setDisable(true);

        nc.write("p4");
        int gameNo = (int) nc.read();
        List<String> names = (List<String>) nc.read();
        int serial = (int) nc.read();
        int [] ints = (int []) nc.read();
        String serverMsg = (String) nc.read();
        if (serverMsg.equalsIgnoreCase("start")) {
            new client.ReadThreadClient(nc);
            waiting.setText("Starting...");
            waiting.setVisible(true);
            startGame();
            timeline.setOnFinished(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try {
                        Play.showMultiPlayerPage(serial, 4, ints, gameNo, names);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    @FXML
    void p5Pressed(ActionEvent event) {
        p1.setDisable(true);
        p2.setDisable(true);
        p3.setDisable(true);
        p4.setDisable(true);
        p5.setDisable(true);
        logout.setDisable(true);
        stats.setDisable(true);

        nc.write("p5");
        int gameNo = (int) nc.read();
        List<String> names = (List<String>) nc.read();
        int serial = (int) nc.read();
        int [] ints = (int []) nc.read();
        String serverMsg = (String) nc.read();
        if (serverMsg.equalsIgnoreCase("start")) {
            new client.ReadThreadClient(nc);
            waiting.setText("Starting...");
            waiting.setVisible(true);
            startGame();
            timeline.setOnFinished(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try {
                        Play.showMultiPlayerPage(serial, 5, ints, gameNo, names);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    @FXML
    void statsPressed(ActionEvent event) {
        nc.write("history");

        userinfo = new ArrayList<>();

        Object ob = nc.read();

        if (ob instanceof ArrayList) {
            userinfo = (ArrayList<String>) ob;
        }
        else userinfo.add("History Loading Unsuccessful");
        System.out.println("done");

        try {
            History.showHistory();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert p1 != null : "fx:id=\"p1\" was not injected: check your FXML file 'selection.fxml'.";
        assert p2 != null : "fx:id=\"p2\" was not injected: check your FXML file 'selection.fxml'.";
        assert p3 != null : "fx:id=\"p3\" was not injected: check your FXML file 'selection.fxml'.";
        assert p4 != null : "fx:id=\"p4\" was not injected: check your FXML file 'selection.fxml'.";
        assert p5 != null : "fx:id=\"p5\" was not injected: check your FXML file 'selection.fxml'.";
        assert stats != null : "fx:id=\"stats\" was not injected: check your FXML file 'selection.fxml'.";
        assert logout != null : "fx:id=\"logout\" was not injected: check your FXML file 'selection.fxml'.";
        assert waiting != null : "fx:id=\"waiting\" was not injected: check your FXML file 'selection.fxml'.";
        assert time != null : "fx:id=\"time\" was not injected: check your FXML file 'selection.fxml'.";

        waiting.setVisible(false);
        time.setVisible(false);
    }

    private void startGame() {
        time.setVisible(true);
        final long start = System.currentTimeMillis();
        timeline = new Timeline(
                new KeyFrame(
                        Duration.millis(1000),
                        event -> {
                            diff = System.currentTimeMillis() - start;
                            remain = 3 - (int)(diff/1000);
                            time.setText(remain + "");
                        }
                )
        );
        timeline.setCycleCount(4);
        timeline.play();
    }
}
