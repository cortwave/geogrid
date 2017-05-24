package by.cortwave.geogrid.shape;

import java.util.Arrays;
import java.util.List;

/**
 * @author Dmitry Pranchuk
 * @since 5/8/17.
 */
public class Triangle implements Zone {
    public final GeoPoint a;
    public final GeoPoint b;
    public final GeoPoint c;
    public final String id;
    public Triangle(GeoPoint a, GeoPoint b, GeoPoint c, String id) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.id = id;
    }

    /**
     * Checks if triangle intersects with circle
     *
     * @param circleCenter center of circle
     * @param radius       radius of circle in metres
     * @return is circle intersects with triangular
     */
    public boolean intersectsWithCircle(GeoPoint circleCenter, double radius) {
        return Math.abs(circleCenter.getCrossTrackDistance(a, b)) <= radius || Math.abs(circleCenter.getCrossTrackDistance(b, c)) <= radius ||
                Math.abs(circleCenter.getCrossTrackDistance(c, a)) <= radius;
    }

    /**
     * Returns center of triangle
     * Works good only for small triangles where we can ignore spherical distortion
     *
     * @return center of triangle
     */
    public GeoPoint getCenter() {
        CartesianPoint pointA = a.toCartesianPoint();
        CartesianPoint pointB = b.toCartesianPoint();
        CartesianPoint pointC = c.toCartesianPoint();
        CartesianPoint center = new CartesianPoint((pointA.x + pointB.x + pointC.x) / 3,
                (pointA.y + pointB.y + pointC.y) / 3,
                (pointA.z + pointB.z + pointC.z) / 3);
        return center.toGeoPoint();
    }

    /**
     * Splits triangle using middle lines
     *
     * @return splitted triangles
     */
    public List<Triangle> splitByMiddleLines() {
        GeoPoint ab = a.getMiddlePointTo(b);
        GeoPoint bc = b.getMiddlePointTo(c);
        GeoPoint ca = c.getMiddlePointTo(a);
        return Arrays.asList(new Triangle(a, ab, ca, id + "1"),
                new Triangle(ab, b, bc, id + "2"),
                new Triangle(ca, bc, c, id + "3"),
                new Triangle(ca, ab, bc, id + "4"));
    }


    /**
     * Checks if triangle can contains part (or whole) of hex which contains point.
     *
     * @param point point which belongs to hex
     * @return result of check
     */
    public boolean isPartOfHex(GeoPoint point) {
        double ac = a.getDistanceTo(c);
        return point.getDistanceTo(getCenter()) < 1.5 * ac;
    }

    /**
     * Calculates distance in metres from point to closest triangle vertex
     *
     * @param point point to calculate distance from
     * @return distance to closest vertex
     */
    public double getDistanceToClosestVertex(GeoPoint point) {
        return Math.min(a.getDistanceTo(point), Math.min(b.getDistanceTo(point), c.getDistanceTo(point)));
    }

    @Override
    public String toString() {
        return "Triangle{" +
                "a=" + a +
                ", b=" + b +
                ", c=" + c +
                ", id='" + id + '\'' +
                '}';
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public List<GeoPoint> getPolygon() {
        return Arrays.asList(a, b, c);
    }
}
