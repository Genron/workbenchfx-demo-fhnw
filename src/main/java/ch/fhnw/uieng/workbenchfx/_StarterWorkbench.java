package ch.fhnw.uieng.workbenchfx;

import ch.fhnw.uieng.workbenchfx.workbenchmodules.*;
import com.dlsc.preferencesfx.PreferencesFx;
import com.dlsc.preferencesfx.model.Category;
import com.dlsc.preferencesfx.model.Group;
import com.dlsc.preferencesfx.model.Setting;
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
 * @author Dieter Holz
 */
public class _StarterWorkbench extends Application {

    private Stage primaryStage;
    private Workbench workbench;
    private PreferencesFx preferences;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;

        preferences = createPreferences();

        workbench = Workbench.builder(
                new CantonAppModule(),
                new MountainAppModule(),
                new SwitzerlandModule()
        ).navigationDrawerItems(
                setupPreferencesItem()
        ).build();

        Scene scene = new Scene(workbench);
        primaryStage.setScene(scene);

        primaryStage.setWidth(900);
        primaryStage.setHeight(550);

        primaryStage.show();
        primaryStage.centerOnScreen();

        setNightMode(false);
    }

    private MenuItem setupPreferencesItem() {
        MenuItem openPreferences = new MenuItem("Settings", new MaterialDesignIconView(MaterialDesignIcon.SETTINGS));
        openPreferences.setOnAction(event -> {
            openPreferences.setDisable(true);
            workbench.showDialog(
                    WorkbenchDialog.builder("Preferences", preferences.getView(), ButtonType.CLOSE)
                        .maximized(true)
                        .onResult(buttonType -> openPreferences.setDisable(false))
                        .build()
            );
            workbench.hideNavigationDrawer();
        });
        return openPreferences;
    }

    private PreferencesFx createPreferences() {
        BooleanProperty nightMode = new SimpleBooleanProperty();
        nightMode.addListener((observable, oldValue, newValue) -> setNightMode(newValue));

        StringProperty workbenchTitle = new SimpleStringProperty("WorkbenchFX");
        primaryStage.titleProperty().bind(workbenchTitle);

        return PreferencesFx.of(_StarterWorkbench.class,
                Category.of("General",
                        Group.of("Appearance",
                                Setting.of("Night mode", nightMode),
                                Setting.of("Workbench Title", workbenchTitle)
                        )
                )
        );
    }

    private void setNightMode(boolean on) {
        String customTheme = _StarterWorkbench.class.getResource("/styles-custom/customTheme.css").toExternalForm();
        String darkTheme = _StarterWorkbench.class.getResource("/styles-custom/darkTheme.css").toExternalForm();
        ObservableList<String> stylesheets = workbench.getStylesheets();
        if (on) {
            stylesheets.remove(customTheme);
            stylesheets.add(darkTheme);
        } else {
            stylesheets.remove(darkTheme);
            stylesheets.add(customTheme);
        }
    }
}
