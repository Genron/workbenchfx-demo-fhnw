package ch.fhnw.uieng.workbenchfx.workbenchmodules;

import ch.fhnw.uieng.module03.lazylist_filtered.mountainapp_solution.presentationmodel.Switzerland;
import ch.fhnw.uieng.module03.lazylist_filtered.mountainapp_solution.service.MountainDTO;
import ch.fhnw.uieng.module03.lazylist_filtered.mountainapp_solution.service.PagingService;
import ch.fhnw.uieng.module03.lazylist_filtered.mountainapp_solution.service.serviceimpl.MountainServiceFileBased;
import ch.fhnw.uieng.module03.lazylist_filtered.mountainapp_solution.view.RootPane;
import com.dlsc.workbenchfx.Workbench;
import com.dlsc.workbenchfx.model.WorkbenchModule;
import com.dlsc.workbenchfx.view.controls.ToolbarItem;
import org.kordamp.ikonli.materialdesign.MaterialDesign;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.TextField;

public class MountainAppModule extends WorkbenchModule {

    PagingService<MountainDTO> service;
    Switzerland switzerland;
    private Parent rootPanel;

    private ToolbarItem searchField;

    public MountainAppModule() {
        super("Mountain App", MaterialDesign.MDI_IMAGE_FILTER_HDR);
    }

    @Override
    public void init(Workbench workbench) {
        super.init(workbench);
        service = new MountainServiceFileBased();
        switzerland = new Switzerland(service);
        rootPanel = new RootPane(switzerland);

        setupModuleToolbarRight();
    }

    private void setupModuleToolbarRight() {
        TextField filter = new TextField();
        filter.setPromptText("Filter");
        filter.textProperty().bindBidirectional(switzerland.filterProperty());

        searchField = new ToolbarItem(filter);
    }

    @Override
    public Node activate() {
        getToolbarControlsRight().add(searchField);
        return rootPanel;
    }

    @Override
    public void deactivate() {
        getToolbarControlsRight().remove(searchField);
    }
}
