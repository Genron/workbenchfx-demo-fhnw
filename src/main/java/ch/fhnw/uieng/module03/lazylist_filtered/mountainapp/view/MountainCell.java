package ch.fhnw.uieng.module03.lazylist_filtered.mountainapp.view;

import ch.fhnw.uieng.module03.lazylist_filtered.mountainapp.presentationmodel.MountainPM;
import ch.fhnw.uieng.module03.lazylist_filtered.mountainapp.view.util.RoundImageView;
import ch.fhnw.uieng.module03.lazylist_filtered.mountainapp.view.util.ViewMixin;
import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.apache.commons.collections4.map.LRUMap;

import java.util.Collections;
import java.util.Map;

/**
 * @author Dieter Holz
 */
public class MountainCell extends ListCell<MountainPM> {

    private final Description description = new Description();

    public MountainCell() {
        setText(null);
        setGraphic(description);
    }

    @Override
    protected void updateItem(MountainPM item, boolean empty) {
        super.updateItem(item, empty);

        if (!empty && item != null) {
            description.bind(item);
        } else {
            description.unbind();
        }
    }

    private static class Description extends HBox implements ViewMixin {
        private static final String NO_IMG = MountainCell.class.getResource("/images/noImg.jpg").toExternalForm();
        private static final int MAX_SIZE = 60;

        // Verwenden eines Least-Recently-Used-Caches

        private final Map<String, Image> imageCache = Collections.synchronizedMap(new LRUMap<>(MAX_SIZE));

        private RoundImageView imageView;
        private Label nameLabel;
        private Label heightLabel;
        private Label regionLabel;

        Description() {
            init();
        }

        @Override
        public void initializeParts() {
            imageView = new RoundImageView();

            nameLabel = new Label();
            nameLabel.getStyleClass().add("list-title");

            heightLabel = new Label();
            heightLabel.getStyleClass().add("list-description");

            regionLabel = new Label();
            regionLabel.getStyleClass().add("list-description");
        }

        @Override
        public void layoutParts() {
            VBox vBox = new VBox();
            vBox.setPadding(new Insets(0, 5, 0, 5));
            vBox.getChildren().addAll(nameLabel, heightLabel, regionLabel);

            getChildren().addAll(imageView, vBox);
        }

        /**
         * Es ist wichtig hier Bindings zu verwenden. Nur einmaliges Setzen der neuen Werte reicht nicht aus.
         * <p>
         * Wir setzen auf LazyLoading. Die Werte werden also erst mal mit Platzhaltern gefüllt, die dann ersetzt
         * werden sobald der asychrone Service-Call zurückkommt. Nur durch ein Binding wird diese Wertänderung
         * auch in der Cell angezeigt.
         *
         * @param mountain der aktuell in dieser Zelle dargestellte Mountain
         */
        private void bind(MountainPM mountain) {
            // das image wird hier aufgebaut. Es ist in der Entscheidung des Views ob die imageURL als Bild oder
            // eventuell als String dargestellt werden soll.
            imageView.imageProperty().bind(Bindings.createObjectBinding(() -> getImage(mountain.getImageURL()),
                    mountain.imageURLProperty()));
            nameLabel.textProperty().bind(mountain.nameProperty());
            heightLabel.textProperty().bind(mountain.heightProperty().asString("%.2f m"));
            regionLabel.textProperty().bind(mountain.regionProperty());
        }

        /**
         * Falls die Zelle "empty" wird, das kann bei den untersten Zellen der Fall sein, müssen die Bindings
         * zurückgesetzt werden, ansonsten werden die alten Werte weiterhin sichtbar.
         */
        private void unbind() {
            imageView.imageProperty().unbind();
            nameLabel.textProperty().unbind();
            heightLabel.textProperty().unbind();
            regionLabel.textProperty().unbind();
        }

        private Image getImage(String imageURL) {
            final String lookupURL = (imageURL == null || imageURL.isEmpty()) ?
                    NO_IMG :
                    imageURL;

            return imageCache.computeIfAbsent(lookupURL, s -> new Image(lookupURL, true));
        }

    }
}
