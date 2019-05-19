package ch.fhnw.uieng.module02.cantonfiltered_solution.view;

import ch.fhnw.uieng.module02.cantonfiltered_solution.presentationmodel.Switzerland;
import ch.fhnw.uieng.module02.cantonfiltered_solution.view.util.ViewMixin;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;

/**
 * @author Dieter Holz
 */
public class RootPane extends BorderPane implements ViewMixin {
    private final Switzerland rootPM;

    private Node header;
    private Node content;
    private Node footer;

    public RootPane(Switzerland pm) {
        this.rootPM = pm;
        init();
    }

    @Override
    public void initializeSelf() {
        addStylesheetFiles("/fonts/fonts.css", "/styles-ue02/style.css");
        getStyleClass().add("root-pane");
    }

    @Override
    public void initializeParts() {
        header = new Header(rootPM);
        content = new Content(rootPM);
        footer = new Footer(rootPM);
    }

    @Override
    public void layoutParts() {
        setTop(header);
        setCenter(content);
        setBottom(footer);
    }
}
