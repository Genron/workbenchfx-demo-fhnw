package ch.fhnw.uieng.module03.lazylist_filtered.mountainapp.service;

import java.util.List;

/**
 * @author Dieter Holz
 */
public interface PagingService<DTO> {
    int getTotalCount();

    List<DTO> getPage(int start, int pageSize);
}
