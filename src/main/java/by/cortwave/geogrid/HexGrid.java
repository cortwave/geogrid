package by.cortwave.geogrid;

import by.cortwave.geogrid.shape.GeoPoint;
import by.cortwave.geogrid.shape.Hex;
import by.cortwave.geogrid.shape.Triangle;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Dmitry Pranchuk
 * @since 5/9/17.
 */
public class HexGrid implements Grid<Hex> {
    private final int detalizationLevel;
    private final List<Triangle> firstDetalizationLevel = Arrays.asList(
            new Triangle(new GeoPoint(0, 0), new GeoPoint(90, 0), new GeoPoint(0, 90), "NE1"),
            new Triangle(new GeoPoint(0, 90), new GeoPoint(90, 0), new GeoPoint(0, 180), "NE2"),
            new Triangle(new GeoPoint(0, 0), new GeoPoint(90, 0), new GeoPoint(0, -90), "NW1"),
            new Triangle(new GeoPoint(0, -90), new GeoPoint(90, 0), new GeoPoint(0, -180), "NW2"),
            new Triangle(new GeoPoint(0, 0), new GeoPoint(-90, 0), new GeoPoint(0, 90), "SE1"),
            new Triangle(new GeoPoint(0, 90), new GeoPoint(-90, 0), new GeoPoint(0, 180), "SE2"),
            new Triangle(new GeoPoint(0, 0), new GeoPoint(-90, 0), new GeoPoint(0, -90), "SW1"),
            new Triangle(new GeoPoint(0, -90), new GeoPoint(-90, 0), new GeoPoint(0, -180), "SW2")
    );

    public HexGrid(int detalizationLevel) {
        assert (detalizationLevel >= 1);
        this.detalizationLevel = detalizationLevel;
    }

    @Override
    public Hex getZoneAt(GeoPoint point) {
        int currentDetalization = 1;
        Stream<Triangle> sortedTriangles = firstDetalizationLevel.stream();
        while (currentDetalization < detalizationLevel) {
            sortedTriangles = sortedTriangles
                    .flatMap(t -> t.splitByMiddleLines().stream())
                    .filter(t -> t.isPartOfHex(point));
            currentDetalization += 1;
        }
        List<Triangle> hexTriangles = sortedTriangles
                .sorted(Comparator.comparingDouble(t -> t.getDistanceToClosestVertex(point)))
                .limit(6)
                .collect(Collectors.toList());
        return new Hex(hexTriangles);
    }
}
