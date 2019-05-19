package ch.fhnw.uieng.module03.lazylist_filtered.mountainapp.service.serviceimpl;

import ch.fhnw.uieng.module03.lazylist_filtered.mountainapp.service.MountainDTO;
import ch.fhnw.uieng.module03.lazylist_filtered.mountainapp.service.PagingService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Dieter Holz
 */
public class MountainServiceFileBased implements PagingService<MountainDTO> {

    private static final String FILE_NAME = "/data/mountains.csv";
    private static final String DELIMITER = ";";
    private static final int NUMBER_OF_COLUMNS = 12;


    @Override
    public List<MountainDTO> getPage(int start, int pageSize) {
        try (BufferedReader reader = getReader(FILE_NAME)) {
            return reader.lines()
                    .skip(start + 1)
                    .map(s -> new MountainDTO(s.split(DELIMITER, NUMBER_OF_COLUMNS)))
                    .limit(pageSize)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new IllegalStateException("failed");
        }
    }

    @Override
    public int getTotalCount() {
        try (BufferedReader reader = getReader(FILE_NAME)) {
            return (int) reader.lines()
                    .skip(1)
                    .count();
        } catch (IOException e) {
            throw new IllegalStateException("failed");
        }
    }

    private BufferedReader getReader(String fileName) {
        return new BufferedReader(new InputStreamReader(getClass().getResourceAsStream(fileName), StandardCharsets.UTF_8));
    }

}
