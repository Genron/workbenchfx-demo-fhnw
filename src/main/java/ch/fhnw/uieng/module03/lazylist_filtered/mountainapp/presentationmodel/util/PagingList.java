package ch.fhnw.uieng.module03.lazylist_filtered.mountainapp.presentationmodel.util;

import ch.fhnw.uieng.module03.lazylist_filtered.mountainapp.presentationmodel.PMBase;
import ch.fhnw.uieng.module03.lazylist_filtered.mountainapp.service.PagingService;
import javafx.beans.binding.Bindings;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableListBase;
import javafx.collections.ObservableMap;
import javafx.concurrent.Service;
import javafx.concurrent.Task;

import java.time.Duration;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Implementation of an ObservableList as required build a JavaFX UI supporting lazy loading
 * <p>
 * The list contains the data that are actually displayed by a TableView or ListView plus
 * some additional records before and after the current viewport to avoid reloading
 * on small scrolling movements.
 * <p>
 * The corresponding service calls are asynchronously.
 * <p>
 * PLEASE NOTE: This implementation is for educational purposes.
 * <p>
 * It contains features to explore the consequences of slow backend service and of different page sizes.
 * <p>
 * Moreover it makes assumptions, e.g. that the total number of elements doesn't change, that are not
 * valid in real world applications.
 *
 * @author Dieter Holz
 */
public class PagingList<PM extends PMBase<DTO>, DTO> extends ObservableListBase<PM> {

    private final PagingService<DTO> service;
    private final Function<Integer, PM> pmFactory;

    private final ObservableMap<Integer, PM> cache = FXCollections.observableHashMap();

    private final IntegerProperty firstIndexShown = new SimpleIntegerProperty(-1);
    private final IntegerProperty visibleRows = new SimpleIntegerProperty(30);

    // only for monitoring reasons
    private final IntegerProperty cacheLevel = new SimpleIntegerProperty();
    private final IntegerProperty serviceCalls = new SimpleIntegerProperty();
    private final IntegerProperty pageSizeFactor = new SimpleIntegerProperty(2);
    private final IntegerProperty sleep = new SimpleIntegerProperty(100);
    private final TaskBatcher taskBatcher = new TaskBatcher(Duration.ofMillis(80));
    private int actualFirstInCache = -99999;
    private ServiceCaller currentServiceCall;
    private int size = -1;

    /**
     * A LazyLoading list needs to know which service needs to be called to get the data
     * and how to build an empty instance of the PresentationModel managed by the list.
     *
     * @param service   the service to get the required data
     * @param pmFactory builds an empty instance of the PresentationModel
     */
    public PagingList(PagingService<DTO> service, Function<Integer, PM> pmFactory) {
        this.service = service;
        this.pmFactory = pmFactory;

        firstIndexShown.addListener((observable, oldValue, newValue) -> taskBatcher.batch(() -> loadNextPage(newValue.intValue())));

        cacheLevel.bind(Bindings.size(cache));
        loadNextPage(0);
    }

    /**
     * Immediately return an empty PresentationModel in case it's not available.
     * <p>
     * Whenever the corresponding service call returns the results will be applied to the pm.
     */
    @Override
    public PM get(int index) {
        return cache.computeIfAbsent(index, pmFactory);
    }

    /**
     * PLEASE NOTE: the underlying assumption is that the number of elements doesn't change
     * That's not given in real world applications, of course.
     *
     * @return the total number of elements in the data store
     */
    @Override
    public int size() {
        if (size == -1) {
            size = service.getTotalCount();
        }
        return size;
    }

    public void stopServiceCalls() {
        if (currentServiceCall != null) {
            currentServiceCall.cancel();
        }
    }

    private void loadNextPage(int startIndex) {
        int pageSize = getVisibleRows() * getPageSizeFactor();
        if (startIndex >= actualFirstInCache && startIndex + getVisibleRows() <= actualFirstInCache + 2 * pageSize) {
            return;
        }
        int firstIndex = Math.max(0, startIndex - pageSize);
        int lastIndex = firstIndex + 2 * pageSize;

        List<Integer> toBeRemovedFromCache = cache.keySet().stream()
                .filter(idx -> idx < firstIndex || idx > lastIndex)
                .collect(Collectors.toList());
        toBeRemovedFromCache.forEach(cache::remove);

        serviceCalls.setValue(serviceCalls.get() + 1);
        actualFirstInCache = firstIndex;

        // if there is another service call on the way we don't need the results any more; it can be cancelled
        if (currentServiceCall != null) {
            currentServiceCall.cancel();
        }
        currentServiceCall = new ServiceCaller(firstIndex, 2 * pageSize);
        currentServiceCall.start();
    }

    public int getFirstIndexShown() {
        return firstIndexShown.get();
    }

    // all the getters and setters

    public void setFirstIndexShown(int firstIndexShown) {
        this.firstIndexShown.set(firstIndexShown);
    }

    public IntegerProperty firstIndexShownProperty() {
        return firstIndexShown;
    }

    public int getVisibleRows() {
        return visibleRows.get();
    }

    public void setVisibleRows(int visibleRows) {
        this.visibleRows.set(visibleRows);
    }

    public IntegerProperty visibleRowsProperty() {
        return visibleRows;
    }

    public ReadOnlyIntegerProperty cacheLevelProperty() {
        return cacheLevel;
    }

    public ReadOnlyIntegerProperty serviceCallsProperty() {
        return serviceCalls;
    }

    public int getPageSizeFactor() {
        return pageSizeFactor.get();
    }

    public IntegerProperty pageSizeFactorProperty() {
        return pageSizeFactor;
    }

    public IntegerProperty sleepProperty() {
        return sleep;
    }

    /**
     * Asynchronous service call to avoid UI freezing.
     */
    private class ServiceCaller extends Service<List<DTO>> {
        private final int start;
        private final int pageSize;

        ServiceCaller(int start, int pageSize) {
            this.start = start;
            this.pageSize = pageSize;
            setOnSucceeded(event -> {
                for (int i = 0; i < getValue().size(); i++) {
                    PM pm = cache.computeIfAbsent(start + i, pmFactory);
                    pm.apply(getValue().get(i));
                }
                currentServiceCall = null;
            });
        }

        @Override
        protected Task<List<DTO>> createTask() {
            return new Task<List<DTO>>() {
                @Override
                protected List<DTO> call() throws Exception {

                    // this is for simulating long running backend services
                    // in a productive system this has to be removed of course
                    Thread.sleep(sleep.get());

                    return service.getPage(start, pageSize);
                }
            };
        }
    }
}
