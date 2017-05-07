package by.cortwave.geogrid.util;

import by.cortwave.geogrid.constant.GeoConstants;
import by.cortwave.geogrid.model.Point;

import static java.lang.Math.atan;
import static java.lang.Math.cos;
import static java.lang.Math.pow;
import static java.lang.Math.sin;
import static java.lang.Math.sqrt;
import static java.lang.Math.toDegrees;
import static java.lang.Math.toRadians;
import static java.lang.Math.atan2;

/**
 * Util class for calculating geometric values on the sphere
 * <p>
 * More info about formulas can be found here http://www.movable-type.co.uk/scripts/latlong.html
 *
 * @author Dmitry Pranchuk
 * @since 5/7/17.
 */
public class GeoUtil {
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

    public static double getDistance(Point pointA, Point pointB) {
        double dLat = toRadians(pointA.lat - pointB.lat);
        double dLong = toRadians(pointA.lon - pointB.lon);
        double a = pow(sin(dLat / 2), 2) + cos(toRadians(pointA.lat)) * cos(toRadians(pointB.lat)) * pow(sin(dLong / 2), 2);
        double c = 2 * atan2(sqrt(a), sqrt(1 - a));
        return GeoConstants.MEAN_EARTH_RADIUS_IN_METRES * c;
    }
}
