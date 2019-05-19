package ch.fhnw.uieng.module03.lazylist_filtered.mountainapp.presentationmodel;

import ch.fhnw.uieng.module03.lazylist_filtered.mountainapp.presentationmodel.util.PagingList;
import ch.fhnw.uieng.module03.lazylist_filtered.mountainapp.service.MountainDTO;
import ch.fhnw.uieng.module03.lazylist_filtered.mountainapp.service.PagingService;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * @author Dieter Holz
 */
public class Switzerland {

    private final PagingService<MountainDTO> service;

    private final StringProperty applicationTitle = new SimpleStringProperty("Schweizer Berge");
    private final StringProperty filter = new SimpleStringProperty();

    private final PagingList<MountainPM, MountainDTO> allMountains;

    public Switzerland(PagingService<MountainDTO> service) {
        this.service = service;

        allMountains = new PagingList<>(service, index -> new MountainPM());
    }

    public PagingList<MountainPM, ?> getMountains() {
        return allMountains;
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

    public String getApplicationTitle() {
        return applicationTitle.get();
    }

    public void setApplicationTitle(String applicationTitle) {
        this.applicationTitle.set(applicationTitle);
    }

    public StringProperty applicationTitleProperty() {
        return applicationTitle;
    }

    public void applicationStopped() {
        allMountains.stopServiceCalls();

    }
}
