package by.cortwave.geogrid.shape;

/**
 * @author Dmitry Pranchuk
 * @since 5/8/17.
 */
public class Triangle {
    public Triangle(GeoPoint a, GeoPoint b, GeoPoint c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public final GeoPoint a;
    public final GeoPoint b;
    public final GeoPoint c;

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
     * Checks if point inside triangle
     *
     * @param geoPoint point
     * @return is point inside triangle
     */
    public boolean isPointInside(GeoPoint geoPoint) {
        boolean orientationA = geoPoint.getCrossTrackDistance(a, b) > 0;
        boolean orientationB = geoPoint.getCrossTrackDistance(b, c) > 0;
        boolean orientationC = geoPoint.getCrossTrackDistance(c, a) > 0;
        return (orientationA == orientationB) && (orientationB == orientationC);
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
}
