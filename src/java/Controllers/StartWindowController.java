package Controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.media.AudioClip;

import java.io.IOException;

public class StartWindowController {

    static boolean isFirstOpen = true;
    static boolean isPlaying = true;

    @FXML
    public ImageView speakerImage;

    @FXML
    private MainWindowController mainWindowController;

    @FXML
    AudioClip bgSound = new AudioClip(this.getClass().getResource("/sounds/Horizon.mp3").toExternalForm());
    AudioClip bgSound2 = new AudioClip(this.getClass().getResource("/sounds/Horizon2.mp3").toExternalForm());

    {
        if(isFirstOpen) {
            bgSound.play(0.5);
            isPlaying = true;
            isFirstOpen = false;
        }
        else {
            if(isPlaying) {
                bgSound2.stop();
                bgSound.play(0.5);
            }
        }
    }

    public void generateCharacter() {
        if(!isMuted()) {
            bgSound.stop();
            bgSound2.play(0.5);
            isPlaying = true;
        }
        else {
            isPlaying = false;
        }

        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/CardWindow.fxml"));
        Pane pane = null;
        try {
            pane = loader.load();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        CardWindowController cardWindowController = loader.getController();
        cardWindowController.setMainWindowController(mainWindowController);
        mainWindowController.setScreen(pane);
    }

    public void displayCharacter() {
        if(!isMuted()) {
            bgSound.stop();
            bgSound2.play(0.5);
            isPlaying = true;
        }
        else {
            isPlaying = false;
        }

        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/ListWindow.fxml"));
        Pane pane = null;
        try {
            pane = loader.load();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        ListWindowController listWindowController = loader.getController();
        listWindowController.setMainWindowController(mainWindowController);
        mainWindowController.setScreen(pane);
    }

    public void rollTheDice() {
        bgSound.stop();
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/DiceWindow.fxml"));
        Pane pane = null;
        try {
            pane = loader.load();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        DiceWindowController diceWindowController = loader.getController();
        diceWindowController.setMainWindowController(mainWindowController);
        mainWindowController.setScreen(pane);
    }

    public void muteVolume() {
        String type = speakerImage.getImage().getUrl();
        if(type.contains("soundOff")) {
            speakerImage.setImage(new Image("/images/soundOn.png"));
            bgSound.play(0.5);
            isPlaying = true;
        }
        else {
            speakerImage.setImage(new Image("/images/soundOff.png"));
            bgSound.stop();
            isPlaying = false;
        }
    }

    private boolean isMuted() {
        return speakerImage.getImage().getUrl().contains("soundOff");
    }


    public void setMainWindowController(MainWindowController mainWindowController) {
        this.mainWindowController = mainWindowController;
        if(!isPlaying) {
            speakerImage.setImage(new Image("/images/soundOff.png"));
        }
    }

    public void exit() {
        Platform.exit();
    }

}
