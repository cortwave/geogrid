package by.cortwave.geogrid.util;

import by.cortwave.geogrid.model.Point;

/**
 * @author Dmitry Pranchuk
 * @since 5/7/17.
 */
public class GeoUtil {
    public static Point getMiddlePoint(Point pointA, Point pointB) {
        double latA = Math.toRadians(pointA.lat);
        double latB = Math.toRadians(pointB.lat);
        double longA = Math.toRadians(pointA.lon);
        double longB = Math.toRadians(pointB.lon);
        double dLong = longB - longA;
        double longMiddle = longA + Math.atan(Math.cos(latB) * Math.sin(dLong) / (Math.cos(latA) + Math.cos(latB) * Math.cos(dLong)));
        double latMiddle = Math.atan((Math.sin(latA) + Math.sin(latB)) / Math.sqrt(Math.pow(Math.cos(latA) +
                Math.cos(latB) * Math.cos(dLong), 2.0D) + Math.pow(Math.cos(latB) * Math.sin(dLong), 2.0D)));
        return new Point(Math.toDegrees(latMiddle), Math.toDegrees(longMiddle));
    }
}
