package ch.fhnw.uieng.module04.mountainform_attributebased_solution.view;

import ch.fhnw.uieng.module04.mountainform_attributebased_solution.presentationmodel.Language;
import ch.fhnw.uieng.module04.mountainform_attributebased_solution.presentationmodel.MountainDetailPM;
import ch.fhnw.uieng.module04.mountainform_attributebased_solution.presentationmodel.Switzerland;
import ch.fhnw.uieng.module04.mountainform_attributebased_solution.view.util.ViewMixin;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

/**
 * @author Dieter Holz
 */
class Toolbar extends ToolBar implements ViewMixin {
    private static final String SAVE_ICON = "\uf0c7";
    private static final String UNDO_ICON = "\uf0e2";
    private static final String CH_ICON = "\ue001";
    private static final String UK_ICON = "\ue000";
    private final Switzerland switzerland;
    private Button saveButton;
    private Button cancelButton;
    private Button languageDEButton;
    private Button languageUKButton;

    Toolbar(Switzerland switzerland) {
        this.switzerland = switzerland;
        init();
    }

    @Override
    public void initializeSelf() {
        getStyleClass().add("tool-bar");
    }

    @Override
    public void initializeParts() {
        saveButton = new Button(SAVE_ICON);
        saveButton.getStyleClass().add("fontawesome");

        cancelButton = new Button(UNDO_ICON);
        cancelButton.getStyleClass().add("fontawesome");

        languageDEButton = new Button(CH_ICON);
        languageDEButton.getStyleClass().add("flaticon");

        languageUKButton = new Button(UK_ICON);
        languageUKButton.getStyleClass().add("flaticon");
    }

    @Override
    public void layoutParts() {
        HBox spacer = new HBox();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        getItems().addAll(saveButton, cancelButton, spacer, languageDEButton, languageUKButton);
    }

    @Override
    public void setupEventHandlers() {
        saveButton.setOnAction(event -> switzerland.save());
        cancelButton.setOnAction(event -> switzerland.revert());

        languageDEButton.setOnAction(event -> switzerland.setCurrentLanguage(Language.DE));
        languageUKButton.setOnAction(event -> switzerland.setCurrentLanguage(Language.UK));
    }

    @Override
    public void setupBindings() {
        languageUKButton.disableProperty().bind(switzerland.currentLanguageProperty().isEqualTo(Language.UK));
        languageDEButton.disableProperty().bind(switzerland.currentLanguageProperty().isEqualTo(Language.DE));

        MountainDetailPM mountain = switzerland.getCurrentMountain();

        saveButton.disableProperty().bind((mountain.dirtyProperty().not())
                .or(mountain.validProperty().not()));
        cancelButton.disableProperty().bind(mountain.dirtyProperty().not()
                .and(mountain.validProperty()));
    }
}
