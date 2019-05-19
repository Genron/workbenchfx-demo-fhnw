package ch.fhnw.uieng.module02.cantonfiltered_solution.view;

import ch.fhnw.uieng.module02.cantonfiltered_solution.presentationmodel.Switzerland;
import ch.fhnw.uieng.module02.cantonfiltered_solution.view.util.ViewMixin;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

/**
 * @author Dieter Holz
 */
class Footer extends HBox implements ViewMixin {

    private final Switzerland rootPM;

    private Label countLabel;

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
        countLabel = new Label();
    }

    @Override
    public void layoutParts() {
        getChildren().add(countLabel);
    }

    @Override
    public void setupBindings() {
        countLabel.textProperty().bind(rootPM.filteredCountProperty().asString()
                .concat("/")
                .concat(rootPM.totalCountProperty())
                .concat(" Kantone"));
    }
}
