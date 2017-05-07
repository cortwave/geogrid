package by.cortwave.geogrid.util;

import by.cortwave.geogrid.TestUtil;
import by.cortwave.geogrid.model.Point;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * @author Dmitry Pranchuk
 * @since 5/7/17.
 */
public class GeoUtilTest {
    @Test
    public void getMiddlePointTest() {
        class TestCase {
            private TestCase(Point pointA, Point pointB, Point middlePoint) {
                this.pointA = pointA;
                this.pointB = pointB;
                this.middlePoint = middlePoint;
            }

            private Point pointA;
            private Point pointB;
            private Point middlePoint;
        }

        List<TestCase> testCases = Arrays.asList(
                new TestCase(new Point(0, 0), new Point(0, 90), new Point(0, 45)),
                new TestCase(new Point(0, 0), new Point(90, 0), new Point(45, 0)),
                new TestCase(new Point(54.307829, 27.263027), new Point(43.622513, 105.903936), new Point(55.944408868324984, 71.61309793716599)),
                new TestCase(new Point(-7.065273, -40.711679), new Point(-38.726086, -73.231210), new Point(-23.732961109037166, -54.97116026977392)),
                new TestCase(new Point(34.904545, -117.291397), new Point(48.568881, -65.869824), new Point(44.678439991822614, -94.5267862057571)),
                new TestCase(new Point(-8.546770, 37.077698), new Point(-30.310225, 138.907860), new Point(-29.1399616045078, 83.22081823946012)),
                new TestCase(new Point(0, 45), new Point(0, 90), new Point(0, 67.5))
        );

        for (TestCase testCase : testCases) {
            Point middlePoint = GeoUtil.getMiddlePoint(testCase.pointA, testCase.pointB);
            Point reversedMiddlePoint = GeoUtil.getMiddlePoint(testCase.pointB, testCase.pointA);
            TestUtil.assertEquals(testCase.middlePoint, middlePoint);
            TestUtil.assertEquals(testCase.middlePoint, reversedMiddlePoint);
        }
    }
}