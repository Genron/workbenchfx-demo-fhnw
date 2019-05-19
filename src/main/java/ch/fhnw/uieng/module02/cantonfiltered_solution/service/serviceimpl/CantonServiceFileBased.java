package ch.fhnw.uieng.module02.cantonfiltered_solution.service.serviceimpl;

import ch.fhnw.uieng.module02.cantonfiltered_solution.service.CantonDTO;
import ch.fhnw.uieng.module02.cantonfiltered_solution.service.CantonService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Dieter Holz
 */
public class CantonServiceFileBased implements CantonService {

    private static final String FILE_NAME = "/data/cantons.csv";
    private static final String DELIMITER = ";";

    @Override
    public List<CantonDTO> findAll() {
        try (BufferedReader reader = getReader(FILE_NAME)) {                    // try-with-resources schliesst automatisch den Stream
            return reader.lines()
                    .skip(1)                                               // erste Zeile ist die Headerzeile; ueberspringen
                    .map(line -> new CantonDTO(line.split(DELIMITER, 12))) // aus jeder Zeile ein DTO machen
                    .collect(Collectors.toList());                         // alles aufsammeln
        } catch (IOException e) {
            throw new IllegalStateException("failed");
        }
    }

    private BufferedReader getReader(String fileName) {
        return new BufferedReader(new InputStreamReader(getClass().getResourceAsStream(fileName), StandardCharsets.UTF_8));
    }
}
