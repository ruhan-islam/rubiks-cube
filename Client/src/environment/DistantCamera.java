package environment;

import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.transform.Translate;
import util.Xform;

import static javafx.scene.transform.Rotate.Y_AXIS;
import static sample.Main.screenHeight;

public class DistantCamera {
    double CAMERA_INITIAL_DISTANCE = -1152;
    final double CAMERA_INITIAL_X_ANGLE = 25.0;
    final double CAMERA_INITIAL_Y_ANGLE = 25.0;

    double x_angle = CAMERA_INITIAL_X_ANGLE;
    double y_angle = CAMERA_INITIAL_Y_ANGLE;

    private final double CAMERA_NEAR_CLIP = 10.0;
    private final double CAMERA_FAR_CLIP = 1000000.0;

    PerspectiveCamera camera;
    Xform cameraXform;
    Xform cameraXform2;
    Xform cameraXform3;

    public DistantCamera(Group root) {
        camera = new PerspectiveCamera(true);
        cameraXform = new Xform();
        cameraXform2 = new Xform();
        cameraXform3 = new Xform();

        //System.out.println("buildCamera()");
        root.getChildren().add(cameraXform);
        cameraXform.getChildren().add(cameraXform2);
        cameraXform2.getChildren().add(cameraXform3);
        cameraXform3.getChildren().add(camera);
        cameraXform3.setRotateZ(180.0);

        camera.setNearClip(CAMERA_NEAR_CLIP);
        camera.setFarClip(CAMERA_FAR_CLIP);
        camera.setTranslateZ(CAMERA_INITIAL_DISTANCE);
        cameraXform.ry.setAngle(CAMERA_INITIAL_Y_ANGLE);
        cameraXform.rx.setAngle(CAMERA_INITIAL_X_ANGLE);
    }

    public void rotateY(double angle) {
        y_angle += angle;
        cameraXform.ry.setAngle(y_angle);
    }

    public PerspectiveCamera getDistantCamera() {
        return camera;
    }
}
