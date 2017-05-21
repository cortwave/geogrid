package by.cortwave.geogrid.shape;

import java.util.ArrayList;
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
        List<GeoPoint> polygon =  triangles.stream()
                .map(Triangle::getCenter)
                .collect(Collectors.toList());
        return sortByDistance(polygon);
    }

    private List<GeoPoint> sortByDistance(List<GeoPoint> points) {
        List<GeoPoint> sorted = new ArrayList<>(points.size());
        GeoPoint firstPoint = points.get(0);
        points.remove(firstPoint);
        sorted.add(firstPoint);
        GeoPoint prevPoint = firstPoint;
        while(points.size() > 0) {
            GeoPoint nearest = null;
            double nearestDistance = 0;
            for(GeoPoint p: points) {
                double distanceToPrev = p.getDistanceTo(prevPoint);
                if(nearest == null || nearestDistance > distanceToPrev) {
                    nearest = p;
                    nearestDistance = distanceToPrev;
                }
            }
            points.remove(nearest);
            prevPoint = nearest;
            sorted.add(nearest);
        }
        return sorted;
    }

    @Override
    public String toString() {
        return "Hex{" + getId() +  "}";
    }
}
