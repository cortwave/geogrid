package by.cortwave.geogrid;

import by.cortwave.geogrid.shape.GeoPoint;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author Dmitry Pranchuk
 * @since 5/7/17.
 */
public class TestUtil {
    private final static double DEGREE_EQUALS_DELTA = 1e-9;

    public static void assertEquals(GeoPoint geoPointA, GeoPoint geoPointB) {
        Assert.assertEquals(geoPointA.lat, geoPointB.lat, DEGREE_EQUALS_DELTA);
        Assert.assertEquals(geoPointA.lon, geoPointB.lon, DEGREE_EQUALS_DELTA);
    }
}
