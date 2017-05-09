package by.cortwave.geogrid;

import by.cortwave.geogrid.shape.GeoPoint;
import by.cortwave.geogrid.shape.Zone;

/**
 * @author Dmitry Pranchuk
 * @since 5/9/17.
 */
public interface Grid<T extends Zone> {
    T getZoneAt(GeoPoint point);
}
