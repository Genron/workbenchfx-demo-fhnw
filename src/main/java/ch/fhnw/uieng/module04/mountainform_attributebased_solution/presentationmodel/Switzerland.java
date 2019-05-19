package ch.fhnw.uieng.module04.mountainform_attributebased_solution.presentationmodel;

import ch.fhnw.uieng.module04.mountainform_attributebased_solution.service.MountainService;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.Random;

/**
 * @author Dieter Holz
 */
public class Switzerland {
    public static final String TITLE_DE = "Mountain Formular";
    public static final String TITLE_EN = "Mountain Editor";

    private final StringProperty applicationTitle = new SimpleStringProperty("Mountain Editor");
    private final ObjectProperty<Language> currentLanguage = new SimpleObjectProperty<>();
    private final MountainService service;
    private MountainDetailPM currentMountain;

    public Switzerland(MountainService service) {
        this.service = service;

        long id = new Random().nextInt(service.getTotalCount()); // einen Mountain zufaellig auswaehlen
        currentMountain = MountainDetailPM.of(service.get(id));

        currentLanguage.addListener((observable, oldValue, newValue) -> {
            setApplicationTitle(newValue.equals(Language.DE) ? TITLE_DE : TITLE_EN);
            currentMountain.setLanguage(newValue);
        });

        setCurrentLanguage(Language.DE);
    }

    public void save() {
        service.save(currentMountain.toDTO());
        currentMountain.rebase();
    }

    public void revert() {
        currentMountain.revert();
    }

    public String getApplicationTitle() {
        return applicationTitle.get();
    }

    public void setApplicationTitle(String applicationTitle) {
        this.applicationTitle.set(applicationTitle);
    }

    public StringProperty applicationTitleProperty() {
        return applicationTitle;
    }

    public MountainDetailPM getCurrentMountain() {
        return currentMountain;
    }

    public Language getCurrentLanguage() {
        return currentLanguage.get();
    }

    public void setCurrentLanguage(Language currentLanguage) {
        this.currentLanguage.set(currentLanguage);
    }

    public ObjectProperty<Language> currentLanguageProperty() {
        return currentLanguage;
    }

}
