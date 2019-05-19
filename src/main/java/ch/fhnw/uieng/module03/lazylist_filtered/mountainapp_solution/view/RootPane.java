package ch.fhnw.uieng.module03.lazylist_filtered.mountainapp_solution.view;

import ch.fhnw.uieng.module03.lazylist_filtered.mountainapp_solution.presentationmodel.MountainPM;
import ch.fhnw.uieng.module03.lazylist_filtered.mountainapp_solution.presentationmodel.Switzerland;
import ch.fhnw.uieng.module03.lazylist_filtered.mountainapp_solution.view.util.FHNWListView;
import ch.fhnw.uieng.module03.lazylist_filtered.mountainapp_solution.view.util.ViewMixin;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;

/**
 * @author Dieter Holz
 */
public class RootPane extends BorderPane implements ViewMixin {
    private Switzerland rootPM;

    private ListView<MountainPM> listView;
    private Node header;
    private Node footer;

    public RootPane(Switzerland pm) {
        this.rootPM = pm;
        init();
    }

    @Override
    public void initializeSelf() {
        addStylesheetFiles("/fonts/fonts.css", "/styles-ue03/style.css");
        getStyleClass().add("root-pane");
    }

    @Override
    public void initializeParts() {
        header = new Header(rootPM);
        listView = new FHNWListView<>(param -> new MountainCell());
        footer = new Footer(rootPM);
    }

    @Override
    public void layoutParts() {
        setTop(header);
        setCenter(listView);
        setBottom(footer);
    }

    @Override
    public void setupBindings() {
        listView.itemsProperty().bind(rootPM.filteredMountainsProperty());
    }
}
