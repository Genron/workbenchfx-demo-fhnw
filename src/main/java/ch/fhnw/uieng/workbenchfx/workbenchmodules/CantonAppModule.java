package ch.fhnw.uieng.workbenchfx.workbenchmodules;

import ch.fhnw.uieng.module02.cantonfiltered_solution.presentationmodel.Switzerland;
import ch.fhnw.uieng.module02.cantonfiltered_solution.service.CantonService;
import ch.fhnw.uieng.module02.cantonfiltered_solution.service.serviceimpl.CantonServiceFileBased;
import ch.fhnw.uieng.module02.cantonfiltered_solution.view.Content;
import com.dlsc.workbenchfx.Workbench;
import com.dlsc.workbenchfx.model.WorkbenchModule;
import com.dlsc.workbenchfx.view.controls.ToolbarItem;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIcon;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class CantonAppModule extends WorkbenchModule {

    private CantonService service;
    private Switzerland switzerland;
    private Content rootPanel;

    private ToolbarItem countItem;
    private ToolbarItem searchField;

    public CantonAppModule() {
        super("Canton App", MaterialDesignIcon.CITY);
    }

    @Override
    public void init(Workbench workbench) {
        super.init(workbench);
        service = new CantonServiceFileBased();
        switzerland = new Switzerland(service);
        rootPanel = new Content(switzerland);

        setupModuleToolbarLeft();
        setupModuleToolbarRight();
    }

    private void setupModuleToolbarLeft() {
        Label countLabel = new Label();
        countLabel.textProperty().bind(switzerland.filteredCountProperty().asString()
                .concat("/")
                .concat(switzerland.totalCountProperty())
                .concat(" Kantone"));
        countItem = new ToolbarItem(countLabel);
    }

    private void setupModuleToolbarRight() {
        TextField filter = new TextField();
        filter.setPromptText("Filter");
        filter.textProperty().bindBidirectional(switzerland.filterProperty());

        searchField = new ToolbarItem(filter);
    }

    @Override
    public Node activate() {
        getToolbarControlsLeft().add(countItem);
        getToolbarControlsRight().add(searchField);
        return rootPanel;
    }

    @Override
    public void deactivate() {
        getToolbarControlsLeft().remove(countItem);
        getToolbarControlsRight().remove(searchField);
    }

}
