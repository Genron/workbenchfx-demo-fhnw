package ch.fhnw.uieng.workbenchfx.workbenchmodules;

import ch.fhnw.uieng.module03.lazylist_filtered.mountainapp_solution.presentationmodel.Switzerland;
import ch.fhnw.uieng.module03.lazylist_filtered.mountainapp_solution.service.MountainDTO;
import ch.fhnw.uieng.module03.lazylist_filtered.mountainapp_solution.service.PagingService;
import ch.fhnw.uieng.module03.lazylist_filtered.mountainapp_solution.service.serviceimpl.MountainServiceFileBased;
import ch.fhnw.uieng.module03.lazylist_filtered.mountainapp_solution.view.RootPane;
import com.dlsc.workbenchfx.Workbench;
import com.dlsc.workbenchfx.model.WorkbenchModule;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIcon;
import javafx.scene.Node;
import javafx.scene.Parent;

public class MountainAppModule extends WorkbenchModule {

    PagingService<MountainDTO> service;
    Switzerland switzerland;
    private Parent rootPanel;

    public MountainAppModule() {
        super("Mountain App", MaterialDesignIcon.IMAGE_FILTER_HDR);
    }

    @Override
    public void init(Workbench workbench) {
        super.init(workbench);
        service = new MountainServiceFileBased();
        switzerland = new Switzerland(service);
        rootPanel = new RootPane(switzerland);
    }

    @Override
    public Node activate() {
        return rootPanel;
    }

}
