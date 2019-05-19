package ch.fhnw.uieng.module03.lazylist_filtered.mountainapp_solution.service;

/**
 * @author Dieter Holz
 */
public interface PagingService<DTO> {
    Page<DTO> getPage(int start, int pageSize, String filter);

    int getFilteredCount(String filter);
}
