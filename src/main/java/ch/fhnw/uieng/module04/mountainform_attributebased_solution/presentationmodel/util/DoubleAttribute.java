package ch.fhnw.uieng.module04.mountainform_attributebased_solution.presentationmodel.util;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

/**
 * @author Dieter Holz
 */
public class DoubleAttribute extends Attribute<DoubleProperty, Double, DoubleAttribute> {
    private static final double VALUE_NOT_SET = Double.MIN_VALUE;

    public DoubleAttribute(SimpleDoubleProperty value, SimpleDoubleProperty persistentValue) {
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
                    setValue(Double.valueOf(newValue));
                } catch (NumberFormatException e) {
                    setValid(false);
                    setValidationMessage("Not a Double");
                }
            }
        });

        valueProperty().addListener((observable, oldValue, newValue) -> setUserInput(newValue.doubleValue() == VALUE_NOT_SET ? "" : String.valueOf(newValue)));
    }

    @Override
    public String asString() {
        return asString("%f");
    }

}
