package ch.fhnw.uieng.workbenchfx.workbenchmodules;

import ch.fhnw.uieng.module04.mountainform_attributebased_solution.presentationmodel.Language;
import ch.fhnw.uieng.module04.mountainform_attributebased_solution.presentationmodel.MountainDetailPM;
import ch.fhnw.uieng.module04.mountainform_attributebased_solution.presentationmodel.Switzerland;
import ch.fhnw.uieng.module04.mountainform_attributebased_solution.service.MountainService;
import ch.fhnw.uieng.module04.mountainform_attributebased_solution.service.serviceimpl.MountainServiceImpl;
import ch.fhnw.uieng.module04.mountainform_attributebased_solution.view.RootPane;
import com.dlsc.workbenchfx.Workbench;
import com.dlsc.workbenchfx.model.WorkbenchDialog;
import com.dlsc.workbenchfx.model.WorkbenchModule;
import com.dlsc.workbenchfx.view.controls.ToolbarItem;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuItem;
import org.kordamp.ikonli.javafx.FontIcon;
import org.kordamp.ikonli.materialdesign.MaterialDesign;

public class SwitzerlandModule extends WorkbenchModule {

    private MountainService service;
    private Switzerland switzerland;
    private Parent rootPanel;

    private ToolbarItem saveButton;
    private ToolbarItem cancelButton;
    private ToolbarItem languageItem;

    public SwitzerlandModule() {
        super("Switzerland", MaterialDesign.MDI_FLAG);
    }

    @Override
    public void init(Workbench workbench) {
        super.init(workbench);
        service = new MountainServiceImpl();
        switzerland = new Switzerland(service);
        rootPanel = new RootPane(switzerland);

        setupWorkbenchToolbarLeft();
        setupWorkbenchToolbarRight();
    }

    private void setupWorkbenchToolbarLeft() {
        MountainDetailPM mountain = switzerland.getCurrentMountain();

        saveButton = new ToolbarItem(
                new FontIcon(MaterialDesign.MDI_CONTENT_SAVE), event -> switzerland.save()
        );
        saveButton.disableProperty().bind((mountain.dirtyProperty().not())
                .or(mountain.validProperty().not()));

        cancelButton = new ToolbarItem(
                new FontIcon(MaterialDesign.MDI_UNDO), event -> switzerland.revert()
        );
        cancelButton.disableProperty().bind(mountain.dirtyProperty().not()
                .and(mountain.validProperty()));
    }

    private void setupWorkbenchToolbarRight() {
        MenuItem languageDEButton = new MenuItem("DE");
        languageDEButton.setOnAction(event -> switzerland.setCurrentLanguage(Language.DE));
        languageDEButton.disableProperty().bind(switzerland.currentLanguageProperty().isEqualTo(Language.DE));

        MenuItem languageUKButton = new MenuItem("UK");
        languageUKButton.setOnAction(event -> switzerland.setCurrentLanguage(Language.UK));
        languageUKButton.disableProperty().bind(switzerland.currentLanguageProperty().isEqualTo(Language.UK));

        languageItem = new ToolbarItem("Language", new FontIcon(MaterialDesign.MDI_TRANSLATE), languageDEButton, languageUKButton);
    }

    private void insertWorkbenchToolbarItems() {
        getWorkbench().getToolbarControlsLeft().addAll(saveButton, cancelButton);
        getWorkbench().getToolbarControlsRight().add(languageItem);
    }

    private void removeWorkbenchToolbarItems() {
        getWorkbench().getToolbarControlsLeft().removeAll(saveButton, cancelButton);
        getWorkbench().getToolbarControlsRight().remove(languageItem);
    }

    @Override
    public Node activate() {
        insertWorkbenchToolbarItems();
        return rootPanel;
    }

    @Override
    public void deactivate() {
        removeWorkbenchToolbarItems();
    }

    @Override
    public boolean destroy() {
        if (saveButton.isDisabled()) {
            return true;
        }
        getWorkbench().showDialog(WorkbenchDialog.builder("Confirmation",
                "Are you sure you want to close this module without saving?",
                WorkbenchDialog.Type.CONFIRMATION)
                .blocking(true)
                .onResult(buttonType -> {
                    if (ButtonType.YES.equals(buttonType)) {
                        System.out.println("Pressed: YES");
                        close();
                    }
                })
                .build());
        return false;
    }
}
