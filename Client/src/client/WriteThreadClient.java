package client;

import util.NetworkUtil;
import java.util.Scanner;

public class WriteThreadClient implements Runnable {
    //private Thread thr;

    private NetworkUtil nc;
    private String name;

    WriteThreadClient(NetworkUtil nc, String name) {
        this.nc = nc;
        this.name = name;

        //this.thr = new Thread(this);
        //thr.start();
        new Thread(this).start();
    }

    public void run() {
        try {
            Scanner input = new Scanner(System.in);
            while (true) {
                String s = input.nextLine();
                nc.write(name + ":" + s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            nc.closeConnection();
        }
    }
}
