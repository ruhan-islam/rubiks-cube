package control;

import client.Client;
import design.Play;
import environment.Camera;
import javafx.animation.RotateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.paint.Material;
import javafx.scene.paint.PhongMaterial;
import javafx.util.Duration;
import sample.Cube;
import util.NetworkUtil;

import static javafx.scene.transform.Rotate.X_AXIS;
import static javafx.scene.transform.Rotate.Y_AXIS;
import static javafx.scene.transform.Rotate.Z_AXIS;

public class cubeController extends Cube{
    private NetworkUtil nc = Client.client.nc;

    public static boolean animationRunning;
    private Material[] temp = new PhongMaterial[3];
    private int cubeSerial;
    private String cubeName;

    public cubeController(int cubeSerial) {
        this.cubeSerial = cubeSerial;
    }

    public cubeController(String cubeName) {
        this.cubeName = cubeName;
    }

    //cubeXform.setRy(+50);
    //cubeXform.setRy(-40);


    @Override
    public void scramble(int [] ints, int start) {
        int value;
        for (int i = 0; i < 49; i++) {
            value = ints[start+i];

            switch (value) {
                case 0: {
                    move_xi();
                    break;
                }
                case 1: {
                    move_BI();
                    break;
                }
                case 2: {
                    move_B();
                    break;
                }
                case 3: {
                    move_x();
                    break;
                }
                case 4: {
                    move_b();
                    break;
                }
                case 5: {
                    move_S();
                    break;
                }
                case 6: {
                    move_F();
                    break;
                }
                case 7: {
                    move_M();
                    break;
                }
                case 8: {
                    move_L();
                    break;
                }
                case 9: {
                    move_R();
                    break;
                }
                case 10: {
                    move_E();
                    break;
                }
                case 11: {
                    move_bi();
                    break;
                }
                case 12: {
                    move_D();
                    break;
                }
                case 13: {
                    move_d();
                    break;
                }
                case 14: {
                    move_DI();
                    break;
                }
                case 15: {
                    move_di();
                    break;
                }case 16: {
                    move_EI();
                    break;
                }
                case 17: {
                    move_f();
                    break;
                }
                case 18: {
                    move_FI();
                    break;
                }
                case 19: {
                    move_fi();
                    break;
                }
                case 20: {
                    move_l();
                    break;
                }
                case 21: {
                    move_LI();
                    break;
                }
                case 22: {
                    move_li();
                    break;
                }
                case 23: {
                    move_MI();
                    break;
                }
                case 24: {
                    move_r();
                    break;
                }
                case 25: {
                    move_RI();
                    break;
                }
                case 26: {
                    move_ri();
                    break;
                }
                case 27: {
                    move_SI();
                    break;
                }
                case 28: {
                    move_U();
                    break;
                }
                case 29: {
                    move_u();
                    break;
                }
                case 30: {
                    move_UI();
                    break;
                }
                case 31: {
                    move_ui();
                    break;
                }
                case 32: {
                    move_y();
                    break;
                }
                case 33: {
                    move_yi();
                    break;
                }
                case 34: {
                    move_z();
                    break;
                }
                case 35: {
                    move_zi();
                    break;
                }
            }
        }
    }

    @Override
    public void checkIsDone() {
        if ((Play.total > 1) && (cubeSerial != Client.client.IDserial)) return;

        boolean check = true;

        //System.out.println(rightSticker[0].getMaterial());
        for(int i = 1; i < 9; i++) {
            if (!frontSticker[i].getMaterial().equals(frontSticker[0].getMaterial())) {
                check = false;
            }
        }
        for(int i = 1; i < 9; i++) {
            if (!rightSticker[i].getMaterial().equals(rightSticker[0].getMaterial())) {
                check = false;
            }
        }
        for(int i = 1; i < 9; i++) {
            if (!upSticker[i].getMaterial().equals(upSticker[0].getMaterial())) {
                check = false;
            }
        }
        for(int i = 1; i < 9; i++) {
            if (!leftSticker[i].getMaterial().equals(leftSticker[0].getMaterial())) {
                check = false;
            }
        }
        for(int i = 1; i < 9; i++) {
            if (!downSticker[i].getMaterial().equals(downSticker[0].getMaterial())) {
                check = false;
            }
        }
        for(int i = 1; i < 9; i++) {
            if (!backSticker[i].getMaterial().equals(backSticker[0].getMaterial())) {
                check = false;
            }
        }

        if(check) {
            playController.solved = true;
            playController.timeline.stop();
            long diff = playController.diff;
            System.out.println(diff/1000 + " " + diff%1000);
            String time = playController.timeLabel.getText();
            nc.write("done");
            nc.write(time);
            nc.write(Play.gameNo);
            playController.arrowL.setDisable(true);
            playController.arrowR.setDisable(true);
            playController.againBtn.setDisable(false);
            playController.logoutBtn.setDisable(false);
        }
    }

