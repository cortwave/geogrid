package by.cortwave.geogrid.shape;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author Dmitry Pranchuk
 * @since 5/8/17.
 */
public class TriangleTest {
    @Test
    public void intersectsWithCircleTest() {
        class TestCase {
            private TestCase(Triangle triangle, Point center, double radius, boolean result) {
                this.triangle = triangle;
                this.center = center;
                this.radius = radius;
                this.result = result;
            }

            private Triangle triangle;
            private Point center;
            private double radius;
            private boolean result;
        }

        List<TestCase> testCases = Arrays.asList(
                new TestCase(new Triangle(new Point(62.871692, 22.356582), new Point(41.272043, -1.373887), new Point(44.772492, 49.295058)),
                        new Point(61.371999, 10.974746),
                        1000000.0,
                        true
                ),
                new TestCase(new Triangle(new Point(62.871692, 22.356582), new Point(41.272043, -1.373887), new Point(44.772492, 49.295058)),
                        new Point(44.278054, -101.626254),
                        1000000.0,
                        false
                )
        );
        for(TestCase testCase: testCases) {
            Assert.assertEquals(testCase.result, testCase.triangle.intersectsWithCircle(testCase.center, testCase.radius));
        }
    }

}