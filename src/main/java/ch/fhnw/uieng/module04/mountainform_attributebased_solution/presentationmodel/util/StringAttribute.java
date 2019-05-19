package ch.fhnw.uieng.module04.mountainform_attributebased_solution.presentationmodel.util;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * @author Dieter Holz
 */
public class StringAttribute extends Attribute<StringProperty, String, StringAttribute> {
    public StringAttribute(SimpleStringProperty value, SimpleStringProperty persistentValue) {
        super(value, persistentValue);
        setUserInput(value.get());
        userInputProperty().bindBidirectional(valueProperty());
    }

    @Override
    public String asString() {
        return getValue();
    }
}
