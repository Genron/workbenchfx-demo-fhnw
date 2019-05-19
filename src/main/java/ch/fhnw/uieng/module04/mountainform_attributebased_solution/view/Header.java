package ch.fhnw.uieng.module04.mountainform_attributebased_solution.view;

import ch.fhnw.uieng.module04.mountainform_attributebased_solution.presentationmodel.MountainDetailPM;
import ch.fhnw.uieng.module04.mountainform_attributebased_solution.presentationmodel.Switzerland;
import ch.fhnw.uieng.module04.mountainform_attributebased_solution.view.util.ViewMixin;
import ch.fhnw.uieng.module04.mountainform_attributebased_solution.view.util.rectangularimageview.RectangularImageView;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Dieter Holz
 */
class Header extends GridPane implements ViewMixin {

    private final Switzerland switzerland;

    private Label nameLabel;
    private Label heightLabel;
    private Label rangeLabel;
    private RectangularImageView picture;
    private HBox cantonsBox;
    private Label captionLabel;

    private Map<String, ImageView> coatOfArms = new HashMap<>();

    Header(Switzerland switzerland) {
        this.switzerland = switzerland;
        init();
    }

    @Override
    public void initializeSelf() {
        getStyleClass().add("summary");

        coatOfArms.put("AG", createImageView("AG", "Aargau"));
        coatOfArms.put("AI", createImageView("AI", "Appenzell Innerrhoden"));
        coatOfArms.put("AR", createImageView("AR", "Appenzell Ausserrhoden"));
        coatOfArms.put("BE", createImageView("BE", "Bern"));
        coatOfArms.put("BL", createImageView("BL", "Basel-Landschaft"));
        coatOfArms.put("BS", createImageView("BS", "Basel-Stadt"));
        coatOfArms.put("FR", createImageView("FR", "Freiburg"));
        coatOfArms.put("GE", createImageView("GE", "Genf"));
        coatOfArms.put("GL", createImageView("GL", "Glarus"));
        coatOfArms.put("GR", createImageView("GR", "Graubünden"));
        coatOfArms.put("JU", createImageView("JU", "Jura"));
        coatOfArms.put("LU", createImageView("LU", "Luzern"));
        coatOfArms.put("NE", createImageView("NE", "Neuenburg"));
        coatOfArms.put("NW", createImageView("NW", "Nidwalden"));
        coatOfArms.put("OW", createImageView("OW", "Obwalden"));
        coatOfArms.put("SG", createImageView("SG", "St. Gallen"));
        coatOfArms.put("SH", createImageView("SH", "Schaffhausen"));
        coatOfArms.put("SO", createImageView("SO", "Solothurn"));
        coatOfArms.put("SZ", createImageView("SZ", "Schwyz"));
        coatOfArms.put("TG", createImageView("TG", "Thurgau"));
        coatOfArms.put("TI", createImageView("TI", "Tessin"));
        coatOfArms.put("UR", createImageView("UR", "Uri"));
        coatOfArms.put("VD", createImageView("VD", "Waadt"));
        coatOfArms.put("VS", createImageView("VS", "Wallis"));
        coatOfArms.put("ZG", createImageView("ZG", "Zug"));
        coatOfArms.put("ZH", createImageView("ZH", "Zürich"));
    }

    @Override
    public void initializeParts() {
        nameLabel = new Label();
        nameLabel.getStyleClass().add("heading");

        heightLabel = new Label();
        heightLabel.getStyleClass().add("subheading");

        rangeLabel = new Label();
        rangeLabel.getStyleClass().add("subheading");

        picture = new RectangularImageView();

        cantonsBox = new HBox(3);

        captionLabel = new Label();
        captionLabel.getStyleClass().add("caption");
    }

    @Override
    public void layoutParts() {
        VBox spacerCol = new VBox();
        setHgrow(spacerCol, Priority.ALWAYS);

        setHalignment(captionLabel, HPos.CENTER);
        setHalignment(picture, HPos.CENTER);

        cantonsBox.setMinHeight(48);
        cantonsBox.setAlignment(Pos.BOTTOM_LEFT);
        cantonsBox.setPrefHeight(48);

        add(nameLabel, 0, 0);
        add(spacerCol, 1, 0, 1, 5);
        add(picture, 2, 0, 1, 4);
        add(heightLabel, 0, 1);
        add(rangeLabel, 0, 2);
        add(cantonsBox, 0, 3, 1, 2);
        add(captionLabel, 2, 4);
    }

    @Override
    public void setupValueChangedListeners() {
        switzerland.getCurrentMountain().getCantons().valueProperty().addListener((observable, oldValue, newValue) -> {
            cantonsBox.getChildren().clear();
            if (newValue != null) {
                for (String canton : newValue.split("/")) {
                    ImageView image = coatOfArms.get(canton);
                    if (image != null) {
                        cantonsBox.getChildren().add(image);
                    }
                }
            }
        });
    }

    @Override
    public void setupBindings() {
        MountainDetailPM mountain = switzerland.getCurrentMountain();

        nameLabel.textProperty().bind(mountain.getName().valueProperty());
        heightLabel.textProperty().bind(mountain.getHeight().valueProperty().asString("%.2f m"));
        rangeLabel.textProperty().bind(mountain.getRange().valueProperty());
        captionLabel.textProperty().bind(mountain.getCaption().valueProperty());
        picture.imageURLProperty().bind(mountain.getImageURL().valueProperty());
    }

    private ImageView createImageView(String canton, String cantonName) {
        ImageView imageView = new ImageView(new Image(
                Header.class.getResourceAsStream("/wappen_klein/" + canton + ".png")));

        Tooltip.install(imageView, new Tooltip(cantonName));
        return imageView;
    }
}
