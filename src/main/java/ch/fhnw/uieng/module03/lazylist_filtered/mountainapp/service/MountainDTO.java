package ch.fhnw.uieng.module03.lazylist_filtered.mountainapp.service;

/**
 * @author Dieter Holz
 */

public class MountainDTO {

    private final Long id;
    private final String name;
    private final double height;
    private final String region;

    public MountainDTO(String... args) {
        id = Long.parseLong(args[0]);
        name = args[1];
        height = Double.parseDouble(args[2]);
        region = args[4];
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

    public String getRegion() {
        return region;
    }
}
