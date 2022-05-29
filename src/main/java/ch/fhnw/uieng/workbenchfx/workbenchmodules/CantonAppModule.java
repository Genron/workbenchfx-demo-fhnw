package ch.fhnw.uieng.workbenchfx.workbenchmodules;

import ch.fhnw.uieng.module02.cantonfiltered_solution.presentationmodel.Switzerland;
import ch.fhnw.uieng.module02.cantonfiltered_solution.service.CantonService;
import ch.fhnw.uieng.module02.cantonfiltered_solution.service.serviceimpl.CantonServiceFileBased;
import ch.fhnw.uieng.module02.cantonfiltered_solution.view.RootPane;
import com.dlsc.workbenchfx.Workbench;
import com.dlsc.workbenchfx.model.WorkbenchModule;
import javafx.scene.Node;
import javafx.scene.Parent;
import org.kordamp.ikonli.materialdesign.MaterialDesign;

public class CantonAppModule extends WorkbenchModule {

    private CantonService service;
    private Switzerland switzerland;
    private Parent rootPanel;

    public CantonAppModule() {
        super("Canton App", MaterialDesign.MDI_CITY);
    }

    @Override
    public void init(Workbench workbench) {
        super.init(workbench);
        service = new CantonServiceFileBased();
        switzerland = new Switzerland(service);
        rootPanel = new RootPane(switzerland);
    }

    @Override
    public Node activate() {
        return rootPanel;
    }

}
