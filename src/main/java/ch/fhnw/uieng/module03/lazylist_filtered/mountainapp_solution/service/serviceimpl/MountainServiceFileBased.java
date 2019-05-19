package ch.fhnw.uieng.module03.lazylist_filtered.mountainapp_solution.service.serviceimpl;

import ch.fhnw.uieng.module03.lazylist_filtered.mountainapp_solution.service.MountainDTO;
import ch.fhnw.uieng.module03.lazylist_filtered.mountainapp_solution.service.Page;
import ch.fhnw.uieng.module03.lazylist_filtered.mountainapp_solution.service.PagingService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Dieter Holz
 */
public class MountainServiceFileBased implements PagingService<MountainDTO> {

    private static final String FILE_NAME = "/data/mountains.csv";
    private static final String DELIMITER = ";";
    private static final int NUMBER_OF_COLUMNS = 12;

    @Override
    public Page<MountainDTO> getPage(int start, int pageSize, String filter) {
        int filteredCount = getFilteredCount(filter);

        List<MountainDTO> items = getItems(start, Math.min(pageSize, filteredCount), filter);

        return new Page<>(filteredCount,
                getTotalCount(),
                start,
                start + items.size(),
                items);
    }

    @Override
    public int getFilteredCount(String filter) {
        return convertAllLines(streamOfLines -> (int) streamOfLines.skip(1)
                .filter(line -> contains(line, filter))
                .count());
    }

    private int getTotalCount() {
        return convertAllLines(streamOfLines -> (int) streamOfLines.skip(1)
                .count());
    }

    private List<MountainDTO> getItems(int start, int pageSize, String filter) {
        return convertAllLines(streamOfLines -> streamOfLines.skip(start + 1)
                .filter(line -> contains(line, filter))
                .map(s -> new MountainDTO(s.split(DELIMITER, NUMBER_OF_COLUMNS)))
                .limit(pageSize)
                .collect(Collectors.toList()));
    }

    private boolean contains(String line, String filter) {
        return filter == null || filter.isEmpty() || line.toLowerCase().contains(filter.toLowerCase());
    }

    private <T> T convertAllLines(Function<Stream<String>, T> converter) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream(FILE_NAME),
                StandardCharsets.UTF_8))) {
            return converter.apply(reader.lines());
        } catch (IOException e) {
            throw new IllegalStateException("failed");
        }
    }
}
