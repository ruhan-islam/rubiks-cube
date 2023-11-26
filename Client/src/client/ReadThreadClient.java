package client;

import design.Login;
import design.Play;
import design.Selection;
import javafx.application.Platform;
import util.NetworkUtil;

public class ReadThreadClient implements Runnable {
    public static boolean playing;
    private NetworkUtil nc;

    public ReadThreadClient(NetworkUtil nc) {
        playing = true;
        this.nc = nc;

        //this.thr = new Thread(this);
        //thr.start();
        new Thread(this).start();
    }

    public void run() {
        while (true) {
            String s = (String) nc.read();
            System.out.println(s);
            if (s.equalsIgnoreCase("again")) {
                Platform.runLater( () -> {
                    try {
                        Selection.showSelectionPage();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
                break;
            }
            else if (s.equalsIgnoreCase("logout")) {
                Platform.runLater( () -> {
                    Login.showLoginPage();
                });
                break;
            }


            switch (s.split(",")[1]) {
                case "<" : {
                    Platform.runLater( () ->
                        Play.cubes[Integer.parseInt(s.split(",")[0])].DistantArrowLeft(Integer.parseInt(s.split(",")[0])));
                    break;
                }
                case ">" : {
                    Platform.runLater( () ->
                        Play.cubes[Integer.parseInt(s.split(",")[0])].DistantArrowRight(Integer.parseInt(s.split(",")[0])));
                    break;
                }
                case "B" : {
                    Platform.runLater( () ->
                        Play.cubes[Integer.parseInt(s.split(",")[0])].anim_B());
                    break;
                }
                case "BI" : {
                    Platform.runLater( () ->
                        Play.cubes[Integer.parseInt(s.split(",")[0])].anim_BI());
                    break;
                }
                case "b" : {
                    Platform.runLater( () ->
                        Play.cubes[Integer.parseInt(s.split(",")[0])].anim_b());
                    break;
                }
                case "bi" : {
                    Platform.runLater( () ->
                        Play.cubes[Integer.parseInt(s.split(",")[0])].anim_bi());
                    break;
                }
                case "D" : {
                    Platform.runLater( () ->
                        Play.cubes[Integer.parseInt(s.split(",")[0])].anim_D());
                    break;
                }
                case "DI" : {
                    Platform.runLater( () ->
                        Play.cubes[Integer.parseInt(s.split(",")[0])].anim_DI());
                    break;
                }
                case "d" : {
                    Platform.runLater( () ->
                        Play.cubes[Integer.parseInt(s.split(",")[0])].anim_d());
                    break;
                }
                case "di" : {
                    Platform.runLater( () ->
                        Play.cubes[Integer.parseInt(s.split(",")[0])].anim_di());
                    break;
                }
                case "E" : {
                    Platform.runLater( () ->
                        Play.cubes[Integer.parseInt(s.split(",")[0])].anim_E());
                    break;
                }
                case "EI" : {
                    Platform.runLater( () ->
                        Play.cubes[Integer.parseInt(s.split(",")[0])].anim_EI());
                    break;
                }
                case "F" : {
                    Platform.runLater( () ->
                        Play.cubes[Integer.parseInt(s.split(",")[0])].anim_F());
                    break;
                }
                case "FI" : {
                    Platform.runLater( () ->
                        Play.cubes[Integer.parseInt(s.split(",")[0])].anim_FI());
                    break;
                }
                case "f" : {
                    Platform.runLater( () ->
                        Play.cubes[Integer.parseInt(s.split(",")[0])].anim_f());
                    break;
                }case "fi" : {
                    Platform.runLater( () ->
                        Play.cubes[Integer.parseInt(s.split(",")[0])].anim_fi());
                    break;
                }case "L" : {
                    Platform.runLater( () ->
                        Play.cubes[Integer.parseInt(s.split(",")[0])].anim_L());
                    break;
                }
                case "LI" : {
                    Platform.runLater( () ->
                        Play.cubes[Integer.parseInt(s.split(",")[0])].anim_LI());
                    break;
                }
                case "l" : {
                    Platform.runLater( () ->
                        Play.cubes[Integer.parseInt(s.split(",")[0])].anim_l());
                    break;
                }case "li" : {
                    Platform.runLater( () ->
                        Play.cubes[Integer.parseInt(s.split(",")[0])].anim_li());
                    break;
                }case "M" : {
                    Platform.runLater( () ->
                        Play.cubes[Integer.parseInt(s.split(",")[0])].anim_M());
                    break;
                }case "MI" : {
                    Platform.runLater( () ->
                        Play.cubes[Integer.parseInt(s.split(",")[0])].anim_MI());
                    break;
                }case "R" : {
                    Platform.runLater( () ->
                        Play.cubes[Integer.parseInt(s.split(",")[0])].anim_R());
                    break;
                }
                case "RI" : {
                    Platform.runLater( () ->
                        Play.cubes[Integer.parseInt(s.split(",")[0])].anim_RI());
                    break;
                }
                case "r" : {
                    Platform.runLater( () ->
                        Play.cubes[Integer.parseInt(s.split(",")[0])].anim_r());
                    break;
                }
                case "ri" : {
                    Platform.runLater( () ->
                        Play.cubes[Integer.parseInt(s.split(",")[0])].anim_ri());
                    break;
                }case "S" : {
                    Platform.runLater( () ->
                        Play.cubes[Integer.parseInt(s.split(",")[0])].anim_S());
                    break;
                }case "SI" : {
                    Platform.runLater( () ->
                        Play.cubes[Integer.parseInt(s.split(",")[0])].anim_SI());
                    break;
                }case "U" : {
                    Platform.runLater( () ->
                        Play.cubes[Integer.parseInt(s.split(",")[0])].anim_U());
                    break;
                }case "UI" : {
                    Platform.runLater( () ->
                        Play.cubes[Integer.parseInt(s.split(",")[0])].anim_UI());
                    break;
                }case "u" : {
                    Platform.runLater( () ->
                        Play.cubes[Integer.parseInt(s.split(",")[0])].anim_u());
                    break;
                }case "ui" : {
                    Platform.runLater( () ->
                        Play.cubes[Integer.parseInt(s.split(",")[0])].anim_ui());
                    break;
                }case "x" : {
                    Platform.runLater( () ->
                        Play.cubes[Integer.parseInt(s.split(",")[0])].anim_x());
                    break;
                }case "xi" : {
                    Platform.runLater( () ->
                        Play.cubes[Integer.parseInt(s.split(",")[0])].anim_xi());
                    break;
                }case "y" : {
                    Platform.runLater( () ->
                        Play.cubes[Integer.parseInt(s.split(",")[0])].anim_y());
                    break;
                }case "yi" : {
                    Platform.runLater( () ->
                        Play.cubes[Integer.parseInt(s.split(",")[0])].anim_yi());
                    break;
                }case "z" : {
                    Platform.runLater( () ->
                        Play.cubes[Integer.parseInt(s.split(",")[0])].anim_z());
                    break;
                }
                case "zi" : {
                    Platform.runLater( () ->
                        Play.cubes[Integer.parseInt(s.split(",")[0])].anim_zi());
                    break;
                }
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (s == null) break;
            if (s.equalsIgnoreCase("close")) break;
            if (!playing) break;
            System.out.println(s);
            if (s.split(",")[1] == null) break;
        }
        System.out.println("closing thread");
    }
}
