package by.cortwave.geogrid.model;

/**
 * @author Dmitry Pranchuk
 * @since 5/7/17.
 */
public class Point {
    public Point(double lat, double lon) {
        this.lat = lat;
        this.lon = lon;
    }

    public final double lat;
    public final double lon;
}
