package ch.fhnw.uieng.module04.mountainform_attributebased_solution.presentationmodel;

import ch.fhnw.uieng.module04.mountainform_attributebased_solution.presentationmodel.util.Attribute;
import ch.fhnw.uieng.module04.mountainform_attributebased_solution.presentationmodel.util.DoubleAttribute;
import ch.fhnw.uieng.module04.mountainform_attributebased_solution.presentationmodel.util.LongAttribute;
import ch.fhnw.uieng.module04.mountainform_attributebased_solution.presentationmodel.util.StringAttribute;
import ch.fhnw.uieng.module04.mountainform_attributebased_solution.service.MountainDetailDTO;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;

import java.util.List;
import java.util.function.Consumer;

/**
 * @author Dieter Holz
 */
public class MountainDetailPM implements PMBase<MountainDetailDTO> {
    private static final String ELLIPSIS = "...";
    private static final String BASE_URL = "https://dieterholz.github.io/StaticResources/mountainpictures/";

    private final LongAttribute id = Attribute.of(0L)
            .label(Language.DE, "ID")
            .label(Language.UK, "ID")
            .readOnly(true);

    private final StringAttribute name = Attribute.of(ELLIPSIS)
            .label(Language.DE, "Name")
            .label(Language.UK, "Name")
            .mandatory(true);

    private final DoubleAttribute height = Attribute.of(0.0)
            .label(Language.DE, "H\u00f6he (m)")
            .label(Language.UK, "Height");

    private final StringAttribute type = Attribute.of(ELLIPSIS)
            .label(Language.DE, "Typ")
            .label(Language.UK, "Type");

    private final StringAttribute region = Attribute.of(ELLIPSIS)
            .label(Language.DE, "Region")
            .label(Language.UK, "Region");

    private final StringAttribute cantons = Attribute.of(ELLIPSIS)
            .label(Language.DE, "Kantone")
            .label(Language.UK, "Cantons");

    private final StringAttribute range = Attribute.of(ELLIPSIS)
            .label(Language.DE, "Gebiet")
            .label(Language.UK, "Range");

    private final DoubleAttribute isolation = Attribute.of(0.0)
            .label(Language.DE, "Dominanz")
            .label(Language.UK, "Isolation");

    private final StringAttribute isolationPoint = Attribute.of(ELLIPSIS)
            .label(Language.DE, "km bis")
            .label(Language.UK, "Isolation Point");

    private final DoubleAttribute prominence = Attribute.of(0.0)
            .label(Language.DE, "Schartenh\u00f6he")
            .label(Language.UK, "Prominence");

    private final StringAttribute prominencePoint = Attribute.of(ELLIPSIS)
            .label(Language.DE, "m bis")
            .label(Language.UK, "Prominence Point");

    private final StringAttribute caption = Attribute.of(ELLIPSIS)
            .label(Language.DE, "Bildunterschrift")
            .label(Language.UK, "Caption");

    private final StringAttribute imageURL = Attribute.of(ELLIPSIS)
            .label(Language.DE, "Bild URL")
            .label(Language.UK, "Image URL");

    private final List<Attribute> allMyAttributes = List.of(
            getId(),
            getName(),
            getHeight(),
            getType(),
            getRegion(),
            getCantons(),
            getRange(),
            getIsolation(),
            getIsolationPoint(),
            getProminence(),
            getProminencePoint(),
            getCaption(),
            getImageURL());

    private final BooleanProperty dirty = new SimpleBooleanProperty(false);
    private final ChangeListener<Boolean> dirtyStateListener = (observable, oldValue, newValue) -> checkDirtyState();

    private final BooleanProperty valid = new SimpleBooleanProperty(true);
    private final ChangeListener<Boolean> validStateListener = (observable, oldValue, newValue) -> checkValidState();

    public MountainDetailPM() {
        setupValueChangeListener();
    }

    public static MountainDetailPM of(MountainDetailDTO dto) {
        MountainDetailPM pm = new MountainDetailPM();
        pm.apply(dto);

        pm.rebase();

        return pm;
    }

