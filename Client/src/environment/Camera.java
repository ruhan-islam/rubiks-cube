package environment;

import javafx.scene.Group;
import util.Xform;
import javafx.scene.PerspectiveCamera;

public class Camera {

    double CAMERA_INITIAL_DISTANCE;
    private final double CAMERA_INITIAL_X_ANGLE = 25.0;
    private final double CAMERA_INITIAL_Y_ANGLE = 25.0;

    double x_angle = CAMERA_INITIAL_X_ANGLE;
    double y_angle = CAMERA_INITIAL_Y_ANGLE;

    private final double CAMERA_NEAR_CLIP = 10.0;
    private final double CAMERA_FAR_CLIP = 1000000.0;
    
    PerspectiveCamera camera;
    Xform cameraXform, cameraXform2, cameraXform3;
    
    public Camera(Group root, double distance) {
        CAMERA_INITIAL_DISTANCE = distance * -1;

        camera = new PerspectiveCamera(true);
        cameraXform = new Xform();
        cameraXform2 = new Xform();
        cameraXform3 = new Xform();

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

    public void rotateX(double angle) {
        x_angle += angle;
        cameraXform.rx.setAngle(x_angle);
    }

    public void rotateY(double angle) {
        y_angle += angle;
        cameraXform.ry.setAngle(y_angle);
    }

    public PerspectiveCamera getCamera() {
        return camera;
    }
}
