package by.cortwave.geogrid.shape;

import by.cortwave.geogrid.constant.GeoConstants;

import static java.lang.Math.PI;
import static java.lang.Math.asin;
import static java.lang.Math.atan;
import static java.lang.Math.atan2;
import static java.lang.Math.cos;
import static java.lang.Math.pow;
import static java.lang.Math.sin;
import static java.lang.Math.sqrt;
import static java.lang.Math.toDegrees;
import static java.lang.Math.toRadians;

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

    /**
     * Calculates middle point on great-circle path to other point
     *
     * @param point other point
     * @return middle point
     */
    public Point getMiddlePointTo(Point point) {
        double latA = toRadians(this.lat);
        double latB = toRadians(point.lat);
        double longA = toRadians(this.lon);
        double longB = toRadians(point.lon);
        double dLong = longB - longA;
        double longMiddle = longA + atan(cos(latB) * sin(dLong) / (cos(latA) + cos(latB) * cos(dLong)));
        double latMiddle = atan((sin(latA) + sin(latB)) / sqrt(pow(cos(latA) + cos(latB) * cos(dLong), 2.0D)
                + pow(cos(latB) * sin(dLong), 2.0D)));
        return new Point(toDegrees(latMiddle), toDegrees(longMiddle));
    }

    /**
     * Calculates shortest distance between this and other points (great-circle path) in radians
     *
     * @param point other point
     * @return distance between points in radians
     */
    public double getAngularDistanceTo(Point point) {
        double dLat = toRadians(this.lat - point.lat);
        double dLong = toRadians(this.lon - point.lon);
        double a = pow(sin(dLat / 2), 2) + cos(toRadians(this.lat)) * cos(toRadians(point.lat)) * pow(sin(dLong / 2), 2);
        return 2 * atan2(sqrt(a), sqrt(1 - a));
    }

    /**
     * Calculates shortest distance between this and other points (great-circle path) in metres
     *
     * @param point other point
     * @return distance between points in metres
     */
    public double getDistanceTo(Point point) {
        return GeoConstants.MEAN_EARTH_RADIUS_IN_METRES * getAngularDistanceTo(point);
    }

    /**
     * Calculates initial bearing (forward azimuth) in radians
     *
     * @param point other point
     * @return initial bearing in radians
     */
    public double getBearingTo(Point point) {
        double latA = toRadians(this.lat);
        double latB = toRadians(point.lat);
        double longA = toRadians(this.lon);
        double longB = toRadians(point.lon);
        double dLong = longB - longA;
        double bearing = atan2(sin(dLong) * cos(latB), cos(latA) * sin(latB) - sin(latA) * cos(latB) * cos(dLong));
        return (bearing + 2 * PI) % (2 * PI);
    }

    /**
     * Calculates cross-track distance (cross-track error) in metres.
     * Cross-track distance is distance from point to great-circle path
     *
     * @param pointA great-circle path start
     * @param pointB great-circle path end
     * @return cross-track distance in metres
     */
    public double getCrossTrackDistance(Point pointA, Point pointB) {
        return asin(sin(getAngularDistanceTo(pointA)) * sin(pointA.getBearingTo(this) - pointA.getBearingTo(pointB))) * GeoConstants.MEAN_EARTH_RADIUS_IN_METRES;
    }
}
