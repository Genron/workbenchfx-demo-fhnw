package ch.fhnw.uieng.module02.cantonfiltered_solution.service;

/**
 * Data Transfer Object für Canton
 * <p>
 * Typisch für DTOs:
 * - die Felder sind primitive Datentypen
 * - DTOs können nicht nach der Initialisierung nicht verändert werden (Immutable Objects)
 * - Die Lebensdauer eines DTOs ist kurz. Im PresentationLayer werden mit den Daten des DTOs die PresentationModels befüllt.
 * Danach werden die DTOs nicht mehr benötigt.
 *
 * @author Dieter Holz
 */

public class CantonDTO {
    private final String kanton;
    private final String kuerzel;
    private final long kantonsnummer;
    private final double standesstimme;
    private final int beitritt;
    private final String hauptort;
    private final int einwohner;
    private final double auslaender;
    private final double flaeche;
    private final double einwohnerdichte;
    private final int gemeinden;
    private final String amtssprache;

    public CantonDTO(String... args) {
        kanton = args[0];
        kuerzel = args[1];

        kantonsnummer = Long.parseLong(args[2]);
        standesstimme = Double.parseDouble(args[3]);

        beitritt = Integer.parseInt(args[4]);
        hauptort = args[5];

        einwohner = Integer.parseInt(args[6]);
        auslaender = Double.parseDouble(args[7]);
        flaeche = Double.parseDouble(args[8]);
        einwohnerdichte = Double.parseDouble(args[9]);

        gemeinden = Integer.parseInt(args[10]);
        amtssprache = args[11];
    }

    // DTOs werden nicht mehr veraendert. Deshalb ausschliesslich getter-Methoden
    public String getKanton() {
        return kanton;
    }

    public String getKuerzel() {
        return kuerzel;
    }

    public long getKantonsnummer() {
        return kantonsnummer;
    }

    public double getStandesstimme() {
        return standesstimme;
    }

    public int getBeitritt() {
        return beitritt;
    }

    public String getHauptort() {
        return hauptort;
    }

    public int getEinwohner() {
        return einwohner;
    }

    public double getAuslaender() {
        return auslaender;
    }

    public double getFlaeche() {
        return flaeche;
    }

    public double getEinwohnerdichte() {
        return einwohnerdichte;
    }

    public int getGemeinden() {
        return gemeinden;
    }

    public String getAmtssprache() {
        return amtssprache;
    }

}
