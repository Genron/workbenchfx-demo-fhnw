package ch.fhnw.uieng.workbenchfx.workbenchmodules;

import com.dlsc.workbenchfx.model.WorkbenchModule;
import org.kordamp.ikonli.materialdesign.MaterialDesign;
import javafx.scene.Node;
import javafx.scene.control.Label;

public class HelloWorldModule extends WorkbenchModule {
    public HelloWorldModule() {
        super("Hello World", MaterialDesign.MDI_HUMAN);
    }

    @Override
    public Node activate() {
        return new Label("Hello World");
    }
}
