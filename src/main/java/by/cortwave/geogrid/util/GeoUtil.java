package by.cortwave.geogrid.util;

import by.cortwave.geogrid.constant.GeoConstants;
import by.cortwave.geogrid.model.Point;

import static java.lang.Math.atan;
import static java.lang.Math.atan2;
import static java.lang.Math.cos;
import static java.lang.Math.pow;
import static java.lang.Math.sin;
import static java.lang.Math.sqrt;
import static java.lang.Math.toDegrees;
import static java.lang.Math.toRadians;
import static java.lang.Math.PI;

/**
 * Util class for calculating geometric values on the sphere
 * <p>
 * More info about formulas can be found here http://www.movable-type.co.uk/scripts/latlong.html
 *
 * @author Dmitry Pranchuk
 * @since 5/7/17.
 */
public class GeoUtil {
    /**
     * Calculates middle point on great-circle path
     *
     * @param pointA start point
     * @param pointB end point
     * @return middle point
     */
    public static Point getMiddlePoint(Point pointA, Point pointB) {
        double latA = toRadians(pointA.lat);
        double latB = toRadians(pointB.lat);
        double longA = toRadians(pointA.lon);
        double longB = toRadians(pointB.lon);
        double dLong = longB - longA;
        double longMiddle = longA + atan(cos(latB) * sin(dLong) / (cos(latA) + cos(latB) * cos(dLong)));
        double latMiddle = atan((sin(latA) + sin(latB)) / sqrt(pow(cos(latA) + cos(latB) * cos(dLong), 2.0D)
                + pow(cos(latB) * sin(dLong), 2.0D)));
        return new Point(toDegrees(latMiddle), toDegrees(longMiddle));
    }

    /**
     * Calculates shortest distance between two points (great-circle path) in metres
     *
     * @param pointA start point
     * @param pointB end point
     * @return distance between points in metres
     */
    public static double getDistance(Point pointA, Point pointB) {
        double dLat = toRadians(pointA.lat - pointB.lat);
        double dLong = toRadians(pointA.lon - pointB.lon);
        double a = pow(sin(dLat / 2), 2) + cos(toRadians(pointA.lat)) * cos(toRadians(pointB.lat)) * pow(sin(dLong / 2), 2);
        double c = 2 * atan2(sqrt(a), sqrt(1 - a));
        return GeoConstants.MEAN_EARTH_RADIUS_IN_METRES * c;
    }

    /**
     * Calculates initial bearing (forward azimuth) in radians
     *
     * @param pointA start point
     * @param pointB end point
     * @return initial bearing in radians
     */
    public static double getBearing(Point pointA, Point pointB) {
        double latA = toRadians(pointA.lat);
        double latB = toRadians(pointB.lat);
        double longA = toRadians(pointA.lon);
        double longB = toRadians(pointB.lon);
        double dLong = longB - longA;
        double bearing =  atan2(sin(dLong) * cos(latB), cos(latA) * sin(latB) - sin(latA) * cos(latB) * cos(dLong));
        return (bearing + 2 * PI) % (2 * PI);
    }
}
