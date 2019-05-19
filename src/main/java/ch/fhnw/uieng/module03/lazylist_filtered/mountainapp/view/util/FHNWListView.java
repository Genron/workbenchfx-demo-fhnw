package ch.fhnw.uieng.module03.lazylist_filtered.mountainapp.view.util;

import ch.fhnw.uieng.module03.lazylist_filtered.mountainapp.presentationmodel.PMBase;
import ch.fhnw.uieng.module03.lazylist_filtered.mountainapp.presentationmodel.util.PagingList;
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

    public FHNWListView(PagingList<PM, ?> items, Callback<ListView<PM>, ListCell<PM>> cellFactory) {
        super(items);
        setCellFactory(cellFactory);
    }

    @Override
    protected Skin<?> createDefaultSkin() {
        return new ListViewSkin<PM>(this) {

            protected VirtualFlow<ListCell<PM>> createVirtualFlow() {
                return new VirtualFlowX();
            }
        };
    }

    private PagingList getLazyList() {
        return (PagingList) getItems();
    }

    private class VirtualFlowX extends VirtualFlow<ListCell<PM>> {
        public VirtualFlowX() {
            heightProperty().addListener((observable, oldValue, newValue) -> {
                if (getFirstVisibleCell() != null) {
                    getLazyList().setVisibleRows(visibleRows());
                    getLazyList().setFirstIndexShown(getFirstVisibleCell().getIndex());
                }
            });
        }

//        @Override
//        public void setPosition(double newPosition) {
//            PagingList ll    = getLazyList();
//            int        size  = ll.size();
//            int        first = (int) (newPosition * (size - visibleRows()));
//            ll.setFirstIndexShown(first);
//
//            super.setPosition(newPosition);
//        }

        @Override
        protected void layoutChildren() {
            super.layoutChildren();
            updateVisibleRows();
        }

        private void updateVisibleRows() {
            if (getFirstVisibleCell() != null) {
                getLazyList().setVisibleRows(visibleRows());
            }
        }

        private int visibleRows() {
            return getLastVisibleCell().getIndex() - getFirstVisibleCell().getIndex() + 1;
        }

    }
}
