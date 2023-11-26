package server;

import util.NetworkUtil;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Server {
    public static int gameNo;
    public static int p2, p3, p4, p5;
    public static List<String> p2List, p3List, p4List, p5List;

    private ServerSocket serverSocket;
    static HashMap<String, String> userMap;
    static List<String> userList;
    static List<String> loggedinList;
    //static List<NetworkUtil> ncList;
    static HashMap<Integer, Integer> gameMap;
    static HashMap<Integer, HashMap<String, String>> dataMap;
    static HashMap<String, NetworkUtil> clientMap;
    static HashMap<Integer, List<NetworkUtil>> eventMap;

    private Server() {
        userMap = new HashMap<>();
        userList = new ArrayList<>();
        loggedinList = new ArrayList<>();
        //ncList = new ArrayList<>();
        gameMap = new HashMap<>();
        dataMap = new HashMap<>();
        clientMap = new HashMap<>();
        eventMap = new HashMap<>();

        gameNo = gameMap.size();
        System.out.println(gameNo);


        try (ObjectInputStream usermap = new ObjectInputStream(new FileInputStream("usermap"));
             ObjectInputStream userlist = new ObjectInputStream(new FileInputStream("userlist"));
             //ObjectInputStream gamemap = new ObjectInputStream(new FileInputStream("gamemap"))
        ) {
            try {
                userMap = (HashMap<String, String>) usermap.readObject();
                userList = (List<String>) userlist.readObject();
                //gameMap = (HashMap<Integer, Integer>) gamemap.readObject();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            //System.out.println(userMap);
            //System.out.println(userList);
        } catch (IOException e) {
            e.printStackTrace();
        }














        try {
            serverSocket = new ServerSocket(33333);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //new WriteThreadServer(clientMap, "Server");
        while (true) {
            Socket clientSocket = null;
            try {
                clientSocket = serverSocket.accept();
            } catch (IOException e) {
                e.printStackTrace();
            }
            serve(clientSocket);
        }
    }

    private void serve(Socket clientSocket) {
        System.out.println("Client added");
        NetworkUtil nc = new NetworkUtil(clientSocket);

        new ReadThreadServer(nc);

        //clientMap.put(clientName, nc);
        //new ReadThread(nc);
    }

    public static void main(String args[]) {
        /*Server server = */ new Server();
    }
}
