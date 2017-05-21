package by.cortwave.geogrid.shape;

import by.cortwave.geogrid.TestUtil;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * @author Dmitry Pranchuk
 * @since 5/8/17.
 */
public class GeoPointTest {
    @Test
    public void getMiddlePointTest() {
        class TestCase {
            private TestCase(GeoPoint pointA, GeoPoint pointB, GeoPoint middlePoint) {
                this.pointA = pointA;
                this.pointB = pointB;
                this.middlePoint = middlePoint;
            }

            private GeoPoint pointA;
            private GeoPoint pointB;
            private GeoPoint middlePoint;

            @Override
            public String toString() {
                return "TestCase{" +
                        "pointA=" + pointA +
                        ", pointB=" + pointB +
                        ", middlePoint=" + middlePoint +
                        '}';
            }
        }

        List<TestCase> testCases = Arrays.asList(
                new TestCase(new GeoPoint(0, 0), new GeoPoint(0, 90), new GeoPoint(0, 45)),
                new TestCase(new GeoPoint(0, 0), new GeoPoint(90, 0), new GeoPoint(45, 0)),
                new TestCase(new GeoPoint(90, 0), new GeoPoint(0, 0), new GeoPoint(45, 0)),
                new TestCase(new GeoPoint(54.307829, 27.263027), new GeoPoint(43.622513, 105.903936), new GeoPoint(55.944408868324984, 71.61309793716599)),
                new TestCase(new GeoPoint(-7.065273, -40.711679), new GeoPoint(-38.726086, -73.231210), new GeoPoint(-23.732961109037166, -54.97116026977392)),
                new TestCase(new GeoPoint(34.904545, -117.291397), new GeoPoint(48.568881, -65.869824), new GeoPoint(44.678439991822614, -94.5267862057571)),
                new TestCase(new GeoPoint(-8.546770, 37.077698), new GeoPoint(-30.310225, 138.907860), new GeoPoint(-29.1399616045078, 83.22081823946012)),
                new TestCase(new GeoPoint(0, 45), new GeoPoint(0, 90), new GeoPoint(0, 67.5))
        );

        for (TestCase testCase : testCases) {
            GeoPoint middleGeoPoint = testCase.pointA.getMiddlePointTo(testCase.pointB);
            GeoPoint reversedMiddleGeoPoint = testCase.pointB.getMiddlePointTo(testCase.pointA);
            System.out.println(testCase);
            TestUtil.assertEquals(testCase.middlePoint, middleGeoPoint);
            TestUtil.assertEquals(testCase.middlePoint, reversedMiddleGeoPoint);
        }
    }

    @Test
    public void getDistanceTest() {
        double delta = 50;
        Assert.assertEquals(5553800, new GeoPoint(54.307829, 27.263027).getDistanceTo(new GeoPoint(43.622513, 105.903936)), delta);
        Assert.assertEquals(4796090, new GeoPoint(-7.065273, -40.711679).getDistanceTo(new GeoPoint(-38.726086, -73.231210)), delta);
        Assert.assertEquals(4434120, new GeoPoint(34.904545, -117.291397).getDistanceTo(new GeoPoint(48.568881, -65.869824)), delta);
        Assert.assertEquals(10645800, new GeoPoint(-8.546770, 37.077698).getDistanceTo(new GeoPoint(-30.310225, 138.907860)), delta);
    }

    @Test
    public void getBearingTest() {
        double delta = 1e-2;
        Assert.assertEquals(Math.toRadians(68), new GeoPoint(54.307829, 27.263027).getBearingTo(new GeoPoint(43.622513, 105.903936)), delta);
        Assert.assertEquals(Math.toRadians(218), new GeoPoint(-7.065273, -40.711679).getBearingTo(new GeoPoint(-38.726086, -73.231210)), delta);
        Assert.assertEquals(Math.toRadians(54), new GeoPoint(34.904545, -117.291397).getBearingTo(new GeoPoint(48.568881, -65.869824)), delta);
        Assert.assertEquals(Math.toRadians(121.5), new GeoPoint(-8.546770, 37.077698).getBearingTo(new GeoPoint(-30.310225, 138.907860)), delta);
    }

    @Test
    public void getAngularDistanceTest() {
        double delta = 1e-6;
        Assert.assertEquals(0.8717358727393598, new GeoPoint(54.307829, 27.263027).getAngularDistanceTo(new GeoPoint(43.622513, 105.903936)), delta);
        Assert.assertEquals(0.7527981024177604, new GeoPoint(-7.065273, -40.711679).getAngularDistanceTo(new GeoPoint(-38.726086, -73.231210)), delta);
        Assert.assertEquals(0.6959834406457822, new GeoPoint(34.904545, -117.291397).getAngularDistanceTo(new GeoPoint(48.568881, -65.869824)), delta);
        Assert.assertEquals(1.6709816538873894, new GeoPoint(-8.546770, 37.077698).getAngularDistanceTo(new GeoPoint(-30.310225, 138.907860)), delta);
    }

    @Test
    public void toCartesianTest() {
        List<GeoPoint> points = Arrays.asList(new GeoPoint(54.307829, 27.263027),
                new GeoPoint(-7.065273, -40.711679),
                new GeoPoint(34.904545, -117.291397),
                new GeoPoint(-8.546770, 37.077698),
                new GeoPoint(65.261136, 18.908054),
                new GeoPoint(38.511113, -3.238962),
                new GeoPoint(39.080154, 43.732182));

        for (GeoPoint point : points) {
            TestUtil.assertEquals(point.toCartesianPoint().toGeoPoint(), point);
        }
    }
}