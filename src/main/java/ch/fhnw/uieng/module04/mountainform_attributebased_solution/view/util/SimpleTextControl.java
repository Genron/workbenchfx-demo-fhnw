package ch.fhnw.uieng.module04.mountainform_attributebased_solution.view.util;

import ch.fhnw.uieng.module04.mountainform_attributebased_solution.presentationmodel.util.Attribute;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.StackPane;

/**
 * @author Dieter Holz
 */
public class SimpleTextControl extends StackPane implements ViewMixin {
    private final Attribute attribute;

    private TextField editableField;
    private Label readOnlyLabel;

    public SimpleTextControl(Attribute attribute) {
        this.attribute = attribute;
        init();
    }

    @Override
    public void initializeParts() {
        editableField = new TextField();
        readOnlyLabel = new Label();

        updateInvalidStyle(attribute.isValid());
        updateMandatoryStyle(attribute.isMandatory());
        updateDirtyStyle(attribute.isDirty());
    }

    @Override
    public void layoutParts() {
        setAlignment(editableField, Pos.CENTER_LEFT);
        setAlignment(readOnlyLabel, Pos.CENTER_LEFT);
        getChildren().setAll(editableField, readOnlyLabel);
    }

    @Override
    public void setupValueChangedListeners() {
        attribute.validProperty().addListener((observable, oldValue, newValue) -> updateInvalidStyle(newValue));

        attribute.validationMessageProperty().addListener((observable, oldValue, newValue) -> setTooltip(newValue));

        attribute.mandatoryProperty().addListener((observable, oldValue, newValue) -> updateMandatoryStyle(newValue));

        attribute.dirtyProperty().addListener((observable, oldValue, newValue) -> updateDirtyStyle(newValue));
    }

    @Override
    public void setupBindings() {
        editableField.visibleProperty().bind(attribute.readOnlyProperty().not());
        readOnlyLabel.visibleProperty().bind(attribute.readOnlyProperty());

        editableField.textProperty().bindBidirectional(attribute.userInputProperty());
        readOnlyLabel.textProperty().bind(attribute.userInputProperty());
    }

    private void setTooltip(String text) {
        Tooltip tooltip = editableField.getTooltip();
        if (tooltip == null) {
            editableField.setTooltip(new Tooltip(text));
        } else {
            tooltip.setText(text);
        }
    }


    private void updateStyle(String style, boolean newValue) {
        if (newValue) {
            editableField.getStyleClass().add(style);
        } else {
            editableField.getStyleClass().remove(style);
        }
    }

    private void updateInvalidStyle(boolean newValue) {
        updateStyle("invalid", !newValue);
    }

    private void updateMandatoryStyle(boolean newValue) {
        updateStyle("mandatory", newValue);
    }

    private void updateDirtyStyle(boolean newValue) {
        updateStyle("dirty", newValue);
    }

}
