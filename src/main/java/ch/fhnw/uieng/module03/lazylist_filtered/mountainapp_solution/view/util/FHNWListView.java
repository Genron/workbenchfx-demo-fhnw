package ch.fhnw.uieng.module03.lazylist_filtered.mountainapp_solution.view.util;

import ch.fhnw.uieng.module03.lazylist_filtered.mountainapp_solution.presentationmodel.PMBase;
import ch.fhnw.uieng.module03.lazylist_filtered.mountainapp_solution.presentationmodel.util.PagingList;
import javafx.application.Platform;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Skin;
import javafx.scene.control.skin.ListViewSkin;
import javafx.scene.control.skin.VirtualFlow;
import javafx.util.Callback;

/**
 * @author Dieter Holz
 */
public class FHNWListView<PM extends PMBase<?>> extends ListView<PM> {

    public FHNWListView(Callback<ListView<PM>, ListCell<PM>> cellFactory) {
        setCellFactory(cellFactory);
    }

    @Override
    protected Skin<?> createDefaultSkin() {
        Skin<?> defaultSkin = super.createDefaultSkin();

        VirtualFlow flow = getVirtualFlow((ListViewSkin) defaultSkin);

        flow.heightProperty().addListener((observable, oldValue, newValue) -> {
            Platform.runLater(() -> {
                if (flow.getFirstVisibleCell() != null) {
                    getPagingList().setVisibleRows(visibleRows(flow));
                    getPagingList().setFirstIndexShown(flow.getFirstVisibleCell().getIndex());
                    if (newValue.doubleValue() > oldValue.doubleValue()) {
                        getPagingList().reload();
                    }
                }
            });
        });

        flow.positionProperty().addListener((observable, oldValue, newPosition) -> {
            PagingList ll = getPagingList();
            int size = ll.size();
            int first = (int) (newPosition.doubleValue() * (size - visibleRows(flow)));
            ll.setFirstIndexShown(first);
        });

        itemsProperty().addListener((observable, oldValue, newValue) -> {
            PagingList pagingList = (PagingList) newValue;
            pagingList.setVisibleRows(visibleRows(flow));
            pagingList.setFirstIndexShown(0);
        });

        return defaultSkin;
    }

    private PagingList getPagingList() {
        return (PagingList) getItems();
    }

    private VirtualFlow getVirtualFlow(ListViewSkin skin) {
        return (VirtualFlow) skin.getChildren().stream()
                .filter(VirtualFlow.class::isInstance)
                .findAny()
                .orElse(null);
    }

    private int visibleRows(VirtualFlow flow) {
        return (flow.getLastVisibleCell() == null || flow.getFirstVisibleCell() == null) ?
                30 :
                flow.getLastVisibleCell().getIndex() - flow.getFirstVisibleCell().getIndex() + 1;
    }


}