    @Override
    public void arrowRight(Camera camera) {
        if (animationRunning) return;
        animationRunning = true;
//        Platform.runLater( () -> {
//            playController.arrowR.setDisable(true);
//            playController.arrowL.setDisable(false);
//        });
        RotateTransition rotateTransition = new RotateTransition();
        rotateTransition.setDuration(Duration.millis(1000));
        Group tempL = new Group();
        for(int i = 0; i < 27; i++) tempL.getChildren().addAll(Xpart[i]);
        rotateTransition.setNode(tempL);
        rotateTransition.setAxis(Y_AXIS);
        rotateTransition.setByAngle(+50);
        //rotateTransition.setAutoReverse(false);
        rotateTransition.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            synchronized public void handle(ActionEvent event) {
                //move_yi();
                cubeXform.getChildren().removeAll(Xpart);
                cubeXform.getChildren().removeAll(tempL);
                cubeXform.getChildren().addAll(Xpart);
                camera.rotateY(-50);
                animationRunning = false;
            }
        });
        rotateTransition.play();
        cubeXform.getChildren().addAll(tempL);
    }

    @Override
    public void arrowLeft(Camera camera) {
        if (animationRunning) return;
        animationRunning = true;
//        Platform.runLater( () -> {
//            playController.arrowR.setDisable(false);
//            playController.arrowL.setDisable(true);
//        });
        RotateTransition rotateTransition = new RotateTransition();
        rotateTransition.setDuration(Duration.millis(1000));
        Group tempL = new Group();
        for(int i = 0; i < 27; i++) tempL.getChildren().addAll(Xpart[i]);
        rotateTransition.setNode(tempL);
        rotateTransition.setAxis(Y_AXIS);
        rotateTransition.setByAngle(-50);
        //rotateTransition.setAutoReverse(false);
        rotateTransition.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            synchronized public void handle(ActionEvent event) {
                //move_yi();
                cubeXform.getChildren().removeAll(Xpart);
                cubeXform.getChildren().removeAll(tempL);
                cubeXform.getChildren().addAll(Xpart);
                camera.rotateY(+50);
                animationRunning = false;
            }
        });
        rotateTransition.play();
        cubeXform.getChildren().addAll(tempL);
    }

    @Override
    public void DistantArrowRight(int participant) {
        if (participant == playController.cubeID) {
            System.out.println("recursion!!!");
            return;
        }
        if (animationRunning) return;
        animationRunning = true;
//        Platform.runLater( () -> {
//            playController.arrowR.setDisable(true);
//            playController.arrowL.setDisable(false);
//        });
        RotateTransition rotateTransition = new RotateTransition();
        rotateTransition.setDuration(Duration.millis(1000));
        Group tempL = new Group();
        for(int i = 0; i < 27; i++) tempL.getChildren().addAll(Xpart[i]);
        rotateTransition.setNode(tempL);
        rotateTransition.setAxis(Y_AXIS);
        rotateTransition.setByAngle(+50);
        //rotateTransition.setAutoReverse(false);
        rotateTransition.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            synchronized public void handle(ActionEvent event) {
                //move_yi();
                cubeXform.getChildren().removeAll(Xpart);
                cubeXform.getChildren().removeAll(tempL);
                cubeXform.getChildren().addAll(Xpart);
                playController.DCamera[participant].rotateY(-50);
                animationRunning = false;
            }
        });
        rotateTransition.play();
        cubeXform.getChildren().addAll(tempL);
    }

    @Override
    public void DistantArrowLeft(int participant) {
        if (participant == playController.cubeID) {
            System.out.println("recursion!!!");
            return;
        }
        if (animationRunning) return;
        animationRunning = true;
//        Platform.runLater( () -> {
//            playController.arrowR.setDisable(false);
//            playController.arrowL.setDisable(true);
//        });
        RotateTransition rotateTransition = new RotateTransition();
        rotateTransition.setDuration(Duration.millis(1000));
        Group tempL = new Group();
        for(int i = 0; i < 27; i++) tempL.getChildren().addAll(Xpart[i]);
        rotateTransition.setNode(tempL);
        rotateTransition.setAxis(Y_AXIS);
        rotateTransition.setByAngle(-50);
        //rotateTransition.setAutoReverse(false);
        rotateTransition.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            synchronized public void handle(ActionEvent event) {
                //move_yi();
                cubeXform.getChildren().removeAll(Xpart);
                cubeXform.getChildren().removeAll(tempL);
                cubeXform.getChildren().addAll(Xpart);
                playController.DCamera[participant].rotateY(+50);
                animationRunning = false;
            }
        });
        rotateTransition.play();
        cubeXform.getChildren().addAll(tempL);
    }

    @Override
    public void arrowUp() {

    }

    @Override
    public void arrowDown() {

    }
    
    @Override
    public void anim_R() {
        if (animationRunning) return;
        animationRunning = true;
        RotateTransition rotateTransition = new RotateTransition();
        rotateTransition.setDuration(Duration.millis(1000));
        Group tempL = new Group();
        for(int i = 2; i < 27; i+=3) tempL.getChildren().addAll(Xpart[i]);
        rotateTransition.setNode(tempL);
        rotateTransition.setAxis(X_AXIS);
        rotateTransition.setByAngle(90);
        //rotateTransition.setAutoReverse(false);
        rotateTransition.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            synchronized public void handle(ActionEvent event) {
                move_R();
                cubeXform.getChildren().removeAll(Xpart);
                cubeXform.getChildren().removeAll(tempL);
                cubeXform.getChildren().addAll(Xpart);
                animationRunning = false;
                checkIsDone();
            }
        });
        rotateTransition.play();
        cubeXform.getChildren().addAll(tempL);
    }

    private void move_R() {
        // t(0,1)=r(0,1)<-r(6,3)<-r(8,7)<-r(2,5)<-t(0,1)

        // t(0,1)=r(0,1)
        temp[0] = rightSticker[0].getMaterial();
        temp[1] = rightSticker[1].getMaterial();

        // r(0,1)<-r(6,3)
        rightSticker[0].setMaterial(rightSticker[6].getMaterial());
        rightSticker[1].setMaterial(rightSticker[3].getMaterial());

        // r(6,3)<-r(8,7)
        rightSticker[6].setMaterial(rightSticker[8].getMaterial());
        rightSticker[3].setMaterial(rightSticker[7].getMaterial());

        // r(8,7)<-r(2,5)
        rightSticker[8].setMaterial(rightSticker[2].getMaterial());
        rightSticker[7].setMaterial(rightSticker[5].getMaterial());

        rightSticker[2].setMaterial(temp[0]);
        rightSticker[5].setMaterial(temp[1]);


        // t(0,1,2)=b(6,3,0)<-u(2,5,8)<-f(2,5,8)<-d(2,5,8)<-t(0,1,2)

        // t(0,1,2)=b(6,3,0)
        temp[0] = backSticker[6].getMaterial();
        temp[1] = backSticker[3].getMaterial();
        temp[2] = backSticker[0].getMaterial();

        // b(6,3,0)<-u(2,5,8)
        backSticker[6].setMaterial(upSticker[2].getMaterial());
        backSticker[3].setMaterial(upSticker[5].getMaterial());
        backSticker[0].setMaterial(upSticker[8].getMaterial());

        // u(2,5,8)<-f(2,5,8)
        upSticker[2].setMaterial(frontSticker[2].getMaterial());
        upSticker[5].setMaterial(frontSticker[5].getMaterial());
        upSticker[8].setMaterial(frontSticker[8].getMaterial());

        // f(2,5,8)<-d(2,5,8)
        frontSticker[2].setMaterial(downSticker[2].getMaterial());
        frontSticker[5].setMaterial(downSticker[5].getMaterial());
        frontSticker[8].setMaterial(downSticker[8].getMaterial());

        // d(2,5,8)<-t(0,1,2)
        downSticker[2].setMaterial(temp[0]);
        downSticker[5].setMaterial(temp[1]);
        downSticker[8].setMaterial(temp[2]);
    }

    @Override
    public void anim_U() {
        if (animationRunning) return;
        animationRunning = true;
        RotateTransition rotateTransition = new RotateTransition();
        rotateTransition.setDuration(Duration.millis(1000));
        Group tempL = new Group();
        for(int i = 0; i < 27; i+=9) tempL.getChildren().addAll(Xpart[i], Xpart[i+1], Xpart[i+2]);
        rotateTransition.setNode(tempL);
        rotateTransition.setAxis(Y_AXIS);
        rotateTransition.setByAngle(-90);
        //rotateTransition.setAutoReverse(false);
        rotateTransition.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            synchronized public void handle(ActionEvent event) {
                move_U();
                cubeXform.getChildren().removeAll(Xpart);
                cubeXform.getChildren().removeAll(tempL);
                cubeXform.getChildren().addAll(Xpart);
                animationRunning = false;
                checkIsDone();
            }
        });
        rotateTransition.play();
        cubeXform.getChildren().addAll(tempL);
    }

    private void move_U() {
        // t(0,1)=u(0,1)<-u(6,3)<-u(8,7)<-u(2,5)<-t(0,1)

        // t(0,1)=u(0,1)
        temp[0] = upSticker[0].getMaterial();
        temp[1] = upSticker[1].getMaterial();

        // u(0,1)<-u(6,3)
        upSticker[0].setMaterial(upSticker[6].getMaterial());
        upSticker[1].setMaterial(upSticker[3].getMaterial());

        // u(6,3)<-u(8,7)
        upSticker[6].setMaterial(upSticker[8].getMaterial());
        upSticker[3].setMaterial(upSticker[7].getMaterial());

        // u(8,7)<-u(2,5)
        upSticker[8].setMaterial(upSticker[2].getMaterial());
        upSticker[7].setMaterial(upSticker[5].getMaterial());

        // u(2,5)<-t(0,1)
        upSticker[2].setMaterial(temp[0]);
        upSticker[5].setMaterial(temp[1]);


        // t(0,1,2)=b(0,1,2)<-l(0,1,2)<-f(0,1,2)<-r(0,1,2)<-t(0,1,2)

        // t(0,1,2)=b(0,1,2)
        temp[0] = backSticker[0].getMaterial();
        temp[1] = backSticker[1].getMaterial();
        temp[2] = backSticker[2].getMaterial();

        // b(0,1,2)<-l(0,1,2)
        backSticker[0].setMaterial(leftSticker[0].getMaterial());
        backSticker[1].setMaterial(leftSticker[1].getMaterial());
        backSticker[2].setMaterial(leftSticker[2].getMaterial());

        // l(0,1,2)<-f(0,1,2)
        leftSticker[0].setMaterial(frontSticker[0].getMaterial());
        leftSticker[1].setMaterial(frontSticker[1].getMaterial());
        leftSticker[2].setMaterial(frontSticker[2].getMaterial());

        // f(0,1,2)<-r(0,1,2)
        frontSticker[0].setMaterial(rightSticker[0].getMaterial());
        frontSticker[1].setMaterial(rightSticker[1].getMaterial());
        frontSticker[2].setMaterial(rightSticker[2].getMaterial());

        // r(0,1,2)<-t(0,1,2)
        rightSticker[0].setMaterial(temp[0]);
        rightSticker[1].setMaterial(temp[1]);
        rightSticker[2].setMaterial(temp[2]);
    }

    @Override
    public void anim_F() {
        if (animationRunning) return;
        animationRunning = true;
        RotateTransition rotateTransition = new RotateTransition();
        rotateTransition.setDuration(Duration.millis(1000));
        Group tempL = new Group();
        for(int i = 0; i < 9; i++) tempL.getChildren().addAll(Xpart[i]);
        rotateTransition.setNode(tempL);
        rotateTransition.setAxis(Z_AXIS);
        rotateTransition.setByAngle(+90);
        //rotateTransition.setAutoReverse(false);
        rotateTransition.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            synchronized public void handle(ActionEvent event) {
                move_F();
                cubeXform.getChildren().removeAll(Xpart);
                cubeXform.getChildren().removeAll(tempL);
                cubeXform.getChildren().addAll(Xpart);
                animationRunning = false;
                checkIsDone();
            }
        });
        rotateTransition.play();
        cubeXform.getChildren().addAll(tempL);
    }

    private void move_F() {
        // t(0,1)=f(0,1)<-f(6,3)<-f(8,7)<-f(2,5)<-t(0,1)

        // t(0,1)=f(0,1)
        temp[0] = frontSticker[0].getMaterial();
        temp[1] = frontSticker[1].getMaterial();

        // f(0,1)<-f(6,3)
        frontSticker[0].setMaterial(frontSticker[6].getMaterial());
        frontSticker[1].setMaterial(frontSticker[3].getMaterial());

        // f(6,3)<-f(8,7)
        frontSticker[6].setMaterial(frontSticker[8].getMaterial());
        frontSticker[3].setMaterial(frontSticker[7].getMaterial());

        // f(8,7)<-f(2,5)
        frontSticker[8].setMaterial(frontSticker[2].getMaterial());
        frontSticker[7].setMaterial(frontSticker[5].getMaterial());

        // f(2,5)<-t(0,1)
        frontSticker[2].setMaterial(temp[0]);
        frontSticker[5].setMaterial(temp[1]);


        // t(0,1,2)=u(6,7,8)<-l(8,5,2)<-d(2,1,0)<-r(0,3,6)<-t(0,1,2)

        // t(0,1,2)=u(6,7,8)
        temp[0] = upSticker[6].getMaterial();
        temp[1] = upSticker[7].getMaterial();
        temp[2] = upSticker[8].getMaterial();

        // u(6,7,8)<-l(8,5,2)
        upSticker[6].setMaterial(leftSticker[8].getMaterial());
        upSticker[7].setMaterial(leftSticker[5].getMaterial());
        upSticker[8].setMaterial(leftSticker[2].getMaterial());

        // l(8,5,2)<-d(2,1,0)
        leftSticker[8].setMaterial(downSticker[2].getMaterial());
        leftSticker[5].setMaterial(downSticker[1].getMaterial());
        leftSticker[2].setMaterial(downSticker[0].getMaterial());

        // d(2,1,0)<-r(0,3,6)
        downSticker[2].setMaterial(rightSticker[0].getMaterial());
        downSticker[1].setMaterial(rightSticker[3].getMaterial());
        downSticker[0].setMaterial(rightSticker[6].getMaterial());

        // r(0,3,6)<-t(0,1,2)
        rightSticker[0].setMaterial(temp[0]);
        rightSticker[3].setMaterial(temp[1]);
        rightSticker[6].setMaterial(temp[2]);
    }

    @Override
    public void anim_L() {
        if (animationRunning) return;
        animationRunning = true;
        RotateTransition rotateTransition = new RotateTransition();
        rotateTransition.setDuration(Duration.millis(1000));
        Group tempL = new Group();
        // (18,9,0),(21,12,3),(24,15,6)
        for(int i = 0; i < 9; i++) tempL.getChildren().addAll(Xpart[i*3]);
        rotateTransition.setNode(tempL);
        rotateTransition.setAxis(X_AXIS);
        rotateTransition.setByAngle(-90);
        //rotateTransition.setAutoReverse(false);
        rotateTransition.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            synchronized public void handle(ActionEvent event) {
                move_L();
                cubeXform.getChildren().removeAll(Xpart);
                cubeXform.getChildren().removeAll(tempL);
                cubeXform.getChildren().addAll(Xpart);
                animationRunning = false;
                checkIsDone();
            }
        });
        rotateTransition.play();
        cubeXform.getChildren().addAll(tempL);
    }

    private void move_L() {
        // t(0,1)=l(0,1)<-l(6,3)<-l(8,7)<-l(2,5)<-t(0,1)

        // t(0,1)=l(0,1)
        temp[0] = leftSticker[0].getMaterial();
        temp[1] = leftSticker[1].getMaterial();

        // l(0,1)<-l(6,3)
        leftSticker[0].setMaterial(leftSticker[6].getMaterial());
        leftSticker[1].setMaterial(leftSticker[3].getMaterial());

        // l(6,3)<-l(8,7)
        leftSticker[6].setMaterial(leftSticker[8].getMaterial());
        leftSticker[3].setMaterial(leftSticker[7].getMaterial());

        // l(8,7)<-l(2,5)
        leftSticker[8].setMaterial(leftSticker[2].getMaterial());
        leftSticker[7].setMaterial(leftSticker[5].getMaterial());

        // l(2,5)<-t(0,1)
        leftSticker[2].setMaterial(temp[0]);
        leftSticker[5].setMaterial(temp[1]);


        // t(0,1,2)=u(0,3,6)<-b(8,5,2)<-d(0,3,6)<-f(0,3,6)<-t(0,1,2)

        // t(0,1,2)=u(0,3,6)
        temp[0] = upSticker[0].getMaterial();
        temp[1] = upSticker[3].getMaterial();
        temp[2] = upSticker[6].getMaterial();

        // u(0,3,6)<-b(8,5,2)
        upSticker[0].setMaterial(backSticker[8].getMaterial());
        upSticker[3].setMaterial(backSticker[5].getMaterial());
        upSticker[6].setMaterial(backSticker[2].getMaterial());

        // b(8,5,2)<-d(0,3,6)
        backSticker[8].setMaterial(downSticker[0].getMaterial());
        backSticker[5].setMaterial(downSticker[3].getMaterial());
        backSticker[2].setMaterial(downSticker[6].getMaterial());

        // d(0,3,6)<-f(0,3,6)
        downSticker[0].setMaterial(frontSticker[0].getMaterial());
        downSticker[3].setMaterial(frontSticker[3].getMaterial());
        downSticker[6].setMaterial(frontSticker[6].getMaterial());

        // f(0,3,6)<-t(0,1,2)
        frontSticker[0].setMaterial(temp[0]);
        frontSticker[3].setMaterial(temp[1]);
        frontSticker[6].setMaterial(temp[2]);
    }

    @Override
    public void anim_D() {
        if (animationRunning) return;
        animationRunning = true;
        RotateTransition rotateTransition = new RotateTransition();
        rotateTransition.setDuration(Duration.millis(1000));
        Group tempL = new Group();
        //(6,7,8),(15,16,17),(24,25,26)
        for(int i = 6; i < 27; i+=9) tempL.getChildren().addAll(Xpart[i],Xpart[i+1],Xpart[i+2]);
        rotateTransition.setNode(tempL);
        rotateTransition.setAxis(Y_AXIS);
        rotateTransition.setByAngle(+90);
        //rotateTransition.setAutoReverse(false);
        rotateTransition.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            synchronized public void handle(ActionEvent event) {
                move_D();
                cubeXform.getChildren().removeAll(Xpart);
                cubeXform.getChildren().removeAll(tempL);
                cubeXform.getChildren().addAll(Xpart);
                animationRunning = false;
                checkIsDone();
            }
        });
        rotateTransition.play();
        cubeXform.getChildren().addAll(tempL);
    }

    private void move_D() {
        // t(0,1)=d(0,1)<-d(6,3)<-d(8,7)<-d(2,5)<-t(0,1)

        // t(0,1)=d(0,1)
        temp[0] = downSticker[0].getMaterial();
        temp[1] = downSticker[1].getMaterial();

        // d(0,1)<-d(6,3)
        downSticker[0].setMaterial(downSticker[6].getMaterial());
        downSticker[1].setMaterial(downSticker[3].getMaterial());

        // d(6,3)<-d(8,7)
        downSticker[6].setMaterial(downSticker[8].getMaterial());
        downSticker[3].setMaterial(downSticker[7].getMaterial());

        // d(8,7)<-d(2,5)
        downSticker[8].setMaterial(downSticker[2].getMaterial());
        downSticker[7].setMaterial(downSticker[5].getMaterial());

        // d(2,5)<-d(0,1)
        downSticker[2].setMaterial(temp[0]);
        downSticker[5].setMaterial(temp[1]);


        // t(0,1,2)=f(6,7,8)<-l(8,5,2)<-b(6,7,8)<-r(6,7,8)<-t(0,1,2)

        // t(0,1,2)=f(6,7,8)
        temp[0] = frontSticker[6].getMaterial();
        temp[1] = frontSticker[7].getMaterial();
        temp[2] = frontSticker[8].getMaterial();

        // f(6,7,8)<-l(6,7,8)
        frontSticker[6].setMaterial(leftSticker[6].getMaterial());
        frontSticker[7].setMaterial(leftSticker[7].getMaterial());
        frontSticker[8].setMaterial(leftSticker[8].getMaterial());

        // l(6,7,8)<-b(6,7,8)
        leftSticker[6].setMaterial(backSticker[6].getMaterial());
        leftSticker[7].setMaterial(backSticker[7].getMaterial());
        leftSticker[8].setMaterial(backSticker[8].getMaterial());

        // b(6,7,8)<-r(6,7,8)
        backSticker[6].setMaterial(rightSticker[6].getMaterial());
        backSticker[7].setMaterial(rightSticker[7].getMaterial());
        backSticker[8].setMaterial(rightSticker[8].getMaterial());

        // r(6,7,8)<-t(0,1,2)
        rightSticker[6].setMaterial(temp[0]);
        rightSticker[7].setMaterial(temp[1]);
        rightSticker[8].setMaterial(temp[2]);
    }

    @Override
    public void anim_B() {
        if (animationRunning) return;
        animationRunning = true;
        RotateTransition rotateTransition = new RotateTransition();
        rotateTransition.setDuration(Duration.millis(1000));
        Group tempL = new Group();
        for(int i = 0; i < 9; i++) tempL.getChildren().addAll(Xpart[18+i]);
        rotateTransition.setNode(tempL);
        rotateTransition.setAxis(Z_AXIS);
        rotateTransition.setByAngle(-90);
        //rotateTransition.setAutoReverse(false);
        rotateTransition.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            synchronized public void handle(ActionEvent event) {
                move_B();
                cubeXform.getChildren().removeAll(Xpart);
                cubeXform.getChildren().removeAll(tempL);
                cubeXform.getChildren().addAll(Xpart);
                animationRunning = false;
                checkIsDone();
            }
        });
        rotateTransition.play();
        cubeXform.getChildren().addAll(tempL);
    }

    private void move_B() {
        // t(0,1)=b(0,1)<-b(6,3)<-b(8,7)<-b(2,5)<-t(0,1)

        // t(0,1)=b(0,1)
        temp[0] = backSticker[0].getMaterial();
        temp[1] = backSticker[1].getMaterial();

        // b(0,1)<-b(6,3)
        backSticker[0].setMaterial(backSticker[6].getMaterial());
        backSticker[1].setMaterial(backSticker[3].getMaterial());

        // b(6,3)<-b(8,7)
        backSticker[6].setMaterial(backSticker[8].getMaterial());
        backSticker[3].setMaterial(backSticker[7].getMaterial());

        // b(8,7)<-b(2,5)
        backSticker[8].setMaterial(backSticker[2].getMaterial());
        backSticker[7].setMaterial(backSticker[5].getMaterial());

        // b(2,5)<-t(0,1)
        backSticker[2].setMaterial(temp[0]);
        backSticker[5].setMaterial(temp[1]);


        // t(0,1,2)=u(0,1,2)<-r(2,5,8)<-d(8,7,6)<-l(6,3,0)<-t(0,1,2)

        // t(0,1,2)=u(0,1,2)
        temp[0] = upSticker[0].getMaterial();
        temp[1] = upSticker[1].getMaterial();
        temp[2] = upSticker[2].getMaterial();

        // u(0,1,2)<-r(2,5,8)
        upSticker[0].setMaterial(rightSticker[2].getMaterial());
        upSticker[1].setMaterial(rightSticker[5].getMaterial());
        upSticker[2].setMaterial(rightSticker[8].getMaterial());

        // r(2,5,8)<-d(8,7,6)
        rightSticker[2].setMaterial(downSticker[8].getMaterial());
        rightSticker[5].setMaterial(downSticker[7].getMaterial());
        rightSticker[8].setMaterial(downSticker[6].getMaterial());

        // d(8,7,6)<-l(6,3,0)
        downSticker[8].setMaterial(leftSticker[6].getMaterial());
        downSticker[7].setMaterial(leftSticker[3].getMaterial());
        downSticker[6].setMaterial(leftSticker[0].getMaterial());

        // l(6,3,0)<-t(0,1,2)
        leftSticker[6].setMaterial(temp[0]);
        leftSticker[3].setMaterial(temp[1]);
        leftSticker[0].setMaterial(temp[2]);
    }

    @Override
    public void anim_M() {
        if (animationRunning) return;
        animationRunning = true;
        RotateTransition rotateTransition = new RotateTransition();
        rotateTransition.setDuration(Duration.millis(1000));
        Group tempL = new Group();
        for(int i = 0; i < 9; i++) tempL.getChildren().addAll(Xpart[i*3+1]);
        rotateTransition.setNode(tempL);
        rotateTransition.setAxis(X_AXIS);
        rotateTransition.setByAngle(-90);
        //rotateTransition.setAutoReverse(false);
        rotateTransition.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            synchronized public void handle(ActionEvent event) {
                move_M();
                cubeXform.getChildren().removeAll(Xpart);
                cubeXform.getChildren().removeAll(tempL);
                cubeXform.getChildren().addAll(Xpart);
                animationRunning = false;
                checkIsDone();
            }
        });
        rotateTransition.play();
        cubeXform.getChildren().addAll(tempL);
    }

    private void move_M() {
        // t(0,1,2)=f(1,4,7)<-u(1,4,7)<-b(7,4,1)<-d(1,4,7)<-t(0,1,2)

        // t(0,1,2)=f(1,4,7)
        temp[0] = frontSticker[1].getMaterial();
        temp[1] = frontSticker[4].getMaterial();
        temp[2] = frontSticker[7].getMaterial();

        // f(1,4,7)<-u(1,4,7)
        frontSticker[1].setMaterial(upSticker[1].getMaterial());
        frontSticker[4].setMaterial(upSticker[4].getMaterial());
        frontSticker[7].setMaterial(upSticker[7].getMaterial());

        // u(1,4,7)<-b(7,4,1)
        upSticker[1].setMaterial(backSticker[7].getMaterial());
        upSticker[4].setMaterial(backSticker[4].getMaterial());
        upSticker[7].setMaterial(backSticker[1].getMaterial());

        // b(7,4,1)<-d(1,4,7)
        backSticker[7].setMaterial(downSticker[1].getMaterial());
        backSticker[4].setMaterial(downSticker[4].getMaterial());
        backSticker[1].setMaterial(downSticker[7].getMaterial());

        // d(1,4,7)<-t(0,1,2)
        downSticker[1].setMaterial(temp[0]);
        downSticker[4].setMaterial(temp[1]);
        downSticker[7].setMaterial(temp[2]);
    }

    @Override
    public void anim_E() {
        if (animationRunning) return;
        animationRunning = true;
        RotateTransition rotateTransition = new RotateTransition();
        rotateTransition.setDuration(Duration.millis(1000));
        Group tempL = new Group();
        for(int i = 3; i < 27; i+=9) tempL.getChildren().addAll(Xpart[i],Xpart[i+1], Xpart[i+2]);
        rotateTransition.setNode(tempL);
        rotateTransition.setAxis(Y_AXIS);
        rotateTransition.setByAngle(+90);
        //rotateTransition.setAutoReverse(false);
        rotateTransition.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            synchronized public void handle(ActionEvent event) {
                move_E();
                cubeXform.getChildren().removeAll(Xpart);
                cubeXform.getChildren().removeAll(tempL);
                cubeXform.getChildren().addAll(Xpart);
                animationRunning = false;
                checkIsDone();
            }
        });
        rotateTransition.play();
        cubeXform.getChildren().addAll(tempL);
    }

    private void move_E() {
        // t(0,1,2)=f(3,4,5)<-l(3,4,5)<-b(3,4,5)<-r(3,4,5)<-t(0,1,2)

        // t(0,1,2)=f(3,4,5)
        temp[0] = frontSticker[3].getMaterial();
        temp[1] = frontSticker[4].getMaterial();
        temp[2] = frontSticker[5].getMaterial();

        // f(3,4,5)<-l(3,4,5)
        frontSticker[3].setMaterial(leftSticker[3].getMaterial());
        frontSticker[4].setMaterial(leftSticker[4].getMaterial());
        frontSticker[5].setMaterial(leftSticker[5].getMaterial());

        // l(3,4,5)<-b(3,4,5)
        leftSticker[3].setMaterial(backSticker[3].getMaterial());
        leftSticker[4].setMaterial(backSticker[4].getMaterial());
        leftSticker[5].setMaterial(backSticker[5].getMaterial());

        // b(3,4,5)<-r(3,4,5)
        backSticker[3].setMaterial(rightSticker[3].getMaterial());
        backSticker[4].setMaterial(rightSticker[4].getMaterial());
        backSticker[5].setMaterial(rightSticker[5].getMaterial());

        // r(3,4,5)<-t(0,1,2)
        rightSticker[3].setMaterial(temp[0]);
        rightSticker[4].setMaterial(temp[1]);
        rightSticker[5].setMaterial(temp[2]);
    }

    @Override
    public void anim_S() {
        if (animationRunning) return;
        animationRunning = true;
        RotateTransition rotateTransition = new RotateTransition();
        rotateTransition.setDuration(Duration.millis(1000));
        Group tempL = new Group();
        for(int i = 0; i < 9; i++) tempL.getChildren().addAll(Xpart[9+i]);
        rotateTransition.setNode(tempL);
        rotateTransition.setAxis(Z_AXIS);
        rotateTransition.setByAngle(+90);
        //rotateTransition.setAutoReverse(false);
        rotateTransition.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            synchronized public void handle(ActionEvent event) {
                move_S();
                cubeXform.getChildren().removeAll(Xpart);
                cubeXform.getChildren().removeAll(tempL);
                cubeXform.getChildren().addAll(Xpart);
                animationRunning = false;
                checkIsDone();
            }
        });
        rotateTransition.play();
        cubeXform.getChildren().addAll(tempL);
    }

    private void move_S() {
        // t(0,1,2)=u(3,4,5)<-l(7,4,1)<-d(5,4,3)<-r(1,4,7)<-t(0,1,2)

        // t(0,1,2)=u(3,4,5)
        temp[0] = upSticker[3].getMaterial();
        temp[1] = upSticker[4].getMaterial();
        temp[2] = upSticker[5].getMaterial();

        // u(3,4,5)<-l(7,4,1)
        upSticker[3].setMaterial(leftSticker[7].getMaterial());
        upSticker[4].setMaterial(leftSticker[4].getMaterial());
        upSticker[5].setMaterial(leftSticker[1].getMaterial());

        // l(7,4,1)<-d(5,4,3)
        leftSticker[7].setMaterial(downSticker[5].getMaterial());
        leftSticker[4].setMaterial(downSticker[4].getMaterial());
        leftSticker[1].setMaterial(downSticker[3].getMaterial());

        // d(5,4,3)<-r(1,4,7)
        downSticker[5].setMaterial(rightSticker[1].getMaterial());
        downSticker[4].setMaterial(rightSticker[4].getMaterial());
        downSticker[3].setMaterial(rightSticker[7].getMaterial());

        // r(1,4,7)<-t(0,1,2)
        rightSticker[1].setMaterial(temp[0]);
        rightSticker[4].setMaterial(temp[1]);
        rightSticker[7].setMaterial(temp[2]);
    }

    @Override
    public void anim_x() {
        if (animationRunning) return;
        animationRunning = true;
        RotateTransition rotateTransition = new RotateTransition();
        rotateTransition.setDuration(Duration.millis(1000));
        Group tempL = new Group();
        for(int i = 0; i < 27; i++) tempL.getChildren().addAll(Xpart[i]);
        rotateTransition.setNode(tempL);
        rotateTransition.setAxis(X_AXIS);
        rotateTransition.setByAngle(+90);
        //rotateTransition.setAutoReverse(false);
        rotateTransition.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            synchronized public void handle(ActionEvent event) {
                move_x();
                cubeXform.getChildren().removeAll(Xpart);
                cubeXform.getChildren().removeAll(tempL);
                cubeXform.getChildren().addAll(Xpart);
                animationRunning = false;
                checkIsDone();
            }
        });
        rotateTransition.play();
        cubeXform.getChildren().addAll(tempL);
    }

    private void move_x() {
        move_R();
        for(int i = 0; i < 3; i++) {
            move_M();
            move_L();
        }
    }

    @Override
    public void anim_y() {
        if (animationRunning) return;
        animationRunning = true;
        RotateTransition rotateTransition = new RotateTransition();
        rotateTransition.setDuration(Duration.millis(1000));
        Group tempL = new Group();
        for(int i = 0; i < 27; i++) tempL.getChildren().addAll(Xpart[i]);
        rotateTransition.setNode(tempL);
        rotateTransition.setAxis(Y_AXIS);
        rotateTransition.setByAngle(-90);
        //rotateTransition.setAutoReverse(false);
        rotateTransition.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            synchronized public void handle(ActionEvent event) {
                move_y();
                cubeXform.getChildren().removeAll(Xpart);
                cubeXform.getChildren().removeAll(tempL);
                cubeXform.getChildren().addAll(Xpart);
                animationRunning = false;
                checkIsDone();
            }
        });
        rotateTransition.play();
        cubeXform.getChildren().addAll(tempL);
    }

    private void move_y() {
        move_U();
        for(int i = 0; i < 3; i++) {
            move_E();
            move_D();
        }
    }

    @Override
    public void anim_z() {
        if (animationRunning) return;
        animationRunning = true;
        RotateTransition rotateTransition = new RotateTransition();
        rotateTransition.setDuration(Duration.millis(1000));
        Group tempL = new Group();
        for(int i = 0; i < 27; i++) tempL.getChildren().addAll(Xpart[i]);
        rotateTransition.setNode(tempL);
        rotateTransition.setAxis(Z_AXIS);
        rotateTransition.setByAngle(+90);
        //rotateTransition.setAutoReverse(false);
        rotateTransition.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            synchronized public void handle(ActionEvent event) {
                move_z();
                cubeXform.getChildren().removeAll(Xpart);
                cubeXform.getChildren().removeAll(tempL);
                cubeXform.getChildren().addAll(Xpart);
                animationRunning = false;
                checkIsDone();
            }
        });
        rotateTransition.play();
        cubeXform.getChildren().addAll(tempL);
    }

    private void move_z() {
        move_F();
        move_S();
        for (int i = 0; i < 3; i++) {
            move_B();
        }
    }

    @Override
    public void anim_r() {
        if (animationRunning) return;
        animationRunning = true;
        RotateTransition rotateTransition = new RotateTransition();
        rotateTransition.setDuration(Duration.millis(1000));
        Group tempL = new Group();
        for(int i = 1; i < 27; i+=3) tempL.getChildren().addAll(Xpart[i], Xpart[i+1]);
        rotateTransition.setNode(tempL);
        rotateTransition.setAxis(X_AXIS);
        rotateTransition.setByAngle(+90);
        //rotateTransition.setAutoReverse(false);
        rotateTransition.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            synchronized public void handle(ActionEvent event) {
                move_r();
                cubeXform.getChildren().removeAll(Xpart);
                cubeXform.getChildren().removeAll(tempL);
                cubeXform.getChildren().addAll(Xpart);
                animationRunning = false;
                checkIsDone();
            }
        });
        rotateTransition.play();
        cubeXform.getChildren().addAll(tempL);
    }

    private void move_r() {
        move_R();
        for(int i = 0; i < 3; i++) {
            move_M();
        }
    }

    @Override
    public void anim_u() {
        if (animationRunning) return;
        animationRunning = true;
        RotateTransition rotateTransition = new RotateTransition();
        rotateTransition.setDuration(Duration.millis(1000));
        Group tempL = new Group();
        for(int i = 0; i < 18; i++) tempL.getChildren().addAll(Xpart[9*(i/6)+i%6]);
        rotateTransition.setNode(tempL);
        rotateTransition.setAxis(Y_AXIS);
        rotateTransition.setByAngle(-90);
        //rotateTransition.setAutoReverse(false);
        rotateTransition.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            synchronized public void handle(ActionEvent event) {
                move_u();
                cubeXform.getChildren().removeAll(Xpart);
                cubeXform.getChildren().removeAll(tempL);
                cubeXform.getChildren().addAll(Xpart);
                animationRunning = false;
                checkIsDone();
            }
        });
        rotateTransition.play();
        cubeXform.getChildren().addAll(tempL);
    }

    private void move_u() {
        move_U();
        for(int i = 0; i < 3; i++) {
            move_E();
        }
    }

    @Override
    public void anim_f() {
        if (animationRunning) return;
        animationRunning = true;
        RotateTransition rotateTransition = new RotateTransition();
        rotateTransition.setDuration(Duration.millis(1000));
        Group tempL = new Group();
        for(int i = 0; i < 18; i++) tempL.getChildren().addAll(Xpart[i]);
        rotateTransition.setNode(tempL);
        rotateTransition.setAxis(Z_AXIS);
        rotateTransition.setByAngle(+90);
        //rotateTransition.setAutoReverse(false);
        rotateTransition.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            synchronized public void handle(ActionEvent event) {
                move_f();
                cubeXform.getChildren().removeAll(Xpart);
                cubeXform.getChildren().removeAll(tempL);
                cubeXform.getChildren().addAll(Xpart);
                animationRunning = false;
                checkIsDone();
            }
        });
        rotateTransition.play();
        cubeXform.getChildren().addAll(tempL);
    }

    private void move_f() {
        move_F();
        move_S();
    }

    @Override
    public void anim_l() {
        if (animationRunning) return;
        animationRunning = true;
        RotateTransition rotateTransition = new RotateTransition();
        rotateTransition.setDuration(Duration.millis(1000));
        Group tempL = new Group();
        for(int i = 0; i < 27; i+=3) tempL.getChildren().addAll(Xpart[i], Xpart[i+1]);
        rotateTransition.setNode(tempL);
        rotateTransition.setAxis(X_AXIS);
        rotateTransition.setByAngle(-90);
        //rotateTransition.setAutoReverse(false);
        rotateTransition.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            synchronized public void handle(ActionEvent event) {
                move_l();
                cubeXform.getChildren().removeAll(Xpart);
                cubeXform.getChildren().removeAll(tempL);
                cubeXform.getChildren().addAll(Xpart);
                animationRunning = false;
                checkIsDone();
            }
        });
        rotateTransition.play();
        cubeXform.getChildren().addAll(tempL);
    }

    private void move_l() {
        move_L();
        move_M();
    }

    @Override
    public void anim_d() {
        if (animationRunning) return;
        animationRunning = true;
        RotateTransition rotateTransition = new RotateTransition();
        rotateTransition.setDuration(Duration.millis(1000));
        Group tempL = new Group();
        for(int i = 0; i < 18; i++) tempL.getChildren().addAll(Xpart[9*(i/6)+i%6+3]);
        rotateTransition.setNode(tempL);
        rotateTransition.setAxis(Y_AXIS);
        rotateTransition.setByAngle(+90);
        //rotateTransition.setAutoReverse(false);
        rotateTransition.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            synchronized public void handle(ActionEvent event) {
                move_d();
                cubeXform.getChildren().removeAll(Xpart);
                cubeXform.getChildren().removeAll(tempL);
                cubeXform.getChildren().addAll(Xpart);
                animationRunning = false;
                checkIsDone();
            }
        });
        rotateTransition.play();
        cubeXform.getChildren().addAll(tempL);
    }

    private void move_d() {
        move_D();
        move_E();
    }

    @Override
    public void anim_b() {
        if (animationRunning) return;
        animationRunning = true;
        RotateTransition rotateTransition = new RotateTransition();
        rotateTransition.setDuration(Duration.millis(1000));
        Group tempL = new Group();
        for(int i = 9; i < 27; i++) tempL.getChildren().addAll(Xpart[i]);
        rotateTransition.setNode(tempL);
        rotateTransition.setAxis(Z_AXIS);
        rotateTransition.setByAngle(-90);
        //rotateTransition.setAutoReverse(false);
        rotateTransition.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            synchronized public void handle(ActionEvent event) {
                move_b();
                cubeXform.getChildren().removeAll(Xpart);
                cubeXform.getChildren().removeAll(tempL);
                cubeXform.getChildren().addAll(Xpart);
                animationRunning = false;
                checkIsDone();
            }
        });
        rotateTransition.play();
        cubeXform.getChildren().addAll(tempL);
    }

    private void move_b() {
        move_B();
        for (int i = 0; i < 3; i++) {
            move_S();
        }
    }


    @Override
    public void anim_RI() {
        if (animationRunning) return;
        animationRunning = true;
        RotateTransition rotateTransition = new RotateTransition();
        rotateTransition.setDuration(Duration.millis(1000));
        Group tempL = new Group();
        for(int i = 2; i < 27; i+=3) tempL.getChildren().addAll(Xpart[i]);
        rotateTransition.setNode(tempL);
        rotateTransition.setAxis(X_AXIS);
        rotateTransition.setByAngle(-90);
        //rotateTransition.setAutoReverse(false);
        rotateTransition.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            synchronized public void handle(ActionEvent event) {
                move_RI();
                cubeXform.getChildren().removeAll(Xpart);
                cubeXform.getChildren().removeAll(tempL);
                cubeXform.getChildren().addAll(Xpart);
                animationRunning = false;
                checkIsDone();
            }
        });
        rotateTransition.play();
        cubeXform.getChildren().addAll(tempL);
    }

    private void move_RI() {
        for (int i = 0; i < 3; i++) {
            move_R();
        }
    }

    @Override
    public void anim_UI() {
        if (animationRunning) return;
        animationRunning = true;
        RotateTransition rotateTransition = new RotateTransition();
        rotateTransition.setDuration(Duration.millis(1000));
        Group tempL = new Group();
        for(int i = 0; i < 27; i+=9) tempL.getChildren().addAll(Xpart[i], Xpart[i+1], Xpart[i+2]);
        rotateTransition.setNode(tempL);
        rotateTransition.setAxis(Y_AXIS);
        rotateTransition.setByAngle(+90);
        //rotateTransition.setAutoReverse(false);
        rotateTransition.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            synchronized public void handle(ActionEvent event) {
                move_UI();
                cubeXform.getChildren().removeAll(Xpart);
                cubeXform.getChildren().removeAll(tempL);
                cubeXform.getChildren().addAll(Xpart);
                animationRunning = false;
                checkIsDone();
            }
        });
        rotateTransition.play();
        cubeXform.getChildren().addAll(tempL);
    }

    private void move_UI() {
        for (int i = 0; i < 3; i++) {
            move_U();
        }
    }

    @Override
    public void anim_FI() {
        if (animationRunning) return;
        animationRunning = true;
        RotateTransition rotateTransition = new RotateTransition();
        rotateTransition.setDuration(Duration.millis(1000));
        Group tempL = new Group();
        for(int i = 0; i < 9; i++) tempL.getChildren().addAll(Xpart[i]);
        rotateTransition.setNode(tempL);
        rotateTransition.setAxis(Z_AXIS);
        rotateTransition.setByAngle(-90);
        //rotateTransition.setAutoReverse(false);
        rotateTransition.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            synchronized public void handle(ActionEvent event) {
                move_FI();
                cubeXform.getChildren().removeAll(Xpart);
                cubeXform.getChildren().removeAll(tempL);
                cubeXform.getChildren().addAll(Xpart);
                animationRunning = false;
                checkIsDone();
            }
        });
        rotateTransition.play();
        cubeXform.getChildren().addAll(tempL);
    }

    private void move_FI() {
        for (int i = 0; i < 3; i++) {
            move_F();
        }
    }

    @Override
    public void anim_LI() {
        if (animationRunning) return;
        animationRunning = true;
        RotateTransition rotateTransition = new RotateTransition();
        rotateTransition.setDuration(Duration.millis(1000));
        Group tempL = new Group();
        // (18,9,0),(21,12,3),(24,15,6)
        for(int i = 0; i < 9; i++) tempL.getChildren().addAll(Xpart[i*3]);
        rotateTransition.setNode(tempL);
        rotateTransition.setAxis(X_AXIS);
        rotateTransition.setByAngle(+90);
        //rotateTransition.setAutoReverse(false);
        rotateTransition.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            synchronized public void handle(ActionEvent event) {
                move_LI();
                cubeXform.getChildren().removeAll(Xpart);
                cubeXform.getChildren().removeAll(tempL);
                cubeXform.getChildren().addAll(Xpart);
                animationRunning = false;
                checkIsDone();
            }
        });
        rotateTransition.play();
        cubeXform.getChildren().addAll(tempL);
    }

    private void move_LI() {
        for (int i = 0; i < 3; i++) {
            move_L();
        }
    }

    @Override
    public void anim_DI() {
        if (animationRunning) return;
        animationRunning = true;
        RotateTransition rotateTransition = new RotateTransition();
        rotateTransition.setDuration(Duration.millis(1000));
        Group tempL = new Group();
        //(6,7,8),(15,16,17),(24,25,26)
        for(int i = 6; i < 27; i+=9) tempL.getChildren().addAll(Xpart[i],Xpart[i+1],Xpart[i+2]);
        rotateTransition.setNode(tempL);
        rotateTransition.setAxis(Y_AXIS);
        rotateTransition.setByAngle(-90);
        //rotateTransition.setAutoReverse(false);
        rotateTransition.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            synchronized public void handle(ActionEvent event) {
                move_DI();
                cubeXform.getChildren().removeAll(Xpart);
                cubeXform.getChildren().removeAll(tempL);
                cubeXform.getChildren().addAll(Xpart);
                animationRunning = false;
                checkIsDone();
            }
        });
        rotateTransition.play();
        cubeXform.getChildren().addAll(tempL);
    }

    private void move_DI() {
        for (int i = 0; i < 3; i++) {
            move_D();
        }
    }

    @Override
    public void anim_BI() {
        if (animationRunning) return;
        animationRunning = true;
        RotateTransition rotateTransition = new RotateTransition();
        rotateTransition.setDuration(Duration.millis(1000));
        Group tempL = new Group();
        for(int i = 0; i < 9; i++) tempL.getChildren().addAll(Xpart[18+i]);
        rotateTransition.setNode(tempL);
        rotateTransition.setAxis(Z_AXIS);
        rotateTransition.setByAngle(+90);
        //rotateTransition.setAutoReverse(false);
        rotateTransition.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            synchronized public void handle(ActionEvent event) {
                move_BI();
                cubeXform.getChildren().removeAll(Xpart);
                cubeXform.getChildren().removeAll(tempL);
                cubeXform.getChildren().addAll(Xpart);
                animationRunning = false;
                checkIsDone();
            }
        });
        rotateTransition.play();
        cubeXform.getChildren().addAll(tempL);
    }

    private void move_BI() {
        for (int i = 0; i < 3; i++) {
            move_B();
        }
    }

    @Override
    public void anim_MI() {
        if (animationRunning) return;
        animationRunning = true;
        RotateTransition rotateTransition = new RotateTransition();
        rotateTransition.setDuration(Duration.millis(1000));
        Group tempL = new Group();
        for(int i = 0; i < 9; i++) tempL.getChildren().addAll(Xpart[i*3+1]);
        rotateTransition.setNode(tempL);
        rotateTransition.setAxis(X_AXIS);
        rotateTransition.setByAngle(+90);
        //rotateTransition.setAutoReverse(false);
        rotateTransition.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            synchronized public void handle(ActionEvent event) {
                move_MI();
                cubeXform.getChildren().removeAll(Xpart);
                cubeXform.getChildren().removeAll(tempL);
                cubeXform.getChildren().addAll(Xpart);
                animationRunning = false;
                checkIsDone();
            }
        });
        rotateTransition.play();
        cubeXform.getChildren().addAll(tempL);
    }

    private void move_MI() {
        for (int i = 0; i < 3; i++) {
            move_M();
        }
    }

    @Override
    public void anim_EI() {
        if (animationRunning) return;
        animationRunning = true;
        RotateTransition rotateTransition = new RotateTransition();
        rotateTransition.setDuration(Duration.millis(1000));
        Group tempL = new Group();
        for(int i = 3; i < 27; i+=9) tempL.getChildren().addAll(Xpart[i],Xpart[i+1], Xpart[i+2]);
        rotateTransition.setNode(tempL);
        rotateTransition.setAxis(Y_AXIS);
        rotateTransition.setByAngle(-90);
        //rotateTransition.setAutoReverse(false);
        rotateTransition.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            synchronized public void handle(ActionEvent event) {
                move_EI();
                cubeXform.getChildren().removeAll(Xpart);
                cubeXform.getChildren().removeAll(tempL);
                cubeXform.getChildren().addAll(Xpart);
                animationRunning = false;
                checkIsDone();
            }
        });
        rotateTransition.play();
        cubeXform.getChildren().addAll(tempL);
    }

    private void move_EI() {
        for (int i = 0; i < 3; i++) {
            move_E();
        }
    }

    @Override
    public void anim_SI() {
        if (animationRunning) return;
        animationRunning = true;
        RotateTransition rotateTransition = new RotateTransition();
        rotateTransition.setDuration(Duration.millis(1000));
        Group tempL = new Group();
        for(int i = 0; i < 9; i++) tempL.getChildren().addAll(Xpart[9+i]);
        rotateTransition.setNode(tempL);
        rotateTransition.setAxis(Z_AXIS);
        rotateTransition.setByAngle(-90);
        //rotateTransition.setAutoReverse(false);
        rotateTransition.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            synchronized public void handle(ActionEvent event) {
                move_SI();
                cubeXform.getChildren().removeAll(Xpart);
                cubeXform.getChildren().removeAll(tempL);
                cubeXform.getChildren().addAll(Xpart);
                animationRunning = false;
                checkIsDone();
            }
        });
        rotateTransition.play();
        cubeXform.getChildren().addAll(tempL);
    }

    private void move_SI() {
        for (int i = 0; i < 3; i++) {
            move_S();
        }
    }

    @Override
    public void anim_xi() {
        if (animationRunning) return;
        animationRunning = true;
        RotateTransition rotateTransition = new RotateTransition();
        rotateTransition.setDuration(Duration.millis(1000));
        Group tempL = new Group();
        for(int i = 0; i < 27; i++) tempL.getChildren().addAll(Xpart[i]);
        rotateTransition.setNode(tempL);
        rotateTransition.setAxis(X_AXIS);
        rotateTransition.setByAngle(-90);
        //rotateTransition.setAutoReverse(false);
        rotateTransition.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            synchronized public void handle(ActionEvent event) {
                move_xi();
                cubeXform.getChildren().removeAll(Xpart);
                cubeXform.getChildren().removeAll(tempL);
                cubeXform.getChildren().addAll(Xpart);
                animationRunning = false;
                checkIsDone();
            }
        });
        rotateTransition.play();
        cubeXform.getChildren().addAll(tempL);
    }

    private void move_xi() {
        move_M();
        move_L();
        for(int i = 0; i < 3; i++) {
            move_R();
        }
    }

    @Override
    public void anim_yi() {
        if (animationRunning) return;
        animationRunning = true;
        RotateTransition rotateTransition = new RotateTransition();
        rotateTransition.setDuration(Duration.millis(1000));
        Group tempL = new Group();
        for(int i = 0; i < 27; i++) tempL.getChildren().addAll(Xpart[i]);
        rotateTransition.setNode(tempL);
        rotateTransition.setAxis(Y_AXIS);
        rotateTransition.setByAngle(+90);
        //rotateTransition.setAutoReverse(false);
        rotateTransition.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            synchronized public void handle(ActionEvent event) {
                move_yi();
                cubeXform.getChildren().removeAll(Xpart);
                cubeXform.getChildren().removeAll(tempL);
                cubeXform.getChildren().addAll(Xpart);
                animationRunning = false;
                checkIsDone();
            }
        });
        rotateTransition.play();
        cubeXform.getChildren().addAll(tempL);
    }

    private void move_yi() {
        move_E();
        move_D();
        for(int i = 0; i < 3; i++) {
            move_U();
        }
    }

    @Override
    public void anim_zi() {
        if (animationRunning) return;
        animationRunning = true;
        RotateTransition rotateTransition = new RotateTransition();
        rotateTransition.setDuration(Duration.millis(1000));
        Group tempL = new Group();
        for(int i = 0; i < 27; i++) tempL.getChildren().addAll(Xpart[i]);
        rotateTransition.setNode(tempL);
        rotateTransition.setAxis(Z_AXIS);
        rotateTransition.setByAngle(-90);
        //rotateTransition.setAutoReverse(false);
        rotateTransition.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            synchronized public void handle(ActionEvent event) {
                move_zi();
                cubeXform.getChildren().removeAll(Xpart);
                cubeXform.getChildren().removeAll(tempL);
                cubeXform.getChildren().addAll(Xpart);
                animationRunning = false;
                checkIsDone();
            }
        });
        rotateTransition.play();
        cubeXform.getChildren().addAll(tempL);
    }

    private void move_zi() {
        move_B();
        for (int i = 0; i < 3; i++) {
            move_F();
            move_S();
        }
    }

    @Override
    public void anim_ri() {
        if (animationRunning) return;
        animationRunning = true;
        RotateTransition rotateTransition = new RotateTransition();
        rotateTransition.setDuration(Duration.millis(1000));
        Group tempL = new Group();
        for(int i = 1; i < 27; i+=3) tempL.getChildren().addAll(Xpart[i], Xpart[i+1]);
        rotateTransition.setNode(tempL);
        rotateTransition.setAxis(X_AXIS);
        rotateTransition.setByAngle(-90);
        //rotateTransition.setAutoReverse(false);
        rotateTransition.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            synchronized public void handle(ActionEvent event) {
                move_ri();
                cubeXform.getChildren().removeAll(Xpart);
                cubeXform.getChildren().removeAll(tempL);
                cubeXform.getChildren().addAll(Xpart);
                animationRunning = false;
                checkIsDone();
            }
        });
        rotateTransition.play();
        cubeXform.getChildren().addAll(tempL);
    }

    private void move_ri() {
        move_M();
        for(int i = 0; i < 3; i++) {
            move_R();
        }
    }

    @Override
    public void anim_ui() {
        if (animationRunning) return;
        animationRunning = true;
        RotateTransition rotateTransition = new RotateTransition();
        rotateTransition.setDuration(Duration.millis(1000));
        Group tempL = new Group();
        for(int i = 0; i < 18; i++) tempL.getChildren().addAll(Xpart[9*(i/6)+i%6]);
        rotateTransition.setNode(tempL);
        rotateTransition.setAxis(Y_AXIS);
        rotateTransition.setByAngle(+90);
        //rotateTransition.setAutoReverse(false);
        rotateTransition.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            synchronized public void handle(ActionEvent event) {
                move_ui();
                cubeXform.getChildren().removeAll(Xpart);
                cubeXform.getChildren().removeAll(tempL);
                cubeXform.getChildren().addAll(Xpart);
                animationRunning = false;
                checkIsDone();
            }
        });
        rotateTransition.play();
        cubeXform.getChildren().addAll(tempL);
    }

    private void move_ui() {
        move_E();
        for(int i = 0; i < 3; i++) {
            move_U();
        }
    }

    @Override
    public void anim_fi() {
        if (animationRunning) return;
        animationRunning = true;
        RotateTransition rotateTransition = new RotateTransition();
        rotateTransition.setDuration(Duration.millis(1000));
        Group tempL = new Group();
        for(int i = 0; i < 18; i++) tempL.getChildren().addAll(Xpart[i]);
        rotateTransition.setNode(tempL);
        rotateTransition.setAxis(Z_AXIS);
        rotateTransition.setByAngle(-90);
        //rotateTransition.setAutoReverse(false);
        rotateTransition.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            synchronized public void handle(ActionEvent event) {
                move_fi();
                cubeXform.getChildren().removeAll(Xpart);
                cubeXform.getChildren().removeAll(tempL);
                cubeXform.getChildren().addAll(Xpart);
                animationRunning = false;
                checkIsDone();
            }
        });
        rotateTransition.play();
        cubeXform.getChildren().addAll(tempL);
    }

    private void move_fi() {
        for (int i = 0; i < 3; i++) {
            move_F();
            move_S();
        }
    }

    @Override
    public void anim_li() {
        if (animationRunning) return;
        animationRunning = true;
        RotateTransition rotateTransition = new RotateTransition();
        rotateTransition.setDuration(Duration.millis(1000));
        Group tempL = new Group();
        for(int i = 0; i < 27; i+=3) tempL.getChildren().addAll(Xpart[i], Xpart[i+1]);
        rotateTransition.setNode(tempL);
        rotateTransition.setAxis(X_AXIS);
        rotateTransition.setByAngle(+90);
        //rotateTransition.setAutoReverse(false);
        rotateTransition.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            synchronized public void handle(ActionEvent event) {
                move_li();
                cubeXform.getChildren().removeAll(Xpart);
                cubeXform.getChildren().removeAll(tempL);
                cubeXform.getChildren().addAll(Xpart);
                animationRunning = false;
                checkIsDone();
            }
        });
        rotateTransition.play();
        cubeXform.getChildren().addAll(tempL);
    }

    private void move_li() {
        for (int i = 0; i < 3; i++) {
            move_L();
            move_M();
        }
    }

    @Override
    public void anim_di() {
        if (animationRunning) return;
        animationRunning = true;
        RotateTransition rotateTransition = new RotateTransition();
        rotateTransition.setDuration(Duration.millis(1000));
        Group tempL = new Group();
        for(int i = 0; i < 18; i++) tempL.getChildren().addAll(Xpart[9*(i/6)+i%6+3]);
        rotateTransition.setNode(tempL);
        rotateTransition.setAxis(Y_AXIS);
        rotateTransition.setByAngle(-90);
        //rotateTransition.setAutoReverse(false);
        rotateTransition.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            synchronized public void handle(ActionEvent event) {
                move_di();
                cubeXform.getChildren().removeAll(Xpart);
                cubeXform.getChildren().removeAll(tempL);
                cubeXform.getChildren().addAll(Xpart);
                animationRunning = false;
                checkIsDone();
            }
        });
        rotateTransition.play();
        cubeXform.getChildren().addAll(tempL);
    }

    private void move_di() {
        for (int i = 0; i < 3; i++) {
            move_D();
            move_E();
        }
    }

    @Override
    public void anim_bi() {
        if (animationRunning) return;
        animationRunning = true;
        RotateTransition rotateTransition = new RotateTransition();
        rotateTransition.setDuration(Duration.millis(1000));
        Group tempL = new Group();
        for(int i = 9; i < 27; i++) tempL.getChildren().addAll(Xpart[i]);
        rotateTransition.setNode(tempL);
        rotateTransition.setAxis(Z_AXIS);
        rotateTransition.setByAngle(+90);
        //rotateTransition.setAutoReverse(false);
        rotateTransition.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            synchronized public void handle(ActionEvent event) {
                move_bi();
                cubeXform.getChildren().removeAll(Xpart);
                cubeXform.getChildren().removeAll(tempL);
                cubeXform.getChildren().addAll(Xpart);
                animationRunning = false;
                checkIsDone();
            }
        });
        rotateTransition.play();
        cubeXform.getChildren().addAll(tempL);
    }

    private void move_bi() {
        move_S();
        for (int i = 0; i < 3; i++) {
            move_B();
        }
    }
}
