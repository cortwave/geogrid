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
}
