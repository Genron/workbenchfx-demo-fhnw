package ch.fhnw.uieng.module03.lazylist_filtered.mountainapp.view;

import ch.fhnw.uieng.module03.lazylist_filtered.mountainapp.presentationmodel.Switzerland;
import ch.fhnw.uieng.module03.lazylist_filtered.mountainapp.view.util.FHNWListView;
import ch.fhnw.uieng.module03.lazylist_filtered.mountainapp.view.util.ViewMixin;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;

/**
 * @author Dieter Holz
 */
public class RootPane extends BorderPane implements ViewMixin {
    private Switzerland rootPM;

    // für das Gesamtlayout sind spezifischen Typen nicht relavant. Es reicht 'Node'
    private Node listView;
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
        listView = new FHNWListView<>(rootPM.getMountains(), param -> new MountainCell());
        footer = new Footer(rootPM);
    }

    @Override
    public void layoutParts() {
        setTop(header);
        setCenter(listView);
        setBottom(footer);
    }

}
