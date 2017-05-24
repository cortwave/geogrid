package by.cortwave.geogrid;

import by.cortwave.geogrid.shape.GeoPoint;
import by.cortwave.geogrid.shape.Hex;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author Dmitry Pranchuk
 * @since 5/9/17.
 */
public class HexGridTest {
    @Test
    public void getZoneAt() throws Exception {
        List<GeoPoint> points = Arrays.asList(new GeoPoint(51.631907, 69.882734), new GeoPoint(53.870121, 27.550767),
                new GeoPoint(39.822484, -89.721609), new GeoPoint(-12.173478, -54.291088), new GeoPoint(-9.780389, 29.084406),
                new GeoPoint(-24.190612, 144.314865));
        printPoints(points);
    }

    @Test
    public void generateZones() throws Exception {
        double delta = 1;
        int radius = 10;
        GeoPoint point0 = new GeoPoint(51.631907, 69.882734);
        List<GeoPoint> points = generateNeighbours(point0, delta, radius);
        printPoints(points);
    }

    private List<GeoPoint> generateNeighbours(GeoPoint point, double delta, int radius) {
        List<GeoPoint> points = new ArrayList<>();
        for (int i = -radius; i <= radius; i++) {
            for (int j = -radius; j <= radius; j++) {
                points.add(new GeoPoint(point.lat + i * delta, point.lon + j * delta));
            }
        }
        return points;
    }

    private void printPoints(List<GeoPoint> points) {
        Grid<Hex> grid = new HexGrid(8);
        Map<String, Hex> hexes = new HashMap<>();
        for (GeoPoint point : points) {
            Hex hex = grid.getZoneAt(point);
            hexes.put(hex.getId(), hex);

        }
        hexes.values().forEach(hex -> {
            System.out.print("[");
            hex.getPolygon().stream()
                    .map(x -> "(" + x.lat + "," + x.lon + ")")
                    .reduce((a, b) -> a + ", " + b)
                    .ifPresent(System.out::println);
            System.out.print("],");
        });
        Hex hex = hexes.values().iterator().next();
        System.out.println(hex.getPolygon().get(0).getDistanceTo(hex.getPolygon().get(1)));
    }
}