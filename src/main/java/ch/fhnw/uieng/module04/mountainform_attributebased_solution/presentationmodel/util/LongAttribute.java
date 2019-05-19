package ch.fhnw.uieng.module04.mountainform_attributebased_solution.presentationmodel.util;

import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;

/**
 * @author Dieter Holz
 */
public class LongAttribute extends Attribute<LongProperty, Long, LongAttribute> {
    private static final long VALUE_NOT_SET = Long.MIN_VALUE;

    public LongAttribute(SimpleLongProperty value, SimpleLongProperty persistentValue) {
        super(value, persistentValue);

        userInputProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.isEmpty()) {
                if (isMandatory()) {
                    setValid(false);
                    setValidationMessage("Mandatory Field");
                } else {
                    setValid(true);
                    setValidationMessage("OK");
                }
                setValue(VALUE_NOT_SET);
            } else {
                try {
                    setValid(true);
                    setValidationMessage("OK");
                    setValue(Long.valueOf(newValue));
                } catch (NumberFormatException e) {
                    setValid(false);
                    setValidationMessage("Not a Double");
                }
            }
        });

        valueProperty().addListener((observable, oldValue, newValue) -> setUserInput(newValue.longValue() == VALUE_NOT_SET ? "" : String.format("%d", newValue.longValue())));
    }

    @Override
    public String asString() {
        return asString("%d");
    }


}
