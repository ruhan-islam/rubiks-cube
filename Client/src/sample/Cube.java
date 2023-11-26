package sample;

import environment.Camera;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;

public abstract class Cube {
    private static final double screenWidth = Main.screenWidth;
    private static final double screenHeight = Main.screenHeight;

    final private static double cubeLength = screenHeight / 8.0;
    final private static double stickerDepth = cubeLength / 100.0 * 4;
    final private static double stickerLength = cubeLength * 0.98;
    
    public  Group cubeXform;
    protected  Group [] Xpart;

    protected  Box [] frontSticker;
    protected  Box [] rightSticker;
    protected  Box [] leftSticker;
    protected  Box [] upSticker;
    protected  Box [] downSticker;
    protected  Box [] backSticker;

    public Cube() {
        cubeXform = new Group();
        Xpart = new Group[27];
        
        Box [] cube = new Box[27];

        frontSticker = new Box[9];
        rightSticker = new Box[9];
        leftSticker = new Box[9];
        upSticker = new Box[9];
        downSticker = new Box[9];
        backSticker = new Box[9];
        
        
        final PhongMaterial bodyMaterial = new PhongMaterial();
        bodyMaterial.setDiffuseColor(Color.BLACK);
        bodyMaterial.setSpecularColor(Color.GREY);

        final PhongMaterial frontMaterial = new PhongMaterial();
        frontMaterial.setDiffuseColor(Color.RED);
        //frontMaterial.setSpecularColor(Color.RED);

        final PhongMaterial rightMaterial = new PhongMaterial();
        rightMaterial.setDiffuseColor(Color.GREEN);
        //rightMaterial.setSpecularColor(Color.GREEN);

        final PhongMaterial leftMaterial = new PhongMaterial();
        leftMaterial.setDiffuseColor(Color.BLUE);
        //leftMaterial.setSpecularColor(Color.BLUE);

        final PhongMaterial backMaterial = new PhongMaterial();
        backMaterial.setDiffuseColor(Color.CHOCOLATE);
        //backMaterial.setSpecularColor(Color.DEEPPINK);

        final PhongMaterial upMaterial = new PhongMaterial();
        upMaterial.setDiffuseColor(Color.YELLOW);
        //upMaterial.setSpecularColor(Color.YELLOW);

        final PhongMaterial downMaterial = new PhongMaterial();
        downMaterial.setDiffuseColor(Color.WHITE);
        //downMaterial.setSpecularColor(Color.WHITE);

        for(int i = 0; i < cube.length; i++) {
            cube[i] = new Box();
            cube[i].setWidth(cubeLength);
            cube[i].setHeight(cubeLength);
            cube[i].setDepth(cubeLength);
            cube[i].setMaterial(bodyMaterial);
            cube[i].setTranslateZ(+(i/9-1) * cubeLength);
            cube[i].setTranslateX(-(i%3-1) * cubeLength);
            cube[i].setTranslateY(-((i%9)/3-1) * cubeLength);
        }

        for(int i = 0; i < frontSticker.length; i++) {
            frontSticker[i] = new Box();
            frontSticker[i].setWidth(stickerLength);
            frontSticker[i].setHeight(stickerLength);
            frontSticker[i].setDepth(stickerDepth);
            frontSticker[i].setMaterial(frontMaterial);
            frontSticker[i].setTranslateZ(-1.5 * cubeLength);
            frontSticker[i].setTranslateX(-(i%3-1) * cubeLength);
            frontSticker[i].setTranslateY(-(i/3-1) * cubeLength);
        }

        for(int i = 0; i < rightSticker.length; i++) {
            rightSticker[i] = new Box();
            rightSticker[i].setWidth(stickerDepth);
            rightSticker[i].setHeight(stickerLength);
            rightSticker[i].setDepth(stickerLength);
            rightSticker[i].setMaterial(rightMaterial);
            rightSticker[i].setTranslateX(-1.5 * cubeLength);
            rightSticker[i].setTranslateY(-(i/3-1) * cubeLength);
            rightSticker[i].setTranslateZ(+(i%3-1) * cubeLength);
        }

        for(int i = 0; i < leftSticker.length; i++) {
            leftSticker[i] = new Box();
            leftSticker[i].setWidth(stickerDepth);
            leftSticker[i].setHeight(stickerLength);
            leftSticker[i].setDepth(stickerLength);
            leftSticker[i].setMaterial(leftMaterial);
            leftSticker[i].setTranslateX(+1.5 * cubeLength);
            leftSticker[i].setTranslateY(-(i/3-1) * cubeLength);
            leftSticker[i].setTranslateZ(-(i%3-1) * cubeLength);
        }

        for(int i = 0; i < upSticker.length; i++) {
            upSticker[i] = new Box();
            upSticker[i].setWidth(stickerLength);
            upSticker[i].setHeight(stickerDepth);
            upSticker[i].setDepth(stickerLength);
            upSticker[i].setMaterial(upMaterial);
            upSticker[i].setTranslateY(+1.5 * cubeLength);
            upSticker[i].setTranslateZ(-(i/3-1) * cubeLength);
            upSticker[i].setTranslateX(-(i%3-1) * cubeLength);
        }

        for(int i = 0; i < downSticker.length; i++) {
            downSticker[i] = new Box();
            downSticker[i].setWidth(stickerLength);
            downSticker[i].setHeight(stickerDepth);
            downSticker[i].setDepth(stickerLength);
            downSticker[i].setMaterial(downMaterial);
            downSticker[i].setTranslateY(-1.5 * cubeLength);
            downSticker[i].setTranslateZ(+(i/3-1) * cubeLength);
            downSticker[i].setTranslateX(-(i%3-1) * cubeLength);
        }

        for(int i = 0; i < backSticker.length; i++) {
            backSticker[i] = new Box();
            backSticker[i].setWidth(stickerLength);
            backSticker[i].setHeight(stickerLength);
            backSticker[i].setDepth(stickerDepth);
            backSticker[i].setMaterial(backMaterial);
            backSticker[i].setTranslateZ(+1.5 * cubeLength);
            backSticker[i].setTranslateX(+(i%3-1) * cubeLength);
            backSticker[i].setTranslateY(-(i/3-1) * cubeLength);
        }

        for(int i = 0; i < Xpart.length; i++) {
            Xpart[i] = new Group();
        }

        Xpart[0].getChildren().addAll(cube[0], frontSticker[0], upSticker[6], leftSticker[2]);
        Xpart[1].getChildren().addAll(cube[1], frontSticker[1], upSticker[7]);
        Xpart[2].getChildren().addAll(cube[2], frontSticker[2], upSticker[8], rightSticker[0]);
        Xpart[3].getChildren().addAll(cube[3], frontSticker[3], leftSticker[5]);
        Xpart[4].getChildren().addAll(cube[4], frontSticker[4]);
        Xpart[5].getChildren().addAll(cube[5], frontSticker[5], rightSticker[3]);
        Xpart[6].getChildren().addAll(cube[6], frontSticker[6], leftSticker[8], downSticker[0]);
        Xpart[7].getChildren().addAll(cube[7], frontSticker[7], downSticker[1]);
        Xpart[8].getChildren().addAll(cube[8], frontSticker[8], rightSticker[6], downSticker[2]);
        
        Xpart[9].getChildren().addAll(cube[9], upSticker[3], leftSticker[1]);
        Xpart[10].getChildren().addAll(cube[10], upSticker[4]);
        Xpart[11].getChildren().addAll(cube[11], upSticker[5], rightSticker[1]);
        Xpart[12].getChildren().addAll(cube[12], leftSticker[4]);
        Xpart[13].getChildren().addAll(cube[13]);
        Xpart[14].getChildren().addAll(cube[14], rightSticker[4]);
        Xpart[15].getChildren().addAll(cube[15], leftSticker[7], downSticker[3]);
        Xpart[16].getChildren().addAll(cube[16], downSticker[4]);
        Xpart[17].getChildren().addAll(cube[17], rightSticker[7], downSticker[5]);
        
        Xpart[18].getChildren().addAll(cube[18], backSticker[2], upSticker[0], leftSticker[0]);
        Xpart[19].getChildren().addAll(cube[19], backSticker[1], upSticker[1]);
        Xpart[20].getChildren().addAll(cube[20], backSticker[0], upSticker[2], rightSticker[2]);
        Xpart[21].getChildren().addAll(cube[21], backSticker[5], leftSticker[3]);
        Xpart[22].getChildren().addAll(cube[22], backSticker[4]);
        Xpart[23].getChildren().addAll(cube[23], backSticker[3], rightSticker[5]);
        Xpart[24].getChildren().addAll(cube[24], backSticker[8], leftSticker[6], downSticker[6]);
        Xpart[25].getChildren().addAll(cube[25], backSticker[7], downSticker[7]);
        Xpart[26].getChildren().addAll(cube[26], backSticker[6], rightSticker[8], downSticker[8]);

        cubeXform.getChildren().addAll(Xpart);
    }

