package ch.fhnw.uieng.module04.mountainform_attributebased_solution.view;

import ch.fhnw.uieng.module04.mountainform_attributebased_solution.presentationmodel.MountainDetailPM;
import ch.fhnw.uieng.module04.mountainform_attributebased_solution.presentationmodel.Switzerland;
import ch.fhnw.uieng.module04.mountainform_attributebased_solution.view.util.SimpleTextControl;
import ch.fhnw.uieng.module04.mountainform_attributebased_solution.view.util.ViewMixin;
import javafx.scene.control.Label;
import javafx.scene.layout.*;

/**
 * @author Dieter Holz
 */

class MountainForm extends GridPane implements ViewMixin {

    private final Switzerland switzerland;

    private Label nameLabel;
    private Label heightLabel;
    private Label typeLabel;
    private Label regionLabel;
    private Label cantonsLabel;
    private Label rangeLabel;
    private Label isolationLabel;
    private Label isolationPointLabel;
    private Label prominenceLabel;
    private Label prominencePointLabel;
    private Label captionLabel;
    private Label imageURLLabel;

    private SimpleTextControl nameField;
    private SimpleTextControl heightField;
    private SimpleTextControl typeField;
    private SimpleTextControl regionField;
    private SimpleTextControl cantonsField;
    private SimpleTextControl rangeField;
    private SimpleTextControl isolationField;
    private SimpleTextControl isolationPointField;
    private SimpleTextControl prominenceField;
    private SimpleTextControl prominencePointField;
    private SimpleTextControl captionField;
    private SimpleTextControl imageURLField;

    MountainForm(Switzerland switzerland) {
        this.switzerland = switzerland;
        init();
    }

    @Override
    public void initializeSelf() {
        getStyleClass().add("form");
    }

    @Override
    public void initializeParts() {
        nameLabel = new Label();
        heightLabel = new Label();
        typeLabel = new Label();
        regionLabel = new Label();
        cantonsLabel = new Label();
        rangeLabel = new Label();
        isolationLabel = new Label();
        isolationPointLabel = new Label();
        prominenceLabel = new Label();
        prominencePointLabel = new Label();
        captionLabel = new Label();
        imageURLLabel = new Label();

        MountainDetailPM mountain = switzerland.getCurrentMountain();

        nameField = new SimpleTextControl(mountain.getName());
        heightField = new SimpleTextControl(mountain.getHeight());
        typeField = new SimpleTextControl(mountain.getType());
        regionField = new SimpleTextControl(mountain.getRegion());
        cantonsField = new SimpleTextControl(mountain.getCantons());
        rangeField = new SimpleTextControl(mountain.getRange());
        isolationField = new SimpleTextControl(mountain.getIsolation());
        isolationPointField = new SimpleTextControl(mountain.getIsolationPoint());
        prominenceField = new SimpleTextControl(mountain.getProminence());
        prominencePointField = new SimpleTextControl(mountain.getProminencePoint());
        captionField = new SimpleTextControl(mountain.getCaption());
        imageURLField = new SimpleTextControl(mountain.getImageURL());
    }

    @Override
    public void layoutParts() {
        ColumnConstraints firstLabelCol = new ColumnConstraints();
        firstLabelCol.setMaxWidth(ConstraintsBase.CONSTRAIN_TO_PREF);
        firstLabelCol.setMinWidth(30);
        firstLabelCol.setPrefWidth(110);

        ColumnConstraints firstFieldCol = new ColumnConstraints();
        firstFieldCol.setMinWidth(100);
        firstFieldCol.setFillWidth(true);
        firstFieldCol.setHgrow(Priority.ALWAYS);

        ColumnConstraints secondLabelCol = new ColumnConstraints();
        secondLabelCol.setMaxWidth(ConstraintsBase.CONSTRAIN_TO_PREF);
        secondLabelCol.setMinWidth(30);
        secondLabelCol.setPrefWidth(110);

        ColumnConstraints secondFieldCol = new ColumnConstraints();
        secondFieldCol.setMinWidth(100);
        secondFieldCol.setFillWidth(true);
        secondFieldCol.setHgrow(Priority.ALWAYS);

        getColumnConstraints().addAll(firstLabelCol, firstFieldCol, new ColumnConstraints(), secondLabelCol, secondFieldCol);

        Region spacer = new Region();
        spacer.getStyleClass().add("spacer");

        addRow(0, nameLabel, nameField, spacer, heightLabel, heightField);
        addRow(1, isolationLabel, isolationField, new Region(), prominenceLabel, prominenceField);
        addRow(2, isolationPointLabel, isolationPointField, new Region(), prominencePointLabel, prominencePointField);
        addRow(3, typeLabel, typeField, new Region(), regionLabel, regionField);
        addRow(4, cantonsLabel, cantonsField, new Region(), rangeLabel, rangeField);

        add(imageURLLabel, 0, 5);
        add(imageURLField, 1, 5, 4, 1);
        add(captionLabel, 0, 6);
        add(captionField, 1, 6, 4, 1);
    }

    @Override
    public void setupBindings() {
        MountainDetailPM mountain = switzerland.getCurrentMountain();

        nameLabel.textProperty().bind(mountain.getName().labelProperty());
        heightLabel.textProperty().bind(mountain.getHeight().labelProperty());
        typeLabel.textProperty().bind(mountain.getType().labelProperty());
        regionLabel.textProperty().bind(mountain.getRegion().labelProperty());
        cantonsLabel.textProperty().bind(mountain.getCantons().labelProperty());
        rangeLabel.textProperty().bind(mountain.getRange().labelProperty());
        isolationLabel.textProperty().bind(mountain.getIsolation().labelProperty());
        isolationPointLabel.textProperty().bind(mountain.getIsolationPoint().labelProperty());
        prominenceLabel.textProperty().bind(mountain.getProminence().labelProperty());
        prominencePointLabel.textProperty().bind(mountain.getProminencePoint().labelProperty());
        captionLabel.textProperty().bind(mountain.getCaption().labelProperty());
        imageURLLabel.textProperty().bind(mountain.getImageURL().labelProperty());
    }
}
