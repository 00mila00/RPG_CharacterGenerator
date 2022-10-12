package Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.AudioClip;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Random;

import Kodzik.Bones;

public class DiceWindowController {

    private final Random random = new Random();
    public Text rollResult;
    public TextField diceNumber;
    public AnchorPane mainAnchorPane;
    public TextField amoutOfNumber;

    @FXML
    private MainWindowController mainWindowController;


    @FXML
    public void rollTheDice() throws IOException {

        int diceNum;
        int amount;
        try{
            diceNum = Integer.parseInt(diceNumber.getText());
            amount = Integer.parseInt(amoutOfNumber.getText());
        }  catch (NumberFormatException e) {
            DialogWindowController.setMessageInfo("Zagalopowałeś się");
            FXMLLoader loader = new FXMLLoader();
            Parent root = loader.load(this.getClass().getResource("/DialogWindow.fxml").openStream());
            Stage stage = new Stage();
            stage.setTitle("Zagalopowałeś się");
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.setScene(new Scene(root));
            stage.initOwner(mainAnchorPane.getScene().getWindow());
            stage.show();
            return;
        }


        AudioClip diceSound = new AudioClip(this.getClass().getResource("/sounds/diceSound.mp3").toExternalForm());
        diceSound.play();

        String result = Bones.gererate(amount, diceNum);
        rollResult.setText(result);

    }


    @FXML
    public void previous() {
        mainWindowController.loadMenuScreen();
    }

    public void setMainWindowController(MainWindowController mainWindowController) {
        this.mainWindowController = mainWindowController;
    }


}