    abstract public void arrowRight(Camera camera);
    abstract public void arrowLeft(Camera camera);
    abstract public void arrowUp();
    abstract public void arrowDown();

    abstract public void DistantArrowRight(int participant);
    abstract public void DistantArrowLeft(int participant);

    abstract public void checkIsDone();
    abstract public void scramble(int [] ints, int start);

    abstract public void anim_R();
    abstract public void anim_U();
    abstract public void anim_F();
    abstract public void anim_L();
    abstract public void anim_D();
    abstract public void anim_B();
    abstract public void anim_M();
    abstract public void anim_E();
    abstract public void anim_S();
    abstract public void anim_x();
    abstract public void anim_y();
    abstract public void anim_z();
    abstract public void anim_r();
    abstract public void anim_u();
    abstract public void anim_f();
    abstract public void anim_l();
    abstract public void anim_d();
    abstract public void anim_b();

    abstract public void anim_RI();
    abstract public void anim_UI();
    abstract public void anim_FI();
    abstract public void anim_LI();
    abstract public void anim_DI();
    abstract public void anim_BI();
    abstract public void anim_MI();
    abstract public void anim_EI();
    abstract public void anim_SI();
    abstract public void anim_xi();
    abstract public void anim_yi();
    abstract public void anim_zi();
    abstract public void anim_ri();
    abstract public void anim_ui();
    abstract public void anim_fi();
    abstract public void anim_li();
    abstract public void anim_di();
    abstract public void anim_bi();


//    public static Group getCube() {
//        buildCube();
//        return cubeXform;
//    }
}
