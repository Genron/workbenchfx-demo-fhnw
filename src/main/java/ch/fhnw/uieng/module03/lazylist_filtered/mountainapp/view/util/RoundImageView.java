package ch.fhnw.uieng.module03.lazylist_filtered.mountainapp.view.util;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.Control;
import javafx.scene.control.Skin;
import javafx.scene.image.Image;

public class RoundImageView extends Control {
    private final SimpleObjectProperty<Image> image = new SimpleObjectProperty<>();
    private final SimpleDoubleProperty defaultSize = new SimpleDoubleProperty(64.0);

    public RoundImageView() {
        getStyleClass().add("round-image-view");
    }

    @Override
    protected Skin<?> createDefaultSkin() {
        return new RoundImageViewSkin(this);
    }

    public SimpleDoubleProperty defaultSizeProperty() {
        return defaultSize;
    }

    public SimpleObjectProperty<Image> imageProperty() {
        return image;
    }

    public Image getImage() {
        return image.get();
    }

    public void setImage(Image image) {
        this.image.set(image);
    }

    public double getDefaultSize() {
        return defaultSize.get();
    }

    public void setDefaultSize(double defaultSize) {
        this.defaultSize.set(defaultSize);
    }
}
