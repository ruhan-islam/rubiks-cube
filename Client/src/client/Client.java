package client;

import control.multiplayerController;
import javafx.application.Platform;
import util.NetworkUtil;

public class Client {
    public static Client client;
    private static String serverAddress;
    public int IDserial = 0;

    public NetworkUtil nc;

    private Client(String serverAddress, int serverPort) {
        nc = new NetworkUtil(serverAddress, serverPort);
    }

    public static void create() {
        int serverPort = 33333;
        serverAddress = "127.0.0.1";

        //System.out.println(Server.serverNeedNotAcceptAnymore + " " + i);

        Thread t;
        t = new Thread(() -> {
            try {
                //System.out.println("creating...");
                client = new Client(serverAddress, serverPort);
            } catch (Exception e) {
                Platform.runLater(() -> {
                    multiplayerController.joinBtn.setDisable(false);
                    multiplayerController.createBtn.setDisable(false);
                    multiplayerController.joinLabels[0].setText("Can't connect to embeddedServer.\nPlease try again.");
                    multiplayerController.joinLabels[0].setVisible(true);
                });
            }
        });
        //Main.threadList.add(t);
        t.start();
    }
}
