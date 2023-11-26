package environment;

import client.Client;
import control.playController;
import design.Play;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;

import static design.Play.cubes;
import static environment.Camera.*;
import static environment.Axis.axisGroup;

public enum Keyboard {
    ;
    public static boolean animationRunning;
    public static int cubeID = 0;

    public static void handleKeyboard(Scene scene, final Node root) {
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (playController.solved) return;
                switch (event.getCode()) {
                    case W: {
                        axisGroup.setVisible(!axisGroup.isVisible());
                        break;
                    }
                    case R: {
                        if(event.isControlDown() & event.isShiftDown()) {
                            if (Play.total > 1) {
                                Client.client.nc.write(Client.client.IDserial + ",ri");
                                break;
                            }
                            Play.cubes[cubeID].anim_ri();
                            break;
                        }
                        else if (event.isControlDown()) {
                            if (Play.total > 1) {
                                Client.client.nc.write(Client.client.IDserial + ",r");
                                break;
                            }
                            Play.cubes[cubeID].anim_r();
                            break;
                        }
                        else if (event.isShiftDown()) {
                            if (Play.total > 1) {
                                Client.client.nc.write(Client.client.IDserial + ",RI");
                                break;
                            }
                            Play.cubes[cubeID].anim_RI();
                            break;
                        }
                        else {
                            if (Play.total > 1) {
                                Client.client.nc.write(Client.client.IDserial + ",R");
                                break;
                            }
                            Play.cubes[cubeID].anim_R();
                            break;
                        }
                    }
                    case U: {
                        if(event.isControlDown() & event.isShiftDown()) {
                            if (Play.total > 1) {
                                Client.client.nc.write(Client.client.IDserial + ",ui");
                                break;
                            }
                            Play.cubes[cubeID].anim_ui();
                            break;
                        }
                        else if (event.isControlDown()) {
                            if (Play.total > 1) {
                                Client.client.nc.write(Client.client.IDserial + ",u");
                                break;
                            }
                            Play.cubes[cubeID].anim_u();
                            break;
                        }
                        else if (event.isShiftDown()) {
                            if (Play.total > 1) {
                                Client.client.nc.write(Client.client.IDserial + ",UI");
                                break;
                            }
                            Play.cubes[cubeID].anim_UI();
                            break;
                        }
                        else {
                            if (Play.total > 1) {
                                Client.client.nc.write(Client.client.IDserial + ",U");
                                break;
                            }
                            Play.cubes[cubeID].anim_U();
                            break;
                        }
                    }
                    case F: {
                        if(event.isControlDown() & event.isShiftDown()) {
                            if (Play.total > 1) {
                                Client.client.nc.write(Client.client.IDserial + ",fi");
                                break;
                            }
                            Play.cubes[cubeID].anim_fi();
                            break;
                        }
                        else if (event.isControlDown()) {
                            if (Play.total > 1) {
                                Client.client.nc.write(Client.client.IDserial + ",f");
                                break;
                            }
                            Play.cubes[cubeID].anim_f();
                            break;
                        }
                        else if (event.isShiftDown()) {
                            if (Play.total > 1) {
                                Client.client.nc.write(Client.client.IDserial + ",FI");
                                break;
                            }
                            Play.cubes[cubeID].anim_FI();
                            break;
                        }
                        else {
                            if (Play.total > 1) {
                                Client.client.nc.write(Client.client.IDserial + ",F");
                                break;
                            }
                            Play.cubes[cubeID].anim_F();
                            break;
                        }
                    }
                    case L: {
                        if(event.isControlDown() & event.isShiftDown()) {
                            if (Play.total > 1) {
                                Client.client.nc.write(Client.client.IDserial + ",li");
                                break;
                            }
                            Play.cubes[cubeID].anim_li();
                            break;
                        }
                        else if (event.isControlDown()) {
                            if (Play.total > 1) {
                                Client.client.nc.write(Client.client.IDserial + ",l");
                                break;
                            }
                            Play.cubes[cubeID].anim_l();
                            break;
                        }
                        else if (event.isShiftDown()) {
                            if (Play.total > 1) {
                                Client.client.nc.write(Client.client.IDserial + ",LI");
                                break;
                            }
                            Play.cubes[cubeID].anim_LI();
                            break;
                        }
                        else {
                            if (Play.total > 1) {
                                Client.client.nc.write(Client.client.IDserial + ",L");
                                break;
                            }
                            Play.cubes[cubeID].anim_L();
                            break;
                        }
                    }
                    case D: {
                        if(event.isControlDown() & event.isShiftDown()) {
                            if (Play.total > 1) {
                                Client.client.nc.write(Client.client.IDserial + ",di");
                                break;
                            }
                            Play.cubes[cubeID].anim_di();
                            break;
                        }
                        else if (event.isControlDown()) {
                            if (Play.total > 1) {
                                Client.client.nc.write(Client.client.IDserial + ",d");
                                break;
                            }
                            Play.cubes[cubeID].anim_d();
                            break;
                        }
                        else if (event.isShiftDown()) {
                            if (Play.total > 1) {
                                Client.client.nc.write(Client.client.IDserial + ",DI");
                                break;
                            }
                            Play.cubes[cubeID].anim_DI();
                            break;
                        }
                        else {
                            if (Play.total > 1) {
                                Client.client.nc.write(Client.client.IDserial + ",D");
                                break;
                            }
                            Play.cubes[cubeID].anim_D();
                            break;
                        }
                    }
                    case B: {
                        if(event.isControlDown() & event.isShiftDown()) {
                            if (Play.total > 1) {
                                Client.client.nc.write(Client.client.IDserial + ",bi");
                                break;
                            }
                            Play.cubes[cubeID].anim_bi();
                            break;
                        }
                        else if (event.isControlDown()) {
                            if (Play.total > 1) {
                                Client.client.nc.write(Client.client.IDserial + ",b");
                                break;
                            }
                            Play.cubes[cubeID].anim_b();
                            break;
                        }
                        else if (event.isShiftDown()) {
                            if (Play.total > 1) {
                                Client.client.nc.write(Client.client.IDserial + ",BI");
                                break;
                            }
                            Play.cubes[cubeID].anim_BI();
                            break;
                        }
                        else {
                            if (Play.total > 1) {
                                Client.client.nc.write(Client.client.IDserial + ",B");
                                break;
                            }
                            Play.cubes[cubeID].anim_B();
                            break;
                        }
                    }
                    case M: {
                        if (event.isShiftDown()) {
                            if (Play.total > 1) {
                                Client.client.nc.write(Client.client.IDserial + ",MI");
                                break;
                            }
                            Play.cubes[cubeID].anim_MI();
                            break;
                        }
                        else {
                            if (Play.total > 1) {
                                Client.client.nc.write(Client.client.IDserial + ",M");
                                break;
                            }
                            Play.cubes[cubeID].anim_M();
                            break;
                        }
                    }
                    case E: {
                        if (event.isShiftDown()) {
                            if (Play.total > 1) {
                                Client.client.nc.write(Client.client.IDserial + ",EI");
                                break;
                            }
                            Play.cubes[cubeID].anim_EI();
                            break;
                        }
                        else {
                            if (Play.total > 1) {
                                Client.client.nc.write(Client.client.IDserial + ",E");
                                break;
                            }
                            Play.cubes[cubeID].anim_E();
                            break;
                        }
                    }
                    case S: {
                        if (event.isShiftDown()) {
                            if (Play.total > 1) {
                                Client.client.nc.write(Client.client.IDserial + ",SI");
                                break;
                            }
                            Play.cubes[cubeID].anim_SI();
                            break;
                        }
                        else {
                            if (Play.total > 1) {
                                Client.client.nc.write(Client.client.IDserial + ",S");
                                break;
                            }
                            Play.cubes[cubeID].anim_S();
                            break;
                        }
                    }
                    case X: {
                        if (event.isShiftDown()) {
                            if (Play.total > 1) {
                                Client.client.nc.write(Client.client.IDserial + ",xi");
                                break;
                            }
                            Play.cubes[cubeID].anim_xi();
                            break;
                        }
                        else {
                            if (Play.total > 1) {
                                Client.client.nc.write(Client.client.IDserial + ",x");
                                break;
                            }
                            Play.cubes[cubeID].anim_x();
                            break;
                        }
                    }
                    case Y: {
                        if (event.isShiftDown()) {
                            if (Play.total > 1) {
                                Client.client.nc.write(Client.client.IDserial + ",yi");
                                break;
                            }
                            Play.cubes[cubeID].anim_yi();
                            break;
                        }
                        else {
                            if (Play.total > 1) {
                                Client.client.nc.write(Client.client.IDserial + ",y");
                                break;
                            }
                            Play.cubes[cubeID].anim_y();
                            break;
                        }
                    }
                    case Z: {
                        if (event.isShiftDown()) {
                            if (Play.total > 1) {
                                Client.client.nc.write(Client.client.IDserial + ",zi");
                                break;
                            }
                            Play.cubes[cubeID].anim_zi();
                            break;
                        }
                        else {
                            if (Play.total > 1) {
                                Client.client.nc.write(Client.client.IDserial + ",z");
                                break;
                            }
                            Play.cubes[cubeID].anim_z();
                            break;
                        }
                    }
                }
            }
        });
    }
}
