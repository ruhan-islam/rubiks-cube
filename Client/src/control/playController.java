/*
 * Sample Skeleton for 'play.fxml' Controller Class
 */

package control;

import java.net.URL;
import java.util.ResourceBundle;

import client.Client;
import client.ReadThreadClient;
import design.Login;
import design.Play;
import design.Selection;
import environment.Camera;
import environment.DistantCamera;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.SceneAntialiasing;
import javafx.scene.SubScene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.util.Duration;
import sample.Main;
import util.NetworkUtil;

import static design.Play.cubes;
import static sample.Main.screenHeight;
import static sample.Main.screenWidth;

public class playController {
    public static boolean solved;

    private NetworkUtil nc = Client.client.nc;

    public static DistantCamera [] DCamera;

    public static int cubeID = 0;
    public static Timeline timeline;
    public static long diff;
    public static Button arrowL;
    public static Button arrowR;
    public static Button logoutBtn;
    public static Button againBtn;
    public static Label timeLabel;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="screen"
    private BorderPane screen; // Value injected by FXMLLoader

    @FXML // fx:id="centerScene"
    private SubScene centerScene; // Value injected by FXMLLoader

    @FXML // fx:id="u"
    private Button u; // Value injected by FXMLLoader

    @FXML // fx:id="U"
    private Button U; // Value injected by FXMLLoader

    @FXML // fx:id="FI"
    private Button FI; // Value injected by FXMLLoader

    @FXML // fx:id="fi"
    private Button fi; // Value injected by FXMLLoader

    @FXML // fx:id="LI"
    private Button LI; // Value injected by FXMLLoader

    @FXML // fx:id="li"
    private Button li; // Value injected by FXMLLoader

    @FXML // fx:id="EI"
    private Button EI; // Value injected by FXMLLoader

    @FXML // fx:id="SI"
    private Button SI; // Value injected by FXMLLoader

    @FXML // fx:id="MI"
    private Button MI; // Value injected by FXMLLoader

    @FXML // fx:id="xi"
    private Button xi; // Value injected by FXMLLoader

    @FXML // fx:id="yi"
    private Button yi; // Value injected by FXMLLoader

    @FXML // fx:id="zi"
    private Button zi; // Value injected by FXMLLoader

    @FXML // fx:id="L"
    private Button L; // Value injected by FXMLLoader

    @FXML // fx:id="l"
    private Button l; // Value injected by FXMLLoader

    @FXML // fx:id="B"
    private Button B; // Value injected by FXMLLoader

    @FXML // fx:id="b"
    private Button b; // Value injected by FXMLLoader

    @FXML // fx:id="DI"
    private Button DI; // Value injected by FXMLLoader

    @FXML // fx:id="di"
    private Button di; // Value injected by FXMLLoader

    @FXML // fx:id="displayBox"
    private GridPane displayBox; // Value injected by FXMLLoader

    @FXML // fx:id="R"
    private Button R; // Value injected by FXMLLoader

    @FXML // fx:id="r"
    private Button r; // Value injected by FXMLLoader

    @FXML // fx:id="F"
    private Button F; // Value injected by FXMLLoader

    @FXML // fx:id="f"
    private Button f; // Value injected by FXMLLoader

    @FXML // fx:id="UI"
    private Button UI; // Value injected by FXMLLoader

    @FXML // fx:id="ui"
    private Button ui; // Value injected by FXMLLoader

    @FXML // fx:id="E"
    private Button E; // Value injected by FXMLLoader

    @FXML // fx:id="S"
    private Button S; // Value injected by FXMLLoader

    @FXML // fx:id="M"
    private Button M; // Value injected by FXMLLoader

    @FXML // fx:id="x"
    private Button x; // Value injected by FXMLLoader

    @FXML // fx:id="y"
    private Button y; // Value injected by FXMLLoader

    @FXML // fx:id="z"
    private Button z; // Value injected by FXMLLoader

    @FXML // fx:id="RI"
    private Button RI; // Value injected by FXMLLoader

    @FXML // fx:id="ri"
    private Button ri; // Value injected by FXMLLoader

    @FXML // fx:id="BI"
    private Button BI; // Value injected by FXMLLoader

    @FXML // fx:id="bi"
    private Button bi; // Value injected by FXMLLoader

    @FXML // fx:id="D"
    private Button D; // Value injected by FXMLLoader

    @FXML // fx:id="d"
    private Button d; // Value injected by FXMLLoader

    @FXML // fx:id="time"
    private Label time; // Value injected by FXMLLoader

