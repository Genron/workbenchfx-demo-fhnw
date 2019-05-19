package ch.fhnw.uieng.module02.cantonfiltered_solution.presentationmodel;

import ch.fhnw.uieng.module02.cantonfiltered_solution.service.CantonService;
import javafx.beans.binding.Bindings;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;

import java.util.stream.Collectors;

/**
 * Switzerland ist der zentrale Einstieg in den PresentationModel-Layer.
 * <p>
 * Von hier aus sind alle darzustellenden Informationen zugreifbar.
 * <p>
 * Von Switzerland gibt es genau eine Instanz.
 *
 * @author Dieter Holz
 */
public class Switzerland {
    private final StringProperty applicationTitle = new SimpleStringProperty("Kantone der Schweiz");

    private final StringProperty filter = new SimpleStringProperty();   // der aktuelle Filter String
    private final IntegerProperty totalCount = new SimpleIntegerProperty();  // so viele Kantone gibt es insgesamt
    private final IntegerProperty filteredCount = new SimpleIntegerProperty();  // so viele enthalten den Filter String

    // hier sind alle 26 Kantone drin
    private final ObservableList<CantonPM> allCantons = FXCollections.observableArrayList();

    // hier sind nur die drin, die dem eingegebenen Filter entsprechen
    private final FilteredList<CantonPM> filteredCantons = new FilteredList<>(allCantons);

    // sortiert die gefilterte Liste anhand eines Attributs
    private final SortedList<CantonPM> sortedCantons = new SortedList<>(filteredCantons);

    private final CantonService service;

    public Switzerland(CantonService service) {
        this.service = service;

        setupValueChangedListeners();
        setupBindings();

        // die Daten können bei Eager-Loading initial gelesen werden.
        allCantons.addAll(service.findAll().stream()
                .map(CantonPM::of)
                .collect(Collectors.toList()));

    }

    public ObservableList<CantonPM> getCantons() {
        return sortedCantons;
    }

    private void setupValueChangedListeners() {
        //immer wenn der Filter-String sich ändert, muss die filteredCantons-Liste neu berechnet werden
        filterProperty().addListener((observable, oldValue, newValue) -> applyFilterString(newValue));
    }

    private void setupBindings() {
        totalCount.bind(Bindings.size(allCantons));
        filteredCount.bind(Bindings.size(filteredCantons));
    }

    private void applyFilterString(String newValue) {
        filteredCantons.setPredicate(cantonPM -> {
            if (newValue == null || newValue.isEmpty()) {
                return true;
            }

            String filterString = newValue.toLowerCase();
            String name = cantonPM.getKanton().toLowerCase();
            String kuerzel = cantonPM.getKuerzel().toLowerCase();

            return filterString.equals(kuerzel) || name.contains(filterString);
        });
    }


    // alle Getter und Setter
    public String getApplicationTitle() {
        return applicationTitle.get();
    }

    public void setApplicationTitle(String applicationTitle) {
        this.applicationTitle.set(applicationTitle);
    }

    public StringProperty applicationTitleProperty() {
        return applicationTitle;
    }

    public String getFilter() {
        return filter.get();
    }

    public void setFilter(String filter) {
        this.filter.set(filter);
    }

    public StringProperty filterProperty() {
        return filter;
    }

    public int getTotalCount() {
        return totalCount.get();
    }

    public void setTotalCount(int totalCount) {
        this.totalCount.set(totalCount);
    }

    public IntegerProperty totalCountProperty() {
        return totalCount;
    }

    public int getFilteredCount() {
        return filteredCount.get();
    }

    public void setFilteredCount(int filteredCount) {
        this.filteredCount.set(filteredCount);
    }

    public IntegerProperty filteredCountProperty() {
        return filteredCount;
    }
}