    @Override
    public void apply(MountainDetailDTO dto) {
        getId().setValue((dto.getId()));
        getName().setValue(dto.getName());
        getHeight().setValue(dto.getHeight());
        getRegion().setValue(dto.getRegion());
        getType().setValue(dto.getType());
        getCantons().setValue(dto.getCantons());
        getRange().setValue(dto.getRange());
        getIsolation().setValue(dto.getIsolation());
        getIsolationPoint().setValue(dto.getIsolationPoint());
        getProminence().setValue(dto.getProminence());
        getProminencePoint().setValue(dto.getProminencePoint());
        getCaption().setValue(dto.getCaption());
        getImageURL().setValue(BASE_URL + dto.getId() + "-1.jpg");
    }

    @Override
    public MountainDetailDTO toDTO() {
        return new MountainDetailDTO(getId().asString(),
                getName().asString(),
                getHeight().asString(),
                getRegion().asString(),
                getType().asString(),
                getCantons().asString(),
                getRange().asString(),
                getIsolation().asString(),
                getIsolationPoint().asString(),
                getProminence().asString(),
                getProminencePoint().asString(),
                getCaption().asString()
        );
    }

    @Override
    public void setLanguage(Language lang) {
        forAllAttributes(attribute -> attribute.setLanguage(lang));
    }

    @Override
    public void rebase() {
        forAllAttributes(Attribute::rebase);
    }

    @Override
    public void revert() {
        forAllAttributes(Attribute::revert);
    }

    private void setupValueChangeListener() {
        imageURL.valueProperty().addListener((observable, oldValue, newValue) -> caption.setMandatory(newValue != null &&
                !newValue.isEmpty() &&
                (newValue.startsWith("http:") || newValue.startsWith(
                        "https:"))));

        cantons.valueProperty().addListener((observable, oldValue, newValue) -> getRange().setReadOnly(newValue.contains("AG")));

        isolation.userInputProperty().addListener((observable, oldValue, newValue) -> getIsolationPoint().setMandatory(!newValue.isEmpty()));

        prominence.userInputProperty().addListener((observable, oldValue, newValue) -> getProminencePoint().setMandatory(!newValue.isEmpty()));

        height.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.doubleValue() < 0 || newValue.doubleValue() >= 6000) {
                height.setValid(false);
                height.setValidationMessage("Not a Swiss mountain");
            }
        });

        forAllAttributes(attribute -> attribute.dirtyProperty().addListener(dirtyStateListener));
        forAllAttributes(attribute -> attribute.validProperty().addListener(validStateListener));
    }

    private void checkDirtyState() {
        dirtyProperty().set(someAttributeDirty());
    }

    private void checkValidState() {
        validProperty().set(allAttributesValid());
    }

    private boolean allAttributesValid() {
        return allMyAttributes.stream().allMatch(Attribute::isValid);
    }

    private boolean someAttributeDirty() {
        return allMyAttributes.stream().anyMatch(Attribute::isDirty);
    }

    private void forAllAttributes(Consumer<Attribute> consumer) {
        allMyAttributes.forEach(consumer);
    }

    // alle Getter und Setter

    public LongAttribute getId() {
        return id;
    }

    public StringAttribute getName() {
        return name;
    }

    public DoubleAttribute getHeight() {
        return height;
    }

    public StringAttribute getType() {
        return type;
    }

    public StringAttribute getRegion() {
        return region;
    }

    public StringAttribute getCantons() {
        return cantons;
    }

    public StringAttribute getRange() {
        return range;
    }

    public DoubleAttribute getIsolation() {
        return isolation;
    }

    public StringAttribute getIsolationPoint() {
        return isolationPoint;
    }

    public DoubleAttribute getProminence() {
        return prominence;
    }

    public StringAttribute getProminencePoint() {
        return prominencePoint;
    }

    public StringAttribute getCaption() {
        return caption;
    }

    public StringAttribute getImageURL() {
        return imageURL;
    }

    public boolean isDirty() {
        return dirty.get();
    }

    @Override
    public BooleanProperty dirtyProperty() {
        return dirty;
    }

    public boolean isValid() {
        return valid.get();
    }

    @Override
    public BooleanProperty validProperty() {
        return valid;
    }

}
