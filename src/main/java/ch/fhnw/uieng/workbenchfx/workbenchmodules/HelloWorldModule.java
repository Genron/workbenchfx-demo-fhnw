package ch.fhnw.uieng.workbenchfx.workbenchmodules;

import com.dlsc.workbenchfx.model.WorkbenchModule;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIcon;
import javafx.scene.Node;
import javafx.scene.control.Label;

public class HelloWorldModule extends WorkbenchModule {
    public HelloWorldModule() {
        super("Hello World", MaterialDesignIcon.HUMAN);
    }

    @Override
    public Node activate() {
        return new Label("Hello World");
    }
}
