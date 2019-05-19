package ch.fhnw.uieng.workbenchfx;

import ch.fhnw.uieng.workbenchfx.workbenchmodules.*;
import com.dlsc.workbenchfx.Workbench;
import javafx.application.Application;
import javafx.scene.Scene;
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
                new CantonAppModule(),
                new MountainAppModule(),
                new SwitzerlandModule()
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
