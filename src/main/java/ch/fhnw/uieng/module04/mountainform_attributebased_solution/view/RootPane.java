package ch.fhnw.uieng.module04.mountainform_attributebased_solution.view;

import ch.fhnw.uieng.module04.mountainform_attributebased_solution.presentationmodel.Switzerland;
import ch.fhnw.uieng.module04.mountainform_attributebased_solution.view.util.ViewMixin;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

/**
 * @author Dieter Holz
 */
public class RootPane extends BorderPane implements ViewMixin {
    private Switzerland switzerland;

//    private Node toolbar;
    private Node summary;
    private Node form;

    public RootPane(Switzerland switzerland) {
        this.switzerland = switzerland;
        init();
    }

    @Override
    public void initializeSelf() {
        addStylesheetFiles("/fonts/fonts.css", "/styles-ue04/style.css");
        getStyleClass().add("root-pane");
    }

    @Override
    public void initializeParts() {
//        toolbar = new Toolbar(switzerland);
        summary = new Header(switzerland);
        form = new MountainForm(switzerland);
    }

    @Override
    public void layoutParts() {
//        setTop(toolbar);

        ScrollPane scrollPane = new ScrollPane(form);
        scrollPane.setFitToHeight(true);
        scrollPane.setFitToWidth(true);

        setCenter(new VBox(summary, scrollPane));
    }

    @Override
    public void setupBindings() {
    }
}
