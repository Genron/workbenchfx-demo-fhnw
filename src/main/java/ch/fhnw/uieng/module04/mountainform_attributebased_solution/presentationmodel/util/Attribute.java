package ch.fhnw.uieng.module04.mountainform_attributebased_solution.presentationmodel.util;

import ch.fhnw.uieng.module04.mountainform_attributebased_solution.presentationmodel.Language;
import javafx.beans.binding.Bindings;
import javafx.beans.property.*;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * @author Dieter Holz
 */
public abstract class Attribute<P extends Property, E, A extends Attribute> {
    public static final Locale CH = new Locale("de", "CH");
    private final P value;
    private final P persistentValue;
    private final StringProperty userInput = new SimpleStringProperty();
    private final StringProperty label = new SimpleStringProperty();
    private final BooleanProperty mandatory = new SimpleBooleanProperty(false);
    private final BooleanProperty readOnly = new SimpleBooleanProperty(false);
    private final BooleanProperty valid = new SimpleBooleanProperty(true);
    private final StringProperty validationMessage = new SimpleStringProperty();
    private final BooleanProperty dirty = new SimpleBooleanProperty(false);
    private Map<Language, String> labels = new HashMap<>();

    protected Attribute(P valueProperty, P persistentValueProperty) {
        value = valueProperty;
        persistentValue = persistentValueProperty;
        dirtyProperty().bind(Bindings.createBooleanBinding(() -> !persistentValueProperty().getValue().equals(valueProperty().getValue()),
                valueProperty(), persistentValueProperty()));

    }

    public static DoubleAttribute of(double value) {
        return new DoubleAttribute(new SimpleDoubleProperty(value), new SimpleDoubleProperty(value));
    }

    public static LongAttribute of(long value) {
        return new LongAttribute(new SimpleLongProperty(value), new SimpleLongProperty(value));
    }

    public static StringAttribute of(String value) {
        return new StringAttribute(new SimpleStringProperty(value), new SimpleStringProperty(value));
    }

    public A label(Language lang, String label) {
        labels.put(lang, label);

        return (A) this;
    }

    public A mandatory(boolean mandatory) {
        setMandatory(mandatory);

        return (A) this;
    }

    public A readOnly(boolean readOnly) {
        setReadOnly(readOnly);

        return (A) this;
    }

    public void setLanguage(Language lang) {
        setLabel(labels.getOrDefault(lang, "some Label"));
    }

    public void rebase() {
        persistentValue.setValue(value.getValue());
    }

    public void revert() {
        value.setValue(persistentValue.getValue());
        setUserInput(String.valueOf(getValue()));
    }

    public abstract String asString();

    public String asString(String formatPattern) {
        return String.format(CH, formatPattern, getValue());
    }

    public String getLabel() {
        return label.get();
    }

    public void setLabel(String label) {
        this.label.set(label);
    }

    public StringProperty labelProperty() {
        return label;
    }

    public E getValue() {
        return (E) value.getValue();
    }

    public void setValue(E value) {
        this.value.setValue(value);
    }

    public P valueProperty() {
        return value;
    }

    public E getPersistentValue() {
        return (E) persistentValue.getValue();
    }

    public void setPersistentValue(E persistentValue) {
        this.persistentValue.setValue(persistentValue);
    }

    public P persistentValueProperty() {
        return persistentValue;
    }

    public boolean isMandatory() {
        return mandatory.get();
    }

    public void setMandatory(boolean mandatory) {
        this.mandatory.set(mandatory);
    }

    public BooleanProperty mandatoryProperty() {
        return mandatory;
    }

    public boolean isValid() {
        return valid.get();
    }

    public void setValid(boolean valid) {
        this.valid.set(valid);
    }

    public BooleanProperty validProperty() {
        return valid;
    }

    public String getValidationMessage() {
        return validationMessage.get();
    }

    public void setValidationMessage(String validationMessage) {
        this.validationMessage.set(validationMessage);
    }

    public StringProperty validationMessageProperty() {
        return validationMessage;
    }

    public boolean isDirty() {
        return dirty.get();
    }

    public void setDirty(boolean dirty) {
        this.dirty.set(dirty);
    }

    public BooleanProperty dirtyProperty() {
        return dirty;
    }

    public boolean isReadOnly() {
        return readOnly.get();
    }

    public void setReadOnly(boolean readOnly) {
        this.readOnly.set(readOnly);
    }

    public BooleanProperty readOnlyProperty() {
        return readOnly;
    }

    public String getUserInput() {
        return userInput.get();
    }

    public void setUserInput(String userInput) {
        this.userInput.set(userInput);
    }

    public StringProperty userInputProperty() {
        return userInput;
    }

}
