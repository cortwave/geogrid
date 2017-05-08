package by.cortwave.geogrid.shape;

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
                new TestCase(new Triangle(new GeoPoint(62.871692, 22.356582), new GeoPoint(41.272043, -1.373887), new GeoPoint(44.772492, 49.295058)),
                        new GeoPoint(61.371999, 10.974746),
                        1000000.0,
                        true),
                new TestCase(new Triangle(new GeoPoint(62.871692, 22.356582), new GeoPoint(41.272043, -1.373887), new GeoPoint(44.772492, 49.295058)),
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
                new TestCase(new Triangle(new GeoPoint(65.261136, 18.908054), new GeoPoint(38.511113, -3.238962), new GeoPoint(39.080154, 43.732182)),
                        new GeoPoint(48.855187, 17.326124),
                        true),
                new TestCase(new Triangle(new GeoPoint(65.261136, 18.908054), new GeoPoint(38.511113, -3.238962), new GeoPoint(39.080154, 43.732182)),
                        new GeoPoint(59.095086, 80.968374),
                        false),
                new TestCase(new Triangle(new GeoPoint(65.261136, 18.908054), new GeoPoint(38.511113, -3.238962), new GeoPoint(39.080154, 43.732182)),
                        new GeoPoint(16.332001, 12.520270),
                        false),
                new TestCase(new Triangle(new GeoPoint(65.261136, 18.908054), new GeoPoint(38.511113, -3.238962), new GeoPoint(39.080154, 43.732182)),
                        new GeoPoint(49.060369, -25.624261),
                        false)
        );

        for(TestCase testCase: testCases) {
            Assert.assertEquals(testCase.result, testCase.triangle.isPointInside(testCase.point));
        }
    }
}