package ch.fhnw.uieng.workbenchfx.workbenchmodules;

import com.dlsc.workbenchfx.model.WorkbenchModule;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIcon;
import javafx.scene.Node;
import javafx.scene.control.Label;

public class HelloWorldModule extends WorkbenchModule {
    private final Label content;

    public HelloWorldModule(String name, MaterialDesignIcon icon, String labelText) {
        super(name, icon);
        content = new Label(labelText);
    }

    @Override
    public Node activate() {
        return content;
    }
}
