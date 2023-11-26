package embeddedServer;

import control.multiplayerController;
import javafx.application.Platform;
import sample.Main;
import util.NetworkUtil;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class Server {
    public static Thread thread0;

    public static HashMap<Integer, String> clientMap = new HashMap<>();
    public static List<NetworkUtil> clientList = new ArrayList<>();

    public static boolean serverNeedNotAcceptAnymore = false;

    public static ServerSocket serverSocket;
    public static int clientSerial = 0;
    public static NetworkUtil [] nus = new NetworkUtil[5];
    public static int [] ints = new int[250];

    private static void serve(Socket clientSocket) {
        Random random = new Random();
        nus[clientSerial] = new NetworkUtil(clientSocket);

        String clientName = (String) nus[clientSerial].read();
        clientMap.put(clientSerial,clientName);
        clientList.add(nus[clientSerial]);
        nus[clientSerial].write(clientSerial);
        for (int j = 50*clientSerial ; j < 50*(clientSerial+1); j++) {
            ints[j] = random.nextInt() % 36;
            if(ints[j] < 0) ints[j] *= -1;
        }
        new ReadThreadServer(nus[clientSerial]);
        clientSerial++;

        if(clientSerial > 1) {
            Platform.runLater(() -> {
                multiplayerController.startBtn.setDisable(false);
                multiplayerController.createLabels[clientSerial-1].setText((clientSerial-1) + ". " + clientName + " added");
                multiplayerController.createLabels[clientSerial-1].setVisible(true);
                if (clientSerial == 5) {
                    multiplayerController.createLabels[0].setText("No more opponent can be added!");
                    //multiplayerController.createLabels[0].setVisible(true);
                }
            });
        }
    }

    public static void create() {
         thread0 = new Thread(() -> {
            serverNeedNotAcceptAnymore = false;
            clientList.clear();
            clientMap.clear();
            try {
                serverSocket = new ServerSocket(33333);
            } catch (IOException e) {
                e.printStackTrace();
            }
            //new WriteThreadServer(clientMap, "Server");
            while (true) {
                if(serverNeedNotAcceptAnymore) {
                    System.out.println("breaking...");
                    break;
                }

                Socket clientSocket = null;
                try {
                    System.out.println(serverNeedNotAcceptAnymore);
                    if (!serverNeedNotAcceptAnymore) {
                        clientSocket = serverSocket.accept();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                //System.out.println("client added");
                serve(clientSocket);

                if(clientSerial == 5) serverNeedNotAcceptAnymore = true;
            }
        });
        Main.threadList.add(thread0);
        thread0.start();
    }
}
