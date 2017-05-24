package by.cortwave.geogrid.shape;

import java.util.List;

/**
 * @author Dmitry Pranchuk
 * @since 5/9/17.
 */
public interface Zone {
    /**
     * Get unique zone identifier
     *
     * @return zone id
     */
    String getId();

    /**
     * Get list of points which are vertexes of polygon of this zone
     *
     * @return vertexes of polygon
     */
    List<GeoPoint> getPolygon();
}
