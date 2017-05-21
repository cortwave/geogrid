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
public class TriangleTest {
    @Test
    public void intersectsWithCircleTest() {
        class TestCase {
            private TestCase(Triangle triangle, GeoPoint center, double radius, boolean result) {
                this.triangle = triangle;
                this.center = center;
                this.radius = radius;
                this.result = result;
            }

            private Triangle triangle;
            private GeoPoint center;
            private double radius;
            private boolean result;
        }

        List<TestCase> testCases = Arrays.asList(
                new TestCase(new Triangle(new GeoPoint(62.871692, 22.356582), new GeoPoint(41.272043, -1.373887), new GeoPoint(44.772492, 49.295058), "1"),
                        new GeoPoint(61.371999, 10.974746),
                        1000000.0,
                        true),
                new TestCase(new Triangle(new GeoPoint(62.871692, 22.356582), new GeoPoint(41.272043, -1.373887), new GeoPoint(44.772492, 49.295058), "1"),
                        new GeoPoint(44.278054, -101.626254),
                        1000000.0,
                        false)
        );
        for (TestCase testCase : testCases) {
            Assert.assertEquals(testCase.result, testCase.triangle.intersectsWithCircle(testCase.center, testCase.radius));
        }
    }

    @Test
    public void isPointInsideTest() {
        class TestCase {
            private TestCase(Triangle triangle, GeoPoint point, boolean result) {
                this.triangle = triangle;
                this.point = point;
                this.result = result;
            }

            private Triangle triangle;
            private GeoPoint point;
            private boolean result;
        }

        List<TestCase> testCases = Arrays.asList(
                new TestCase(new Triangle(new GeoPoint(65.261136, 18.908054), new GeoPoint(38.511113, -3.238962), new GeoPoint(39.080154, 43.732182), "1"),
                        new GeoPoint(48.855187, 17.326124),
                        true),
                new TestCase(new Triangle(new GeoPoint(65.261136, 18.908054), new GeoPoint(38.511113, -3.238962), new GeoPoint(39.080154, 43.732182), "1"),
                        new GeoPoint(59.095086, 80.968374),
                        false),
                new TestCase(new Triangle(new GeoPoint(65.261136, 18.908054), new GeoPoint(38.511113, -3.238962), new GeoPoint(39.080154, 43.732182), "1"),
                        new GeoPoint(16.332001, 12.520270),
                        false),
                new TestCase(new Triangle(new GeoPoint(65.261136, 18.908054), new GeoPoint(38.511113, -3.238962), new GeoPoint(39.080154, 43.732182), "1"),
                        new GeoPoint(49.060369, -25.624261),
                        false)
        );

        for (TestCase testCase : testCases) {
            Assert.assertEquals(testCase.result, testCase.triangle.isPointInside(testCase.point));
        }
    }

    @Test
    public void getCenterTest() {
        class TestCase {
            private TestCase(Triangle triangle, GeoPoint center) {
                this.triangle = triangle;
                this.center = center;
            }

            private Triangle triangle;
            private GeoPoint center;
        }

        List<TestCase> testCases = Arrays.asList(
                new TestCase(new Triangle(new GeoPoint(-18.742777, -55.257133), new GeoPoint(-18.835921, -55.314301), new GeoPoint(-18.832588, -55.201208), "1"),
                        new GeoPoint(-18.803756471738208, -55.25754688430901)),
                new TestCase(new Triangle(new GeoPoint(51.836852, 13.990265), new GeoPoint(51.761543, 13.922953), new GeoPoint(51.762179, 14.057576), "1"),
                        new GeoPoint(51.78684414554892, 13.990264350242164))
        );

        for (TestCase testCase : testCases) {
            TestUtil.assertEquals(testCase.center, testCase.triangle.getCenter());
        }
    }

    @Test
    public void splitByMiddleLinesTest() {
        Triangle triangle = new Triangle(new GeoPoint(65.261136, 18.908054), new GeoPoint(38.511113, -3.238962), new GeoPoint(39.080154, 43.732182), "T");
        List<Triangle> splittedTriangles = triangle.splitByMiddleLines();

        TestUtil.assertEquals(splittedTriangles.get(0).a, new GeoPoint(65.261136, 18.908054));
        TestUtil.assertEquals(splittedTriangles.get(0).b, new GeoPoint(52.35920313565145, 4.439812989521824));
        TestUtil.assertEquals(splittedTriangles.get(0).c, new GeoPoint(52.765289886638556, 35.09086252092489));
        TestUtil.assertEquals(splittedTriangles.get(1).a, new GeoPoint(52.35920313565145, 4.439812989521824));
        TestUtil.assertEquals(splittedTriangles.get(1).b, new GeoPoint(38.511113, -3.238962));
        TestUtil.assertEquals(splittedTriangles.get(1).c, new GeoPoint(41.23466486219862, 20.147225449769866));
        TestUtil.assertEquals(splittedTriangles.get(2).a, new GeoPoint(52.765289886638556, 35.09086252092489));
        TestUtil.assertEquals(splittedTriangles.get(2).b, new GeoPoint(41.23466486219862, 20.147225449769866));
        TestUtil.assertEquals(splittedTriangles.get(2).c, new GeoPoint(39.080154, 43.732182));
        TestUtil.assertEquals(splittedTriangles.get(3).a, new GeoPoint(52.765289886638556, 35.09086252092489));
        TestUtil.assertEquals(splittedTriangles.get(3).b, new GeoPoint(52.35920313565145, 4.439812989521824));
        TestUtil.assertEquals(splittedTriangles.get(3).c, new GeoPoint(41.23466486219862, 20.14722544976986));
    }
}