package ch.fhnw.uieng.workbenchfx;

import ch.fhnw.uieng.workbenchfx.workbenchmodules.HelloWorldModule;
import com.dlsc.workbenchfx.Workbench;
import com.dlsc.workbenchfx.model.WorkbenchDialog;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIcon;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import javafx.application.Application;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

/**
 * @author Genron
 */
public class _StarterWorkbench extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        Workbench workbench = Workbench.builder(
                new HelloWorldModule()
        ).build();

        Scene scene = new Scene(workbench);
        primaryStage.setScene(scene);

        primaryStage.setWidth(900);
        primaryStage.setHeight(550);

        primaryStage.show();
        primaryStage.centerOnScreen();
        primaryStage.setTitle("WorkbenchFX");

    }
}
