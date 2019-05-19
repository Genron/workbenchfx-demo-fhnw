package ch.fhnw.uieng.module02.cantonfiltered_solution.view;

import ch.fhnw.uieng.module02.cantonfiltered_solution.presentationmodel.CantonPM;
import ch.fhnw.uieng.module02.cantonfiltered_solution.presentationmodel.Switzerland;
import ch.fhnw.uieng.module02.cantonfiltered_solution.view.util.ViewMixin;
import javafx.collections.transformation.SortedList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Dieter Holz
 */
public class Content extends TableView<CantonPM> implements ViewMixin {

    private final Switzerland rootPM;

    public Content(Switzerland rootPM) {
        super(rootPM.getCantons());

        this.rootPM = rootPM;

        init();
    }

    @Override
    public void initializeSelf() {
        addStylesheetFiles("/fonts/fonts.css", "/styles-ue02/style.css");
    }

    @Override
    public void initializeParts() {
        //todo Die Beschriftungen der ColumnHeader ins PM verlagern.
        TableColumn<CantonPM, String> wappenCol = new TableColumn<>("");
        wappenCol.setCellValueFactory(cell -> cell.getValue().kuerzelProperty());
        wappenCol.setCellFactory(cell -> new KantonTableCell());

        TableColumn<CantonPM, String> nameCol = new TableColumn<>("Kanton");
        nameCol.setCellValueFactory(cell -> cell.getValue().kantonProperty());

        TableColumn<CantonPM, String> kuerzelCol = new TableColumn<>("K\u00fcrzel");
        kuerzelCol.setCellValueFactory(cell -> cell.getValue().kuerzelProperty());

        TableColumn<CantonPM, Number> kantonsnummerCol = new TableColumn<>("Nr.");
        kantonsnummerCol.setCellValueFactory(cell -> cell.getValue().kantonsnummerProperty());

        TableColumn<CantonPM, Number> standesstimmeCol = new TableColumn<>("Stimme");
        standesstimmeCol.setCellValueFactory(cell -> cell.getValue().standesstimmeProperty());

        TableColumn<CantonPM, Number> beitrittCol = new TableColumn<>("Beitritt");
        beitrittCol.setCellValueFactory(cell -> cell.getValue().beitrittProperty());

        TableColumn<CantonPM, String> hauptortCol = new TableColumn<>("Hauptort");
        hauptortCol.setCellValueFactory(cell -> cell.getValue().hauptortProperty());

        TableColumn<CantonPM, Number> einwohnerCol = new TableColumn<>("Einwohner");
        einwohnerCol.setCellValueFactory(cell -> cell.getValue().einwohnerProperty());

        TableColumn<CantonPM, Number> auslaenderCol = new TableColumn<>("% Ausländer");
        auslaenderCol.setCellValueFactory(cell -> cell.getValue().auslaenderProperty());

        TableColumn<CantonPM, Number> flaecheCol = new TableColumn<>("Fl\u00e4che");
        flaecheCol.setCellValueFactory(cell -> cell.getValue().flaecheProperty());

        TableColumn<CantonPM, Number> einwohnerdichteCol = new TableColumn<>("Einw. / km2");
        einwohnerdichteCol.setCellValueFactory(cell -> cell.getValue().einwohnerdichteProperty());

        TableColumn<CantonPM, Number> gemeindenCol = new TableColumn<>("Gemeinden");
        gemeindenCol.setCellValueFactory(cell -> cell.getValue().gemeindenProperty());

        TableColumn<CantonPM, String> amtsspracheCol = new TableColumn<>("Amtssprachen");
        amtsspracheCol.setCellValueFactory(cell -> cell.getValue().amtsspracheProperty());

        getColumns()
                .addAll(wappenCol, nameCol, kuerzelCol, kantonsnummerCol, standesstimmeCol, beitrittCol, hauptortCol,
                        einwohnerCol, auslaenderCol, flaecheCol,
                        einwohnerdichteCol, gemeindenCol, amtsspracheCol);

    }

    @Override
    public void layoutParts() {
        // nothing to do
    }

    @Override
    public void setupBindings() {
        // damit die Tabelle (wieder) via Header sortierbar wird. Wurde erst durch die Verwendung einer FilteredList notwendig
        if (rootPM.getCantons() instanceof SortedList) {
            SortedList<CantonPM> sortedList = (SortedList<CantonPM>) rootPM.getCantons();
            sortedList.comparatorProperty().bind(comparatorProperty());
        }
    }

    /**
     * Tabellenzelle, die anstatt des Kantonskuerzels das Wappen des Kantons anzeigt.
     */
    private static class KantonTableCell extends TableCell<CantonPM, String> {
        private static final Map<String, Image> WAPPEN = new HashMap<>();
        private static final Insets INSETS = new Insets(1, 8, 1, 5);

        private final ImageView imageView;

        KantonTableCell() {
            imageView = new ImageView();
            imageView.setPreserveRatio(true);
            imageView.setFitHeight(18);
            imageView.setSmooth(true);

            setContentDisplay(ContentDisplay.GRAPHIC_ONLY);

            setAlignment(Pos.CENTER);
            setPadding(INSETS);
        }

        @Override
        protected void updateItem(String item, boolean empty) {
            setGraphic(null);
            if (item != null && !empty) {
                Image img = WAPPEN.computeIfAbsent(item,
                        s -> new Image(getClass().getResourceAsStream("/wappen_klein/" + item + ".png")));

                imageView.setImage(img);

                setGraphic(imageView);
                setTooltip(new Tooltip(item));
            }
        }
    }
}
