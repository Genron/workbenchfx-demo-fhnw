package ch.fhnw.uieng.module02.cantonfiltered_solution.view;

import ch.fhnw.uieng.module02.cantonfiltered_solution.presentationmodel.Switzerland;
import ch.fhnw.uieng.module02.cantonfiltered_solution.view.util.ViewMixin;
import javafx.geometry.Insets;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

/**
 * @author Dieter Holz
 */
class Header extends HBox implements ViewMixin {

    private final Switzerland rootPM;

    private TextField filter;

    Header(Switzerland rootPM) {
        this.rootPM = rootPM;

        init();
    }

    @Override
    public void initializeSelf() {
        getStyleClass().add("header");
    }

    @Override
    public void initializeParts() {
        filter = new TextField();
        filter.getStyleClass().add("filter");
    }

    @Override
    public void layoutParts() {
        setPadding(new Insets(0, 0, 10, 0));

        HBox spacer = new HBox();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        getChildren().addAll(spacer, filter);
    }

    @Override
    public void setupBindings() {
        // Bindings werden (fast) ausschliesslich zwischen einer
        // UI-Property (der textProperty des TextFields) und einer Property aus dem PresentationModel definiert
        // Zwei UI-Properties zu binden ist in der Regel eine Verletzung der Architekturregeln

        // Hinweis: Aufwendige Logik an dieser Stelle (z.B. die Implementierung der Filterung) wäre nicht mit einem
        // einfachen JUnit-Test testbar.

        filter.textProperty().bindBidirectional(rootPM.filterProperty());
    }
}
