package by.cortwave.geogrid.shape;

import by.cortwave.geogrid.constant.GeoConstants;

import static java.lang.Math.PI;
import static java.lang.Math.asin;
import static java.lang.Math.atan2;
import static java.lang.Math.cos;
import static java.lang.Math.pow;
import static java.lang.Math.sin;
import static java.lang.Math.sqrt;
import static java.lang.Math.toDegrees;
import static java.lang.Math.toRadians;

/**
 * Geo point shape with lat, long
 * <p>
 * See here for more formulas http://www.movable-type.co.uk/scripts/latlong.html
 *
 * @author Dmitry Pranchuk
 * @since 5/7/17.
 */
public class GeoPoint {
    public GeoPoint(double lat, double lon) {
        this.lat = lat;
        this.lon = lon;
    }

    public final double lat;
    public final double lon;

    /**
     * Calculates middle point on great-circle path to other point
     *
     * @param geoPoint other point
     * @return middle point
     */
    public GeoPoint getMiddlePointTo(GeoPoint geoPoint) {
        // corner case check
        if (this.lat == 90 || geoPoint.lat == 90 || this.lat == -90 || geoPoint.lat == -90) {
            return new GeoPoint((this.lat + geoPoint.lat) / 2, (this.lon + geoPoint.lon) / 2);
        }
        double latA = toRadians(this.lat);
        double latB = toRadians(geoPoint.lat);
        double longA = toRadians(this.lon);
        double longB = toRadians(geoPoint.lon);
        double dLong = longB - longA;
        double latMiddle = atan2(sin(latA) + sin(latB), sqrt((cos(latA) + cos(latB) * cos(dLong)) * (cos(latA) + cos(latB) * cos(dLong)) + pow(cos(latB), 2) * pow(sin(dLong), 2)));
        double longMiddle = longA + atan2(cos(latB) * sin(dLong), cos(latA) + cos(latB) * cos(dLong));
        return new GeoPoint(toDegrees(latMiddle), toDegrees(longMiddle));
    }

    /**
     * Calculates shortest distance between this and other points (great-circle path) in radians
     *
     * @param geoPoint other point
     * @return distance between points in radians
     */
    public double getAngularDistanceTo(GeoPoint geoPoint) {
        double dLat = toRadians(this.lat - geoPoint.lat);
        double dLong = toRadians(this.lon - geoPoint.lon);
        double a = pow(sin(dLat / 2), 2) + cos(toRadians(this.lat)) * cos(toRadians(geoPoint.lat)) * pow(sin(dLong / 2), 2);
        return 2 * atan2(sqrt(a), sqrt(1 - a));
    }

    /**
     * Calculates shortest distance between this and other points (great-circle path) in metres
     *
     * @param geoPoint other point
     * @return distance between points in metres
     */
    public double getDistanceTo(GeoPoint geoPoint) {
        return GeoConstants.MEAN_EARTH_RADIUS_IN_METRES * getAngularDistanceTo(geoPoint);
    }

    /**
     * Calculates initial bearing (forward azimuth) in radians
     *
     * @param geoPoint other point
     * @return initial bearing in radians
     */
    public double getBearingTo(GeoPoint geoPoint) {
        double latA = toRadians(this.lat);
        double latB = toRadians(geoPoint.lat);
        double longA = toRadians(this.lon);
        double longB = toRadians(geoPoint.lon);
        double dLong = longB - longA;
        double bearing = atan2(sin(dLong) * cos(latB), cos(latA) * sin(latB) - sin(latA) * cos(latB) * cos(dLong));
        return (bearing + 2 * PI) % (2 * PI);
    }

    /**
     * Calculates cross-track distance (cross-track error) in metres.
     * Cross-track distance is distance from point to great-circle path.
     * The sign of result tells which side of the path the point is on.
     *
     * @param geoPointA great-circle path start
     * @param geoPointB great-circle path end
     * @return cross-track distance in metres
     */
    public double getCrossTrackDistance(GeoPoint geoPointA, GeoPoint geoPointB) {
        return asin(sin(geoPointA.getAngularDistanceTo(this)) * sin(geoPointA.getBearingTo(this) - geoPointA.getBearingTo(geoPointB))) * GeoConstants.MEAN_EARTH_RADIUS_IN_METRES;
    }

    /**
     * Converse to cartesian coordinate system in metres
     *
     * @return point in cartesian coordinate system
     */
    public CartesianPoint toCartesianPoint() {
        double latR = toRadians(lat);
        double longR = toRadians(lon);
        double x = GeoConstants.MEAN_EARTH_RADIUS_IN_METRES * cos(latR) * cos(longR);
        double y = GeoConstants.MEAN_EARTH_RADIUS_IN_METRES * cos(latR) * sin(longR);
        double z = GeoConstants.MEAN_EARTH_RADIUS_IN_METRES * sin(latR);
        return new CartesianPoint(x, y, z);
    }

    @Override
    public String toString() {
        return "GeoPoint{" +
                "lat, lon = " + lat +
                ", " + lon +
                '}';
    }
}
