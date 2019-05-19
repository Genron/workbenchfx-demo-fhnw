package ch.fhnw.uieng.module04.mountainform_attributebased_solution.service.serviceimpl;

import ch.fhnw.uieng.module04.mountainform_attributebased_solution.service.MountainDetailDTO;
import ch.fhnw.uieng.module04.mountainform_attributebased_solution.service.MountainService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * @author Dieter Holz
 */
public class MountainServiceImpl implements MountainService {
    private static final String FILE_NAME = "/data/mountains.csv";
    private static final String DELIMITER = ";";
    private static final int NUMBER_OF_COLUMNS = 12;
    private static final int ID_COLUMN = 0;

    @Override
    public MountainDetailDTO get(long id) {
        try (BufferedReader reader = getReader(FILE_NAME)) {
            return reader.lines()
                    .skip(1)
                    .map(line -> splitLine(line))
                    .filter(data -> data[ID_COLUMN].equals(Long.toString(id)))
                    .map(MountainDetailDTO::new)
                    .findFirst()
                    .orElseThrow(NoSuchElementException::new);
        } catch (IOException e) {
            throw new IllegalStateException("failed");
        }
    }


    @Override
    public void save(MountainDetailDTO dto) {
        String idStr = Long.toString(dto.getId());
        List<String> linesToSave = new ArrayList<>();
        try (BufferedReader reader = getReader(FILE_NAME)) {
            reader.lines()
                    .forEach(line -> {
                        String[] data = splitLine(line);
                        if (data[ID_COLUMN].equals(idStr)) {
                            linesToSave.add(dto.toLine(DELIMITER));
                        } else {
                            linesToSave.add(line);
                        }
                    });
        } catch (IOException e) {
            throw new IllegalStateException("reload failed");
        }

        try {
            Files.write(getPath(FILE_NAME), linesToSave);
        } catch (IOException e) {
            throw new IllegalStateException("save failed");
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


    private Path getPath(String fileName) {
        try {
            return Paths.get(getClass().getResource(fileName).toURI());
        } catch (URISyntaxException e) {
            throw new IllegalArgumentException(e);
        }
    }

    private String[] splitLine(String line) {
        return line.split(DELIMITER, NUMBER_OF_COLUMNS);
    }

}
