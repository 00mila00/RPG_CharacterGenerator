package Controllers;

import javafx.scene.control.ToggleButton;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class DialogWindowController {

    public ToggleButton okButton;
    public Text message;
    public static String messageInfo;

    public void initialize() {
        message.setText(messageInfo);
    }

    public static void setMessageInfo(String messageInfo) {
        DialogWindowController.messageInfo = messageInfo;
    }

    public void exit() {
        Stage stage = (Stage) okButton.getScene().getWindow();
        stage.close();
    }

}
