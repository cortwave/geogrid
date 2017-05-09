package by.cortwave.geogrid.shape;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Dmitry Pranchuk
 * @since 5/9/17.
 */
public class Hex implements Zone {
    private final List<Triangle> triangles;

    public Hex(List<Triangle> triangles) {
        assert triangles.size() == 6;
        triangles.sort(Comparator.comparing(t -> t.id));
        this.triangles = triangles;
    }

    @Override
    public String getId() {
        return triangles.stream()
                .map(Triangle::getId)
                .reduce((a, b) -> a + ":" + b)
                .orElseThrow(() -> new RuntimeException("Unexpected triangles size"));
    }

    @Override
    public List<GeoPoint> getPolygon() {
        return triangles.stream()
                .map(Triangle::getCenter)
                .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return "Hex{" + getId() +  "}";
    }
}
