package ch.fhnw.uieng.module02.cantonfiltered_solution.presentationmodel;

import ch.fhnw.uieng.module02.cantonfiltered_solution.service.CantonDTO;
import javafx.beans.property.*;

/**
 * Das PresentationModel stellt alles was dargestellt werden soll in Form von Properties zur Verfügung.
 * <p>
 * Es ist Bestandteil des PresentationLayers und ist demzufolge an den Bedürfnissen der UIs ausgerichtet.
 * <p>
 * Um moderne UIs umsetzen zu können haben sich observierbare und bindable Properties bewährt.
 * <p>
 * Welche Informationen hier zusammengefasst sind, ist ebenfalls UI-orientiert. Insbesondere orientieren
 * sich PresentationModels nicht an der Datenbankstruktur.
 *
 * @author Dieter Holz
 */
public class CantonPM {
    private final StringProperty kanton = new SimpleStringProperty();
    private final StringProperty kuerzel = new SimpleStringProperty();
    private final LongProperty kantonsnummer = new SimpleLongProperty();
    private final DoubleProperty standesstimme = new SimpleDoubleProperty();
    private final IntegerProperty beitritt = new SimpleIntegerProperty();
    private final StringProperty hauptort = new SimpleStringProperty();
    private final IntegerProperty einwohner = new SimpleIntegerProperty();
    private final DoubleProperty auslaender = new SimpleDoubleProperty();
    private final DoubleProperty flaeche = new SimpleDoubleProperty();
    private final DoubleProperty einwohnerdichte = new SimpleDoubleProperty();
    private final IntegerProperty gemeinden = new SimpleIntegerProperty();
    private final StringProperty amtssprache = new SimpleStringProperty();

    // eine Instanz von CantonPM kriegt man nur ueber die 'of'-Methode
    private CantonPM() {
    }

    public static CantonPM of(CantonDTO dto) {
        CantonPM pm = new CantonPM();
        pm.setKanton(dto.getKanton());
        pm.setKuerzel(dto.getKuerzel());
        pm.setKantonsnummer(dto.getKantonsnummer());
        pm.setStandesstimme(dto.getStandesstimme());
        pm.setBeitritt(dto.getBeitritt());
        pm.setHauptort(dto.getHauptort());
        pm.setEinwohner(dto.getEinwohner());
        pm.setAuslaender(dto.getAuslaender());
        pm.setFlaeche(dto.getFlaeche());
        pm.setEinwohnerdichte(dto.getEinwohnerdichte());
        pm.setGemeinden(dto.getGemeinden());
        pm.setAmtssprache(dto.getAmtssprache());

        return pm;
    }

    //alle Getter und Setter

    public String getKanton() {
        return kanton.get();
    }

    public void setKanton(String kanton) {
        this.kanton.set(kanton);
    }

    public StringProperty kantonProperty() {
        return kanton;
    }

    public String getKuerzel() {
        return kuerzel.get();
    }

    public void setKuerzel(String kuerzel) {
        this.kuerzel.set(kuerzel);
    }

    public StringProperty kuerzelProperty() {
        return kuerzel;
    }

    public long getKantonsnummer() {
        return kantonsnummer.get();
    }

    public void setKantonsnummer(long kantonsnummer) {
        this.kantonsnummer.set(kantonsnummer);
    }

    public LongProperty kantonsnummerProperty() {
        return kantonsnummer;
    }

    public double getStandesstimme() {
        return standesstimme.get();
    }

    public void setStandesstimme(double standesstimme) {
        this.standesstimme.set(standesstimme);
    }

    public DoubleProperty standesstimmeProperty() {
        return standesstimme;
    }

    public int getBeitritt() {
        return beitritt.get();
    }

    public void setBeitritt(int beitritt) {
        this.beitritt.set(beitritt);
    }

    public IntegerProperty beitrittProperty() {
        return beitritt;
    }

    public String getHauptort() {
        return hauptort.get();
    }

    public void setHauptort(String hauptort) {
        this.hauptort.set(hauptort);
    }

    public StringProperty hauptortProperty() {
        return hauptort;
    }

    public int getEinwohner() {
        return einwohner.get();
    }

    public void setEinwohner(int einwohner) {
        this.einwohner.set(einwohner);
    }

    public IntegerProperty einwohnerProperty() {
        return einwohner;
    }

    public double getAuslaender() {
        return auslaender.get();
    }

    public void setAuslaender(double auslaender) {
        this.auslaender.set(auslaender);
    }

    public DoubleProperty auslaenderProperty() {
        return auslaender;
    }

    public double getFlaeche() {
        return flaeche.get();
    }

    public void setFlaeche(double flaeche) {
        this.flaeche.set(flaeche);
    }

    public DoubleProperty flaecheProperty() {
        return flaeche;
    }

    public double getEinwohnerdichte() {
        return einwohnerdichte.get();
    }

    public void setEinwohnerdichte(double einwohnerdichte) {
        this.einwohnerdichte.set(einwohnerdichte);
    }

    public DoubleProperty einwohnerdichteProperty() {
        return einwohnerdichte;
    }

    public int getGemeinden() {
        return gemeinden.get();
    }

    public void setGemeinden(int gemeinden) {
        this.gemeinden.set(gemeinden);
    }

    public IntegerProperty gemeindenProperty() {
        return gemeinden;
    }

    public String getAmtssprache() {
        return amtssprache.get();
    }

    public void setAmtssprache(String amtssprache) {
        this.amtssprache.set(amtssprache);
    }

    public StringProperty amtsspracheProperty() {
        return amtssprache;
    }
}
