package sample;

import client.Client;
import design.Login;
import javafx.application.Application;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class Main extends Application {
    public static Stage stage;
    public static double screenHeight;
    public static double screenWidth;
    public static List<Thread> threadList = new ArrayList<>();
    public static User user0;
    public static String username = " ";

    @Override
    public void start(Stage primaryStage) {
        stage = primaryStage;
        stage.setTitle("Rubik's Cube");

        stage.setMaximized(true);
        try {
            Login.showLoginPage();
            //Play.showPlayPage(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        stage.setOnCloseRequest((event) -> {
            Client.client.nc.write("exit");

            System.exit(0);
        });
    }

    public static void main(String[] args) {
        screenHeight = Screen.getPrimary().getBounds().getHeight();
        screenWidth = Screen.getPrimary().getBounds().getWidth();
        //System.out.println(screenWidth + " " + screenHeight);

        Client.create();

        launch(args); //start() method will be called
    }
}
