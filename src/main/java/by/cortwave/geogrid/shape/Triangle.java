package by.cortwave.geogrid.shape;

/**
 * @author Dmitry Pranchuk
 * @since 5/8/17.
 */
public class Triangle {
    public Triangle(Point a, Point b, Point c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public final Point a;
    public final Point b;
    public final Point c;

    /**
     * Checks if triangle intersects with circle
     *
     * @param circleCenter center of circle
     * @param radius radius of circle in metres
     * @return is circle intersects with triangular
     */
    public boolean intersectsWithCircle(Point circleCenter, double radius) {
        return Math.abs(circleCenter.getCrossTrackDistance(a, b)) <= radius || Math.abs(circleCenter.getCrossTrackDistance(b, c)) <= radius ||
                Math.abs(circleCenter.getCrossTrackDistance(c, a)) <= radius;
    }
}