    @FXML // fx:id="leftArrow"
    private Button leftArrow; // Value injected by FXMLLoader

    @FXML // fx:id="rightArrow"
    private Button rightArrow; // Value injected by FXMLLoader

    @FXML // fx:id="downArrow"
    private Button downArrow; // Value injected by FXMLLoader

    @FXML // fx:id="upArrow"
    private Button upArrow; // Value injected by FXMLLoader

    @FXML // fx:id="username"
    private Label username; // Value injected by FXMLLoader

    @FXML // fx:id="logoutButton"
    private Button logoutButton; // Value injected by FXMLLoader

    @FXML // fx:id="againButton"
    private Button againButton; // Value injected by FXMLLoader

    @FXML // fx:id="finishedLabel"
    private Label finishedLabel; // Value injected by FXMLLoader


    @FXML
    void logoutButtonPressed(ActionEvent event) {
        ReadThreadClient.playing = false;
        nc.write("logout");
        if (Play.total == 1) Login.showLoginPage();
    }

    @FXML
    void againButtonPressed(ActionEvent event) {
        ReadThreadClient.playing = false;
        nc.write("again");
        if (Play.total == 1) {
            try {
                Selection.showSelectionPage();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void upArrowPressed(ActionEvent event) {

    }

    @FXML
    void downArrowPressed(ActionEvent event) {
        
    }

    @FXML
    void leftArrowPressed(ActionEvent event) {
        if (!cubeController.animationRunning) {
            leftArrow.setDisable(true);
            rightArrow.setDisable(false);
            cubes[cubeID].arrowLeft(Play.camera);
            if (Play.total > 1) {
                Client.client.nc.write(Client.client.IDserial + ",<");
                //return;
            }
        }
    }

    @FXML
    void rightArrowPressed(ActionEvent event) {
        if (!cubeController.animationRunning) {
            rightArrow.setDisable(true);
            leftArrow.setDisable(false);
            cubes[cubeID].arrowRight(Play.camera);
            if (Play.total > 1) {
                Client.client.nc.write(Client.client.IDserial + ",>");
                //return;
            }
        }
    }

    @FXML
    void BI_pressed(ActionEvent event) {
        if (Play.total > 1) {
            Client.client.nc.write(Client.client.IDserial + ",BI");
            return;
        }
        cubes[cubeID].anim_BI();
    }

    @FXML
    void B_pressed(ActionEvent event) {
        if (Play.total > 1) {
            Client.client.nc.write(Client.client.IDserial + ",B");
            return;
        }
        cubes[cubeID].anim_B();
    }

    @FXML
    void DI_pressed(ActionEvent event) {
        if (Play.total > 1) {
            Client.client.nc.write(Client.client.IDserial + ",DI");
            return;
        }
        cubes[cubeID].anim_DI();
    }

    @FXML
    void D_pressed(ActionEvent event) {
        if (Play.total > 1) {
            Client.client.nc.write(Client.client.IDserial + ",D");
            return;
        }
        cubes[cubeID].anim_D();
    }

    @FXML
    void EI_pressed(ActionEvent event) {
        if (Play.total > 1) {
            Client.client.nc.write(Client.client.IDserial + ",EI");
            return;
        }
        cubes[cubeID].anim_EI();
    }

    @FXML
    void E_pressed(ActionEvent event) {
        if (Play.total > 1) {
            Client.client.nc.write(Client.client.IDserial + ",E");
            return;
        }
        cubes[cubeID].anim_E();
    }

    @FXML
    void FI_pressed(ActionEvent event) {
        if (Play.total > 1) {
            Client.client.nc.write(Client.client.IDserial + ",FI");
            return;
        }
        cubes[cubeID].anim_FI();
    }

    @FXML
    void F_pressed(ActionEvent event) {
        if (Play.total > 1) {
            Client.client.nc.write(Client.client.IDserial + ",F");
            return;
        }
        cubes[cubeID].anim_F();
    }

    @FXML
    void LI_pressed(ActionEvent event) {
        if (Play.total > 1) {
            Client.client.nc.write(Client.client.IDserial + ",LI");
            return;
        }
        cubes[cubeID].anim_LI();
    }

    @FXML
    void L_pressed(ActionEvent event) {
        if (Play.total > 1) {
            Client.client.nc.write(Client.client.IDserial + ",L");
            return;
        }
        cubes[cubeID].anim_L();
    }

    @FXML
    void MI_pressed(ActionEvent event) {
        if (Play.total > 1) {
            Client.client.nc.write(Client.client.IDserial + ",MI");
            return;
        }
        cubes[cubeID].anim_MI();
    }

    @FXML
    void M_pressed(ActionEvent event) {
        if (Play.total > 1) {
            Client.client.nc.write(Client.client.IDserial + ",M");
            return;
        }
        cubes[cubeID].anim_M();
    }

    @FXML
    void RI_pressed(ActionEvent event) {
        if (Play.total > 1) {
            Client.client.nc.write(Client.client.IDserial + ",RI");
            return;
        }
        cubes[cubeID].anim_RI();
    }

    @FXML
    void R_pressed(ActionEvent event) {
        if (Play.total > 1) {
            Client.client.nc.write(Client.client.IDserial + ",R");
            return;
        }
        cubes[cubeID].anim_R();
    }

    @FXML
    void SI_pressed(ActionEvent event) {
        if (Play.total > 1) {
            Client.client.nc.write(Client.client.IDserial + ",SI");
            return;
        }
        cubes[cubeID].anim_SI();
    }

    @FXML
    void S_pressed(ActionEvent event) {
        if (Play.total > 1) {
            Client.client.nc.write(Client.client.IDserial + ",S");
            return;
        }
        cubes[cubeID].anim_S();
    }

    @FXML
    void UI_pressed(ActionEvent event) {
        if (Play.total > 1) {
            Client.client.nc.write(Client.client.IDserial + ",UI");
            return;
        }
        cubes[cubeID].anim_UI();
    }

    @FXML
    void U_pressed(ActionEvent event) {
        if (Play.total > 1) {
            Client.client.nc.write(Client.client.IDserial + ",U");
            return;
        }
        cubes[cubeID].anim_U();
    }

    @FXML
    void b_pressed(ActionEvent event) {
        if (Play.total > 1) {
            Client.client.nc.write(Client.client.IDserial + ",b");
            return;
        }
        cubes[cubeID].anim_b();
    }

    @FXML
    void bi_pressed(ActionEvent event) {
        if (Play.total > 1) {
            Client.client.nc.write(Client.client.IDserial + ",bi");
            return;
        }
        cubes[cubeID].anim_bi();
    }

    @FXML
    void d_pressed(ActionEvent event) {
        if (Play.total > 1) {
            Client.client.nc.write(Client.client.IDserial + ",d");
            return;
        }
        cubes[cubeID].anim_d();
    }

    @FXML
    void di_pressed(ActionEvent event) {
        if (Play.total > 1) {
            Client.client.nc.write(Client.client.IDserial + ",di");
            return;
        }
        cubes[cubeID].anim_di();
    }

    @FXML
    void f_pressed(ActionEvent event) {
        if (Play.total > 1) {
            Client.client.nc.write(Client.client.IDserial + ",f");
            return;
        }
        cubes[cubeID].anim_f();
    }

    @FXML
    void fi_pressed(ActionEvent event) {
        if (Play.total > 1) {
            Client.client.nc.write(Client.client.IDserial + ",fi");
            return;
        }
        cubes[cubeID].anim_fi();
    }

    @FXML
    void l_pressed(ActionEvent event) {
        if (Play.total > 1) {
            Client.client.nc.write(Client.client.IDserial + ",l");
            return;
        }
        cubes[cubeID].anim_l();
    }

    @FXML
    void li_pressed(ActionEvent event) {
        if (Play.total > 1) {
            Client.client.nc.write(Client.client.IDserial + ",li");
            return;
        }
        cubes[cubeID].anim_li();
    }

    @FXML
    void r_pressed(ActionEvent event) {
        if (Play.total > 1) {
            Client.client.nc.write(Client.client.IDserial + ",r");
            return;
        }
        cubes[cubeID].anim_r();
    }

    @FXML
    void ri_pressed(ActionEvent event) {
        if (Play.total > 1) {
            Client.client.nc.write(Client.client.IDserial + ",ri");
            return;
        }
        cubes[cubeID].anim_ri();
    }

    @FXML
    void u_pressed(ActionEvent event) {
        if (Play.total > 1) {
            Client.client.nc.write(Client.client.IDserial + ",u");
            return;
        }
        cubes[cubeID].anim_u();
    }

    @FXML
    void ui_pressed(ActionEvent event) {
        if (Play.total > 1) {
            Client.client.nc.write(Client.client.IDserial + ",ui");
            return;
        }
        cubes[cubeID].anim_ui();
    }

    @FXML
    void x_pressed(ActionEvent event) {
        if (Play.total > 1) {
            Client.client.nc.write(Client.client.IDserial + ",x");
            return;
        }
        cubes[cubeID].anim_x();
    }

    @FXML
    void xi_pressed(ActionEvent event) {
        if (Play.total > 1) {
            Client.client.nc.write(Client.client.IDserial + ",xi");
            return;
        }
        cubes[cubeID].anim_xi();
    }

    @FXML
    void y_pressed(ActionEvent event) {
        if (Play.total > 1) {
            Client.client.nc.write(Client.client.IDserial + ",y");
            return;
        }
        cubes[cubeID].anim_y();
    }

    @FXML
    void yi_pressed(ActionEvent event) {
        if (Play.total > 1) {
            Client.client.nc.write(Client.client.IDserial + ",yi");
            return;
        }
        cubes[cubeID].anim_yi();
    }

    @FXML
    void z_pressed(ActionEvent event) {
        if (Play.total > 1) {
            Client.client.nc.write(Client.client.IDserial + ",z");
            return;
        }
        cubes[cubeID].anim_z();
    }

    @FXML
    void zi_pressed(ActionEvent event) {
        if (Play.total > 1) {
            Client.client.nc.write(Client.client.IDserial + ",zi");
            return;
        }
        cubes[cubeID].anim_zi();
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert screen != null : "fx:id=\"screen\" was not injected: check your FXML file 'play.fxml'.";
        assert centerScene != null : "fx:id=\"centerScene\" was not injected: check your FXML file 'play.fxml'.";
        assert u != null : "fx:id=\"u\" was not injected: check your FXML file 'play.fxml'.";
        assert U != null : "fx:id=\"U\" was not injected: check your FXML file 'play.fxml'.";
        assert FI != null : "fx:id=\"FI\" was not injected: check your FXML file 'play.fxml'.";
        assert fi != null : "fx:id=\"fi\" was not injected: check your FXML file 'play.fxml'.";
        assert LI != null : "fx:id=\"LI\" was not injected: check your FXML file 'play.fxml'.";
        assert li != null : "fx:id=\"li\" was not injected: check your FXML file 'play.fxml'.";
        assert EI != null : "fx:id=\"EI\" was not injected: check your FXML file 'play.fxml'.";
        assert SI != null : "fx:id=\"SI\" was not injected: check your FXML file 'play.fxml'.";
        assert MI != null : "fx:id=\"MI\" was not injected: check your FXML file 'play.fxml'.";
        assert xi != null : "fx:id=\"xi\" was not injected: check your FXML file 'play.fxml'.";
        assert yi != null : "fx:id=\"yi\" was not injected: check your FXML file 'play.fxml'.";
        assert zi != null : "fx:id=\"zi\" was not injected: check your FXML file 'play.fxml'.";
        assert L != null : "fx:id=\"L\" was not injected: check your FXML file 'play.fxml'.";
        assert l != null : "fx:id=\"l\" was not injected: check your FXML file 'play.fxml'.";
        assert B != null : "fx:id=\"B\" was not injected: check your FXML file 'play.fxml'.";
        assert b != null : "fx:id=\"b\" was not injected: check your FXML file 'play.fxml'.";
        assert DI != null : "fx:id=\"DI\" was not injected: check your FXML file 'play.fxml'.";
        assert di != null : "fx:id=\"di\" was not injected: check your FXML file 'play.fxml'.";
        assert displayBox != null : "fx:id=\"displayBox\" was not injected: check your FXML file 'play.fxml'.";
        assert R != null : "fx:id=\"R\" was not injected: check your FXML file 'play.fxml'.";
        assert r != null : "fx:id=\"r\" was not injected: check your FXML file 'play.fxml'.";
        assert F != null : "fx:id=\"F\" was not injected: check your FXML file 'play.fxml'.";
        assert f != null : "fx:id=\"f\" was not injected: check your FXML file 'play.fxml'.";
        assert UI != null : "fx:id=\"UI\" was not injected: check your FXML file 'play.fxml'.";
        assert ui != null : "fx:id=\"ui\" was not injected: check your FXML file 'play.fxml'.";
        assert E != null : "fx:id=\"E\" was not injected: check your FXML file 'play.fxml'.";
        assert S != null : "fx:id=\"S\" was not injected: check your FXML file 'play.fxml'.";
        assert M != null : "fx:id=\"M\" was not injected: check your FXML file 'play.fxml'.";
        assert x != null : "fx:id=\"x\" was not injected: check your FXML file 'play.fxml'.";
        assert y != null : "fx:id=\"y\" was not injected: check your FXML file 'play.fxml'.";
        assert z != null : "fx:id=\"z\" was not injected: check your FXML file 'play.fxml'.";
        assert RI != null : "fx:id=\"RI\" was not injected: check your FXML file 'play.fxml'.";
        assert ri != null : "fx:id=\"ri\" was not injected: check your FXML file 'play.fxml'.";
        assert BI != null : "fx:id=\"BI\" was not injected: check your FXML file 'play.fxml'.";
        assert bi != null : "fx:id=\"bi\" was not injected: check your FXML file 'play.fxml'.";
        assert D != null : "fx:id=\"D\" was not injected: check your FXML file 'play.fxml'.";
        assert d != null : "fx:id=\"d\" was not injected: check your FXML file 'play.fxml'.";
        assert time != null : "fx:id=\"time\" was not injected: check your FXML file 'play.fxml'.";
        assert leftArrow != null : "fx:id=\"leftArrow\" was not injected: check your FXML file 'play.fxml'.";
        assert rightArrow != null : "fx:id=\"rightArrow\" was not injected: check your FXML file 'play.fxml'.";
        assert downArrow != null : "fx:id=\"downArrow\" was not injected: check your FXML file 'play.fxml'.";
        assert upArrow != null : "fx:id=\"upArrow\" was not injected: check your FXML file 'play.fxml'.";

        assert username != null : "fx:id=\"username\" was not injected: check your FXML file 'play.fxml'.";
        assert logoutButton != null : "fx:id=\"logoutButton\" was not injected: check your FXML file 'play.fxml'.";
        assert againButton != null : "fx:id=\"againButton\" was not injected: check your FXML file 'play.fxml'.";
        assert finishedLabel != null : "fx:id=\"finishedLabel\" was not injected: check your FXML file 'play.fxml'.";

        username.setText(Main.username);

        arrowL = leftArrow;
        arrowR = rightArrow;
        logoutBtn = logoutButton;
        againBtn = againButton;
        timeLabel = time;

        setup();
    }

    private void setup() {
        Group root0;
        if (Play.total == 1) {
            root0 = Play.root0;
        } else {
            root0 = Play.roots[Client.client.IDserial];
        }

        SubScene centerScene = new SubScene(root0, screenWidth*0.5, screenHeight*0.8, true, SceneAntialiasing.BALANCED);
        centerScene.setFill(Color.WHITE);
        centerScene.setCamera(Play.camera.getCamera());
        screen.setCenter(centerScene);

        //screen.setRight(centerScene);
        Group [] subroot = new Group[Play.total];
        SubScene [] subScene = new SubScene[Play.total];
        DCamera = new DistantCamera[Play.total];

        //System.out.println(Play.total);

        for (int i = 1, j = 0; i < Play.total; i++, j++) {
            if (j == Client.client.IDserial) j++;
            if(j >= 5) j %= 5;
            subroot[i] = Play.roots[j];
            subScene[i] = new SubScene(subroot[i], screenWidth * 0.1, screenWidth * 0.1, true, SceneAntialiasing.BALANCED);
            subScene[i].setFill(Color.AQUAMARINE);
            DCamera[j] = new DistantCamera(subroot[i]);
            subScene[i].setCamera(DCamera[j].getDistantCamera());
            //displayBox.add(subScene[i], 1+2*(i/3),(i%2==0)?3:1);
        }

        if (Play.total > 1) displayBox.add(subScene[1], 1,1);
        if (Play.total > 2) displayBox.add(subScene[2], 1,3);
        if (Play.total > 3) displayBox.add(subScene[3], 3,1);
        if (Play.total > 4) displayBox.add(subScene[4], 3,3);


        final long start = System.currentTimeMillis();
        timeline = new Timeline(
                new KeyFrame(
                        Duration.millis(1),
                        event -> {
                            diff = System.currentTimeMillis() - start;
                            String milsec = (int)(diff % 1000) + "";
                            if (milsec.length() == 2) milsec = "0" + milsec;
                            else if (milsec.length() == 1) milsec = "00" + milsec;
                            String sec = (int)((diff / 1000) % 60) + "";
                            if (sec.length() == 1) sec = "0" + sec;
                            String min = (int)((diff / 60000) % 60) + "";
                            time.setText(min + ":" + sec + ":" + milsec);
                        }
                )
        );
        timeline.setCycleCount( Animation.INDEFINITE );
        timeline.play();
    }
}
