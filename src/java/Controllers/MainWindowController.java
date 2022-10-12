package Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class MainWindowController {

    @FXML
    public AnchorPane mainAnchorPane;

    @FXML
    public void initialize() {
        loadMenuScreen();
    }

    public void loadMenuScreen() {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/StartWindow.fxml"));
        Pane pane = null;
        try {
            pane = loader.load();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        StartWindowController startWindowController = loader.getController();
        startWindowController.setMainWindowController(this);
        setScreen(pane);
    }

    public void setScreen(Pane pane) {
        mainAnchorPane.getChildren().clear();
        mainAnchorPane.getChildren().add(pane);
    }
}
