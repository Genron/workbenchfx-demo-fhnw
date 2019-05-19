package ch.fhnw.uieng.module02.cantonfiltered_solution.service.serviceimpl;

import ch.fhnw.uieng.module02.cantonfiltered_solution.service.CantonDTO;
import ch.fhnw.uieng.module02.cantonfiltered_solution.service.CantonService;

import java.util.List;

/**
 * @author Dieter Holz
 */
public class CantonServiceMemoryBased implements CantonService {

    @Override
    public List<CantonDTO> findAll() {
        return List.of(
                new CantonDTO("Zürich", "ZH", "01", "1", "1351", "Zürich", "1408575", "25.0", "1729", "815", "170", "deutsch"),
                new CantonDTO("Bern", "BE", "02", "1", "1353", "Bern", "992617", "13.8", "5959", "167", "382", "deutsch, französisch"),
                new CantonDTO("Luzern", "LU", "03", "1", "1332", "Luzern", "386082", "16.8", "1493", "259", "87", "deutsch"),
                new CantonDTO("Uri", "UR", "04", "1", "1291", "Altdorf", "35693", "10.7", "1077", "33", "20", "deutsch"),
                new CantonDTO("Schwyz", "SZ", "05", "1", "1291", "Schwyz", "149830", "19.3", "908", "165", "30", "deutsch"),
                new CantonDTO("Obwalden", "OW", "06", "0.5", "1291", "Sarnen", "36115", "13.6", "491", "74", "7", "deutsch"),
                new CantonDTO("Nidwalden", "NW", "07", "0.5", "1291", "Stans", "41584", "12.6", "276", "151", "11", "deutsch"),
                new CantonDTO("Glarus", "GL", "08", "1", "1352", "Glarus", "39369", "21.8", "685", "57", "3", "deutsch"),
                new CantonDTO("Zug", "ZG", "09", "1", "1352", "Zug", "116575", "25.6", "239", "488", "11", "deutsch"),
                new CantonDTO("Freiburg", "FR", "10", "1", "1481", "Freiburg", "291395", "20.6", "1671", "174", "165", "französisch, deutsch"),
                new CantonDTO("Solothurn", "SO", "11", "1", "1481", "Solothurn", "259283", "20.0", "791", "328", "120", "deutsch"),
                new CantonDTO("Basel-Stadt", "BS", "12", "0.5", "1501", "Basel", "194661", "34.4", "37", "5261", "3", "deutsch"),
                new CantonDTO("Basel-Landschaft", "BL", "13", "0.5", "1501", "Liestal", "276537", "20.1", "518", "534", "86", "deutsch"),
                new CantonDTO("Schaffhausen", "SH", "14", "1", "1501", "Schaffhausen", "77955", "24.4", "298", "262", "27", "deutsch"),
                new CantonDTO("Appenzell Ausserrhoden", "AR", "15", "0.5", "1513", "Herisau, Trogen", "53438", "14.6", "243", "220", "20", "deutsch"),
                new CantonDTO("Appenzell Innerrhoden", "AI", "16", "0.5", "1513", "Appenzell", "15789", "9.9", "173", "91", "6", "deutsch"),
                new CantonDTO("St. Gallen", "SG", "17", "1", "1803", "St. Gallen", "487060", "22.6", "2026", "240", "85", "deutsch"),
                new CantonDTO("Graubünden", "GR", "18", "1", "1803", "Chur", "193920", "17.3", "7105", "27", "176", "deutsch, rätoromanisch, italienisch"),
                new CantonDTO("Aargau", "AG", "19", "1", "1803", "Aarau", "627893", "23.0", "1404", "447", "219", "deutsch"),
                new CantonDTO("Thurgau", "TG", "20", "1", "1803", "Frauenfeld", "256213", "23.1", "991", "259", "80", "deutsch"),
                new CantonDTO("Tessin", "TI", "21", "1", "1803", "Bellinzona", "341652", "27.4", "2812", "121", "147", "italienisch"),
                new CantonDTO("Waadt", "VD", "22", "1", "1803", "Lausanne", "729971", "32.1", "3212", "227", "326", "französisch"),
                new CantonDTO("Wallis", "VS", "23", "1", "1815", "Sitten", "321732", "22.0", "5224", "62", "141", "französisch, deutsch"),
                new CantonDTO("Neuenburg", "NE", "24", "1", "1815", "Neuenburg", "174554", "24.6", "803", "217", "53", "französisch"),
                new CantonDTO("Genf", "GE", "25", "1", "1815", "Genf", "476024", "37.2", "282", "1688", "45", "französisch"),
                new CantonDTO("Jura", "JU", "26", "1", "1979", "Delsberg", "70942", "13.1", "838", "85", "64", "französisch")
        );
    }
}
