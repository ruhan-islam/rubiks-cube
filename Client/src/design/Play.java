package design;

import client.Client;
import control.cubeController;
import control.playController;
import environment.Axis;
import environment.Camera;
import environment.Keyboard;
import environment.Mouse;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import sample.Cube;
import sample.Main;

import java.util.List;
import java.util.Random;


public class Play {
    public static Camera camera;
    public static boolean againState = false;
    public static Scene scene;

    private static Stage stage = Main.stage;
    private static double screenWidth = Main.screenWidth;
    private static double screenHeight = Main.screenHeight;

    public static Group root0;
    public static Group [] roots;
    public static Cube [] cubes;
    public static int total;
    public static int gameNo;
    public static List<String> usernames;

    public static void showPlayPage(int gameSerial) throws Exception {
        gameNo = gameSerial;
        Random random = new Random();
        int value;

        total = 1;
        roots = new Group[total];
        cubes = new Cube[total];

        int [] ints = new int[50];
        cubes[0] = new cubeController(Main.username);
        root0 = new Group();
        root0.getChildren().add(cubes[0].cubeXform);
        root0.setDepthTest(DepthTest.ENABLE);
        for (int i = 0; i < 50; i++) {
            value = random.nextInt() % 36;
            if (value < 0) value *= -1;
            //System.out.println(value);
            ints[i] = value;
        }
        cubes[0].scramble(ints, 0);

//        if (againState) {
//            stage.setScene(scene);
//            stage.show();
//            return;
//        }

        camera = new Camera(root0, screenHeight * 1.5);
        Axis.buildAxes();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Login.class.getResource("play.fxml"));

//        Parent root = loader.load();
//
//        Scene scene = new Scene(root, screenWidth, screenHeight, true);


        Region contentRootRegion = loader.load();

        //Set a default "standard" or "100%" resolution
        double origW = screenWidth;
        double origH = screenHeight;

        //If the Region containing the GUI does not already have a preferred width and height, set it.
        //But, if it does, we can use that setting as the "standard" resolution.
        if (contentRootRegion.getPrefWidth() == Region.USE_COMPUTED_SIZE)
            contentRootRegion.setPrefWidth(origW);
        else
            origW = contentRootRegion.getPrefWidth();

        if (contentRootRegion.getPrefHeight() == Region.USE_COMPUTED_SIZE)
            contentRootRegion.setPrefHeight(origH);
        else
            origH = contentRootRegion.getPrefHeight();

        //Wrap the resizable content in a non-resizable container (Group)
        Group group = new Group(contentRootRegion);

        //Place the Group in a StackPane, which will keep it centered
        StackPane rootPane = new StackPane();
        rootPane.getChildren().add(group);

        //Create the scene initally at the "100%" size
        scene = new Scene(rootPane, origW, origH);

        //Bind the scene's width and height to the scaling parameters on the group
        group.scaleXProperty().bind(scene.widthProperty().divide(origW));
        group.scaleYProperty().bind(scene.heightProperty().divide(origH));

        Keyboard.handleKeyboard(scene, root0);
        Mouse.handleMouse(scene, root0);

        //Set the scene to the window (stage) and show it
        stage.setScene(scene);
        stage.setResizable(true);
        //stage.setFullScreen(true);
        stage.show();
    }

    public static void showMultiPlayerPage(int serial, int participants, int [] ints, int gameSerial, List<String> names) throws Exception {
        gameNo = gameSerial;
        usernames = names;
        Client.client.IDserial = serial;

        total = participants;
        roots = new Group[participants];
        cubes = new Cube[participants];

        for (int i = 0; i < participants; i++) {
            cubes[i] = new cubeController(i);
            roots[i] = new Group();
            roots[i].getChildren().add(cubes[i].cubeXform);
            roots[i].setDepthTest(DepthTest.ENABLE);
            cubes[i].scramble(ints, 50*i);
        }

        root0 = roots[serial];
        playController.cubeID = serial;
        Keyboard.cubeID = serial;
        //System.out.println("root0 = root" + Client.client.IDserial);

        camera = new Camera(root0, screenHeight * 1.5);
        Axis.buildAxes();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Login.class.getResource("play.fxml"));
        //playController.timeline.play();
//        Parent root = loader.load();
//
//        Scene scene = new Scene(root, screenWidth, screenHeight, true);
//

        Region contentRootRegion = loader.load();

        //Set a default "standard" or "100%" resolution
        double origW = screenWidth;
        double origH = screenHeight;

        //If the Region containing the GUI does not already have a preferred width and height, set it.
        //But, if it does, we can use that setting as the "standard" resolution.
        if (contentRootRegion.getPrefWidth() == Region.USE_COMPUTED_SIZE)
            contentRootRegion.setPrefWidth(origW);
        else
            origW = contentRootRegion.getPrefWidth();

        if (contentRootRegion.getPrefHeight() == Region.USE_COMPUTED_SIZE)
            contentRootRegion.setPrefHeight(origH);
        else
            origH = contentRootRegion.getPrefHeight();

        //Wrap the resizable content in a non-resizable container (Group)
        Group group = new Group(contentRootRegion);

        //Place the Group in a StackPane, which will keep it centered
        StackPane rootPane = new StackPane();
        rootPane.getChildren().add(group);

        //Create the scene initally at the "100%" size
        Scene scene = new Scene(rootPane, origW, origH);

        //Bind the scene's width and height to the scaling parameters on the group
        group.scaleXProperty().bind(scene.widthProperty().divide(origW));
        group.scaleYProperty().bind(scene.heightProperty().divide(origH));

        Keyboard.handleKeyboard(scene, root0);
        Mouse.handleMouse(scene, root0);

        //Set the scene to the window (stage) and show it
        stage.setScene(scene);
        stage.setResizable(true);
        //stage.setFullScreen(true);
        stage.show();
    }
}
