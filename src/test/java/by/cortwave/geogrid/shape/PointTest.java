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
public class PointTest {
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
            Point middlePoint = testCase.pointA.getMiddlePointTo(testCase.pointB);
            Point reversedMiddlePoint = testCase.pointB.getMiddlePointTo(testCase.pointA);
            TestUtil.assertEquals(testCase.middlePoint, middlePoint);
            TestUtil.assertEquals(testCase.middlePoint, reversedMiddlePoint);
        }
    }

    @Test
    public void getDistanceTest() {
        double delta = 50;
        Assert.assertEquals(5553800, new Point(54.307829, 27.263027).getDistanceTo(new Point(43.622513, 105.903936)), delta);
        Assert.assertEquals(4796090, new Point(-7.065273, -40.711679).getDistanceTo(new Point(-38.726086, -73.231210)), delta);
        Assert.assertEquals(4434120, new Point(34.904545, -117.291397).getDistanceTo(new Point(48.568881, -65.869824)), delta);
        Assert.assertEquals(10645800, new Point(-8.546770, 37.077698).getDistanceTo(new Point(-30.310225, 138.907860)), delta);
    }

    @Test
    public void getBearingTest() {
        double delta = 1e-2;
        Assert.assertEquals(Math.toRadians(68), new Point(54.307829, 27.263027).getBearingTo(new Point(43.622513, 105.903936)), delta);
        Assert.assertEquals(Math.toRadians(218), new Point(-7.065273, -40.711679).getBearingTo(new Point(-38.726086, -73.231210)), delta);
        Assert.assertEquals(Math.toRadians(54), new Point(34.904545, -117.291397).getBearingTo(new Point(48.568881, -65.869824)), delta);
        Assert.assertEquals(Math.toRadians(121.5), new Point(-8.546770, 37.077698).getBearingTo(new Point(-30.310225, 138.907860)), delta);
    }

    @Test
    public void getAngularDistanceTest() {
        double delta = 1e-6;
        Assert.assertEquals(0.8717358727393598, new Point(54.307829, 27.263027).getAngularDistanceTo(new Point(43.622513, 105.903936)), delta);
        Assert.assertEquals(0.7527981024177604, new Point(-7.065273, -40.711679).getAngularDistanceTo(new Point(-38.726086, -73.231210)), delta);
        Assert.assertEquals(0.6959834406457822, new Point(34.904545, -117.291397).getAngularDistanceTo(new Point(48.568881, -65.869824)), delta);
        Assert.assertEquals(1.6709816538873894, new Point(-8.546770, 37.077698).getAngularDistanceTo(new Point(-30.310225, 138.907860)), delta);
    }
}