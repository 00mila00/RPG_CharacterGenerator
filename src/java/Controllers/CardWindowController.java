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
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import java.io.IOException;

public class CardWindowController {

    private Creator creator = new Creator();
    private Character character = new Character();

    @FXML
    public AnchorPane anchorPane;

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

    @FXML
    private MainWindowController mainWindowController;

    @FXML
    public TextField nameField;

    @FXML
    public ChoiceBox<String> raseChoiceBox;
    public ChoiceBox<String> proffesionChoiceBox;
    public ChoiceBox<String> mountainChoicBox;

    @FXML
    public CheckBox nameCheckBox;
    public CheckBox professionCheckBox;
    public CheckBox raseCheckBox;
    public CheckBox mountainCheckBox;

    @FXML
    private ImageView characterImage;

    @FXML
    private ObservableList<String> raseType;
    private ObservableList<String> mountainType;
    private ObservableList<String> proffesionType;

    @FXML
    private void initialize() {

        try {
            creator.loadCharacters();
        } catch (IOException e) {
        }

        mountainType = FXCollections.observableArrayList(creator.getMountainPool());

        raseChoiceBox.setValue("");
        proffesionChoiceBox.setValue("");
        mountainChoicBox.setValue("");

        proffesionChoiceBox.setDisable(true);
        raseChoiceBox.setDisable(true);

        nameCheckBox.setDisable(true);
        mountainCheckBox.setDisable(true);
        raseCheckBox.setDisable(true);
        professionCheckBox.setDisable(true);

        mountainChoicBox.setItems(mountainType);

        setStatisticsDisable();

        //LISTENER DO NAME FIELD
        nameField.textProperty().addListener(((observableValue, s, t1) -> {
            nameCheckBox.setDisable(false);
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
                else if(rase.equals("Hochland")) {
                    Image image = null;
                    image = new Image("/images/whochland.png");
                    characterImage.setImage(image);
                }
                else if(rase.equals("Averland")) {
                    Image image = null;
                    image = new Image("/images/averland.png");
                    characterImage.setImage(image);
                }
                else if(rase.equals("Altdorf")) {
                    Image image = null;
                    image = new Image("/images/altdorf.png");
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
                String race = creator.getRacesPool().get(mountainChoicBox.getSelectionModel().getSelectedIndex())[t1.intValue()];
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
                else if(race.equals("Hochland")) {
                    Image image = new Image("/images/whochland.png");
                    characterFadeOut();
                    characterImage.setImage(image);
                    characterFadeIn();
                }
                else if(race.equals("Averland")) {
                    Image image = new Image("/images/averland.png");
                    characterFadeOut();
                    characterImage.setImage(image);
                    characterFadeIn();
                }
                else if(race.equals("Altdorf")) {
                    Image image = new Image("/images/altdorf.png");
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
        mountainChoicBox.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                if(t1.equals(-1)) {
                    return;
                }
                raseChoiceBox.setValue("");
                raseChoiceBox.setDisable(false);
                mountainCheckBox.setDisable(false);
                raseType = FXCollections.observableArrayList(creator.getRacesPool().get(t1.intValue()));
                raseChoiceBox.setItems(raseType);
            }
        });

        //LISTENER DO RASE
        raseChoiceBox.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                proffesionChoiceBox.setDisable(false);
                proffesionChoiceBox.setValue("");
                if(t1.equals(-1)) {
                    return;
                }
                int indexOfMountain = creator.indexOfMountain(mountainChoicBox.getSelectionModel().getSelectedItem());
                proffesionType = FXCollections.observableArrayList(creator.getProfessionsPool().get(t1.intValue()
                        + creator.indexOfProffesion(indexOfMountain)));
                proffesionChoiceBox.setItems(proffesionType);
            }
        });

        //LISTENER DO MOUNTAIN CHECKBOX

        ChangeListener<Boolean> listener = new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean bool, Boolean t1) {
                if(observableValue.equals(mountainCheckBox.selectedProperty())) {
                    if(t1) {
                        raseCheckBox.setDisable(false);
                    } else {
                        raseCheckBox.setDisable(true);
                        raseCheckBox.setSelected(false);
                        professionCheckBox.setDisable(true);
                        professionCheckBox.setSelected(false);
                    }
                } else if(observableValue.equals(raseCheckBox.selectedProperty())) {
                    if(t1) {
                        professionCheckBox.setDisable(false);
                    } else {
                        professionCheckBox.setDisable(true);
                        professionCheckBox.setSelected(false);
                    }
                }
            }
        };

        mountainCheckBox.selectedProperty().addListener(listener);
        raseCheckBox.selectedProperty().addListener(listener);

    }//End of Initialize

    @FXML
    public void randCharacter() {
        String name = null;
        String mountain = null;
        String rase = null;
        String proffesion = null;

        if(nameCheckBox.isSelected()) {
            name = nameField.getText();
        }
        if(mountainCheckBox.isSelected()) {
            mountain = mountainChoicBox.getSelectionModel().getSelectedItem();;
        }
        if(raseCheckBox.isSelected()) {
            rase = raseChoiceBox.getSelectionModel().getSelectedItem();
        }
        if(professionCheckBox.isSelected()) {
            proffesion = proffesionChoiceBox.getSelectionModel().getSelectedItem();;
        }
        character = creator.createCharacter(name, mountain, rase, proffesion);
        showCharacter(character);
    }

    private void showCharacter(Character character) {
        nameField.setText(character.getName());
        mountainChoicBox.setValue(character.getMountain());
        raseChoiceBox.setValue(character.getRace());
        proffesionChoiceBox.setValue(character.getProfession());

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

    public void saveCharacter() throws CloneNotSupportedException {
        String name = nameField.getText();
        String rase = raseChoiceBox.getSelectionModel().getSelectedItem();
        String mountain = mountainChoicBox.getSelectionModel().getSelectedItem();
        String proffesion = proffesionChoiceBox.getSelectionModel().getSelectedItem();

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
                stage.initOwner(anchorPane.getScene().getWindow());
                stage.show();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        } else if(creator.contains(character)) {
            try {
                DialogWindowController.setMessageInfo("Taka postać już istnieje");
                FXMLLoader loader = new FXMLLoader();
                Parent root = loader.load(this.getClass().getResource("/DialogWindow.fxml").openStream());
                Stage stage = new Stage();
                stage.setTitle("Zagalopowałeś się");
                stage.initModality(Modality.WINDOW_MODAL);
                stage.initStyle(StageStyle.TRANSPARENT);
                stage.setScene(new Scene(root));
                stage.initOwner(anchorPane.getScene().getWindow());
                stage.show();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        else{
            character.setName(name);
            character.setMountain(mountain);
            character.setRace(rase);
            character.setProfession(proffesion);
            creator.saveCharacter(character);

            DialogWindowController.setMessageInfo("Zapisano postać");
            FXMLLoader loader = new FXMLLoader();
            Parent root = null;
            try {
                root = loader.load(this.getClass().getResource("/DialogWindow.fxml").openStream());
                Stage stage = new Stage();
                stage.setTitle("Zapis");
                stage.initModality(Modality.WINDOW_MODAL);
                stage.initStyle(StageStyle.TRANSPARENT);
                stage.setScene(new Scene(root));
                stage.initOwner(anchorPane.getScene().getWindow());
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

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

    private void setStatisticsDisable() {
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

    @FXML
    private void previous() {
        mainWindowController.loadMenuScreen();
    }

    public void setMainWindowController(MainWindowController mainWindowController) {
        this.mainWindowController = mainWindowController;
    }

}
