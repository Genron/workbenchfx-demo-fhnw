package ch.fhnw.uieng.module04.mountainform_attributebased_solution.service;

/**
 * @author Dieter Holz
 */

public class MountainDetailDTO {

    private final Long id;
    private final String name;
    private final double height;
    private final String type;
    private final String region;
    private final String cantons;
    private final String range;
    private final double isolation;
    private final String isolationPoint;
    private final double prominence;
    private final String prominencePoint;
    private final String caption;

    public MountainDetailDTO(String... args) {
        id = Long.parseLong(args[0]);
        name = args[1];
        height = Double.parseDouble(args[2]);
        type = args[3];
        region = args[4];
        cantons = args[5];
        range = args[6];
        isolation = Double.parseDouble(args[7]);
        isolationPoint = args[8];
        prominence = Double.parseDouble(args[9]);
        prominencePoint = args[10];
        caption = args[11];
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getHeight() {
        return height;
    }

    public String getType() {
        return type;
    }

    public String getRegion() {
        return region;
    }

    public String getCantons() {
        return cantons;
    }

    public String getRange() {
        return range;
    }

    public double getIsolation() {
        return isolation;
    }

    public String getIsolationPoint() {
        return isolationPoint;
    }

    public double getProminence() {
        return prominence;
    }

    public String getProminencePoint() {
        return prominencePoint;
    }

    public String getCaption() {
        return caption;
    }

    public String toLine(String delimiter) {
        return String.join(delimiter,
                Long.toString(getId()),
                getName(),
                Double.toString(getHeight()),
                getType(),
                getRegion(),
                getCantons(),
                getRange(),
                Double.toString(getIsolation()),
                getIsolationPoint(),
                Double.toString(getProminence()),
                getProminencePoint(),
                getCaption());
    }
}
