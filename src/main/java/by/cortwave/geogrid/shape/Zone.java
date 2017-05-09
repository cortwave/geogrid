package by.cortwave.geogrid.shape;

import by.cortwave.geogrid.shape.GeoPoint;

import java.util.List;

/**
 * @author Dmitry Pranchuk
 * @since 5/9/17.
 */
public interface Zone {
    String getId();
    List<GeoPoint> getPolygon();
}
