package embeddedServer;

import util.NetworkUtil;

import java.util.List;

public class ReadThreadServer implements Runnable {
    public static List<NetworkUtil> clientList = Server.clientList;


    private Thread thr;
    private NetworkUtil nc;

    public ReadThreadServer(NetworkUtil nc) {
        this.nc = nc;
        this.thr = new Thread(this);
        thr.start();
    }

    public void run() {
        System.out.println("starting");
        try {
            while (true) {
                String s = (String) nc.read();
                if (s != null) {
                    System.out.println(s);
                    for (NetworkUtil net : clientList) {
                        net.write(s);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            nc.closeConnection();
        }
    }
}
