package by.cortwave.geogrid;

import by.cortwave.geogrid.shape.Point;
import org.junit.Assert;

/**
 * @author Dmitry Pranchuk
 * @since 5/7/17.
 */
public class TestUtil {
    private final static double DEGREE_EQUALS_DELTA = 1e-9;

    public static void assertEquals(Point pointA, Point pointB) {
        Assert.assertEquals(pointA.lat, pointB.lat, DEGREE_EQUALS_DELTA);
        Assert.assertEquals(pointA.lon, pointB.lon, DEGREE_EQUALS_DELTA);
    }
}
