package by.cortwave.geogrid.shape;

import by.cortwave.geogrid.constant.GeoConstants;

import static java.lang.Math.asin;
import static java.lang.Math.atan2;
import static java.lang.Math.toDegrees;

/**
 * @author Dmitry Pranchuk
 * @since 5/8/17.
 */
public class CartesianPoint {
    public CartesianPoint(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public final double x;
    public final double y;
    public final double z;

    /**
     * Converses to spherical lat-long coordinates
     *
     * @return point in spherical lat-long coordinates
     */
    public GeoPoint toGeoPoint() {
        double lat = asin(z / GeoConstants.MEAN_EARTH_RADIUS_IN_METRES);
        double lon = atan2(y, x);
        return new GeoPoint(toDegrees(lat), toDegrees(lon));
    }
}
