package ch.fhnw.uieng.module03.lazylist_filtered.mountainapp_solution.service;

import java.util.List;

/**
 * @author Dieter Holz
 */
public class Page<DTO> {
    private final int filteredCount;
    private final int totalCount;
    private final int start;
    private final int end;
    private final List<DTO> items;

    public Page(int filteredCount, int totalCount, int start, int end, List<DTO> items) {
        this.filteredCount = filteredCount;
        this.totalCount = totalCount;
        this.start = start;
        this.end = end;
        this.items = items;
    }

    public int getFilteredCount() {
        return filteredCount;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }

    public List<DTO> getItems() {
        return items;
    }
}
