package ch.fhnw.uieng.module03.lazylist_filtered.mountainapp.view;

import ch.fhnw.uieng.module03.lazylist_filtered.mountainapp.presentationmodel.Switzerland;
import ch.fhnw.uieng.module03.lazylist_filtered.mountainapp.presentationmodel.util.PagingList;
import ch.fhnw.uieng.module03.lazylist_filtered.mountainapp.view.util.ViewMixin;
import javafx.beans.binding.Bindings;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

/**
 * @author Dieter Holz
 */
class Footer extends GridPane implements ViewMixin {

    private final Switzerland rootPM;

    private Label count;
    private Label instances;
    private Slider pageSizeFactor;
    private Slider sleep;
    private Label serviceCalls;

    Footer(Switzerland rootPM) {
        this.rootPM = rootPM;

        init();
    }

    @Override
    public void initializeSelf() {
        getStyleClass().add("footer");
    }

    @Override
    public void initializeParts() {
        count = new Label();

        instances = new Label();

        pageSizeFactor = new Slider(1, 5, 1);
        pageSizeFactor.setShowTickMarks(true);
        pageSizeFactor.setMajorTickUnit(1);
        pageSizeFactor.setMinorTickCount(0);
        pageSizeFactor.setShowTickLabels(true);
        pageSizeFactor.setSnapToTicks(true);

        sleep = new Slider(0, 1000, 200);
        sleep.setShowTickMarks(true);
        sleep.setShowTickLabels(true);
        sleep.setMajorTickUnit(200);
        sleep.setMinorTickCount(1);
        sleep.setSnapToTicks(true);

        serviceCalls = new Label();
    }

    @Override
    public void layoutParts() {
        ColumnConstraints cc = new ColumnConstraints();
        cc.setHgrow(Priority.ALWAYS);

        getColumnConstraints().addAll(new ColumnConstraints(), cc);

        addRow(0, new Label("Mountains"), count);
        addRow(1, new Label("Mountain Instances"), instances);
        addRow(2, new Label("Service Calls"), serviceCalls);
        addRow(3, new Label("Paging Faktor"), pageSizeFactor);
        addRow(4, new Label("Sleep"), sleep);
    }

    @Override
    public void setupBindings() {
        PagingList mountains = rootPM.getMountains();

        count.textProperty().bind(Bindings.size(mountains).asString());
        instances.textProperty().bind(mountains.cacheLevelProperty().asString());
        serviceCalls.textProperty().bind(mountains.serviceCallsProperty().asString());
        sleep.valueProperty().bindBidirectional(mountains.sleepProperty());
        pageSizeFactor.valueProperty().bindBidirectional(mountains.pageSizeFactorProperty());
    }
}
