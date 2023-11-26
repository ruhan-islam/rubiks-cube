package client;

import util.NetworkUtil;

public class ReadThread implements Runnable {
    //private Thread thr;

    private NetworkUtil nc;

    public ReadThread(NetworkUtil nc) {
        this.nc = nc;

        //this.thr = new Thread(this);
        //thr.start();
        new Thread(this).start();
    }

    public void run() {
        try {
            while (true) {
                String s = (String) nc.read();
                if (s != null) {
                    System.out.println(s);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            nc.closeConnection();
        }
    }
}



