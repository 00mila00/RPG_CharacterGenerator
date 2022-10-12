package Controllers;

import Kodzik.Creator;
import Kodzik.Character;
import javafx.animation.FadeTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.AudioClip;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class ListWindowController {

    @FXML
    //PRIMARY STATISTICS
    public TextField k;
    public TextField odp;
    public TextField zr;
    public TextField ref;
    public TextField inte;
    public TextField per;
    public TextField odp2;
    public TextField sw;
    //SECONDARY STATISTICS
    public TextField s;
    public TextField wt;
    public TextField sz;
    public TextField ini;
    public TextField kon;
    public TextField pp;
    public TextField dow;
    public TextField ps;
    //THIRD STATISTICS
    public TextField zyw;
    public TextField szc;
    public TextField poc;

    private final Creator creator = new Creator();
    public AnchorPane mainAnchorPane;
    public ToggleButton nextButton;
    public ToggleButton prevButton;
    public Text page;
    public ToggleButton delButton;
    List<Character> characters = new LinkedList<>();
    private static int currentCharacter = 0;
    Character character;

    private final ObservableList<String> mountainType = FXCollections.observableArrayList(creator.getMountainPool());
    private ObservableList<String> raseType;
    private ObservableList<String> proffessionType;

    @FXML
    public ImageView characterImage;
    public ChoiceBox<String> mountainChoiceBox;
    public ChoiceBox<String> raseChoiceBox;
    public ChoiceBox<String> proffesionChoiceBox;
    public ToggleButton modifButton;
    public ToggleButton saveButton;
    public TextField nameField;

    @FXML
    private MainWindowController mainWindowController;

    public void setMainWindowController(MainWindowController mainWindowController) {
        this.mainWindowController = mainWindowController;
    }

    public void initialize() {

        try {
            creator.loadCharacters();
        } catch (IOException e) {
           setDisable();
           modifButton.setDisable(true);
           nextButton.setDisable(true);
           prevButton.setDisable(true);
           delButton.setDisable(true);
           characterImage.setImage(new Image("images/noCharacter.png"));
           return;
        }

        delButton.setDisable(true);
        page.setText(currentCharacter + 1 + "/" + creator.getAmountOfCharacters());
        characters = creator.getCharacters();

        if(creator.getAmountOfCharacters() > 0) {
            character = characters.get(0);
        }
        showCharacter(character);
        showFirstImage();
        setDisable();
        mountainChoiceBox.setItems(mountainType);

        //LISTENER DO NAME FIELD
        nameField.textProperty().addListener(((observableValue, s, t1) -> {
            if(t1.equals("Smoli") && raseChoiceBox.getSelectionModel().getSelectedItem().equals("Elfy")) {
                Image image = new Image("/images/elfPro.png");
                characterImage.setImage(image);
            }
            else if(t1.equals("Karbo") && raseChoiceBox.getSelectionModel().getSelectedItem().equals("Krasnoludy")) {
                Image image = new Image("/images/karbo.png");
                characterImage.setImage(image);
            }
            else {
                String rase = raseChoiceBox.getSelectionModel().getSelectedItem();
                if(rase.equals("Elfy")) {
                    Image image = null;
                    image = new Image("/images/elf.png");
                    characterImage.setImage(image);
                }
                else if(rase.equals("Krasnoludy")) {
                    Image image = null;
                    image = new Image("/images/krasnolud.png");
                    characterImage.setImage(image);
                }
                else if(rase.equals("Marienburg")) {
                    Image image = null;
                    image = new Image("/images/niziolek.png");
                    characterImage.setImage(image);
                }
                else {
                    Image image = new Image("/images/human.png");
                    characterFadeOut();
                    characterImage.setImage(image);
                    characterFadeIn();
                }
            }

        }));

        //LISTENER DO RASE TYPE
        raseChoiceBox.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                if(t1.equals(-1)) {
                    return;
                }
                String race = creator.getRacesPool().get(mountainChoiceBox.getSelectionModel().getSelectedIndex())[t1.intValue()];

                if(race.equals("Elfy")) {
                    Image image = null;
                    if(nameField.getText().equals("Smoli")) {
                        image = new Image("/images/elfPro.png");
                    }
                    else {
                        image = new Image("/images/elf.png");
                    }
                    characterFadeOut();
                    characterImage.setImage(image);
                    characterFadeIn();
                }
                else if(race.equals("Krasnoludy")) {
                    Image image = null;
                    if(nameField.getText().equals("Karbo")) {
                        image = new Image("/images/karbo.png");
                    }
                    else {
                        image = new Image("/images/krasnolud.png");
                    }
                    characterFadeOut();
                    characterImage.setImage(image);
                    characterFadeIn();
                }
                else if(race.equals("Marienburg")) {
                    Image image = new Image("/images/niziolek.png");
                    characterFadeOut();
                    characterImage.setImage(image);
                    characterFadeIn();
                }
                else {
                    Image image = new Image("/images/human.png");
                    characterFadeOut();
                    characterImage.setImage(image);
                    characterFadeIn();
                }
            }
        });

        //LISTENER DO MOUNTAIN
        mountainChoiceBox.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                if(t1.equals(-1)) {
                    return;
                }
                raseChoiceBox.setValue("");
                raseType = FXCollections.observableArrayList(creator.getRacesPool().get(t1.intValue()));
                raseChoiceBox.setItems(raseType);
            }
        });

        //LISTENER DO RASE
        raseChoiceBox.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                proffesionChoiceBox.setValue("");
                if(t1.equals(-1)) {
                    return;
                }
                int indexOfMountain = creator.indexOfMountain(mountainChoiceBox.getSelectionModel().getSelectedItem());
                proffessionType = FXCollections.observableArrayList(creator.getProfessionsPool().get(t1.intValue()
                        + creator.indexOfProffesion(indexOfMountain)));
                proffesionChoiceBox.setItems(proffessionType);
            }
        });

    }

    public void prevCharacter() {
        AudioClip diceSound = new AudioClip(this.getClass().getResource("/sounds/bookPageSound.mp3").toExternalForm());
        diceSound.play();
        if(saveButton.isVisible()) {
            saveButton.setVisible(false);
            modifButton.setVisible(true);
        }
        currentCharacter--;
        if(currentCharacter < 0) {
            currentCharacter = creator.getAmountOfCharacters() - 1;
        }
        character = characters.get(currentCharacter);
        showCharacter(character);
        page.setText(currentCharacter + 1 + "/" + creator.getAmountOfCharacters());
    }

    public void nextCharacter() {
        AudioClip diceSound = new AudioClip(this.getClass().getResource("/sounds/bookPageSound.mp3").toExternalForm());
        diceSound.play();
        if(saveButton.isVisible()) {
            saveButton.setVisible(false);
            modifButton.setVisible(true);
        }
        currentCharacter++;
        if(currentCharacter > creator.getAmountOfCharacters() - 1) {
            currentCharacter = 0;
        }
        character = characters.get(currentCharacter);
        showCharacter(character);
        page.setText(currentCharacter + 1 + "/" + creator.getAmountOfCharacters());
    }

    public void modifCharacter() {
        modifButton.setVisible(false);
        saveButton.setVisible(true);
        delButton.setDisable(false);
        setEnable();
    }

    public void deleteCharacter() {
        characters.remove(character);
        page.setText(currentCharacter + "/" + creator.getAmountOfCharacters());
        if(creator.getAmountOfCharacters() == 0) {
            setDisable();
            saveButton.setVisible(false);
            modifButton.setVisible(true);
            modifButton.setDisable(true);
            nextButton.setDisable(true);
            prevButton.setDisable(true);
            delButton.setDisable(true);
            creator.deleteFile();
            clear();
            characterImage.setImage(new Image("images/noCharacter.png"));
            return;
        } else if(currentCharacter == creator.getAmountOfCharacters()) {
            currentCharacter--;
        }
        character = characters.get(currentCharacter);
        showCharacter(character);
        delButton.setDisable(true);
        saveButton.setVisible(false);
        modifButton.setVisible(true);
    }

    public void saveCharacter() throws CloneNotSupportedException {
        saveButton.setVisible(false);
        modifButton.setVisible(true);
        setDisable();
        String name = nameField.getText();
        String rase = raseChoiceBox.getSelectionModel().getSelectedItem();
        String mountain = mountainChoiceBox.getSelectionModel().getSelectedItem();
        String proffesion = proffesionChoiceBox.getSelectionModel().getSelectedItem();

        Character tempCharacter = new Character();
        tempCharacter.setName(name);
        tempCharacter.setMountain(mountain);
        tempCharacter.setRace(rase);
        tempCharacter.setProfession(proffesion);

        if(name.equals("") || rase.equals("") || mountain.equals("") || proffesion.equals("")) {
            try {
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
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        } else if(creator.contains(tempCharacter)) {
            try {
                DialogWindowController.setMessageInfo("Taka postać już istnieje");
                FXMLLoader loader = new FXMLLoader();
                Parent root = loader.load(this.getClass().getResource("/DialogWindow.fxml").openStream());
                Stage stage = new Stage();
                stage.setTitle("Zagalopowałeś się");
                stage.initModality(Modality.WINDOW_MODAL);
                stage.initStyle(StageStyle.TRANSPARENT);
                stage.setScene(new Scene(root));
                stage.initOwner(mainAnchorPane.getScene().getWindow());
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
            showCharacter(character);
        } else{
            character.setName(name);
            character.setMountain(mountain);
            character.setRace(rase);
            character.setProfession(proffesion);
        }

    }

    private void showCharacter(Character character) {
        nameField.setText(character.getName());
        mountainChoiceBox.getSelectionModel().select(null);
        mountainChoiceBox.getSelectionModel().select(character.getMountain());
        raseChoiceBox.getSelectionModel().select(character.getRace());
        proffesionChoiceBox.getSelectionModel().select(character.getProfession());

        int[] primary = character.getPrimary();
        int[] secondary = character.getSecondary();

        k.setText(String.valueOf(primary[0]));
        odp.setText(String.valueOf(primary[1]));
        zr.setText(String.valueOf(primary[2]));
        ref.setText(String.valueOf(primary[3]));
        inte.setText(String.valueOf(primary[4]));
        per.setText(String.valueOf(primary[5]));
        odp2.setText(String.valueOf(primary[6]));
        sw.setText(String.valueOf(primary[7]));

        zyw.setText(String.valueOf(secondary[0]));
        s.setText(String.valueOf(secondary[1]));
        wt.setText(String.valueOf(secondary[2]));
        sz.setText(String.valueOf(secondary[3]));
        ini.setText(String.valueOf(secondary[4]));
        kon.setText(String.valueOf(secondary[5]));
        pp.setText(String.valueOf(secondary[6]));
        dow.setText(String.valueOf(secondary[7]));
        ps.setText(String.valueOf(secondary[8]));

        szc.setText(String.valueOf(character.getLuck()));
        poc.setText(String.valueOf(character.getSanity()));
    }

    private void clear() {
        nameField.setText("");
        mountainChoiceBox.setValue("");
        raseChoiceBox.setValue("");
        proffesionChoiceBox.setValue("");

        k.setText("");
        odp.setText("");
        zr.setText("");
        ref.setText("");
        inte.setText("");
        per.setText("");
        odp2.setText("");
        sw.setText("");

        zyw.setText("");
        s.setText("");
        wt.setText("");
        sz.setText("");
        ini.setText("");
        kon.setText("");
        pp.setText("");
        dow.setText("");
        ps.setText("");

        szc.setText("");
        poc.setText("");
    }

    private void setDisable() {
        proffesionChoiceBox.setDisable(true);
        proffesionChoiceBox.setOpacity(1);
        raseChoiceBox.setDisable(true);
        raseChoiceBox.setOpacity(1);
        mountainChoiceBox.setDisable(true);
        mountainChoiceBox.setOpacity(1);
        nameField.setDisable(true);
        nameField.setOpacity(1);
        delButton.setDisable(true);
        k.setDisable(true);
        k.setOpacity(1);
        odp.setDisable(true);
        odp.setOpacity(1);
        zr.setDisable(true);
        zr.setOpacity(1);
        ref.setDisable(true);
        ref.setOpacity(1);
        inte.setDisable(true);
        inte.setOpacity(1);
        per.setDisable(true);
        per.setOpacity(1);
        odp2.setDisable(true);
        odp2.setOpacity(1);
        sw.setDisable(true);
        sw.setOpacity(1);
        s.setDisable(true);
        s.setOpacity(1);
        wt.setDisable(true);
        wt.setOpacity(1);
        sz.setDisable(true);
        sz.setOpacity(1);
        ini.setDisable(true);
        ini.setOpacity(1);
        kon.setDisable(true);
        kon.setOpacity(1);
        pp.setDisable(true);
        pp.setOpacity(1);
        dow.setDisable(true);
        dow.setOpacity(1);
        ps.setDisable(true);
        ps.setOpacity(1);
        zyw.setDisable(true);
        zyw.setOpacity(1);
        szc.setDisable(true);
        szc.setOpacity(1);
        poc.setDisable(true);
        poc.setOpacity(1);
    }

    private void setEnable() {
        proffesionChoiceBox.setDisable(false);
        raseChoiceBox.setDisable(false);
        mountainChoiceBox.setDisable(false);
        nameField.setDisable(false);
    }

    private void characterFadeIn(){
        FadeTransition ft = new FadeTransition();
        ft.setNode(characterImage);
        ft.setDuration(new Duration(700));
        ft.setFromValue(0.0);
        ft.setToValue(1.0);
        ft.play();
    }

    private void characterFadeOut(){
        FadeTransition ft = new FadeTransition();
        ft.setNode(characterImage);
        ft.setDuration(new Duration(700));
        ft.setFromValue(1.0);
        ft.setToValue(0.0);
        ft.play();
    }

    @FXML
    public void previous() {
        if(creator.getAmountOfCharacters() > 0) {
            creator.saveCharacters();
        }
        currentCharacter = 0;
        mainWindowController.loadMenuScreen();
    }

    private void showFirstImage() {
        if(nameField.getText().equals("Smoli") && raseChoiceBox.getSelectionModel().getSelectedItem().equals("Elfy")) {
            Image image = new Image("/images/elfPro.png");
            characterImage.setImage(image);
        }
        else if(nameField.getText().equals("Karbo") && raseChoiceBox.getSelectionModel().getSelectedItem().equals("Krasnoludy")) {
            Image image = new Image("/images/karbo.png");
            characterImage.setImage(image);
        }
        else {
            String rase = raseChoiceBox.getSelectionModel().getSelectedItem();
            if(rase.equals("Elfy")) {
                Image image = null;
                image = new Image("/images/elf.png");
                characterImage.setImage(image);
            }
            else if(rase.equals("Krasnoludy")) {
                Image image = null;
                image = new Image("/images/krasnolud.png");
                characterImage.setImage(image);
            }
            else if(rase.equals("Marienburg")) {
                Image image = null;
                image = new Image("/images/niziolek.png");
                characterImage.setImage(image);
            }
            else {
                Image image = new Image("/images/human.png");
                characterFadeOut();
                characterImage.setImage(image);
                characterFadeIn();
            }
        }
    }

}
