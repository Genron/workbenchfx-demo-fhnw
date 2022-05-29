package ch.fhnw.uieng.workbenchfx.workbenchmodules;

import com.dlsc.workbenchfx.model.WorkbenchModule;
import javafx.scene.Node;
import javafx.scene.control.Label;
import org.kordamp.ikonli.materialdesign.MaterialDesign;

public class HelloWorldModule extends WorkbenchModule {
    private final Label content;

    public HelloWorldModule(String name, MaterialDesign icon, String labelText) {
        super(name, icon);
        content = new Label(labelText);
    }

    @Override
    public Node activate() {
        return content;
    }
}
