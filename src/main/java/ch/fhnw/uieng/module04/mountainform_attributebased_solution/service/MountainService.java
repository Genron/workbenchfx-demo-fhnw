package ch.fhnw.uieng.module04.mountainform_attributebased_solution.service;

/**
 * @author Dieter Holz
 */
public interface MountainService {
    MountainDetailDTO get(long id);

    void save(MountainDetailDTO dto);

    int getTotalCount();
}
