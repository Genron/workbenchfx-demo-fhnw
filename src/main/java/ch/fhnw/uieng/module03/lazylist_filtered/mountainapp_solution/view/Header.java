package ch.fhnw.uieng.module03.lazylist_filtered.mountainapp_solution.view;

import ch.fhnw.uieng.module03.lazylist_filtered.mountainapp_solution.presentationmodel.Switzerland;
import ch.fhnw.uieng.module03.lazylist_filtered.mountainapp_solution.view.util.ViewMixin;
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
        filter.textProperty().bindBidirectional(rootPM.filterProperty());
    }

}
