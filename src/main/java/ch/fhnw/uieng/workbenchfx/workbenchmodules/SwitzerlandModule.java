package ch.fhnw.uieng.workbenchfx.workbenchmodules;

import ch.fhnw.uieng.module04.mountainform_attributebased_solution.presentationmodel.Switzerland;
import ch.fhnw.uieng.module04.mountainform_attributebased_solution.service.MountainService;
import ch.fhnw.uieng.module04.mountainform_attributebased_solution.service.serviceimpl.MountainServiceImpl;
import ch.fhnw.uieng.module04.mountainform_attributebased_solution.view.RootPane;
import com.dlsc.workbenchfx.Workbench;
import com.dlsc.workbenchfx.model.WorkbenchModule;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIcon;
import javafx.scene.Node;
import javafx.scene.Parent;

public class SwitzerlandModule extends WorkbenchModule {

    private MountainService service;
    private Switzerland switzerland;
    private Parent rootPanel;

    public SwitzerlandModule() {
        super("Switzerland", MaterialDesignIcon.FLAG);
    }

    @Override
    public void init(Workbench workbench) {
        super.init(workbench);
        service = new MountainServiceImpl();
        switzerland = new Switzerland(service);
        rootPanel = new RootPane(switzerland);
    }

    @Override
    public Node activate() {
        return rootPanel;
    }

}
