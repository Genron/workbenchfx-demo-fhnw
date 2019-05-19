package ch.fhnw.uieng.module04.mountainform_attributebased_solution;

import ch.fhnw.uieng.module04.mountainform_attributebased_solution.presentationmodel.Switzerland;
import ch.fhnw.uieng.module04.mountainform_attributebased_solution.service.MountainService;
import ch.fhnw.uieng.module04.mountainform_attributebased_solution.service.serviceimpl.MountainServiceImpl;
import ch.fhnw.uieng.module04.mountainform_attributebased_solution.view.RootPane;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @author Dieter Holz
 */
public class AppStarter extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        MountainService service = new MountainServiceImpl();
        Switzerland switzerland = new Switzerland(service);
        Parent rootPanel = new RootPane(switzerland);

        Scene scene = new Scene(rootPanel);

        primaryStage.titleProperty().bind(switzerland.applicationTitleProperty());

        primaryStage.setScene(scene);

        primaryStage.show();
    }
}
