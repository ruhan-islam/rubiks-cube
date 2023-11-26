package server;

import util.NetworkUtil;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class ReadThreadServer implements Runnable {
    private boolean check;

    private Thread thr;
    private NetworkUtil nc;
    private String user0;
    private ArrayList<String> userinfo;
    private HashMap<String, String> data;
    private Random random;

    ReadThreadServer(NetworkUtil nc) {
        check = true;
        this.nc = nc;
        this.thr = new Thread(this);
        thr.start();
    }

    public void run() {
        start2();
    }

    private void start2() {
        System.out.println("start called");
        boolean isDone = false;

        while (true) {
            String mode = (String) nc.read();

            if (mode.equalsIgnoreCase("Login")) {
                isDone = serveLogin(nc);
            }

            else if (mode.equalsIgnoreCase("Register")) {
                isDone = serveRegister(nc);
                if (isDone) isDone = !isDone;
            }

            else if (mode.equalsIgnoreCase("exit")) {
                isDone = true;
                exit(nc);
                //return;
            }

            if (isDone) break;
        }

        while (true) {
            System.out.println("check = " + check);
            if (!check) {
                System.out.println("breaking");
                break;
            }
            else {
                System.out.println("cont run called");
                continuousRun();
            }
        }
    }

    private void continuousRun() {
        System.out.println("starting cont run");
        boolean continuousCheck = false;
        while (true) {
            String clientMessage = (String) nc.read();
            System.out.println(clientMessage);
            if (clientMessage == null) break;

            if (clientMessage.equalsIgnoreCase("exit")) {
                continuousCheck = true;
                exit(nc);
                break;
            }

            switch (clientMessage) {
                case "logout" : {
                    //continuousCheck = true;
                    logout(nc);
                    break;
                }

                case "p1" : {
                    data = new HashMap<>();
                    System.out.println("p1 pressed");
                    Server.gameNo++;
                    System.out.println(Server.gameNo);
                    Server.gameMap.put(Server.gameNo, 1);
                    Server.dataMap.put(Server.gameNo, data);

                    try (ObjectOutputStream gamemap = new ObjectOutputStream(new FileOutputStream("gamemap"))) {
                        gamemap.writeObject(Server.gameMap);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    nc.write(Server.gameNo);
                    nc.write("start");
                    String clientMsg = (String) nc.read();
                    System.out.println("clientmsg = " + clientMsg);
                    if (clientMsg.equalsIgnoreCase("exit")) {
                        continuousCheck = true;
                        userinfo.add("aborted");
                        exit(nc);
                    }
                    else {
                        String time = (String) nc.read();
                        System.out.println(time);
                        userinfo.add("Single (time = " + time + ")");
                        int gameSerial = (int) nc.read();
                        System.out.println(gameSerial);
                        data = Server.dataMap.get(gameSerial);
                        data.put(user0, time);
                        Server.dataMap.put(Server.gameNo, data);
                        //nc.write("close");

                        String msg = (String) nc.read();
                        switch (msg) {
                            case "exit" : {
                                continuousCheck = true;
                                exit(nc);
                                break;
                            }
                            case "logout" : {
                                logout(nc);
                                break;
                            }
                            case "again" : {
                                System.out.println("again pressed");
                                continuousCheck = true;
                                break;
                            }
                        }
                    }
                    break;
                }

                case "p2" : {
                    if (Server.p2 == 0) {
                        Server.p2List = new ArrayList<>();
                    }

                    Server.p2++;
                    Server.p2List.add(user0);

//                    for (String user : Server.p2List) {
//                        nc.write(Server.p2);
//                    }

                    if (Server.p2 == 2) {
                        data = new HashMap<>();
                        Server.p2 = 0;
                        Server.gameNo++;
                        Server.gameMap.put(Server.gameNo, 2);
                        Server.dataMap.put(Server.gameNo, data);

                        random = new Random();
                        int [] ints = new int[100];
                        for (int i = 0; i < ints.length; i++) {
                            ints[i] = random.nextInt() % 36;
                        }

                        try (ObjectOutputStream gamemap = new ObjectOutputStream(new FileOutputStream("gamemap"))) {
                            gamemap.writeObject(Server.gameMap);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        int i = 0;
                        for (String user : Server.p2List) {
                            Server.clientMap.get(user).write(Server.gameNo);
                            Server.clientMap.get(user).write(Server.p2List);
                            Server.clientMap.get(user).write(i);
                            Server.clientMap.get(user).write(ints);
                            Server.clientMap.get(user).write("start");
                            i++;
                        }
                    }


                    String move;
                    while (true) {
                        move = (String) nc.read();
                        if (move.equalsIgnoreCase("exit") || move.equalsIgnoreCase("done")) {
                            break;
                        }
                        for (String user : Server.p2List) {
                            Server.clientMap.get(user).write(move);
                        }
                    }

                    if (move.equalsIgnoreCase("exit")) {
                        continuousCheck = true;
                        userinfo.add("2 Player: aborted");
                        exit(nc);
                    }
                    else {
                        String time = (String) nc.read();
                        int gameSerial = (int) nc.read();
                        userinfo.add("2 Player: time = " + time);
                        data = Server.dataMap.get(gameSerial);
                        data.put(user0, time);

                        String msg = (String) nc.read();
                        switch (msg) {
                            case "exit" : {
                                continuousCheck = true;
                                exit(nc);
                                break;
                            }
                            case "logout" : {
                                nc.write("logout");
                                logout(nc);
                                break;
                            }
                            case "again" : {
                                System.out.println("again pressed");
                                nc.write("again");
                                continuousCheck = true;
                                break;
                            }
                        }
                    }
                    break;
                }

                case "p3" : {
                    if (Server.p3 == 0) {
                        Server.p3List = new ArrayList<>();
                    }

                    Server.p3++;
                    Server.p3List.add(user0);

                    if (Server.p3 == 3) {
                        data = new HashMap<>();
                        Server.p3 = 0;
                        Server.gameNo++;
                        Server.gameMap.put(Server.gameNo, 3);
                        Server.dataMap.put(Server.gameNo, data);

                        random = new Random();
                        int [] ints = new int[150];
                        for (int i = 0; i < ints.length; i++) {
                            ints[i] = random.nextInt() % 36;
                        }

                        try (ObjectOutputStream gamemap = new ObjectOutputStream(new FileOutputStream("gamemap"))) {
                            gamemap.writeObject(Server.gameMap);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        int i = 0;
                        for (String user : Server.p3List) {
                            Server.clientMap.get(user).write(Server.gameNo);
                            Server.clientMap.get(user).write(Server.p3List);
                            Server.clientMap.get(user).write(i);
                            Server.clientMap.get(user).write(ints);
                            Server.clientMap.get(user).write("start");
                            i++;
                        }
                    }


                    String move;
                    while (true) {
                        move = (String) nc.read();
                        if (move.equalsIgnoreCase("exit") || move.equalsIgnoreCase("done")) {
                            break;
                        }
                        for (String user : Server.p3List) {
                            Server.clientMap.get(user).write(move);
                        }
                    }

                    if (move.equalsIgnoreCase("exit")) {
                        continuousCheck = true;
                        userinfo.add("3 Player: aborted");
                        exit(nc);
                    }
                    else {
                        String time = (String) nc.read();
                        int gameSerial = (int) nc.read();
                        userinfo.add("3 Player: time = " + time);
                        data = Server.dataMap.get(gameSerial);
                        data.put(user0, time);

                        String msg = (String) nc.read();
                        switch (msg) {
                            case "exit" : {
                                continuousCheck = true;
                                exit(nc);
                                break;
                            }
                            case "logout" : {
                                nc.write("logout");
                                logout(nc);
                                break;
                            }
                            case "again" : {
                                nc.write("again");
                                continuousCheck = true;
                                break;
                            }
                        }
                    }
                    break;
                }

                case "p4" : {
                    if (Server.p4 == 0) {
                        Server.p4List = new ArrayList<>();
                    }

                    Server.p4++;
                    Server.p4List.add(user0);

                    if (Server.p4 == 4) {
                        data = new HashMap<>();
                        Server.p4 = 0;
                        Server.gameNo++;
                        Server.gameMap.put(Server.gameNo, 4);
                        Server.dataMap.put(Server.gameNo, data);

                        random = new Random();
                        int [] ints = new int[200];
                        for (int i = 0; i < ints.length; i++) {
                            ints[i] = random.nextInt() % 36;
                        }

                        try (ObjectOutputStream gamemap = new ObjectOutputStream(new FileOutputStream("gamemap"))) {
                            gamemap.writeObject(Server.gameMap);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        int i = 0;
                        for (String user : Server.p4List) {
                            Server.clientMap.get(user).write(Server.gameNo);
                            Server.clientMap.get(user).write(Server.p4List);
                            Server.clientMap.get(user).write(i);
                            Server.clientMap.get(user).write(ints);
                            Server.clientMap.get(user).write("start");
                            i++;
                        }
                    }


                    String move;
                    while (true) {
                        move = (String) nc.read();
                        if (move.equalsIgnoreCase("exit") || move.equalsIgnoreCase("done")) {
                            break;
                        }
                        for (String user : Server.p4List) {
                            Server.clientMap.get(user).write(move);
                        }
                    }

                    if (move.equalsIgnoreCase("exit")) {
                        continuousCheck = true;
                        userinfo.add("4 Player: aborted");
                        exit(nc);
                    }
                    else {
                        String time = (String) nc.read();
                        int gameSerial = (int) nc.read();
                        userinfo.add("4 Player: time = " + time);
                        data = Server.dataMap.get(gameSerial);
                        data.put(user0, time);

                        String msg = (String) nc.read();
                        switch (msg) {
                            case "exit" : {
                                continuousCheck = true;
                                exit(nc);
                                break;
                            }
                            case "logout" : {
                                nc.write("logout");
                                logout(nc);
                                break;
                            }
                            case "again" : {
                                nc.write("again");
                                continuousCheck = true;
                                break;
                            }
                        }
                    }
                    break;
                }

                case "p5" : {
                    if (Server.p5 == 0) {
                        Server.p5List = new ArrayList<>();
                    }

                    Server.p5++;
                    Server.p5List.add(user0);

                    if (Server.p5 == 5) {
                        data = new HashMap<>();
                        Server.p5 = 0;
                        Server.gameNo++;
                        Server.gameMap.put(Server.gameNo, 5);
                        Server.dataMap.put(Server.gameNo, data);

                        random = new Random();
                        int [] ints = new int[250];
                        for (int i = 0; i < ints.length; i++) {
                            ints[i] = random.nextInt() % 36;
                        }

                        try (ObjectOutputStream gamemap = new ObjectOutputStream(new FileOutputStream("gamemap"))) {
                            gamemap.writeObject(Server.gameMap);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        int i = 0;
                        for (String user : Server.p5List) {
                            Server.clientMap.get(user).write(Server.gameNo);
                            Server.clientMap.get(user).write(Server.p5List);
                            Server.clientMap.get(user).write(i);
                            Server.clientMap.get(user).write(ints);
                            Server.clientMap.get(user).write("start");
                            i++;
                        }
                    }


                    String move;
                    while (true) {
                        move = (String) nc.read();
                        if (move.equalsIgnoreCase("exit") || move.equalsIgnoreCase("done")) {
                            break;
                        }
                        for (String user : Server.p5List) {
                            Server.clientMap.get(user).write(move);
                        }
                    }

                    if (move.equalsIgnoreCase("exit")) {
                        continuousCheck = true;
                        userinfo.add("5 Player: aborted");
                        exit(nc);
                    }
                    else {
                        String time = (String) nc.read();
                        int gameSerial = (int) nc.read();
                        userinfo.add("5 Player: time = " + time);
                        data = Server.dataMap.get(gameSerial);
                        data.put(user0, time);

                        String msg = (String) nc.read();
                        switch (msg) {
                            case "exit" : {
                                continuousCheck = true;
                                exit(nc);
                                break;
                            }
                            case "logout" : {
                                nc.write("logout");
                                logout(nc);
                                break;
                            }
                            case "again" : {
                                nc.write("again");
                                continuousCheck = true;
                                break;
                            }
                        }
                    }
                    break;
                }

                case "history" : {
                    System.out.println("History Pressed");
                    nc.write(userinfo);
                    String str = (String) nc.read();
                    if (str.equalsIgnoreCase("close")) {
                        continuousCheck = true;
                    }
                    else if (str.equalsIgnoreCase("exit")) {
                        continuousCheck = true;
                        exit(nc);
                    }
                    break;
                }
            }
            if (continuousCheck) {
                System.out.println("breaking while");
                break;
            }
        }
    }

    private void logout(NetworkUtil nc) {
        System.out.println("logging out a client");

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(user0 + ""))) {
            oos.writeObject(userinfo);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Server.loggedinList.remove(user0);
        Server.clientMap.remove(user0);

        user0 = null;

        start2();
    }

    private void exit(NetworkUtil nc) {
        check = false;
        System.out.println("exiting a client");
        //String username = (String) nc.read();
        //System.out.println(username);
        if (Server.loggedinList.contains(user0)) {
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(user0 + ""))) {
                oos.writeObject(userinfo);
            } catch (IOException e) {
                e.printStackTrace();
            }
            Server.loggedinList.remove(user0);
        }
        //System.out.println(nc);
        //nc.closeConnection();
    }

    private boolean serveLogin(NetworkUtil nc) {
        boolean isDone = false;

        System.out.println("login pressed");

        String userName = (String) nc.read();
        String userPass = (String) nc.read();

        String serverMsg;

        if (Server.userMap.get(userName) == null) {
            serverMsg = "invalid username";
        }
        else if (Server.loggedinList.contains(userName)) {
            serverMsg = "already logged in";
        }
        else if (Server.userMap.get(userName).equals(userPass)) {
            user0 = userName;
            serverMsg = "login successful";
            Server.loggedinList.add(user0);
            Server.clientMap.put(user0, nc);
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(user0 + ""))) {
                try {
                    userinfo = (ArrayList<String>) ois.readObject();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            isDone = true;
        }
        else {
            serverMsg = "password mismatch";
        }

        nc.write(serverMsg);
        return isDone;
    }

    private boolean serveRegister(NetworkUtil nc) {
        boolean isDone = false;

        System.out.println("Register button pressed");

        String clientName = (String) nc.read();
        String clientPass = (String) nc.read();
        String name = (String) nc.read();
        String email = (String) nc.read();

        String serverMsg;

        if (Server.userList.contains(clientName)) {
            System.out.println("username already in use.");
            serverMsg = "username already in use";
            //nc.write(serverMsg);
        }
        else {
            serverMsg = "username unique";
            Server.userList.add(clientName);
            Server.userMap.put(clientName, clientPass);
            userinfo = new ArrayList<>();
            userinfo.add("Name : " + name);
            userinfo.add("Email : " + email);

            try (ObjectOutputStream usermap = new ObjectOutputStream(new FileOutputStream("usermap"));
                 ObjectOutputStream userlist = new ObjectOutputStream(new FileOutputStream("userlist"));
                 ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(clientName + ""))
            ) {
                usermap.writeObject(Server.userMap);
                userlist.writeObject(Server.userList);
                oos.writeObject(userinfo);
            } catch (IOException e) {
                e.printStackTrace();
            }

            //isDone = true;
        }
        nc.write(serverMsg);

        return isDone;
    }
}
