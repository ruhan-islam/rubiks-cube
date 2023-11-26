/*
 * Sample Skeleton for 'history.fxml' Controller Class
 */

package control;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import client.Client;
import design.Selection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import util.NetworkUtil;

public class historyController {
    private NetworkUtil nc = Client.client.nc;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="text"
    private TextArea text; // Value injected by FXMLLoader

    @FXML // fx:id="closeButton"
    private Button closeButton; // Value injected by FXMLLoader

    @FXML
    void closeButtonPressed(ActionEvent event) {
        try {
            nc.write("close");
            Selection.showSelectionPage();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert text != null : "fx:id=\"text\" was not injected: check your FXML file 'history.fxml'.";
        assert closeButton != null : "fx:id=\"closeButton\" was not injected: check your FXML file 'history.fxml'.";

        List<String> userinfo = selectionController.userinfo;


        text.setEditable(false);
        //text.prefWidthProperty().bind(hbox.widthProperty());

        text.setWrapText(true);     // New line of the text exceeds the text area
        text.setText("\n");

        
        int i = -1;
        for (String str : userinfo) {
            if (i > 0) {
                text.appendText("game # " + i + " : ");
            }
            text.appendText(str);
            text.appendText("\n");
            i++;
        }
    }
}
